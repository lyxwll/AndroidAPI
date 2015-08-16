package com.cloudy.androidapi.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.adapter.DialogContentAdapter;
import com.cloudy.androidapi.bean.FruitBean;
import com.cloudy.androidapi.customdialog.CustomAlertDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.LinearLayout.LayoutParams;

public class DialogActivity extends Activity{
	
	private String TAG = "DialogTAG";
	
	int[] images = {
			R.drawable.fruit_apple,
			R.drawable.fruit_little_apple,
			R.drawable.fruit_big_apple,
			R.drawable.fruit_banana,
			R.drawable.fruit_orange,
			R.drawable.fruit_peach,
			R.drawable.fruit_pear};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(linearLayout.VERTICAL);
		Button button = new Button(this);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		button.setLayoutParams(layoutParams);
		button.setText(R.string.dialog_show_btn);
		//new Tag();����Ϊ������һ��Tag��,��ΪButton�ı�ǩ
		button.setTag(new Tag(1));
		
		//��������һ����ť,��ʾ�Զ���Ի���
		Button button2 = new Button(this);
		button2.setLayoutParams(layoutParams);
		button2.setText(R.string.dialog_show_btn2);
		button2.setTag(new Tag(2));
		
		//��ʾ��ѡ�б�ĶԻ���ť
		Button button3 = new Button(this);
		button3.setLayoutParams(layoutParams);
		button3.setText(R.string.dialog_show_btn3);
		button3.setTag(new Tag(3));
		
		//��ʾ��ѡ�б�ť�ĶԻ���
		Button button4 = new Button(this);
		button4.setLayoutParams(layoutParams);
		button4.setText(R.string.dialog_show_btn4);
		button4.setTag(new Tag(4));
		
		//��ʾAdapter�ĶԻ���
		Button button5 = new Button(this);
		button5.setLayoutParams(layoutParams);
		button5.setText(R.string.dialog_show_btn5);
		button5.setTag(new Tag(5));
		
		//��ʾDatePickerDialog�ĶԻ���
		Button button6 = new Button(this);
		button6.setLayoutParams(layoutParams);
		button6.setText(R.string.dialog_show_btn6);
		button6.setTag(new Tag(6));
		
		//��ʾTimerPickerDialog�ĶԻ���
		Button button7 = new Button(this);
		button7.setLayoutParams(layoutParams);
		button7.setText(R.string.dialog_show_btn7);
		button7.setTag(new Tag(7));
		
		//��ʾProgressDialog�ĶԻ���
		Button button8 = new Button(this);
		button8.setLayoutParams(layoutParams);
		button8.setText(R.string.dialog_show_btn8);
		button8.setTag(new Tag(8));
		
		//��ʾActivityAsDialog�ĶԻ���
		Button button9 = new Button(this);
		button9.setLayoutParams(layoutParams);
		button9.setText(R.string.dialog_show_btn9);
		button9.setTag(new Tag(9));
		
		//��ʾCustomDialog�ĶԻ���
		Button button10 = new Button(this);
		button10.setLayoutParams(layoutParams);
		button10.setText(R.string.dialog_show_btn10);
		button10.setTag(new Tag(10));
		
		//��ʾCustomStyleDialog�ĶԻ���
		Button button11 = new Button(this);
		button11.setLayoutParams(layoutParams);
		button11.setText(R.string.dialog_show_btn11);
		button11.setTag(new Tag(11));
		
		Button button12 = new Button(this);
		button12.setLayoutParams(layoutParams);
		button12.setText(R.string.show_popupwindows);
		button12.setTag(new Tag(12));
		
		//��ӵ���ͼ
		linearLayout.addView(button);
		linearLayout.addView(button2);
		linearLayout.addView(button3);
		linearLayout.addView(button4);
		linearLayout.addView(button5);
		linearLayout.addView(button6);
		linearLayout.addView(button7);
		linearLayout.addView(button8);
		linearLayout.addView(button9);
		linearLayout.addView(button10);
		linearLayout.addView(button11);
		linearLayout.addView(button12);
		
		
		setContentView(linearLayout);
		
