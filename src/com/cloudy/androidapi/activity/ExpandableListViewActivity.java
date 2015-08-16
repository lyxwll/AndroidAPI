package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.ExpandableListViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class ExpandableListViewActivity extends Activity{
	
	private ExpandableListView expandableListView;
	String[] groups ={"一连","二连","三连"};
	String[][] childs = {
			{"一连长","一排长","一营长"},
			{"二连长","二排长","二营长"},
			{"三连长","三排长","三营长"}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_listview_layout);
		
		expandableListView = (ExpandableListView) findViewById(R.id.expand);
		ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(this, groups, childs);
		expandableListView.setAdapter(adapter);
		
		//子项的点击事件
		expandableListView.setOnChildClickListener(new OnChildClickListener() {
			//parent 可以扩展的列表视图
			//View 子项的view
			//groupPosition  该子项所在的组的位置
			//childPosition  该子项的位置
			//id 该子项的id
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(ExpandableListViewActivity.this,"点击了"+
					parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString(),
						Toast.LENGTH_SHORT).show();
				//返回false:事件会下发
				//返回true:认为事件完成,不会下发
				return false;
			}
		});
		
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				Toast.makeText(ExpandableListViewActivity.this,"点击了"+
						parent.getExpandableListAdapter().getGroup(groupPosition).toString(),
							Toast.LENGTH_SHORT).show();
				//返回false:会触发展开和收缩事件
				//返回true:不会触发扩展和收缩事件
				return false;
			}
		});
		
		//group项收缩的点击监听事件
		expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(ExpandableListViewActivity.this,"Collapse"+groupPosition,
						Toast.LENGTH_SHORT).show();
			}
		});
		
		//group项的扩展点击监听事件
		expandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(ExpandableListViewActivity.this,"Expand"+groupPosition,
						Toast.LENGTH_SHORT).show();
			}
		});
		
	}


}
