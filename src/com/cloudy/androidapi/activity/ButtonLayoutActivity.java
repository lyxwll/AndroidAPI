package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ButtonLayoutActivity extends Activity implements OnClickListener{
	
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private ImageButton btn6;
	
	//成员属性的事件监听
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Toast.makeText(ButtonLayoutActivity.this, 
					R.string.button3, Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_layout);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (ImageButton) findViewById(R.id.btn6);
		
		//
		btn1.setOnClickListener(new InnerClickListener());
		//
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(ButtonLayoutActivity.this, 
						R.string.button2, Toast.LENGTH_SHORT).show();
			}
		});
		//成员属性的监听事件
		btn3.setOnClickListener(listener);
		//Activity的监听事件
		btn4.setOnClickListener(this);
		
	}
	
	class InnerClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Toast.makeText(ButtonLayoutActivity.this, 
					R.string.button2, Toast.LENGTH_SHORT).show();
			
		}
		
	}

	//Activity 的事件监听
	@Override
	public void onClick(View v) {
		Toast.makeText(ButtonLayoutActivity.this, 
				R.string.button4, Toast.LENGTH_SHORT).show();		
	}
	//布局文件 的事件监听
	public void clickBtn5(View v){
		Toast.makeText(ButtonLayoutActivity.this, 
				R.string.button5, Toast.LENGTH_SHORT).show();
	}
	
}








