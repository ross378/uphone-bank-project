package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CreditInventoryResult extends ListActivity{
	private Intent intent=null;
	private Button btnReturn=null;
	private String[] reslut=null;
	private String  disc=null;
	private String  type=null;
	private EditText txt_M=null;
	String date;
	String name;
	String amount;
	String jiao;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_layout_result);
        Intent receive_intent = getIntent();
        date = receive_intent.getStringExtra("date");
        name= receive_intent.getStringExtra("name");
        jiao=receive_intent.getStringExtra("jiao");
        amount=receive_intent.getStringExtra("amount");

        intent = new Intent();
        TextView  tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 intent = CreditInventoryResult.this.getIntent();
				 intent.setClass(CreditInventoryResult.this, ABankMain.class);
				 startActivity(intent);
			}
		});
	    TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("信用卡>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent = CreditInventoryResult.this.getIntent();
				 intent.setClass(CreditInventoryResult.this, CreditView.class);
				 startActivity(intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
        
        TextView  tvClassFirst1 = (TextView) this.findViewById(R.id.class_third);
		tvClassFirst1.setText("帐户查询");
		tvClassFirst1.setVisibility(View.VISIBLE);
	
		//返回键设定
        ImageView btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		  //设置底部按钮
        ImageView	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CreditInventoryResult.this, ABankMain.class);
				CreditInventoryResult.this.startActivity(intent);
				finish();
			}
		});
		//btnCoustom.setVisibility(View.VISIBLE);
		
		ImageView btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(CreditInventoryResult.this,  FinancialConsultation.class);
				CreditInventoryResult.this.startActivity(intent);
				finish();
			}
		});
		/**
         * 
         * 接收上一个Activity传来的值
         */
       /* Intent intent=getIntent();
        reslut=intent.getStringArrayExtra("result");
        disc=intent.getStringExtra("disc");
        type=intent.getStringExtra("type");
		for(String g:reslut)
		{
			System.out.println(g+"=========支出中============>>>>>>>");
		}
		txt_M=(EditText)findViewById(R.id.txt_M);
		txt_M.setText(disc) ;  */
		
		
	                
	        ArrayList<HashMap<String,String>> accoutList = new ArrayList<HashMap<String,String>>();
	                
	        HashMap<String,String> acclist1 = new HashMap<String,String>();
	        HashMap<String,String> acclist2 = new HashMap<String,String>();
	        HashMap<String,String> acclist3 = new HashMap<String,String>();
	        HashMap<String,String> acclist4 = new HashMap<String,String>();
	       // HashMap<String,String> acclist5 = new HashMap<String,String>();
	               	        
	        acclist1.put("account_list", "交易日期：");
	        acclist1.put("account_list_info", date);
	        acclist2.put("account_list", "来帐账户：");
	        acclist2.put("account_list_info",  name);
	        acclist3.put("account_list", "交易类型");
	        acclist3.put("account_list_info",  jiao);
	        acclist4.put("account_list", "余额：");
	        acclist4.put("account_list_info", amount);
	        
	        
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
