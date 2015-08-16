package com.cloudy.androidapi.activity;

import java.util.ArrayList;
import java.util.List;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.GridViewAdapter;
import com.cloudy.androidapi.bean.FruitBean;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class GridViewActivity extends Activity{
	
	private GridView gridView;
	
	int[] images = {
			R.drawable.fruit_apple,
			R.drawable.fruit_little_apple,
			R.drawable.fruit_big_apple,
			R.drawable.fruit_banana,
			R.drawable.fruit_orange,
			R.drawable.fruit_peach,
			R.drawable.fruit_pear};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_layout);
		
		gridView = (GridView) findViewById(R.id.grid_view);
		
		String[] fruitTitles = getResources().getStringArray(R.array.fruit_titles);
		List<FruitBean> list = new ArrayList<FruitBean>();
		for(int i=0; i < images.length && i < fruitTitles.length; i++) {
			FruitBean bean = new FruitBean();
			bean.title = fruitTitles[i];
			bean.iconId = images[i];
			list.add(bean);
		}
		
		GridViewAdapter adapter = new GridViewAdapter(this, list);
		gridView.setAdapter(adapter);
		
		
	}
	

}





















