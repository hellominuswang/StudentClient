package com.woneva.basecell0416;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.woneva.DB.DBDao;
//import com.woneva.DB.DbOpenHelper;

public class AllClass extends Activity {
	List<Map<String, String>> data = null;
	ListView lv;
	private static final int ITEM_MODIFY = 1;
	private static final int ITEM_DELETE = 2;
	private static final int ITEM_CANCEL = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allclassactivity);
		lv = (ListView) findViewById(R.id.lv_allclass);
		int a = getdata().size();
		Log.i("haha", String.valueOf(a));
		SimpleAdapter adapter = buildListAdapter(this, getdata());
		lv.setAdapter(adapter);

		// 设置在条目上单击监听器
		lv.setOnItemClickListener(itemListener);
		// 设置长按事件
		registerForContextMenu(lv);
	}

	// 长按时显示的菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("请选择操作");
		menu.add(0, ITEM_MODIFY, 0, "编辑");
		menu.add(0, ITEM_DELETE, 1, "删除");
		menu.add(0, ITEM_CANCEL, 2, "取消");
	}

	// 响应编辑和删除事件处理
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		// info.targetView得到list.xml中的LinearLayout对象.
		String classId = ((TextView) info.targetView.findViewById(R.id.one))
				.getText().toString();
		String className = ((TextView) info.targetView.findViewById(R.id.two))
				.getText().toString();
		String teacherName = ((TextView) info.targetView
				.findViewById(R.id.three)).getText().toString();
		String teachernum = ((TextView) info.targetView.findViewById(R.id.four))
				.getText().toString();

		if (!TextUtils.isEmpty(className)) {
//			DbOpenHelper helper = new DbOpenHelper(this);
//			helper.getReadableDatabase();
			DBDao dbdao = new DBDao(this);
			switch (item.getItemId()) {
			case ITEM_MODIFY:
				toastShow("编辑" + className + "课程");
				Intent myintent = new Intent();
				myintent.setClass(this, ModifyClass.class);
				Bundle bundle = new Bundle();
				bundle.putString("编号", classId);
				bundle.putString("课程", className);
				bundle.putString("教师", teacherName);
				bundle.putString("电话", teachernum);
				myintent.putExtras(bundle);
				startActivity(myintent);
				break;
			case ITEM_DELETE:
				toastShow("即将删除课程:" + className);
				String sqltwo = "delete from classinfo where id=?";
				Object[] paramstwo = { classId };
				dbdao.delData(sqltwo, paramstwo);

				Toast.makeText(getApplicationContext(), "删除" + className + "成功！",
						Toast.LENGTH_SHORT).show();
				Intent mine = new Intent();
				mine.setClass(AllClass.this, AddClass.class);
				startActivity(mine);
				break;
			case ITEM_CANCEL:
				break;
			default:
				break;
			}
		}
		return false;
	}

	// 条目上单击处理方法.
	OnItemClickListener itemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// 这里的view是我们在list.xml中定义的LinearLayout对象.
			// 所以可以通过findViewById方法可以找到list.xml中定义的它的子对象,如下:
			TextView stuId = (TextView) view.findViewById(R.id.two);
			TextView stuName = (TextView) view.findViewById(R.id.three);
			TextView stuAge = (TextView) view.findViewById(R.id.four);

			toastShow("课程名称:" + stuId.getText().toString() + "; 任课教师:"
					+ stuName.getText().toString() + "; 教师手机号:"
					+ stuAge.getText().toString());
		}
	};

	// 封装Toast,一方面调用简单,另一方面调整显示时间只要改此一个地方即可.
	public void toastShow(String text) {
		Toast.makeText(AllClass.this, text, Toast.LENGTH_SHORT).show();
	}

	private List<Map<String, String>> getdata() {
//		DbOpenHelper helper = new DbOpenHelper(this);
//		helper.getReadableDatabase();
		DBDao dbdao = new DBDao(this);
		String sellsql = "select id,classname,teachername,teachertel from classinfo";
		data = dbdao.listDataMaps(sellsql, null);
		return data;
	}

	// 构建adapter.
	public SimpleAdapter buildListAdapter(Context context,
			List<Map<String, String>> data) {
		SimpleAdapter adapter = new SimpleAdapter(context, data, R.layout.list,
				new String[] { "_id", "id", "classname", "teachername",
						"teachertel" }, new int[] { R.id.zero, R.id.one,
						R.id.two, R.id.three, R.id.four });
		return adapter;
	}
}
