package com.cloudy.androidapi.activity;

import java.util.Calendar;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class DateTimePickerActivity extends Activity{
	
	private DatePicker datePicker;
	private TimePicker timePicker;
	private TextView textView;
	
	private Calendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.date_time_picker);
		
		datePicker = (DatePicker) findViewById(R.id.date);
		timePicker = (TimePicker) findViewById(R.id.time);
		textView = (TextView) findViewById(R.id.text);
		
		calendar = Calendar.getInstance();
		
		//初始化日期
		datePicker.init(calendar.get(calendar.YEAR), 
				calendar.get(calendar.MONTH), 
				calendar.get(calendar.DAY_OF_MONTH), 
				new OnDateChangedListener() {
					//日期改变事件回调
					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						calendar.set(year, monthOfYear, dayOfMonth);
						
					}
				});
		
		//设置时间格式为true:24小时制
		timePicker.setIs24HourView(true);
		//时间改变事件监听
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				
				calendar.set(calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(calendar.MINUTE, minute);
				
				textView.setText("你设置的时间是:"+
						calendar.get(calendar.YEAR)+"年"+
						(calendar.get(calendar.MONTH)+1)+"月"+
						calendar.get(calendar.DAY_OF_MONTH)+"日"+
						calendar.get(calendar.HOUR_OF_DAY)+"时"+
						calendar.get(calendar.MINUTE)+"分");
				
				
			}
		});
		
		
	}

}
