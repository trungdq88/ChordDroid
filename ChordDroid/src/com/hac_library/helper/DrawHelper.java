package com.hac_library.helper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;

import com.hac_library.classes.Chord;

public class DrawHelper {

	/**
	 * Draw the fret wires and strings
	 * 
	 * @param canvas
	 */
	public static void drawBaseLines(Canvas canvas, int position) {
		int height = canvas.getHeight();
		int width = canvas.getWidth();

		int firstFretStrokeWidth = height / 30;
		int fretStrokeWidth = firstFretStrokeWidth / 3;
		float fretWidth = width * 1.0F / 8;
		float fretHeight = height * 1.0F / 8;

		float imageStartX = width * 1.0F * 1 / 8;
		float imageStopX = width * 1.0F * 6 / 8;
		float imageStartY = height * 1.0F * 3 / 8;
		float imageStopY = height * 1.0F * 7 / 8 + (height * 1 / 8) / 2;

		// White background
		canvas.drawColor(Color.WHITE);

		// Draw border box
		drawBorderBox(canvas);

		// Draw the Nut (The first fret wire - the big line)
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		if (position <= 1) {
			paint.setStrokeWidth(firstFretStrokeWidth);
		} else {
			paint.setStrokeWidth(fretStrokeWidth);
		}
		paint.setAntiAlias(true);
		canvas.drawLine(imageStartX, imageStartY, imageStopX, imageStartY,
				paint);

		// Draw other lines (small)
		// Strings (Vertical lines)
		paint.setStrokeWidth(fretStrokeWidth);
		canvas.drawLine(imageStartX, imageStartY + fretHeight * 1, imageStopX,
				imageStartY + fretHeight * 1, paint);
		canvas.drawLine(imageStartX, imageStartY + fretHeight * 2, imageStopX,
				imageStartY + fretHeight * 2, paint);
		canvas.drawLine(imageStartX, imageStartY + fretHeight * 3, imageStopX,
				imageStartY + fretHeight * 3, paint);
		canvas.drawLine(imageStartX, imageStartY + fretHeight * 4, imageStopX,
				imageStartY + fretHeight * 4, paint);

		// Fret wires (Horizontal lines);
		canvas.drawLine(imageStartX + fretWidth * 0, imageStartY, imageStartX
				+ fretWidth * 0, imageStopY, paint);
		canvas.drawLine(imageStartX + fretWidth * 1, imageStartY, imageStartX
				+ fretWidth * 1, imageStopY, paint);
		canvas.drawLine(imageStartX + fretWidth * 2, imageStartY, imageStartX
				+ fretWidth * 2, imageStopY, paint);
		canvas.drawLine(imageStartX + fretWidth * 3, imageStartY, imageStartX
				+ fretWidth * 3, imageStopY, paint);
		canvas.drawLine(imageStartX + fretWidth * 4, imageStartY, imageStartX
				+ fretWidth * 4, imageStopY, paint);
		canvas.drawLine(imageStartX + fretWidth * 5, imageStartY, imageStartX
				+ fretWidth * 5, imageStopY, paint);

	}

	/**
	 * Draw a black circle with the finger number on stringNum:fretNum Finger
	 * number: 0: No finger 1: Index finger 2: Middle finger 3: Ring finger 4:
	 * Pinky finger (or baby finger)
	 * 
	 * @param canvas
	 * @param string
	 *            integer value from 1 to 6
	 * @param fret
	 *            integer value from -1 to 5
	 * @param finger
	 *            integer value from 0 to 4
	 * @throws Exception
	 */
	public static void drawFingerPosition(Canvas canvas, int stringNum,
			int fretNum, int fingerNum) throws Exception {
		 
		if (stringNum > 0 && stringNum < 7 && fretNum > -2 && fretNum < 6
				&& fingerNum > -2 && fingerNum < 5) {
			// Explain fingerNum > -2: to adapt jtab database.

			int height = canvas.getHeight();
			int width = canvas.getWidth();
			float fretWidth = width * 1.0F / 8;
			float fretHeight = height * 1.0F / 8;

			// TODO: check if fretNum == 0 (no finger string) || fretNum == -1
			// (no sound string)

			// Small trick to take care of -1 fret
			int fretNumPosition = fretNum;
			if (fretNum == -1) {
				fretNumPosition = 0;
			}

			float dotTop = height * 3 / 8 + (height * 1 / 8) / 2
					+ (fretNumPosition - 1) * fretHeight;
			float dotLeft = width * 1 / 8 + (stringNum - 1) * fretWidth;
			float dotRadius = fretHeight * 2 / 5;
			float textSize = (float) Math.sqrt(height * height + width * width) / 16;
			float textTop = dotTop + (height * 1 / 8) / 3;

			if (fretNum > 0) {
				// Draw the black circle
				Paint paint = new Paint();
				paint.setColor(Color.BLACK);
				paint.setAntiAlias(true);
				canvas.drawCircle(dotLeft, dotTop, dotRadius, paint);
			}

			// Draw the finger number
			Paint textPaint = new Paint();
			textPaint.setColor(Color.BLACK);

			String drawText = "";
			if (fretNum > 0) {
				if (fingerNum > 0) {
					drawText = String.valueOf(fingerNum);
					textPaint.setColor(Color.WHITE);
				}
			} else if (fretNum == 0) {
				drawText = "o";
			} else if (fretNum == -1) {
				drawText = "x";
				// textTop =
			}

			textPaint.setTextAlign(Align.CENTER);
			textPaint.setTextSize(textSize);
			canvas.drawText(drawText, dotLeft, textTop, textPaint);

		} else {
			Log.i("Debug", "Parameter validate fail!");
			Log.i("Debug", " string:" + stringNum + " fret:" + fretNum +
					 " finger:" + fingerNum);
			throw new Exception("Parameter validate fail!");
		}
	}

