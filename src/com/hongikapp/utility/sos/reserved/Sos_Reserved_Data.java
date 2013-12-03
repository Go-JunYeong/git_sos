package com.hongikapp.utility.sos.reserved;

import android.content.Context;

public class Sos_Reserved_Data {

	// JSON Node names
	private String uid;
	private String reserve_place;
	private String reserve_month;
	private String reserve_day;
	private String start_hour;
	private String start_min;
	
	public Sos_Reserved_Data(Context context, String p_uid,
			String p_reserve_place, String p_reserve_month,
			String p_reserve_day, String p_start_hour, String p_start_min) {

		uid = p_uid;
		reserve_place = p_reserve_place;
		reserve_month = p_reserve_month;
		reserve_day = p_reserve_day;
		start_hour = p_start_hour;
		start_min = p_start_min;
	}

	public String getuid() {
		return uid;
	}

	public String getreserve_place() {
		return reserve_place;
	}

	public String getreserve_month() {
		return reserve_month;
	}

	public String getreserve_day() {
		return reserve_day;
	}

	public String getstart_hour() {
		return start_hour;
	}

	public String getstart_min() {
		return start_min;
	}

	public void setuid(String uid) {
		this.uid = uid;
	}

	public void setreserve_place(String reserve_place) {
		this.reserve_place = reserve_place;
	}

	public void setreserve_month(String reserve_month) {
		this.reserve_month = reserve_month;
	}

	public void setreserve_day(String reserve_day) {
		this.reserve_day = reserve_day;
	}

	public void setstart_hour(String start_hour) {
		this.start_hour = start_hour;
	}

	public void setstart_min(String start_min) {
		this.start_min = start_min;
	}
}
