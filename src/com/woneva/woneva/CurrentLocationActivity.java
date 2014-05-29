package com.woneva.woneva;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.woneva.DB.DBDao;
//import com.woneva.DB.DbOpenHelper;
import com.woneva.basecell0416.R;

public class CurrentLocationActivity extends Activity {
	Button btnlocation;
	TextView tvlocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.currentlocation);
		
		btnlocation=(Button)findViewById(R.id.btn_location);
		tvlocation=(TextView)findViewById(R.id.tv_location);
		btnlocation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvlocation.setText(findlocation());
			}
		});
	}
	
	public String findlocation(){
//		DbOpenHelper helper = new DbOpenHelper(this);
//		helper.getReadableDatabase();
		DBDao dbdao = new DBDao(this);
		String[] params = {"460"};
		String querysql = "select * from userinfo where mcc=?  limit 0,1";
		
		Map<String, String> map =dbdao.viewData(querysql, params);
		String output =map.get("lac")+map.get("mcc")+map.get("mnc")+map.get("cid")+map.get("radioType");
//		for (Iterator<String> i = map.keySet().iterator(); i.hasNext(); ) {  
//            String key = i.next();           
//            output=" key = " + key + "; value = " + map.get(key);  
//       }
		return output;
	}
	
	
}
