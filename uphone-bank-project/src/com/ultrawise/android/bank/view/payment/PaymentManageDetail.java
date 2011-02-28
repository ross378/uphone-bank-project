package com.ultrawise.android.bank.view.payment;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PaymentManageDetail extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_hisdtl);
		TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManageDetail.this, ABankMain.class);
				PaymentManageDetail.this.startActivity(payment_intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
        TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
        tvClassSecond.setText("自助缴费>");
        tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManageDetail.this, PaymentMain.class);
				PaymentManageDetail.this.startActivity(payment_intent);
			}
		});
        tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("缴费项目管理");
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManageDetail.this, PaymentManage.class);
				PaymentManageDetail.this.startActivity(payment_intent);
			}
		});
		
        
        //返回键设定
        ImageView    btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
        btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
			
		});
		
		//底部两个按钮
        ImageView	btnMain = (ImageView) this.findViewById(R.id.btnMain);
        btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PaymentManageDetail.this, ABankMain.class);
				PaymentManageDetail.this.startActivity(intent);
			}
		});
		
        ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
        btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentManageDetail.this, FinancialConsultation.class);
				PaymentManageDetail.this.startActivity(intent);
			}
		});
		
		
		TextView tv_payhis_title_detail1=(TextView)findViewById(R.id.tv_payhis_title_detail1);
		tv_payhis_title_detail1.setText("扬子晚报");
	    
	    TextView tv_payhis_title_detail2=(TextView)findViewById(R.id.tv_payhis_title_detail2);
	    tv_payhis_title_detail2.setText("10元/月");
	    
	    TextView tv_payhis_title_detail3=(TextView)findViewById(R.id.tv_payhis_title_detail3);
	    tv_payhis_title_detail3.setText("扬子晚报");
	    TextView tv_payhis_title_detail4=(TextView)findViewById(R.id.tv_payhis_title_detail4);
	    tv_payhis_title_detail4.setText("中国建设银行");
	    TextView tv_payhis_title_detail5=(TextView)findViewById(R.id.tv_payhis_title_detail5);
	    tv_payhis_title_detail5.setText("2元/月");
	    TextView tv_payhis_title_detail6=(TextView)findViewById(R.id.tv_payhis_title_detail6);
	    tv_payhis_title_detail6.setText("每月1日至15日");
     
	    Button ok=(Button)findViewById(R.id.manage_ok);
	    ok.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent=new Intent();
				payment_intent.setClass(PaymentManageDetail.this,PaymentManage.class);
				
				startActivity(payment_intent);
			}
		});
		
		
		
		
		
		
		
		
		
		
	}
}