	/**
	 * Draw fret position on the right side
	 * 
	 * @param canvas
	 * @param position
	 *            integer value from 1 to 20
	 */
	public static void drawFretPosition(Canvas canvas, int position) {
		if (position > -1 && position < 21) {

			// Dont display fret 0
			if (position == 0) {
				position = 1;
			}

			int height = canvas.getHeight();
			int width = canvas.getWidth();

			float textLeft = width * 1.0F * 6 / 8;
			float textTop = height * 1.0F * 4 / 8 - (height * 1 / 8) / 5;
			float fretHeight = height * 1.0F / 8;

			float textSize = (float) Math.sqrt(height * height + width * width) / 16;
			Paint paint = new Paint();
			paint.setTextAlign(Align.LEFT);
			paint.setTextSize(textSize);
			canvas.drawText("   " + String.valueOf(position) + " fr", textLeft,
					textTop + fretHeight * 0, paint);
			canvas.drawText("   " + String.valueOf(position + 1) + " fr",
					textLeft, textTop + fretHeight * 1, paint);
			canvas.drawText("   " + String.valueOf(position + 2) + " fr",
					textLeft, textTop + fretHeight * 2, paint);
			canvas.drawText("   " + String.valueOf(position + 3) + " fr",
					textLeft, textTop + fretHeight * 3, paint);
		}
	}

	/**
	 * Draw the big chord name
	 * 
	 * @param canvas
	 * @param name
	 */
	public static void drawChordName(Canvas canvas, String name) {
		int height = canvas.getHeight();
		int width = canvas.getWidth();

		int chordNameTop = height * 2 / 8;
		int chordNameLeft = width / 2;
		float textSize = (float) Math.sqrt(height * height + width * width) / 8;

		Paint paint = new Paint();
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(textSize);
		canvas.drawText(name, chordNameLeft, chordNameTop, paint);

	}

	/**
	 * Draw a border box
	 * 
	 * @param canvas
	 */
	public static void drawBorderBox(Canvas canvas) {
		Paint borderPaint = new Paint();
		borderPaint.setColor(Color.BLACK);
		borderPaint.setStrokeWidth(1);
		borderPaint.setAntiAlias(true);
		// Top line
		canvas.drawLine(0, 0, canvas.getWidth(), 0, borderPaint);
		// Right line
		canvas.drawLine(canvas.getWidth(), 0, canvas.getWidth(),
				canvas.getHeight(), borderPaint);
		// Bottom line
		canvas.drawLine(canvas.getWidth(), canvas.getHeight(), 0,
				canvas.getHeight(), borderPaint);
		// Left line
		canvas.drawLine(0, 0, 0, canvas.getHeight(), borderPaint);
	}

	/**
	 * Draw the whole chord
	 * 
	 * @param canvas
	 * @param chord
	 * @throws Exception
	 */
	public static void drawChord(Canvas canvas, Chord chord) throws Exception {
		int[] frets = chord.getFrets();
		int[] fingers = chord.getFingers();
		ChordHelper.recudeFretPosition(frets, chord);
		int position = chord.getPosition();
		
		drawBaseLines(canvas, position);
		drawChordName(canvas, chord.getName());
		drawFretPosition(canvas, position);
		Log.i("Debug", "position: " + position);
		Log.i("Debug", "frets: " + frets[0] + " " + frets[1] + " " + frets[2] + " " + frets[3] + " " + frets[4] + " " + frets[5] + " ");
		for (int i = 0; i <= 5; ++i) {
			drawFingerPosition(canvas, i + 1, frets[i], fingers[i]);
		}
	}
}
