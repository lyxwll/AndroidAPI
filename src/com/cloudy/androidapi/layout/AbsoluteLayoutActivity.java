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
	private float x = 0;// ��ǰ�ؼ���X����
	private float y = 0;// ��ǰ�ؼ���Y����
	private float xSpeed = 9.6f;
	private float ySpeed = 14.56f;
	
	//��ʶ�Ƿ��Ѿ�������
	private boolean isMeasured = false;  

	//������һ��������Ϣ�ı���
	private static final int MOVE = 0x1111;
	private static final String TAG = "AbsoluteActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.absolute_layout);

		// absolute_layout.xml�л�ȡImageView���
		imageview = (ImageView) findViewById(R.id.image);
		absoluteLayout = (AbsoluteLayout) findViewById(R.id.abs_layout);
		
		//��ȡ����ǰ��ͼ��������ͼ���Ĺ۲�������
		//����ȡ��,��:ViewTreeObserver
		ViewTreeObserver observer = absoluteLayout.getViewTreeObserver();
		observer.addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				if(!isMeasured){
					//���û�б�����,��ȡ�������ĳߴ�,Ҳ���ǿ��
					screenWidth = absoluteLayout.getMeasuredWidth();
					screenHeigth = absoluteLayout.getMeasuredHeight();
					isMeasured = true;
					
					//��logcat����ʾ��,�ߵ���Ϣ
					Log.d(TAG, "screenWidth="+screenWidth+"screenHeigth="+screenHeigth);
					
					/*
					 * ����Ҫ����һ��start()������������һ���߳�
					 * �������run()����,��ôֻ�ǰ����Thread�൱������ͨ��,ȥִ��run()����,
					 * ������������һ���߳�,��run()�����������߳�����ִ�е�
					 */
					new RunThread().start();
					
				}
				/*
				 * return ture;��ǰ�����ѽ���
				 * ��ͼ��δ�ı�֮ǰ
				 * �����return false;��һֱִ�в���
				 */
				return true;
				
			}
		});
		
	/*	
		 * ����Ҫ����һ��start()������������һ���߳�
		 * �������run()����,��ôֻ�ǰ����Thread�൱������ͨ��,ȥִ��run()����,
		 * ������������һ���߳�,��run()�����������߳�����ִ�е�
		 
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
				//����Ϣ֪ͨ���߳�,ͼƬ��λ�øı���
				handler.sendEmptyMessage(MOVE);

			}
		}

		//��Ϣ������
		Handler handler = new Handler() {
			//������Ϣ
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				//���յ�ͼƬλ�øı����Ϣ
				case MOVE:
					//��ȡImageView�Ĳ��ֲ���
					//����ImageView��X,Y��ֵ
					AbsoluteLayout.LayoutParams layoutParams =(LayoutParams) imageview.getLayoutParams();
					layoutParams.x = (int) x;
					layoutParams.y = (int) y;
					/*layoutParams.height = 100;
					layoutParams.width= 40;*/
					
					//��������ͼƬ�Ĳ���
					imageview.setLayoutParams(layoutParams);

					break;

				}
			};
		};
	}
}
