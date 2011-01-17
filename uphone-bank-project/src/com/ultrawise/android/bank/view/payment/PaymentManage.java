package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class PaymentManage extends Activity {
	
	private Button btn_main = null;
	private Button btn_help = null;
	private Button btn_now = null;
	
	Intent payment_intent = new Intent();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_manage);
        
        Button btn_paymanage_ok = (Button)findViewById(R.id.btn_paymana_ok);
        
        btn_main = (Button)findViewById(R.id.btnMain);
        btn_help = (Button)findViewById(R.id.btnHelper);
        btn_now = (Button)findViewById(R.id.btnCoustom);
        
        btn_help = (Button)this.findViewById(R.id.btnCoustom);
        btn_help.setText("手机缴费");
        btn_help.setVisibility(View.VISIBLE);
        
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