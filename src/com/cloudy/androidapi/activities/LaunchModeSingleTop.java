package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.bean.Config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class LaunchModeSingleTop extends Activity{
	/**
	 * �����ж��ʵ��,������������ͬ��Activity����.��:���Activity��ջ����ʱ��
	 * ������ͬ��Activity,���ᴴ���µ�ʵ��,���������onNewIntent����
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("LaunchModeSingleTop onCreate ");
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("�����Լ�"+Config.COUNTER);
		Config.COUNTER++;
		Button button2 = new Button(this);
		button2.setText("��������");
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LaunchModeSingleTop.this, LaunchModeSingleTop.class);
				startActivity(intent);
				
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LaunchModeSingleTop.this, SingleTopOtherActivity.class);
				startActivity(intent);
			}
		});
		
		linearLayout.addView(button);
		linearLayout.addView(button2);
		
		setContentView(linearLayout);
		
	}

}
