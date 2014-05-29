package com.woneva.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	private static String name = "studentclient.db";// 表示数据库的名称
	private static int version = 5;// 表示数据库的版本号码

	public DbOpenHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	// 当数据库创建的时候，是第一次被执行,完成对数据库的表的创建
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sqla = "create table wifiinfo(id integer primary key autoincrement,"
				+ "bssid varchar(64),ssid varchar(64),level integer)";
		db.execSQL(sqla);
		String sqlb = "create table userinfo(id integer primary key autoincrement,"
				+ "name varchar(64),studycode varchar(64),phone varchar(64),password varchar(64))";
		db.execSQL(sqlb);
		String sqlc = "create table classinfo(id integer primary key autoincrement,"
				+ "classname varchar(64),teachername varchar(64),teachertel varchar(64),location varchar(64))";
		db.execSQL(sqlc);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// String sql = "alter table person add sex varchar(8)";
		// db.execSQL(sql);
		String sql="insert into classinfo(classname,teachername,teachertel,location)values('更新','王语文','15243455011','三教')";
		db.execSQL(sql);
		
	}
}
