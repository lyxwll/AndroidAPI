package com.cloudy.androidapi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "student.db";
	private static final int DATABASE_VERSION = 4;
	
	private DbOpenHelper dbOpenHelper;
	private SQLiteDatabase database;

	//
	public static final String CREATE_CURRENT_STUDENT = 
			"create table current_student ("
			+ "_id integer primary key autoincrement,"
			+ "name vchar(64) not null,"
			+ "stage_id integer not null,"
			+ "school vchar(64),"
			+ "major vchar(64),"
			+ "phone vchar(20),"
			+ "naming_count integer" + ");";

	//
	public static final String CREATE_PAST_STUDENT = 
			"create table past_student ("
			+ "_id integer primary key autoincrement,"
			+ "name vchar(64) not null,"
			+ "stage_id integer not null,"
			+ "school vchar(64),"
			+ "major vchar(64),"
			+ "phone vchar(20),"
			+ "naming_count integer" + ");";
	
	//
	public static final String CREATE_STAGE = 
			"create table stage (" +
			"_id integer primary key autoincrement," +
			"stage_id integer not null," +
			"stage_name vchar(64) not null," +
			"is_current_stage integer default 0" +
			");";
	
	//������ �ı�
	public static final String CREATE_CURRENT_STUDENT_BAK = 
			"create table if not exists current_student_bak ("
					+ "_id integer primary key autoincrement,"
					+ "name vchar(64) not null,"
					+ "stage_id integer not null,"
					+ "school vchar(64),"
					+ "major vchar(64),"
					+ "phone vchar(20),"
					+ "naming_count integer" + ");";
	
	//
	public static final String CREATE_PAST_STUDENT_BAK = 
			"create table if not exists past_student_bak ("
					+ "_id integer primary key autoincrement,"
					+ "name vchar(64) not null,"
					+ "stage_id integer not null,"
					+ "school vchar(64),"
					+ "major vchar(64),"
					+ "phone vchar(20),"
					+ "naming_count integer" + ");";
	
	//
	public static final String CREATE_STAGE_BAK = 
			"create table if not exists stage_bak (" +
					"_id integer primary key autoincrement," +
					"stage_id integer not null," +
					"stage_name vchar(64) not null," +
					"is_current_stage integer default 0" +
					");";

	public DbOpenHelper(Context context) {
		//���ø���Ĺ�����ȥ�������ݿ�
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("DbopenHleper "+ "onCreate");
		//�����ݿ��ﴴ����
		db.execSQL(CREATE_STAGE);
		db.execSQL(CREATE_CURRENT_STUDENT);
		db.execSQL(CREATE_PAST_STUDENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("DbOpenHleper onUpgrade");
		
		db.beginTransaction();//��ʼ����
		//�������ݱ�
		db.execSQL(CREATE_STAGE_BAK);
		db.execSQL(CREATE_CURRENT_STUDENT_BAK);
		db.execSQL(CREATE_PAST_STUDENT_BAK);
		//��������
		Cursor cursor = db.query("current_student", null, null, null, null, null, null);
		if(cursor != null && cursor.getCount() >0){
			cursor.moveToFirst();
			do {
				ContentValues contentValues = new ContentValues();
				contentValues.put("name", cursor.getInt(cursor.getColumnIndex("name")));
				contentValues.put("stage_id", cursor.getInt(cursor.getColumnIndex("stage_id")));
				contentValues.put("school", cursor.getInt(cursor.getColumnIndex("school")));
				contentValues.put("major", cursor.getInt(cursor.getColumnIndex("major")));
				contentValues.put("phone", cursor.getInt(cursor.getColumnIndex("phone")));
				contentValues.put("naming_count", cursor.getInt(cursor.getColumnIndex("naming_count")));
				db.insert("current_student_bak", null, contentValues);
			} while (cursor.moveToNext());
			cursor.close();
		}
		
		//ɾ���ɵı�
		db.execSQL("drop table if exists current_student");
		db.execSQL("drop table if exists past_student");
		db.execSQL("drop table if exists stage");
		
		db.setTransactionSuccessful();
		db.endTransaction();
		
		//����OnCreate�������´������ݿ�
		onCreate(db);
	}

}
