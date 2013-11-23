package com.hongikapp.utility.sos.reserved;

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
public class Sos_TabHost_Reserved extends ActivityGroup {
	public static Sos_TabHost_Reserved Reserved_Group;
	private ArrayList<View> history;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		history = new ArrayList<View>();
		Reserved_Group = this;

		Intent intent = new Intent(Sos_TabHost_Reserved.this, Sos_Reserved_main.class);
		View view = getLocalActivityManager().startActivity("Sos_Reserved_main",
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		replaceView(view);
	}

	// 새로운 Level의 Activity를 추가하는 경우
	public void replaceView(View view) {
		history.add(view);
		setContentView(view);
	}
/*
	// Back Key가 눌러졌을 경우에 대한 처리
	public void back() {
		if (history.size() > 0) {
			history.remove(history.size() - 1);
			if (history.size() == 0) {
				Intent intent = new Intent(Sos_TabHost_Reserved.this, Utility_main.class);
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

	// Back Key에 대한 Event Handler
	@Override
	public void onBackPressed() {
		Reserved_Group.back();
		return;
	}
	*/
}
