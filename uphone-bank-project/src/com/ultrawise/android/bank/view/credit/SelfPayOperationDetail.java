package com.ultrawise.android.bank.view.credit;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelfPayOperationDetail extends Activity {
	private Button selfPayOperationDetailButton=null;
	private TextView selfPayBalText=null;
	private TextView selfPayOperationPayActText=null;//还款账户
	private TextView selfPayCreditCardActText=null;//信用卡账户
	private EditText selfPayPasswdEdit=null;
	private String selfPaycreditNo=null;
	private String selfPayActNo=null;
	private String selfPayBal=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfpayoperationdetail);
        Intent intent=this.getIntent();
        selfPaycreditNo=intent.getStringExtra("creditNo");
        selfPayActNo=this.getIntent().getStringExtra("selfPayActNo");
        selfPayBal=intent.getStringExtra("selfPayBal");
        
        
        selfPayOperationDetailButton=(Button)findViewById(R.id.selfPayOperationDetailButton);
        selfPayOperationDetailButton.setOnClickListener(new SelfPayOperationButtonListener());
        
        selfPayCreditCardActText=(TextView)findViewById(R.id.selfPayCreditCardActText);
        selfPayCreditCardActText.setText(selfPaycreditNo);
        selfPayBalText=(TextView)findViewById(R.id.selfPayBalText);
        selfPayBalText.setText(selfPayBal);
        selfPayOperationPayActText=(TextView)findViewById(R.id.selfPayOperationPayActText);
        selfPayOperationPayActText.setText(selfPayActNo);
        
        selfPayPasswdEdit=(EditText)findViewById(R.id.selfPayPasswdEdit);
        
	}
	class SelfPayOperationButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			String passwd=selfPayPasswdEdit.getText().toString();
			if(passwd==null || passwd.trim().length()==0){
				Toast.makeText(SelfPayOperationDetail.this, "支付密码不能为空", Toast.LENGTH_SHORT).show();
			}else{
				Intent intent=new Intent();
				intent.putExtra("flag", "自助信用卡还款成功！");
				intent.putExtra("info", "还款账户："+selfPayActNo+"\n信用卡账户："+selfPaycreditNo+"\n还款金额："+selfPayBal+"\n转账凭证号:1234\n");
				intent.setClass(SelfPayOperationDetail.this,SelfPayOperationDialog.class);
				SelfPayOperationDetail.this.startActivity(intent);
			}
			
		}
		
	}
}
