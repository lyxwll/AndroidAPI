package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class ScrollViewActivity extends Activity{
	
	private ScrollView scrollView;
	private LinearLayout linearLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollview_layout);
		
		scrollView = (ScrollView) findViewById(R.id.vertical_scroll);
		linearLayout = (LinearLayout) findViewById(R.id.scroll_content);
		
		for(int i = 0;i<50;i++){
			TextView textView = new TextView(this);
			LinearLayout.LayoutParams layoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			textView.setLayoutParams(layoutParams);
			textView.setText(R.string.Scroll_Textview);
			linearLayout.addView(textView);
			
			
		}
	
		
	}
	

}
