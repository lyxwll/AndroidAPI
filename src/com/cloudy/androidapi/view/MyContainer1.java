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
	 * �����¼��ַ�
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.d(TAG, "dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);//Ĭ�ϵķ���ֵ,�൱��false
	}
	
	/**
	 * �����¼�����
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.d(TAG, "onInterceptTouchEvent");
		return super.onInterceptTouchEvent(ev);//Ĭ�ϵķ���ֵ,�൱��false
	}
	
	/**
	 * �����¼�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");
		return super.onTouchEvent(event);//Ĭ�ϵķ���ֵ,�൱��false
	}

}
