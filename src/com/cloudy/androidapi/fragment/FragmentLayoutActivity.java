package com.cloudy.androidapi.fragment;

import com.cloudy.androidapi.R;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class FragmentLayoutActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_layout);
	}

	//竖屏点击时显示内容的Activity
	public static class DetailsActivity extends FragmentActivity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				finish();
				return;
			}
			if (savedInstanceState == null) {
				setContentView(R.layout.fragment_content_layout);
				System.out.println("AAAAAAAAAA");
				DetailsFragment details = new DetailsFragment();
				details.setArguments(getIntent().getExtras());
				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager.beginTransaction()
						.add(R.id.frag_content, details).commit();
			}
		}
	}

	//标题Fragment
	public static class TitleFragment extends ListFragment {

		boolean mDualpane;//双窗格:即横屏显示时出现title和details
		int mCurCheckPosition = 0;//当前选中的位置

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			//setListAdapter();是继承的ListFragment里已经有的方法
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					R.layout.autocomplete_textview, Shakespeare.TITLES));
			//获取内容的布局视图
			View detailsFrame = getActivity().findViewById(R.id.details);
			mDualpane = detailsFrame != null
					&& detailsFrame.getVisibility() == View.VISIBLE;
			if (savedInstanceState != null) {
				//如果选择了某项,则保存当前选择的状态
				mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
			}
			if (mDualpane) {
				//如果是双窗格,设置选择模式为单选
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				//显示当前选择项的内容
				showDetail(mCurCheckPosition);
			}
		}

		/**
		 * 选择项状态保存方法
		 */
		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("curChoice", mCurCheckPosition);
		}
		
		/**
		 * 标题内容显示方法
		 * @param index
		 */
		public void showDetail(int index){
			//设置当前选中的某项
			mCurCheckPosition = index;
			if(mDualpane){//如果是双窗口
				//选中当前的项
				getListView().setItemChecked(mCurCheckPosition, true);
				DetailsFragment detailsFragment = (DetailsFragment) 
						getFragmentManager().findFragmentById(R.id.details);
				if(detailsFragment == null
						|| detailsFragment.getShownIndex() != index){
					//实例化一个detailsFragment
					detailsFragment = DetailsFragment.newInstance(index);
					//开始事务
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.details, detailsFragment, "details");
					//transaction出现的类型
					transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					transaction.commit();//提交事务
				}
			}else{//不是双窗口
				Intent intent = new Intent();
				//启动DetailsActivity
				intent.setClass(getActivity(), DetailsActivity.class);
				intent.putExtra("index", index);
				startActivity(intent);
			}
		}

		/**
		 * item项点击事件
		 */
		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			showDetail(position);
		}
	}

	/**
	 * 
	 *DetailsFragment
	 * @author Administrator
	 */
	public static class DetailsFragment extends Fragment {

		public static DetailsFragment newInstance(int index) {
			DetailsFragment f = new DetailsFragment();
			Bundle args = new Bundle();
			args.putInt("index", index);
			f.setArguments(args);
			return f;
		}

		public int getShownIndex() {
			return getArguments().getInt("index");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if (container == null) {
				return null;
			}
			ScrollView scrollView = new ScrollView(getActivity());
			LinearLayout linearLayout = new LinearLayout(getActivity());
			linearLayout.setOrientation(LinearLayout.VERTICAL);
			TextView titles = new TextView(getActivity());
			titles.setPadding(10, 10, 10, 10);
			titles.setText(Shakespeare.TITLES[getShownIndex()]);
			titles.setBackgroundResource(R.drawable.edit_text_bg_selected);
			LinearLayout.LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			layoutParams.bottomMargin = 10;
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			layoutParams.topMargin = 10;
			titles.setLayoutParams(layoutParams);
			TextView textView = new TextView(getActivity());
			textView.setBackgroundResource(R.drawable.edit_text_bg_normal);
			int padding = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
							.getResources().getDisplayMetrics());
			textView.setPadding(padding, padding, padding, padding);
			textView.setLayoutParams(layoutParams);
			linearLayout.addView(titles);
			linearLayout.addView(textView);
			scrollView.addView(linearLayout);
			textView.setText(Shakespeare.DIALOGUE[getShownIndex()]);

			return scrollView;

		}
	}

}
