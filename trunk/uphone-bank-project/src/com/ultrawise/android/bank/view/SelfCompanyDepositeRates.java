package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.DepositeRates.OtherItemImageListener;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/*
 * @author weijuan
 * @date 2011-1-18
 * 个人单位存款利率Activity-SelfCompanyDepositeRates
 */
public class SelfCompanyDepositeRates extends Activity {
	//定期存款利率图标
	private ImageView dingQiDepositeImage=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selfcompanydepositerates);
        //获得定期存款利率图标，并设置其鼠标单击响应事件
        dingQiDepositeImage=(ImageView)findViewById(R.id.dingQiDepositeImage);
        dingQiDepositeImage.setOnClickListener(new DingQiDepositeImageListener());
	}
	/*
	 * 定期存款利率图标响应
	 * 跳转到定期存款Activity-DingQiDepositeRatesDetail
	 */
	 class DingQiDepositeImageListener implements OnClickListener{

			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(SelfCompanyDepositeRates.this, DingQiDepositeRatesDetail.class);
				SelfCompanyDepositeRates.this.startActivity(intent);
				
			}
			 
		 }
}
