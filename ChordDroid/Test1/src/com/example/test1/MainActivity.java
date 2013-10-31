package com.example.test1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.hac_library.classes.ChordLibrary;
import com.hac_library.components.ChordSurfaceView;

public class MainActivity extends Activity {

	ChordSurfaceView chord2;
	
	List<String> chords = new ArrayList<String>();
	int currentChord = -1;
	int currentPos = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for (String key : ChordLibrary.baseChords.keySet()) {
			chords.add(key);
		}
		 
		
		Button b1 = (Button) findViewById(R.id.btnLoad);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				currentPos = 0;
				String text = ((EditText) findViewById(R.id.editText1)).getText().toString();
				chord2.drawChord(text, currentPos);
			}
		});
		
		Button b2 = (Button) findViewById(R.id.btnNextPos);
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				chord2.nextPosition();
			}
		});
		Button b3 = (Button) findViewById(R.id.btnPrevPos);
		b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				chord2.prevPosition();
			}
		});
		Button b4 = (Button) findViewById(R.id.btnTransNext);
		b4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				chord2.nextTranspose();
			}
		});
		Button b5 = (Button) findViewById(R.id.btnTransPrev);
		b5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				chord2.prevTranspose();
			}
		});
		
		chord2 = ((ChordSurfaceView) findViewById(R.id.chord2));
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
