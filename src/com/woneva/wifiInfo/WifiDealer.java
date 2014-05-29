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
			Toast.makeText(getApplicationContext(), "�豸WIFIû�д�",
					Toast.LENGTH_SHORT).show();
			openwifidialog();
		} else {
			run();
		}
	}

	/**
	 * ������ת 2014��1��1��18:15:03 author����һ��
	 */
	public void run() {
		// TODO Auto-generated method stub
		Intent goIntent = new Intent();
		goIntent.setClass(WifiDealer.this, MyMainGridView.class);
		startActivity(goIntent);
	}

	/**
	 * �ж��Ƿ��WIFI 2014��1��1��18:13:26 author����һ��
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
	 * ��ʾ��WIFI 2014��1��1��18:11:39 author����һ��
	 */
	public void openwifidialog() {
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setTitle("������");
		ad.setMessage("����豸WIFI");
		ad.setIcon(android.R.drawable.ic_dialog_alert);
		ad.setCancelable(false);
		ad.setPositiveButton("��", new DialogInterface.OnClickListener() {
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

		ad.setNegativeButton("�ܾ�", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				dialog.dismiss();
				exit();
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