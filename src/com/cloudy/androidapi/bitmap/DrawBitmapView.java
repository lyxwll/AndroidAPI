package com.cloudy.androidapi.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * 采用双缓冲实现画图板
 *DrawBitmapView
 * @author Administrator
 */
public class DrawBitmapView extends View{
	
	float preX;//前一个坐标的X
	float preY;//前一个坐标的Y
	private Path path;
	public Paint paint = null;
	
	//控件的宽高
	final int VIEW_WIDTH;
	final int VIEW_HEIGHT;
	//定义一个内存中的图片,该图片作为缓冲区
	Bitmap cacheBitmap = null;
	//定义缓冲区的画布对象
	Canvas cacheCanvas = null;

	public DrawBitmapView(Context context) {
		super(context);
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		
		VIEW_WIDTH = windowManager.getDefaultDisplay().getWidth();
		VIEW_HEIGHT = windowManager.getDefaultDisplay().getHeight();
		
		//创建一个与缓冲区大小相等的Bitmap
		cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
		cacheCanvas = new Canvas();//生成一块画布
		path = new Path();
		//将cacheCanvas绘制到内存中的cacheBitmap上
		cacheCanvas.setBitmap(cacheBitmap);
		//设置画笔的颜色
		paint = new Paint(Paint.DITHER_FLAG);//掩码位标志使抖动
		paint.setColor(Color.RED);
		//设置画笔风格
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
		paint.setDither(true);//设置防抖动
		
	}

	/*public DrawBitmapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DrawBitmapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}*/
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//获取拖动事件发生的位置
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			//从最后一个点增加一个二次贝塞尔，接近控制点（X1，Y1），并且在（X2，Y2）结束。
			//如果没有MOVETO（）调用已为这个轮廓，第一点被自动设置为（0,0）。
			path.quadTo(preX, preY, x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(path, paint);
			path.reset();//清除任何直线和曲线的路径，使其空。这不会改变填充类型设置。
			break;
		}
		invalidate();//通知重绘
		//返回true,表示方法已经处理完毕,事件不再下发
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint bmpPaint = new Paint();
		//将cacheBitmap绘制在该View组件上
		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);
		//沿着Path绘制
		canvas.drawPath(path, paint);
		
		
	}
	
	

}
