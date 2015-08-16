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

	// ����һ������ͼƬ������
	int image[] = { R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8,
			R.drawable.a9, R.drawable.a10, };
	// ����Ĭ����ʾ��ͼƬ
	int currentImg = 1;
	// ����ͼƬ�ĳ�ʼ͸����
	private int alpha = 255;
	//image2��λͼ
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
		//���ð�ť�ĵ���¼�
		plusbtn.setOnClickListener(new MyClickListener());
		minusbtn.setOnClickListener(new MyClickListener());
		nextbtn.setOnClickListener(new MyClickListener());
		// ����image1�Ĵ����¼�
		images1.setOnTouchListener(new MyOnTouchListener());

	}

	class MyClickListener implements OnClickListener {

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.plus:
				//����alpha͸���ȵ�ֵ���ᳬ���߽�
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
			 * if(!drawable.getBitmap().isRecycled()){ //���bitmapδ����,����ǿ�ƻ���
			 * drawable.getBitmap().isRecycled(); } //��������images1�ϵ�ͼƬ
			 * images1.setImageBitmap
			 * (BitmapFactory.decodeResource(getResources(), currentImg));
			 * break;
			 */
			}
		}

	}

	// �鿴��һ��ͼƬ�ķ���
	private void changeImageView() {
		if (currentImg > image.length - 1) {
			currentImg = 0;
		}
		//�õ�images1��BitmapDrawable��ʵ��
		BitmapDrawable drawable = (BitmapDrawable) images1.getDrawable();
		if (!drawable.getBitmap().isRecycled()) {
			// ���bitmapδ����,����ǿ�ƻ���
			drawable.getBitmap().isRecycled();
		}
		// ��������images1�ϵ�ͼƬ
		images1.setImageBitmap(BitmapFactory.decodeResource(getResources(),
				image[currentImg]));
		currentImg++;

	}

	//��ʾͼƬ�ֲ�ϸ�ڵķ���
	class MyOnTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) images1
					.getDrawable();
			// ��ȡ��һ��ͼƬ��ʾ���е�λͼ
			Bitmap bitmap = bitmapDrawable.getBitmap();
			// bitmapͼƬʵ�ʴ�С���һ��ImageView�����ű���
			double scale = bitmap.getWidth() / 320.0;
			// ��ȡ��Ҫ��ʾͼƬϸ�ڵĿ�ʼλ��
			int X = (int) (event.getX() * scale);
			int Y = (int) (event.getY() * scale);

			// ���е���ı߽���
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
			// ǿ�ƻ���bitmap2
			if (bitmap2 != null && !bitmap2.isRecycled()) {
				bitmap2.isRecycled();
			}
			// ��ȡimages1�����ͼƬ
			// ��X,YΪ���,width height Ϊ200
			bitmap2 = Bitmap.createBitmap(bitmap, X, Y, 200, 200);
			// �Ѵ�images1��ȡ��ͼƬ���ø�images2
			images2.setImageBitmap(bitmap2);
			images2.setAlpha(alpha);
			// ����true��ʾ�¼������·�

			return true;
		}

	}

}
