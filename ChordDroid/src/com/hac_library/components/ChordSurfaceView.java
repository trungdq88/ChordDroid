package com.hac_library.components;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.hac_library.classes.Chord;
import com.hac_library.classes.ChordLibrary;
import com.hac_library.helper.ChordHelper;
import com.hac_library.helper.DrawHelper;

public class ChordSurfaceView extends SurfaceView implements Callback {

	private SurfaceHolder holder;
	private String chordName = null;    // Value must be exists in ChordLibrary.baseChords
	private int position = 0;			// Value from 0 to 8: position of chord
	private int transpose = 0;			// Value from 0 to 12: transpose distance

	public ChordSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		holder = this.getHolder();
		holder.addCallback(this);
	}

	public ChordSurfaceView(Context context) {
		super(context);
		holder = this.getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		onDrawing(holder);
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		onDrawing(holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {

	}

	public void onDrawing(SurfaceHolder holder) {
		if (chordName != null) {
			Canvas canvas = holder.lockCanvas();
			if (canvas == null) {
				Log.i("Debug", "canvas is null");
			} else {
				onDrawing(canvas);
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	private void onDrawing(Canvas canvas) {
		if (chordName != null) {
			try {
				Chord chord = ChordHelper.getChord(chordName, position,
						transpose);
				DrawHelper.drawChord(canvas, chord);
			} catch (Exception e) {
				Log.i("Debug",
						"Error when trying to draw a chord: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void drawChord(String chordName, int position, int transpose) {
		String name = ChordHelper.simplifyName(chordName);
		if (ChordLibrary.baseChords.get(name) != null) {
			this.chordName = name;
			this.position = position;
			this.transpose = transpose;
			onDrawing(holder);
		} else {
			Log.i("Debug", "Unsupported chord: " + name);
		}
	}

	/**
	 * Draw a chord to surface view using chord name & position (default
	 * transpose = 0)
	 * 
	 * @param name
	 * @param position
	 */
	public void drawChord(String name, int position) {
		drawChord(name, position, 0);
	}

	/**
	 * Draw a chord to surface view using chord name only (default position = 0)
	 * 
	 * @param name
	 */
	public void drawChord(String name) {
		drawChord(name, 0, 0);
	}

	/**
	 * Draw next position of current chord
	 */
	public void nextPosition() {
		if (++this.position > 8) {
			this.position = 0;
		}
		onDrawing(holder);
	}

	/**
	 * Draw previous position of current chord
	 */
	public void prevPosition() {
		if (--this.position < 0) {
			this.position = 8;
		}
		onDrawing(holder);
	}

	/**
	 * Display a specific position of current chord
	 * 
	 * @param position
	 */
	public void toPosition(int position) {
		if (position >= 0 && position <= 8) {
			this.position = position;
			onDrawing(holder);
		}
	}

	/**
	 * Display next chord in scale bar
	 */
	public void nextTranspose() {
		this.position = 0;
		if (++this.transpose < 0) {
			this.transpose = 12;
		}
		onDrawing(holder);	
	}

	/**
	 * Display previous chord in scale bar
	 */
	public void prevTranspose() {
		this.position = 0;
		if (--this.transpose > 12) {
			this.transpose = 0;
		}
		onDrawing(holder);	
	}

	/**
	 * Dislay a transpose using distance
	 * 
	 * @param distance
	 *            value from -12 to 12
	 */
	public void transposeTo(int distance) {
		if (distance >= 0 && distance <= 12) {
			this.position = 0;
			this.transpose = distance;
			onDrawing(holder);
		}
	}
}
