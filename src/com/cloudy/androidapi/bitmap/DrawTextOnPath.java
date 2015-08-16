package com.cloudy.androidapi.bitmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

public class DrawTextOnPath extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(new TextView(this));
	}
	
	class TextView extends View{
		
		String Draw_TEXT = "疯狂Java讲义";
		Path[] paths = new Path[3];
		Paint paint;

		public TextView(Context context) {
			super(context);
			
			paths[0] = new Path();
			paths[0].moveTo(0, 0);
			for(int i = 7;i <= 7;i++){
				//生成7个点,并随机生成他们的坐标,在连成一条线
				paths[0].lineTo(i*30, (float) (Math.random()*30));
			}
			paths[1] = new Path();
			RectF rectF = new RectF(0, 0, 200, 120);
			paths[1].addOval(rectF, Path.Direction.CCW);
			paths[2] = new Path();
			paths[2].addArc(rectF, 60, 180);
			
			//初始化画笔
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(Color.CYAN);
			paint.setStrokeWidth(2);
			
		}

		public TextView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public TextView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			canvas.drawColor(Color.WHITE);
			canvas.translate(40, 40);
			//设置从右边开始绘制
			paint.setTextAlign(Paint.Align.RIGHT);
			paint.setTextSize(20);
			//绘制路径
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawPath(paths[0], paint);
			//沿着路径绘制一段文本
			paint.setStyle(Paint.Style.FILL);
			canvas.drawTextOnPath(Draw_TEXT, paths[0], -8, 20, paint);
			
			canvas.translate(0, 60);
			//绘制路径
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawPath(paths[1], paint);
			//沿着路径绘制一段文本
			paint.setStyle(Paint.Style.FILL);
			canvas.drawTextOnPath(Draw_TEXT, paths[1], -20, 20, paint);
			
			canvas.translate(0, 120);
			//绘制路径
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawPath(paths[2], paint);
			//沿着路径绘制一段文本
			paint.setStyle(Paint.Style.FILL);
			canvas.drawTextOnPath(Draw_TEXT, paths[2], -10, 20, paint);
			
		}
		
		
		
	}
	

}
