package com.cloudy.androidapi.res;

import com.cloudy.androidapi.R;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class TestClipActivity extends FragmentActivity{
	
	//ClipDrawable:它可以在与<片段>元素的XML文件中定义
	ClipDrawable clipDrawable;
	ClipDrawable clipDrawable2;
	ImageView imageView;
	ImageView imageView2;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.clip_layout);
		imageView = (ImageView) findViewById(R.id.clipdrawable);
		clipDrawable = (ClipDrawable) imageView.getDrawable();
		
		imageView2 = (ImageView) findViewById(R.id.clip_image);
		clipDrawable2 = (ClipDrawable) imageView2.getDrawable();
		
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 123:
					clipDrawable.setLevel(clipDrawable.getLevel() + 200);
					clipDrawable2.setLevel(clipDrawable2.getLevel()+200);
					break;
				}
			}
		};
		
		Thread  myThread = new Thread(){
			boolean flag = true;
			@Override
			public void run() {
				while(flag){
					if(clipDrawable.getLevel() < 10000){
						handler.sendEmptyMessage(123);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						flag = false;
					}
				}
			}
		};
		
		myThread.start();
		
	}
	
	
	
	
	

}
