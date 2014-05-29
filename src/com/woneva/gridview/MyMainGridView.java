package com.woneva.gridview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;
import com.woneva.basecell0416.R;

public class MyMainGridView extends Activity {
	GridView gv;
	Context context;
	// ArrayList<?> prgmName;
	public static String[] prgmNameList = { "点名答道", "短信请假", "课堂名单", "快递查询",
			"校内新闻", "导航定位", "天气预报", "联系我们", "退出系统" };

	public static int[] prgmImages = { R.drawable.woneva01,
			R.drawable.woneva02, R.drawable.woneva03, R.drawable.woneva04,
			R.drawable.woneva05, R.drawable.woneva06, R.drawable.woneva07,
			R.drawable.woneva08, R.drawable.woneva09 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		WifiDealer wifidealer =new WifiDealer();
//		
//		if(wifidealer.isWiFiActive()==false){
//			wifidealer.openwifidialog();
//		}
			setContentView(R.layout.mymaingridview);
			gv = (GridView) findViewById(R.id.gridView1);
			gv.setAdapter(new MyCustomAdapter(this, prgmNameList, prgmImages));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
