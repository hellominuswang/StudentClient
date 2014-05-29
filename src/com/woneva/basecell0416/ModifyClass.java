package com.woneva.basecell0416;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.woneva.DB.DBDao;
//import com.woneva.DB.DbOpenHelper;

public class ModifyClass extends Activity {
	EditText et_one;
	EditText et_two;
	EditText et_three;
	Button btn_one;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifiyclass);

		Intent intent = this.getIntent();
		final String result0 = intent.getStringExtra("编号");
		String result1 = intent.getStringExtra("课程");
		String result2 = intent.getStringExtra("教师");
		String result3 = intent.getStringExtra("电话");

		et_one = (EditText) findViewById(R.id.mkcmctext);
		et_two = (EditText) findViewById(R.id.mrkjstext);
		et_three = (EditText) findViewById(R.id.mteacherphonenumtext);
		btn_one = (Button) findViewById(R.id.maddclassinfo);

		et_one.setText(result1);
		et_two.setText(result2);
		et_three.setText(result3);

		btn_one.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String a, b, c, d;
				a = et_one.getText().toString();
				b = et_two.getText().toString();
				c = et_three.getText().toString();
				d = result0;
				if (isNullOrBlank(a) || isNullOrBlank(b) || isNullOrBlank(c)) {
					Toast.makeText(getApplicationContext(), "请正确的填写上述信息！",
							Toast.LENGTH_SHORT).show();
				} else {
					updatedata(a, b, c, d);
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

	public void updatedata(String a, String b, String c, String d) {
//		DbOpenHelper helper = new DbOpenHelper(this);
//		helper.getReadableDatabase();
		DBDao dbdao = new DBDao(this);
		String sqlone = "update classinfo set classname=?,teachername=?,teachertel=? where id=?";
		Object[] params = { a, b, c, d };
		dbdao.addData(sqlone, params);
		Toast.makeText(getApplicationContext(), "更新成功!", Toast.LENGTH_SHORT)
				.show();
		Intent myIntent = new Intent();
		myIntent.setClass(ModifyClass.this, AllClass.class);
		startActivity(myIntent);
	}

}
