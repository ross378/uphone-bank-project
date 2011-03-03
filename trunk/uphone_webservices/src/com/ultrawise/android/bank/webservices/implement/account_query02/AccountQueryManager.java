package com.ultrawise.android.bank.webservices.implement.account_query02;

import com.ultrawise.android.bank.webservices.implement.account_query02.AccountDAOQuery;

public class AccountQueryManager {
	private AccountDAOQuery accountQueryDAO=null;
	private  static AccountQueryManager accountQueryMgr=null;
	private AccountQueryManager(){
	};
	static{						//class load creat accountQueryDAO
		if(accountQueryMgr==null){//
			accountQueryMgr=new AccountQueryManager(); 
			accountQueryMgr.setDao(new AccountDAOQuery());
		}
	}
	public static AccountQueryManager getInstance(){
		return accountQueryMgr;
	}
	private void setDao(AccountDAOQuery accountQueryDAO) {
		this.accountQueryDAO=accountQueryDAO;
	}
	//get Dao
	private AccountDAOQuery getDao() {
		return accountQueryDAO;
	}
	
	public String getAccountQueryById(int account)
	{
		return getQueryById(account);
	}
	private String getQueryById(int account) {
		return accountQueryDAO.getAccountQueryById(account);
	}
	
	public String getAccountQueryByName(String account)
	{
		return getQueryByName(account);
	}
	private String getQueryByName(String account) {
		return accountQueryDAO.getAccountQueryByName(account);
	}
}
