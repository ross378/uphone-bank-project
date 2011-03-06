package com.ultrawise.android.bank.webservices.base.payment06;

public interface IPaymentPend {
	//获取缴费账号是否为激活状态
	public String verifyAccount(String usrNo,String paymentActNo,String paymentActPasswd);
	
	//修改缴费账号的状态
	public String updateAccountStatue(String usrNo,String paymentActNo);
	
	//获取缴费账号的余额
	public String getAccountBalance(String usrNo,String paymentActNo);
	
	//修改缴费账户的余额
	public String updateAccountBalance(String paymentName,double paymentAmt,String paymentActNo);
	

	
}
