package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;

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

public class AccountQueryResult extends ListActivity{
	private Intent intent=null;
	private Button btnReturn=null;
	private TextView acc1=null;
	private TextView acc2=null;
	private TextView type1=null;
	private TextView type2=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_querytype);
        

        intent = new Intent();
        TextView  tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("账户查询>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		//返回键设定
        ImageView btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
	   
		    TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
	        tvClassSecond.setText("明细查询");
//	        tvClassSecond.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					// intent = QueryAccount.this.getIntent();
//					// intent.setClass(QueryAccount.this, AccountManagement.class);
//					// QueryAccount.this.startActivity(intent);
//				}
//			});
	        tvClassSecond.setVisibility(View.VISIBLE);
	        
		    acc1=(TextView)findViewById(R.id.acc1);
		    acc1.setText("账户信息");
	        
	        ArrayList<HashMap<String,String>> accoutList = new ArrayList<HashMap<String,String>>();
	        
	        
	        HashMap<String,String> acclist1 = new HashMap<String,String>();
	        HashMap<String,String> acclist2 = new HashMap<String,String>();
	        HashMap<String,String> acclist3 = new HashMap<String,String>();
	        HashMap<String,String> acclist4 = new HashMap<String,String>();
	        HashMap<String,String> acclist5 = new HashMap<String,String>();
	        
	        
	        
	        acclist1.put("account_list", "币种：");
	        acclist1.put("account_list_info", "人民币");
	        acclist2.put("account_list", "余额：");
	        acclist2.put("account_list_info", "4000");
	        acclist3.put("account_list", "存期");
	        acclist3.put("account_list_info", "三个月");
	        acclist4.put("account_list", "起息月：");
	        acclist4.put("account_list_info", "2011.12.03");
	        acclist5.put("account_list", "利率");
	        acclist5.put("account_list_info", "2.25%");
	        
	        
	        accoutList.add(acclist1);
	        accoutList.add(acclist2);
	        accoutList.add(acclist3);
	        accoutList.add(acclist4);
	        accoutList.add(acclist5);
	        
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.account_quer_list, new String[] {
					"account_list", "account_list_info" }, new int[] { R.id.account_list1, R.id.account_list_info } );
	        this.setListAdapter(MainListAdapter);
	        
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.VISIBLE);
	}
}
