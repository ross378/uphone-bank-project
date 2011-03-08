package com.ultrawise.android.bank.view.account_query;

import java.sql.Date;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.QuerySever;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.payment.PaymentHistory;
import com.ultrawise.android.bank.view.payment.PaymentSetTimeDialog;
import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AccountFrom extends Activity {

	Button timeStart, timeOver;
	String sql = null;
	DatePicker d;
	static String start_time = "开始时间";
	static String end_time = "结束时间";
	private static  String nomber=null;
	private static  String type=null;
	private String start=null;
	private String end=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		/**
		 * 
		 * 接收从上一个Activity传递数据
		 */
		Intent intent_type_nomber=getIntent();
		nomber=intent_type_nomber.getStringExtra("nomber");
		type=intent_type_nomber.getStringExtra("type");
		
		
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.inventory);
		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AccountFrom.this, ABankMain.class);
				AccountFrom.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassSecond = (TextView) this
				.findViewById(R.id.class_second);
		tvClassSecond.setText("账户查询>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
				/**
	        	 * 从服务器上取得所需要的数据
	        	 * @author gsm
	        	 * @param 功能号 021
	        	 * @return 返回卡的类型
	        	 */
				List<String> result=QuerySever.connectHttp("021", null);
				String[] arrResult=new String[result.size()];
				for(int i=0;i<result.size();i++)
				{   
					 arrResult[i]= result.get(i);
				}
				intent.putExtra("result", arrResult);
				intent.setClass(AccountFrom.this,AccountQuery.class);
				AccountFrom.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		TextView tvClassSecond1 = (TextView) this.findViewById(R.id.class_third);
		tvClassSecond1.setText("来账查询");
		tvClassSecond1.setVisibility(View.VISIBLE);

		// 设定返回按钮
		ImageView btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				onBackPressed();
				finish();
			}
		});
		
		  //设置底部按钮
		ImageView	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(AccountFrom.this, ABankMain.class);
				AccountFrom.this.startActivity(intent);
				finish();
			}
		});
		
		ImageView btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(AccountFrom.this,  FinancialConsultation.class);
				AccountFrom.this.startActivity(intent);
				finish();
			}
		});
		
		/**
		 * 
		 * 起始时间按钮
		 */
		
		timeStart = (Button) findViewById(R.id.timechange_from);
		timeStart.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent();
				intent.putExtra("start", "start");
				
				/**
				 * 第一次传
				 */
				intent.putExtra("nomber", nomber);
				intent.putExtra("type",type);
				
				
				intent.setClass(AccountFrom.this, Query_Settime.class);
				AccountFrom.this.startActivity(intent);
			}
		});
		timeOver = (Button) findViewById(R.id.timechange_to);
		timeOver.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.putExtra("end", "end");
				/**
				 * 第二次传
				 */
				intent.putExtra("nomber", nomber);
				intent.putExtra("type",type);
				
				intent.setClass(AccountFrom.this, Query_Settime.class);
				AccountFrom.this.startActivity(intent);
			}
		});
		Button run = (Button) findViewById(R.id.Query_Run);
		
		
		
		
		run.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				start=timeStart.getText().toString().trim();
			    end=timeOver.getText().toString().trim();
				
			    
			    if(!start_time.equals("开始时间")&&!end_time.equals("结束时间")){
			    	String time1="2011-3-8";
					String time2="2011-3-10";
					Date date1 = Date.valueOf(time1);
					Date date2 = Date.valueOf(time2);
					Date date3 = Date.valueOf(start);
					Date date4 = Date.valueOf(end);

					if(date3.before(date1)&&date4.after(date2)){
			     Intent intent=new Intent();
				/**
	        	 * 从服务器上取得所需要时间段的数据
	        	 * 
	        	 * @author gsm
	        	 * @param 功能号 024
	        	 * @return 返回卡的类型
	        	 */
				String[] str=new String[]{nomber+"#"+start+"#",end};
				List<String> result=QuerySever.connectHttp("024", str);
				String[] arrResult=new String[result.size()];
				for(int i=0;i<result.size();i++)
				{   
					System.out.println(result.get(i));
					 arrResult[i]= result.get(i);
				}
				
				if(result.size()==1){
					System.out.println("记录为空！");
					Intent btnok_intent = new Intent();
	       		    btnok_intent.putExtra("flag", "失败提示");
	       			btnok_intent.putExtra("info", "本账号没有记录");
	       			btnok_intent.setClass(AccountFrom.this,FailOk.class);
	       			AccountFrom.this.startActivity(btnok_intent);
	       			return;
				}else{
				
				/**
				 * 
				 * 包装intent传递数据
				 */
				intent.putExtra("result", arrResult);
				intent.putExtra("type", type);
				intent.putExtra("nomber", nomber);
				intent.putExtra("start", start);
				intent.putExtra("end", end);
				
				System.out.println("前一个avtivity出来"+type+"======"+nomber);
				System.out.println("开始"+start);
				System.out.println("结束"+end);
				
				intent.setClass(AccountFrom.this, AccountQueryEinnahme.class);
				AccountFrom.this.startActivity(intent);
				}
			   }else{
				   
				    Intent btnok_intent = new Intent();
	       		    btnok_intent.putExtra("flag", "失败提示");
	       			btnok_intent.putExtra("info", "本时间段没有记录");
	       			btnok_intent.setClass(AccountFrom.this,FailOk.class);
	       			AccountFrom.this.startActivity(btnok_intent);
			   }
			  }
			    else{
			    Intent btnok_intent = new Intent();
       		    btnok_intent.putExtra("flag", "失败提示");
       			btnok_intent.putExtra("info", "请输入开始时间和结束时间");
       			btnok_intent.setClass(AccountFrom.this,FailOk.class);
       			AccountFrom.this.startActivity(btnok_intent);
			    }
			}
		});
		
		Intent intent = this.getIntent();
        if(intent.hasExtra("start")){
        	String[] date = intent.getStringArrayExtra("start");
        	nomber=date[3];
        	type=date[4];
        	
        	start_time = date[0]+"-"+date[1]+"-"+date[2];
        	
        }
        timeStart.setText(start_time);
        
        if(intent.hasExtra("end")){
        	String[] date = intent.getStringArrayExtra("end");
        	end_time = date[0]+"-"+date[1]+"-"+date[2];
        	
        	nomber=date[3];
        	type=date[4];
        }
        timeOver.setText(end_time);
	}
}
