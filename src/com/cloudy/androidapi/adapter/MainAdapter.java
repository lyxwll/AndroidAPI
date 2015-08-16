package com.cloudy.androidapi.adapter;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

	private String[] titles;
	private LayoutInflater inflater;
	
	//构造方法
	public MainAdapter(String[] title, Context context) {
		this.titles = title;
		inflater = LayoutInflater.from(context);
	}
	
	//适配器 有多少 个数据
	@Override
	public int getCount() {
		if(titles != null) {
			return titles.length;
		}
		return 0;
	}

	//根据指定的位置 ,返回当前的项
	@Override
	public Object getItem(int position) {
		if(position >=0 && position < titles.length) {
			return titles[position];
		}
		return null;
	}

	//根据指定的位置，返回当前位置 项 的 id
	@Override
	public long getItemId(int position) {
		return position;
	}

	//根据指定的 position 绑定数据
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.top_list_item, null);
			holder.textView = (TextView) convertView.findViewById(R.id.title);
			/* 设置与此视图关联的标签.一个标签可以被用来标记在其层次结构的视图,
			 * 并不一定是层次结构中是唯一的.
			 * 标签也可以用来存储一个视图内的数据无需求助于另一数据结构*/
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if(position < titles.length) {
			//绑定数据
			holder.textView.setText(titles[position]);
		}
		return convertView;
	}

	class Holder {
		TextView textView;
	}
	
}
