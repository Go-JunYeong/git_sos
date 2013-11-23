package com.hongikapp.utility.sos.reserved;

import com.sos.R;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayReserved_adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Sos_Reserved_main extends Activity {

	private ListView listView; // ListView 선언
	private TextView userInfo;
	Sos_ReservedAdapter Reserved_adapter; // Data를 연결할 Reserved_adapter
	ArrayList<Sos_Reserved_Data> alist; // Data를 담을 자료구조
	Button btnMore, btnCancel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reserved);
		Log.e("ad55p", "ad55p");
		listView = (ListView) findViewById(R.id.txtlist_sos_reserved_list);
		userInfo = (TextView) findViewById(R.id.txt_sos_reserved_title);
		userInfo.setText("B011128 김현아 짱짱짱짱");
		//btnMore = (Button) this.findViewById(R.id.btn_more);
		//btnCancel = (Button) this.findViewById(R.id.btn_cancel);

		alist = new ArrayList<Sos_Reserved_Data>(); // Data를 연결할 Reserved_adapter
		Reserved_adapter = new Sos_ReservedAdapter(this.getParent().getParent(), alist); // 데이터를 받기 위해 데이터어댑터 객체 선언
		// 리스트뷰에 어댑터 연결

		userInfo.setText("B011128 김현아 짱짱짱짱");

		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "HOLLYS\nCOFFEE",
				"5월 25일\n7시에 예약되셨습니다.", R.drawable.ic_launcher));
		Reserved_adapter.add(new CData(getApplicationContext(), "T606",
				"5월 20일\n7시에 예약되셨습니다.", R.drawable.ic_launcher));
		Reserved_adapter.add(new CData(getApplicationContext(), "CAFE\nBENEE",
				"9월 20일\n3시에 예약되셨습니다.", R.drawable.ic_launcher));
	
		listView.setAdapter(Reserved_adapter);
		
		//btnMore.setOnClickListener(mClick);
		//btnCancel.setOnClickListener(mClick);

	}

	private class DataReserved_adapter{
		// 레이아웃 XML을 읽어들이기 위한 객체

	}

	// CData안에 받은 값을 직접 할당
	public class CData {

		private String m_szLabel;
		private String m_szData;
		private int m_szData2;

		public CData(Context context, String p_szLabel, String p_szDataFile,
				int p_szData2) {
			m_szLabel = p_szLabel;
			m_szData = p_szDataFile;
			m_szData2 = p_szData2;
		}

		public String getLabel() {
			return m_szLabel;
		}

		public String getData() {
			return m_szData;
		}

		public int getData2() {
			return m_szData2;
		}
	}
/*
	OnClickListener mClick = new OnClickListener() {

		@SuppressWarnings("deprecation")
		public void onClick(View v) {
			if (v == btnMore) {
				Intent intent = new Intent(
						Sos_Reserved_main.this,
						com.hongikapp.utility.sos.reservingin.Sos_ReservingInSelectSpace.class);
				Sos_TabHost_ReservingIn.ReservingIn_Group
						.replaceView(Sos_TabHost_ReservingIn.ReservingIn_Group
								.getLocalActivityManager()
								.startActivity(
										"Utility_Sos_reserved",
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView());
			} else if (v == btnCancel) {
				// 삭제할 수 있도록
				//Reserved_adapter.delete(new CData(getApplicationContext(), "CAFE\nBENE",
				//		"9월 20일\n3시에 예약되셨습니다.", R.drawable.ic_launcher));
				//
			}
		}
	};*/
}
