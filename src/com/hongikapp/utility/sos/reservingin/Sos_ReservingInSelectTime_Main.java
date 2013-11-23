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
	// description: �� ���ڸ� Ŭ���ϰ� �Ǹ� �����ϰԵ� �۾����� �����Ѵ�.
	protected void onTouched(Sos_ReservingInSelectTime_OnedaySchedule touchedDay) {

		if (isInside(touchedDay, basisDay, during)) {
			Calendar cal = Calendar.getInstance();
			cal.set(basisDay.getYear(), basisDay.getMonth(), basisDay.getDay());
			cal.add(Calendar.DAY_OF_MONTH, during);
			Toast.makeText(
					this,
					(cal.get(Calendar.MONTH) + 1) + "."
							+ cal.get(Calendar.DAY_OF_MONTH)
							+ " ������ ����Ͽ� �ֽʽÿ�.", Toast.LENGTH_SHORT).show();
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
	// description: ���� �۾� �� �ٸ� ��Ƽ��Ƽ�� ��ȯ �Ŀ� �ٽ� ���� ��Ƽ��Ƽ�� ���ƿ��� ��
	// ���ƿ��� �������� �����ϴ� ���� �����ؾ� �Ѵ�.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		SharedPreferences pref = null; // ������ ���� SharedPreferences�� �̿��Ͽ� ������ ������
										// �����Ѵ�.

		Sos_ReservingInSelectTime_OnedaySchedule tempday; // schedule�� �ٷ�� ���� �ӽ�
															// ����
		String temp1;
		int temp2;
		int temp3;
		boolean temp4;
		String temp5;
		boolean temp6; // tmep* �۾��� ���� �ӽú�����
		int ID; // ��� �� ������ ���� OnedaySchedule�� ID�� Ư���Ѵ�.

		if (resultCode == RESULT_OK) {

			switch (requestCode) {
			case 0: // ����
				String deleteOb;
				ID = data.getIntExtra("deleteReturn", 0);
				deleteOb = String.valueOf(ID);
				tempday = (Sos_ReservingInSelectTime_OnedaySchedule) findViewById(ID); // code�󿡼�
																						// ������
																						// �����
																						// �����Ѵ�.
				tempday.deleteSchedule(); // ���� ��Ƽ��Ƽ���� �ݿ��ϵ��� schedule�� �����Ѵ�.

				pref = getSharedPreferences(deleteOb + "view", MODE_PRIVATE);
				SharedPreferences.Editor prefEditor = pref.edit(); // ����� �ݿ��ϱ�
																	// ����
																	// SharedPreference��
																	// �ҷ��´�.
				prefEditor.clear(); // ������ ������ �����Ѵ�.
				prefEditor.commit(); // �� ����� �ݿ��Ѵ�.
				break;

			case 1: // ����
				temp1 = data.getStringExtra("temptitle");
				temp2 = data.getIntExtra("temphour", 0);
				temp3 = data.getIntExtra("tempminute", 0);
				temp4 = data.getBooleanExtra("tempalarm", false);
				temp5 = data.getStringExtra("tempdetails");
				temp6 = data.getBooleanExtra("tempisEnrolled", true); // schedule��
																		// �⺻
																		// ������
																		// ���
																		// ��Ƽ��Ƽ����
																		// �����´�.

				ID = data.getIntExtra("scheID", 0);
				String forsave;
				forsave = String.valueOf(ID);
				tempday = (Sos_ReservingInSelectTime_OnedaySchedule) findViewById(ID); // �����ϱ�
																						// ����
																						// view��
																						// Ư���Ѵ�.

				tempday.setSchedule(temp1, temp2, temp3, temp4, temp5, temp6); // schedule��
																				// ������
																				// �ݿ��Ѵ�.
				tempday.invalidate(); // ���� ��Ƽ��Ƽ�� �����Ѵ�.

				pref = getSharedPreferences(forsave + "view", MODE_PRIVATE);
				SharedPreferences.Editor prvEditor = pref.edit(); // �����ϱ� ����
																	// SharedPreference��
																	// �ҷ��´�.

				prvEditor.putString(forsave + "title", tempday.scheTitle);
				prvEditor.putInt(forsave + "hour", tempday.schetime_Hour);
				prvEditor.putInt(forsave + "minute", tempday.schetime_minute);
				prvEditor.putBoolean(forsave + "alarm", tempday.alarmON);
				prvEditor.putString(forsave + "details", tempday.details);
				prvEditor
						.putBoolean(forsave + "isEnrolled", tempday.isEnrolled); // ������
																					// ������
																					// EnrollSchedule����
																					// �����´�.

				prvEditor.commit(); // ������ ������ �Է��Ѵ�.

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
