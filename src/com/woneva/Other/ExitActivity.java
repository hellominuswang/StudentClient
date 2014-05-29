package com.woneva.Other;

import com.woneva.basecell0416.R;
import com.woneva.gridview.MyMainGridView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Process;

public class ExitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exitsystem);
		openwifidialog();
	}

	/**
	 * ������ת 2014��1��1��18:15:03 author����һ��
	 */
	public void run() {
		// TODO Auto-generated method stub
		Intent goIntent = new Intent();
		goIntent.setClass(ExitActivity.this, MyMainGridView.class);
		startActivity(goIntent);
	}

	/**
	 * ��ʾ��WIFI 2014��1��1��18:11:39 author����һ��
	 */
	public void openwifidialog() {
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setTitle("�������");
		ad.setMessage("ȷ��Ҫ�˳�ϵͳô�ף�");
		ad.setIcon(R.drawable.zoom);
		ad.setCancelable(false);

		ad.setNegativeButton("�˳�", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				exit();
			}
		});

		ad.setPositiveButton("ȡ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				dialog.dismiss();
				run();
			}
		});

		ad.create();
		ad.show();
	}

	/**
	 * �ر�WIFI�����˳����� 2014��1��1��18:10:51 author����һ��
	 */
	public void exit() {
		String wserviceName = Context.WIFI_SERVICE;
		WifiManager firstwm = (WifiManager) getSystemService(wserviceName);
		if (firstwm.getWifiState() == 3) {
			firstwm.setWifiEnabled(false);
		}
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		android.os.Process.killProcess(Process.myPid());
	}
}
