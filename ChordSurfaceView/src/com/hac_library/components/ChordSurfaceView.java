package com.hac_library.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.hac_library.classes.Chord;


public class ChordSurfaceView extends SurfaceView implements Callback {

	private SurfaceHolder holder;
	private Chord chord;
	
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
        }
        else {
            onDrawing(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void onDrawing(Canvas canvas) {
    	Log.i("Debug", "Canvas height: " + canvas.getHeight());
    	Log.i("Debug", "Canvas height: " + canvas.getWidth());
        if (chord.getName().equals("Am")) {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setAntiAlias(true);
            canvas.drawCircle(50, 50, 80, paint);
        } else {
            Paint paint = new Paint();
            paint.setColor(Color.CYAN);
            paint.setAntiAlias(true);
            canvas.drawRect(50, 50, 100, 100, paint);
        }
    }

    public void reDraw(Chord chord) {
    	this.chord = chord;
        onDrawing(holder);
    }
    
}
