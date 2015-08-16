package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class RatingBarActivity extends Activity{
	
	private RatingBar ratingBar;
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratingbar_layout);
		
		ratingBar = (RatingBar) findViewById(R.id.rating);
		imageView = (ImageView) findViewById(R.id.rating_image);
		imageView.setAlpha(0);
		
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				System.out.println("rating="+rating);
				imageView.setAlpha((int)(rating*255/5));
				
			}
		});
		
	}

}
