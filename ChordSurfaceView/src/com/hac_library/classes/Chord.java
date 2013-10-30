package com.hac_library.classes;

import java.util.Arrays;


public class Chord extends Position{
	private String name;

	public Chord(String name, int position, int[] frets, int[] fingers) {
		super(position, frets, fingers);
		this.name = name;
	}

	public Chord(int fret, int[] frets, int[] fingers) {
		super(fret, frets, fingers);
	}

	@Override
	public String toString() {
		return "Chord [name=" + name + "] Position [fret=" + getPosition() + ", frets=" + Arrays.toString(getFrets())
				+ ", fingers=" + Arrays.toString(getFingers()) + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
