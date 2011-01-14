package com.ultrawise.bank.view.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainBank extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {   
	    super.onCreate(savedInstanceState);   
	    setContentView(R.layout.mainbank);   
	  
	    TabHost mTabHost = getTabHost();   
	       
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("TAB 1").setContent(new Intent(this,JinRZSView.class)));   
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("TAB 2").setContent(R.id.textview2));   
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("TAB 3").setContent(R.id.textview3));   
	       
	    mTabHost.setCurrentTab(0);   
	}  

}
