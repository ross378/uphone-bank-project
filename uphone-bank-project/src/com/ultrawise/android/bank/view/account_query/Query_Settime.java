package com.ultrawise.android.bank.view.account_query;

import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Query_Settime extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_set_time);
        this.setTitle("设置起始时间");
	}
}
