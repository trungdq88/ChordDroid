package com.hac_library.classes;

import java.util.Arrays;

public class Position {
	private int position;
	private int frets[] = new int[6];
	private int fingers[] = new int[6];
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int[] getFrets() {
		return frets;
	}
	public void setFrets(int[] frets) {
		this.frets = frets;
	}
	public int[] getFingers() {
		return fingers;
	}
	public void setFingers(int[] fingers) {
		this.fingers = fingers;
	}
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
		super();
	}
	
	
}
