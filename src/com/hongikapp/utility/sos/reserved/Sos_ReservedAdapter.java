package com.hongikapp.utility.sos.reserved;

import java.util.ArrayList;

import android.R.raw;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sos.R;

public class Sos_ReservedAdapter  extends ArrayAdapter<Sos_Reserved_Data> {
	private LayoutInflater mInflater;

	public Sos_ReservedAdapter(Context context, ArrayList<Sos_Reserved_Data> object) {
		super(context, 0, object);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	
	
	}

	public View gespaceiew(int position, View v, ViewGroup parent) {
		View view = null;
		if (v == null) {
			view = mInflater.inflate(R.layout.sos_reserved_info, null);
		} else {
			view = v;
		}
		// 자료를 받는다
		final Sos_Reserved_Data data = this.getItem(position);
		if (data != null) {
			// 화면 출력
			ImageView spaceimg = (ImageView) view.findViewById(R.id.imageView_mark);
			TextView space = (TextView) view.findViewById(R.id.txt_spaceName);
			TextView date = (TextView) view.findViewById(R.id.txt_reservedDate);
			TextView time = (TextView) view.findViewById(R.id.txt_reservedTime);

			
			
			// ImageView에 뿌려질 해당 이미지값을 연결 3번째 인수값
			// Texspaceiew1에 getLabel()을 출력 즉 1번째 인수값
			//spaceimg.setImageURI(uri);
			spaceimg.setImageResource(R.drawable.ic_launcher);
			space.setText(data.getReserved_Space());
			// Texspaceiew2에 getLabel()을 출력 즉 2번째 인수값
			date.setText(data.getReserved_Date());
			
			time.setText(data.getReserved_Time());
		}
		return view;
	}
}
