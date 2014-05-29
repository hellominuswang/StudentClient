package com.woneva.basecell0416;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.woneva.DB.DBDao;
//import com.woneva.DB.DbOpenHelper;

public class AddClass extends Activity {
	Button allclass;
	Button addclass;
	TextView one;
	TextView two;
	TextView three;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addclassactivity);

		one = (TextView) findViewById(R.id.kcmctext);
		two = (TextView) findViewById(R.id.rkjstext);
		three = (TextView) findViewById(R.id.teacherphonenumtext);
		allclass = (Button) findViewById(R.id.allclassinfo);
		addclass = (Button) findViewById(R.id.addclassinfo);
		allclass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent();
				myintent.setClass(AddClass.this, AllClass.class);
				startActivity(myintent);
			}
		});

		addclass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String first = one.getText().toString();
				String second = two.getText().toString();
				String third = three.getText().toString();
				if (isNullOrBlank(first) || isNullOrBlank(second)
						|| isNullOrBlank(third)) {
					Toast.makeText(getApplicationContext(), "请正确的填写上述信息！",
							Toast.LENGTH_SHORT).show();
				} else {
					insertdata(first, second, third);
				}
			}
		});
	}

	public static boolean isNullOrBlank(String param) {
		if (param == null || param.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void insertdata(String a, String b, String c) {
//		DbOpenHelper helper = new DbOpenHelper(this);
//		helper.getReadableDatabase();
		DBDao dbdao = new DBDao(this);
		String sqlone = "insert into classinfo (classname,teachername,teachertel)values(?,?,?)";
		Object[] params = { a, b, c };
		dbdao.addData(sqlone, params);

		one.setText("");
		two.setText("");
		three.setText("");

		Toast.makeText(getApplicationContext(), "添加成功!继续添加或返回！",
				Toast.LENGTH_SHORT).show();
	}

}
