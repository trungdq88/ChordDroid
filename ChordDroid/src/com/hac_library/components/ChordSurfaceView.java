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
import com.hac_library.helper.DrawHelper;

public class ChordSurfaceView extends SurfaceView implements Callback {

	private SurfaceHolder holder;
	private String chordName = null;

	public ChordSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		holder = this.getHolder();
		holder.addCallback(this);
	}

	public ChordSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		holder = this.getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		onDrawing(holder);
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		onDrawing(holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	public void onDrawing(SurfaceHolder holder) {
		Canvas canvas = holder.lockCanvas();
		if (canvas == null) {
			Log.i("Debug", "canvas is null");
		} else {
			onDrawing(canvas);
			holder.unlockCanvasAndPost(canvas);
		}
	}

	private void onDrawing(Canvas canvas) {
		// int[] frets = new int[]{0,1,2,2,0,0};
		// int[] fingers = new int[]{0,1,3,2,0,0};
		if (chordName != null) {
			Chord c = new Chord(chordName,
					ChordLibrary.baseChords.get(chordName)[0].getPosition(),
					ChordLibrary.baseChords.get(chordName)[0].getFrets(),
					ChordLibrary.baseChords.get(chordName)[0].getFingers());
			try {
				DrawHelper.drawChord(canvas, c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Draw new chord to the Surface
	 * 
	 * @param chord
	 */
	public void reDraw(String chordName) {
		this.chordName = chordName;
		onDrawing(holder);
	}

}
