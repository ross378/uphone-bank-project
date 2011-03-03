package com.ultrawise.android.bank.webservices.base.payment06;

public interface IPaymentServices {
	//获取便捷服务的项目名称
	public String getServicesName(String userNo);
	
	//获取用户缴费的账户
	public String getUserAccount(String userNo);
	
	//获取手机缴费的信息
	public String phonePaymentDetail(String paymentName,String paymentActNo,double paymentAmt,String paymentPhoneNum);
	
	//确认手机缴费
	public String phonePayment(String paymenName,double paymentAmt,String paymentActNo,String paymentActPasswd,String paymentPhoneNum);
	
	
	
	
	
}
