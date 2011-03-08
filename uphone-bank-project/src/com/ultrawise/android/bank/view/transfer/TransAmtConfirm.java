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

public class TransAmtConfirm extends Activity {
	private GestureDetector mGestureDetector;
	private Button btn_trans_cofok;
	private Button btn_trans_cofcan; 
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private TextView conacc;
	private TextView conamt;
	private TextView confee;
	private TextView conamtph;
	private TextView conpayee;
	
	
	private String transtype;
	private String username;
	private String balance;
	private String account;
	private String accinfo;
	
	private String amtnum;
	private String amtph;
	private String amtfee;
	private String amtpayee;
	Intent intent;
	Intent receive_intent;
	
	private String flag = "false";
	private String newbal = null;
	
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
        setContentView(R.layout.trans_amt_confirm);
        
        receive_intent = getIntent();
        intent = new Intent();
        transtype = receive_intent.getStringExtra("transtype"); 
        username = receive_intent.getStringExtra("username");
        accinfo = receive_intent.getStringExtra("accinfo");
        account = receive_intent.getStringExtra("account");
        balance = receive_intent.getStringExtra("balance");
        amtnum = receive_intent.getStringExtra("amtnum");
        amtph = receive_intent.getStringExtra("amtph");
        amtfee = receive_intent.getStringExtra("fee");
        amtpayee = receive_intent.getStringExtra("receiver");
        
        conacc = (TextView)findViewById(R.id.tv_trans_confirmacc);
        conamt = (TextView)findViewById(R.id.tv_trans_confirmamt);
        confee = (TextView)findViewById(R.id.tv_trans_confirmamtpay);
        conamtph = (TextView)findViewById(R.id.tv_trans_confirmamtph);
        conpayee = (TextView)findViewById(R.id.tv_trans_confirmamtname);
        
        conacc.setText(account);
        conamt.setText(amtnum+"元");
        confee.setText(amtfee+"元");
        conamtph.setText(amtph);
        conpayee.setText(amtpayee);
        
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
				intent.setClass(TransAmtConfirm.this, ABankMain.class);
				TransAmtConfirm.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(TransAmtConfirm.this, TransferMain.class);
				TransAmtConfirm.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
        
		//确认转账
		btn_trans_cofok = (Button)findViewById(R.id.btn_trans_confok);
        btn_trans_cofok.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lstinfo.add(account);
				lstinfo.add(amtnum);
				lstinfo.add(amtph);
				
				lstout = transferwebservice.connectHttp("506", lstinfo);
				
				newbal = lstout.get(0);
				flag = lstout.get(1);
				if(flag.equals("succeed")){
					Intent intent = new Intent();
					intent.putExtra("diatitle", "转账成功");
					intent.putExtra("diacontent", "账户："+account+"\n"+"余额："+newbal+"\n"+"交易流水号：1122");
					intent.putExtra("btntext", "返回");
					intent.setClass(TransAmtConfirm.this, CommonDialog.class);	
					TransAmtConfirm.this.startActivity(intent);
				}else{
					Intent intent = new Intent();
					intent.putExtra("diatitle", "错误信息");
					intent.putExtra("diacontent", "未知错误！");
					intent.putExtra("btntext", "返回");
					intent.setClass(TransAmtConfirm.this, CommonDialog.class);
					TransAmtConfirm.this.startActivity(intent);
				}
				
				/*Intent trans_amt_cofcan = new Intent();
				trans_amt_cofcan.setClass(TransAmtConfirm.this, TransSuccDialog.class);
				TransAmtConfirm.this.startActivity(trans_amt_cofcan);
				TransAmtConfirm.this.startActivity(trans_amt_cofcan);*/
			}
        	
        });
        
        //取消转账
        btn_trans_cofcan = (Button)findViewById(R.id.btn_trans_confcancle);
        btn_trans_cofcan.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent trans_amt_cofcan = new Intent();
				trans_amt_cofcan.setClass(TransAmtConfirm.this, TransferMain.class);
				TransAmtConfirm.this.startActivity(trans_amt_cofcan);
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
				intent.setClass(TransAmtConfirm.this, ABankMain.class);
				TransAmtConfirm.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(TransAmtConfirm.this, FinancialConsultation.class);
				TransAmtConfirm.this.startActivity(intent);
			}
		});
        
	}
}