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
	 * ����false�򽻸��ϲ���������
	 * �������super.dispatchTouchEvent(event) �Ż����onTouchEvent
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.d(TAG, "dispatchTouchEvent");
		return super.dispatchTouchEvent(event);//Ĭ�ϵķ���ֵ,�൱��false
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent");
		return super.onTouchEvent(event);//Ĭ�ϵķ���ֵ,�൱��false
	}
	

}







