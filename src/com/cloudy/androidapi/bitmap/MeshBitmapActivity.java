package com.cloudy.androidapi.bitmap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MeshBitmapActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		MeshBitmapView meshBitmapView = new MeshBitmapView(this);
		setContentView(meshBitmapView);
	}
	
}
