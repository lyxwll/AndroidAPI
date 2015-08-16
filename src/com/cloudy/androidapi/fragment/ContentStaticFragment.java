package com.cloudy.androidapi.fragment;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContentStaticFragment extends Fragment{
	
	View view;
	ListView listView;
	
	String[] names = {"张三","李四","王麻子","吴二娃"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.content_fragment_layout, container,false);
		listView = (ListView) view.findViewById(R.id.content_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.autocomplete_textview, names);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new MyOnItemclickListener());
		
		return view;
	}
	
	class MyOnItemclickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(getActivity(), "你点击了"+names[position], Toast.LENGTH_LONG).show();
		}
		
	}
	
	

}

















