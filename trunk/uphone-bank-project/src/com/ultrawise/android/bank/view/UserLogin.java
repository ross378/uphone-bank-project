package com.ultrawise.android.bank.view;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ultrawise.android.bank.view.ABankMain.CreditButtonListener;
import com.ultrawise.android.bank.view.DepositeRates.BackImageViewListener;
import com.ultrawise.android.bank.view.DepositeRates.PhoneBankImageViewListener;

import com.ultrawise.android.bank.view.credit.CreditView;
import com.ultrawise.android.bank.view.transfer.R;
import com.ultrawise.android.bank.view.transfer.TransResult;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author weijuan
 * @Date 2011-1-18
 * 手机用户使用手机银行需要的登陆Activity-UserLogin
 *
 */
public class UserLogin extends Activity {
	public static String userNO = "";
	//登陆按钮
	private Button userLoginButton=null;
	//用户名输入框
	private EditText nameEditText=null;
	//密码输入框
	private EditText passwdEditText=null;
	//附加码输入框
	private EditText pyramidEditText=null;
	//用户名
	private String userName=null;
	//密码
	private String passwd=null;
	//附加码
	private String pyramid=null;
	//记录用户名的单选框
	private CheckBox checkBox = null;
	//从文件中读出的用户名和密码
	String result = "";
	
	
	private int randomNo = 0;
	private ImageButton randomButton = null;
	 private ImageView phoneBank = null;
	 private ImageView helper = null;
	 
	 private TextView extraCode = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.userlogin);
       
        //获得用户名输入框，并获得输入框中的值
        nameEditText=(EditText)findViewById(R.id.nameEdit);
        
        
        //获得登陆按钮，并设置登陆按钮的鼠标响应事件
        userLoginButton=(Button)findViewById(R.id.userLogin);
       // userLoginButton.setVisibility(View.VISIBLE);
        userLoginButton.setOnClickListener(new UserLoginButtonListener());
        //获得用户名输入框，并获得输入框中输入的值
        passwdEditText=(EditText)findViewById(R.id.passwdEdit);
        
        
        //获得密码输入框，并获得输入框中输入的值
        pyramidEditText=(EditText)findViewById(R.id.pyramidEdit);
        
        checkBox = (CheckBox)findViewById(R.id.notesPasswdCheckBox);
        
        //附加码显示控件
        extraCode = (TextView)findViewById(R.id.extraCode);
        Intent intent = getIntent();
        List<String> rand = intent.getStringArrayListExtra("key");
        extraCode.setText(rand.get(0));
        
        phoneBank = (ImageView)findViewById(R.id.btnMain);
        helper = (ImageView)findViewById(R.id.btnHelper);
        helper.setOnClickListener(new HelperImageViewListener());
        
        //从手机内存中读取已经保存的用户名和密码，并显示在对应的输入框中
		try {
			InputStream inStream = this.openFileInput("user.txt");
			result = RecordUser.getFile(inStream);
			if(!"".equals(result)||result != null){
				nameEditText.setText(result.split(":")[0]);
				passwdEditText.setText(result.split(":")[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
	}
	
	
	/**
	 * 登陆按钮响应
	 * @author Administrator
	 *跳转到登陆成功对话框Activity-UserLoginDialog
	 */
	class UserLoginButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			String flag="手机银行";
			String info=null;
			int loginFlag=0;
			userName=nameEditText.getText().toString();
			passwd=passwdEditText.getText().toString();
			pyramid=pyramidEditText.getText().toString();
			List<String> loginInfo = new ArrayList<String>();
			loginInfo.add(userName);
			loginInfo.add(passwd);
			loginInfo.add(pyramid);
			
			//判断用户名，密码，附加码为空或输入空白
			if(userName==null || userName.trim().length()==0||
			   passwd==null || passwd.trim().length()==0||
			   pyramid==null || pyramid.trim().length()==0){
				info="用户名，密码，附加码不能为空!";
				loginFlag=1;
			//判断正确的用户名，密码
			}else
			{
				
				List<String> backInfo = WebTools.connectHttp(10, loginInfo);
				
				if(backInfo.get(0).equals("true"))
				{
					//勾选了记录用户 ，则将用户名和密码保存在手机的内存中
					if(checkBox.isChecked()){
						String userinfor = userName+":"+passwd;
						try {
							OutputStream outStream = UserLogin.this.openFileOutput("user.txt", Context.MODE_PRIVATE);
							RecordUser.saveFile(outStream, userinfor);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					info="尊敬的客户"+backInfo.get(1)+"\n您上次登陆的时间为\n8:15\n这是你第3次登录系统！\n";
					loginFlag = 2;
					FinancialConsultation.loggingStatus = true;
				}else
				{
					info = backInfo.get(1);
					loginFlag = 3;
				}
			}
//			
			
			Intent intent=new Intent();
				intent.putExtra("flag", flag);
				intent.putExtra("info", info);
				intent.putExtra("loginFlag",loginFlag+"");
				intent.setClass(UserLogin.this,UserLoginDialog.class);
				UserLogin.this.startActivity(intent);
					
		}
		
	}
	
	
	
 class PhoneBankImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 UserLogin.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(UserLogin.this, ABankMain.class);
			 UserLogin.this.startActivity(intent);
		 }
	 }
	 
	 class HelperImageViewListener implements OnClickListener{
		 
		 public void onClick(View args0){
			 UserLogin.this.finish();
			 Intent intent = new Intent();
			 intent.setClass(UserLogin.this, FinancialConsultation.class);
			 UserLogin.this.startActivity(intent);
		 }
	 }
	 
//	 public void Text(){
//		// 移动运营商允许每次发送的字节数据有限,我们可以使用Android给我们提供 的短信工具
//
//		 SmsManager smsManager = SmsManager.getDefault();
//		 PendingIntent sendIntent = PendingIntent.getBroadcast(SmsActivity.this, 0, new Intent(), 0);
//		 // 如果短信没有超过限制长度,则返回一个长度的List
//		 ArrayList<String> messages = smsManager.divideMessage(msg);
//		 for (String message : messages) {
//		 smsManager.sendTextMessage(mobile,// 接收方的手机号码
//		 null,// 发送方的手机号码
//		 message,// 信息内容
//		 sendIntent,// 发送是否成功的回执
//		 null// 接收是否成功的回执
//
//
//	 }

}
