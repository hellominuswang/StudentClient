package com.woneva.woneva;

import android.app.Application;

public class WonEvaApp extends Application {

	private String wondata;

	public String getwondata() {
		return wondata;
	}

	public void setwondata(String newwondata) {
		this.wondata = newwondata;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		setwondata("Welcome new comer!");
	}

}
