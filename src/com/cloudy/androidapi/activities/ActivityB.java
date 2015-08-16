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
		
		//获取到ActivityA发送过来的值
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
				editText.setText("获取到A传递过来的值n="+n);
				handler.sendEmptyMessageDelayed(CALCULATING, 2000);
				break;
			case CALCULATING:
				//调用求值方法
				new MyThread(n).start();
				break;
			case CALCULATE_N:
				//editText.append("\n计算 "+ msg.arg1 +" 的斐波那契数列的值");
				System.out.println("计算 "+ msg.arg1 +" 的斐波那契数列的值");
				break;
			case END_CALCULATE:
				//把求得的值返回给A
				editText.append("\n得到求值结果, result=" + msg.obj + ", 并返回给A");
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
				// 计算fn的值
				fn = fn_1 + fn_2;
				// 将fn_2替代成 fn_1
				fn_2 = fn_1;
				// 将fn_1替换成fn
				fn_1 = fn;
			}
			return fn;
		}
	}
	

}
