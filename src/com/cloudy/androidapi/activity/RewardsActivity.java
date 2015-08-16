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
	private int width;//�ν����Ŀ�
	private int height;//�ν����ĸ�
	
	private Bitmap bitmap;//�ν�����λͼ
	private int[] pixels;//�ν�����λͼ����
	
	private int X;//�ν�ʱ�������X����
	private int Y;//�ν�ʱ�������X����
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rewards_layout);
		imageView = (ImageView) findViewById(R.id.rewards);
		//�����ͼ�Ŀ��:ViewTreeObserver
		ViewTreeObserver observer = imageView.getViewTreeObserver();
		observer.addOnPreDrawListener(new MyOnPreDrawListener());
		
	}
	
	class MyOnPreDrawListener implements OnPreDrawListener{

		@Override
		public boolean onPreDraw() {
			if(!isMeasured){
				//��ȡImageView�Ŀ��
				width = imageView.getMeasuredWidth();
				height = imageView.getMeasuredHeight();
				//��ʼ������:����ÿ�����ص�
				pixels = new int[width*height];
				isMeasured = true;
				setImage();
				//���ùν��¼������¼�
				imageView.setOnTouchListener(new MyOnTouchListener());
			}
			return true;
		}
		
	}
	
	//���ɸ��ǹν������ɲ�
	private void setImage(){
		//����һ����ImageView���һ����λͼ
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		for(int i=0;i<width*height;i++){
			pixels[i]=0xff747474;
		}
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		imageView.setImageBitmap(bitmap);
	}
	
	//�ν������¼��ķ���
	class MyOnTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				X = (int) event.getX();
				Y = (int) event.getY();
				System.out.println("down x="+X+",Y="+Y);
				break;
				//�����йν�
			case MotionEvent.ACTION_MOVE:
				X = (int) event.getX();
				Y = (int) event.getY();
				//���߽�:���ν�ʱ����Ƿ��ڴ�����
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
				//�����ɲ��������ز���:�����ɲ�ȥ��
				for(int i=X-10;i<X+10;i++){
					for(int j = Y-10;j<Y+10;j++){
						//�ж�i,j�Ƿ���ͼƬ��
						if(i>=0&&i<width-1&&j>=0&&j<height-1){
							int pixel = 0x00000000;//���ν���ĵط���������Ϊȫ͸��
							bitmap.setPixel(i, j, pixel);
						}
					}
				}
				//���ı����ɲ���������
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



















