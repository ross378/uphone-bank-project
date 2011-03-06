package com.ultrawise.android.bank.view.account_query;

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

public class inventory extends Activity {

	Intent intent = new Intent();
	Button timeStart, timeOver;
	String sql = null;
	DatePicker d;
	static String start_time = "开始时间";
	static String end_time = "结束时间";;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.inventory);

		TextView tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(inventory.this, ABankMain.class);
				inventory.this.startActivity(intent);
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
	        	 * 
	        	 * @author gsm
	        	 * @param 功能号
	        	 * @return 返回卡的类型
	        	 */
				List<String> result=QuerySever.connectHttp("021", null);
				for(String g:result)
				{
				System.out.println("服务器上取得所需要的数据-明文======"+g.toString());	
				}
				
				
				String[] arrResult=new String[result.size()];
				for(int i=0;i<result.size();i++)
				{   
					 arrResult[i]= result.get(i);
				}
				intent.putExtra("result", arrResult);
				
				intent.setClass(inventory.this,AccountQuery.class);
				inventory.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		TextView tvClassSecond1 = (TextView) this.findViewById(R.id.class_third);
		tvClassSecond1.setText("明细查询");
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
				intent.setClass(inventory.this, ABankMain.class);
				inventory.this.startActivity(intent);
				finish();
			}
		});
		
		ImageView btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(inventory.this,  FinancialConsultation.class);
				inventory.this.startActivity(intent);
				finish();
			}
		});
		
		
		timeStart = (Button) findViewById(R.id.timechange_from);
		timeStart.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub;

				Intent intent = new Intent();
				intent.setClass(inventory.this, AccountQueryResult.class);
				intent.putExtra("start", "start");
				inventory.this.startActivity(intent);
			}
		});
		timeOver = (Button) findViewById(R.id.timechange_to);
		timeOver.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(inventory.this, AccountQueryResult.class);
				intent.putExtra("end", "end");
				inventory.this.startActivity(intent);
			}
		});

		Button run = (Button) findViewById(R.id.Query_Run);
		run.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent=new Intent();
				System.out.println("开始"+timeStart.getText());
				System.out.println("结束"+timeOver.getText());
				intent.setClass(inventory.this, Inventorylist.class);
				startActivity(intent);
			}
		});
		Intent intent = this.getIntent();
        if(intent.hasExtra("start")){
        	String[] date = intent.getStringArrayExtra("start");
        	start_time = date[0]+"-"+date[1]+"-"+date[2];
        }
        timeStart.setText(start_time);
        if(intent.hasExtra("end")){
        	String[] date = intent.getStringArrayExtra("end");
        	end_time = date[0]+"-"+date[1]+"-"+date[2];
        }
        timeOver.setText(end_time);
	}
}
