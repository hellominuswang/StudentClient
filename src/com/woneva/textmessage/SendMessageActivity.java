package com.woneva.textmessage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.woneva.basecell0416.R;

public class SendMessageActivity extends Activity {

	EditText tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendmessage);

		Button msgbtn =(Button) findViewById(R.id.btn_send);
		msgbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText msgnumber =(EditText) findViewById(R.id.inputnumber);
				EditText msgtv =(EditText) findViewById(R.id.et_message);
				String telnumber =msgnumber.getText().toString();
				String telmessage =msgtv.getText().toString();
				sendMessage(telnumber, telmessage);
				Toast.makeText(getApplicationContext(), "短信发送成功!",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * 发送短信
	 * @param telnumber
	 * @param telmessage
	 */
	private void sendMessage(String telnumber,String telmessage){
	    PendingIntent paIntent;
	    SmsManager smsManager;
        paIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0); 
        smsManager = SmsManager.getDefault();    
        smsManager.sendTextMessage(telnumber, null, telmessage, paIntent, null); 	
	}
}
