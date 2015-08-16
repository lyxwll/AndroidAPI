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

	//���ݸ�����λ����Ϣ,���������view���󶨸�����λ�õ��������
	//position λ�� (position��0��ʼ��)
	//convertView ��������view
	//parent ָ�����Ƿ���Ҫ����Ĳ������� ͨ�������,����Ҫ����
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(convertView == null){
			System.out.println("δʹ�û���,convertView == null");
			holder = new Holder();
			convertView = inflater.inflate(R.layout.gridview_item, null);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
			
		}else{
			holder = (Holder) convertView.getTag();
			System.out.println("ʹ���˻��� convertView != null");
		}
		if(position<list.size()){
			holder.icon.setBackgroundResource(list.get(position).iconId);
			holder.title.setText(list.get(position).title);
		}
		
		return convertView;
		
	}
	
	//Holder ��ǩ,��ÿһ�������һ����ǩ
	//�˱�ǩ�����ݾ���ÿ��view�Ŀؼ�������
	class Holder{
		ImageView icon;
		TextView title;
	}
	
	

}
