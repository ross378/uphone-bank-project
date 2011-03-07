package com.ultrawise.android.bank.view.transfer;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.TransferWebservicesClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TransAmtInput extends Activity {
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private EditText transamt;
	private EditText transph;
	private EditText transpsd;
	private Button btn_trans_amtnext;
	private String amtnum;
	private String amtph;
	private String amtpsd;
	
	private String transtype;
	private String username;
	private String balance;
	private String account;
	private String accinfo;
	Intent intent;
	Intent receive_intent;
	
	private TransferWebservicesClient transferwebservice = new TransferWebservicesClient();
	private List<String> lstout = new ArrayList<String>();
	private List<String> lstinfo = new ArrayList<String>();
	
	private CommonDialog Dialog = new CommonDialog();
	
	//触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_amt_input);
        
        receive_intent = getIntent();
        intent = new Intent();
        transtype = receive_intent.getStringExtra("transtype"); 
        username = receive_intent.getStringExtra("username");
        accinfo = receive_intent.getStringExtra("accinfo");
        account = receive_intent.getStringExtra("account");
        balance = receive_intent.getStringExtra("balance");
        
        transamt = (EditText)findViewById(R.id.et_trans_amt);
        transph = (EditText)findViewById(R.id.et_trans_amtph);
        transpsd = (EditText)findViewById(R.id.et_trans_amtpsd);
        
        amtnum = transamt.getText().toString();
        amtph = transph.getText().toString();
        amtpsd = transpsd.getText().toString();
        
      //向右滑动触发后退
		mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				// TODO Auto-generated method stub
				if (distanceY == 0 && distanceX < 0)
					onBackPressed();

				return super.onScroll(e1, e2, distanceX, distanceY);
			}
		});
        
		//顶部导航文本
        transtype = receive_intent.getStringExtra("transtype");
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(TransAmtInput.this, ABankMain.class);
				TransAmtInput.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(TransAmtInput.this, TransferMain.class);
				TransAmtInput.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
        
		//确定
        btn_trans_amtnext = (Button)findViewById(R.id.btn_trans_amtnext);
        btn_trans_amtnext.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				lstinfo.add(account);
				lstinfo.add(amtnum);
				lstinfo.add(amtpsd);
				lstinfo.add(amtph);
				
				lstout=transferwebservice.connectHttp("505", lstinfo);
				
				Intent trans_amtnext = new Intent();
				trans_amtnext.putExtra("transtype", transtype);
				trans_amtnext.putExtra("account", account);
				trans_amtnext.putExtra("amtnum", amtnum);
				trans_amtnext.putExtra("amtph", amtph);
				
				trans_amtnext.setClass(TransAmtInput.this, TransAmtConfirm.class);
				TransAmtInput.this.startActivity(trans_amtnext);
			}
        	
        });
        
      //返回键设定
        btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
			
		});
		
		//底部两个按钮
		btnMain = (ImageView) this.findViewById(R.id.btnMain);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(TransAmtInput.this, ABankMain.class);
				TransAmtInput.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(TransAmtInput.this, FinancialConsultation.class);
				TransAmtInput.this.startActivity(intent);
			}
		});
        
	}
}