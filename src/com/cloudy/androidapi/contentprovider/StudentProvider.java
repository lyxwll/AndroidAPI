package com.cloudy.androidapi.contentprovider;

import com.cloudy.androidapi.database.DbOpenHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StudentProvider extends ContentProvider{
	
	private static final String AUTHORITY = "com.cloudy.androidapi.contentprovider.StudentProvider";
	
	public static final Uri CURRENT_STUDENT_URI = Uri.parse("content:// " + AUTHORITY + "/current_student");
	public static final Uri PAST_STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/past_student");
	public static final Uri STAGE_URI = Uri.parse("content://" + AUTHORITY + "/stage");
	
	private SQLiteDatabase database;

	static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static{
		uriMatcher.addURI(AUTHORITY, "current_student", 1);
		uriMatcher.addURI(AUTHORITY, "current_student/#", 2);
		
		uriMatcher.addURI(AUTHORITY, "past_student", 3);
		uriMatcher.addURI(AUTHORITY, "past_student/#", 4);
		
		uriMatcher.addURI(AUTHORITY, "stage", 5);
		uriMatcher.addURI(AUTHORITY, "stage/#", 6);
	}
	
	@Override
	public boolean onCreate() {
		DbOpenHelper dbOpenHelper = new DbOpenHelper(getContext());
		database = dbOpenHelper.getWritableDatabase();
		return database == null ? false:true;
	}
	
	//返回此URI的类型
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case 1:
		case 3:
		case 5:
			return "vnd.android.cursor.dir/com.cloudy.androidapi.contentprovider";
		case 2:
		case 4:
		case 6:
			return "vnd.android.cursor.item/com.cloudy.androidapi.contentprovider";
		}
		return null;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri uri2 = null;
		long id = -1;
		switch (uriMatcher.match(uri)) {
		case 1:
		case 2:
			id = database.insert("current_student", null, values);
			uri2 = Uri.withAppendedPath(CURRENT_STUDENT_URI, Long.toString(id));
			break;
		case 3:
		case 4:
			id = database.insert("past_student", null, values);
			uri2 = Uri.withAppendedPath(PAST_STUDENT_URI, Long.toString(id));
			break;
		case 5:
		case 6:
			id = database.insert("stage", null, values);
			uri2 = Uri.withAppendedPath(STAGE_URI, Long.toString(id));
			break;
		}
		getContext().getContentResolver().notifyChange(uri2, null);
		return null;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
		case 1:
		case 2:
			count = database.delete("current_student", selection, selectionArgs);
			break;
		case 3:
		case 4:
			count = database.delete("past_student", selection, selectionArgs);
			break;
		case 5:
		case 6:
			count = database.delete("stage", selection, selectionArgs);
			break;
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case 1:
		case 2:
			cursor = database.query("current_student", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case 3:
		case 4:
			cursor = database.query("past_student", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case 5:
		case 6:
			cursor = database.query("stage",projection, selection, selectionArgs, null, null, sortOrder);
			break;
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count  = -1;
		switch (uriMatcher.match(uri)) {
		case 1:
		case 2:
			count = database.update("current_student", values, selection, selectionArgs);
			break;
		case 3:
		case 4:
			count = database.update("past_student", values, selection, selectionArgs);
			break;
		case 5:
		case 6:
			count = database.update("stage", values, selection, selectionArgs);
			break;
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
