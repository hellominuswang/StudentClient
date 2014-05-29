package com.woneva.wifiInfo;

import com.woneva.basecell0416.R;
import com.woneva.gridview.MyMainGridView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Process;
import android.widget.Toast;

public class WifiDealer extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifidealer);

		if (isWiFiActive() == false) {
			Toast.makeText(getApplicationContext(), "设备WIFI没有打开",
					Toast.LENGTH_SHORT).show();
			openwifidialog();
		} else {
			run();
		}
	}

	/**
	 * 界面跳转 2014年1月1日18:15:03 author：王一发
	 */
	public void run() {
		// TODO Auto-generated method stub
		Intent goIntent = new Intent();
		goIntent.setClass(WifiDealer.this, MyMainGridView.class);
		startActivity(goIntent);
	}

	/**
	 * 判断是否打开WIFI 2014年1月1日18:13:26 author：王一发
	 */
	public boolean isWiFiActive() {
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] infos = connectivity.getAllNetworkInfo();
			if (infos != null) {
				for (NetworkInfo ni : infos) {
					if (ni.getTypeName().equals("WIFI") && ni.isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 提示打开WIFI 2014年1月1日18:11:39 author：王一发
	 */
	public void openwifidialog() {
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setTitle("燕大点名");
		ad.setMessage("请打开设备WIFI");
		ad.setIcon(android.R.drawable.ic_dialog_alert);
		ad.setCancelable(false);
		ad.setPositiveButton("打开", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {

				String wserviceName = Context.WIFI_SERVICE;
				WifiManager firstwm = (WifiManager) getSystemService(wserviceName);
				firstwm.setWifiEnabled(true);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				run();
			}
		});

		ad.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				dialog.dismiss();
				exit();
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