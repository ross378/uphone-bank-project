package com.ultrawise.android.bank.webservices.base.payment06;

import java.util.Date;

public interface IPaymentLastMonth {
	//获取一个月内的缴费项目名称
	public String[] getLastPaymentName(String serNo,String userNo,Date paymentDateNow,Date paymentDateBeforeAMonth);
	
	//获取某条缴费记录的详细信息
	public String getLastPaymentDetail(String serNo,String userNo,Date paymentDateNow,Date paymentDateBeforeAMonth);
}
