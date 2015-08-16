package com.cloudy.androidapi.intent;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ComponentAttr extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.componentattr_layout);
		Button button = (Button) findViewById(R.id.component_btn);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ComponentName comp = new ComponentName(ComponentAttr.this, ComponentSecond.class);
				Intent intent = new Intent();
				intent.setComponent(comp);
				startActivity(intent);
			}
		});
		
	}

}
