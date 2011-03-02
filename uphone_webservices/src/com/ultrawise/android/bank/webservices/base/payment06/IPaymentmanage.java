package com.ultrawise.android.bank.webservices.base.payment06;

public interface IPaymentmanage {
	//获取缴费项目的名称
	public String getPaymentmanageName();
	
	//获取用户已经开通的缴费项目名称
	public String getUserOpenPayment(String userNo);
	
	//修改缴费项目的状态
	public String updatePayment(String userNo,String paymentName);
}
