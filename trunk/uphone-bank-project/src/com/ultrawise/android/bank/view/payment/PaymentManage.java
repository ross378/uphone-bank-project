package com.ultrawise.android.bank.view.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.PaymentWebservices;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.payment.TreeViewAdapter.TreeNode;
import com.ultrawise.android.bank.view.transfer.R;

import android.R.color;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.view.View.OnClickListener;

public class PaymentManage extends ListActivity {//缴费项目管理
	
	ImageView list_img;
	ImageView img;
	int [] yy=null;
	String[] values = null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);

        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				 intent.setClass(PaymentManage.this, ABankMain.class);
				 PaymentManage.this.startActivity(intent);	
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);

		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText("自助缴费>");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManage.this, PaymentMain.class);
				PaymentManage.this.startActivity(payment_intent);	
				
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);

		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText("缴费项目管理");
		tvClassThird.setVisibility(View.VISIBLE);

		TextView tv_acc_typ = (TextView)this.findViewById(R.id.paymenthistory);
		tv_acc_typ.setText("已开通项目");
    	tv_acc_typ.setVisibility(View.VISIBLE);
		
    	PaymentWebservices.paramsString = "payment";
    	List<String> value = new ArrayList<String>();
    	values = PaymentWebservices.connectHttp("60401", value);
    	yy=new int[values.length/2];
		
        ArrayList<HashMap<String,Object>> mainlist = new ArrayList<HashMap<String,Object>>();
        
        HashMap<String,Object> paylist1 = null;
        int j = 0;
        for(int i=0;i<values.length;i+=2){
        	paylist1 = new HashMap<String,Object>();
        	paylist1.put("payment_list",values[i]);
        	if(values[i+1].equals("1")){
        		paylist1.put("listimg2", R.drawable.item_enable);
        	}else{
        		paylist1.put("listimg2", R.drawable.item_stop);
        	}
        	yy[j++] = Integer.parseInt(values[i+1]);
        	mainlist.add(paylist1);
        }

        SimpleAdapter MainListAdapter = new SimpleAdapter(
        		this, mainlist,R.layout.payment_manage_list, new String[]{
        				"payment_list","listimg2"},new int[]{
        				R.id.manage_list_text,R.id.manage_list_img } );
        this.setListAdapter(MainListAdapter);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
//        iv_now.setVisibility(View.VISIBLE);
        
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
				intent.setClass(PaymentManage.this, ABankMain.class);
				PaymentManage.this.startActivity(intent);
			}
		});
		
        ImageView	btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
        btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent	intent = new Intent();
				intent.setClass(PaymentManage.this, FinancialConsultation.class);
				PaymentManage.this.startActivity(intent);
			}
		});
        
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		img=(ImageView)v.findViewById(R.id.manage_list_img);
		int flag = (int)id;
		List<String> params = new ArrayList<String>();
		params.add(values[2*flag]);
		if(yy[0]==0){
			params.add("1");
		}else{
			params.add("0");
		}
		if(updataStatue("60402",params)){
			enAbleToStop(flag);
		}
	}
	private void enAbleToStop(int tag){
		System.out.println("---------"+yy[tag]);
		if(yy[tag]==1){
            PaymentManage.this.img.setImageResource(R.drawable.item_stop);
            yy[tag]=0;
		}else {
		if(yy[tag]==0){
            PaymentManage.this.img.setImageResource(R.drawable.item_enable);
            yy[tag]=1;
		}
		}
	}
	
	private boolean updataStatue(String funNo,List<String> params){
		PaymentWebservices.paramsString = "payment";
		String[] values=PaymentWebservices.connectHttp(funNo, params);
		System.out.println(values[0]);
		if(values[0].equals("true")){
			return true;
		}
		return false;
	}
}
