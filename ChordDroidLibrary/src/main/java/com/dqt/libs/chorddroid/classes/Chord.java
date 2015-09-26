package com.dqt.libs.chorddroid.classes;

import java.util.Arrays;


public class Chord extends Position{
	public String name;

	public Chord(String name, int position, int[] frets, int[] fingers) {
		super(position, frets, fingers);
		this.name = name;
	}

	public Chord(int fret, int[] frets, int[] fingers) {
		super(fret, frets, fingers);
	}
	public Chord() {
	}

	@Override
	public String toString() {
		return "Chord [name=" + name + "] Position [fret=" + position + ", frets=" + Arrays.toString(frets)
				+ ", fingers=" + Arrays.toString(fingers) + "]";
	}
}
