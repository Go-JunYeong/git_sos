package com.hongikapp.utility.sos.reservingin;

import java.util.Calendar;

import com.sos.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Sos_ReservingInSelectTime_Main extends Sos_ReservingInSelectTime_CTCalendarView {

	private Sos_ReservingInSelectTime_OnedaySchedule basisDay;
	private int during;
	Button btnSelect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initialize();

		basisDay = new Sos_ReservingInSelectTime_OnedaySchedule(this);

		Intent intent = getIntent();
		int[] b = intent.getIntArrayExtra("basisDay");
		during = intent.getIntExtra("during", 0);
		if (b != null) {
			basisDay.setYear(b[0]);
			basisDay.setMonth(b[1]);
			basisDay.setDay(b[2]);
		} else {
			Calendar cal = Calendar.getInstance();
			basisDay.setYear(cal.get(Calendar.YEAR));
			basisDay.setMonth(cal.get(Calendar.MONTH));
			basisDay.setDay(cal.get(Calendar.DAY_OF_MONTH));
		}

		btnSelect = (Button) this.findViewById(R.id.btn_Select);
		btnSelect.setOnClickListener(mClick);
	}

	// operation: onTouched
	// description: 각 일자를 클릭하게 되면 실행하게될 작업들을 서술한다.
	protected void onTouched(Sos_ReservingInSelectTime_OnedaySchedule touchedDay) {

		if (isInside(touchedDay, basisDay, during)) {
			Calendar cal = Calendar.getInstance();
			cal.set(basisDay.getYear(), basisDay.getMonth(), basisDay.getDay());
			cal.add(Calendar.DAY_OF_MONTH, during);
			Toast.makeText(
					this,
					(cal.get(Calendar.MONTH) + 1) + "."
							+ cal.get(Calendar.DAY_OF_MONTH)
							+ " 다음에 등록하여 주십시오.", Toast.LENGTH_SHORT).show();
			return;
		}
		/*
		 * if(touchedDay.isEnrolled){ Intent intent = new Intent(this,
		 * ShowSchedule.class); int tempID = touchedDay.getId();
		 * 
		 * final int hour = touchedDay.getschetime_hour(); final int minute =
		 * touchedDay.getschetime_minute(); String s1; String s2; if(hour < 12)
		 * s1 = "AM"; else s1 = "PM"; if(touchedDay.getalarmON()) s2 = "Yes";
		 * else s2 = "No";
		 * 
		 * intent.putExtra("TempID_Show", tempID);
		 * intent.putExtra("Schedule_Title",touchedDay.getschetitle() );
		 * intent.putExtra("Schedule_Limit", hour + ":" + minute +" "+s1);
		 * intent.putExtra("Schedule_Alarm",s2);
		 * intent.putExtra("Schedule_Details", touchedDay.getdetails());
		 * 
		 * startActivityForResult(intent,0); } else { final Intent intent = new
		 * Intent(this, EnrollSchedule.class); int tempID = touchedDay.getId();
		 * intent.putExtra("TempID_Enroll", tempID);
		 * startActivityForResult(intent, 1); }
		 */
	}

	// operation: onActivityResult
	// description: 위의 작업 중 다른 액티비티로 전환 후에 다시 메인 액티비티로 돌아오는 데
	// 돌아오는 과정에서 리턴하는 값을 지정해야 한다.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		SharedPreferences pref = null; // 저장을 위해 SharedPreferences를 이용하여 간단한 변수르
										// 저장한다.

		Sos_ReservingInSelectTime_OnedaySchedule tempday; // schedule을 다루기 위한 임시
															// 변수
		String temp1;
		int temp2;
		int temp3;
		boolean temp4;
		String temp5;
		boolean temp6; // tmep* 작업을 위한 임시변수들
		int ID; // 등록 및 삭제를 위한 OnedaySchedule의 ID를 특정한다.

		if (resultCode == RESULT_OK) {

			switch (requestCode) {
			case 0: // 삭제
				String deleteOb;
				ID = data.getIntExtra("deleteReturn", 0);
				deleteOb = String.valueOf(ID);
				tempday = (Sos_ReservingInSelectTime_OnedaySchedule) findViewById(ID); // code상에서
																						// 삭제할
																						// 대상을
																						// 지정한다.
				tempday.deleteSchedule(); // 메인 액티비티에서 반영하도록 schedule을 삭제한다.

				pref = getSharedPreferences(deleteOb + "view", MODE_PRIVATE);
				SharedPreferences.Editor prefEditor = pref.edit(); // 결과를 반영하기
																	// 위한
																	// SharedPreference를
																	// 불러온다.
				prefEditor.clear(); // 내부의 정보를 삭제한다.
				prefEditor.commit(); // 그 결과를 반영한다.
				break;

			case 1: // 저장
				temp1 = data.getStringExtra("temptitle");
				temp2 = data.getIntExtra("temphour", 0);
				temp3 = data.getIntExtra("tempminute", 0);
				temp4 = data.getBooleanExtra("tempalarm", false);
				temp5 = data.getStringExtra("tempdetails");
				temp6 = data.getBooleanExtra("tempisEnrolled", true); // schedule의
																		// 기본
																		// 정보를
																		// 등록
																		// 액티비티에서
																		// 가져온다.

				ID = data.getIntExtra("scheID", 0);
				String forsave;
				forsave = String.valueOf(ID);
				tempday = (Sos_ReservingInSelectTime_OnedaySchedule) findViewById(ID); // 저장하기
																						// 위한
																						// view를
																						// 특정한다.

				tempday.setSchedule(temp1, temp2, temp3, temp4, temp5, temp6); // schedule의
																				// 정보를
																				// 반영한다.
				tempday.invalidate(); // 메인 액티비티에 적용한다.

				pref = getSharedPreferences(forsave + "view", MODE_PRIVATE);
				SharedPreferences.Editor prvEditor = pref.edit(); // 저장하기 위해
																	// SharedPreference를
																	// 불러온다.

				prvEditor.putString(forsave + "title", tempday.scheTitle);
				prvEditor.putInt(forsave + "hour", tempday.schetime_Hour);
				prvEditor.putInt(forsave + "minute", tempday.schetime_minute);
				prvEditor.putBoolean(forsave + "alarm", tempday.alarmON);
				prvEditor.putString(forsave + "details", tempday.details);
				prvEditor
						.putBoolean(forsave + "isEnrolled", tempday.isEnrolled); // 저정할
																					// 정보를
																					// EnrollSchedule에서
																					// 가져온다.

				prvEditor.commit(); // 가져온 정보를 입력한다.

				Log.d("check save",
						"ID : "
								+ forsave
								+ "Title : "
								+ pref.getString(forsave + "title", "")
								+ "\n"
								+ "details : "
								+ pref.getString(forsave + "details", "")
								+ "\n"
								+ "isEnrolled : "
								+ pref.getBoolean(forsave + "isEnrolled", false));

				break;
			default:
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.calendar_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.menuitem_calendar_0:
			gotoToday();
			return true;
		}

		return false;
	}

	OnClickListener mClick = new OnClickListener() {

		@SuppressWarnings("deprecation")
		public void onClick(View v) {
			if (v == btnSelect) {
				Log.d("OnClickButton", "Time -> ClassRoom");
				Intent intent = new Intent(Sos_ReservingInSelectTime_Main.this,
						Sos_ReservingInSelectClassRoom.class);
				Sos_TabHost_ReservingIn.ReservingIn_Group
						.replaceView(Sos_TabHost_ReservingIn.ReservingIn_Group
								.getLocalActivityManager()
								.startActivity(
										"Utility_Sos",
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView());
			}
		}
	};
}
