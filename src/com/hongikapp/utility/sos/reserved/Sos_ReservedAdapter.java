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
		// �ڷḦ �޴´�
		final Sos_Reserved_Data data = this.getItem(position);
		if (data != null) {
			// ȭ�� ���
			ImageView iv = (ImageView) view
					.findViewById(R.id.imageView_mark);
			TextView tv = (TextView) view
					.findViewById(R.id.textView_spaceName);
			TextView tv2 = (TextView) view
					.findViewById(R.id.textView_reservedInfo);

			// ImageView�� �ѷ��� �ش� �̹������� ���� 3��° �μ���
			// TextView1�� getLabel()�� ��� �� 1��° �μ���
			tv.setText(data.getReserved_Space());
			// TextView2�� getLabel()�� ��� �� 2��° �μ���
			tv2.setText(data.getReserved_Date());
			tv2.setTextColor(Color.GRAY);
		}
		return view;
	}
}
