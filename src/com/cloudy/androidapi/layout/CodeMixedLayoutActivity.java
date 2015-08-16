package com.cloudy.androidapi.layout;

/*
 * ͼƬ������
 */
import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CodeMixedLayoutActivity extends Activity{
	
	//����һ������ͼƬ������
	int[] images = new int[]{
		R.drawable.a,
		R.drawable.a2,
		R.drawable.a3,
		R.drawable.a4,
		R.drawable.z1,
		R.drawable.z2,
		R.drawable.z3,
		R.drawable.z4,
		R.drawable.z5,
		R.drawable.z6,
		R.drawable.z7,
		R.drawable.z8,
	};
	
	int currentImg = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.code_mixed);
		//��ȡlinearlayout�Ĳ�������
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.mixcode_layout);
		//����ImageView���
		final ImageView imageView = new ImageView(this);
		//��ImageView�����ӵ�layout������
		linearLayout.addView(imageView);
		//��ʼ��ʱ��ʾ��һ��ͼƬ
		imageView.setImageResource(images[0]);
		
		//���õ���¼�,ʹ�õ��������ڲ���
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(currentImg >= 11){
					currentImg =-1;
				}
				//�ı�ImageView����ʾ��ͼƬ
				imageView.setImageResource(images[++currentImg]);
			}
		});
		
	}
			
}
