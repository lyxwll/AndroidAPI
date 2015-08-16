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
	 * 只有一个实例。在同一个应用程序中启动他的时候，若Activity不存在，则会在当前task创建一个新的实例，
	 * 若存在，则会把task中在其之上的其它Activity destory掉并调用它的onNewIntent方法。
	 * 如果是在别的应用程序中启动它，则会新建一个task，并在该task中启动这个Activity，
	 * singleTask允许别的Activity与其在一个task中共存，
	 * 也就是说，如果我在这个singleTask的实例中再打开新的Activity，
	 * 这个新的Activity还是会在singleTask的实例的task中。
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("LaunchModeSingleTask onCreate");
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("启动自己"+Config.COUNTER);
		Button button2 = new Button(this);
		button2.setText("启动其他");
		
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
