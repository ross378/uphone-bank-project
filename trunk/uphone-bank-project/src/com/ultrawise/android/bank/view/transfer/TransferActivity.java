package com.ultrawise.android.bank.view.transfer;

import com.ultrawise.android.bank.view.transfer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TransferActivity extends Activity {
	private Button btn_ok = null;
	private Button btn_cancle = null;
	private Button btn_main = null;
	private Button btn_help = null;
	private Button btn_now = null;
	private RadioGroup rg_acc = null;
	private RadioButton rb_acc1 = null;
	private RadioButton rb_acc2 = null;
	private RadioButton rb_acc3 = null;
	private EditText inpt_num = null;
	private EditText trans_amount = null;
	private EditText psswrd = null;
	
	private String SelectedAcc = null;
	private String InputNum = null;
	private String Amount = null;
	private String PassWord = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_ph);
        
        btn_ok = (Button)findViewById(R.id.btn_ph_ok);
        btn_cancle = (Button)findViewById(R.id.btn_ph_cancle);
        btn_main = (Button)findViewById(R.id.btnMain);
        btn_help = (Button)findViewById(R.id.btnHelper);
        btn_now = (Button)findViewById(R.id.btnCoustom);
        
        btn_ok.setOnClickListener(new BtnOkCL());
        //btn_cancle.setOnClickListener(new BtnCancleCL());
        //btn_main.setOnClickListener(new BtnMainCL());
        //btn_help.setOnClickListener(new BtnHelpCL());
        //btn_now.setOnClickListener(new BtnCustomCL());
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
			InputNum = inpt_num.getText().toString();
			Amount = trans_amount.getText().toString();
			PassWord = psswrd.getText().toString();
			if(PassWord == "123456"){
				Intent trans_intent = new Intent();
				trans_intent.putExtra("password", PassWord);
				if(Amount != null){
					trans_intent.putExtra("amount", Amount);
					if(InputNum != null){
						trans_intent.putExtra("inputnum", InputNum);
						if(SelectedAcc != null){
							trans_intent.putExtra("selectedacc", SelectedAcc);
							trans_intent.setClass(TransferActivity.this, TransPhConfirm.class);
							startActivity(trans_intent);
						}
						else{
							Intent transfail_intent = new Intent();
							transfail_intent.putExtra("info", "The selected account is not correct!");
							transfail_intent.putExtra("flag", "Transfer failure");
							transfail_intent.setClass(TransferActivity.this, TransInfo.class);
							startActivity(transfail_intent);
						}
					}
					else{
						Intent transfail_intent = new Intent();
						transfail_intent.putExtra("info", "The input number is not correct!");
						transfail_intent.putExtra("flag", "Transfer failure");
						transfail_intent.setClass(TransferActivity.this, TransInfo.class);
						startActivity(transfail_intent);
					}
				}
				else{
					Intent transfail_intent = new Intent();
					transfail_intent.putExtra("info", "The input amount is not correct!");
					transfail_intent.putExtra("flag", "Transfer failure");
					transfail_intent.setClass(TransferActivity.this, TransInfo.class);
					startActivity(transfail_intent);										
				}
			}
			else {
				Intent transfail_intent = new Intent();
				transfail_intent.putExtra("info", "The password is not correct!");
				transfail_intent.putExtra("flag", "Transfer failure");
				transfail_intent.setClass(TransferActivity.this, TransInfo.class);
				startActivity(transfail_intent);				
			}
			
		}
    	
    }
    
}