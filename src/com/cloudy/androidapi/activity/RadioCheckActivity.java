package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioCheckActivity extends Activity{
	
	private RadioGroup group;
	private String gender;
	private RadioButton male;
	private RadioButton female;
	
	private String colors;
	private CheckBox box1;
	private CheckBox box2;
	private CheckBox box3;
	
	private Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_check_layout);
		
		group = (RadioGroup) findViewById(R.id.group);
		male = (RadioButton) findViewById(R.id.male);
		female = (RadioButton) findViewById(R.id.female);
		
		box1 = (CheckBox) findViewById(R.id.box1);
		box2 = (CheckBox) findViewById(R.id.box2);
		box3 = (CheckBox) findViewById(R.id.box3);
		
		submit = (Button) findViewById(R.id.submit);
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//获取性别
				int id =group.getCheckedRadioButtonId();
				switch (id) {
				case R.id.male:
					gender = male.getText().toString();
					break;
				case R.id.female:
					gender = female.getText().toString();
					break;
				}
				
				colors = " ";
				//获取到颜色
				if(box1.isChecked()){
					colors += box1.getText().toString();
				}
				
				if(box2.isChecked()){
					colors += box2.getText().toString();
				}
				
				if(box3.isChecked()){
					colors += box3.getText().toString();
				}
				Toast.makeText(RadioCheckActivity.this, getResources().getString(R.string.gender)
						+"="+gender+","+getResources().getString(R.string.colors)
						+"="+colors, Toast.LENGTH_SHORT).show();
				
				
			}
		});
		
		
	}

}
