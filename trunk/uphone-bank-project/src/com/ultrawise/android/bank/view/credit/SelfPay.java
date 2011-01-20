package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SelfPay extends ListActivity {
	
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.selepay);
	        
	        
	        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	        HashMap<String,String> map1=new HashMap<String,String>();
	        HashMap<String,String> map2=new HashMap<String,String>();
	        HashMap<String,String> map3=new HashMap<String,String>();
	        HashMap<String,String> map4=new HashMap<String,String>();
	        HashMap<String,String> map5=new HashMap<String,String>();
	        HashMap<String,String> map6=new HashMap<String,String>();
	        
	        map1.put("creditNo_key", "还款账户");
	        map1.put("selfPay_value", "应还金额");
	        map2.put("creditNo_key","11111111111111111111");
	        map2.put("selfPay_value", "500");
	        map3.put("creditNo_key","22222222222222222222");
	        map3.put("selfPay_value","300");
	        map4.put("creditNo_key", "33333333333333333333");
	        map4.put("selfPay_value", "600");
	        map5.put("creditNo_key","44444444444444444444");
	        map5.put("selfPay_value", "800");
	        map6.put("creditNo_key","5555555555555555555");
	        map6.put("selfPay_value","2000");
	        list.add(map1);
	        list.add(map2);
	        list.add(map3);
	        list.add(map4);
	        list.add(map5);
	        list.add(map6);
	        SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.selfpaylist,new String[]{"creditNo_key","selfPay_value"},new int[]{R.id.creditNo_key,R.id.selfPay_value});
	        setListAdapter(listAdapter);
	    }
	 public void onListItemClick(ListView l,View v,int position,long id){
	    	super.onListItemClick(l, v, position, id);
	    	HashMap<String,String> map=(HashMap<String,String>)l.getItemAtPosition(position);
	    	System.out.println(map);
	    	String selfPayBal=map.get(1);
	    	System.out.print(selfPayBal);
	    	String selfPayActNo=null;
	    	Iterator<String> iter=map.keySet().iterator();
	    	if(iter.hasNext())selfPayActNo=iter.next();
	    	System.out.print(selfPayActNo);
	    	Intent intent=new Intent();
	    	intent.putExtra("selfPayActNo",selfPayBal);
	    	intent.putExtra("selfPayBal",selfPayActNo);
	    	intent.setClass(SelfPay.this,SelfPayAct.class);
	    	SelfPay.this.startActivity(intent);
	    }
	 	
}
