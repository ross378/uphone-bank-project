package com.ultrawise.android.bank.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.ultrawise.android.bank.view.credit.SelfPay;
import com.ultrawise.android.bank.view.credit.SelfPayAct;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
/*
 * @author weijuan 
 * @date 2011-1-18
 * 存款利率Activity DepositeRates
 */
public class DepositeRates extends Activity {
	//个人单位存款利率图标
	private ImageView selfCompanyItemImage=null;
	//其他存款利率图标
	private ImageView otherItemImage=null;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.depositereates);
	        //获得个人单位存款利率图标，并设置鼠标单击事件
	        selfCompanyItemImage=(ImageView)findViewById(R.id.SelfCompanyItemImage);
	        selfCompanyItemImage.setOnClickListener(new SelfCompanyItemImageListener());
	        
	        
	      //获得其他存款利率图标，并设置鼠标单击事件
	        otherItemImage=(ImageView)findViewById(R.id.OtherItemImage);
	        otherItemImage.setOnClickListener(new OtherItemImageListener());
	    }
	 /*
	  * 个人单位存款利率图标响应
	  * 跳转到个人单位存款Activity-SelfCompanyDepositeRates
	  */
	 class SelfCompanyItemImageListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(DepositeRates.this, SelfCompanyDepositeRates.class);
			DepositeRates.this.startActivity(intent);
			
		}
		 
	 }
	 /*
	  * 其他存款利率图标响应
	  * 跳转到其他存款Activity-OtherDepositeRates
	  */
	 class OtherItemImageListener implements OnClickListener{

		public void onClick(View arg0) {
			Intent intent=new Intent();
			intent.setClass(DepositeRates.this, OtherDepositeRates.class);
			DepositeRates.this.startActivity(intent);
			
		}
		 
	 }
}
