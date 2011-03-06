package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.QuerySever;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Inventorylist extends ListActivity{
	private Intent intent=null;
	private Button btnReturn=null;
	private TextView acc1=null;
	private TextView acc2=null;
	private TextView type1=null;
	private TextView type2=null;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_chance);
        

        intent = new Intent();
        TextView  tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent();
				 intent.setClass(Inventorylist.this, ABankMain.class);
				 Inventorylist.this.startActivity(intent);
			}
		});
		
		//返回键设定
        ImageView btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
	   
		    TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
	        tvClassSecond.setText("账户查询>");
	        tvClassSecond.setOnClickListener(new OnClickListener() {
	        	
				public void onClick(View v) {

					Intent intent=new Intent();
					
					/**
		        	 * 从服务器上取得所需要的数据
		        	 * 
		        	 * @author gsm
		        	 * @param 功能号
		        	 * @return 返回卡的类型
		        	 */
					List<String> result=QuerySever.connectHttp("021", null);
					for(String g:result)
					{
					System.out.println("服务器上取得所需要的数据-明文======"+g.toString());	
					}
					
					
					String[] arrResult=new String[result.size()];
					for(int i=0;i<result.size();i++)
					{   
						 arrResult[i]= result.get(i);
					}
					intent.putExtra("result", arrResult);
					
					intent.setClass(Inventorylist.this,AccountQuery.class);
					Inventorylist.this.startActivity(intent);
				}
			});
	        tvClassSecond.setVisibility(View.VISIBLE);
			TextView tvClassSecond1 = (TextView) this.findViewById(R.id.class_third);
			tvClassSecond1.setText("明细查询");
			tvClassSecond1.setVisibility(View.VISIBLE);
		
		    acc1=(TextView)findViewById(R.id.account_chance_text);
		    acc1.setText("定期账户012345678在20101123到20100111"+"\n"+"之间的交易记录如下：");
		    
		    ArrayList<HashMap<String,Object>> accoutList = new ArrayList<HashMap<String,Object>>();
	        
	        HashMap<String,Object> acclist1 = new HashMap<String,Object>();
	        HashMap<String,Object> acclist2 = new HashMap<String,Object>();
	        HashMap<String,Object> acclist3 = new HashMap<String,Object>();
	        
	        String data="";
	        acclist1.put("txtView1","20101123");
	        acclist1.put("txtView2", "支出");
	        acclist1.put("txtView3",R.drawable.account2);
	        
	        acclist2.put("txtView1","20101124");
	        acclist2.put("txtView2", "收入");
	        acclist2.put("txtView3", R.drawable.account2);
	        
	        acclist3.put("txtView1","20101125");
	        acclist3.put("txtView2", "支出");
	        acclist3.put("txtView3",R.drawable.account2);
	        
	        accoutList.add(acclist1);
	        accoutList.add(acclist2);
	        accoutList.add(acclist3);
	        
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.account_quer_list2, new String[] {
					"txtView1", "txtView2" ,"txtView3",}, new int[] { R.id.txtView1, R.id.txtView2 ,R.id.txtView3} );
	        this.setListAdapter(MainListAdapter);
	        
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        //iv_now.setVisibility(View.VISIBLE);
	        
	        //设置底部按钮
	        ImageView	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(Inventorylist.this, ABankMain.class);
					Inventorylist.this.startActivity(intent);
					finish();
				}
			});
			//btnCoustom.setVisibility(View.VISIBLE);
			
			ImageView btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					Intent intent=new Intent();
					intent.setClass(Inventorylist.this,  FinancialConsultation.class);
					Inventorylist.this.startActivity(intent);
					finish();
				}
			});
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if(id==0){//账户信息及余额查询
			Intent payment_intent = new Intent();
			payment_intent.setClass(Inventorylist.this, InventoryResult.class);
			Inventorylist.this.startActivity(payment_intent);
		}else if(id==1){//账户明细查询
			Intent payment_intent = new Intent();
			payment_intent.setClass(Inventorylist.this, InventoryResult.class);
			Inventorylist.this.startActivity(payment_intent);
		}else if(id==2){//账户来帐查询
			Intent payment_intent = new Intent();
			payment_intent.setClass(Inventorylist.this, InventoryResult.class);
			Inventorylist.this.startActivity(payment_intent);
		}
	}
}
