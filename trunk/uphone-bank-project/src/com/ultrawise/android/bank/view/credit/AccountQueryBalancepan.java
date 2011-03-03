package com.ultrawise.android.bank.view.credit;
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

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

public class AccountQueryBalancepan extends ListActivity {
	private ImageView btnReturn=null;
	private ImageView btnCoustom=null;
	private ImageView btnMain=null;
	private TextView  tvClassFirst=null;
	private TextView acc1=null;
	private TextView acc2=null;
	private TextView type1=null;
	private TextView type2=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.querybalancepa);
        tvClassFirst = (TextView) this.findViewById(R.id.class_first);
        tvClassFirst.setText("首页>信用卡>");
        tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			     Intent intent = new Intent();
				 intent = AccountQueryBalancepan.this.getIntent();
				 intent.setClass(AccountQueryBalancepan.this, CreditView.class);
				 AccountQueryBalancepan.this.startActivity(intent);
			}
		});
	tvClassFirst.setVisibility(View.VISIBLE);
		
		//返回键设定
		btnReturn= (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
	   
		    TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
	        tvClassSecond.setText("账户查询");
	        tvClassSecond.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					 Intent intent = new Intent();
					 intent = AccountQueryBalancepan.this.getIntent();
					 intent.setClass(AccountQueryBalancepan.this,CreditQuery .class);
					 AccountQueryBalancepan.this.startActivity(intent);
				}
			});
	        tvClassSecond.setVisibility(View.VISIBLE);
		
//		    acc1=(TextView)findViewById(R.id.acc1);
//		    acc1.setText("账户：");
//		    acc2=(TextView)findViewById(R.id.acc2);
//		    acc2.setText("1234578xxxx");
//		    type1=(TextView)findViewById(R.id.type1);
//		    type1.setText("账户类型：");
//		    type2=(TextView)findViewById(R.id.type2);
//		    type2.setText("定期存储(零存整取)");
	        
	        ArrayList<HashMap<String,String>> accoutList = new ArrayList<HashMap<String,String>>();
	        
	         
	        HashMap<String,String> acclist1 = new HashMap<String,String>();
	        HashMap<String,String> acclist2 = new HashMap<String,String>();
	        HashMap<String,String> acclist3 = new HashMap<String,String>();
	        HashMap<String,String> acclist4 = new HashMap<String,String>();
	        HashMap<String,String> acclist5 = new HashMap<String,String>();
	        HashMap<String,String> acclist6 = new HashMap<String,String>();
	        HashMap<String,String> acclist7 = new HashMap<String,String>();
	        
	        acclist1.put("account_list", "账户：");
	        acclist1.put("account_list_info", "12345678xx");
	        acclist2.put("account_list", "账户类型：");
	        acclist2.put("account_list_info", "定期存储（零存整取）");
	        acclist3.put("account_list", "币种：");
	        acclist3.put("account_list_info", "人民币");
	        acclist4.put("account_list", "余额：");
	        acclist4.put("account_list_info", "30000");
	        acclist5.put("account_list", "存期：");
	        acclist5.put("account_list_info", "三个月");
	        acclist6.put("account_list", "起息：");
	        acclist6.put("account_list_info", "2011.12.15");
	        acclist7.put("account_list", "利率");
	        acclist7.put("account_list_info", "2.25%");
	        
	        accoutList.add(acclist1);
	        accoutList.add(acclist2);
	        accoutList.add(acclist3);
	        accoutList.add(acclist4);
	        accoutList.add(acclist5);
	        accoutList.add(acclist6);
	        accoutList.add(acclist7);
	        
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.querybalancepan, new String[] {
					"account_list", "account_list_info" }, new int[] { R.id.txtViewpan, R.id.txtViewpan1 } );
	        this.setListAdapter(MainListAdapter);
	        
	        //设置底部按钮
	    	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
	    	btnCoustom.setOnClickListener(new OnClickListener() {
	    		public void onClick(View v) {
	    			Intent intent=new Intent();
	    			intent.setClass(AccountQueryBalancepan.this, ABankMain.class);
	    			AccountQueryBalancepan.this.startActivity(intent);
	    			finish();
	    		}
	    	});
	    	
	    	btnMain = (ImageView) this.findViewById(R.id.btnHelper);
	    	btnMain.setOnClickListener(new OnClickListener() {

	    		public void onClick(View v) {
	    			Intent intent=new Intent();
	    			intent.setClass(AccountQueryBalancepan.this,  FinancialConsultation.class);
	    			AccountQueryBalancepan.this.startActivity(intent);
	    			finish();
	    		}
	    	});
	}
}
