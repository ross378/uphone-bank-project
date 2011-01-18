package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

public class PaymentHistory extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_hisdtl_ch);
        
        DatePicker datepicker = (DatePicker)findViewById(R.id.datepick_payment_hissta);
        DatePicker datepicker2 = (DatePicker)findViewById(R.id.datepick_payment_hisend);
        Button btn_payhis_ok = (Button)findViewById(R.id.btn_payhisck_ok);
        Button btn_payhis_cancle = (Button)findViewById(R.id.btn_payhisck_cancle);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("手机缴费>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("选择时间>");
		tvClassSecond.setVisibility(View.VISIBLE);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        btn_payhis_ok.setOnClickListener(new BtnHischOKCL());
        btn_payhis_cancle.setOnClickListener(new BtnHischCancleCL());
	}
	class BtnHischOKCL implements OnClickListener{
		public void onClick(View v){
			Intent payhisch_intent = new Intent();
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
}