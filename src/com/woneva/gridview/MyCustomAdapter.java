package com.woneva.gridview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.woneva.Other.LoginActivity;
import com.woneva.basecell0416.MainActivity;
import com.woneva.basecell0416.R;
import com.woneva.textmessage.SendMessageActivity;
import com.woneva.woneva.ClassStudentList;
import com.woneva.woneva.CurrentLocationActivity;
import com.woneva.Other.ExitActivity;

public class MyCustomAdapter extends BaseAdapter {

	String[] result;
	Context context;
	int[] imageId;
	private static LayoutInflater inflater = null;
//    private WonEvaApp wonevaapp;
	
	public MyCustomAdapter(MyMainGridView mymaingridview,
			String[] prgmNameList, int[] prgmImages) {
		// TODO Auto-generated constructor stub
		result = prgmNameList;
		context = mymaingridview;
		imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public class Holder {
		TextView tv;
		ImageView img;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.mylistgridview, null);
		holder.tv = (TextView) rowView.findViewById(R.id.textView1);
		holder.img = (ImageView) rowView.findViewById(R.id.imageView1);
		holder.tv.setText(result[position]);
		holder.img.setImageResource(imageId[position]);

		rowView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:// ���ͼƬ0��ת
				{
//					wonevaapp=(WonEvaApp) context.getApplicationContext();
//					wonevaapp.getwondata();
					Intent goIntent = new Intent();
					goIntent.setClass(context, MainActivity.class);
					context.startActivity(goIntent);
				}
				break;

				case 1:// ���ͼƬ1��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, SendMessageActivity.class);
					context.startActivity(goIntent);
				}
				break;

				case 2:// ���ͼƬ2��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, ClassStudentList.class);
					context.startActivity(goIntent);
				}
				break;

				case 3:// ���ͼƬ3��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, LoginActivity.class);
					context.startActivity(goIntent);
				}
				break;

				case 4:// ���ͼƬ4��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, LoginActivity.class);
					context.startActivity(goIntent);
				}
				break;

				case 5:// ���ͼƬ5��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, CurrentLocationActivity.class);
					context.startActivity(goIntent);
				}
				break;

				case 6:// ���ͼƬ6��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, LoginActivity.class);
					context.startActivity(goIntent);
				}
				break;

				case 7:// ���ͼƬ7��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, LoginActivity.class);
					context.startActivity(goIntent);
				}
				break;

				case 8:// ���ͼƬ8��ת
				{
					Intent goIntent = new Intent();
					goIntent.setClass(context, ExitActivity.class);
					context.startActivity(goIntent);
				}
					break;
				default:
					Toast.makeText(context, "You Clicked " + result[position],
							Toast.LENGTH_LONG).show();
				}
			}
		});

		return rowView;
	}
	
	
}
