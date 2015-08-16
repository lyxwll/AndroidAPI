package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class ToggleButtonActivit extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toggle__button);
		
		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle);
		final LinearLayout togglelinear = (LinearLayout) findViewById(R.id.toggle_linear);
		
		//设置按钮 选择改变的监听事件
		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//如果选中,则改变另一个LinearLayout里的Button的排列方式
				if(isChecked){
					togglelinear.setOrientation(LinearLayout.VERTICAL);
				}else{
					togglelinear.setOrientation(LinearLayout.HORIZONTAL);
				}
			}
		});
		

		
	}

}
