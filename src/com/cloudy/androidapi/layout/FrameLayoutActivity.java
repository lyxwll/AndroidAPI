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
	//LayoutInflater������:��layout�ļ����ص��ڴ�,��ת����һ��view
	private LayoutInflater inflater;

	//��Ϣ������
	Handler handler = new Handler() {
		//������Ϣ
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CHANGE_PIC:
				System.out.println("received message");
				//���ּ�����,��res/layout/imageview.xml;���ص��ڴ���,��ת����view
				ImageView imageView = (ImageView) inflater.inflate(
						R.layout.imageview, null);
				//���ñ���ͼƬ
				imageView.setBackgroundResource(images[index]);
				//����ImageView�Ĳ���Ϊ:�������� WRAP_CONTENT
				imageView.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				//��ImageView��ӵ�framelayout��
				frameLayout.addView(imageView); 
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���ز����ļ�
		setContentView(R.layout.frame_layout);
		//��ȡ�����ļ�������
		inflater = LayoutInflater.from(this);
		//���Ҳ����ļ���idΪframe_layout��view�ؼ�
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
					//����һ������Ϣ
					handler.sendEmptyMessage(CHANGE_PIC);
				} else {
					flag = false;
					//index = 0;
				}
			}
		}

	}

}
