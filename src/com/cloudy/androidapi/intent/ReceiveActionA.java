package com.cloudy.androidapi.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.Toast;

public class ReceiveActionA extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getIntent() != null){
			ComponentName componentName = getIntent().getComponent();
			Toast.makeText(this, "A œÏ”¶Intent("+componentName.toShortString()+")" , Toast.LENGTH_SHORT).show();
		}
	}

}














