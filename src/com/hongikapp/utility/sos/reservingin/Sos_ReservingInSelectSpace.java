package com.hongikapp.utility.sos.reservingin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sos.R;

public class Sos_ReservingInSelectSpace extends Activity {

	Button btnSelect;
	final static int DIALOG_1 = 0;
	LayoutInflater inflater;
	String classname;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sos_reservingin_selectspace);
		

		
		inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		btnSelect = (Button) this
				.findViewById(R.id.btn_sos_reservingin_selectspace_Select);
		btnSelect.setOnClickListener(mClick);
		// SelectButton();
		classname="c703";
		setclass(classname);
	}
	
	private void setclass(String classname2) {
		// TODO Auto-generated method stub
		if(classname=="sema"){	
			LinearLayout linearLayout;
			linearLayout = (LinearLayout)findViewById(R.id.changelayout);
			View view;
			view = inflater.inflate( R.layout.space_semina_a,null ); 
			view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			linearLayout.addView(view);
		
		}
		if(classname=="semb"){	
			LinearLayout linearLayout;
			linearLayout = (LinearLayout)findViewById(R.id.changelayout);
			View view;
			view = inflater.inflate( R.layout.space_semina_b,null ); 
			view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			linearLayout.addView(view);
		
		}
		if(classname=="c701"){	
			LinearLayout linearLayout;
			linearLayout = (LinearLayout)findViewById(R.id.changelayout);
			View view;
			view = inflater.inflate( R.layout.space_class_c701,null ); 
			view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			linearLayout.addView(view);
		
		}	
		if(classname=="c702"){	
			LinearLayout linearLayout;
			linearLayout = (LinearLayout)findViewById(R.id.changelayout);
			View view;
			view = inflater.inflate( R.layout.space_class_c702,null ); 
			view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			linearLayout.addView(view);
		
		}		
		if(classname=="c703"){	
			LinearLayout linearLayout;
			linearLayout = (LinearLayout)findViewById(R.id.changelayout);
			View view;
			view = inflater.inflate( R.layout.space_class_c703,null ); 
			view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			linearLayout.addView(view);
		
		}
		
		
		
	}

	OnClickListener mClick = new OnClickListener() {

		@SuppressWarnings("deprecation")
		public void onClick(View v) {
			AlertDialog.Builder alert = new AlertDialog.Builder(
					Sos_ReservingInSelectSpace.this.getParent().getParent().getParent());
			 alert.setInverseBackgroundForced(true);
			alert.setTitle("�˸�");
			alert.setMessage("B011128 �й����� K204ȣ����\n8�� 20�� 7~8:30 �ÿ� �����Ͻðڽ��ϱ�?");
            alert.setCancelable(false);
			Log.d("check", "is OK");
			 alert.setIcon(R.drawable.ic_launcher);
		
			alert.setPositiveButton("��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Log.d("check : ", "#5");
					
					Intent intent = new Intent(
							Sos_ReservingInSelectSpace.this,
							com.hongikapp.utility.sos.reserved.Sos_Reserved_main.class);
					com.hongikapp.utility.sos.reserved.Sos_TabHost_Reserved.Reserved_Group
							.replaceView(com.hongikapp.utility.sos.reserved.Sos_TabHost_Reserved.Reserved_Group
									.getLocalActivityManager()
									.startActivity(
											"Utility_Sos",
											intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
									.getDecorView());
					
					Log.d("check", "is OK");	
				}
					
			});
			
			/*
			 * startActivity(new Intent( Sos_ReservingInSelectSpace.this,
			 * com.hongikapp.utility .sos.reserved.Sos_TabHost_Reserved.class)
			 * .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
			 * Intent.FLAG_ACTIVITY_SINGLE_TOP)); } });
			 */
			
			alert.setNegativeButton("�ƴϿ�",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							/*
							 * startActivity(new Intent(
							 * Sos_ReservingInSelectSpace.this,
							 * Sos_ReservingInSelectTime_Main.class) .
							 * addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
							 * Intent.FLAG_ACTIVITY_SINGLE_TOP));
							 */
							Intent intent = new Intent(
									Sos_ReservingInSelectSpace.this,
									Sos_ReservingInSelectTime_Main.class);
							Sos_TabHost_ReservingIn.ReservingIn_Group
									.replaceView(Sos_TabHost_ReservingIn.ReservingIn_Group
											.getLocalActivityManager()
											.startActivity(
													"Utility_Sos",
													intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
											.getDecorView());
						}
					});
	
			Log.d("check", "is OK?????");
			alert.show();
			Log.d("check", "is OK...............");
		}
		// });
	};

	/*
	 * public void SelectButton() { ((Button)
	 * findViewById(R.id.btn_sos_reservingin_selectspace_Select))
	 * .setOnClickListener(new OnClickListener() {
	 */
}