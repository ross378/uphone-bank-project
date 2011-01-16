package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class TransactionDetailsList extends ListActivity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.transactiondetails);
	        String creditNo=this.getIntent().getStringExtra("creditNo");
	        Bundle bundle=this.getIntent().getExtras();
	        ArrayList<HashMap<String,String>> transDetailInfos=(ArrayList)bundle.getSerializable("transDetailInfos");
	        
	      //设置ListView的值的
	        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	        for(HashMap<String,String> map:transDetailInfos){
	        	HashMap<String,String> map1=new HashMap<String,String>();
	        	Iterator<String> iter=map.keySet().iterator();
	        	while(iter.hasNext()){
	        		String key=iter.next();
	        		String value=map.get(key);
	        		map1.put("transDetail_key", key);
	        		map1.put("transDetail_value", value);
	        	}
	        	System.out.println(map1.size());
	        	list.add(map1);
	        }	
	        System.out.println(list.size());
	        SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.transactiondetailslist,new String[]{"transDetail_key","transDetail_value"},new int[]{R.id.transDetail_key,R.id.transDetail_value});
	        setListAdapter(listAdapter);
	 }
}
