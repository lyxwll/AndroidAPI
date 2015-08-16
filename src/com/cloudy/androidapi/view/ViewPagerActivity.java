package com.cloudy.androidapi.view;

import java.util.ArrayList;
import java.util.List;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerActivity extends Activity{
	
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_view_pager);
		
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		List<View> list = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.absolute_layout, null);
		View view2 = inflater.inflate(R.layout.linear_layout, null);
		View view3 = inflater.inflate(R.layout.table_layout, null);
		list.add(view);
		list.add(view2);
		list.add(view3);
		viewPager.setAdapter(new MyPagerAdapter(list));
		viewPager.setCurrentItem(0);
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			double previousPositionOffset = -1;//
			double nextPositionOffset;//
			
			@Override
			public void onPageSelected(int position) {
				System.out.println("onPageSelected=" + position);
			}
			/**
			 * position:��ǰ������ʾ�ĵ�һҳ��λ�õ�����
			 * positionOffset: ƫ����
			 * positionOffsetPixels: ƫ�Ƶ�����
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				if(previousPositionOffset == -1){
					previousPositionOffset = positionOffset;
				}
				nextPositionOffset = positionOffset;
				if(nextPositionOffset > previousPositionOffset){//���󻬶�
					System.out.println("���󻬶�");
				}else if(nextPositionOffset < previousPositionOffset){
					System.out.println("���ұ߻���");
				}
				System.out.println("position=" + position + ",positionOffset=" + positionOffset + ",positionOffsetPixels=" + positionOffsetPixels);
			}
			
			//����״̬�ı�
			@Override
			public void onPageScrollStateChanged(int state) {
				switch (state) {
				case ViewPager.SCROLL_STATE_DRAGGING://��ק��
					System.out.println("SCROLL_STATE_DRAGGING");
					break;
				case ViewPager.SCROLL_STATE_IDLE://����״̬
					System.out.println("SCROLL_STATE_IDLE");
					break;
				case ViewPager.SCROLL_STATE_SETTLING://����״̬
					System.out.println("SCROLL_STATE_SETTLING");
					break;
				}
			}
		});
		
	}
	
	class MyPagerAdapter extends PagerAdapter{

		List<View> views;
		
		public MyPagerAdapter(List<View> views) {
			this.views = views;
		}
		
		//����ָ��λ�õ���
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(views.get(position));
		}
		
		@Override
		public void finishUpdate(View container) {
			super.finishUpdate(container);
		}
		
		//�൱��Adapter ���getView
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewGroup) container).addView(views.get(position),0);
			return views.get(position);
		}
		//���ص�ǰ�ж���ҳ
		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
			super.restoreState(state, loader);
		}
		
		@Override
		public Parcelable saveState() {
			return super.saveState();
		}
		
		@Override
		public void startUpdate(View container) {
			
		}
		
	}
	
	
	
	
	

}
