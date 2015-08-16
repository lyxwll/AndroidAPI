package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.view.CustomToast;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class ToastActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linearLayout.setLayoutParams(layoutParams);
		
		Button button = new Button(this);
		button.setLayoutParams(layoutParams);
		button.setText("show simple toast");
		
		Button button2 = new Button(this);
		button2.setLayoutParams(layoutParams);
		button2.setText("show custom toast");
		
		Button button3 = new Button(this);
		button3.setLayoutParams(layoutParams);
		button3.setText("show customStyle toast");
		
		linearLayout.addView(button);
		linearLayout.addView(button2);
		linearLayout.addView(button3);
		
		setContentView(linearLayout);
		
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				simpleToast();
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				customToast();
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				customStyleToast();
			}
		});
	}
	
	//显示simpleToast
	private void simpleToast(){
		
		Toast.makeText(this, "simpleToast", Toast.LENGTH_SHORT).show();
		
	}
	
	//显示自定义Toast
	private void customToast(){
		
		Toast toast =new Toast(this);
		View view = LayoutInflater.from(this).inflate(R.layout.userlogin_layout, null);
		toast.setView(view);
		//Set how long to show the view for.
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
		
	}
	
	//显示自定义样式Toast
	private void customStyleToast(){
		
		CustomToast.makeText(this, "show customStyle toast", Toast.LENGTH_SHORT).show();
		
	}
	
}
























