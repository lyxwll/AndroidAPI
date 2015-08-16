package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarActivity extends Activity{
	
	private SeekBar seekBar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seek_bar_layout);
		
		seekBar = (SeekBar) findViewById(R.id.seekbar1);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.d("seekbar", "onStopTrackingTouch");
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.d("SeekBar", "onStartTrackingTouch");
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Log.d("SeekBar", "progress = "+progress+"fromUser = "+fromUser);
			}
		});
		
		
		
		
	}

}
