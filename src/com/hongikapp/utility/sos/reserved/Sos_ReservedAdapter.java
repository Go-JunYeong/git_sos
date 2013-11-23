package com.hongikapp.utility.sos.reserved;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
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

	public View getView(int position, View v, ViewGroup parent) {
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
			ImageView iv = (ImageView) view
					.findViewById(R.id.imageView_mark);
			TextView tv = (TextView) view
					.findViewById(R.id.textView_spaceName);
			TextView tv2 = (TextView) view
					.findViewById(R.id.textView_reservedInfo);

			// ImageView에 뿌려질 해당 이미지값을 연결 3번째 인수값
			iv.setImageResource(data.getData2());
			// TextView1에 getLabel()을 출력 즉 1번째 인수값
			tv.setText(data.getLabel());
			// TextView2에 getLabel()을 출력 즉 2번째 인수값
			tv2.setText(data.getData());
			tv2.setTextColor(Color.GRAY);
		}
		return view;
	}
}
