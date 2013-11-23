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

	private ListView listView; // ListView ����
	private TextView userInfo;
	Sos_ReservedAdapter Reserved_adapter; // Data�� ������ Reserved_adapter
	ArrayList<Sos_Reserved_Data> alist; // Data�� ���� �ڷᱸ��
	Button btnMore, btnCancel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reserved);
		Log.e("ad55p", "ad55p");
		listView = (ListView) findViewById(R.id.txtlist_sos_reserved_list);
		userInfo = (TextView) findViewById(R.id.txt_sos_reserved_title);
		userInfo.setText("B011128 ������ ¯¯¯¯");
		//btnMore = (Button) this.findViewById(R.id.btn_more);
		//btnCancel = (Button) this.findViewById(R.id.btn_cancel);

		alist = new ArrayList<Sos_Reserved_Data>(); // Data�� ������ Reserved_adapter
		Reserved_adapter = new Sos_ReservedAdapter(this.getParent().getParent(), alist); // �����͸� �ޱ� ���� �����;���� ��ü ����
		// ����Ʈ�信 ����� ����

		userInfo.setText("B011128 ������ ¯¯¯¯");

		Reserved_adapter.add(new Sos_Reserved_Data(getApplicationContext(), "HOLLYS\nCOFFEE",
				"5�� 25��\n7�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
		Reserved_adapter.add(new CData(getApplicationContext(), "T606",
				"5�� 20��\n7�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
		Reserved_adapter.add(new CData(getApplicationContext(), "CAFE\nBENEE",
				"9�� 20��\n3�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
	
		listView.setAdapter(Reserved_adapter);
		
		//btnMore.setOnClickListener(mClick);
		//btnCancel.setOnClickListener(mClick);

	}

	private class DataReserved_adapter{
		// ���̾ƿ� XML�� �о���̱� ���� ��ü

	}

	// CData�ȿ� ���� ���� ���� �Ҵ�
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
				// ������ �� �ֵ���
				//Reserved_adapter.delete(new CData(getApplicationContext(), "CAFE\nBENE",
				//		"9�� 20��\n3�ÿ� ����Ǽ̽��ϴ�.", R.drawable.ic_launcher));
				//
			}
		}
	};*/
}
