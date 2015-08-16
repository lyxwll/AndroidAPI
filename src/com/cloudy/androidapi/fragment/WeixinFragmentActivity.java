package com.cloudy.androidapi.fragment;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.MainAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WeixinFragmentActivity extends FragmentActivity implements
		OnClickListener,MyItemClickListener {

	private TextView weixin;
	private TextView friend;
	private TextView contacts;
	private TextView settings;
	
	public int index;

	@Override
	protected void onCreate(Bundle arg0) {
		System.out.println("onCreate");
		super.onCreate(arg0);

		if(arg0 != null){
			index = arg0.getInt("index",0);
		}
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weixin_fragment_layout);
		weixin = (TextView) findViewById(R.id.bottom_weixin1);
		friend = (TextView) findViewById(R.id.bottom_weixin2);
		contacts = (TextView) findViewById(R.id.bottom_weixin3);
		settings = (TextView) findViewById(R.id.bottom_weixin4);

		Fragment fragment = null;
		switch (index) {
		case 0:
			fragment = WeiXinFragment.newInstance();
			break;
		case 1:
			fragment = FriendFragment.newInstance();
			break;
		case 2:
			fragment = ContactsFragment.newInstance();
			break;
		case 3:
			fragment = SettingsFragment.newInstance();
			break;
		}
		FragmentManager fragmentManager = getSupportFragmentManager();
		//WeiXinFragment mWeiXinFragment = WeiXinFragment.newInstance();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.content_weixin, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
		/*fragmentManager.beginTransaction()
				.replace(R.id.content_weixin, fragment).commit();*/

		weixin.setOnClickListener(this);
		friend.setOnClickListener(this);
		contacts.setOnClickListener(this);
		settings.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		switch (v.getId()) {
		case R.id.bottom_weixin1:
			index = 0;
			WeiXinFragment mWeiXinFragment = WeiXinFragment.newInstance();
			/*fragmentManager.beginTransaction()
					.replace(R.id.content_weixin, mWeiXinFragment)
					.commit();*/
			transaction.replace(R.id.content_weixin, mWeiXinFragment);
			transaction.addToBackStack(null);
			transaction.commit();
			break;
		case R.id.bottom_weixin2:
			index = 1;
			FriendFragment mFriendFragment = FriendFragment.newInstance();
			transaction.replace(R.id.content_weixin, mFriendFragment);
			transaction.addToBackStack(null);
			transaction.commit();
			/*fragmentManager.beginTransaction()
					.replace(R.id.content_weixin, mFriendFragment )
					.commit();*/
			mFriendFragment.setMyItemClickListener(this);
			break;
		case R.id.bottom_weixin3:
			index = 2;
			ContactsFragment mContactsFragment = ContactsFragment.newInstance();
			transaction.replace(R.id.content_weixin, mContactsFragment);
			transaction.addToBackStack(null);
			transaction.commit();
			/*fragmentManager
					.beginTransaction()
					.replace(R.id.content_weixin, mContactsFragment)
					.commit();*/
			break;
		case R.id.bottom_weixin4:
			index = 3;
			SettingsFragment mSettingsFragment = SettingsFragment.newInstance();
			transaction.replace(R.id.content_weixin, mSettingsFragment);
			transaction.addToBackStack(null);
			transaction.commit();
			/*fragmentManager
					.beginTransaction()
					.replace(R.id.content_weixin, mSettingsFragment)
					.commit();*/
			break;
		}
	}
	
	/**
	 * item的点击事件
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String name = l.getAdapter().getItem(position).toString();
		System.out.println("你点击了"+name);
		Toast.makeText(this, "你点击了"+name, Toast.LENGTH_SHORT).show();
	}

	/**
	 *WeiXinFragment
	 * @author Administrator
	 */
	public static class WeiXinFragment extends Fragment {

		public static WeiXinFragment newInstance() {
			WeiXinFragment w = new WeiXinFragment();
			return w;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if (container == null) {
				return null;
			}
			EditText editText = new EditText(getActivity());
			editText.setBackgroundResource(R.drawable.edit_text_bg);
			editText.setMinLines(4);
			editText.setMaxLines(8);
			editText.setGravity(Gravity.CENTER | Gravity.LEFT);
			ViewGroup.LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			editText.setLayoutParams(layoutParams);

			return editText;
		}

	}

	public static class FriendFragment extends ListFragment {

		public static FriendFragment newInstance() {
			FriendFragment f = new FriendFragment();
			return f;
		}
		
		

		@Override
		public void onResume() {
			super.onResume();
			String[] names = { "小名", "小米", "马云", "马化腾", "雷军" };
			MainAdapter adapter = new MainAdapter(names, getActivity());
			setListAdapter(adapter);
			// getListView().setAdapter(adapter);//次两句代码跟setListAdapter效果一样
			// setListShown(true);
		}
		
		MyItemClickListener itemClickListener;
		public void setMyItemClickListener(MyItemClickListener itemClickListener){
			this.itemClickListener = itemClickListener;
		}
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			if(itemClickListener != null){
				itemClickListener.onListItemClick(l, v, position, id);
			}
		}
		
	}

	/**
	 *ContactsFragment
	 * @author Administrator
	 */
	public static class ContactsFragment extends ListFragment {

		public static ContactsFragment newInstance() {
			ContactsFragment c = new ContactsFragment();
			return c;
		}

		@Override
		public void onResume() {
			super.onResume();

			String[] contacts = { "小名", "小米", "马云", "马化腾", "雷军", "李彦宏", "李四" };
			MainAdapter adapter = new MainAdapter(contacts, getActivity());
			setListAdapter(adapter);
		}

	}

	/**
	 *SettingsFragment
	 * @author Administrator
	 */
	public static class SettingsFragment extends ListFragment {
		public static SettingsFragment newInstance() {
			return new SettingsFragment();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if (container == null) {
				return null;
			}

			ImageView imageView = new ImageView(getActivity());
			imageView.setBackgroundResource(R.drawable.c1);
			return imageView;
		}
	}

	

}
