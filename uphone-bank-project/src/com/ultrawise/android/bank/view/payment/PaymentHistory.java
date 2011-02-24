package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class PaymentHistory extends Activity {
	static String start_time = "起始时间";
	static String end_time = "终止时间";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_hisdtl_ch);
       
        Button btn_payhis_ok = (Button)findViewById(R.id.btn_payhisck_ok);
        Button btn_payhis_starttime = (Button)findViewById(R.id.tv_payhis_ckstartbut);
        btn_payhis_starttime.setOnClickListener(new SetStartTime());
        Button btn_payhis_endtime=(Button)findViewById(R.id.tv_payhis_ckendendbut);
        btn_payhis_endtime.setOnClickListener(new SetEndTime());
        
        Intent intent = this.getIntent();
        if(intent.hasExtra("start")){
        	String[] date = intent.getStringArrayExtra("start");
        	start_time = date[0]+"-"+date[1]+"-"+date[2];
        }
        btn_payhis_starttime.setText(start_time);
        if(intent.hasExtra("end")){
        	String[] date = intent.getStringArrayExtra("end");
        	end_time = date[0]+"-"+date[1]+"-"+date[2];
        }
        btn_payhis_endtime.setText(end_time);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("自助缴费>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent();
				 intent.setClass(PaymentHistory.this, ABankMain.class);
				 PaymentHistory.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("历史缴费记录");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent();
				 intent.setClass(PaymentHistory.this, PaymentMain.class);
				 PaymentHistory.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        btn_payhis_ok.setOnClickListener(new BtnHischOKCL());
        //btn_payhis_cancle.setOnClickListener(new BtnHischCancleCL());
	}
	class BtnHischOKCL implements OnClickListener{
		public void onClick(View v){
		
			Intent payhisch_intent = new Intent();
			if(start_time.equals("起始时间")){
				payhisch_intent.putExtra("start_time", "2011-1-1");
			}else{
				payhisch_intent.putExtra("start_time", start_time);
			}
			if(end_time.equals("起始时间")){
				payhisch_intent.putExtra("end_time", "2011-1-1");
			}else{
				payhisch_intent.putExtra("end_time", end_time);
			}
			
			payhisch_intent.setClass(PaymentHistory.this, PaymentLastMonth.class);
			PaymentHistory.this.startActivity(payhisch_intent);
		}
	}
	class BtnHischCancleCL implements OnClickListener{
		public void onClick(View v){
			Intent payhisch_intent = new Intent();
			payhisch_intent.setClass(PaymentHistory.this, PaymentMain.class);
			PaymentHistory.this.startActivity(payhisch_intent);
		}
	}
	class SetStartTime implements OnClickListener{

		public void onClick(View v) {
			Intent set_start_time = new Intent();
			set_start_time.putExtra("start", "start");
			set_start_time.setClass(PaymentHistory.this, PaymentSetTimeDialog.class);
			PaymentHistory.this.startActivity(set_start_time);
		}
	}
	class SetEndTime implements OnClickListener{

		public void onClick(View v) {
			Intent set_end_time = new Intent();
			set_end_time.putExtra("end", "end");
			set_end_time.setClass(PaymentHistory.this, PaymentSetTimeDialog.class);
			PaymentHistory.this.startActivity(set_end_time);
		}
		
	}
}