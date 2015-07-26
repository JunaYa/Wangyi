package com.aya.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CollectionHelper extends SQLiteOpenHelper {

	
	private static final int VERSION=3;
	private static final String DBNAME="Collection.db";  //ÁÐÃû 
	public CollectionHelper(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	private static final String TAB_COLLECT="collectnews";
	private static final String TAB_COLLECT_URL="_url";
	private static final String TAB_COLLECT_DOCID="_docID";
	private static final String TAB_COLLECT_TITLE="_title";
	private static final String TAB_COLLECT_SOURCE="_source";
	private static final String TAB_COLLECT_REPLAYCOUNT="_replycount";
	
	private static final String TAB_USERS="users";
	private static final String TAB_USERS_ID="_id";
	private static final String TAB_USERS_name="_name";
	private static final String TAB_USERS_PWD="_pwd";
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append(" create table if not exsits");
		sb.append(" " + TAB_COLLECT + " " + "( ");
		sb.append(" " + TAB_COLLECT_DOCID + " " + "String primary key ,");
		sb.append(" " + TAB_COLLECT_TITLE + " " + "varchar(100),");
		sb.append(" " + TAB_COLLECT_SOURCE + " " + "varchar(100),");
		sb.append(" " + TAB_COLLECT_REPLAYCOUNT + " " + "varchar(50),");
		sb.append(" " + TAB_COLLECT_URL + " " + "varchar(100)");
		sb.append(")");
		db.execSQL(sb.toString());
		
		StringBuffer sb2=new StringBuffer();
		sb2.append("create table if not exists "+TAB_USERS);
		sb2.append("  ( ");
		sb2.append(TAB_USERS_ID + "  integer primary key autoincrement,  ");//autoincrement 
		sb2.append(TAB_USERS_name + " varchar(100) ,  ");
		sb2.append(TAB_USERS_PWD + " varchar(100) ");
		sb2.append(")");
		db.execSQL(sb2.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		String sql="drop table if exists " + TAB_COLLECT;
		String sql2="drop table if exists  " + TAB_USERS;
		db.execSQL(sql);
		db.execSQL(sql2);
		onCreate(db);
	}

}
