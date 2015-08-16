package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;

public class TextViewlayoutActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		/*LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(linearLayout.VERTICAL);
		
		TextView textView = new TextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		textView.setText(R.string.hello_password);
		linearLayout.addView(textView);*/
		
		setContentView(R.layout.textview_layout);
		
	}
	
	
	

}
