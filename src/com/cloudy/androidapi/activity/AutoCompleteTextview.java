package com.cloudy.androidapi.activity;

/*
 * �Զ�����ı���
 */
import java.util.ArrayList;
import java.util.List;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.AutoCompleteAdapter;
import com.cloudy.androidapi.bean.FruitBean;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextview extends Activity{
	
	private AutoCompleteTextView autoComplete;
	//��ȫ��ʾʹ�õ��Ѵ��ڵľ���
	String[] titles;
	
	int[] images = {
			R.drawable.fruit_apple,R.drawable.fruit_little_apple,
			R.drawable.fruit_big_apple,R.drawable.fruit_banana,
			R.drawable.fruit_orange,R.drawable.fruit_peach,
			R.drawable.fruit_pear,
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aotucomplete_layout);
		
		autoComplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
		//�����е�ϵͳ�л�ò�ȫ��Ϣ������:����String.XML�л��main_titles;
		titles = getResources().getStringArray(R.array.main_titles);
		
		//�����е�ϵͳ�л�ò�ȫ��Ϣ������:����String.XML�л��fruittitles_title;
		String[] fruittitles = getResources().getStringArray(R.array.fruit_titles);
		List<FruitBean> list = new ArrayList<FruitBean>();
		for(int i=0;i<images.length&&i<fruittitles.length;i++){
			FruitBean bean = new FruitBean();
			bean.title = fruittitles[i];
			bean.iconId = images[i];
			list.add(bean);
			
		}
		
		AutoCompleteAdapter adapter = new AutoCompleteAdapter(this, list);
		autoComplete.setAdapter(adapter);
		
		
		//ʹ��ϵͳ�Ĳ����ļ�
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
		//ʹ���Լ��Ĳ����ļ�(����ֻ��һ��Textview)
		//ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.textview_auto, titles);
		//ʹ���Լ��Ĳ����ļ�(����Ҫ��һ��Textview,����ָ����Id)
		//�ɹ��˵��б�������������
		//ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.autocomplete2_textview,R.id.auto2, titles);
		//���������б������Զ���ȫ���ṩ���б�����ǿɹ��˵��б�������
		//autoComplete.setAdapter(adapter3);
		
		
		
	}

}
















