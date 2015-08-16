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

	//�������ʱ��ʾ���ݵ�Activity
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

	//����Fragment
	public static class TitleFragment extends ListFragment {

		boolean mDualpane;//˫����:��������ʾʱ����title��details
		int mCurCheckPosition = 0;//��ǰѡ�е�λ��

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			//setListAdapter();�Ǽ̳е�ListFragment���Ѿ��еķ���
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					R.layout.autocomplete_textview, Shakespeare.TITLES));
			//��ȡ���ݵĲ�����ͼ
			View detailsFrame = getActivity().findViewById(R.id.details);
			mDualpane = detailsFrame != null
					&& detailsFrame.getVisibility() == View.VISIBLE;
			if (savedInstanceState != null) {
				//���ѡ����ĳ��,�򱣴浱ǰѡ���״̬
				mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
			}
			if (mDualpane) {
				//�����˫����,����ѡ��ģʽΪ��ѡ
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				//��ʾ��ǰѡ���������
				showDetail(mCurCheckPosition);
			}
		}

		/**
		 * ѡ����״̬���淽��
		 */
		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("curChoice", mCurCheckPosition);
		}
		
		/**
		 * ����������ʾ����
		 * @param index
		 */
		public void showDetail(int index){
			//���õ�ǰѡ�е�ĳ��
			mCurCheckPosition = index;
			if(mDualpane){//�����˫����
				//ѡ�е�ǰ����
				getListView().setItemChecked(mCurCheckPosition, true);
				DetailsFragment detailsFragment = (DetailsFragment) 
						getFragmentManager().findFragmentById(R.id.details);
				if(detailsFragment == null
						|| detailsFragment.getShownIndex() != index){
					//ʵ����һ��detailsFragment
					detailsFragment = DetailsFragment.newInstance(index);
					//��ʼ����
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.details, detailsFragment, "details");
					//transaction���ֵ�����
					transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					transaction.commit();//�ύ����
				}
			}else{//����˫����
				Intent intent = new Intent();
				//����DetailsActivity
				intent.setClass(getActivity(), DetailsActivity.class);
				intent.putExtra("index", index);
				startActivity(intent);
			}
		}

		/**
		 * item�����¼�
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
