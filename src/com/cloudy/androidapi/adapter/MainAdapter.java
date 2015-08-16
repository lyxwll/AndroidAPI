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
	
	//���췽��
	public MainAdapter(String[] title, Context context) {
		this.titles = title;
		inflater = LayoutInflater.from(context);
	}
	
	//������ �ж��� ������
	@Override
	public int getCount() {
		if(titles != null) {
			return titles.length;
		}
		return 0;
	}

	//����ָ����λ�� ,���ص�ǰ����
	@Override
	public Object getItem(int position) {
		if(position >=0 && position < titles.length) {
			return titles[position];
		}
		return null;
	}

	//����ָ����λ�ã����ص�ǰλ�� �� �� id
	@Override
	public long getItemId(int position) {
		return position;
	}

	//����ָ���� position ������
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.top_list_item, null);
			holder.textView = (TextView) convertView.findViewById(R.id.title);
			/* ���������ͼ�����ı�ǩ.һ����ǩ���Ա�������������νṹ����ͼ,
			 * ����һ���ǲ�νṹ����Ψһ��.
			 * ��ǩҲ���������洢һ����ͼ�ڵ�����������������һ���ݽṹ*/
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if(position < titles.length) {
			//������
			holder.textView.setText(titles[position]);
		}
		return convertView;
	}

	class Holder {
		TextView textView;
	}
	
}
