package com.urltrawise.android.bank.webservices.base.payment06;

import java.util.Calendar;

import org.codehaus.jettison.json.JSONObject;


public interface IPaymentInfo {
	//根据用户输入的日期查询历史记录
	public JSONObject ShowHistoryDetail(String serNo,String userNo,Calendar paymentStartDate,Calendar paymentEndDate);
	
	//显示某条记录的详细信息
	public JSONObject ShowAccountDetaul(String serNo,String userNo,Calendar paymentDateNow,Calendar paymentDateBeforeAMonth,String paymentName);
}
