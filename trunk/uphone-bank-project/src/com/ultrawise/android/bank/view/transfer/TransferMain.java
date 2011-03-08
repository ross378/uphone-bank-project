package com.ultrawise.android.bank.view.transfer;

import java.util.ArrayList;
import java.util.HashMap;
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

public class TransferMain extends ListActivity {
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private static String username;
	Intent receive_intent;
	Intent intent;
	
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
        
        username =  receive_intent.getStringExtra(username);
        
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
				intent.setClass(TransferMain.this, ABankMain.class);
				TransferMain.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setVisibility(View.VISIBLE);
		
		//ListView
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("listimg1",R.drawable.trans_main);
        map.put("payment_list","手机到手机转账");
        map.put("listimg2", R.drawable.trans_main2);
        list.add(map);
        
        map = new HashMap<String,Object>();
        map.put("listimg1",R.drawable.trans_main);
        map.put("payment_list","手机到签约账户转账");
        map.put("listimg2", R.drawable.trans_main2);
        list.add(map);
        
        SimpleAdapter TransMainAdapter = new SimpleAdapter(this,list,R.layout.trans_main_list,new String[]{"listimg1","payment_list","listimg2"},new int[]{R.id.listimg1,R.id.payment_list,R.id.listimg2});
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
				intent.setClass(TransferMain.this, ABankMain.class);
				TransferMain.this.startActivity(intent);
			}
		});
		
		btnHelper = (ImageView) this.findViewById(R.id.btnHelper);
		btnHelper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(TransferMain.this, FinancialConsultation.class);
				TransferMain.this.startActivity(intent);
			}
		});
	}
	
	//ListView监听器
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent();
		if (id == 0) {
			intent.putExtra("transtype", ">手机到手机转账");
			intent.putExtra("username","Sun01");
			intent.setClass(TransferMain.this, TransAccSelect.class);
			TransferMain.this.startActivity(intent);
		}else if(id==1){
			intent.putExtra("transtype", ">手机到签约账户转账");
			intent.putExtra("username","Sun01");
			intent.setClass(TransferMain.this, TransAccSelect.class);
			TransferMain.this.startActivity(intent);
		}
	}
}