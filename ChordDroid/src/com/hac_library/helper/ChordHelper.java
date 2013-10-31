package com.hac_library.helper;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.util.Log;

import com.hac_library.classes.Chord;
import com.hac_library.classes.ChordLibrary;
import com.hac_library.classes.Position;

@SuppressLint("DefaultLocale")
public class ChordHelper {
	/**
	 * Transpose a chord with distance, return the transposed chord name
	 * 
	 * @param chordName
	 * @param distance
	 *            value from -12 to 12
	 */
	public static String transpose(String chordName, int distance) {
		String chord = chordName;
		if (chord == null) {
			return null;
		}
		if (chord.isEmpty()) {
			return null;
		}
		
		chord = chord.toLowerCase();
		// The first letter
		chord = Character.toString(chord.charAt(0)).toUpperCase()+chord.substring(1);
		// The letter after "/" character
		int theChar = chord.indexOf("/");
		if (theChar > -1) {
			chord = chord.substring(0, theChar) + 
					Character.toString(chord.charAt(theChar)).toUpperCase() +
					chord.substring(theChar + 1);
		}
		
		String[] sameScale = new String[]{"Db", "C#", "Eb", "D#", "Gb", "F#", "Ab", "G#", "Bb", "A#"};
		String[] scale = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		Pattern p;
		Matcher m;
		p = Pattern.compile("([DEGAB]b)");
		m = p.matcher(chord);
		while(m.find()) {
		    String token = m.group(1);
		    String newValue = sameScale[(Arrays.asList(sameScale).indexOf(token) + 1)];
		    chord = chord.replaceAll(token, newValue);
		}
		
		////
		
		p = Pattern.compile("([CDEFGAB]#?)");
		m = p.matcher(chord);
		while(m.find()) {
		    String token = m.group(1);
		    //String newValue = sameScale[(Arrays.asList(sameScale).indexOf(token) + 1)];
		    int i = (Arrays.asList(scale).indexOf(token) + distance) % scale.length;
		    String newValue = scale[i < 0 ? i + scale.length : i];
		    chord = chord.replaceAll(token, newValue);
		}
		
		return chord;
	}

	/**
	 * Move fret: -1 14 15 12 14 12 => -1 3 4 1 3 1
	 * 
	 * @param frets
	 * @param c
	 *            use to change chord position variable
	 */
	public static void recudeFretPosition(int[] frets, Chord c) {
		int min = 99;

		// Find the min
		for (int i = 0; i <= 5; ++i) {
			if (frets[i] > -1 && frets[i] < min) {
				min = frets[i];
			}
		}
		Log.i("Debug", "min = " + min);
		// Only reduce for fret 12
		if (min >= 12) {
			// Subtract all for (min - 1)
			for (int i = 0; i <= 5; ++i) {
				if (frets[i] > -1) {
					frets[i] -= (min - 1);
				}
			}
		} else {
			if (min > 1) {
				for (int i = 0; i <= 5; ++i) {
					if (frets[i] > -1) {
						frets[i] -= (min - 1);
					}
				}
				c.setPosition(c.getPosition() + (min - 1));
			}
		}
	}

	public static Chord getChord(String name) {
		return getChord(name, 0);
	}
	
	public static Chord getChord(String name, int position, int transposeDistance) {
		String chordName = transposeDistance != 0 ? transpose(name, transposeDistance) : name;
		Chord chord = getChord(chordName, position);
		return chord;
	}
	
	/**
	 * Get a chord state by name and fret position, and transpose to `transpose` distance.
	 * 
	 * @param name
	 * @param position
	 * @return Chord
	 */
	public static Chord getChord(String name, int position) {
		Chord c = new Chord();
		c.setName(name);
		String originalName = getChordBaseName(name);
		int i = Arrays.asList(ChordLibrary.N).indexOf(originalName);

		String equavilentChord = "";

		if (i > -1) {
			equavilentChord = (ChordLibrary.Bname
					.get(ChordLibrary.N[(i + position) % 5]) + getChordBaseNameTail(name));
			c.setPosition(X(i, position, 0));
		} else {
			i = Arrays.asList(ChordLibrary.N).indexOf(
					ChordLibrary.Bname.get(originalName));
			equavilentChord = (ChordLibrary.Bname
					.get(ChordLibrary.N[(i + position) % 5]) + getChordBaseNameTail(name));
			c.setPosition(X(i, position, ChordLibrary.Bfret.get(originalName)));
		}

		Position[] positions = ChordLibrary.baseChords.get(equavilentChord);
		if (c.getPosition() > 0) {
			if (positions[1] != null) {
				c.setFrets(positions[1].getFrets());
				c.setFingers(positions[1].getFingers());
			} else {
				c.setFrets(positions[0].getFrets());
				c.setFingers(positions[0].getFingers());
				increaseEveryFretsByOne(c);
			}
		} else {
			c.setFrets(positions[0].getFrets());
			c.setFingers(positions[0].getFingers());
		}

		return c;
	}

	public static String simplifyName(String chordName) {
		String chord = chordName;
		chord = chord.toLowerCase();
		// The first letter
		chord = Character.toString(chord.charAt(0)).toUpperCase()+chord.substring(1);
		int theChar = chordName.indexOf("/");
		if (theChar > -1) {
			return chord.substring(0, theChar);
		} else {
			return chord;
		}
	}

	private static void increaseEveryFretsByOne(Chord c) {
		Log.i("Debug", "Increase: fret before:" + Arrays.toString(c.getFrets()));
		int[] newFrets = new int[6];
		for (int i = 0; i <= 5; ++i) {
			if (c.getFrets()[i] > -1) {
				newFrets[i] = c.getFrets()[i] + 1;
			} else {
				newFrets[i] = c.getFingers()[i];
			}
		}
		c.setFrets(newFrets);
	}

	/**
	 * This is a recursive method calculates the fret. G:1 ==> ***3***-E (+3)
	 * 
	 * @param i
	 * @param position
	 * @param defaultValue
	 * @return
	 */
	public static int X(int i, int position, int defaultValue) {
		if (position == 0) {
			return defaultValue;
		} else {
			return X(i, position - 1, defaultValue)
					+ ChordLibrary.F[(position + i - 1) % 5];
		}
	}

	/**
	 * From Abm7 ==> Ab
	 * 
	 * @param name
	 * @return
	 */
	public static String getChordBaseName(String name) {
		String result = name.substring(0, 1);
		if (name.length() > 1) {
			if (name.substring(1, 2).equals("#")
					|| name.substring(1, 2).equals("b")) {
				result += name.substring(1, 2);
			}
		}
		return result;
	}

	/**
	 * From Abm7 ==> m7
	 * 
	 * @param name
	 * @return
	 */
	public static String getChordBaseNameTail(String name) {
		int start = 0;
		if (name.length() > 1) {
			// Log.i("Debug", "substring: " + name.substring(1, 2) +
			// " substring13:" + name.substring(1, 3));
			if (name.substring(1, 2).equals("#")
					|| name.substring(1, 2).equals("b")) {
				start = 1;
			}
		}
		return name.substring(start + 1, name.length());
	}
}
