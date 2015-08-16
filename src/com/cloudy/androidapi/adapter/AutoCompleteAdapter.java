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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class AutoCompleteAdapter extends BaseAdapter implements Filterable{

	//将布局文件转换成View视图
	private LayoutInflater inflater;
	private List<FruitBean> list;//补全信息前的小图片的总的列表,导入了一个FruitBean类
	//数组过滤器
	private ArrayFilter mFilter;
	//加锁:同步最小代码段
	@SuppressWarnings("unused")
	private final Object mLock = new Object();
	//经过过滤器筛选后的列表
	private List<FruitBean> filterlist = new ArrayList<FruitBean>();
	
	//构造方法
	public AutoCompleteAdapter(Context context,List<FruitBean> list) {
		this.list = list;
		//从给定的内容获取LayoutInflater
		inflater = LayoutInflater.from(context);
		//inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	//获取所有项的数目
	@Override
	public int getCount() {
		if(filterlist != null){
			return filterlist.size();
		}
		return 0;
	}

	//根据给定的position返回该项的数据内容
	//通常是一个Java Bean
	@Override
	public Object getItem(int position) {
		return filterlist.get(position);
	}

	//根据给定的位置信息返回此位置的项在列表中的Id
	@Override
	public long getItemId(int position) {
		return position;
	}

	//根据给定的位置信息,绑定给定的位置的项的数据
	//position 位置(是从0开始的:因为是计算机是二进制运算)
	//convertView 缓存的项的view
	//parent 指此项是否需要额外的布局属性,通常情况下不需要设置
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder holder;
		if(convertView == null){//还未达到缓存条件,缓存View为空
			holder = new Holder();//生成一个标签
			//重新生成一个数据项的View
			convertView = inflater.inflate(R.layout.autocomplete2_textview, null);
			//给标签赋值
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			//给此项的View设置一个标签
			convertView.setTag(holder);
		}else{//如果此项是使用缓存的View
			//通过缓存的View上的标签得到此项View在缓存里的控件
			//holder 标签保存的就是此项View的控件的引用
			holder = (Holder) convertView.getTag();
		}
		if(position < filterlist.size()){
			//根据给定的位置,绑定每一项的数据
			holder.icon.setBackgroundResource(filterlist.get(position).iconId);
			holder.title.setText(filterlist.get(position).title);
		}
		return convertView;
	}
	
	//holder 标签,给每一个项添加一个标签
	//此标签的内容就是每项View控件的引用
	class Holder{
		ImageView icon;
		TextView title;
	}

	@Override
	public Filter getFilter() {
		if(mFilter == null){
			mFilter = new ArrayFilter();
		}
		return mFilter;
	}
	
	//使用数组过滤的方法
	private class ArrayFilter extends Filter{

		//当输入字符改变的时候,会回调此方法
		//constraint就是输入的内容
		//在未达到提示字符个数的要求之前.constraint为空
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			System.out.println("performFiltering constriant="+constraint);
			//生成一个过滤结果的保存器
			FilterResults results = new FilterResults();
			//新生成一个空的FruitBean列表
			ArrayList<FruitBean> mList = new ArrayList<FruitBean>();
			//在总的水果列表中列出包含输入字符的补全提示信息的内容
			for(int i =0;i<list.size();i++){
				System.out.println("========="+list.get(i).title);
				//添加到新的水果列表中
				if(list.get(i).title.contains(constraint)){
					System.out.println("add to filter>>>>"+list.get(i).title);
					//添加到小图片列表
					mList.add(list.get(i));
				}
			}
			//把新的水果列表保存在过滤后的结果集中
			results.values = mList;
			results.count = mList.size();
			return results;
		}

		//当回调完performFiltering之后,就会回调此方法
		//发布结果集(更新数据)
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			System.out.println("result.values="+results.count);
			System.out.println("publishResults");
			//将结果集赋值给filterlist
			filterlist = (List<FruitBean>) results.values;
			//通知更新
			if(results.count>0){
				//通知所附的观察者，底层的数据已被更改，任何反映了数据集的观点应该刷新。
				notifyDataSetChanged();
			}else{
				//通知数据源发生改变
				notifyDataSetInvalidated();
			}
			
		}
	}

}

















