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
	String[] groups ={"һ��","����","����"};
	String[][] childs = {
			{"һ����","һ�ų�","һӪ��"},
			{"������","���ų�","��Ӫ��"},
			{"������","���ų�","��Ӫ��"}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_listview_layout);
		
		expandableListView = (ExpandableListView) findViewById(R.id.expand);
		ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(this, groups, childs);
		expandableListView.setAdapter(adapter);
		
		//����ĵ���¼�
		expandableListView.setOnChildClickListener(new OnChildClickListener() {
			//parent ������չ���б���ͼ
			//View �����view
			//groupPosition  ���������ڵ����λ��
			//childPosition  �������λ��
			//id �������id
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(ExpandableListViewActivity.this,"�����"+
					parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString(),
						Toast.LENGTH_SHORT).show();
				//����false:�¼����·�
				//����true:��Ϊ�¼����,�����·�
				return false;
			}
		});
		
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				Toast.makeText(ExpandableListViewActivity.this,"�����"+
						parent.getExpandableListAdapter().getGroup(groupPosition).toString(),
							Toast.LENGTH_SHORT).show();
				//����false:�ᴥ��չ���������¼�
				//����true:���ᴥ����չ�������¼�
				return false;
			}
		});
		
		//group�������ĵ�������¼�
		expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(ExpandableListViewActivity.this,"Collapse"+groupPosition,
						Toast.LENGTH_SHORT).show();
			}
		});
		
		//group�����չ��������¼�
		expandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(ExpandableListViewActivity.this,"Expand"+groupPosition,
						Toast.LENGTH_SHORT).show();
			}
		});
		
	}


}
