package com.hac_library.classes;

import java.util.Arrays;

public class Chord {
	private String name;
	private int position;
	private int frets[] = new int[6];
	private int fingers[] = new int[6];
	
	
	public Chord() {
		super();
	}

	public Chord(String name, int position, int[] frets, int[] fingers) {
		super();
		this.name = name;
		this.position = position;
		this.frets = frets;
		this.fingers = fingers;
	}

	
	@Override
	public String toString() {
		return "Chord [name=" + name + ", position=" + position + ", frets="
				+ Arrays.toString(frets) + ", fingers="
				+ Arrays.toString(fingers) + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int[] getFingers() {
		return fingers;
	}
	public void setFingers(int[] fingers) {
		this.fingers = fingers;
	}
	
	public int[] getFrets() {
		return frets;
	}
	public void setFrets(int[] frets) {
		this.frets = frets;
	}
}
