package com.cloudy.androidapi.activities;

/**
 * 参数传递:计算斐波那契
 */
import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityA extends Activity{
	
	public static final int REQUEST_CODE = 124;
	EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_a_layout);
		editText = (EditText) findViewById(R.id.edit_a);
		findViewById(R.id.send_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendToActivityB();
			}
		});
	}
	
	/**
	 * 将请求发送给ActivityB
	 */
	public void sendToActivityB(){
		String str = editText.getText().toString();
		if(str != null && !str.equals("")){
			try {
				int n = Integer.parseInt(str);
				//创建Intent 并发送给Activity B
				Intent intent = new Intent(this, ActivityB.class);
				Bundle bundle = new Bundle();
				//传递复杂数据类型
				//bundle.putSerializable(key, value);
				//bundle.putParcelable(key, value);
				bundle.putInt("sendn", n);
				intent.putExtras(bundle);
				startActivityForResult(intent, REQUEST_CODE);
				
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "invald input", Toast.LENGTH_SHORT).show();
			}
			
		}else{
			Toast.makeText(this, "no input", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("onActivityResult requestCode="  + requestCode + ", resultCode=" + resultCode);
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
			System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
			if(data != null){
				long result = data.getLongExtra("calculate_n", -1);
				Toast.makeText(this, "B 真能干,这么复杂都计算出来了, result=" + result, Toast.LENGTH_LONG).show();
			}
			
		}
	}
	
	

}
