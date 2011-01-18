package com.ultrawise.android.bank.view.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TransferMain extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_main);
        
        Button btn_trans_ph = (Button)findViewById(R.id.btn_trans_main_ph);
        Button btn_trans_acc = (Button)findViewById(R.id.btn_trans_main_acc);
        
        btn_trans_ph.setOnClickListener(new BtnTransMainPh());
        btn_trans_acc.setOnClickListener(new BtnTransMainAcc());
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("转账汇款");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
        
	}
	class BtnTransMainPh implements OnClickListener{
		public void onClick(View v){
			Intent trans_main_ph = new Intent();
			trans_main_ph.setClass(TransferMain.this, TransferActivity.class);
			startActivity(trans_main_ph);
		}
	}
	class BtnTransMainAcc implements OnClickListener{
		public void onClick(View v){
			Intent trans_main_acc = new Intent();
			trans_main_acc.setClass(TransferMain.this, TransferAccActivity.class);
			startActivity(trans_main_acc);
		}
	}
	class BtnMainCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transmain_intent = new Intent();
    		transmain_intent.putExtra("flag", "failed");
    		transmain_intent.putExtra("info", "The transfer is canceled");
    		transmain_intent.setClass(TransferMain.this, TransResult.class);
    		startActivity(transmain_intent);
    	}
    }
    class BtnHelpCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransferMain.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
    class BtnNowCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransferMain.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
}