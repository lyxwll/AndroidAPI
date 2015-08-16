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
		
		//��ʼ������
		datePicker.init(calendar.get(calendar.YEAR), 
				calendar.get(calendar.MONTH), 
				calendar.get(calendar.DAY_OF_MONTH), 
				new OnDateChangedListener() {
					//���ڸı��¼��ص�
					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						calendar.set(year, monthOfYear, dayOfMonth);
						
					}
				});
		
		//����ʱ���ʽΪtrue:24Сʱ��
		timePicker.setIs24HourView(true);
		//ʱ��ı��¼�����
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				
				calendar.set(calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(calendar.MINUTE, minute);
				
				textView.setText("�����õ�ʱ����:"+
						calendar.get(calendar.YEAR)+"��"+
						(calendar.get(calendar.MONTH)+1)+"��"+
						calendar.get(calendar.DAY_OF_MONTH)+"��"+
						calendar.get(calendar.HOUR_OF_DAY)+"ʱ"+
						calendar.get(calendar.MINUTE)+"��");
				
				
			}
		});
		
		
	}

}
