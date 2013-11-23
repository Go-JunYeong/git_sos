package com.hongikapp.utility.sos;

import com.sos.R;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;

import com.hongikapp.utility.sos.reserved.Sos_TabHost_Reserved;
import com.hongikapp.utility.sos.reservingin.Sos_TabHost_ReservingIn;
import com.hongikapp.utility.sos.reservingout.Sos_TabHost_ReservingOut;

@SuppressWarnings("deprecation")
public class Sos_MainTabActivity extends TabActivity implements OnTabChangeListener {
	
	public TabHost tabHost = null;
	public static TabWidget tabwidget;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.subtabactivity);
	    
	    tabHost = (TabHost)findViewById(android.R.id.tabhost);
	    tabwidget = (TabWidget)findViewById(android.R.id.tabs);
	    Log.e("adp2", "adp2");
	    LayoutInflater reserved_vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    LayoutInflater reservingIn_vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    LayoutInflater reservingOut_vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    View reserved_view = (View)reserved_vi.inflate(R.layout.t_tabitem, null);
	    View reservingIn_view = (View)reservingIn_vi.inflate(R.layout.t_tabitem, null);
	    View reservingOut_view = (View)reservingOut_vi.inflate(R.layout.t_tabitem, null);
	    
	    LinearLayout reserved_layout = (LinearLayout)reserved_view.findViewById(R.id.t_tabitem);
	    LinearLayout reservingIn_layout = (LinearLayout)reservingIn_view.findViewById(R.id.t_tabitem);
	    LinearLayout reservingOut_layout = (LinearLayout)reservingOut_view.findViewById(R.id.t_tabitem);

	    
	    //≈« ¿ÃπÃ¡ˆ ª¿‘¿Âº“
	    
	    reserved_layout.setBackgroundResource(R.drawable.ic_launcher);
	    reservingIn_layout.setBackgroundResource(R.drawable.ic_launcher);
	    reservingOut_layout.setBackgroundResource(R.drawable.ic_launcher);
	    
	    Log.e("adp3", "adp3");
	    tabHost.addTab(tabHost.newTabSpec("Sos_Reserved_main")
	    		.setIndicator(reserved_view)
	    		.setContent(new Intent(this, Sos_TabHost_Reserved.class)));
	    
	    tabHost.addTab(tabHost.newTabSpec("Sos_ReservingIn_main")
	    	      .setIndicator(reservingIn_view)
	    	      .setContent(new Intent(this, Sos_TabHost_ReservingIn.class)));
	    
	    tabHost.addTab(tabHost.newTabSpec("Sos_ReservingOut_main")
	    	      .setIndicator(reservingOut_view)
	    	      .setContent(new Intent(this, Sos_TabHost_ReservingOut.class)));
	    for (int tab=0;tab<tabHost.getTabWidget().getChildCount();tab++){
	    	tabHost.getTabWidget().getChildAt(tab).getLayoutParams().height=50;
	    }
	    tabHost.setCurrentTab(0);
	}
	
	public static void toggleMenu() {
		if (tabwidget.getVisibility() == View.GONE) {
			tabwidget.setVisibility(View.VISIBLE);
		} else {
			tabwidget.setVisibility(View.GONE);
		}		
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		
	}
}
