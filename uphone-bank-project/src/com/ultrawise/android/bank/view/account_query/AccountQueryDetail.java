package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.payment.PaymentDefAcc;
import com.ultrawise.android.bank.view.payment.PaymentHistory;
import com.ultrawise.android.bank.view.payment.PaymentLastMonth;
import com.ultrawise.android.bank.view.payment.PaymentMain;
import com.ultrawise.android.bank.view.payment.PaymentManage;
import com.ultrawise.android.bank.view.payment.PaymentPend;
import com.ultrawise.android.bank.view.payment.PaymentSelfService;
import com.ultrawise.android.bank.view.transfer.R;

public class AccountQueryDetail extends ListActivity {
	private Button btnReturn=null;
	private String[] reslut=null;
	private String type=null;
	private EditText txt_M=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_layout_result);
        

        TextView  tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent=new Intent();
				 intent = AccountQueryDetail.this.getIntent();
				 intent.setClass(AccountQueryDetail.this, ABankMain.class);
				 startActivity(intent);
			}
		});
	    TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("账户查询>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent=new Intent();
				 intent = AccountQueryDetail.this.getIntent();
				 intent.setClass(AccountQueryDetail.this, AccountQuery.class);
				 startActivity(intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
        
        TextView  tvClassFirst1 = (TextView) this.findViewById(R.id.class_third);
		tvClassFirst1.setText("明细查询>");
		tvClassFirst1.setVisibility(View.VISIBLE);
		tvClassFirst1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				Intent intent=new Intent();
				intent.setClass(AccountQueryDetail.this, inventory.class);
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
	   
        /**
         * 
         * 接收上一个Activity传来的值
         */
        Intent intent=getIntent();
        reslut=intent.getStringArrayExtra("result");
        type=intent.getStringExtra("type");
		for(String g:reslut)
		{
			System.out.println(g+"=====================>>>>>>>");
		}
		txt_M=(EditText)findViewById(R.id.txt_M);
		txt_M.setText(reslut[2].toString().trim()) ;  
		
		
	        ArrayList<HashMap<String,String>> accoutList = new ArrayList<HashMap<String,String>>();
	                
	        HashMap<String,String> acclist1 = new HashMap<String,String>();
	        HashMap<String,String> acclist2 = new HashMap<String,String>();
	        HashMap<String,String> acclist3 = new HashMap<String,String>();
	        HashMap<String,String> acclist4 = new HashMap<String,String>();
	        HashMap<String,String> acclist5 = new HashMap<String,String>();
	        HashMap<String,String> acclist6 = new HashMap<String,String>(); 
	        
	        
	        acclist1.put("account_list", "来帐时间：");//"来帐时间："
	        acclist1.put("account_list_info", reslut[4]);//"20110224"
	        acclist2.put("account_list", "来帐类型：");
	        acclist2.put("account_list_info", type);//"转账"
	        acclist3.put("account_list", "来帐金额:");
	        acclist3.put("account_list_info", reslut[3]);
	        acclist4.put("account_list", "付款人姓名：");
	        acclist4.put("account_list_info", reslut[6]);
	        acclist5.put("account_list", "付款账号种类：");
	        acclist5.put("account_list_info", reslut[5]);
	        acclist6.put("account_list", "付款账号：");
	        acclist6.put("account_list_info", reslut[0]);
	        
	        accoutList.add(acclist1);
	        accoutList.add(acclist2);
	        accoutList.add(acclist3);
	        accoutList.add(acclist4);
	        accoutList.add(acclist5);
	        accoutList.add(acclist6);
	        
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.accountfrom, new String[] {
					"account_list", "account_list_info" }, new int[] { R.id.accountfrom_1, R.id.accountfrom_2 } );
	        this.setListAdapter(MainListAdapter);
	        
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        //iv_now.setVisibility(View.VISIBLE);
	}
}