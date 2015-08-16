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

	// ����group�����Ŀ
	@Override
	public int getGroupCount() {
		if (groupItems != null) {
			return groupItems.length;
		}
		return 0;
	}

	// ����ָ����group��position,���ص�ǰgroup������������
	@Override
	public int getChildrenCount(int groupPosition) {
		if (childItems != null && groupPosition < childItems.length) {
			return childItems[groupPosition].length;
		}
		return 0;
	}

	// ����ָ����groupPosition,���ص�ǰgroup�������
	@Override
	public Object getGroup(int groupPosition) {
		return groupItems[groupPosition];
	}

	// ���ݸ�����groupPosition��childPosition,
	// ���ص�ǰgroup���µ�ָ��child�������
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		if (groupPosition < childItems.length
				&& childPosition < childItems[groupPosition].length) {
			return childItems[groupPosition][childPosition];
		}
		return null;
	}

	// ����ָ����groupPosition,���ص�ǰgroup���id
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	// ���ݸ�����groupPosition��childPosition,
	// ���ص�ǰgroup���µ�,childPositionλ�õ�child���id
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	// �Ƿ����ȶ���id
	@Override
	public boolean hasStableIds() {
		return false;
	}

	// ����group��,����group�������
	/*
	 * groupPosition group���λ�� isExpanded �Ƿ����չ convertView �����View�� parent
	 * �����parent
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

	// group�ı�ǩ
	class GroupHolder {
		TextView groupTextView;
	}

	/*
	 * ����child���,����child������� 
	 * groupPosition group���λ�� 
	 * childPosition child���λ��
	 * isLastChild �Ƿ������һ������
	 *  convertView �����View�� 
	 *  parent �����parent
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

	// child�ı�ǩ
	class ChildHolder {
		TextView childTextView;
	}

	// �����Ƿ��ǿ�ѡ���
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
