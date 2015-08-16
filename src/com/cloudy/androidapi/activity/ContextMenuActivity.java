package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;

public class ContextMenuActivity extends Activity{
	
	//��ɫ��־
	static final int COLOR_RED = 0x123;
	static final int COLOR_GREEN = 0x124;
	static final int COLOR_BLUE = 0x125;
	
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.context_menu_layout);
		
		textView = (TextView) findViewById(R.id.context_edit);
		//
		registerForContextMenu(textView);
		
		/**
		 * �����Ĳ˵���Ҫ��������,��ͬʱע���˳����¼��������Ĳ˵�ʱ,
		 * ���ȴ��������¼�,�������¼�����������falseʱ,��ᴥ�������Ĳ˵��¼�
		 */
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		//��������Ĳ˵�
		menu.add(0, COLOR_RED, 0, R.string.color_red);
		menu.add(0, COLOR_GREEN, 0, R.string.color_grenn);
		menu.add(0, COLOR_BLUE, 0, R.string.color_blue);
		//���ò˵���ͼ��,����
		menu.setHeaderIcon(R.drawable.fruit_little_apple);
		menu.setHeaderTitle(R.string.color_menu_title);
		//����Ϊ��ѡ��
		menu.setGroupCheckable(0, true, true);
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case COLOR_RED:
			item.setChecked(true);
			textView.setTextColor(Color.RED);
			break;
		case COLOR_GREEN:
			item.setChecked(true);
			textView.setTextColor(Color.GREEN);
			break;
		case COLOR_BLUE:
			item.setChecked(true);
			textView.setTextColor(Color.BLUE);
			break;
		}
		
		return super.onContextItemSelected(item);
	}
	

}
















