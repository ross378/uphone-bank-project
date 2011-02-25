package com.ultrawise.android.bank.view.credit;

import com.ultrawise.android.bank.view.ABankMain;
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
	//销卡按钮
	private Button cancelCardButton=null;
	//信用卡号选择下拉框
	private Spinner creditNoSpinner=null;
	//证件类型选择下拉框
	private Spinner pakitSpinner=null;
	//证件号输入框
	private EditText pakitNoEdit=null;
	//用户名号输入框
	private EditText userNameEdit=null;
	//手机号输入框
	private EditText mobileNoEdit=null;
	//信用卡密码输入框
	private EditText creditPasswdEdit=null;
	//信用卡号
	private String creditNo=null;
	//用户名
	private String userName=null;
	//证件类型值
	private String pakitValue=null;
	//证件号
	private String pakitNo=null;
	//手机号
	private String mobileNo=null;
	//信用卡密码
	private String creditPasswd=null;
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelthecard);
        
        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
        iv_now.setVisibility(View.GONE);
    	intent = new Intent();
        TextView tvCredit= (TextView)this.findViewById(R.id.class_first);
        tvCredit.setText("首页>信用卡>销卡");
        tvCredit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 intent.setClass(CancelTheCard.this, CreditView.class);
				 CancelTheCard.this.startActivity(intent);
			}
		});
        tvCredit.setVisibility(View.VISIBLE);
        
      //设置底部按钮
		btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
		btnCoustom.setImageResource(R.drawable.cardbg_sy_b);
		btnCoustom.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(CancelTheCard.this, ABankMain.class);
				CancelTheCard.this.startActivity(intent);
			}
		});
		btnCoustom.setVisibility(View.VISIBLE);
		
		btnMain = (ImageView) this.findViewById(R.id.btnHelper);
		btnMain.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.setClass(CancelTheCard.this, ABankMain.class);
				CancelTheCard.this.startActivity(intent);
			}
		});
        
        //获得证件类型控件对象
        pakitSpinner=(Spinner)findViewById(R.id.pakitSpinner);
        //获得信用卡号选择框对象
        creditNoSpinner=(Spinner)findViewById(R.id.creditNoSpinner);
        
        //获得销卡按钮对象
        cancelCardButton=(Button)findViewById(R.id.cancelCard);
        cancelCardButton.setOnClickListener(new CancelCardButtonListener());
        
        //获得用户名输入框对象
        userNameEdit=(EditText)findViewById(R.id.userNameEdit);
        //获得证件号输入框对象
        pakitNoEdit=(EditText)findViewById(R.id.pakitNoEdit);
        //获得手机号输入框对象
        mobileNoEdit=(EditText)findViewById(R.id.mobileNoEdit);
      //获得信用卡密码输入框对象
        creditPasswdEdit=(EditText)findViewById(R.id.creditpasswordEdit);
        
        
        final String[] arrs1=new String[]{"身份证","学生证","工作证","军人证"};
        final String[] arrs2=new String[10];
        for(int i=0;i<10;i++){
        	arrs2[i]="1111222233334444"+i;
        }
        pakitSpinner= (Spinner)findViewById(R.id.pakitSpinner); 
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
        creditNoSpinner= (Spinner)findViewById(R.id.creditNoSpinner); 
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

            });
        //creditNo=creditNoSpinner.getSelectedItem().toString();
	}
	class CancelCardButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			int cancelFlag=0;
			String info=null;
			String flag=null;
			userName=userNameEdit.getText().toString();
			creditNo="1111111111";
			pakitValue="身份证";
			
			pakitNo=pakitNoEdit.getText().toString();;
			mobileNo=mobileNoEdit.getText().toString();;
			creditPasswd=creditPasswdEdit.getText().toString();
			
			if(userName==null || userName.trim().length()==0 ||
				creditNo==null || creditNo.trim().length()==0 ||
				pakitValue==null || pakitValue.trim().length()==0 || 
				pakitNo==null || pakitNo.trim().length()==0 || 
				mobileNo==null || mobileNo.trim().length()==0 ||
				creditPasswd==null || creditPasswd.trim().length()==0){
				cancelFlag=1;
				flag="失败提示：";
				info="销卡失败!请确认所\n填信息是否正确!";
			}else{
				cancelFlag=2;
				flag="成功提示：";
				info="销卡成功！\n";
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
