package com.cloudy.androidapi.activity;

/*
 * 自动完成文本框
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
	//补全提示使用的已存在的句子
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
		//从已有的系统中获得补全信息的内容:即从String.XML中获得main_titles;
		titles = getResources().getStringArray(R.array.main_titles);
		
		//从已有的系统中获得补全信息的内容:即从String.XML中获得fruittitles_title;
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
		
		
		//使用系统的布局文件
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
		//使用自己的布局文件(里面只有一个Textview)
		//ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.textview_auto, titles);
		//使用自己的布局文件(不仅要有一个Textview,还有指定的Id)
		//可过滤的列表适配器的内容
		//ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.autocomplete2_textview,R.id.auto2, titles);
		//更改数据列表用于自动补全。提供的列表必须是可过滤的列表适配器
		//autoComplete.setAdapter(adapter3);
		
		
		
	}

}
















