package com.aya.util;


import java.util.ArrayList;
import java.util.List;

import com.aya.entity.News;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony.Sms.Conversations;

public class CollectionDao {

	private CollectionHelper helper;
	public CollectionDao(Context context){
		helper = new CollectionHelper(context); 
	}
	
	private SQLiteDatabase db;
	//存数据
	public void insertNews(News news){
		    db=helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("_docId", news.getDocid());
			values.put("_title",news.getTitle());
			values.put("_source", news.getSource());
			values.put("_replycount",""+ news.getReplyCount());
			values.put("_url",news.getDetailUrl());
			db.insert("collectnews", null,values);
			db.close();
		}
		
	//查数据
	public List<News> findNews(){
		db=helper.getReadableDatabase();
		List<News> list=new ArrayList<News>();
		News news=null;
		Cursor c=db.rawQuery("select * from collectnews ", null);
		while(c.moveToNext()){
			news=new News();
			news.setDocid(c.getString(c.getColumnIndex("_docId")));
			news.setTitle(c.getString(c.getColumnIndex("_title")));
			news.setSource(c.getString(c.getColumnIndex("_source")));
			news.setReplyCount(c.getInt(c.getColumnIndex("_replycount")));
			news.setUrl(c.getString(c.getColumnIndex("_url")));
			list.add(news);
		}
		c.close();
		db.close();
		return list;
		
	}
	//删除数据
	public int delNews(String docId){
		db=helper.getWritableDatabase();
		db.update("collectnews", null, "_docId", new String[]{docId});
		db.close();
		return 1;
	}
	
	public void insertUser( String name,String pwd){
		db=helper.getWritableDatabase();
//		String sql="insert into users values(null,?,?)";
		db.execSQL("insert into users values(null,?,?)",new String[]{name,pwd});
		db.close();
	}
	public String findUser(String name,String pwd){
		String ret=null;
		db = helper.getReadableDatabase();
		Cursor c=db.rawQuery("select * from users where _name=? and _pwd=?",new String[]{name,pwd});
		while(c.moveToNext()){
			StringBuffer sb= new StringBuffer();
			sb.append(c.getString(c.getColumnIndex("_name")));
			sb.append(c.getString(c.getColumnIndex("_pwd")));
			ret=sb.toString();
		}
		return ret;
		}
}
