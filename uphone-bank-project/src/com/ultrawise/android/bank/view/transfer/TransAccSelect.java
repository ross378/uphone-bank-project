package com.ultrawise.android.bank.view.transfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.ultrawise.android.bank.consum_webservices.TransferWebservicesClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TransAccSelect extends ListActivity {
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private String transtype;
	private String username;
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
        setContentView(R.layout.trans_main);
        
        intent = new Intent();
        receive_intent = getIntent();
        transtype = receive_intent.getStringExtra("transtype");
        username = receive_intent.getStringExtra("username");
        
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
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransAccSelect.this, ABankMain.class);
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
		
		//ListView控件
		 ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	        
	        HashMap<String,Object> map = new HashMap<String,Object>();
	        map.put("payment_list","首选账户");
	        map.put("listimg2", R.drawable.trans_main2);
	        list.add(map);
	          
	        map = new HashMap<String,Object>();
	        map.put("payment_list","其他账户");
	        map.put("listimg2", R.drawable.trans_main2);
	        list.add(map);
	        
	        SimpleAdapter TransMainAdapter = new SimpleAdapter(this,list,R.layout.trans_main_list,new String[]{"payment_list","listimg2"},new int[]{R.id.payment_list,R.id.listimg2});
	        this.setListAdapter(TransMainAdapter);
	        
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
					intent.setClass(TransAccSelect.this, ABankMain.class);
					TransAccSelect.this.startActivity(intent);
				}
			});
			
			btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
			btnHelper.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent = new Intent();
					intent.setClass(TransAccSelect.this, FinancialConsultation.class);
					TransAccSelect.this.startActivity(intent);
				}
			});
	}
	
	//ListView监听器
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			
			lstinfo.add(username);
			lstout=transferwebservice.connectHttp("0501", lstinfo);
			if(lstout != null){
			Intent intent = new Intent();
			intent.putExtra("transtype", transtype);
			intent.putExtra("username",username);
			intent.setClass(TransAccSelect.this, TransAccActive.class);
			TransAccSelect.this.startActivity(intent);
			}else{
				Dialog.showDialog("获取信息失败", "请确认您已经设置首选账户", "返回");
			}
		}else if(id==1){
			Intent intent = new Intent();
			intent.putExtra("transtype", transtype);
			intent.putExtra("username",username);
			intent.setClass(TransAccSelect.this, TransAccInput.class);
			TransAccSelect.this.startActivity(intent);
		}
	}
	
}