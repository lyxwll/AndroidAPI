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
		//触屏事件
		trackBallView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//修改TrackBallview组件的属性
				trackBallView.currentX = (int) event.getX();
				trackBallView.currentY = (int) event.getY();
				//通知重绘
				trackBallView.invalidate();
				//返回true表明处理方法已经处理该事件
				return true;
			}
		});
		
	}
	
}
