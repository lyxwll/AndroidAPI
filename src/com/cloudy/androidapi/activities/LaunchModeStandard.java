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
	 * launchMode的默认模式,可以不用写配置.在这个模式下都会默认创建一个新的实例.
	 * 因此,在此模式下,可以有多个相同的实例,也允许多个相同的Activity叠加
	 * 其启动模式，每次激活Activity时都会创建Activity，并放入任务栈中。
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("LaunchModeStandard onCreate");
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("启动自己"+Config.COUNTER);
		Config.COUNTER++;
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				//启动自己LaunchModeStandard
				intent.setClass(LaunchModeStandard.this, LaunchModeStandard.class);
				startActivity(intent);
				
			}
		});
		
		linearLayout.addView(button);
		setContentView(linearLayout);
	}

}
















