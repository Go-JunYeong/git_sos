package com.hongikapp.utility.sos.reservingin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.sos.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.TableRow.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Sos_ReservingInSelectTime_CTCalendarView extends Activity
		implements OnClickListener {

	private Calendar rightNow;
	private GregorianCalendar gCal;
	private int iYear = 0;
	private int iMonth = 0;

	private int startDayOfweek = 0;
	private int maxDay = 0;
	private int oneday_width = 0;
	private int oneday_height = 0;

	ArrayList<String> daylist;
	ArrayList<String> actlist;

	TextView aDateTxt;

	private int dayCnt;
	private int mSelect = -1;

	protected void initialize() {
		setContentView(R.layout.sos_reservingin_selecttime);

		rightNow = Calendar.getInstance();
		gCal = new GregorianCalendar();
		iYear = rightNow.get(Calendar.YEAR);
		iMonth = rightNow.get(Calendar.MONTH);

		Button btnMPrev = (Button) findViewById(R.id.btn_calendar_prevmonth);
		btnMPrev.setOnClickListener(this);
		Button btnMNext = (Button) findViewById(R.id.btn_calendar_nextmonth);
		btnMNext.setOnClickListener(this);

		if (iMonth == 0)
			btnMPrev.setText(12 + "월");
		else if (iMonth == 11)
			btnMNext.setText(1 + "월");
		else {
			btnMPrev.setText(iMonth + "월");
			btnMNext.setText((iMonth + 2) + "월");
		}

		aDateTxt = (TextView) findViewById(R.id.CalendarMonthTxt);

		makeCalendardata(iYear, iMonth);
	}

	private void printDate(String thisYear, String thisMonth) {

		if (thisMonth.length() == 1) {
			aDateTxt.setText(String.valueOf(thisYear) + "." + "0" + thisMonth);
		} else {
			aDateTxt.setText(String.valueOf(thisYear) + "." + thisMonth);
		}
	}

	private void makeCalendardata(int thisYear, int thisMonth) {
		printDate(String.valueOf(thisYear), String.valueOf(thisMonth + 1));

		Button btnMPrev = (Button) findViewById(R.id.btn_calendar_prevmonth);
		Button btnMNext = (Button) findViewById(R.id.btn_calendar_nextmonth);
		if (iMonth == 0)
			btnMPrev.setText(12 + "월");
		else if (iMonth == 11)
			btnMNext.setText(1 + "월");
		else {
			btnMPrev.setText(iMonth + "월");
			btnMNext.setText((iMonth + 2) + "월");
		}

		rightNow.set(thisYear, thisMonth, 1);
		gCal.set(thisYear, thisMonth, 1);
		startDayOfweek = rightNow.get(Calendar.DAY_OF_WEEK);

		maxDay = gCal.getActualMaximum((Calendar.DAY_OF_MONTH));
		if (daylist == null)
			daylist = new ArrayList<String>();
		daylist.clear();

		if (actlist == null)
			actlist = new ArrayList<String>();
		actlist.clear();

		daylist.add("일");
		actlist.add("");
		daylist.add("월");
		actlist.add("");
		daylist.add("화");
		actlist.add("");
		daylist.add("수");
		actlist.add("");
		daylist.add("목");
		actlist.add("");
		daylist.add("금");
		actlist.add("");
		daylist.add("토");
		actlist.add("");

		if (startDayOfweek != 1) {
			gCal.set(thisYear, thisMonth - 1, 1);
			int prevMonthMaximumDay = (gCal
					.getActualMaximum((Calendar.DAY_OF_MONTH)) + 2);
			for (int i = startDayOfweek; i > 1; i--) {
				daylist.add(Integer.toString(prevMonthMaximumDay - i));
				actlist.add("p");
			}
		}

		for (int i = 1; i <= maxDay; i++) {
			daylist.add(Integer.toString(i));
			actlist.add("");
		}

		int dayDummy = (startDayOfweek - 1) + maxDay;
		if (dayDummy > 35) {
			dayDummy = 42 - dayDummy;
		} else {
			dayDummy = 35 - dayDummy;
		}

		if (dayDummy != 0) {
			for (int i = 1; i <= dayDummy; i++) {
				daylist.add(Integer.toString(i));
				actlist.add("n");
			}
		}

		makeCalendar();
	}

	@SuppressWarnings("deprecation")
	private void makeCalendar() {

		final Sos_ReservingInSelectTime_OnedaySchedule[] oneday = new Sos_ReservingInSelectTime_OnedaySchedule[daylist
				.size()];
		final Calendar today = Calendar.getInstance();

		TableLayout tl = (TableLayout) findViewById(R.id.tl_calendar_monthly);
		tl.removeAllViews();

		dayCnt = 0;
		int maxRow = ((daylist.size() > 42) ? 7 : 6);
		int maxColumn = 7;

		oneday_width = getWindow().getWindowManager().getDefaultDisplay()
				.getWidth();
		oneday_height = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();

		oneday_height = ((((oneday_height >= oneday_width) ? oneday_height
				: oneday_width) - tl.getTop()) / (maxRow + 1)) - 110;
		// 기본값-10
		oneday_width = ((oneday_width) / maxColumn) + 1;

		int daylistsize = daylist.size() - 1;
		for (int i = 1; i <= maxRow; i++) {
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			for (int j = 1; j <= maxColumn; j++) {
				oneday[dayCnt] = new Sos_ReservingInSelectTime_OnedaySchedule(
						getApplicationContext());

				if ((dayCnt % 7) == 0) {
					oneday[dayCnt].setTextDayColor(Color.RED);
				} else if ((dayCnt % 7) == 6) {
					oneday[dayCnt].setTextDayColor(Color.BLUE);
				} else {
					oneday[dayCnt].setTextDayColor(Color.BLACK);
				}

				if (i % 2 == 1) {
					oneday[dayCnt].setBgDayPaint(Color.LTGRAY);
				}

				// 요일 글자 설정
				if (dayCnt >= 0 && dayCnt < 7) {
					// oneday[dayCnt].setBgDayPaint(Color.DKGRAY);
					oneday[dayCnt].setTextDayColor(Color.BLACK);
					oneday[dayCnt].setTextActcntSize(14);
					// oneday[dayCnt].setTextActCnt(oneday[dayCnt].getTextActCnt());
					oneday[dayCnt].setTextDaySize(20);
					oneday[dayCnt].setLayoutParams(new LayoutParams(
							oneday_width, oneday_height));
					// oneday_width, 35));
					oneday[dayCnt].isToday = false;

				}
				// 요일 숫자 설정
				else {

					oneday[dayCnt].isToday = false;
					oneday[dayCnt].setDayOfWeek(dayCnt % 7 + 1);
					oneday[dayCnt].setDay(Integer.valueOf(daylist.get(dayCnt))
							.intValue());
					oneday[dayCnt].setTextActcntTopPadding(18);
					oneday[dayCnt].setTextActcntSize(14);
					oneday[dayCnt].setTextActcntColor(Color.BLACK);
					oneday[dayCnt]
							.setBgSelectedDayPaint(Color.rgb(0, 162, 232));
					// Today's Background Color is White
					oneday[dayCnt].setBgTodayPaint(Color.rgb(255, 255, 255));
					// oneday[dayCnt].setBgTodayPaint(Color.rgb(255, 167, 167));
					// oneday[dayCnt].setBgActcntPaint(Color.YELLOW);//
					// Color.rgb(251,
					// 247,
					// 176));
					oneday[dayCnt].setLayoutParams(new LayoutParams(
							oneday_width, oneday_height));

					// 이전 달 날짜 중 일부를 표시
					if (actlist.get(dayCnt).equals("p")) {
						oneday[dayCnt].setTextDaySize(18);
						oneday[dayCnt].setTextDayColor(Color.GRAY);
						actlist.set(dayCnt, "");
						oneday[dayCnt].setTextDayTopPadding(-4);

						if (iMonth - 1 < Calendar.JANUARY) {
							oneday[dayCnt].setMonth(Calendar.DECEMBER);
							oneday[dayCnt].setYear(iYear - 1);
						} else {
							oneday[dayCnt].setMonth(iMonth - 1);
							oneday[dayCnt].setYear(iYear);
						}

					}
					// 다음 달 날짜 중 일부를 표시
					else if (actlist.get(dayCnt).equals("n")) {
						oneday[dayCnt].setTextDaySize(18);
						oneday[dayCnt].setTextDayColor(Color.GRAY);
						actlist.set(dayCnt, "");
						oneday[dayCnt].setTextDayTopPadding(-4);
						if (iMonth + 1 > Calendar.DECEMBER) {
							oneday[dayCnt].setMonth(Calendar.JANUARY);
							oneday[dayCnt].setYear(iYear + 1);
						} else {
							oneday[dayCnt].setMonth(iMonth + 1);
							oneday[dayCnt].setYear(iYear);
						}

					} else {
						oneday[dayCnt].setTextDaySize(24);
						oneday[dayCnt].setYear(iYear);
						oneday[dayCnt].setMonth(iMonth);

						if (oneday[dayCnt].getDay() == today
								.get(Calendar.DAY_OF_MONTH)
								&& oneday[dayCnt].getMonth() == today
										.get(Calendar.MONTH)
								&& oneday[dayCnt].getYear() == today
										.get(Calendar.YEAR)) {

							Log.d("Today check",
									"Today is " + oneday[dayCnt].getDay());
							oneday[dayCnt].isToday = true;
							actlist.set(dayCnt, "Today");
							oneday[dayCnt].invalidate();
							mSelect = dayCnt;
						}

						// Days that can Select are colored Pink.
						if ((oneday[dayCnt].getYear() == today
								.get(Calendar.YEAR))
								&& (oneday[dayCnt].getMonth() == today
										.get(Calendar.MONTH))
								&& (oneday[dayCnt].getDay() > today
										.get(Calendar.DAY_OF_MONTH))
								&& (oneday[dayCnt].getDay() < (today
										.get(Calendar.DAY_OF_MONTH) + 8))) {
							oneday[dayCnt].setBgDayPaint(Color.rgb(255, 167,
									167));
						}
					}
					oneday[dayCnt].setId(dayCnt); // 저장을 위해 view의 ID를 미리 지정한다.

					// Get ID's Info. for saving
					String resume = String.valueOf(oneday[dayCnt].getId());

					SharedPreferences Resuprv = getSharedPreferences(resume
							+ "view", MODE_PRIVATE);
					oneday[dayCnt].setSchedule(
							Resuprv.getString(resume + "title", ""),
							Resuprv.getInt(resume + "hour", 0),
							Resuprv.getInt(resume + "minute", 0),
							Resuprv.getBoolean(resume + "alarm", false),
							Resuprv.getString(resume + "details", ""),
							// 저장된 정보를 불러와서 Main Activity에 반영한다.
							Resuprv.getBoolean(resume + "isEnrolled", false));

					/*
					 * Log.d("check Resume", "ID : " + resume + " Title : " +
					 * Resuprv.getString(resume + "title", " ") + "details : " +
					 * Resuprv.getString(resume + "details", " ") +
					 * "isEnrolled : " + Resuprv.getBoolean(resume +
					 * "isEnrolled", false));
					 */
					oneday[dayCnt].invalidate(); // 화면 재출력

					oneday[dayCnt]
							.setOnLongClickListener(new OnLongClickListener() {
								public boolean onLongClick(View v) {
									/*
									 * Toast.makeText(context,
									 * iYear+"-"+iMonth+"-"
									 * +oneday[v.getId()].getTextDay(),
									 * Toast.LENGTH_LONG).show();
									 */
									return false;
								}
							});

					oneday[dayCnt].setOnTouchListener(new OnTouchListener() {

						public boolean onTouch(View v, MotionEvent event) {

							if (oneday[v.getId()].getTextDay() != ""
									&& event.getAction() == MotionEvent.ACTION_UP) {
								if (mSelect != -1) {
									oneday[mSelect].setSelected(false);
									oneday[mSelect].invalidate();
								}
								oneday[v.getId()].setSelected(true);
								oneday[v.getId()].invalidate();
								mSelect = v.getId();

								onTouched(oneday[mSelect]);
							}
							return false;
						}
					});
				}

				oneday[dayCnt].setTextDay(daylist.get(dayCnt).toString());
				oneday[dayCnt].setTextActCnt(actlist.get(dayCnt).toString());

				oneday[dayCnt].invalidate();
				tr.addView(oneday[dayCnt]);

				if (daylistsize != dayCnt) {
					dayCnt++;
				} else {
					break;
				}
			}
			tl.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}

	protected String doubleString(int value) {
		String temp;

		if (value < 10) {
			temp = "0" + String.valueOf(value);

		} else {
			temp = String.valueOf(value);
		}
		return temp;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_calendar_nextmonth:
			if (iMonth == 11) {
				iYear = iYear + 1;
				iMonth = 0;
			} else {
				iMonth = iMonth + 1;
			}
			makeCalendardata(iYear, iMonth);
			break;
		case R.id.btn_calendar_prevmonth:
			if (iMonth == 0) {
				iYear = iYear - 1;
				iMonth = 11;
			} else {
				iMonth = iMonth - 1;
			}
			makeCalendardata(iYear, iMonth);
			break;
		}
	}

	protected void onTouched(Sos_ReservingInSelectTime_OnedaySchedule oneday) {

	}

	protected boolean isInside(Sos_ReservingInSelectTime_OnedaySchedule select,
			Sos_ReservingInSelectTime_OnedaySchedule today, int during) {
		if ((select.getYear() == today.getYear())
				&& (select.getMonth() == today.getMonth())
				&& (select.getDay() > today.getDay())
				&& (select.getDay() < (today.getDay() + 8))) {
			return false; // Can Select
		}
		return true; // Can't Select
	}

	public void gotoToday() {
		final Calendar today = Calendar.getInstance();
		iYear = today.get(Calendar.YEAR);
		iMonth = today.get(Calendar.MONTH);
		makeCalendardata(today.get(Calendar.YEAR), today.get(Calendar.MONTH));
	}

}