package com.cloudy.androidapi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.ListViewAdapter;
import com.cloudy.androidapi.bean.FruitBean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SpinnerLayoutActivity extends Activity {

	private Spinner spinner;
	private Spinner spinner2;
	private Spinner spinner3;
	
	private Button button;

	int[] images = { R.drawable.fruit_apple, R.drawable.fruit_little_apple,
			R.drawable.fruit_big_apple, R.drawable.fruit_banana,
			R.drawable.fruit_orange, R.drawable.fruit_peach,
			R.drawable.fruit_pear, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_layout);

		spinner = (Spinner) findViewById(R.id.spinner);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);

		String[] fruitTitles = getResources().getStringArray(
				R.array.fruit_titles);
		List<FruitBean> list = new ArrayList<FruitBean>();
		for (int i = 0; i < images.length && i < fruitTitles.length; i++) {
			FruitBean bean = new FruitBean();
			bean.title = fruitTitles[i];
			bean.iconId = images[i];
			list.add(bean);

		}

		ListViewAdapter adapter = new ListViewAdapter(this, list);
		spinner2.setAdapter(adapter);
		spinner3.setAdapter(adapter);
		
		//设置第三项为默认选中项
		spinner2.setSelection(2);
		//监听选中某项的事件
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				FruitBean bean = (FruitBean) spinner2.getSelectedItem();
				Toast.makeText(SpinnerLayoutActivity.this, "select fruit is "+bean.title, 
						Toast.LENGTH_SHORT).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
		});
		
		
		findViewById(R.id.get_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//获取选中的spinner的值
				FruitBean bean = (FruitBean) spinner2.getSelectedItem();
				Toast.makeText(SpinnerLayoutActivity.this, "select fruit is "+bean.title, 
						Toast.LENGTH_SHORT).show();
				
			}
		});
		
		List<Map<String, Object>> mlist = new ArrayList<Map<String, Object>>();
		for(int i=0;i<images.length&&i<fruitTitles.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", images[i]);
			map.put("title",fruitTitles[i]);
			mlist.add(map);
			
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, mlist, R.layout.autocomplete2_textview,
				new String[]{"icon","title"}, new int[]{R.id.icon,R.id.title});
		spinner3.setAdapter(simpleAdapter);
		
		

	}

}












