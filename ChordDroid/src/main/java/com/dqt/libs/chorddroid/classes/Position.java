package com.dqt.libs.chorddroid.classes;

import java.util.Arrays;

public class Position {
	public int position;
	public int frets[] = new int[6];
	public int fingers[] = new int[6];

	@Override
	public String toString() {
		return "Position [fret=" + position + ", frets=" + Arrays.toString(frets)
				+ ", fingers=" + Arrays.toString(fingers) + "]";
	}
	public Position(int position, int[] frets, int[] fingers) {
		super();
		this.position = position;
		this.frets = frets;
		this.fingers = fingers;
	}
	public Position() {
	}
	
	
}
