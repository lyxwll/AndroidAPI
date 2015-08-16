package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;

public class RewardsActivity extends Activity{
	
	private ImageView imageView;
	private boolean isMeasured = false;
	private int width;//刮奖区的宽
	private int height;//刮奖区的高
	
	private Bitmap bitmap;//刮奖区的位图
	private int[] pixels;//刮奖区的位图像素
	
	private int X;//刮奖时触摸点的X坐标
	private int Y;//刮奖时触摸点的X坐标
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rewards_layout);
		imageView = (ImageView) findViewById(R.id.rewards);
		//获得视图的宽高:ViewTreeObserver
		ViewTreeObserver observer = imageView.getViewTreeObserver();
		observer.addOnPreDrawListener(new MyOnPreDrawListener());
		
	}
	
	class MyOnPreDrawListener implements OnPreDrawListener{

		@Override
		public boolean onPreDraw() {
			if(!isMeasured){
				//获取ImageView的宽高
				width = imageView.getMeasuredWidth();
				height = imageView.getMeasuredHeight();
				//初始化像素:生成每个像素点
				pixels = new int[width*height];
				isMeasured = true;
				setImage();
				//设置刮奖事件监听事件
				imageView.setOnTouchListener(new MyOnTouchListener());
			}
			return true;
		}
		
	}
	
	//生成覆盖刮奖区的蒙层
	private void setImage(){
		//创建一个和ImageView宽高一样的位图
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		for(int i=0;i<width*height;i++){
			pixels[i]=0xff747474;
		}
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		imageView.setImageBitmap(bitmap);
	}
	
	//刮奖监听事件的方法
	class MyOnTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				X = (int) event.getX();
				Y = (int) event.getY();
				System.out.println("down x="+X+",Y="+Y);
				break;
				//即进行刮奖
			case MotionEvent.ACTION_MOVE:
				X = (int) event.getX();
				Y = (int) event.getY();
				//检查边界:即刮奖时鼠标是否在此区域
				if(X<0){
					X=0;
				}
				if(Y < 0){
					Y = 0;
				}
				if(X>width-1){
					X = width-1;
				}
				if(Y>height-1){
					Y = height-1;
				}
				//进行蒙层区的像素操作:即把蒙层去除
				for(int i=X-10;i<X+10;i++){
					for(int j = Y-10;j<Y+10;j++){
						//判断i,j是否在图片内
						if(i>=0&&i<width-1&&j>=0&&j<height-1){
							int pixel = 0x00000000;//将刮奖后的地方像素设置为全透明
							bitmap.setPixel(i, j, pixel);
						}
					}
				}
				//即改变了蒙层区的像素
				imageView.setImageBitmap(bitmap);
				break;
			case MotionEvent.ACTION_UP:
				break;
			}
			//
			return true;
		}
	}
}



















