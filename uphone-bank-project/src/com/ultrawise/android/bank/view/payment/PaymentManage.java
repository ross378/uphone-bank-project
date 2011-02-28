package com.ultrawise.android.bank.view.payment;

import java.util.List;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.payment.TreeViewAdapter.TreeNode;
import com.ultrawise.android.bank.view.transfer.R;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.view.View.OnClickListener;

public class PaymentManage extends Activity {//缴费项目管理
	
	Intent payment_intent = new Intent();
	ListView payment_manage_list = null;
	ExpandableListView expandableList;     
	TreeViewAdapter adapter;
	public String[] groups = { "   已开通项目", "   所有项目"};     
	public String[][]  child= {     
	            { "水费", "电费", "报纸订阅"},     
	            { "平安保险", "人寿保险", "交通罚款","水费", "电费", "报纸订阅"},          
	    };
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_tree);
        
        //Button btn_paymanage_ok = (Button)findViewById(R.id.btn_paymana_ok);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        //iv_now.setVisibility(View.VISIBLE);
        
        TextView tvClassFirst = (TextView)this.findViewById(R.id.class_first);
		tvClassFirst.setText("首页>");
		tvClassFirst.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent payment_intent = new Intent();
				payment_intent.setClass(PaymentManage.this, ABankMain.class);
				PaymentManage.this.startActivity(payment_intent);
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
       
		adapter=new TreeViewAdapter(this,TreeViewAdapter.PaddingLeft>>1);
		expandableList =(ExpandableListView) findViewById(R.id.ExpandableListView01);
		adapter.RemoveAll();     
        adapter.notifyDataSetChanged();
        List<TreeNode> treeNode = adapter.GetTreeNode();     
        for(int i=0;i<groups.length;i++)     
        {     
            TreeNode node=new TreeNode();     
            node.parent=groups[i];     
            for(int ii=0;ii<child[i].length;ii++)     
            {     
                node.childs.add(child[i][ii]);     
            }     
            treeNode.add(node);     
        }     
             
        adapter.UpdateTreeNode(treeNode);          
        expandableList.setAdapter(adapter);     
        expandableList.setOnChildClickListener(new OnChildClickListener(){

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent();
				intent.setClass(PaymentManage.this, PaymentManageDetail.class);
				PaymentManage.this.startActivity(intent);
				return false;
			}     
        });
        
        
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

	class BtnManaOK implements OnClickListener{
		public void onClick(View v){
			Intent btnok_intent = new Intent();
			btnok_intent.putExtra("flag", "设置成功");
			btnok_intent.putExtra("info", "缴费项目管理设置成功");
			btnok_intent.setClass(PaymentManage.this, PaymentResult.class);
			PaymentManage.this.startActivity(btnok_intent);
		}
	}
}