package com.ultrawise.android.bank.view.account_query;

import java.util.ArrayList;
import java.util.HashMap;
import com.ultrawise.android.bank.view.transfer.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AccountFrom_selection extends ListActivity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_main);
         
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("账户查询>");
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView) this.findViewById(R.id.class_second);
		tvClassSecond.setText("来帐查询");
		tvClassSecond.setVisibility(View.VISIBLE);
		
		//设定返回按钮
		ImageView btnReturn = (ImageView)this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				finish();
			}
		});
		ArrayList<HashMap<String,String>> accoutList = new ArrayList<HashMap<String,String>>();
		
		HashMap<String,String> acclist1 = new HashMap<String,String>();
        HashMap<String,String> acclist2 = new HashMap<String,String>();
        HashMap<String,String> acclist3 = new HashMap<String,String>();
        
        acclist1.put("title", "账户信息及余额查询");
        acclist1.put("info", ">");
        acclist2.put("title", "账单明细查询");
        acclist2.put("info", ">");
        acclist3.put("title", "账户来帐查询");
        acclist3.put("info", ">");
        
        accoutList.add(acclist1);
        accoutList.add(acclist2);
        accoutList.add(acclist3);
        
		SimpleAdapter adapter = new SimpleAdapter(this,accoutList, R.id.accountfrom_list,new String[]{"title","info"},
				new int[]{R.id.accountfrom_selection_1,R.id.accountfrom_selection_2});
		 
		this.setListAdapter(adapter);

        
	}
//	private void setListAdapter(SimpleAdapter adapter) {
//		// TODO Auto-generated method stub
//		
//	}
	//protected void onListItemClick(ListView l, View v, int position, long id) {
	//	super.onListItemClick(l, v, position, id);
//		if(id==0){//账户信息及余额查询
//			Intent payment_intent = new Intent();
//			payment_intent.setClass(AccountQueryType.this, AccountQueryBalance.class);
//			AccountQueryType.this.startActivity(payment_intent);
//		}else if(id==1){//账户明细查询
//			Intent payment_intent = new Intent();
//			payment_intent.setClass(AccountQueryType.this, AccountQueryDetail.class);
//			AccountQueryType.this.startActivity(payment_intent);
//		}else if(id==2){//账户来帐查询
//			Intent payment_intent = new Intent();
//			payment_intent.setClass(AccountQueryType.this, AccountQueryEinnahme.class);
//			AccountQueryType.this.startActivity(payment_intent);
//		}
	//}
}
