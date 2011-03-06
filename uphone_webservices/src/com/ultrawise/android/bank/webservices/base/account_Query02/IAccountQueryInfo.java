package com.ultrawise.android.bank.webservices.base.account_Query02;

import java.util.List;

public interface IAccountQueryInfo {
	
	public List<String> getAccountQueryByType(String name);
	public String getAccountQueryById(int id);
	public List<String> getAccType();
}
