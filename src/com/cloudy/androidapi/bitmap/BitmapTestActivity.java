package com.cloudy.androidapi.bitmap;

import java.io.IOException;
import java.io.InputStream;

import com.cloudy.androidapi.R;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class BitmapTestActivity extends FragmentActivity{
	
	String[] images = null;
	AssetManager assetManager = null;
	int currentIma = 0;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.bitmap_test);
		
		imageView = (ImageView) findViewById(R.id.bit_imageview);
		try {
			assetManager = getAssets();
			//获取/assets/目录下的所有文件
			images = assetManager.list("");
			//images = assetManager.list("images");//如果在assets下面图片是放在images文件里
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取到btn按钮
		Button button = (Button) findViewById(R.id.bitmap_btn);
		//为btn按钮绑定事件监听器,该监听器将会查看下一张图片
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//如果发生数组越界
				if(currentIma >= images.length){
					currentIma = 0;
				}
				//找到下一个图片文件
				while(!images[currentIma].endsWith(".png") && !images[currentIma].endsWith(".jpg") && !images[currentIma].endsWith(".gif")){
					currentIma++;
					//如果已发生数组越界
					if(currentIma >= images.length){
						currentIma = 0;
					}
				}
				InputStream assetFile = null;
				try {
					//打开指定资源对应的输入流
					assetFile = assetManager.open(images[currentIma++]);
					//如果在assets下面图片是放在images文件里
					//assetFile = assetManager.open("images"+images[currentIma++]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
				//判断当前ImageView所显示的图片是否已被回收,如果该图片还未被回收,系统强制回收该图片
				if(bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled()){
					bitmapDrawable.getBitmap().recycle();
				}
				//改变ImageView显示的图片:调用BitmapFactory从指定的输入流解析并创建Bitmap对象
				imageView.setImageBitmap(BitmapFactory.decodeStream(assetFile));
			}
		});
	}
	
	
	
	

}
