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

public class ListViewAdapter extends BaseAdapter {

	// �������ļ�ת����View��ͼ
	private LayoutInflater inflater;
	private List<FruitBean> list;// ��ȫ��Ϣǰ��СͼƬ���ܵ��б�,������һ��FruitBean��

	// ���췽��
	public ListViewAdapter(Context context, List<FruitBean> list) {
		this.list = list;
		// �Ӹ��������ݻ�ȡLayoutInflater
		inflater = LayoutInflater.from(context);
		// inflater = (LayoutInflater)
		// context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// ��ȡ���������Ŀ
	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	// ���ݸ�����position���ظ������������
	// ͨ����һ��Java Bean
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	// ���ݸ�����λ����Ϣ���ش�λ�õ������б��е�Id
	@Override
	public long getItemId(int position) {
		return position;
	}

	// ���ݸ�����λ����Ϣ,�󶨸�����λ�õ��������
	// position λ��(�Ǵ�0��ʼ��:��Ϊ�Ǽ�����Ƕ���������)
	// convertView ��������view
	// parent ָ�����Ƿ���Ҫ����Ĳ�������,ͨ������²���Ҫ����
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder;
		if (convertView == null) {// ��δ�ﵽ��������,����ViewΪ��
			holder = new Holder();// ����һ����ǩ
			// ��������һ���������View
			convertView = inflater.inflate(R.layout.autocomplete2_textview,
					null);
			// ����ǩ��ֵ
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			// �������View����һ����ǩ
			convertView.setTag(holder);
		} else {// ���������ʹ�û����View
				// ͨ�������View�ϵı�ǩ�õ�����View�ڻ�����Ŀؼ�
				// holder ��ǩ����ľ��Ǵ���View�Ŀؼ�������
			holder = (Holder) convertView.getTag();
		}
		if (position < list.size()) {
			// ���ݸ�����λ��,��ÿһ�������
			holder.icon.setBackgroundResource(list.get(position).iconId);
			holder.title.setText(list.get(position).title);
		}
		return convertView;
	}

	// holder ��ǩ,��ÿһ�������һ����ǩ
	// �˱�ǩ�����ݾ���ÿ��View�ؼ�������
	class Holder {
		ImageView icon;
		TextView title;
	}

}
