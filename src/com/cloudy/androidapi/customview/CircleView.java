package com.cloudy.androidapi.customview;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View{

	private int color;//背景色
	private int width;//
	private int height;//
	private int radius;//半径
	private int centerX;//中心点X
	private int centerY;//中心点Y
	
	public CircleView(Context context) {
		//super(context);
		this(context,null);
	}

	public CircleView(Context context, AttributeSet attrs) {
		//super(context, attrs);
		this(context, attrs, 0);
	}

	public CircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		System.out.println("constructor");
		width = getWidth();
		height =  getHeight();
		System.out.println("width=" + width +";height="+ height);
		//
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
		if(typedArray != null){
			this.radius = typedArray.getInteger(R.styleable.CircleView_radius, -1);
			this.centerX = typedArray.getInteger(R.styleable.CircleView_centerX, -1);
			this.centerY = typedArray.getInteger(R.styleable.CircleView_centerY, -1);
			this.color = typedArray.getInteger(R.styleable.CircleView_bgcolor, Color.WHITE);
		}
		//回收
		typedArray.recycle();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println("onMeasure");
		System.out.println("widthMeasureSpec=" + widthMeasureSpec + ";heightMeasureSpec=" + heightMeasureSpec);
		System.out.println("measureWidth=" + getMeasuredWidth());
		System.out.println("measureHeight=" + getMeasuredHeight());
		//获取到宽高
		width = getMeasuredWidth();
		height = getMeasuredHeight();
		if(radius == -1){
			if(width < height){
				radius = width/2;
			}else{
				radius = height/2;
			}
		}
		if(centerX == -1 || centerY == -1){
			centerX = width/2;
			centerY = height/2;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		System.out.println("onDraw");
		
		Resources resources = getResources();
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setAntiAlias(true);//反锯齿
		paint.setStrokeWidth(2);
		//画圆
		canvas.drawCircle(centerY, centerY, radius, paint);
		
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		System.out.println("onLayout");
		//this.width = right - left;
		//this.height = top - bottom;
	}
	
	
	

}
