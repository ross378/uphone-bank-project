package com.ultrawise.android.bank.webservices.base.payment06;

public interface IPaymentmanage {
	//获取缴费项目的名称
	public String getPaymentmanageName();
	
	//修改缴费项目的状态
	public String updatePayment(String paymentName,String value);
}
