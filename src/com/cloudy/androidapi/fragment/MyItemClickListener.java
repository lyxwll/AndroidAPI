package com.cloudy.androidapi.fragment;

import android.view.View;
import android.widget.ListView;

public interface MyItemClickListener {

	void onListItemClick(ListView l, View v, int position, long id);
	
}
