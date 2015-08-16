package com.cloudy.androidapi.adapter;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

	private String[] groupItems;
	private String[][] childItems;
	private LayoutInflater inflater;

	public ExpandableListViewAdapter(Context context, String[] groupItems,
			String[][] childItems) {
		this.childItems = childItems;
		this.groupItems = groupItems;
		inflater = LayoutInflater.from(context);

	}

	// 返回group项的数目
	@Override
	public int getGroupCount() {
		if (groupItems != null) {
			return groupItems.length;
		}
		return 0;
	}

	// 根据指定的group和position,返回当前group项的子项的数量
	@Override
	public int getChildrenCount(int groupPosition) {
		if (childItems != null && groupPosition < childItems.length) {
			return childItems[groupPosition].length;
		}
		return 0;
	}

	// 根据指定的groupPosition,返回当前group项的内容
	@Override
	public Object getGroup(int groupPosition) {
		return groupItems[groupPosition];
	}

	// 根据给定的groupPosition和childPosition,
	// 返回当前group项下的指定child项的内容
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		if (groupPosition < childItems.length
				&& childPosition < childItems[groupPosition].length) {
			return childItems[groupPosition][childPosition];
		}
		return null;
	}

	// 根据指定的groupPosition,返回当前group项的id
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// 根据给定的groupPosition和childPosition,
	// 返回当前group项下的,childPosition位置的child项的id
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// 是否有稳定的id
	@Override
	public boolean hasStableIds() {
		return false;
	}

	// 生成group项,并绑定group项的数据
	/*
	 * groupPosition group项的位置 isExpanded 是否可扩展 convertView 缓存的View项 parent
	 * 该项的parent
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupHolder groupHolder;
		if (convertView == null) {
			groupHolder = new GroupHolder();
			convertView = inflater.inflate(R.layout.expand_group_item, null);
			groupHolder.groupTextView = (TextView) convertView
					.findViewById(R.id.expand_group);
			convertView.setTag(groupHolder);
		} else {
			groupHolder = (GroupHolder) convertView.getTag();
		}
		if (groupPosition < groupItems.length) {
			groupHolder.groupTextView.setText(groupItems[groupPosition]);
		}

		return convertView;
	}

	// group的标签
	class GroupHolder {
		TextView groupTextView;
	}

	/*
	 * 生成child项的,并绑定child项的数据 
	 * groupPosition group项的位置 
	 * childPosition child项的位置
	 * isLastChild 是否是最后一个子项
	 *  convertView 缓存的View项 
	 *  parent 该项的parent
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		ChildHolder childHolder;
		if (convertView == null) {
			childHolder = new ChildHolder();
			convertView = inflater.inflate(R.layout.expand_child_item, null);
			childHolder.childTextView = (TextView) convertView
					.findViewById(R.id.expand_child);
			convertView.setTag(childHolder);
		} else {
			childHolder = (ChildHolder) convertView.getTag();
		}
		if (groupPosition < childItems.length
				&& childPosition < childItems[groupPosition].length) {
			childHolder.childTextView
					.setText(childItems[groupPosition][childPosition]);

		}

		return convertView;
	}

	// child的标签
	class ChildHolder {
		TextView childTextView;
	}

	// 子项是否是可选择的
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
