package com.cloudy.androidapi.intent;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.EditText;

public class ComponentSecond extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.component_second_layout);
		EditText editText = (EditText) findViewById(R.id.component_edit);
		ComponentName name = getIntent().getComponent();
		editText.setText("组件包名为:"+name.getPackageName()
				+"\n组件类名为:"+name.getClassName());
		
	}

}
