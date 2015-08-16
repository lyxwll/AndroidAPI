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
	 *  只有一个实例，并且这个实例独立运行在一个task中，这个task只有这个实例，不允许有别的Activity存在。
	 *  在一个新栈中创建该Activity实例，并让多个应用共享改栈中的该Activity实例。
	 *  一旦改模式的Activity的实例存在于某个栈中，任何应用再激活改Activity时都会重用该栈中的实例，
	 *  其效果相当于多个应用程序共享一个应用，不管谁激活该Activity都会进入同一个应用中。  
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("LaunchModeSingleInstance onCreate");
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("启动自己"+Config.COUNTER);
		Config.COUNTER++;
		Button button2 = new Button(this);
		button2.setText("启动其他");
		
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
