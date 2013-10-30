package com.example.test1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.hac_library.classes.Chord;
import com.hac_library.components.ChordSurfaceView;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	@Override
	protected void onResume() {
		((ChordSurfaceView) findViewById(R.id.chord1)).reDraw(new Chord("Am",1, null));
		((ChordSurfaceView) findViewById(R.id.chord2)).reDraw(new Chord("Bm",1, null));
		
		super.onResume();
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
