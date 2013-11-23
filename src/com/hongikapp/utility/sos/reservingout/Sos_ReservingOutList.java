package com.hongikapp.utility.sos.reservingout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.sos.R;

public class Sos_ReservingOutList extends Activity {

	ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sos_reservingout_list);
		listView = (ListView) findViewById(R.id.txtlist_sos_reservingout_list_list);
	}
}

