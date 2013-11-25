package com.hongikapp.utility.sos.reserved;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hongikapp.utility.sos.reservingin.Sos_TabHost_ReservingIn;
import com.sos.R;

public class Sos_Reserved_main extends Activity {

	private ListView listView; // ListView ����
	private TextView userInfo;
	Sos_ReservedAdapter Reserved_adapter; // Data�� ������ Reserved_adapter
	ArrayList<Sos_Reserved_Data> alist; // Data�� ���� �ڷᱸ��


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reserved);
		Log.e("ad55p", "ad55p");
		listView = (ListView) findViewById(R.id.txtlist_sos_reserved_list);
		userInfo = (TextView) findViewById(R.id.txt_sos_reserved_title);
		userInfo.setText("B011128 ������ ¯¯¯¯");
		Log.d("nimi1","sibal");
		alist = new ArrayList<Sos_Reserved_Data>(); // Data�� ������ Reserved_adapter
		Log.d("nimi2","sibal");
		Reserved_adapter = new Sos_ReservedAdapter(this.getParent(), alist); // �����͸� �ޱ� ���� �����;���� ��ü ����
		// ����Ʈ�信 ����� ����
		Log.d("nimi3","sibal");
		userInfo.setText("B011128 ������ ¯¯¯¯");

		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "HOLLYS\nCOFFEE",
				"5�� 25��\n7�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "T606",
				"5�� 20��\n7�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "CAFE\nBENEE",
				"9�� 20��\n3�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
		Log.d("nimi4","sibal");
		listView.setAdapter(Reserved_adapter);
		Log.d("nimi5","sibal");

	}


}
