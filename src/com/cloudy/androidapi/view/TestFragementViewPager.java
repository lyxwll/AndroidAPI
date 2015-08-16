package com.cloudy.androidapi.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class TestFragementViewPager extends FragmentActivity{
	
	ViewPager viewPager;
	//保存每个碎片
	List<Fragment> list = new ArrayList<Fragment>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_fragment_viewpager);
		list.add(new ViewPagerFragment1());
		list.add(new ViewPagerFragment2());
		list.add(new ViewPagerFragment3());
		viewPager = (ViewPager) findViewById(R.id.fragment_view_pager);
		viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),list));
		
	}
	
	class MyFragmentPagerAdapter extends FragmentPagerAdapter{
		List<Fragment> fragments;
		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment child = fragments.get(position);
			return child;
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
		
	}
	
	
	
	/*ViewPager viewPager;
	//保存每个碎片
	Map<Integer, Fragment> map = new HashMap<Integer, Fragment>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_fragment_viewpager);
		viewPager = (ViewPager) findViewById(R.id.fragment_view_pager);
		viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
		
	}
	 
	  class MyFragmentPagerAdapter extends FragmentPagerAdapter{
		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int position) {
			Fragment child = map.get(position);
			if(child == null){
				switch (position) {
				case 0:
					child = new ViewPagerFragment1();
					map.put(position, child);
					break;
				case 1:
					child = new ViewPagerFragment2();
					map.put(position, child);
					break;
				case 2:
					child = new ViewPagerFragment3();
					map.put(position, child);
					break;
				}
			}
			return child;
		}

		@Override
		public int getCount() {
			return 3;
		}
		
	}*/

}
