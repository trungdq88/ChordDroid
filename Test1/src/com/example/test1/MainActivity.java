package com.example.test1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hac_library.classes.ChordLibrary;
import com.hac_library.components.ChordSurfaceView;
import com.hac_library.helper.ChordHelper;

public class MainActivity extends Activity {

	ChordSurfaceView chord1, chord2, chord3;
	
	List<String> chords = new ArrayList<String>();
	int currentChord = 0;
	int currentPos = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for (String key : ChordLibrary.baseChords.keySet()) {
			chords.add(key);
		}
		 
		
		// This button is to view all chord
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				currentPos = 0;
				chord2.drawChord(ChordHelper.getChord(chords.get(++currentChord), currentPos));
				
			}
		});
		
		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Log.i("Debug", ChordHelper.getChord("A", 1).toString());
				chord3.drawChord(ChordHelper.getChord(chords.get(currentChord), currentPos++));
				if (currentPos == 10) {
					currentPos = 0;
				}
			}
		});
		
		
		chord1 = ((ChordSurfaceView) findViewById(R.id.chord1));
		chord2 = ((ChordSurfaceView) findViewById(R.id.chord2));
		chord3 = ((ChordSurfaceView) findViewById(R.id.chord3));
	}
	@Override
	protected void onResume() {
		super.onResume();
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
