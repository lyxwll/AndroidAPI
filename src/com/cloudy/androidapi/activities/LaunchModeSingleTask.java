package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.bean.Config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class LaunchModeSingleTask extends Activity{
	
	/**
	 * ֻ��һ��ʵ������ͬһ��Ӧ�ó�������������ʱ����Activity�����ڣ�����ڵ�ǰtask����һ���µ�ʵ����
	 * �����ڣ�����task������֮�ϵ�����Activity destory������������onNewIntent������
	 * ������ڱ��Ӧ�ó�����������������½�һ��task�����ڸ�task���������Activity��
	 * singleTask������Activity������һ��task�й��棬
	 * Ҳ����˵������������singleTask��ʵ�����ٴ��µ�Activity��
	 * ����µ�Activity���ǻ���singleTask��ʵ����task�С�
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("LaunchModeSingleTask onCreate");
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("�����Լ�"+Config.COUNTER);
		Button button2 = new Button(this);
		button2.setText("��������");
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LaunchModeSingleTask.this, LaunchModeSingleTask.class);
				startActivity(intent);
				
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LaunchModeSingleTask.this, SingleTaskOtherActivity.class);
				startActivity(intent);
			}
		});
		
		linearLayout.addView(button);
		linearLayout.addView(button2);
		
		setContentView(linearLayout);
		
	}

}
