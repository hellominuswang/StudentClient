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

		// ��������Ŀ�ϵ���������
		lv.setOnItemClickListener(itemListener);
		// ���ó����¼�
		registerForContextMenu(lv);
	}

	// ����ʱ��ʾ�Ĳ˵�
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("��ѡ�����");
		menu.add(0, ITEM_MODIFY, 0, "�༭");
		menu.add(0, ITEM_DELETE, 1, "ɾ��");
		menu.add(0, ITEM_CANCEL, 2, "ȡ��");
	}

	// ��Ӧ�༭��ɾ���¼�����
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		// info.targetView�õ�list.xml�е�LinearLayout����.
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
				toastShow("�༭" + className + "�γ�");
				Intent myintent = new Intent();
				myintent.setClass(this, ModifyClass.class);
				Bundle bundle = new Bundle();
				bundle.putString("���", classId);
				bundle.putString("�γ�", className);
				bundle.putString("��ʦ", teacherName);
				bundle.putString("�绰", teachernum);
				myintent.putExtras(bundle);
				startActivity(myintent);
				break;
			case ITEM_DELETE:
				toastShow("����ɾ���γ�:" + className);
				String sqltwo = "delete from classinfo where id=?";
				Object[] paramstwo = { classId };
				dbdao.delData(sqltwo, paramstwo);

				Toast.makeText(getApplicationContext(), "ɾ��" + className + "�ɹ���",
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

	// ��Ŀ�ϵ���������.
	OnItemClickListener itemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// �����view��������list.xml�ж����LinearLayout����.
			// ���Կ���ͨ��findViewById���������ҵ�list.xml�ж���������Ӷ���,����:
			TextView stuId = (TextView) view.findViewById(R.id.two);
			TextView stuName = (TextView) view.findViewById(R.id.three);
			TextView stuAge = (TextView) view.findViewById(R.id.four);

			toastShow("�γ�����:" + stuId.getText().toString() + "; �ον�ʦ:"
					+ stuName.getText().toString() + "; ��ʦ�ֻ���:"
					+ stuAge.getText().toString());
		}
	};

	// ��װToast,һ������ü�,��һ���������ʾʱ��ֻҪ�Ĵ�һ���ط�����.
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

	// ����adapter.
	public SimpleAdapter buildListAdapter(Context context,
			List<Map<String, String>> data) {
		SimpleAdapter adapter = new SimpleAdapter(context, data, R.layout.list,
				new String[] { "_id", "id", "classname", "teachername",
						"teachertel" }, new int[] { R.id.zero, R.id.one,
						R.id.two, R.id.three, R.id.four });
		return adapter;
	}
}
