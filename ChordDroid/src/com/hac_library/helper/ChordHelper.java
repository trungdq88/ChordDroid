package com.hac_library.helper;

import java.util.Arrays;

import com.hac_library.classes.Chord;
import com.hac_library.classes.ChordLibrary;
import com.hac_library.classes.Position;

public class ChordHelper {
	/**
	 * Transpose a chord with distance
	 * 
	 * @param chord
	 * @param distance
	 *            value from -12 to 12
	 */
	public static void transpose(Chord chord, int distance) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Move fret: -1 14 15 12 14 12 => -1 3 4 1 3 1
	 * 
	 * @param frets
	 */
	public static void recudeFretPosition(int[] frets) {
		int min = 99;

		// First find the min
		for (int i = 0; i <= 5; ++i) {
			if (frets[i] > -1 && frets[i] < min) {
				min = frets[i];
			}
		}

		if (min > 0) {
			// Subtract all for (min - 1)
			for (int i = 0; i <= 5; ++i) {
				if (frets[i] > -1) {
					frets[i] -= (min - 1);
				}
			}
		}
	}

	/**
	 * Get a chord state by name and fret position
	 * 
	 * @param name
	 * @param position
	 * @return Chord
	 */
	public static Chord getChord(String name, int position) {
		Chord c = new Chord();
		c.setName(name);
		int i = Arrays.asList(ChordLibrary.N).indexOf(name.substring(0, 1));

		String equavilentChord = "";

		if (i > -1) {
			equavilentChord = (ChordLibrary.N[(i + position) % 5] + name
					.substring(1, name.length()));
			c.setPosition(X(i, position, 0));
		} else {
			i = Arrays.asList(ChordLibrary.N).indexOf(
					ChordLibrary.Bname.get(getChordBaseName(name)));
			equavilentChord = (ChordLibrary.N[(i + position) % 5] + getChordBaseNameTail(name));
			c.setPosition(X(i, position,
					ChordLibrary.Bfret.get(equavilentChord)));
		}

		Position[] positions = ChordLibrary.baseChords.get(equavilentChord);
		if (c.getPosition() > 0 && positions.length > 1) {
			c.setFrets(positions[1].getFrets());
			c.setFingers(positions[1].getFingers());
		} else {
			c.setFrets(positions[0].getFrets());
			c.setFingers(positions[0].getFingers());
		}
		return c;
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
			if (name.substring(1, 2) == "#" || name.substring(1, 2) == "b") {
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
			if (name.substring(1, 2) == "#" || name.substring(1, 2) == "b") {
				start = 1;
			}
		}
		return name.substring(start + 1, name.length());
	}
}
