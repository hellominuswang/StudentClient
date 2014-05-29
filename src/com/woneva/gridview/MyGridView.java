package com.woneva.gridview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.woneva.basecell0416.R;

public class MyGridView extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.mygridview);
	    
	    GridView gv=(GridView) findViewById(R.id.mygridview);
	    gv.setAdapter(new ImageAdapter(this));
	    
	    gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MyGridView.this,mThumbIds[position], Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private class ImageAdapter extends BaseAdapter{
		private Context mContext;

		public ImageAdapter(Context context) {
			this.mContext=context;
		}
		
		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return mThumbIds[position];
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			//定义一个ImageView,显示在GridView里
			ImageView imageView;
			if(convertView==null){
				imageView=new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(0, 0, 0, 0);
			}else{
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(mThumbIds[position]);
			return imageView;
		}
	}
		
		private Integer[] mThumbIds = {
				R.drawable.woneva01,R.drawable.woneva02,
				R.drawable.woneva03,R.drawable.woneva04,
				R.drawable.woneva05,R.drawable.woneva06,
				R.drawable.woneva07,R.drawable.woneva08,
				R.drawable.woneva09
	    };

}
