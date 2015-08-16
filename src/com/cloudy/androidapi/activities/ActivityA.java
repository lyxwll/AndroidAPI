package com.cloudy.androidapi.activities;

/**
 * ��������:����쳲�����
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
	 * �������͸�ActivityB
	 */
	public void sendToActivityB(){
		String str = editText.getText().toString();
		if(str != null && !str.equals("")){
			try {
				int n = Integer.parseInt(str);
				//����Intent �����͸�Activity B
				Intent intent = new Intent(this, ActivityB.class);
				Bundle bundle = new Bundle();
				//���ݸ�����������
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
				Toast.makeText(this, "B ���ܸ�,��ô���Ӷ����������, result=" + result, Toast.LENGTH_LONG).show();
			}
			
		}
	}
	
	

}
