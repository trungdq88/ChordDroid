package com.hac_library.classes;

import java.util.Arrays;

public class Chord {
	private String name;
	private int position;
	private int fingers[] = new int[6];
	
	
	public Chord() {
		super();
	}
	public Chord(String name, int position, int[] fingers) {
		super();
		this.name = name;
		this.position = position;
		this.fingers = fingers;
	}
	@Override
	public String toString() {
		return "Chord [name=" + name + ", position=" + position + ", fingers="
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
	
	/**
	 * Transpose a chord
	 * @param distance value from -12 to 12
	 */
	public void transpose(int distance) {
		throw new UnsupportedOperationException();
	}
}
