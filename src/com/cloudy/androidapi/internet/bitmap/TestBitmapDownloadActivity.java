package com.cloudy.androidapi.internet.bitmap;

import com.cloudy.androidapi.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class TestBitmapDownloadActivity extends FragmentActivity{
	
	public static final int LOAD_IMAGE = 123;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.test_url_layout);
		imageView = (ImageView) findViewById(R.id.image_url);
		//String url = "http://192.168.1.219/hanchang/images/advert_manager_bg.jpg";
		//BitmapUtil.getInstance(R.drawable.image_default,R.drawable.image_default).download("hanchang/images/advert_manager_bg.jpg", imageView);
		BitmapUtil.getInstance(R.drawable.image_default,R.drawable.image_default).reDownload("hanchang/images/advert_manager_bg.jpg", imageView);
		
	}
	
	Handler handler = new Handler(){ 
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOAD_IMAGE:
				Bitmap bitmap = (Bitmap) msg.obj;
				imageView.setImageBitmap(bitmap);
				break;
			}
		};
	};

}
