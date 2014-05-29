package com.woneva.Other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.woneva.DB.DBDao;
//import com.woneva.DB.DbOpenHelper;
import com.woneva.basecell0416.R;

public class RegisterActivity extends Activity {

	// �˴�������findViewId,����ᱨ��ָ���쳣
	Button regbtn;
	EditText et_mingzi;
	EditText et_xuehao;
	EditText et_phonenum;
	EditText et_mima;
	EditText et_querenmima;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myregister);

		regbtn = (Button) findViewById(R.id.zhucexinxi);
		et_mingzi = (EditText) findViewById(R.id.mingzitext);
		et_xuehao = (EditText) findViewById(R.id.xuehaotext);
		et_phonenum = (EditText) findViewById(R.id.phonenumtext);
		et_mima = (EditText) findViewById(R.id.mimatext);
		et_querenmima = (EditText) findViewById(R.id.querenmimatext);

		regbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ʱ����һ���߼����⣬��ȡֵ��ʱ��EditText���ֵΪ�գ�
				// ��Ϊ�����в�û�ж��������ݽ��м�����
				// ���Ե��ڼ����¼���������ȡֵ��
				String mingzi = et_mingzi.getText().toString().trim();
				String xuehao = et_xuehao.getText().toString().trim();
				String phonenum = et_phonenum.getText().toString().trim();
				String mima = et_mima.getText().toString().trim();
				String querenmima = et_querenmima.getText().toString().trim();
				registerinfo(mingzi, xuehao, phonenum, mima, querenmima);
			}
		});
	}

	public void adduser(String mingzi, String xuehao, String phonenum,
			String mima) {
//		DbOpenHelper helper = new DbOpenHelper(this);
//		helper.getReadableDatabase();
		DBDao dbdao = new DBDao(this);
		Object[] params = { mingzi, xuehao, phonenum, mima };
		String addsql = "insert into userinfo(name,studycode,phone,password) values(?,?,?,?)";
		dbdao.addData(addsql, params);
	}

	public static boolean isNullOrBlank(String param) {
		if (param == null || param.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void registerinfo(String mingzi, String xuehao, String phonenum,
			String mima, String querenmima) {
		if (isNullOrBlank(mingzi) || isNullOrBlank(xuehao)
				|| isNullOrBlank(mima) || isNullOrBlank(phonenum)
				|| isNullOrBlank(querenmima)) {
			Toast.makeText(getApplicationContext(), "����ȷ����д������Ϣ��",
					Toast.LENGTH_SHORT).show();
		} else if (!mima.equals(querenmima)) {
			Toast.makeText(getApplicationContext(), "�������벻һ������������",
					Toast.LENGTH_SHORT).show();
		} else {
			adduser(mingzi, xuehao, phonenum, mima);
			Toast.makeText(getApplicationContext(), "ע��ɹ���", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
