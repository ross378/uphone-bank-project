package com.ultrawise.android.bank.webservices.base.account_Query02;

import java.util.List;

public interface IAccountQueryInfo {
	
	public List<String> getAccountQueryByType(String type);//按类别编号查询账户号
	public List<String> getAccountQueryById(String id);//按账户号查询
	public List<String> getAccType();//按账户类别查询
	public List<String> getByTime(String no,String start,String end);//按时间查询
	public List<String> getByTimeAcct(String no,String type);//按账户id和操作类别查询例如：(12120114,"转账")
	
}
