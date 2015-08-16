package com.cloudy.androidapi.activities;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CalculateResultsActivity extends Activity{
	private Intent intent;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate_weight_layout2);
		intent = this.getIntent();
		bundle = intent.getExtras();
		
		String sex = bundle.getString("sex");
		double height = bundle.getDouble("height");
		String sexText = "";
		if(sex.equals("M")){
			sexText = "男性";
		}else{
			sexText = "女性";
		}
		String weight = this.getWeight(sex, height);
		TextView textView = (TextView) findViewById(R.id.result_text);
		textView.setText("你是一位" + sexText + "\n你的身高是" + height + "厘米\n你的标准体重是" + weight + "公斤");
		
		Button button = (Button) findViewById(R.id.back_button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//把result返回上一个 activity 
				CalculateResultsActivity.this.setResult(RESULT_OK, intent);
				CalculateResultsActivity.this.finish();
			}
		});
	}
	
	/**
	 * 获取标准体重
	 * @param sex
	 * @param height
	 * @return
	 */
	public String getWeight(String sex,double height){
		String weight = "";
		if(sex.equals("M")){
			weight = format((height - 80) * 0.7);
		}else{
			weight = format((height - 70) * 0.6);
		}
		return weight;
	}
	
	public String format(double numble){
		NumberFormat numberFormat = new DecimalFormat("0.00");
		String s = numberFormat.format(numble);
		return s;
	}

}
