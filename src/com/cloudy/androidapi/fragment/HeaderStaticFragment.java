package com.cloudy.androidapi.fragment;



import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HeaderStaticFragment extends Fragment{
	
	View view;
	ImageButton imageButton;
	TextView textView;
	
	public HeaderStaticFragment() {

	}
	
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		System.out.println("HeaderStaticFragment--------->>>onAttach");
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("HeaderStaticFragment--------->>>onCreate");
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("HeaderStaticFragment--------->>>onCreateView");
		
		view = inflater.inflate(R.layout.header_fragment_layout, container);
		imageButton = (ImageButton) view.findViewById(R.id.image_button);
		textView = (TextView) view.findViewById(R.id.frag_title);
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "µã»÷ÁËImageButton", Toast.LENGTH_LONG).show();
			}
		});
		return view;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		System.out.println("HeaderStaticFragment--------->>>onActivityCreated");
	}
	
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		System.out.println("HeaderStaticFragment--------->>>onViewStateRestored");
	}
	
	public void onStart() {
		super.onStart();
		System.out.println("HeaderStaticFragment--------->>>onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		System.out.println("HeaderStaticFragment--------->>>onResume");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		System.out.println("HeaderStaticFragment--------->>>onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		System.out.println("HeaderStaticFragment--------->>>onStop");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("HeaderStaticFragment--------->>>onDestroyView");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("HeaderStaticFragment--------->>>onDestroy");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		System.out.println("HeaderStaticFragment--------->>>onDetach");
	}
	
	

}












