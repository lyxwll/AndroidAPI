package com.cloudy.androidapi.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestSwipeRefreshLayout extends FragmentActivity implements OnRefreshListener{

	private static final int REFRESH_COMPLETE = 124;
	private SwipeRefreshLayout mLayout;
	private ListView mListView;
	
	private ArrayAdapter<String> mAdapter;
	private List<String> mDates = new ArrayList<String>
						(Arrays.asList("JAva","JavaScript","C++","Ruby","Html","Json"));
	
	//
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_COMPLETE:
				mDates.addAll(Arrays.asList("Lucene","Canvas","Bitmap"));
				mAdapter.notifyDataSetChanged();
				mLayout.setRefreshing(false);
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe_refresh_layout);
		
		mListView = (ListView) findViewById(R.id.listview_refresh);
		mLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
		
		mLayout.setOnRefreshListener(this);
		mLayout.setColorScheme(R.color.red, R.color.blue, R.color.green, R.color.black);
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mDates);
		mListView.setAdapter(mAdapter);
	}
	
	@Override
	public void onRefresh() {
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
	}
	
	
	

}
