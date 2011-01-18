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
import android.widget.TimePicker;
import android.app.TimePickerDialog;

public class PaymentHistory extends Activity {
	
	private Button btn_main = null;
	private Button btn_help = null;
	private Button btn_now = null;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_hisdtl_ch);
                
        btn_help = (Button)this.findViewById(R.id.btnCoustom);
        btn_help.setText("账户信息");
        btn_help.setVisibility(View.VISIBLE);
        
        DatePicker datepicker = (DatePicker)findViewById(R.id.datepick_payment_hissta);
        DatePicker datepicker2 = (DatePicker)findViewById(R.id.datepick_payment_hisend);
        Button btn_payhis_ok = (Button)findViewById(R.id.btn_payhisck_ok);
        Button btn_payhis_cancle = (Button)findViewById(R.id.btn_payhisck_cancle);
        
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