package com.cloudy.androidapi;

import com.cloudy.androidapi.activities.ActivityA;
import com.cloudy.androidapi.activities.CalculateWeightActivity;
import com.cloudy.androidapi.activities.MyLuancher;
import com.cloudy.androidapi.activities.LaunchModeTest;
import com.cloudy.androidapi.activity.ActivityAsDialog;
import com.cloudy.androidapi.activity.AutoCompleteTextview;
import com.cloudy.androidapi.activity.ButtonLayoutActivity;
import com.cloudy.androidapi.activity.ClockLayoutActivity;
import com.cloudy.androidapi.activity.ConfigrutionActivity;
import com.cloudy.androidapi.activity.ConfigrutionTestActivity;
import com.cloudy.androidapi.activity.ContextMenuActivity;
import com.cloudy.androidapi.activity.DateTimePickerActivity;
import com.cloudy.androidapi.activity.DialogActivity;
import com.cloudy.androidapi.activity.ExpandableListViewActivity;
import com.cloudy.androidapi.activity.GalleryActitvity;
import com.cloudy.androidapi.activity.GridViewActivity;
import com.cloudy.androidapi.activity.ListViewActivity;
import com.cloudy.androidapi.activity.MenuActivity;
import com.cloudy.androidapi.activity.NotificationActivity;
import com.cloudy.androidapi.activity.PopupWindowActivity;
import com.cloudy.androidapi.activity.ProgressBarActivity;
import com.cloudy.androidapi.activity.RadioCheckActivity;
import com.cloudy.androidapi.activity.RatingBarActivity;
import com.cloudy.androidapi.activity.RewardsActivity;
import com.cloudy.androidapi.activity.ScrollViewActivity;
import com.cloudy.androidapi.activity.SeekBarActivity;
import com.cloudy.androidapi.activity.SingleOrMultiMenueActivity;
import com.cloudy.androidapi.activity.SpinnerLayoutActivity;
import com.cloudy.androidapi.activity.TabHostActivity;
import com.cloudy.androidapi.activity.TestContainerActivity;
import com.cloudy.androidapi.activity.ToastActivity;
import com.cloudy.androidapi.activity.ToggleButtonActivit;
import com.cloudy.androidapi.activity.TransparentActivity;
import com.cloudy.androidapi.activity.UserInterfaceActivity;
import com.cloudy.androidapi.adapter.MainAdapter;
import com.cloudy.androidapi.animation.TestAnimationActivity;
import com.cloudy.androidapi.applicationpush.TestPush;
import com.cloudy.androidapi.bean.Person;
import com.cloudy.androidapi.bitmap.BitmapTestActivity;
import com.cloudy.androidapi.bitmap.BombAnimationActivity;
import com.cloudy.androidapi.bitmap.DrawBitmapViewActivity;
import com.cloudy.androidapi.bitmap.DrawTextOnPath;
import com.cloudy.androidapi.bitmap.MeshBitmapActivity;
import com.cloudy.androidapi.bitmap.MyDrawViewActivity;
import com.cloudy.androidapi.bitmap.PinPangBallActivity;
import com.cloudy.androidapi.bitmap.ShowWaveActivity;
import com.cloudy.androidapi.bitmap.SurfaceViewActivity;
import com.cloudy.androidapi.bitmap.TestPathEffectActivity;
import com.cloudy.androidapi.broadcastreceiver.DynamicBroadcast;
import com.cloudy.androidapi.contentprovider.ContentProviderActivityTest;
import com.cloudy.androidapi.customview.TestCircleView;
import com.cloudy.androidapi.database.SQLiteDatabaseTest;
import com.cloudy.androidapi.database.SharedPreferenceTest;
import com.cloudy.androidapi.fragment.FragmentLayoutActivity;
import com.cloudy.androidapi.fragment.FragmentTestActivity;
import com.cloudy.androidapi.gesture.GestureFillPer;
import com.cloudy.androidapi.gesture.ImageScaleGestrue;
import com.cloudy.androidapi.http.TestHttpURLActivity;
import com.cloudy.androidapi.intent.ComponentAttr;
import com.cloudy.androidapi.intent.IntentTest;
import com.cloudy.androidapi.internet.ClientSocketActivity;
import com.cloudy.androidapi.internet.TestNetworkURLActivity;
import com.cloudy.androidapi.internet.bitmap.TestBitmapDownloadActivity;
import com.cloudy.androidapi.layout.AbsoluteLayoutActivity;
import com.cloudy.androidapi.layout.CodeMixedLayoutActivity;
import com.cloudy.androidapi.layout.EditTextActivity;
import com.cloudy.androidapi.layout.FrameLayoutActivity;
import com.cloudy.androidapi.layout.ImagesViewLayoutActivity;
import com.cloudy.androidapi.layout.LinearLayoutActivity;
import com.cloudy.androidapi.layout.NoneLayoutActivity;
import com.cloudy.androidapi.layout.RelativeLayoutActivity;
import com.cloudy.androidapi.layout.TableLayoutActivity;
import com.cloudy.androidapi.layout.TextView2layoutActivity;
import com.cloudy.androidapi.layout.TextViewlayoutActivity;
import com.cloudy.androidapi.layout.TrackBallActivity;
import com.cloudy.androidapi.layout.UserLoginlayoutActivity;
import com.cloudy.androidapi.mymap.TestLocationActivity;
import com.cloudy.androidapi.mymap.TestMapActivity;
import com.cloudy.androidapi.res.TestClipActivity;
import com.cloudy.androidapi.res.TestRawAssetActivity;
import com.cloudy.androidapi.service.MyServiceTestActivity;
import com.cloudy.androidapi.view.TestDownRefreshUpLoad;
import com.cloudy.androidapi.view.TestFragementViewPager;
import com.cloudy.androidapi.view.TestRefreshListView;
import com.cloudy.androidapi.view.TestSwipeRefreshLayout;
import com.cloudy.androidapi.view.ViewPagerActivity;
import com.cloudy.androidapi.view.ViewPagerTableHost;
import com.cloudy.androidapi.xml.XmlActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("MainActivity------------>>onCreat");
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listview);
		//������
		String[] titles = getResources().getStringArray(R.array.main_titles);
		MainAdapter adapter = new MainAdapter(titles, this);
		//�����������Ǹ���ά��֧�ִ��б��е����ݣ������ڲ���һ����ͼ�Ա�ʾ���������е���Ŀ��ListAdapter
		listView.setAdapter(adapter);
		// ע�����¼�
		listView.setOnItemClickListener(new MyOnItemClickListener());
	}

	//�ڽ���onPause֮ǰ,��ص��˷���
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	//����Activity�����ٵ�ʱ��,�Ż����
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}
	
	//item��ĵ���¼�
	class MyOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (parent.getAdapter() instanceof MainAdapter) {
				jumpToOtherActivity(position);
			}
		}
	}

	// ��ת����������
	public void jumpToOtherActivity(int position) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent();
			intent.setClass(this, NoneLayoutActivity.class);
			break;
		case 1:
			intent = new Intent();
			intent.setClass(this, CodeMixedLayoutActivity.class);
			break;
		case 2:
			intent = new Intent();
			intent.setClass(this, TrackBallActivity.class);
			break;
		case 3:
			intent = new Intent();
			intent.setClass(this, LinearLayoutActivity.class);
			break;
		case 4:
			intent = new Intent();
			intent.setClass(this, FrameLayoutActivity.class);
			break;
		case 5:
			intent = new Intent();
			intent.setClass(this, RelativeLayoutActivity.class);
			break;
		case 6:
			intent = new Intent();
			intent.setClass(this, AbsoluteLayoutActivity.class);
			break;
		case 7:
			intent = new Intent();
			intent.setClass(this, TableLayoutActivity.class);
			break;
		case 8:
			intent = new Intent();
			intent.setClass(this, TextViewlayoutActivity.class);
			break;
		case 9:
			intent = new Intent();
			intent.setClass(this, TextView2layoutActivity.class);
			break;
		case 10:
			intent = new Intent();
			intent.setClass(this, UserLoginlayoutActivity.class);
			break;
		case 11:
			intent = new Intent();
			intent.setClass(this, EditTextActivity.class);
			break;
		case 12:
			intent = new Intent();
			intent.setClass(this, UserInterfaceActivity.class);
			break;
		case 13:
			intent = new Intent();
			intent.setClass(this, ButtonLayoutActivity.class);
			break;
		case 14:
			intent = new Intent();
			intent.setClass(this, RadioCheckActivity.class);
			break;
		case 15:
			intent = new Intent();
			intent.setClass(this, ToggleButtonActivit.class);
			break;
		case 16:
			intent = new Intent();
			intent.setClass(this, ClockLayoutActivity.class);
			break;
		case 17:
			intent = new Intent();
			intent.setClass(this, ImagesViewLayoutActivity.class);
			break;
		case 18:
			intent = new Intent();
			intent.setClass(this, RewardsActivity.class);
			break;
		case 19:
			intent = new Intent();
			intent.setClass(this, AutoCompleteTextview.class);
			break;
		case 20:
			intent = new Intent(this, ListViewActivity.class);
			break;
		case 21:
			intent = new Intent(this, SpinnerLayoutActivity.class);
			break;
		case 22:
			intent = new Intent(this, GridViewActivity.class);
			break;
		case 23:
			intent = new Intent(this, GalleryActitvity.class);
			break;
		case 24:
			intent = new Intent(this, ExpandableListViewActivity.class);
			break;
		case 25:
			intent = new Intent(this, DateTimePickerActivity.class);
			break;
		case 26:
			intent = new Intent(this, ProgressBarActivity.class);
			break;
		case 27:
			intent = new Intent(this, SeekBarActivity.class);
			break;
		case 28:
			intent = new Intent(this, RatingBarActivity.class);
			break;
		case 29:
			intent = new Intent(this, TabHostActivity.class);
			break;
		case 30:
			intent = new Intent(this, ScrollViewActivity.class);
			break;
		case 31:
			intent = new Intent(this, DialogActivity.class);
			break;
		case 32:
			intent = new Intent(this, ToastActivity.class);
			break;
		case 33:
			intent = new Intent(this,PopupWindowActivity.class);
			break;
		case 34:
			intent = new Intent(this, NotificationActivity.class);
			Person person = new Person("wangmazi",24,"52212719901024555");
			//���ַ���ֵӳ�䵽����Parcelable����
			Bundle bundle = new Bundle();
			bundle.putParcelable("person", person);
			intent.putExtras(bundle);
			break;
		case 35:
			intent = new Intent(this, MenuActivity.class);
			break;
		case 36:
			intent = new Intent(this, SingleOrMultiMenueActivity.class);
			break;
		case 37:
			intent = new Intent(this, ContextMenuActivity.class);
			break;
		case 38:
			intent = new Intent(this, TestContainerActivity.class);
			break;
		case 39:
			intent = new Intent(this, ConfigrutionActivity.class);
			break;
		case 40:
			intent = new Intent(this, ConfigrutionTestActivity.class);
			break;
		case 41:
			intent = new Intent(this, ActivityAsDialog.class);
			break;
		case 42:
			intent = new Intent(this, TransparentActivity.class);
			break;
		case 43:
			intent = new Intent(this, MyLuancher.class);
			break;
		case 44:
			intent = new Intent(this, LaunchModeTest.class);
			break;
		case 45:
			intent = new Intent(this, ActivityA.class);
			break;
		case 46:
			intent = new Intent(this, ComponentAttr.class);
			break;
		case 47:
			intent = new Intent(this, IntentTest.class);
			break;
		case 48:
			intent = new Intent(this, FragmentTestActivity.class);
			break;
		case 49:
			intent = new Intent(this, FragmentLayoutActivity.class);
			break;
		case 50:
			intent = new Intent(this, ContentProviderActivityTest.class);
			break;
		case 51:
			intent = new Intent(this, SQLiteDatabaseTest.class);
			break;
		case 52:
			intent = new Intent(this, SharedPreferenceTest.class);
			break;
		case 53:
			intent = new Intent(this, ImageScaleGestrue.class);
			break;
		case 54:
			intent = new Intent(this, GestureFillPer.class);
			break;
		case 55:
			intent = new Intent(this, DynamicBroadcast.class);
			break;
		case 56:
			intent = new Intent(this, MyServiceTestActivity.class);
			break;
		case 57:
			intent = new Intent(this, TestCircleView.class);
			break;
		case 58:
			intent = new Intent(this, TestClipActivity.class);
			break;
		case 59:
			intent = new Intent(this, TestAnimationActivity.class);
			break;
		case 60:
			intent = new Intent(this, TestRawAssetActivity.class);
			break;
		case 61:
			intent = new Intent(this, XmlActivity.class);
			break;
		case 62:
			intent = new Intent(this, BitmapTestActivity.class);
			break;
		case 63:
			intent = new Intent(this, MyDrawViewActivity.class);
			break;
		case 64:
			intent = new Intent(this, TestPathEffectActivity.class);
			break;
		case 65:
			intent = new Intent(this, DrawTextOnPath.class);
			break;
		case 66:
			intent = new Intent(this, DrawBitmapViewActivity.class);
			break;
		case 67:
			intent = new Intent(this, PinPangBallActivity.class);
			break;
		case 68:
			intent = new Intent(this, MeshBitmapActivity.class);
			break;
		case 69:
			intent = new Intent(this, BombAnimationActivity.class);
			break;
		case 70:
			intent = new Intent(this, SurfaceViewActivity.class);
			break;
		case 71:
			intent = new Intent(this, ShowWaveActivity.class);
			break;
		case 72:
			intent = new Intent(this, ClientSocketActivity.class);
			break;
		case 73:
			intent = new Intent(this, TestNetworkURLActivity.class);
			break;
		case 74:
			intent = new Intent(this, TestHttpURLActivity.class);
			break;
		case 75:
			intent = new Intent(this, TestBitmapDownloadActivity.class);
			break;
		case 76:
			intent = new Intent(this, ViewPagerActivity.class);
			break;
		case 77:
			intent = new Intent(this, TestFragementViewPager.class);
			break;
		case 78:
			intent = new Intent(this, ViewPagerTableHost.class);
			break;
		case 79:
			intent = new Intent(this, TestLocationActivity.class);
			break;
		case 80:
			intent = new Intent(this, TestMapActivity.class);
			break;
		case 81:
			intent = new Intent(this, TestPush.class);
			break;
		case 82:
			intent = new Intent(this, TestSwipeRefreshLayout.class);
			break;
		case 83:
			intent = new Intent(this, TestRefreshListView.class);
			break;
		case 84:
			intent = new Intent(this, TestDownRefreshUpLoad.class);
			break;
		case 85:
			intent = new Intent(this, CalculateWeightActivity.class);
			break;
			
			
			
			
			
		}
		if (intent != null) {
			// ��ת����������
			startActivity(intent);
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("MainActivity------------>>onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("MainActivity------------>>onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("MainActivity------------>>onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("MainActivity------------>>onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("MainActivity------------>>onDestroy");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("MainActivity------------>>onRestart");
	}
	

}
