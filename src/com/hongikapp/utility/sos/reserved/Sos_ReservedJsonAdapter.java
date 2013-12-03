package com.hongikapp.utility.sos.reserved;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sos.R;

public class Sos_ReservedJsonAdapter  extends ArrayAdapter<Sos_Reserved_Data> {
	private LayoutInflater mInflater;

	public Sos_ReservedJsonAdapter(Context context, ArrayList<Sos_Reserved_Data> object) {
		// ���� Ŭ������ �ʱ�ȭ ����
		// context, 0, �ڷᱸ��
		super(context, 0, object);
		// Context�� ArrayList<Sos_Reserved_Data>���� �̷���� ����͸� �����
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Log.d("Create Adapter","OK");
	}

	public View getView(int position, View v, ViewGroup parent) {
		
		View view = null;
		// ���� ����Ʈ�� �ϳ��� �׸� ���� ��Ʈ�� ���
		if (v == null) {
			// view�� ������ XML ���̾ƿ��� ���� �о ����Ʈ�信 ����
			view = mInflater.inflate(R.layout.sos_reserved_info, null);
		} else {
			view = v;
		}
		// �ڷḦ �޴´�
		final Sos_Reserved_Data data = this.getItem(position);
		if (data != null) {
			// ȭ�� ���

			TextView reserve_place = (TextView) view.findViewById(R.id.txt_spaceName);
			TextView reserve_month = (TextView) view.findViewById(R.id.txt_reservedMonth);
			TextView reserve_day = (TextView) view.findViewById(R.id.txt_reservedDay);
			TextView start_hour = (TextView) view.findViewById(R.id.txt_reservedHour);
			TextView start_min = (TextView) view.findViewById(R.id.txt_reservedMin);
			
			reserve_place.setText(data.getreserve_place());
			reserve_month.setText(data.getreserve_month());
			reserve_day.setText(data.getreserve_day());
			start_hour.setText(data.getstart_hour());
			start_min.setText(data.getstart_min());
		}
		return view;
	}
}
