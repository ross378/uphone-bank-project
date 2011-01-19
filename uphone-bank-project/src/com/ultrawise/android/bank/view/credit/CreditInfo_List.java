package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

public class CreditInfo_List extends ListActivity {
	private ImageButton ib=null;
	private String creditNo=null;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.creditinfo);
	        String creditNo=this.getIntent().getStringExtra("creditNo");
	        
	        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	        HashMap<String,String> map1=new HashMap<String,String>();
	        HashMap<String,String> map2=new HashMap<String,String>();
	        HashMap<String,String> map3=new HashMap<String,String>();
	        HashMap<String,String> map4=new HashMap<String,String>();
	        HashMap<String,String> map5=new HashMap<String,String>();
	        HashMap<String,String> map6=new HashMap<String,String>();
	        map1.put("creditinfo_key", "卡号:");
	        map1.put("creditinfo_value", creditNo);
	        map2.put("creditinfo_key","信用额度:");
	        map2.put("creditinfo_value", "5000");
	        map3.put("creditinfo_key","可用额度:");
	        map3.put("creditinfo_value","3000");
	        map4.put("creditinfo_key", "每月账单日:");
	        map4.put("creditinfo_value", "23日");
	        map5.put("creditinfo_key","本期应还款额:");
	        map5.put("creditinfo_value", "400");
	        map6.put("creditinfo_key","本期到期还款日:");
	        map6.put("creditinfo_value","23日");
	        list.add(map1);
	        list.add(map2);
	        list.add(map3);
	        list.add(map4);
	        list.add(map5);
	        list.add(map6);
	        SimpleAdapter listAdapter=new SimpleAdapter(this,list,R.layout.creditinfolist,new String[]{"creditinfo_key","creditinfo_value"},new int[]{R.id.creditinfo_key,R.id.creditinfo_value});
	        setListAdapter(listAdapter);
	        ib=(ImageButton)findViewById(R.id.transactiondetails);
	        ib.setOnClickListener(new TransactiondetailsImageButtonListener());
	    }
	 	class TransactiondetailsImageButtonListener implements OnClickListener{

			public void onClick(View arg0) {
				ArrayList<HashMap<String,String>> transDetailInfos=new ArrayList<HashMap<String,String>>();
				HashMap<String,String> transDetailInfo1=new HashMap<String,String>();
				
				transDetailInfo1.put("交易日期:", "2010年1月5日");
				transDetailInfo1.put("交易类型:", "购物");
				transDetailInfo1.put("交易金额:","500");
				transDetailInfo1.put("交易结果:","成功");
				
//				HashMap<String,String> transDetailInfo2=new HashMap<String,String>();
//				
//				transDetailInfo2.put("交易日期:", "2010年1月25日");
//				transDetailInfo2.put("交易类型:", "电费");
//				transDetailInfo2.put("交易金额:","200");
//				transDetailInfo2.put("交易结果:","成功");
//				
				transDetailInfos.add(transDetailInfo1);
				//transDetailInfos.add(transDetailInfo2);
				
				Bundle bundle = new Bundle(); 
				bundle.putSerializable("transDetailInfos",transDetailInfos);

				Intent intent=new Intent();
				intent.putExtra("creditNo", creditNo);
				intent.putExtras(bundle); 
				intent.setClass(CreditInfo_List.this, TransactionDetailsList.class);
				CreditInfo_List.this.startActivity(intent); 
				
			}
	 		
	 	}
}
