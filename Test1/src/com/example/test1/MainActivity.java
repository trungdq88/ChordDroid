package com.example.test1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hac_library.classes.Chord;
import com.hac_library.components.ChordSurfaceView;

public class MainActivity extends Activity {

	ChordSurfaceView chord1, chord2, chord3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// This button is to ChordSurfaceView.reDraw()
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				chord2.reDraw(new Chord("Am", 1, null, null));
			}
		});
		
		
		chord1 = ((ChordSurfaceView) findViewById(R.id.chord1));
		chord2 = ((ChordSurfaceView) findViewById(R.id.chord2));
		chord3 = ((ChordSurfaceView) findViewById(R.id.chord3));
	}
	@Override
	protected void onResume() {
		chord1.reDraw(new Chord("Am", 0, null, null));
		chord2.reDraw(new Chord("Am", 0, null, null));
		chord3.reDraw(new Chord("Bm", 0, null, null));
		
		super.onResume();
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
