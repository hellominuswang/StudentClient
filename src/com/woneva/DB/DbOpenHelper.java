package com.woneva.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	private static String name = "studentclient.db";// ��ʾ���ݿ������
	private static int version = 5;// ��ʾ���ݿ�İ汾����

	public DbOpenHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	// �����ݿⴴ����ʱ���ǵ�һ�α�ִ��,��ɶ����ݿ�ı�Ĵ���
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
		String sql="insert into classinfo(classname,teachername,teachertel,location)values('����','������','15243455011','����')";
		db.execSQL(sql);
		
	}
}
