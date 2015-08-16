package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.bean.Config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class LaunchModeStandard extends Activity{
	
	/**
	 * launchMode��Ĭ��ģʽ,���Բ���д����.�����ģʽ�¶���Ĭ�ϴ���һ���µ�ʵ��.
	 * ���,�ڴ�ģʽ��,�����ж����ͬ��ʵ��,Ҳ��������ͬ��Activity����
	 * ������ģʽ��ÿ�μ���Activityʱ���ᴴ��Activity������������ջ�С�
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("LaunchModeStandard onCreate");
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("�����Լ�"+Config.COUNTER);
		Config.COUNTER++;
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				//�����Լ�LaunchModeStandard
				intent.setClass(LaunchModeStandard.this, LaunchModeStandard.class);
				startActivity(intent);
				
			}
		});
		
		linearLayout.addView(button);
		setContentView(linearLayout);
	}

}
















