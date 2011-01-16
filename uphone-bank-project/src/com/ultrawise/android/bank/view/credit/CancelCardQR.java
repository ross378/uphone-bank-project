package com.ultrawise.android.bank.view.credit;


import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CancelCardQR extends Activity {
	private Button cancelCardButton=null;
	private String creditNo=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelcardqr);
        creditNo=this.getIntent().getStringExtra("creditNo");
        cancelCardButton=(Button)findViewById(R.id.cancelCard);
        cancelCardButton.setOnClickListener(new CancelCardButtonListener());
	}
	class CancelCardButtonListener implements OnClickListener{

		public void onClick(View arg0) {
			Toast.makeText(CancelCardQR.this, "恭喜你，你已经通过手机成功办理了卡号为"+creditNo+"信用卡的销卡服务，此卡从现在起将不能再使用！", Toast.LENGTH_SHORT).show();
			
		}
		
	}
}
