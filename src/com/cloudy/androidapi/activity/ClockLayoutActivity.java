package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class ClockLayoutActivity extends Activity{
	
	private Chronometer chronometer;
	private Button startbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock_layout);
		
		chronometer = (Chronometer) findViewById(R.id.jishiqi_clock);
		startbtn = (Button) findViewById(R.id.start);
		
		startbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//���û�׼ʱ��
				chronometer.setBase(SystemClock.elapsedRealtime());
				//������ʱ��
				chronometer.start();
				
			}
		});
		chronometer.setOnChronometerTickListener(new OnChronometerTickListener() {
			
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				if(SystemClock.elapsedRealtime()-chronometer.getBase()>5*1000){
					//����5sֹͣ��ʱ
					chronometer.stop();
				}
				
				
			}
		});
		
		
	}

}
