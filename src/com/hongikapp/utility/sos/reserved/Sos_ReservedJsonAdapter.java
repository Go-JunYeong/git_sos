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
		// 상위 클래스의 초기화 과정
		// context, 0, 자료구조
		super(context, 0, object);
		// Context와 ArrayList<Sos_Reserved_Data>으로 이루어진 어댐터를 만든다
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Log.d("Create Adapter","OK");
	}

	public View getView(int position, View v, ViewGroup parent) {
		
		View view = null;
		// 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
		if (v == null) {
			// view가 없으면 XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
			view = mInflater.inflate(R.layout.sos_reserved_info, null);
		} else {
			view = v;
		}
		// 자료를 받는다
		final Sos_Reserved_Data data = this.getItem(position);
		if (data != null) {
			// 화면 출력

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
