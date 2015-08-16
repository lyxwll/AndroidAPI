package com.cloudy.androidapi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyContainer1 extends LinearLayout{
	
	static final String TAG = "MyContainer1";
	
	public MyContainer1(Context context) {
		super(context);
	}
	
	public MyContainer1(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 触屏事件分发
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.d(TAG, "dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);//默认的返回值,相当于false
	}
	
	/**
	 * 触屏事件拦截
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.d(TAG, "onInterceptTouchEvent");
		return super.onInterceptTouchEvent(ev);//默认的返回值,相当于false
	}
	
	/**
	 * 触屏事件处理
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");
		return super.onTouchEvent(event);//默认的返回值,相当于false
	}

}
