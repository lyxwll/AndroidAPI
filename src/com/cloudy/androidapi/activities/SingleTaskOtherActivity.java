package com.cloudy.androidapi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class SingleTaskOtherActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("SingleTaskOtherActivity onCreate");
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("Æô¶¯×Ô¼ºSingleTask");
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SingleTaskOtherActivity.this, LaunchModeSingleTask.class);
				startActivity(intent);
			}
		});
		
		linearLayout.addView(button);
		setContentView(linearLayout);
		
	}

}
