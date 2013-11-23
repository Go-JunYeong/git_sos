package com.hongikapp.utility.sos.reservingin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.sos.R;

public class Sos_ReservingInSelectClassRoom extends Activity {

	Spinner spin;
	Button btnSelect;
	Button btnClass;
	Button btnTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reservingin_selectclassroom);

		btnSelect = (Button) this
				.findViewById(R.id.btn_sos_reservingin_selectclassroom_select);
		btnSelect.setOnClickListener(mClick);

		btnClass = (Button) this.findViewById(R.id.btn_class);
		btnClass.setOnClickListener(mClick);
		
		btnTime = (Button) this.findViewById(R.id.btn_time);
		btnTime.setOnClickListener(mClick);
	}

	OnClickListener mClick = new OnClickListener() {

		@SuppressLint("NewApi")
		@SuppressWarnings("deprecation")
		public void onClick(View v) {
			if (v == btnSelect) {
				Log.d("OnClickButton", "ClassRoom -> Space");
				Intent intent = new Intent(Sos_ReservingInSelectClassRoom.this,
						Sos_ReservingInSelectSpace.class);
				Sos_TabHost_ReservingIn.ReservingIn_Group
						.replaceView(Sos_TabHost_ReservingIn.ReservingIn_Group
								.getLocalActivityManager()
								.startActivity(
										"Utility_Sos",
										intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
								.getDecorView());
			} else if (v == btnClass) {
				// ������ 硫���� �����곌린
				PopupMenu popupMenu = new PopupMenu(
						Sos_ReservingInSelectClassRoom.this, v);
				// ������ 硫���� 媛�泥대�� �����깊�� 二쇱�댁�� ���怨� (���)
				// �����깅�� 寃���� ��듯�댁�� ���洹쇳�댁�� ������. (������)
				popupMenu.getMenuInflater().inflate(R.menu.classrooms,
						popupMenu.getMenu());
				// 留�吏�留���� .show() 諛�濡� 紐삳����몃��. inflate 諛���� 媛���� void ��대��.

                popupMenu.setOnDismissListener(new OnDismissListener() {

					@SuppressLint("NewApi")
					public boolean onMenuItemClick(MenuItem item) {
				        Toast.makeText(getApplicationContext(), "adddhahah", Toast.LENGTH_SHORT).show();
						switch (item.getItemId()) {
						case R.id.menu01:
							btnClass.setText("��몃�몃�����");
					        Toast.makeText(getApplicationContext(), "ahahah", Toast.LENGTH_SHORT).show();
							break;
						case R.id.menu02:
							btnClass.setText("K201");
							break;
						case R.id.menu03:
							break;
						case R.id.menu04:
							break;
						case R.id.menu05:
							break;
						}
						return true;
					}

					@Override
					public void onDismiss(PopupMenu menu) {
						// TODO Auto-generated method stub

					}
				});

				popupMenu.show(); // 硫���� ���硫댁�� 蹂댁�닿�� ������.
				// PopupMenu��� ������ 諛������� Manifest������ minSdkVersion 蹂�寃쏀�댁＜��댁�쇳�� (11濡� 蹂�寃�)
				// Disable ������ ������ �����쇰�� ������ 媛���� 蹂�寃쏀�댁＜��� 寃���� ������

			} else if (v == btnTime) {
				// ������ 硫���� �����곌린
				PopupMenu popupMenu = new PopupMenu(
						Sos_ReservingInSelectClassRoom.this, v);
				// ������ 硫���� 媛�泥대�� �����깊�� 二쇱�댁�� ���怨� (���)
				// �����깅�� 寃���� ��듯�댁�� ���洹쇳�댁�� ������. (������)
				popupMenu.getMenuInflater().inflate(R.menu.classrooms,
						popupMenu.getMenu());
				// 留�吏�留���� .show() 諛�濡� 紐삳����몃��. inflate 諛���� 媛���� void ��대��.

                popupMenu.setOnDismissListener(new OnDismissListener() {

					@SuppressLint("NewApi")
					public boolean onMenuItemClick(MenuItem item) {
				        Toast.makeText(getApplicationContext(), "adddhahah", Toast.LENGTH_SHORT).show();
						switch (item.getItemId()) {
						case R.id.menu01:
							btnClass.setText("��몃�몃�����");
					        Toast.makeText(getApplicationContext(), "ahahah", Toast.LENGTH_SHORT).show();
							break;
						case R.id.menu02:
							btnClass.setText("K201");
							break;
						case R.id.menu03:
							break;
						case R.id.menu04:
							break;
						case R.id.menu05:
							break;
						case R.id.menu06:
							btnClass.setText("K201");
							break;
						case R.id.menu07:
							break;
						case R.id.menu08:
							break;
						case R.id.menu09:
							break;
						}
						return true;
					}

					@Override
					public void onDismiss(PopupMenu menu) {
						// TODO Auto-generated method stub

					}
				});

				popupMenu.show(); // 硫���� ���硫댁�� 蹂댁�닿�� ������.
				// PopupMenu��� ������ 諛������� Manifest������ minSdkVersion 蹂�寃쏀�댁＜��댁�쇳�� (11濡� 蹂�寃�)
				// Disable ������ ������ �����쇰�� ������ 媛���� 蹂�寃쏀�댁＜��� 寃���� ������

			}
		}
	};
}