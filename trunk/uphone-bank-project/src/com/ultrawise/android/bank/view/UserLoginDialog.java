package com.ultrawise.android.bank.view;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * 用户登陆提示对话框Activity
 * @author Administrator
 *
 */
public class UserLoginDialog extends Activity {
	/** Called when the activity is first created. */
	//登陆情况标志
	private int loginFlag=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        
       
        TextView tvflag = (TextView)findViewById(R.id.flag);
    	TextView tvshow = (TextView)findViewById(R.id.info);
    	Button btnok = (Button)findViewById(R.id.okBtn);
    	
        Intent receive_intent = getIntent();
        String flag = receive_intent.getStringExtra("flag");
        String info = receive_intent.getStringExtra("info");
        tvflag.setText(flag);
        tvshow.setText(info);
        //获得登陆情况标志
        loginFlag=Integer.parseInt(receive_intent.getStringExtra("loginFlag"));
        
        btnok.setOnClickListener(new BtnOkCL());
    }
    
    class BtnOkCL implements OnClickListener{
		public void onClick(View v) {
			Intent intent = new Intent();
			//loinFlag==2表示登陆成功
			if(loginFlag==2){
				intent.setClass(UserLoginDialog.this, ABankMain.class);
    		//其他值为登陆失败
			}else{
				intent.setClass(UserLoginDialog.this, UserLogin.class);
			}
			startActivity(intent);
		}
    }
}
