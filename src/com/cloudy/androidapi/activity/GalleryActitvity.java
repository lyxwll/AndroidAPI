package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.GalleryAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;

public class GalleryActitvity extends Activity{
	
	private Gallery gallery;
	private ImageSwitcher imageSwitcher;
	int[] images = {
			R.drawable.images1,R.drawable.images2,
			R.drawable.images3,R.drawable.images4,
			R.drawable.images5,R.drawable.images6,
			R.drawable.images7,R.drawable.images8,
			R.drawable.images9,R.drawable.images10,
			R.drawable.images11
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_layout);
		
		gallery = (Gallery) findViewById(R.id.gallery);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imgswitcher);
		
		GalleryAdapter adapter = new GalleryAdapter(images, this);
		gallery.setAdapter(adapter);
		
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageSwitcher.setBackgroundResource(images[position]);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
			
		});
		
		
	}

}
