package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ImageView;

public class AbsoluteLayoutActivity extends Activity {

	private ImageView imageview;
	private AbsoluteLayout absoluteLayout;
	private float screenWidth = 480;
	private float screenHeigth = 750;
	private float x = 0;// 当前控件的X坐标
	private float y = 0;// 当前控件的Y坐标
	private float xSpeed = 9.6f;
	private float ySpeed = 14.56f;
	
	//标识是否已经测量过
	private boolean isMeasured = false;  

	//定义了一个接收消息的变量
	private static final int MOVE = 0x1111;
	private static final String TAG = "AbsoluteActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.absolute_layout);

		// absolute_layout.xml中获取ImageView组件
		imageview = (ImageView) findViewById(R.id.image);
		absoluteLayout = (AbsoluteLayout) findViewById(R.id.abs_layout);
		
		//获取到当前视图容器的视图树的观察者类型
		//即获取宽,高:ViewTreeObserver
		ViewTreeObserver observer = absoluteLayout.getViewTreeObserver();
		observer.addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				if(!isMeasured){
					//如果没有被测量,获取测量到的尺寸,也就是宽高
					screenWidth = absoluteLayout.getMeasuredWidth();
					screenHeigth = absoluteLayout.getMeasuredHeight();
					isMeasured = true;
					
					//在logcat中显示宽,高的信息
					Log.d(TAG, "screenWidth="+screenWidth+"screenHeigth="+screenHeigth);
					
					/*
					 * 必须要调用一个start()方法才能启动一个线程
					 * 如果调用run()方法,那么只是把这个Thread类当成了普通类,去执行run()方法,
					 * 而不会新生成一个线程,且run()方法是在主线程里面执行的
					 */
					new RunThread().start();
					
				}
				/*
				 * return ture;当前测量已结束
				 * 视图在未改变之前
				 * 如果是return false;会一直执行测量
				 */
				return true;
				
			}
		});
		
	/*	
		 * 必须要调用一个start()方法才能启动一个线程
		 * 如果调用run()方法,那么只是把这个Thread类当成了普通类,去执行run()方法,
		 * 而不会新生成一个线程,且run()方法是在主线程里面执行的
		 
		new RunThread().start();*/

	}

	class RunThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				x += xSpeed;
				y += ySpeed;
				if (x >= screenWidth && y >= screenHeigth) {
					x = 0;
					y = 0;
				}
				//发消息通知主线程,图片的位置改变了
				handler.sendEmptyMessage(MOVE);

			}
		}

		//消息接收器
		Handler handler = new Handler() {
			//接收消息
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				//接收到图片位置改变的消息
				case MOVE:
					//获取ImageView的布局参数
					//设置ImageView中X,Y的值
					AbsoluteLayout.LayoutParams layoutParams =(LayoutParams) imageview.getLayoutParams();
					layoutParams.x = (int) x;
					layoutParams.y = (int) y;
					/*layoutParams.height = 100;
					layoutParams.width= 40;*/
					
					//重新设置图片的参数
					imageview.setLayoutParams(layoutParams);

					break;

				}
			};
		};
	}
}
