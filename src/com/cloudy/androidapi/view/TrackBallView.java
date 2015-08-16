package com.cloudy.androidapi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 * 自定义view:用来画圆的
 * 尽量把三个构造器都写出来
 */
public class TrackBallView extends View {
	//圆心坐标
	public int currentX = 100;
	public int currentY = 100;

	//使用new创建 view时;会调用一个参数的构造器,在Log日志中查看
	public TrackBallView(Context context) {
		super(context);
		//this(context,null);
		Log.d(VIEW_LOG_TAG, "TrackBallView(Context context)");
	}

	//使用自定义布局文件,会调用两个参数的构造器
	public TrackBallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//this(context,attrs,0);
		Log.d(VIEW_LOG_TAG, "TrackBallView(Context context, AttributeSet attrs)");
	}

	//在布局文件里使用时,且设置了style属性时,会调用三个参数的构造器
	public TrackBallView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Log.d(VIEW_LOG_TAG, "TrackBallView(Context context, AttributeSet attrs, int defStyle)");
	}

	@SuppressLint("DrawAllocation") 
	////绘制圆的界面
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//生成一个画笔
		Paint paint =new Paint();
		//反锯齿
		paint.setAntiAlias(true);
		//设置画笔颜色
		paint.setColor(Color.BLUE);
		//画圆,半径为:25
		canvas.drawCircle(currentX, currentY, 25, paint);
	}

}
