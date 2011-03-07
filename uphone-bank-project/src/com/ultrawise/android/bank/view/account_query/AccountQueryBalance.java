package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.ultrawise.android.bank.consum_webservices.QuerySever;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.payment.PaymentDefAcc;
import com.ultrawise.android.bank.view.payment.PaymentHistory;
import com.ultrawise.android.bank.view.payment.PaymentLastMonth;
import com.ultrawise.android.bank.view.payment.PaymentMain;
import com.ultrawise.android.bank.view.payment.PaymentManage;
import com.ultrawise.android.bank.view.payment.PaymentPend;
import com.ultrawise.android.bank.view.payment.PaymentSelfService;
import com.ultrawise.android.bank.view.transfer.R;

public class AccountQueryBalance extends ListActivity {
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
        setContentView(R.layout.account_querytype_txt);
        tvClassFirst = (TextView) this.findViewById(R.id.class_first);
        tvClassFirst.setText("首页>");
        tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			     Intent intent = new Intent();
				 intent = AccountQueryBalance.this.getIntent();
				 intent.setClass(AccountQueryBalance.this, ABankMain.class);
				 AccountQueryBalance.this.startActivity(intent);
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
	        tvClassSecond.setText("账户查询>");
	        tvClassSecond.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					 Intent intent = new Intent();
					 intent = AccountQueryBalance.this.getIntent();
					 intent.setClass(AccountQueryBalance.this, AccountQueryType.class);
					 AccountQueryBalance.this.startActivity(intent);
				}
			});
	        tvClassSecond.setVisibility(View.VISIBLE);
	        
		    TextView tvClassthird = (TextView)this.findViewById(R.id.class_third);
		    tvClassthird.setText("账户信息及余额查询");
		    tvClassthird.setVisibility(View.VISIBLE);
	        ArrayList<HashMap<String,String>> accoutList = new ArrayList<HashMap<String,String>>();
	        HashMap<String,String> acclist1 = new HashMap<String,String>();
	        HashMap<String,String> acclist2 = new HashMap<String,String>();
	        HashMap<String,String> acclist3 = new HashMap<String,String>();
	        HashMap<String,String> acclist4 = new HashMap<String,String>();
	        HashMap<String,String> acclist5 = new HashMap<String,String>();
	        HashMap<String,String> acclist6 = new HashMap<String,String>();
	        HashMap<String,String> acclist7 = new HashMap<String,String>();
	       
	        
	        Intent intent=getIntent();
	        String nomber=intent.getStringExtra("nomber");
	        String type=intent.getStringExtra("type");
	      
	        /**
			 * 从服务器上活动数据
			 * 
			 * @param 参数 功能号023, 账号nomber
			 * @return返回当前账号的信息
			 */
			String[] str=new String[]{nomber};
			List<String> result=QuerySever.connectHttp("023", str);
			for(int i=0;i<result.size();i++)
			{
			System.out.println("023---023---023==="+result.get(i));	
			}
	        
	        
	        String RenBi=result.get(12).toString().trim();
	        String YuE=result.get(11).toString().trim();
	        String Time=result.get(13).toString().trim();
	        String Mouth=result.get(14).toString().trim();
	        String LiL=result.get(15).toString().trim();
	        
	        
	        
	        acclist1.put("account_list", "账户：");
	        acclist1.put("account_list_info", nomber);
	        acclist2.put("account_list", "账户类型：");
	        acclist2.put("account_list_info", type);
	        acclist3.put("account_list", "币种：");
	        acclist3.put("account_list_info", RenBi);
	        acclist4.put("account_list","余额:" );
	        acclist4.put("account_list_info", YuE);
	        acclist5.put("account_list", "存期：");
	        acclist5.put("account_list_info", Time);
	        acclist6.put("account_list", "起息月：");
	        acclist6.put("account_list_info", Mouth);
	        acclist7.put("account_list", "利率");
	        acclist7.put("account_list_info", LiL);
	        
	        accoutList.add(acclist1);
	        accoutList.add(acclist2);
	        accoutList.add(acclist3);
	        accoutList.add(acclist4);
	        accoutList.add(acclist5);
	        accoutList.add(acclist6);
	        accoutList.add(acclist7);
	        
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.account_quer_list2, new String[] {
					"account_list", "account_list_info" }, new int[] { R.id.txtView1, R.id.txtView2 } );
	        this.setListAdapter(MainListAdapter);
	        
	        //设置底部按钮
	    	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
	    	btnCoustom.setOnClickListener(new OnClickListener() {
	    		public void onClick(View v) {
	    			Intent intent=new Intent();
	    			intent.setClass(AccountQueryBalance.this, ABankMain.class);
	    			AccountQueryBalance.this.startActivity(intent);
	    			finish();
	    		}
	    	});
	    	
	    	btnMain = (ImageView) this.findViewById(R.id.btnHelper);
	    	btnMain.setOnClickListener(new OnClickListener() {

	    		public void onClick(View v) {
	    			Intent intent=new Intent();
	    			intent.setClass(AccountQueryBalance.this,  FinancialConsultation.class);
	    			AccountQueryBalance.this.startActivity(intent);
	    			finish();
	    		}
	    	});
	}
}