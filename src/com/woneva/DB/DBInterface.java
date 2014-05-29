package com.woneva.DB;

import java.util.List;
import java.util.Map;

public interface DBInterface {
	public boolean addData(String addsql,Object[] params);
	
	public boolean delData(String delsql,Object[] params);
	
	public boolean updateData(String updatesql,Object[] params);
	
	public Map<String,String> viewData(String selsql,String[] selectionArgs);
	
	public List<Map<String,String>> listDataMaps(String sellsql,String[] selectionArgs);

}
