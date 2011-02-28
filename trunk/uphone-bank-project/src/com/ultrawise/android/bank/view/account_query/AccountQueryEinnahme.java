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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ultrawise.android.bank.view.payment.PaymentDefAcc;
import com.ultrawise.android.bank.view.payment.PaymentHistory;
import com.ultrawise.android.bank.view.payment.PaymentLastMonth;
import com.ultrawise.android.bank.view.payment.PaymentMain;
import com.ultrawise.android.bank.view.payment.PaymentManage;
import com.ultrawise.android.bank.view.payment.PaymentPend;
import com.ultrawise.android.bank.view.payment.PaymentSelfService;
import com.ultrawise.android.bank.view.transfer.R;

public class AccountQueryEinnahme extends ListActivity {
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
		tvClassFirst.setText("首页>");
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
	        tvClassSecond.setText("账户查询");
//	        tvClassSecond.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					// intent = QueryAccount.this.getIntent();
//					// intent.setClass(QueryAccount.this, AccountManagement.class);
//					// QueryAccount.this.startActivity(intent);
//				}
//			});
	        tvClassSecond.setVisibility(View.VISIBLE);
		
		    acc1=(TextView)findViewById(R.id.acc1);
		    acc1.setText("定期账户012345678在20101123到20100111"+"\n"+"之间的来帐记录如下：");
		    
		    ArrayList<HashMap<String,Object>> accoutList = new ArrayList<HashMap<String,Object>>();
	        
	        HashMap<String,Object> acclist1 = new HashMap<String,Object>();
	        HashMap<String,Object> acclist2 = new HashMap<String,Object>();
	        HashMap<String,Object> acclist3 = new HashMap<String,Object>();
	        
	        acclist1.put("txtView1","20101123");
	        acclist1.put("txtView2", "转账");
	        acclist1.put("txtView3",R.drawable.account2);
	        
	        
	        
	        acclist2.put("txtView1","20101124");
	        acclist2.put("txtView2", "汇款");
	        acclist2.put("txtView3", R.drawable.account2);
	        
	        acclist3.put("txtView1","20101125");
	        acclist3.put("txtView2", "转账");
	        acclist3.put("txtView3",R.drawable.account2);
	        
	        accoutList.add(acclist1);
	        accoutList.add(acclist2);
	        accoutList.add(acclist3);
	        
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.account_quer_list2, new String[] {
					"txtView1", "txtView2" ,"txtView3",}, new int[] { R.id.txtView1, R.id.txtView2 ,R.id.txtView3} );
	        this.setListAdapter(MainListAdapter);
	        
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.VISIBLE);
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if(id==0){//账户信息及余额查询
			Intent payment_intent = new Intent();
			payment_intent.setClass(AccountQueryEinnahme.this, AccountQueryDetail.class);
			AccountQueryEinnahme.this.startActivity(payment_intent);
		}else if(id==1){//账户明细查询
			Intent payment_intent = new Intent();
			payment_intent.setClass(AccountQueryEinnahme.this, AccountQueryDetail.class);
			AccountQueryEinnahme.this.startActivity(payment_intent);
		}else if(id==2){//账户来帐查询
			Intent payment_intent = new Intent();
			payment_intent.setClass(AccountQueryEinnahme.this, AccountQueryDetail.class);
			AccountQueryEinnahme.this.startActivity(payment_intent);
		}
	}
}