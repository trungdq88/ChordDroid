package com.dqt.libs.chorddroid.components;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.dqt.libs.chorddroid.classes.Chord;
import com.dqt.libs.chorddroid.helper.ChordHelper;
import com.dqt.libs.chorddroid.helper.DrawHelper;

public class ChordSurfaceView extends SurfaceView implements Callback {


    private SurfaceHolder holder;
    private String chordName = null;    // Value must be exists in ChordLibrary.baseChords
    private int position = 0;            // Value from 0 to 8: position of chord
    private int transpose = 0;            // Value from 0 to 12: transpose distance

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

    /**
     * method prepare before draw
     * by get Canvas. Lock it. and Release it before finish all process
     * TODO : can make new thread in this method. so SurfaceView will draw on new thread, different from current thread
     */
    public void onDrawing(SurfaceHolder holder) {
        if (chordName != null) {
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                synchronized(holder) {
                    onDrawing(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    /**
     * method for real drawing
     * is called from @link{onDrawing(SurfaceHolder holder)}
     */
    private void onDrawing(Canvas canvas) {
        if (canvas == null) {
            Log.i("Debug", "onDrawing: canvas = null");
        } else {
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
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////// OVERLOADING METHOD FOR USER CHOOSE HOW TO DRAW CHORD /////////
    ////////////////////////////////////////////////////////////////////////////

    public void drawChord(String chordName, int position, int transpose) {
        String name = ChordHelper.simplifyName(chordName);
        try {
            this.chordName = name;
            this.position = position;
            this.transpose = transpose;
            onDrawing(holder);
        } catch (Exception e) {
            Log.i("Debug", "Unable to draw chord: " + chordName + " (" + name + ")");
        }
    }

    /**
     * Draw a chord to surface view using chord name & position (default
     * transpose = 0)
     *
     */
    public void drawChord(String name, int position) {
        drawChord(name, position, 0);
    }

    /**
     * Draw a chord to surface view using chord name only (default position = 0)
     *
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
     * @param distance value from -12 to 12
     */
    public void transposeTo(int distance) {
        if (distance >= 0 && distance <= 12) {
            this.position = 0;
            this.transpose = distance;
            onDrawing(holder);
        }
    }
}
