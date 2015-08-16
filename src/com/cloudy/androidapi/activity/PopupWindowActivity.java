package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
//import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopupWindowActivity extends Activity{
	//API11֮ǰ: android.text.ClipboardManager;
	//API11֮��: android.content.ClipboardManager;
	ClipboardManager cbm;//ճ���������
	EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_window_layout);
		//��ȡϵͳճ�������
		cbm = (ClipboardManager) PopupWindowActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
		editText = (EditText) findViewById(R.id.popup_edit);
		
		findViewById(R.id.popup_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showPopupWindow(v);
			}
		});
		
		findViewById(R.id.popup_text).setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				showCopyPopupwindow((TextView)v);
				return true;
			}
		});
		
		editText.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				showPastePopupWindow(editText);
				return true;
			}
		});
	}
	
	//��ʾshowPastePopupWindow
	public void showPastePopupWindow(final EditText editText){
		final PopupWindow popupWindow = new PopupWindow(this);
		popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		
		TextView textView = new TextView(this);
		textView.setBackgroundResource(R.drawable.list_item_selector);
		textView.setText("Paste");
		
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
				//���ճ��������
				String str = cbm.getText().toString();
				editText.setText(str);
			}
		});
		popupWindow.setContentView(textView);
		popupWindow.showAsDropDown(editText,10,10);
		
		
	}
	
	//��ʾshowCopyPopupwindow
	public void showCopyPopupwindow(final TextView tv){
		final PopupWindow popupWindow = new PopupWindow(this);
		popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		
		final TextView textView = new TextView(this);
		textView.setBackgroundResource(R.drawable.list_item_selector);
		textView.setText("Copy");
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cbm.setText(tv.getText());//���Ƶ����а�
				popupWindow.dismiss();
			}
		});
		popupWindow.setContentView(textView);
		popupWindow.showAsDropDown(tv,10,10);
		
	}
	
	//��ʾpopupWindow
	public void showPopupWindow(View v){
		final PopupWindow popupWindow = new PopupWindow(this);
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));
		popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		
		View view = LayoutInflater.from(this).inflate(R.layout.custom_alert_dialog, null);
		Button button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		//��View������popupwindow��
		popupWindow.setContentView(view);
		//popupWindow.showAsDropDown(v, 10, 10);
		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
		
	}

}


















