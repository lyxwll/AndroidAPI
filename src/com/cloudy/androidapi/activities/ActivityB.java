package com.cloudy.androidapi.activities;


import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ActivityB extends Activity{
	
	static final int START_CALCULATE = 123;
	static final int END_CALCULATE = 124;
	static final int CALCULATING = 125;
	static final int CALCULATE_N = 123456;
	
	EditText editText;
	int n;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(this);
		LinearLayout.LayoutParams layoutParams = 
				new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		editText = new EditText(this);
		editText.setGravity(Gravity.TOP | Gravity.LEFT);
		editText.setLayoutParams(layoutParams);
		editText.setMinLines(5);
		editText.setTextSize(16);
		editText.setBackgroundResource(R.drawable.edit_text_bg);
		linearLayout.addView(editText);
		setContentView(linearLayout);
		
		//��ȡ��ActivityA���͹�����ֵ
		if(getIntent() != null && getIntent().getExtras() != null){
			n = getIntent().getExtras().getInt("sendn", -1);
			if(n != -1){
				handler.sendEmptyMessage(START_CALCULATE);
			}
		}
	}
	
	//
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case START_CALCULATE:
				editText.setText("��ȡ��A���ݹ�����ֵn="+n);
				handler.sendEmptyMessageDelayed(CALCULATING, 2000);
				break;
			case CALCULATING:
				//������ֵ����
				new MyThread(n).start();
				break;
			case CALCULATE_N:
				//editText.append("\n���� "+ msg.arg1 +" ��쳲��������е�ֵ");
				System.out.println("���� "+ msg.arg1 +" ��쳲��������е�ֵ");
				break;
			case END_CALCULATE:
				//����õ�ֵ���ظ�A
				editText.append("\n�õ���ֵ���, result=" + msg.obj + ", �����ظ�A");
				Intent intent = new Intent();
				intent.putExtra("calculate_n", (Long)msg.obj);
				setResult(RESULT_OK, intent);
				finishActivity(124);
				finish();
				break;
			}
		};
	};
	
	class MyThread extends Thread{
		int n;
		public MyThread(int n) {
			this.n = n;
		}
		
		@Override
		public void run() {
			long result = xunhuan(n);
			Message message = new Message();
			message.what = END_CALCULATE;
			message.obj = result;
			handler.sendMessage(message);
		}
		
	}
	
	public long f(int n){
		Message msg = new Message();
		msg.what = CALCULATE_N;
		msg.arg1 = n;
		handler.sendMessage(msg);
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else {
			return  (f(n - 1)) + f(n - 2);
		}
	}
	
	public long xunhuan(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else {
			long fn = 0;
			long fn_1 = 1;
			long fn_2 = 1;
			for (int i = 2; i <= n; i++) {
				// ����fn��ֵ
				fn = fn_1 + fn_2;
				// ��fn_2����� fn_1
				fn_2 = fn_1;
				// ��fn_1�滻��fn
				fn_1 = fn;
			}
			return fn;
		}
	}
	

}
