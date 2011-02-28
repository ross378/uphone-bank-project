package com.ultrawise.android.bank.view.transfer;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TransferMain extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_main);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        //iv_now.setVisibility(View.VISIBLE);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(TransferMain.this, TransferMain.class);
				TransferMain.this.startActivity(intent);
			}
		});
		tvClassFirst.setVisibility(View.VISIBLE);
		
		TextView tvClassSecond = (TextView)this.findViewById(R.id.class_second);
		tvClassSecond.setText(">转账汇款");
		tvClassSecond.setVisibility(View.VISIBLE);
		
		
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
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if (id == 0) {
			Intent intent = new Intent();
			intent.putExtra("transtype", ">手机到手机转账");
			intent.setClass(TransferMain.this, TransAccSelect.class);
			TransferMain.this.startActivity(intent);
		}else if(id==1){
			Intent intent = new Intent();
			intent.putExtra("transtype", ">手机到签约账户转账");
			intent.setClass(TransferMain.this, TransAccSelect.class);
			TransferMain.this.startActivity(intent);
		}
	}
	
	class BtnTransMainPh implements OnClickListener{
		public void onClick(View v){
			Intent trans_main_ph = new Intent();
			trans_main_ph.setClass(TransferMain.this, TransferActivity.class);
			startActivity(trans_main_ph);
		}
	}
	class BtnTransMainAcc implements OnClickListener{
		public void onClick(View v){
			Intent trans_main_acc = new Intent();
			trans_main_acc.setClass(TransferMain.this, TransferAccActivity.class);
			startActivity(trans_main_acc);
		}
	}
	class BtnMainCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transmain_intent = new Intent();
    		transmain_intent.putExtra("flag", "failed");
    		transmain_intent.putExtra("info", "The transfer is canceled");
    		transmain_intent.setClass(TransferMain.this, TransResult.class);
    		startActivity(transmain_intent);
    	}
    }
    class BtnHelpCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransferMain.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
    class BtnNowCL implements OnClickListener{
    	public void onClick(View v){
    		Intent transhelp_intent = new Intent();
    		transhelp_intent.putExtra("flag", "failed");
    		transhelp_intent.putExtra("info", "The transfer is canceled");
    		transhelp_intent.setClass(TransferMain.this, TransResult.class);
    		startActivity(transhelp_intent);
    	}
    }
}