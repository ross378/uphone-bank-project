package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class TransferAccActivity extends Activity {
	private Button btn_ok = null;
	private Button btn_cancle = null;

	private RadioGroup rg_acc = null;
	private RadioButton rb_acc1 = null;
	private RadioButton rb_acc2 = null;
	private RadioButton rb_acc3 = null;
	private TextView tv_tratyp = null;
	private EditText inpt_num = null;
	private EditText trans_amount = null;
	private EditText psswrd = null;
	
	private String SelectedAcc = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transfer_ph);
		

		
		btn_ok = (Button)findViewById(R.id.btn_ph_ok);
        btn_cancle = (Button)findViewById(R.id.btn_ph_cancle);

        tv_tratyp = (TextView)findViewById(R.id.tv_trans_inptype);
        inpt_num = (EditText)findViewById(R.id.input_ph_num);
        trans_amount = (EditText)findViewById(R.id.input_ph_amount);
        psswrd = (EditText)findViewById(R.id.input_ph_psswrd);
        
        btn_ok.setOnClickListener(new BtnOkCL());
        btn_cancle.setOnClickListener(new BtnCancleCL());

        rg_acc = (RadioGroup)findViewById(R.id.rg_transph_acc);
        rb_acc1 = (RadioButton)findViewById(R.id.rb_trph_acc1);
        rb_acc2 = (RadioButton)findViewById(R.id.rb_trph_acc2);
        rb_acc3 = (RadioButton)findViewById(R.id.rb_trph_acc3);
        
        tv_tratyp.setText("请输入转入账号 ");
        
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
		tvClassSecond.setText("转到签约账号>");
		tvClassSecond.setVisibility(View.VISIBLE);
        
        rg_acc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(rb_acc1.getId() == checkedId){
					SelectedAcc = rb_acc1.getText().toString();
				}
				else if(rb_acc2.getId() == checkedId){
					SelectedAcc = rb_acc2.getText().toString();
				}
				else if(rb_acc3.getId() == checkedId){
					SelectedAcc = rb_acc3.getText().toString();
				}
			}
		});
    }
	class BtnOkCL implements OnClickListener{
    	public void onClick(View v) {
    			String InputNum = inpt_num.getText().toString();
    			String Amount = trans_amount.getText().toString();
    			String PassWord = psswrd.getText().toString();
			
				Intent trans_intent = new Intent();
				trans_intent.putExtra("password", PassWord);
				trans_intent.putExtra("amount", Amount);
				trans_intent.putExtra("inputnum", InputNum);
				trans_intent.putExtra("selectedacc", SelectedAcc);
				trans_intent.setClass(TransferAccActivity.this, TransAccConfirm.class);
				startActivity(trans_intent);
				}
			}
    class BtnCancleCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transcancle_intent = new Intent();
    		transcancle_intent.putExtra("flag", "failed");
    		transcancle_intent.putExtra("info", "The transfer is canceled");
    		transcancle_intent.setClass(TransferAccActivity.this, TransferMain.class);
    		startActivity(transcancle_intent);
    	}
    }
    class BtnMainCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transmain_intent = new Intent();
    		transmain_intent.putExtra("flag", "failed");
    		transmain_intent.putExtra("info", "The transfer is canceled");
    		transmain_intent.setClass(TransferAccActivity.this, TransResult.class);
    		startActivity(transmain_intent);
    	}
    }
    class BtnHelpCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransferAccActivity.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
    class BtnNowCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransferAccActivity.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
}