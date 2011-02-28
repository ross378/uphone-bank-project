package com.ultrawise.android.bank.view.transfer;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TransAccSelect extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_main);
        
        Intent receive_intent = getIntent();
        String transtype = receive_intent.getStringExtra("transtype");
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccSelect.this, TransferMain.class);
				TransAccSelect.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccSelect.this, TransferMain.class);
				TransAccSelect.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
		
		TextView tv_trans_title = (TextView)findViewById(R.id.tv_trans_title);
		tv_trans_title.setText("请选择账户:");
		tv_trans_title.setVisibility(View.VISIBLE);
		
		TextView tv_trans_ln = (TextView)findViewById(R.id.tv_trans_ln);
		tv_trans_ln.setVisibility(View.VISIBLE);
		
		 ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	        
	        HashMap<String,Object> map = new HashMap<String,Object>();
	        map.put("payment_list","    首选账户");
	        map.put("listimg2", R.drawable.trans_main2);
	        list.add(map);
	        
	        map = new HashMap<String,Object>();
	        map.put("payment_list","    默认账户");
	        map.put("listimg2", R.drawable.trans_main2);
	        list.add(map);
	        
	        map = new HashMap<String,Object>();
	        map.put("payment_list","    其他账户");
	        map.put("listimg2", R.drawable.trans_main2);
	        list.add(map);
	        
	        SimpleAdapter TransMainAdapter = new SimpleAdapter(this,list,R.layout.trans_main_list,new String[]{"payment_list","listimg2"},new int[]{R.id.payment_list,R.id.listimg2});
	        this.setListAdapter(TransMainAdapter);
	}
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent receive_intent = getIntent();
        String transtype = receive_intent.getStringExtra("transtype");
		if (id == 0) {
			Intent intent = new Intent();
			intent.putExtra("transtype", transtype);
			intent.setClass(TransAccSelect.this, TransAccActive.class);
			TransAccSelect.this.startActivity(intent);
		}else if(id==1){
			Intent intent = new Intent();
			intent.putExtra("transtype", transtype);
			intent.setClass(TransAccSelect.this, TransAccActive.class);
			TransAccSelect.this.startActivity(intent);
		}
		else if(id==2){
			Intent intent = new Intent();
			intent.putExtra("transtype", transtype);
			intent.setClass(TransAccSelect.this, TransAccInput.class);
			TransAccSelect.this.startActivity(intent);
		}
	}
}