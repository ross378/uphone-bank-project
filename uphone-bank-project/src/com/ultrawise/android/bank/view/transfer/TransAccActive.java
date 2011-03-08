package com.ultrawise.android.bank.view.transfer;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.TransferWebservicesClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class TransAccActive extends Activity {
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private TextView tv_acctype;
	private EditText tv_trans_pasd;
	private Button btn_trans_act;
	private String atvaccinfo;
	private String transtype;
	private String username;
	private String password;
	private String account;
	private String accinfo;
	private String balance;
	private String flag;
	Intent receive_intent;
	Intent intent;
	
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
        setContentView(R.layout.trans_acc_active);
        
        receive_intent = getIntent();
        intent = new Intent();
        transtype = receive_intent.getStringExtra("transtype");
        username  = receive_intent.getStringExtra("username");
        
        accinfo = receive_intent.getStringExtra("accinfo");
        account = receive_intent.getStringExtra("account");
        
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
				intent.setClass(TransAccActive.this, ABankMain.class);
				TransAccActive.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(TransAccActive.this, TransferMain.class);
				TransAccActive.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
		
		tv_acctype = (TextView)findViewById(R.id.tv_trans_atvacc);
		atvaccinfo = accinfo + ":" +account;
		tv_acctype.setText(atvaccinfo);
		//next
		btn_trans_act = (Button)findViewById(R.id.btn_trans_act);
		btn_trans_act.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				tv_trans_pasd = (EditText)findViewById(R.id.et_trans_atvpsd);
				password = tv_trans_pasd.getText().toString();
				
		        lstinfo.add(account);
		        lstinfo.add(password);
		        
		        lstout=transferwebservice.connectHttp("504", lstinfo);
		        balance = lstout.get(0);
		        flag = lstout.get(1);
		    if(flag.equals("true")){
				Intent intent = new Intent();
				intent.putExtra("transtype", transtype);
				intent.putExtra("username", username);
				intent.putExtra("account", account);
				intent.putExtra("accinfo", accinfo);
				intent.putExtra("balance", balance);
				intent.setClass(TransAccActive.this, TransAccPsdSucc.class);
				TransAccActive.this.startActivity(intent);
			}else{
				//Dialog.showDialog("密码错误", "请您输入的密码不正确！", "返回");
				Intent intent = new Intent();
				intent.putExtra("diatitle", "密码错误");
				intent.putExtra("diacontent", "您输入的密码不正确！");
				intent.putExtra("btntext", "返回");
				intent.setClass(TransAccActive.this, CommonDialog.class);
				TransAccActive.this.startActivity(intent);
				//Toast.makeText(getApplicationContext(), "请您输入的密码不正确！",Toast.LENGTH_SHORT).show();
			}
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
				intent.setClass(TransAccActive.this, ABankMain.class);
				TransAccActive.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(TransAccActive.this, FinancialConsultation.class);
				TransAccActive.this.startActivity(intent);
			}
		});
	}
}