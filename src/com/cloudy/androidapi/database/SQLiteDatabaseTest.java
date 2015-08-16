package com.cloudy.androidapi.database;

import com.cloudy.androidapi.contentprovider.StudentProvider;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

public class SQLiteDatabaseTest extends FragmentActivity{
	
	DbOpenHelper dbOpenHelper;
	SQLiteDatabase database;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		//��һ�ִ������ݿ�ķ���
		DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
		database = dbOpenHelper.getWritableDatabase();
		/*registerContentObserver();
		 * uri:
		 * notifyForDescendents:���Ϊtrue����ָ����URI�����κ�URIֵ����Ҳ������ƥ��
		 * observer:�۲��߽����ڷ����仯ʱ�ص��Ķ���
		 */
		getContentResolver().registerContentObserver(
				StudentProvider.CURRENT_STUDENT_URI, true, new ContentObserver(new Handler()) {
					@Override
					public void onChange(boolean selfChange) {
						System.out.println("current_student table changes---------->>>");
						super.onChange(selfChange);
					}
		});
		
		//�ڶ��ִ������ݿ�ķ���
		//SQLiteDatabase database2 = this.openOrCreateDatabase("student.db2", MODE_PRIVATE, null);
		//database2.execSQL(DbOpenHelper.CREATE_STAGE);
		//database2.execSQL(DbOpenHelper.CREATE_CURRENT_STUDENT);
		//database2.execSQL(DbOpenHelper.CREATE_PAST_STUDENT);
		
		insert1();
		//insert();
		//delete();
		//update();
		//query();
		
	}
	
	//�ڱ��в�������
	public long insert(){
		//����һ��ContentResolver���Դ����ֵ
		ContentValues values = new ContentValues();
		values.put("name", "Zhangsan");
		values.put("stage_id", 14);
		values.put("school", "cqhsdx");
		values.put("major", "chemistry");
		values.put("phone", "13637271086");
		long id = database.insert("current_student", "name", values);
		return id;
	}
	
	public void insert1(){
		ContentValues values = new ContentValues();
		values.put("name", "Zhangsan");
		values.put("stage_id", 14);
		values.put("school", "cqhsdx");
		values.put("major", "chemistry");
		values.put("phone", "13637271086");
		Uri uri = getContentResolver().insert(StudentProvider.CURRENT_STUDENT_URI, values);
	}
	
	//
	public long delete(){
		long count = database.delete("current_student", "name like '%san%'", null);
		return count;
	}
	
	public long update(){
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", "Lisi");
		contentValues.put("school", "jialidun");
		long count = database.update("current_student", contentValues, "name = ?", new String[]{"Zhangsan"});
		return count;
	}
	
	public void query(){
		Cursor cursor = database.query("current_student", new String[]{"_id","name","school"}, "name = ?",new String[]{"Lisi"}, "school", null, "_id DESC");
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				System.out.println("_id = " + cursor.getInt(cursor.getColumnIndex("_id")));
				System.out.println("name = " + cursor.getInt(cursor.getColumnIndex("name")));
				System.out.println("school = " + cursor.getInt(cursor.getColumnIndex("school")));
				cursor.moveToNext();
			}
			cursor.close();
		}
	}
	
	

}
