package com.ultrawise.android.bank.webservices.implement.account_query02;

import java.util.List;

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
	
	
	public List<String> getAccountQueryById(String id)
	{
		return Iacc.getAccountQueryById(id);
	}
	
	public List<String> getAccountQueryByType(String type)
	{
		return Iacc.getAccountQueryByType(type);
	}
	
	public List<String> getAccType(){
	return Iacc. getAccType();
	}
}
