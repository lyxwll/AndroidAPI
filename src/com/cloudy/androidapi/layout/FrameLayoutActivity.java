package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

@SuppressLint("HandlerLeak") public class FrameLayoutActivity extends Activity {

	int[] images = { R.drawable.z1, R.drawable.z2, R.drawable.z3,
			R.drawable.z4, R.drawable.z5, R.drawable.z6,
			R.drawable.z7, R.drawable.z8, };

	int index = -1;

	public static final int CHANGE_PIC = 0x111;
	private FrameLayout frameLayout;
	// private ImageView imageview;
	//LayoutInflater的作用:把layout文件加载到内存,并转换成一个view
	private LayoutInflater inflater;

	//消息接收器
	Handler handler = new Handler() {
		//接收消息
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CHANGE_PIC:
				System.out.println("received message");
				//布局加载器,将res/layout/imageview.xml;加载到内存中,并转换成view
				ImageView imageView = (ImageView) inflater.inflate(
						R.layout.imageview, null);
				//设置背景图片
				imageView.setBackgroundResource(images[index]);
				//设置ImageView的参数为:包裹内容 WRAP_CONTENT
				imageView.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				//将ImageView添加到framelayout里
				frameLayout.addView(imageView); 
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载布局文件
		setContentView(R.layout.frame_layout);
		//获取布局文件加载器
		inflater = LayoutInflater.from(this);
		//查找布局文件中id为frame_layout的view控件
		frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

		// ImageView imageView = (ImageView) findViewById(R.id.image);
		new RunThread().start();
	}

	class RunThread extends Thread {

		boolean flag = true;

		@Override
		public void run() {
			System.out.println("Thread Running");
			while (flag) {
				if (index < images.length - 1) {
					index++;
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//发出一条空消息
					handler.sendEmptyMessage(CHANGE_PIC);
				} else {
					flag = false;
					//index = 0;
				}
			}
		}

	}

}
