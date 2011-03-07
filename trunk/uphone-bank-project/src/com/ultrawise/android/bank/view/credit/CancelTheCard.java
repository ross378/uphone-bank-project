package com.ultrawise.android.bank.view.credit;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.consum_webservices.CreditClient;
import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
/**
 * 
 * @author weijuajn
 * @date 2011-1-18
 * 销卡布局Activity-CancelTheCard
 *
 */

public class CancelTheCard extends Activity {
	private String funNo="451";
	//销卡按钮
	private Button cancelCardButton=null;
	//信用卡号输入框
	private EditText orderidEdit=null;
	//证件类型选择下拉框
	private Spinner pakitSpinner=null;
	//证件号输入框
//	private EditText pakitNoEdit=null;
	//用户名号输入框
//	private EditText userNameEdit=null;
	//手机号输入框
	//private EditText mobileNoEdit=null;
	//信用卡密码输入框
	private EditText creditPasswdEdit=null;
	//信用卡号
	//private String creditNo=null;
	//用户名
	//private String userName=null;
	//证件类型值
	//private String pakitValue=null;
	//证件号
	//private String pakitNo=null;
	//手机号
	//private String mobileNo=null;
	//信用卡密码
	//private String creditPasswd=null;
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	ImageView btnReturn;
	  List<String> l=new ArrayList<String>();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelthecard);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.GONE);
    	intent = new Intent();
        TextView tvCredit= (TextView)this.findViewById(R.id.class_first);
        tvCredit.setText("首页>信用卡>销卡");
        tvCredit.setTextSize(13);
        tvCredit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent.setClass(CancelTheCard.this, CreditView.class);
				 CancelTheCard.this.startActivity(intent);
			}
		});
        tvCredit.setVisibility(View.VISIBLE);
        
        // 返回键设定
		btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 intent.setClass(CancelTheCard.this, CreditView.class);
				 CancelTheCard.this.startActivity(intent);
			}
		});
      //设置底部按钮
		btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
		//btnCoustom.setImageResource(R.drawable.cardbg_sy_b);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(CancelTheCard.this, ABankMain.class);
				CancelTheCard.this.startActivity(intent);
			}
		});
		//btnCoustom.setVisibility(View.VISIBLE);
		
		btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(CancelTheCard.this, FinancialConsultation.class);
				CancelTheCard.this.startActivity(intent);
			}
		});
        
        //获得证件类型控件对象
        pakitSpinner=(Spinner)findViewById(R.id.pakitSpinner1);
        //获得信用卡号选择框对象
        orderidEdit=(EditText)findViewById(R.id.orderidEdit);
        
        //获得销卡按钮对象
        cancelCardButton=(Button)findViewById(R.id.cancelCard1);
        cancelCardButton.setOnClickListener(new CancelCardButtonListener());
        
        //获得用户名输入框对象
        //userNameEdit=(EditText)findViewById(R.id.userNameEdit1);
        //获得证件号输入框对象
       // pakitNoEdit=(EditText)findViewById(R.id.pakitNoEdit1);
        //获得手机号输入框对象
       // mobileNoEdit=(EditText)findViewById(R.id.mobileNoEdit1);
      //获得信用卡密码输入框对象
        creditPasswdEdit=(EditText)findViewById(R.id.creditpasswordEdit1);
        final String[] arrs1=new String[]{"身份证","学生证","工作证","军人证"};
      /*  final String[] arrs2=new String[5];
        for(int i=0;i<5;i++){
        	arrs2[i]="1111222233334444"+i;
        }*/
        pakitSpinner= (Spinner)findViewById(R.id.pakitSpinner1); 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrs1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   
        pakitSpinner.setAdapter(adapter);  
        pakitSpinner.setSelection(1,true);
        pakitSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                    arg0.setVisibility(View.VISIBLE);

                }
                public void onNothingSelected(AdapterView<?> arg0){
                }

            });
        /*creditNoSpinner= (Spinner)findViewById(R.id.creditNoSpinner1); 
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrs2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   
        creditNoSpinner.setAdapter(adapter2);  
        creditNoSpinner.setSelection(1,true);
        creditNoSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                    arg0.setVisibility(View.VISIBLE);

                }
                public void onNothingSelected(AdapterView<?> arg0){
                }

            });*/
        //creditNo=creditNoSpinner.getSelectedItem().toString();
        
	}
	class CancelCardButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			String orderid=orderidEdit.getText().toString();
	       // String name=userNameEdit.getText().toString();
	        //String patype= pakitSpinner .getSelectedItem().toString();
	       // String panum= pakitNoEdit.getText().toString();
	       String password=creditPasswdEdit.getText().toString();
	       l.add(orderid);
	     //  l.add(name);
	      // l.add(patype);
	    //   l.add(panum);
	       l.add(password);
	       System.out.println(l.size());
			List<String> accuss=CreditClient.connectHttp(funNo, l);
			int cancelFlag=0;
			String info=null;
			String flag=null;
			
			if(accuss.get(0).equals("false")){
				cancelFlag=1;
				flag="失败提示：";
				info="        销卡失败!请确认所               \n       填信息是否正确!            ";
			}else{
				cancelFlag=2;
				flag="成功提示：";
				info="         销卡成功!这只是口头销卡设置，\n需要到实体银行办理销卡手续完成销卡.";
			}
			Intent intent = new Intent();
			intent.putExtra("flag", flag);
			intent.putExtra("info",info);
			intent.putExtra("cancelFlag",cancelFlag+"");
    		intent.setClass(CancelTheCard.this, CancelCardDialog.class);
    		CancelTheCard.this.startActivity(intent);
			
		}
		
	}
}
