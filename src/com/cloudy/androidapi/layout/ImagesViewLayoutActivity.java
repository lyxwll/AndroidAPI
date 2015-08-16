package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImagesViewLayoutActivity extends Activity {

	private Button plusbtn;
	private Button minusbtn;
	private Button nextbtn;

	private ImageView images1;
	private ImageView images2;

	// 定义一个访问图片的数组
	int image[] = { R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8,
			R.drawable.a9, R.drawable.a10, };
	// 定义默认显示的图片
	int currentImg = 1;
	// 定义图片的初始透明度
	private int alpha = 255;
	//image2的位图
	private Bitmap bitmap2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagesview_layout);

		plusbtn = (Button) findViewById(R.id.plus);
		minusbtn = (Button) findViewById(R.id.minus);
		nextbtn = (Button) findViewById(R.id.next);

		images1 = (ImageView) findViewById(R.id.images1);
		images2 = (ImageView) findViewById(R.id.images2);
		//设置按钮的点击事件
		plusbtn.setOnClickListener(new MyClickListener());
		minusbtn.setOnClickListener(new MyClickListener());
		nextbtn.setOnClickListener(new MyClickListener());
		// 设置image1的触屏事件
		images1.setOnTouchListener(new MyOnTouchListener());

	}

	class MyClickListener implements OnClickListener {

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.plus:
				//控制alpha透明度的值不会超过边界
				if (alpha <= 255) {
					alpha -= 20;
				}
				if (alpha > 255) {
					alpha = 255;
				}
				images1.setAlpha(alpha);
				break;

			case R.id.minus:
				if (alpha >= 0) {
					alpha += 20;
				}
				if (alpha < 0) {
					alpha = 0;
				}
				images1.setAlpha(alpha);
				break;
			case R.id.next:
				changeImageView();
				break;
			/*
			 * if(currentImg>image.length){ currentImg =-1; } BitmapDrawable
			 * drawable = (BitmapDrawable) images1.getDrawable();
			 * if(!drawable.getBitmap().isRecycled()){ //如果bitmap未回收,则先强制回收
			 * drawable.getBitmap().isRecycled(); } //重新设置images1上的图片
			 * images1.setImageBitmap
			 * (BitmapFactory.decodeResource(getResources(), currentImg));
			 * break;
			 */
			}
		}

	}

	// 查看下一张图片的方法
	private void changeImageView() {
		if (currentImg > image.length - 1) {
			currentImg = 0;
		}
		//得到images1的BitmapDrawable的实例
		BitmapDrawable drawable = (BitmapDrawable) images1.getDrawable();
		if (!drawable.getBitmap().isRecycled()) {
			// 如果bitmap未回收,则先强制回收
			drawable.getBitmap().isRecycled();
		}
		// 重新设置images1上的图片
		images1.setImageBitmap(BitmapFactory.decodeResource(getResources(),
				image[currentImg]));
		currentImg++;

	}

	//显示图片局部细节的方法
	class MyOnTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) images1
					.getDrawable();
			// 获取第一个图片显示框中的位图
			Bitmap bitmap = bitmapDrawable.getBitmap();
			// bitmap图片实际大小与第一个ImageView的缩放比例
			double scale = bitmap.getWidth() / 320.0;
			// 获取需要显示图片细节的开始位置
			int X = (int) (event.getX() * scale);
			int Y = (int) (event.getY() * scale);

			// 进行点击的边界检查
			if (X + 200 > bitmap.getWidth()) {
				X = bitmap.getWidth() - 200;
			}
			if (X < 0) {
				X = 0;
			}
			if (Y + 200 > bitmap.getHeight()) {
				Y = bitmap.getHeight() - 200;
			}
			if (Y < 0) {
				Y = 0;
			}
			// 强制回收bitmap2
			if (bitmap2 != null && !bitmap2.isRecycled()) {
				bitmap2.isRecycled();
			}
			// 挖取images1里面的图片
			// 以X,Y为起点,width height 为200
			bitmap2 = Bitmap.createBitmap(bitmap, X, Y, 200, 200);
			// 把从images1挖取的图片设置给images2
			images2.setImageBitmap(bitmap2);
			images2.setAlpha(alpha);
			// 返回true表示事件不在下发

			return true;
		}

	}

}
