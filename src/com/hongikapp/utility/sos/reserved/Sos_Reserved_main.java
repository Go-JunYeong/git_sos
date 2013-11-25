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

	private ListView listView; // ListView 선언
	private TextView userInfo;
	Sos_ReservedAdapter Reserved_adapter; // Data를 연결할 Reserved_adapter
	ArrayList<Sos_Reserved_Data> alist; // Data를 담을 자료구조


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reserved);
		Log.e("ad55p", "ad55p");
		listView = (ListView) findViewById(R.id.txtlist_sos_reserved_list);
		userInfo = (TextView) findViewById(R.id.txt_sos_reserved_title);
		userInfo.setText("B011128 김현아 짱짱짱짱");
		Log.d("nimi1","sibal");
		alist = new ArrayList<Sos_Reserved_Data>(); // Data를 연결할 Reserved_adapter
		Log.d("nimi2","sibal");
		Reserved_adapter = new Sos_ReservedAdapter(this.getParent(), alist); // 데이터를 받기 위해 데이터어댑터 객체 선언
		// 리스트뷰에 어댑터 연결
		Log.d("nimi3","sibal");
		userInfo.setText("B011128 김현아 짱짱짱짱");

		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "HOLLYS\nCOFFEE",
				"5월 25일\n7시에 예약되셨습니다.", R.drawable.ic_launcher));
		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "T606",
				"5월 20일\n7시에 예약되셨습니다.", R.drawable.ic_launcher));
		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "CAFE\nBENEE",
				"9월 20일\n3시에 예약되셨습니다.", R.drawable.ic_launcher));
		Log.d("nimi4","sibal");
		listView.setAdapter(Reserved_adapter);
		Log.d("nimi5","sibal");

	}


}
