package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.view.TrackBallView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TrackBallActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.track_ball);
		
		final TrackBallView trackBallView =(TrackBallView) findViewById(R.id.trackball);
		//final TrackBallView trackBallView =new TrackBallView(this);
		//setContentView(trackBallView);
		//�����¼�
		trackBallView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//�޸�TrackBallview���������
				trackBallView.currentX = (int) event.getX();
				trackBallView.currentY = (int) event.getY();
				//֪ͨ�ػ�
				trackBallView.invalidate();
				//����true�����������Ѿ�������¼�
				return true;
			}
		});
		
	}
	
}
