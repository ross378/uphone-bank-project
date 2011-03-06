package com.ultrawise.android.bank.webservices.base.account_Query02;

import java.util.List;

public interface IAccountQueryInfo {
	
	public List<String> getAccountQueryByType(String type);
	public List<String> getAccountQueryById(String id);
	public List<String> getAccType();
	public List<String> getByTime(String start,String end);
}
