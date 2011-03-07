package com.ultrawise.android.bank.webservices.base.payment06;

public interface IPamymentAccountSelect {
	//首选账户
	public String getFirstAccNum(String userNo);
    //其他账户
	public String getAccountTypeAndNum();
	
}
