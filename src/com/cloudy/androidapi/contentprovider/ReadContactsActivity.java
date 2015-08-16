package com.cloudy.androidapi.contentprovider;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


public class ReadContactsActivity extends FragmentActivity{
	//the selected cols for contact user
	String[] selectcol = new String[]{ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER,
					ContactsContract.Contacts._ID}; 
	public static final int COL_NAME = 0;
	public static final int COL_HAS_PHONE = 1;
	public static final int COL_ID = 2;
	
	//the selected cols for phones of a user
	String[] selPhoneCols = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,
			ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
			ContactsContract.CommonDataKinds.Phone.TYPE};
	public static final int COL_PHONE_NUMBER = 0;
	public static final int COL_PHONE_NAME = 1;
	public static final int COL_PHONE_TYPE = 2;
	
	/**
	 * 获取联系人列表 的方法
	 */
	public void getContactList() {
		//
		String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
        + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
        + Contacts.DISPLAY_NAME + " != '' ))";
		//用来保存联系人的号码
		List<ContactItemData> list = new ArrayList<ContactItemData>();
		//游标：
		/**uri:CONTENT_URI
		 * selectcol:查询某些列,是需要读取的字段
		 * select:是数据检索的条件
		 * selectionArgs：是数据检索条件的参数
		 * sortOrder：是排序的字段
		 */
		Cursor cursor = this.getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, selectcol, select, null, 
				ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC ");
		if (cursor ==null) {
			Toast.makeText(this, "cursor is null!", Toast.LENGTH_LONG).show();
			return;
		}
		if (cursor.getCount() == 0) {
			Toast.makeText(this, "cursor count is zero!", Toast.LENGTH_LONG).show();
			return;
		}
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {//是否在最后一行之后
			int contactId;
			contactId = cursor.getInt(cursor.getColumnIndex(
								ContactsContract.Contacts._ID));
			if (cursor.getInt(COL_HAS_PHONE)>0) {
				// the contact has numbers
				// 获得联系人的电话号码列表
				String displayName;
				displayName = cursor.getString(COL_NAME);
				/* Cursor：游标
				 * Uri：CONTENT_URI
				 * selPhoneCols:需要读取的字段
				 * selection:CONTACT_ID是数据检索的条件
				 * 检索参数和排序为空
				 */
                Cursor phoneCursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        selPhoneCols,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + "=" + contactId, null, null);
                if(phoneCursor.moveToFirst()) {
                    do 
                    {
                        //遍历所有的联系人下面所有的电话号码
                        String phoneNumber = phoneCursor.getString(COL_PHONE_NUMBER);
                        ContactItemData data = new ContactItemData();
                        String phoneFiled = new String();
                        data.name = displayName;
                        data.number = phoneFiled + ":" + phoneNumber;
                        data.check = false;
                        System.out.println("displayName="+displayName+
								",number="+phoneFiled+":"+phoneNumber);
                        list.add(data);
                    }while(phoneCursor.moveToNext());
                    phoneCursor.close();//手动关闭游标，避免内存溢出
                }
			}
			cursor.moveToNext();
		}
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		getContactList();
	}
	

}













