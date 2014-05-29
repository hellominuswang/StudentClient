package com.woneva.basecell0416;

import java.util.List;
import java.util.Map;
import com.woneva.DB.DBDao;
import com.woneva.woneva.WonEvaApp;
//import com.woneva.DB.DbOpenHelper;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	List<Map<String, String>> data = null;
	private Spinner spinner;
	TextView tv;
	Button addclass;
	Button baodao;
	private static String wifissid="nodata";
	private WonEvaApp wonevaapp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		addclass = (Button) findViewById(R.id.addclassbtn);
		baodao=(Button) findViewById(R.id.baodaobtn);
		spinner = (Spinner) findViewById(R.id.myspinner);
		SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, getdata(), R.layout.simpleadapterspinneritem,  
	            new String[] {  
	                    "classname", "teachername"  
	            }, new int[] {  
	                    R.id.spinnerone, R.id.spinnertwo  
	            });  
		spinner.setAdapter(simpleAdapter);
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		spinner.setVisibility(View.VISIBLE);
		addclass.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent();
				myintent.setClass(MainActivity.this, AddClass.class);
				startActivity(myintent);
			}
		});
		baodao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String telnum=tv.getText().toString();
				int num=telnum.length();
				String teachertelnum=telnum.substring(num-5,num);
				String fulltelnum=telnum.substring(num-11,num);
				wonevaapp=(WonEvaApp) getApplication();
				if(isWIFItrue(teachertelnum)){
					sendMessage(fulltelnum,"#$#$#$"+wifissid+wonevaapp.getwondata());
					Toast.makeText(getApplicationContext(), "报到成功！",
						     Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "非点名时间！",
						     Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	class SpinnerSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
            @SuppressWarnings("unchecked")
			String className = ((Map<String, Object>) spinner.getItemAtPosition(arg2))  
                    .get("classname").toString(); // 得到当前选中的选项的位置 
            @SuppressWarnings("unchecked")
			String teacherName = ((Map<String, Object>) spinner.getItemAtPosition(arg2))  
                    .get("teachername").toString(); // 得到当前选中的选项的位置 
			@SuppressWarnings("unchecked")
			String teacherTel = ((Map<String, Object>) spinner.getItemAtPosition(arg2))  
                    .get("teachertel").toString(); // 得到当前选中的选项的位置 
			 tv.setText("你选择报到的课程："+className+"\n"+"任课教师："+teacherName+teacherTel);
		}
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	private Boolean isWIFItrue(String teachertelnum) {
		String wserviceName = Context.WIFI_SERVICE;
		WifiManager wm = (WifiManager) getSystemService(wserviceName);
		List<ScanResult> results = wm.getScanResults();
		if(results.size()<=0){
			return false;
		}else{
		for (int i = 0; i < results.size(); i++) {
			ScanResult scanresult = (ScanResult) results.get(i);
			String SSID = scanresult.SSID.toString();
			if (SSID.contains(teachertelnum)) {
				wifissid=SSID;
				return true;
			}
		}
		return false;
		}
	}
	
	private List<Map<String, String>> getdata(){
//		DbOpenHelper helper = new DbOpenHelper(this);
//		helper.getReadableDatabase();
		DBDao dbdao = new DBDao(this);
		String sellsql="select classname,teachername,teachertel from classinfo";
		data=dbdao.listDataMaps(sellsql, null);
		return data;
	}
	
	private void sendMessage(String telnumber,String telmessage){
	    PendingIntent paIntent;
	    SmsManager smsManager;
        paIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0); 
        smsManager = SmsManager.getDefault();    
        smsManager.sendTextMessage(telnumber, null, telmessage, paIntent, null); 	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
