package com.woneva.woneva;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.woneva.basecell0416.R;

public class ClassStudentList extends Activity {
	
	ListView lvstudentlist;
	Button btimport;
	EditText etexcelname;
	ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classstudentlist);
		lvstudentlist = (ListView) findViewById(R.id.lv_studentname);
		btimport=(Button)findViewById(R.id.bt_import);
		etexcelname=(EditText)findViewById(R.id.et_exceltext);
		
		btimport.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				readExcel(etexcelname.getText().toString().trim());
				bindData();
			}
		});

	}

	public void readExcel(String textname) {
		try {
			InputStream is = new FileInputStream("mnt/sdcard/"+textname+".xls");
			// Workbook book = Workbook.getWorkbook(new File("mnt/sdcard/test.xls"));
			Workbook book = Workbook.getWorkbook(is);
			//int num = book.getNumberOfSheets();
			Sheet sheet = book.getSheet(0);
			//String sheetname=sheet.getName();
			int Rows = sheet.getRows();
			int Cols = sheet.getColumns();
			
			for (int i = 0; i < Rows; ++i) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < Cols; ++j) {
					map.put("Item" + j, sheet.getCell(j, i).getContents());
				}
				listItem.add(map);
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void bindData() {
		SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem,
				R.layout.studentlistview, new String[] { "Item0", "Item1",
						"Item2", "Item3", "Item4" }, new int[] { R.id.textzero,
						R.id.textone, R.id.texttwo, R.id.textthree,
						R.id.textfour });
		
		lvstudentlist.setAdapter(mSimpleAdapter);
		lvstudentlist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				 setTitle("你点击了第"+arg2+"行");//设置标题栏显示点击的行
			}
		});
	}
}
