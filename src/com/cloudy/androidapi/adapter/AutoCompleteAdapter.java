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

	//�������ļ�ת����View��ͼ
	private LayoutInflater inflater;
	private List<FruitBean> list;//��ȫ��Ϣǰ��СͼƬ���ܵ��б�,������һ��FruitBean��
	//���������
	private ArrayFilter mFilter;
	//����:ͬ����С�����
	@SuppressWarnings("unused")
	private final Object mLock = new Object();
	//����������ɸѡ����б�
	private List<FruitBean> filterlist = new ArrayList<FruitBean>();
	
	//���췽��
	public AutoCompleteAdapter(Context context,List<FruitBean> list) {
		this.list = list;
		//�Ӹ��������ݻ�ȡLayoutInflater
		inflater = LayoutInflater.from(context);
		//inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	//��ȡ���������Ŀ
	@Override
	public int getCount() {
		if(filterlist != null){
			return filterlist.size();
		}
		return 0;
	}

	//���ݸ�����position���ظ������������
	//ͨ����һ��Java Bean
	@Override
	public Object getItem(int position) {
		return filterlist.get(position);
	}

	//���ݸ�����λ����Ϣ���ش�λ�õ������б��е�Id
	@Override
	public long getItemId(int position) {
		return position;
	}

	//���ݸ�����λ����Ϣ,�󶨸�����λ�õ��������
	//position λ��(�Ǵ�0��ʼ��:��Ϊ�Ǽ�����Ƕ���������)
	//convertView ��������view
	//parent ָ�����Ƿ���Ҫ����Ĳ�������,ͨ������²���Ҫ����
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder holder;
		if(convertView == null){//��δ�ﵽ��������,����ViewΪ��
			holder = new Holder();//����һ����ǩ
			//��������һ���������View
			convertView = inflater.inflate(R.layout.autocomplete2_textview, null);
			//����ǩ��ֵ
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			//�������View����һ����ǩ
			convertView.setTag(holder);
		}else{//���������ʹ�û����View
			//ͨ�������View�ϵı�ǩ�õ�����View�ڻ�����Ŀؼ�
			//holder ��ǩ����ľ��Ǵ���View�Ŀؼ�������
			holder = (Holder) convertView.getTag();
		}
		if(position < filterlist.size()){
			//���ݸ�����λ��,��ÿһ�������
			holder.icon.setBackgroundResource(filterlist.get(position).iconId);
			holder.title.setText(filterlist.get(position).title);
		}
		return convertView;
	}
	
	//holder ��ǩ,��ÿһ�������һ����ǩ
	//�˱�ǩ�����ݾ���ÿ��View�ؼ�������
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
	
	//ʹ��������˵ķ���
	private class ArrayFilter extends Filter{

		//�������ַ��ı��ʱ��,��ص��˷���
		//constraint�������������
		//��δ�ﵽ��ʾ�ַ�������Ҫ��֮ǰ.constraintΪ��
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			System.out.println("performFiltering constriant="+constraint);
			//����һ�����˽���ı�����
			FilterResults results = new FilterResults();
			//������һ���յ�FruitBean�б�
			ArrayList<FruitBean> mList = new ArrayList<FruitBean>();
			//���ܵ�ˮ���б����г����������ַ��Ĳ�ȫ��ʾ��Ϣ������
			for(int i =0;i<list.size();i++){
				System.out.println("========="+list.get(i).title);
				//��ӵ��µ�ˮ���б���
				if(list.get(i).title.contains(constraint)){
					System.out.println("add to filter>>>>"+list.get(i).title);
					//��ӵ�СͼƬ�б�
					mList.add(list.get(i));
				}
			}
			//���µ�ˮ���б����ڹ��˺�Ľ������
			results.values = mList;
			results.count = mList.size();
			return results;
		}

		//���ص���performFiltering֮��,�ͻ�ص��˷���
		//���������(��������)
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			System.out.println("result.values="+results.count);
			System.out.println("publishResults");
			//���������ֵ��filterlist
			filterlist = (List<FruitBean>) results.values;
			//֪ͨ����
			if(results.count>0){
				//֪ͨ�����Ĺ۲��ߣ��ײ�������ѱ����ģ��κη�ӳ�����ݼ��Ĺ۵�Ӧ��ˢ�¡�
				notifyDataSetChanged();
			}else{
				//֪ͨ����Դ�����ı�
				notifyDataSetInvalidated();
			}
			
		}
	}

}

















