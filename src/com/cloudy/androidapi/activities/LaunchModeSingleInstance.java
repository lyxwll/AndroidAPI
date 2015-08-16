package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.bean.Config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class LaunchModeSingleInstance extends Activity{
	
	/**
	 *  ֻ��һ��ʵ�����������ʵ������������һ��task�У����taskֻ�����ʵ�����������б��Activity���ڡ�
	 *  ��һ����ջ�д�����Activityʵ�������ö��Ӧ�ù����ջ�еĸ�Activityʵ����
	 *  һ����ģʽ��Activity��ʵ��������ĳ��ջ�У��κ�Ӧ���ټ����Activityʱ�������ø�ջ�е�ʵ����
	 *  ��Ч���൱�ڶ��Ӧ�ó�����һ��Ӧ�ã�����˭�����Activity�������ͬһ��Ӧ���С�  
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("LaunchModeSingleInstance onCreate");
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
				intent.setClass(LaunchModeSingleInstance.this, LaunchModeSingleInstance.class);
				startActivity(intent);
				
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LaunchModeSingleInstance.this, SingleInstanceOtherActivity.class);
				startActivity(intent);
			}
		});
		
		linearLayout.addView(button);
		linearLayout.addView(button2);
		
		setContentView(linearLayout);
		
	}

}
