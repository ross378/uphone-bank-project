package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class PaymentManage extends Activity {
	
	Intent payment_intent = new Intent();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_manage);
        
        Button btn_paymanage_ok = (Button)findViewById(R.id.btn_paymana_ok);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
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
		tvClassSecond.setText("缴费项目管理");
		tvClassSecond.setVisibility(View.VISIBLE);
        
        btn_paymanage_ok.setOnClickListener(new BtnManaOK());
	}

	class BtnManaOK implements OnClickListener{
		public void onClick(View v){
			Intent btnok_intent = new Intent();
			btnok_intent.putExtra("flag", "设置成功");
			btnok_intent.putExtra("info", "缴费项目管理设置成功");
			btnok_intent.setClass(PaymentManage.this, PaymentResult.class);
			PaymentManage.this.startActivity(btnok_intent);
		}
	}
}