		/*button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMydialog();
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCustomDialog();
			}
		});*/
		button.setOnClickListener(new MyOnClickListener());
		button2.setOnClickListener(new MyOnClickListener());
		button3.setOnClickListener(new MyOnClickListener());
		button4.setOnClickListener(new MyOnClickListener());
		button5.setOnClickListener(new MyOnClickListener());
		button6.setOnClickListener(new MyOnClickListener());
		button7.setOnClickListener(new MyOnClickListener());
		button8.setOnClickListener(new MyOnClickListener());
		button9.setOnClickListener(new MyOnClickListener());
		button10.setOnClickListener(new MyOnClickListener());
		button11.setOnClickListener(new MyOnClickListener());
		button12.setOnClickListener(new MyOnClickListener());
		
	}
	
	//button�ı�ǩ
	class Tag{
		int tag;
		public Tag(int tag) {
			this.tag = tag;
		}
	}
	
	//ͨ���ڲ��෽��ʵ���¼�����
	class MyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Tag tag = (Tag) v.getTag();
			switch (tag.tag) {
			case 1:
				showMydialog();
				break;
			case 2:
				showCustomDialog();
				break;
			case 3:
				showSingleChoiceDialog(true);
				break;
			case 4:
				showSingleChoiceDialog(false);
				break;
			case 5:
				showAdapterDialog();
				break;
			case 6:
				showDatepickerDialog();
				break;
			case 7:
				showTimePickerDialog();
				break;
			case 8:
				showProgressDialog();
				break;
			case 9:
				Intent intent = new Intent();
				intent.setClass(DialogActivity.this, ActivityAsDialog.class);
				startActivity(intent);
				break;
			case 10:
				showStyleDialog();
				break;
			case 11:
				showCustomStyleDialog();
				break;
			case 12:
				
				break;
				
				
			}
		}
	}
	
	//
	public void popupWindow(){
		
	}
	
	/**
	 * ��ʾ��ʽ�Զ���Ի���ķ���
	 */
	public void showStyleDialog(){
		//����һ��alertDialogʹ���Զ������ʽ
		//�����ص���Ȼ��ϵͳ�Ĳ����ļ�
		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(
				new ContextThemeWrapper(this,R.style.MyTheme));
		
		//4.0����ʹ�����ַ�ʽ
		/*int version = Build.VERSION_CODES.ICE_CREAM_SANDWICH;
		if(version < 14){
			builder = new AlertDialog.Builder(
					new ContextThemeWrapper(this,R.style.MyTheme));
		}else{
			builder = new AlertDialog.Builder(this, R.style.MyTheme);
		}*/
		
		builder.setTitle(R.string.dialog_show_btn11);
		builder.setIcon(R.drawable.fruit_orange);
		builder.setMessage(R.string.dialog_show_btn11);
		builder.setPositiveButton(R.string.dialog_show_btn11, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		builder.create().show();
		
	}
	
	/**
	 * ��ʾ�Զ�����Ի���ķ���
	 */
	public void showCustomStyleDialog(){
		AlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
		builder.setTitle(R.string.dialog_show_btn10);
		builder.setIcon(R.drawable.fruit_orange);
		builder.setMessage(R.string.dialog_show_btn10);
		builder.setPositiveButton(R.string.dialog_show_btn10, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		builder.create().show();
		
	}
	
	/**
	 * ��ʾProgressDialog�Ի���ķ���
	 */
	private ProgressDialog progressDialog;
	//������Ϣ�Ĳ���
	private static final int UPDATE_PROGRESS = 0x125;
	Timer timer;
	public void showProgressDialog(){
		if(progressDialog == null){
			//����һ���������Ի������
			progressDialog = new ProgressDialog(this);
			progressDialog.setTitle(R.string.dialog_show_btn8);
			//����һ����Ϣ
			progressDialog.setMessage(getString(R.string.dialog_message));
			progressDialog.setIcon(R.drawable.fruit_orange);
			//���ý�������MAX
			progressDialog.setMax(100);
			progressDialog.setCancelable(false);//���ò���ȡ��
			//���ý�����Ϊˮƽ��
			progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
			//true,����ʾ����,setprogressʧЧ
			//false��ʾ����
			progressDialog.setIndeterminate(false);//��ʾ������
			
		}
		progressDialog.show();
		//����һ����ʱ��
		timer = new Timer();
		//���ü�ʱ������
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				//����һ������Ϣ
				handler.sendEmptyMessage(UPDATE_PROGRESS);
				
			}
		}, 0, 300);
		
	}
	//������Ϣ�ķ���:�˷����������½�����
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_PROGRESS:
				Log.d(TAG, "currentProgress"+progressDialog.getProgress());
				//����ֵ����ʱ
				if(progressDialog.getProgress() < progressDialog.getMax()){
					int progress = progressDialog.getProgress();//�õ���ǰ����ֵ
					progress += 10;//ÿ�ν���ֵ��10
					progressDialog.setProgress(progress);
					
				}else{//����ֵ��ʱ
					progressDialog.setProgress(0);
					progressDialog.dismiss();
					timer.cancel();
					
				}
				break;
			}
		};
		
	};
	
	/**
	 * ��ʾDatePickerDialog�ķ���
	 */
	public void showDatepickerDialog(){
		//����Calendar,getInstance();��õ�ǰϵͳ������
		final Calendar calendar = Calendar.getInstance();
		//�������ʹ�õ�ǰʱ��,�������ó�ʼ����:2015.3.4
		//calendar.set(2015, 2, 4);
		//����DatePickerDialog
		DatePickerDialog dialog = new DatePickerDialog(this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				//����������ʱ�ص��˷���
				calendar.set(year, monthOfYear, dayOfMonth);
				Log.d(TAG, calendar.toString());
				
			}
		}, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
		dialog.setTitle(R.string.dialog_show_btn6);
		dialog.setIcon(R.drawable.fruit_orange);
		dialog.show();
		
	}
	
	/**
	 * ��ʾTimerpickerDialog�ķ���
	 */
	public void showTimePickerDialog(){
		//����Calendar
		final Calendar calendar = Calendar.getInstance();
		//
		//����TimePickerDialog
		TimePickerDialog dialog = new TimePickerDialog(this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				//��ʱ������ʱ,���ô˷���
				calendar.set(calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(calendar.MINUTE, minute);
				Log.d(TAG, "Hour="+calendar.get(Calendar.HOUR_OF_DAY)+",Minute="+calendar.get(Calendar.MINUTE));
				
			}
		}, calendar.get(calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE), true);
		dialog.setTitle(R.string.dialog_show_btn7);
		dialog.setIcon(R.drawable.fruit_orange);
		dialog.show();
		
	}
	
	
	/**
	 * AdapterContent �Ի���ķ���
	 */
	public void showAdapterDialog(){
		//��ʼ������Դ
		List<FruitBean> list = new ArrayList<FruitBean>();
		String[] fruitTitles = getResources().getStringArray(R.array.fruit_titles);
		for(int i =0;i < images.length && i < fruitTitles.length;i++){
			FruitBean bean = new FruitBean();
			bean.iconId = images[i];
			bean.title = fruitTitles[i];
			//����bean��ӵ�list��
			list.add(bean);
			
		}
		//����dialog����
		AlertDialog.Builder builder = new Builder(this);
		final AlertDialog alertDialog = builder.create();
		alertDialog.setTitle(R.string.dialog_title5);
		alertDialog.setIcon(R.drawable.fruit_orange);
		//��ʼ��DialogContentAdapter
		DialogContentAdapter adapter = new DialogContentAdapter(this, list);
		//
		View view = LayoutInflater.from(this).inflate(R.layout.gridview_layout, null);
		GridView gridView = (GridView) view.findViewById(R.id.grid_view);
		gridView.setAdapter(adapter);
		alertDialog.setView(view);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FruitBean bean = (FruitBean) parent.getAdapter().getItem(position);
				Log.d(TAG, "click fruit is="+bean.title);
				alertDialog.dismiss();
				
			}
			
		});
		//��ʾ�Ի���
		alertDialog.show();
	}
	
	/**
	 * ��ѡ���ѡ�б�dialog showSingleChoiceDialog()����
	 * @param isSingle �Ƿ��ǵ�ѡ���ѡ
	 */
	private int singleChoiceItem;
	private boolean[] multiChoiceItems;
	public void showSingleChoiceDialog(final boolean isSingle){
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.fruit_orange);
		//��ȡ����Դ
		final String[] items = {"����","��ʿ��","����"};
		if(isSingle){//��ѡ
			builder.setTitle(R.string.dialog_title3);
			//����singleChoice��ѡ�б�
			builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//���浱ǰѡ�е�ID
					singleChoiceItem = which;
				}
			});
			
		}else{//��ѡ
			builder.setTitle(R.string.dialog_title4);
			//Ĭ����Щ����ѡ�е�,������һ��boolean������
			if(multiChoiceItems == null){
				multiChoiceItems = new boolean[items.length];
			}
			//���ö�ѡ�б�
			builder.setMultiChoiceItems(items, multiChoiceItems, new OnMultiChoiceClickListener() {
				/**dialog ��ǰ��ѡ�Ի���
				 * which  �����������
				 * isChecked  ��ǰwhich���Ƿ�ѡ��
				 */
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					//���浱ǰwhich���ǹ�ѡ����δ��ѡ
					multiChoiceItems[which] = isChecked;
				}
			});
		}
		
		//����һ��PositiveButton
		builder.setPositiveButton(R.string.dialog_confirm_btn, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(isSingle){
					Log.d(TAG, "singleChoice item="+items[singleChoiceItem]);
				}else{
					String str = "multiChoiceItems=";
					for(int i =0;i<multiChoiceItems.length;i++){
						if(multiChoiceItems[i] == true){
							str += items[i]+",";
						}
					}
				}
			}
		});
		
		//��������ʾ
		builder.create().show();
		
	}
	
	/*
	 * ��ͨ��dialog�Ի�����ʾ����
	 */
	public void showMydialog(){
		//ͨ��dialog������AlertDialog.Builder����һ������
		AlertDialog.Builder builder = new Builder(this);
		//����һ��title
		builder.setTitle(R.string.dialog_title);
		//���ñ����ͼ��
		builder.setIcon(R.drawable.fruit_apple);
		//����һ��message
		builder.setMessage(R.string.dialog_message);
		//����NegativeButton��Ϊ����ӵ���¼�
		builder.setNegativeButton(R.string.dialog_cancle_btn, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		//����NeutralButton,��Ϊ����ӵ���¼�
		builder.setNeutralButton(R.string.dialog_dismiss_btn, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		//����positoveButton,��Ϊ����ӵ���¼�
		builder.setPositiveButton(R.string.dialog_confirm_btn, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		//����AlertDialog�Ի�����ʾ����
		//builder.create().show();
		AlertDialog alertDialog = builder.create();
		
		//ȡ���¼�����
		alertDialog.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				Log.d(TAG, "OnCancle");
			}
		});
		//��ʧ�¼�����
		alertDialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				Log.d(TAG, "OnDismiss");
			}
		});
		//��ʾ�¼�����
		alertDialog.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				Log.d(TAG, "onShow");
			}
		});
		
		//��ʾ�Ի���
		alertDialog.show();
		
	}
	
	/*
	 * �Զ���View�Ի������ʾ����
	 */
	public void showCustomDialog(){
		AlertDialog.Builder builder = new Builder(this);
		//���ñ���
		builder.setTitle(R.string.dialog_title2);
		//����ͼ��
		builder.setIcon(R.drawable.fruit_orange);
		//�����Զ���Ի��������
		LayoutInflater inflater = LayoutInflater.from(this);
		//�����Զ���Ի��򲼾�
		View view = inflater.inflate(R.layout.userlogin_layout, null);
		Button loginButton = (Button) view.findViewById(R.id.login_btn);
		final EditText userName = (EditText) view.findViewById(R.id.username);
		final EditText userPassword = (EditText) view.findViewById(R.id.password);
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String mUserName = userName.getText().toString();
				String mPassword = userPassword.getText().toString();
				Log.d(TAG, "login userName="+mUserName+"userPassword="+mPassword);
				
			}
		});
		//��dialog�������Զ����View
		builder.setView(view);
		//����PositiveButton
		builder.setPositiveButton(R.string.dialog_confirm_btn, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.d(TAG, "onPositiveButton");
			}
		});
		//��������ʾ
		builder.create().show();
		
	}
	
}
