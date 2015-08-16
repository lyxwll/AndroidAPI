package com.cloudy.androidapi.bitmap;

import java.util.Timer;
import java.util.TimerTask;

import com.cloudy.androidapi.R;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShowWaveActivity extends FragmentActivity {

	private SurfaceHolder holder;
	private Paint paint;
	final int HEIGHT = 700;
	final int WIDTH = 480;
	private int X_OFFSET = 5;
	private int cx = X_OFFSET;
	private int centerY = HEIGHT / 2;
	Timer timer = new Timer();
	TimerTask task = null;
	SurfaceView surfaceView;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.surface_view);
		
		surfaceView = (SurfaceView) findViewById(R.id.surface2);
		// 初始化SurfaceHolder对象
		holder = surfaceView.getHolder();
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(3);
		paint.setAntiAlias(true);// 反锯齿
		// paint.setStyle(Paint.Style.STROKE);
		Button sin = (Button) findViewById(R.id.sin);
		Button cos = (Button) findViewById(R.id.cos);
		sin.setOnClickListener(clickListener);
		cos.setOnClickListener(clickListener);

		holder.addCallback(new Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				timer.cancel();
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				drawBack(holder);// 圆纵坐标
			}
		});
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(final View v) {
			drawBack(holder);
			cx = X_OFFSET;
			if (task != null) {
				task.cancel();
			}
			task = new TimerTask() {

				@Override
				public void run() {
					// 40 250实际上是控制峰值
					// 20 350控制的波长
					int cy = v.getId() == R.id.sin ? 
							centerY - (int) (40 * Math.sin((cx - 5) * 2 * Math.PI / 20)):
							centerY - (int) (250 * Math.cos((cx - 5) * 2* Math.PI / 350));
					if (v.getId() == R.id.cos) {
						System.out.println();
					} else {
						System.out.println();
					}
					Canvas canvas = holder.lockCanvas(new Rect(cx - 2, cy - 2,
							cx + 2, cy + 2));
					canvas.drawPoint(cx, cy, paint);
					cx++;
					holder.unlockCanvasAndPost(canvas);
					if (cx > WIDTH) {
						task.cancel();
						task = null;
					}
				}
			};
			timer.schedule(task, 0, 30);
		}
	};

	private void drawBack(SurfaceHolder holder) {
		Canvas canvas = holder.lockCanvas();
		// 绘制白色背景
		canvas.drawColor(Color.WHITE);
		Paint p = new Paint();
		p.setColor(Color.BLACK);
		p.setStrokeWidth(2);
		// 绘制坐标轴
		canvas.drawLine(X_OFFSET, centerY, WIDTH, centerY, p);// 横轴
		canvas.drawLine(X_OFFSET, 40, X_OFFSET, HEIGHT, p);// 竖轴
		holder.unlockCanvasAndPost(canvas);
		holder.lockCanvas(new Rect(0, 0, 0, 0));
		holder.unlockCanvasAndPost(canvas);
	}

	
	
	
	
}
