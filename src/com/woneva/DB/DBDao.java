package com.woneva.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBDao implements DBInterface {

	private DbOpenHelper helper = null;
    public DBDao(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DbOpenHelper(context);
	}
	@Override
	public boolean addData(String addsql,Object[] params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//实现对数据库的添加删除和修改查询的功能
		SQLiteDatabase database = null;
		try {
			//String sql = "insert into basecell(name,address,sex) values(?,?,?)";
			database = helper.getWritableDatabase();//实现对数据库的写的操作
			database.execSQL(addsql, params);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean delData(String delsql,Object[] params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		SQLiteDatabase database = null;
		try {
			//String sql = "delete from person where id = ? ";
			database = helper.getWritableDatabase();
			database.execSQL(delsql, params);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean updateData(String updatesql,Object[] params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		SQLiteDatabase database = null;
		try {
			//String sql = "update person set name = ? ,address = ?, sex = ? where id = ? ";
			database = helper.getWritableDatabase();
			database.execSQL(updatesql, params);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return flag;
	}

	@Override
	public Map<String, String> viewData(String selsql,String[] selectionArgs) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		SQLiteDatabase database = null;
		try {
			//String sql = "select * from person where id = ? ";
			database = helper.getReadableDatabase();
			Cursor cursor = database.rawQuery(selsql, selectionArgs);
			//获得数据库的列的个数
			int colums = cursor.getColumnCount();
			while(cursor.moveToNext()){
				for(int i=0;i<colums;i++){
					String cols_name = cursor.getColumnName(i);
					String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
					if(cols_value==null){
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
			}
			cursor.close();/////////////////////
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(database!=null){
				database.close();
			}
			
		}
		return map;
	}

	@Override 
	public List<Map<String, String>> listDataMaps(String sellsql,String[] selectionArgs) {
		// TODO Auto-generated method stub
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		//String sql = "select * from person ";
		SQLiteDatabase database = null;
		try {
			database = helper.getReadableDatabase();
			Cursor cursor = database.rawQuery(sellsql, selectionArgs);
			int colums = cursor.getColumnCount();
			while(cursor.moveToNext()){
				Map<String,String> map = new HashMap<String, String>();
				for(int i=0;i<colums;i++){
					String cols_name = cursor.getColumnName(i);
					String cols_value = cursor.getString(cursor.getColumnIndex(cols_name));
					if(cols_value==null){
						cols_value="";
					}
					map.put(cols_name, cols_value);
				}
				list.add(map);
			}
			cursor.close();////////////////////////////////////
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(database!=null){
				database.close();
			}
		}
		return list;
	}

}
