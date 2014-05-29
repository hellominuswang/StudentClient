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
	 * 界面跳转 2014年1月1日18:15:03 author：王一发
	 */
	public void run() {
		// TODO Auto-generated method stub
		Intent goIntent = new Intent();
		goIntent.setClass(ExitActivity.this, MyMainGridView.class);
		startActivity(goIntent);
	}

	/**
	 * 提示打开WIFI 2014年1月1日18:11:39 author：王一发
	 */
	public void openwifidialog() {
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setTitle("掌上燕大");
		ad.setMessage("确认要退出系统么亲？");
		ad.setIcon(R.drawable.zoom);
		ad.setCancelable(false);

		ad.setNegativeButton("退出", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				exit();
			}
		});

		ad.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				dialog.dismiss();
				run();
			}
		});

		ad.create();
		ad.show();
	}

	/**
	 * 关闭WIFI，并退出程序 2014年1月1日18:10:51 author：王一发
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
