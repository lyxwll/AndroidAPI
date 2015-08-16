package com.cloudy.androidapi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyImageView extends ImageView{
	
	static final String TAG = "MyImageView";

	public MyImageView(Context context) {
		super(context);
	}

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	/**
	 * 返回false则交给上层容器处理
	 * 必须调用super.dispatchTouchEvent(event) 才会调用onTouchEvent
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.d(TAG, "dispatchTouchEvent");
		return super.dispatchTouchEvent(event);//默认的返回值,相当于false
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");
		return super.onTouchEvent(event);//默认的返回值,相当于false
	}
	

}







