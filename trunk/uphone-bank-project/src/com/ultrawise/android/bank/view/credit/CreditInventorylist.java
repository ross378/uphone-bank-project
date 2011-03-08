package com.ultrawise.android.bank.view.credit;

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

public class CreditInventorylist extends ListActivity{
	private Button btnReturn=null;
	private TextView acc1=null;
	private TextView acc2=null;
	private TextView type1=null;
	private TextView type2=null;
	private String[] reslut=null;
	private String nomber=null;
	String cardNo;
	String listview;
	String start;
	String end;
    ArrayList<String> noarr=new ArrayList<String>();
    ArrayList<String> jiao=new  ArrayList<String>();
    ArrayList<String> name=new  ArrayList<String>();
    ArrayList<String> amount=new  ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditchange);
        Intent receive_intent = getIntent();
        cardNo = receive_intent.getStringExtra("cardNo");
        listview= receive_intent.getStringExtra("result");
        start=receive_intent.getStringExtra("start");
        end=receive_intent.getStringExtra("end");
        String[]  zhuan=listview.split(":");
        for(String s :zhuan)
        {
        	String[] zhuann=s.split("#");
        	if(zhuann.length>1)
        	{
        		noarr.add(zhuann[0]);
        		jiao.add(zhuann[1]);
        		amount.add(zhuann[2]);
        		name.add(zhuann[3]);
        	}
        }
        TextView  tvClassFirst = (TextView) this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent();
				 intent.setClass(CreditInventorylist.this, ABankMain.class);
				 CreditInventorylist.this.startActivity(intent);
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
	        tvClassSecond.setText("信用卡>");
	        tvClassSecond.setOnClickListener(new OnClickListener() {
	        	
				public void onClick(View v) {

					Intent intent=new Intent();
					intent.setClass(CreditInventorylist.this,CreditView.class);
					CreditInventorylist.this.startActivity(intent);
				}
			});
	        tvClassSecond.setVisibility(View.VISIBLE);
			TextView tvClassSecond1 = (TextView) this.findViewById(R.id.class_third);
			tvClassSecond1.setText("帐户查询");
			tvClassSecond1.setVisibility(View.VISIBLE);
		
			/**
			 * 接收上一个Activity传过来的值并放在相应位置
			 * 
			 */
			
			/*Intent type_name = getIntent();
			nomber =type_name.getStringExtra("nomber");
		    String type=type_name.getStringExtra("type");
		    String start=type_name.getStringExtra("start");
		    String end=type_name.getStringExtra("end");*/
		    acc1=(TextView)findViewById(R.id.account_chance_textt);
		    acc1.setText("帐号"+cardNo+"在"+start+"到"+end+"\n"+"之间的交易记录如下："); 
		    ArrayList<HashMap<String,Object>> accoutList = new ArrayList<HashMap<String,Object>>();
	        HashMap<String,Object> acclist1 = new HashMap<String,Object>();
                        if(jiao.size()>0)
                        {
                           for(int i=0;i<jiao.size();i++)
                           {
                        	acclist1.put("txtView1",noarr.get(i));
                   	        acclist1.put("txtView2", jiao.get(i));
                   	        acclist1.put("txtView3",R.drawable.account2);
                           }
                        }else
                        {
                        	 acclist1.put("txtView1","没有交易");
                 	        acclist1.put("txtView2", "请选择其它时间段");
                 	        acclist1.put("txtView3",R.drawable.account2);
                        }
	        accoutList.add(acclist1);
	        SimpleAdapter MainListAdapter = new SimpleAdapter(this, accoutList,R.layout.account_quer_list2, new String[] {
					"txtView1", "txtView2" ,"txtView3",}, new int[] { R.id.txtView1, R.id.txtView2 ,R.id.txtView3} );
	        this.setListAdapter(MainListAdapter);
	        
	      //  ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        //iv_now.setVisibility(View.VISIBLE);
	        
	        //设置底部按钮
	        ImageView	btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(CreditInventorylist.this, ABankMain.class);
					CreditInventorylist.this.startActivity(intent);
					finish();
				}
			});
			//btnCoustom.setVisibility(View.VISIBLE);
			
			ImageView btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					Intent intent=new Intent();
					intent.setClass(CreditInventorylist.this,  FinancialConsultation.class);
					CreditInventorylist.this.startActivity(intent);
					finish();
				}
			});
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	
			Intent intent=new Intent();
			intent.putExtra("date", noarr.get((int)id));
			intent.putExtra("name", name.get((int)id));
			intent.putExtra("amount", amount.get((int)id));
			intent.putExtra("jiao", jiao.get((int)id));
			intent.setClass(CreditInventorylist.this, CreditInventoryResult.class);
			CreditInventorylist.this.startActivity(intent);
	}
}