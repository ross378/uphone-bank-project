package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TransPhConfirm extends Activity {
    /** Called when the activity is first created. */
	Intent transcon_intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_ph_confirm);
        
        Button btnok = (Button)findViewById(R.id.btn_cfm_ok);
    	Button btncancle = (Button)findViewById(R.id.btn_cfm_cancle);
        TextView tvtransacc = (TextView)findViewById(R.id.tv_transcon_acc);
        TextView tvtransbal = (TextView)findViewById(R.id.tv_transcon_balance);
        TextView tvtransinput = (TextView)findViewById(R.id.tv_transcon_inputnum);
        TextView tvtransamount = (TextView)findViewById(R.id.tv_transcon_amount);
        
        transcon_intent = getIntent();
        String transacc = transcon_intent.getStringExtra("selectedacc");
        String transbal = transcon_intent.getStringExtra("password");
        String transpas = transcon_intent.getStringExtra("password");
        String transinput = transcon_intent.getStringExtra("inputnum");
        String transamount = transcon_intent.getStringExtra("amount");
        
        tvtransacc.setText(transacc);
        tvtransinput.setText(transinput);
        tvtransamount.setText(transamount);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.VISIBLE);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("转账汇款>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// intent = QueryAccount.this.getIntent();
				// intent.setClass(QueryAccount.this, AccountManagement.class);
				// QueryAccount.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("转到手机账号>");
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThree = (TextView)this.findViewById(R.id.class_third);
		tvClassThree.setText("确认转账");
		tvClassThree.setVisibility(View.VISIBLE);
        
        btnok.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Intent transconok_intent = new Intent();
        		transconok_intent.putExtra("flag", "success");
        		transconok_intent.putExtra("info", "The transfered is successful");
        		transconok_intent.setClass(TransPhConfirm.this, TransResult.class);
        		startActivity(transconok_intent);
        	}
        });
        btncancle.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Intent transconcancle_intent = new Intent();
        		transconcancle_intent.putExtra("flag", "failed");
        		transconcancle_intent.putExtra("info", "The transfer is canceled");
        		transconcancle_intent.setClass(TransPhConfirm.this, TransResult.class);
        		startActivity(transconcancle_intent);
        	}
        });
    }
    class BtnMainCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transmain_intent = new Intent();
    		transmain_intent.putExtra("flag", "failed");
    		transmain_intent.putExtra("info", "The transfer is canceled");
    		transmain_intent.setClass(TransPhConfirm.this, TransResult.class);
    		startActivity(transmain_intent);
    	}
    }
    class BtnHelpCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransPhConfirm.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
    class BtnNowCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransPhConfirm.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
}