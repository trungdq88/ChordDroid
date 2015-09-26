package com.dqt.libs.chorddroid.components;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import com.dqt.libs.chorddroid.classes.Chord;
import com.dqt.libs.chorddroid.helper.ChordHelper;
import com.dqt.libs.chorddroid.helper.DrawHelper;

import static android.view.TextureView.SurfaceTextureListener;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
/**
 * Using TextureView to gain performance on Android ICS above
 * by combine both benefit of ImageView and SurfaceView
 */
public class ChordTextureView extends TextureView implements SurfaceTextureListener {

    private SurfaceTexture surfaceTexture;
    private String chordName = null;    // Value must be exists in ChordLibrary.baseChords
    private int position = 0;            // Value from 0 to 8: position of chord
    private int transpose = 0;            // Value from 0 to 12: transpose distance

    public ChordTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceTexture = this.getSurfaceTexture();
        setSurfaceTextureListener(this);
    }

    public ChordTextureView(Context context) {
        super(context);
        surfaceTexture = this.getSurfaceTexture();
        setSurfaceTextureListener(this);
    }

    /**
     * different from SurfaceView, in constructor we can get Holder
     * in TextureView, we must wait until SurfaceTexture (equivalent with SurfaceHolder) available
     */
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        surfaceTexture = this.getSurfaceTexture();
        setSurfaceTextureListener(this);
        onDrawing(surfaceTexture);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        onDrawing(surfaceTexture);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    public void onDrawing(SurfaceTexture surfaceTexture) {
        if (chordName != null) {
            Canvas canvas = null;
            try {
                canvas = this.lockCanvas();
                synchronized(surfaceTexture) {
                    onDrawing(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    this.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void onDrawing(Canvas canvas) {
        if (canvas == null) {
            Log.i("Debug", "onDrawing: canvas = null");
        } else {
            if (chordName != null) {
                try {
                    Log.i("Debug", "onDrawing: beginning to draw");
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

    public void drawChord(String chordName, int position, int transpose) {
        String name = ChordHelper.simplifyName(chordName);
        try {
            this.chordName = name;
            this.position = position;
            this.transpose = transpose;
            onDrawing(surfaceTexture);
        } catch (Exception e) {
            Log.i("Debug", "Unable to draw chord: " + chordName + " (" + name + ")");
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
        onDrawing(surfaceTexture);
    }

    /**
     * Draw previous position of current chord
     */
    public void prevPosition() {
        if (--this.position < 0) {
            this.position = 8;
        }
        onDrawing(surfaceTexture);
    }

    /**
     * Display a specific position of current chord
     *
     * @param position
     */
    public void toPosition(int position) {
        if (position >= 0 && position <= 8) {
            this.position = position;
            onDrawing(surfaceTexture);
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
        onDrawing(surfaceTexture);
    }

    /**
     * Display previous chord in scale bar
     */
    public void prevTranspose() {
        this.position = 0;
        if (--this.transpose > 12) {
            this.transpose = 0;
        }
        onDrawing(surfaceTexture);
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
            onDrawing(surfaceTexture);
        }
    }

}
