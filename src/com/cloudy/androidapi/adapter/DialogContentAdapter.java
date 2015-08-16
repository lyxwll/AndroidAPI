package com.cloudy.androidapi.adapter;

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

public class DialogContentAdapter extends BaseAdapter{
	
	private List<FruitBean> list;
	private LayoutInflater inflater;
	
	public DialogContentAdapter(Context context,List<FruitBean> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if(list != null){
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(list != null && position < list.size()){
			return list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(convertView == null){
			holder = new Holder();
			convertView = inflater.inflate(R.layout.gridview_item, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
			
		}else{
			holder = (Holder) convertView.getTag();
		}
		if(position < list.size()){
			holder.icon.setBackgroundResource(list.get(position).iconId);
			holder.title.setText(list.get(position).title);
		}
		return convertView;
	}
	
	//给布局的内容设置标签
	class Holder{
		ImageView icon;
		TextView title;
	}
	

}














