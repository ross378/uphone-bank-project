package com.ultrawise.android.bank.webservices.implement.account_query02;

import com.ultrawise.android.bank.webservices.base.account_Query02.IAccountQueryInfo;
import com.ultrawise.android.bank.webservices.implement.account_query02.AccountDAOQuery;

public class AccountQueryManager {
	private IAccountQueryInfo Iacc=null;
	private  static AccountQueryManager accountQueryMgr=null;
	private AccountQueryManager(){
	};
	static{						//class load creat Iacc
		if(accountQueryMgr==null){//
			accountQueryMgr=new AccountQueryManager(); 
			accountQueryMgr.setDao(new AccountDAOQuery());
		}
	}
	public static AccountQueryManager getInstance(){
		return accountQueryMgr;
	}
	private void setDao(AccountDAOQuery accountQueryDAO) {
		this.Iacc=accountQueryDAO;
	}
	//get Dao
	private IAccountQueryInfo getDao() {
		return Iacc;
	}
	
	public String getAccountQueryById(int account)
	{
		return getQueryById(account);
	}
	private String getQueryById(int account) {
		return Iacc.getAccountQueryById(account);
	}
	
	public String getAccountQueryByType(String type)
	{
		return getQueryByType(type);
	}
	private String getQueryByType(String type) {
		return Iacc.getAccountQueryByType(type);
	}
}
