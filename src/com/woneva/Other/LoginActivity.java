package com.woneva.Other;

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
import com.woneva.basecell0416.R;
import com.woneva.wifiInfo.WifiDealer;
import com.woneva.woneva.WonEvaApp;

public class LoginActivity extends Activity {

	EditText etnum;
	EditText etpwd;
	private WonEvaApp wonevaapp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dianminglogin);

		Button btn = (Button) findViewById(R.id.btn_login);
		etnum = (EditText) findViewById(R.id.et_qqNum);
		etpwd = (EditText) findViewById(R.id.et_qqPwd);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String etnumtext = etnum.getText().toString().trim();
				String etpwdtext = etpwd.getText().toString().trim();
				if (etnumtext.length() == 0 || etnumtext == null) {
					Toast.makeText(getApplicationContext(), "用户名不能为空！",
							Toast.LENGTH_SHORT).show();
				} else if (etpwdtext.length() == 0 || etpwdtext == null) {
					Toast.makeText(getApplicationContext(), "密码不能为空！",
							Toast.LENGTH_SHORT).show();
				} else {
					queryuser(etnumtext, etpwdtext);
				}
			}
		});

		// 注册 按钮
		Button btnjieyong = (Button) findViewById(R.id.btn_login_regist);
		btnjieyong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent goIntent = new Intent();
				goIntent.setClass(LoginActivity.this, RegisterActivity.class);
				startActivity(goIntent);
			}
		});
	}

	public void queryuser(String studycode, String password) {
//		DbOpenHelper helper = new DbOpenHelper(this);
//		helper.getReadableDatabase();
		DBDao dbdao = new DBDao(this);
		String[] params = { studycode, password };
		String querysql = "select * from userinfo where studycode=?and password=?";

		if (dbdao.viewData(querysql, params).isEmpty()) {
			Toast.makeText(getApplicationContext(), "用户名或密码错误,请重新输入或注册！",
					Toast.LENGTH_SHORT).show();
		} else {
			wonevaapp=(WonEvaApp) getApplication();
			wonevaapp.setwondata(studycode);
			Intent goIntent = new Intent();
			goIntent.setClass(LoginActivity.this, WifiDealer.class);
			startActivity(goIntent);
		}
	}
}
