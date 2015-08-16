package com.cloudy.androidapi.gesture;

import com.cloudy.androidapi.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ImageScaleGestrue extends FragmentActivity{
	
	ImageView imageView;//图片控件
	Bitmap bitmap;//位图图片
	int width;
	int height;
	Matrix matrix;//变换矩阵
	GestureDetector detector;
	float currentScale = 1;//缩放比
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		detector = new GestureDetector(new MyGestrue());
		matrix = new Matrix();
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setGravity(Gravity.CENTER);
		imageView  = new ImageView(this);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imageView.setLayoutParams(layoutParams);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a11);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.a11));
		linearLayout.addView(imageView);
		setContentView(linearLayout);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}
	
	class MyGestrue implements OnGestureListener{

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			velocityX = velocityX > 4000 ? 4000:velocityX;
			velocityX = velocityX < -4000 ? -4000:velocityX;
			currentScale = currentScale + currentScale*velocityX/4000.f;
			currentScale = currentScale > 0.01f ? currentScale:0.01f;
			//初始化
			matrix.reset();
			matrix.setScale(currentScale, currentScale, 160, 200);
			BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
			if(bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled()){
				bitmapDrawable.getBitmap().recycle();
			}
			//重新生成新的图片
			Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
			imageView.setImageBitmap(bitmap1);
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
		
	}
	
	
	
	
	

}
