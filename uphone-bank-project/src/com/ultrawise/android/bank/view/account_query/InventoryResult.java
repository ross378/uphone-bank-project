package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class InventoryResult extends ListActivity{
	private Intent intent=null;
	private Button btnReturn=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_layout_result);
        

        intent = new Intent();
        TextView  tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 intent = InventoryResult.this.getIntent();
				 intent.setClass(InventoryResult.this, ABankMain.class);
				 startActivity(intent);
			}
		});
	    TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("账户查询>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent = InventoryResult.this.getIntent();
				 intent.setClass(InventoryResult.this, AccountQuery.class);
				 startActivity(intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
        
        TextView  tvClassFirst1 = (TextView) this.findViewById(R.id.class_third);
		tvClassFirst1.setText("明细查询>");
		tvClassFirst1.setVisibility(View.VISIBLE);
		tvClassFirst1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 intent.setClass(InventoryResult.this, inventory.class);
				 startActivity(intent);
			}
		});
		
		//返回键设定
        ImageView btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
	   

	                
	        ArrayList<HashMap<String,String>> accoutList = new ArrayList<HashMap<String,String>>();
	                
	        HashMap<String,String> acclist1 = new HashMap<String,String>();
	        HashMap<String,String> acclist2 = new HashMap<String,String>();
	        HashMap<String,String> acclist3 = new HashMap<String,String>();
	        HashMap<String,String> acclist4 = new HashMap<String,String>();
	       // HashMap<String,String> acclist5 = new HashMap<String,String>();
	               	        
	        acclist1.put("account_list", "交易日期：");
	        acclist1.put("account_list_info", "20110224");
	        acclist2.put("account_list", "来帐账户：");
	        acclist2.put("account_list_info", "238723589732856");
	        acclist3.put("account_list", "收入:");
	        acclist3.put("account_list_info", "10000");
	        acclist4.put("account_list", "余额：");
	        acclist4.put("account_list_info", "25000000000");
	        
	        
	        accoutList.add(acclist1);
	        accoutList.add(acclist2);
	        accoutList.add(acclist3);
	        accoutList.add(acclist4);
	        
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.accountfrom, new String[] {
					"account_list", "account_list_info" }, new int[] { R.id.accountfrom_1, R.id.accountfrom_2 } );
	        this.setListAdapter(MainListAdapter);
	        
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        //iv_now.setVisibility(View.VISIBLE);
	}
}
