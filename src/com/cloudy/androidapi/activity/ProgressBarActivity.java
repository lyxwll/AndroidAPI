package com.cloudy.androidapi.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarActivity extends Activity{
	
	private TextView showTextView;
	private ProgressBar progressBar1;
	private ProgressBar progressBar2;
	
	private Button button1;
	
	int progress1 = 0;
	int progress2 = 0;
	int titleprogress = 0;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0x124:
				progress1 += 5 ;
				if(progress1 > 100){
					progress1 = 0;
				}
				progressBar1.setProgress(progress1);
				/*titleprogress += 500;
				if(titleprogress > 100){
					titleprogress = 0;
				}*/
				showTextView.setText(getString(R.string.progress_show)+"progressBar1="+progress1
						+"progressBar2="+progress2);
				break;

			case 0x125:
				progress2 += 5 ;
				if(progress2 > 100){
					progress2 = 0;
				}
				progressBar2.setProgress(progress2);
				showTextView.setText(getString(R.string.progress_show)+"progressBar1="+progress1
						+"progressBar2="+progress2);
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.progress_bar_layout);
		
		ProgressBarActivity.this.setProgressBarIndeterminateVisibility(true);
		ProgressBarActivity.this.setProgressBarVisibility(true);
		
		showTextView = (TextView) findViewById(R.id.show);
		progressBar1 = (ProgressBar) findViewById(R.id.progress1);
		progressBar2 = (ProgressBar) findViewById(R.id.progress2);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0x124);
			}
		}, 0,300);
		
		Thread thread = new Thread(){
			public void run() {
				while(true){
					handler.sendEmptyMessage(0x125);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
		
		findViewById(R.id.show_progress).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setProgressBarIndeterminateVisibility(true);
				setProgressBarVisibility(true);
				titleprogress += 500;
				if(titleprogress > 100){
					titleprogress = 0;
				}
				setProgress(titleprogress);
				
			}
		});
		
		findViewById(R.id.hide_progress).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setProgressBarIndeterminateVisibility(false);
				setProgressBarVisibility(false);
			}
		});
		
		
	}

	
	
	
	

}
