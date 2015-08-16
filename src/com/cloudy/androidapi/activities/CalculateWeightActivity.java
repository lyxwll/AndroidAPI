package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class CalculateWeightActivity extends Activity{
	
	private EditText heightEditText;
	private RadioButton manRadioButton;
	private RadioButton womanButton;
	
	Button calculateButton;
	public static int requestcode = 0x1234;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate_weight_layout);
		
		heightEditText = (EditText) findViewById(R.id.height_edit);
		manRadioButton = (RadioButton) findViewById(R.id.sex_man);
		womanButton = (RadioButton) findViewById(R.id.sex_woman);
		calculateButton = (Button) findViewById(R.id.calculate_button);
		calculateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					double height = Double.parseDouble(heightEditText.getText().toString());
					String sex = "";
					if(manRadioButton.isChecked()){
						sex = "M";
					}else{
						sex = "F";
					}
					Intent intent = new Intent();
					intent.setClass(CalculateWeightActivity.this, CalculateResultsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putDouble("height", height);
					bundle.putString("sex", sex);
					intent.putExtras(bundle);
					startActivityForResult(intent, requestcode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case RESULT_OK:
			Bundle bundle = data.getExtras();
			String sex = bundle.getString("sex");
			double height = bundle.getDouble("height");
			heightEditText.setText("" + height);
			if(sex.equals("M")){
				manRadioButton.setChecked(true);
			}else{
				womanButton.setChecked(false);
			}
			break;
		default:
			break;
		}
	}

}
