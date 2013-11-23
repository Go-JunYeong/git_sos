package com.hongikapp.utility.sos.reservingout;

import java.util.ArrayList;
/*
import com.hongikapp.utility.TabHost_Utility;
import com.hongikapp.utility.Utility_main;
*/
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressWarnings("deprecation")
public class Sos_TabHost_ReservingOut extends ActivityGroup {
	public static Sos_TabHost_ReservingOut ReservingOut_Group;
	private ArrayList<View> history;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		history = new ArrayList<View>();
		ReservingOut_Group = this;

		Intent intent = new Intent(Sos_TabHost_ReservingOut.this, Sos_ReservingOutList.class);
		View view = getLocalActivityManager().startActivity("Sos_ReservingOutList",
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		replaceView(view);
	}

	// ���ο� Level�� Activity�� �߰��ϴ� ���
	public void replaceView(View view) {
		history.add(view);
		setContentView(view);
	}
/*
	// Back Key�� �������� ��쿡 ���� ó��
	public void back() {
		if (history.size() > 0) {
			history.remove(history.size() - 1);
			if (history.size() == 0) {
				Intent intent = new Intent(Sos_TabHost_ReservingOut.this, Utility_main.class);
				TabHost_Utility.UtilityGroup.replaceView(TabHost_Utility.UtilityGroup
								.getLocalActivityManager()
								.startActivity("Utility_main",
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView());
			}
			else
				setContentView(history.get(history.size() - 1));
		} else {
			finish();
		}
	}

	// Back Key�� ���� Event Handler
	@Override
	public void onBackPressed() {
		ReservingOut_Group.back();
		return;
	}
*/
}
