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
		
		//第一种创建数据库的方法
		DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
		database = dbOpenHelper.getWritableDatabase();
		/*registerContentObserver();
		 * uri:
		 * notifyForDescendents:如果为true，比指定的URI或者任何URI值都低也将进行匹配
		 * observer:观察者接收在发生变化时回调的对象
		 */
		getContentResolver().registerContentObserver(
				StudentProvider.CURRENT_STUDENT_URI, true, new ContentObserver(new Handler()) {
					@Override
					public void onChange(boolean selfChange) {
						System.out.println("current_student table changes---------->>>");
						super.onChange(selfChange);
					}
		});
		
		//第二种创建数据库的方法
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
	
	//在表中插入数据
	public long insert(){
		//存贮一组ContentResolver可以处理的值
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
