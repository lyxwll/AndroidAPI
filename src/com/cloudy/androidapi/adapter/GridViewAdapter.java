package com.cloudy.androidapi.adapter;

import java.util.ArrayList;
import java.util.List;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.bean.FruitBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private List<FruitBean> list = new ArrayList<FruitBean>();
	
	public GridViewAdapter(Context context,List<FruitBean> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);
		
	}

	@Override
	public int getCount() {
		if(list!=null){
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(position<list.size()){
			return list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	//根据给定的位置信息,创建此项的view并绑定给定的位置的项的数据
	//position 位置 (position从0开始的)
	//convertView 缓存的项的view
	//parent 指此项是否需要额外的布局属性 通常情况下,不需要设置
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(convertView == null){
			System.out.println("未使用缓存,convertView == null");
			holder = new Holder();
			convertView = inflater.inflate(R.layout.gridview_item, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
			
		}else{
			holder = (Holder) convertView.getTag();
			System.out.println("使用了缓存 convertView != null");
		}
		if(position<list.size()){
			holder.icon.setBackgroundResource(list.get(position).iconId);
			holder.title.setText(list.get(position).title);
		}
		
		return convertView;
		
	}
	
	//Holder 标签,给每一个项添加一个标签
	//此标签的内容就是每项view的控件的引用
	class Holder{
		ImageView icon;
		TextView title;
	}
	
	

}
