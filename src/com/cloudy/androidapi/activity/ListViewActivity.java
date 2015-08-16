package com.cloudy.androidapi.activity;

import java.util.ArrayList;
import java.util.List;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.ListViewAdapter;
import com.cloudy.androidapi.bean.FruitBean;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends Activity {

	private ListView listView;

	int[] images = { R.drawable.fruit_apple, R.drawable.fruit_little_apple,
			R.drawable.fruit_big_apple, R.drawable.fruit_banana,
			R.drawable.fruit_orange, R.drawable.fruit_peach,
			R.drawable.fruit_pear, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_layout);

		listView = (ListView) findViewById(R.id.list_view);

		// �洢fruit_titles������
		String[] fruittitles = getResources().getStringArray(
				R.array.fruit_titles);
		List<FruitBean> list = new ArrayList<FruitBean>();
		for (int i = 0; i < images.length && i < fruittitles.length; i++) {
			FruitBean bean = new FruitBean();
			bean.title = fruittitles[i];
			bean.iconId = images[i];
			list.add(bean);

		}

		ListViewAdapter adapter = new ListViewAdapter(this, list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new MyOnItemClickListener());
		listView.setOnItemLongClickListener(new MyOnItemLongClickListener());

	}

	class MyOnItemClickListener implements OnItemClickListener {

		// parent ��ǰ��adapter���View ��:ListView AutoCompleteTextView GridView
		// SpinnerView
		// view ��ǰ���View����
		// position ��ǰ�����λ��
		// Id ��ǰ������Id
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (parent.getAdapter() instanceof ListViewAdapter) {
				FruitBean bean = (FruitBean) parent.getAdapter().getItem(
						position);
				Toast.makeText(ListViewActivity.this, "�����" + bean.title,
						Toast.LENGTH_SHORT).show();

			}
		}
	}

	class MyOnItemLongClickListener implements OnItemLongClickListener {

		// parent ��ǰ��adapter���View ��:ListView AutoCompleteTextView GridView
		// SpinnerView
		// view ��ǰ���View����
		// position ��ǰ�����λ��
		// Id ��ǰ������Id
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			// ������ֵΪfalse,�¼����·�,���ἤ��OnItemClick�¼�
			// Ϊtrueʱ,�����·��¼�,Ҳ�Ͳ���ص��̰��¼�(OnItemClick),��Ϊ�¼��Ѿ��������
			return false;
		}

	}

}
