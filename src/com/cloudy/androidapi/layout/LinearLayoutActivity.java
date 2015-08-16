package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/*
 * 测试线性布局
 */

public class LinearLayoutActivity extends Activity{
	
	int[]position ={
			Gravity.BOTTOM|Gravity.CENTER,
			Gravity.CENTER,
			Gravity.TOP|Gravity.LEFT,
			Gravity.LEFT|Gravity.CENTER,
			Gravity.RIGHT|Gravity.BOTTOM,
			
	};
	
	int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linear_layout);
		
		final LinearLayout linearLayout =(LinearLayout) findViewById(R.id.linear);
		//按钮事件
		Button buttchange = (Button) findViewById(R.id.change_butt);
		Button buttgravity =(Button) findViewById(R.id.change_gravity);
		
		buttchange.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(linearLayout.getOrientation() == LinearLayout.HORIZONTAL){
					linearLayout.setOrientation(linearLayout.VERTICAL);
				}else{
					linearLayout.setOrientation(linearLayout.HORIZONTAL);
				}
				
			}
		});
		
		
		buttgravity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				index++;
				if(index >position.length-1){
					index = 0;
				}
				linearLayout.setGravity(position[index]);
			}
		});
		
	
	}


}
