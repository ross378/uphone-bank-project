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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class TransAccInput extends Activity {
	private GestureDetector mGestureDetector;
	private ImageView btnReturn;
	private ImageView btnMain;
	private ImageView btnHelper;
	private Spinner sp_trans_inptype;
	private Spinner sp_trans_inpacc;
	private ArrayAdapter spadapter1;
	private ArrayAdapter spadapter2;
	private ArrayAdapter spadapter3;
	private Button btn_trans_next;
	private String transtype;
	private static String username;
	private String accinfo;
	private String account;
	private String flag;

	Intent receive_intent;
	Intent intent;
	
	
	private TransferWebservicesClient transferwebservice = new TransferWebservicesClient();
	private List<String> lstacctypeout = new ArrayList<String>();
	private List<String> lstusername = new ArrayList<String>();
	private List<String> lstacctypein = new ArrayList<String>();
	private List<String> lstaccount = new ArrayList<String>();
	private List<String> lstaccountnew = new ArrayList<String>();
	
	private CommonDialog Dialog = new CommonDialog();
	
	
	//触摸触发
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return mGestureDetector.onTouchEvent(event);

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_acc_input);
        
        intent = new Intent();        
        receive_intent = getIntent();
        username = receive_intent.getStringExtra("username");
        lstusername.add(username);
    	lstacctypeout=transferwebservice.connectHttp("502", lstusername);
        
    	lstaccount.add("1111111111");
    	lstaccount.add("1111111111");
    	lstaccount.add("1111111111");
    	
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
				intent.setClass(TransAccInput.this, ABankMain.class);
				TransAccInput.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setClass(TransAccInput.this, TransferMain.class);
				TransAccInput.this.startActivity(intent);
			}
		});
		tvClassSecond.setVisibility(View.VISIBLE);
		
		TextView tvClassThird = (TextView)this.findViewById(R.id.class_third);
		tvClassThird.setText(transtype);
		tvClassThird.setVisibility(View.VISIBLE);
        
		//Spinner
        sp_trans_inptype = (Spinner)findViewById(R.id.sp_trans_inptype);
        spadapter1 = new ArrayAdapter<String>(TransAccInput.this,android.R.layout.simple_spinner_item,lstacctypeout);
        spadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_trans_inptype.setAdapter(spadapter1);
        
        sp_trans_inpacc = (Spinner)findViewById(R.id.sp_trans_inpacc);
        spadapter2 = new ArrayAdapter<String>(TransAccInput.this, android.R.layout.simple_spinner_item,lstaccount);
        spadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_trans_inpacc.setAdapter(spadapter2);
        
        
        
        sp_trans_inptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { 
            public void onItemSelected(AdapterView<?> adapterView, View view, 
                    int position, long id) { 
                //被选中时候发生的动作
            	
            	accinfo = adapterView.getItemAtPosition(position).toString();
            	Toast.makeText(getApplicationContext(), accinfo,Toast.LENGTH_SHORT).show();
            	lstacctypein.clear();
            	lstacctypein.add(username);
            	lstacctypein.add(accinfo);
            	lstaccountnew =transferwebservice.connectHttp("503", lstacctypein);
            	spadapter3 = new ArrayAdapter<String>(TransAccInput.this, android.R.layout.simple_spinner_item,lstaccountnew);
                spadapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            	
            	sp_trans_inpacc.setAdapter(spadapter3);
            	//flag = lstout.get(0);
                
            } 
            public void onNothingSelected(AdapterView<?> arg0) { 
            	
            	
            } 
        });
        
        sp_trans_inpacc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { 
            public void onItemSelected(AdapterView<?> adapterView, View view, 
                    int position, long id) { 
                //被选中时候发生的动作
            	account = adapterView.getItemAtPosition(position).toString();
            	Toast.makeText(getApplicationContext(), account,Toast.LENGTH_SHORT).show();
            	
            } 
            public void onNothingSelected(AdapterView<?> arg0) { 
            	
            	
            } 
        });
        
        //next
        btn_trans_next = (Button)findViewById(R.id.btn_trans_inpacc);
        btn_trans_next.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		        transtype = receive_intent.getStringExtra("transtype");
		        username = receive_intent.getStringExtra("username");
				Intent intent = new Intent();
				intent.putExtra("transtype", transtype);
				intent.putExtra("username", username);
				intent.putExtra("accinfo", accinfo);
				intent.putExtra("account", account);
				intent.setClass(TransAccInput.this, TransAccActive.class);
				TransAccInput.this.startActivity(intent);
			}
        });        
	}
}