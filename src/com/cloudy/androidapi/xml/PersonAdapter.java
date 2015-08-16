package com.cloudy.androidapi.xml;

import java.util.ArrayList;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter{
	
	ArrayList<PersonBean> arrayList;
	LayoutInflater inflater;
	
	public PersonAdapter(Context context,ArrayList<PersonBean> arrayList) {
		this.arrayList = arrayList;
		inflater = LayoutInflater.from(context);
	}
	
	public void setList(ArrayList<PersonBean> arrayList){
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {
		if(arrayList != null){
			return arrayList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(arrayList != null && position < arrayList.size()){
			return arrayList.get(position);
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
			convertView = inflater .inflate(R.layout.person_item, null);
			holder.id = (TextView) convertView.findViewById(R.id.person_id);
			holder.name = (TextView) convertView.findViewById(R.id.person_name);
			holder.age = (TextView) convertView.findViewById(R.id.person_age);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		if(position < arrayList.size()){
			holder.id.setText(arrayList.get(position).id);
			holder.name.setText(arrayList.get(position).name);
			holder.age.setText(arrayList.get(position).age);
		}
		return convertView;
	}
	
	class Holder{
		TextView id;
		TextView name;
		TextView age;
	}
	
	
	
	

}














