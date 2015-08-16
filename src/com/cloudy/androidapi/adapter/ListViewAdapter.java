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

	// 将布局文件转换成View视图
	private LayoutInflater inflater;
	private List<FruitBean> list;// 补全信息前的小图片的总的列表,导入了一个FruitBean类

	// 构造方法
	public ListViewAdapter(Context context, List<FruitBean> list) {
		this.list = list;
		// 从给定的内容获取LayoutInflater
		inflater = LayoutInflater.from(context);
		// inflater = (LayoutInflater)
		// context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 获取所有项的数目
	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	// 根据给定的position返回该项的数据内容
	// 通常是一个Java Bean
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	// 根据给定的位置信息返回此位置的项在列表中的Id
	@Override
	public long getItemId(int position) {
		return position;
	}

	// 根据给定的位置信息,绑定给定的位置的项的数据
	// position 位置(是从0开始的:因为是计算机是二进制运算)
	// convertView 缓存的项的view
	// parent 指此项是否需要额外的布局属性,通常情况下不需要设置
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder;
		if (convertView == null) {// 还未达到缓存条件,缓存View为空
			holder = new Holder();// 生成一个标签
			// 重新生成一个数据项的View
			convertView = inflater.inflate(R.layout.autocomplete2_textview,
					null);
			// 给标签赋值
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			// 给此项的View设置一个标签
			convertView.setTag(holder);
		} else {// 如果此项是使用缓存的View
				// 通过缓存的View上的标签得到此项View在缓存里的控件
				// holder 标签保存的就是此项View的控件的引用
			holder = (Holder) convertView.getTag();
		}
		if (position < list.size()) {
			// 根据给定的位置,绑定每一项的数据
			holder.icon.setBackgroundResource(list.get(position).iconId);
			holder.title.setText(list.get(position).title);
		}
		return convertView;
	}

	// holder 标签,给每一个项添加一个标签
	// 此标签的内容就是每项View控件的引用
	class Holder {
		ImageView icon;
		TextView title;
	}

}
