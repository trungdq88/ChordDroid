package com.hac_library.helper;

import com.hac_library.classes.Chord;

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
	 * @param name
	 * @param position
	 * @return Chord
	 */
	public static Chord getChord(String name, int position) {
		return new Chord();
	}
}
