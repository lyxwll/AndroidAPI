package com.cloudy.androidapi.adapter;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter{
	
	private Context mContext;
	private int[] images;
	
	public GalleryAdapter(int[] images,Context context) {
		this.images = images;
		this.mContext =context;
		
	}

	@Override
	public int getCount() {
		if(images != null){
			return images.length;
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(images != null){
			return images[position];
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	//≤ª π”√ª∫¥Ê
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		ImageView imageView = (ImageView) inflater.inflate(R.layout.gallery_item, null);
		if(position < images.length){
			imageView.setBackgroundResource(images[position]);
		}
		return imageView;
	}
	
	
	

}
