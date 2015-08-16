package com.cloudy.androidapi.bitmap;

import com.cloudy.androidapi.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;

public class SurfaceViewActivity extends FragmentActivity{
	private SurfaceHolder holder;
	private Paint paint;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.surfrace_view);
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface_view1);
		
		paint = new Paint();
		holder = surfaceView.getHolder();
		holder.addCallback(new Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				
			}
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				//锁定整个surfaceView
				Canvas canvas = holder.lockCanvas();
				//绘制背景
				Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.b2);
				canvas.drawBitmap(back, 0, 0, null);
				holder.unlockCanvasAndPost(canvas);
				//重新锁定一次,避免下次lockCanvas遮挡
				holder.lockCanvas(new Rect(0, 0, 0, 0));
				holder.unlockCanvasAndPost(canvas);
			}
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				System.out.println("surfaceChanged format="+ format + ", width=" + width+",height="+height);
			}
		});
		
		surfaceView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					int cx = (int) event.getX();
					int cy = (int) event.getY();
					//锁定surfaceView的局部区域
					Canvas canvas = holder.lockCanvas(new Rect(cx-50, cy-50, cx+50, cy+50));
				
					//保存canvas的当前状态
					canvas.save();
					canvas.rotate(30, cx, cy);
					paint.setColor(Color.RED);
					canvas.drawRect(cx-40, cy-40, cx,cy, paint);//画上半部分
					
					//恢复canvas之前的状态
					canvas.restore();
					paint.setColor(Color.GREEN);
					canvas.drawRect(cx, cy, cx+40, cy+40, paint);
					
					//绘制完成,释放画布,提交修改
					holder.unlockCanvasAndPost(canvas);
					
				}
				return false;
			}
		});
	}

	
	
	
	
	
	
}
