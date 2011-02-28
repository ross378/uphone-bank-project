package com.urltrawise.android.bank.webservices.manager.account_query02;

import com.urltrawise.android.bank.webservices.implement.account_query02.AccountDAOQuery;

public class AccountQueryManager {
	private AccountDAOQuery accountQueryDAO=null;
	private  static AccountQueryManager accountQueryMgr=null;
	private AccountQueryManager(){
	};
	static{						//类加载事就调用
		if(accountQueryMgr==null){//如果accountQueryM存在就直接用内存里面的,如果不存在就直接创建一个
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
	//私有防止外界调用
	private AccountDAOQuery getDao() {
		return accountQueryDAO;
	}
	
	public String getAccountQueryById(String id)
	{
		return getQueryById(id);
	}
	private String getQueryById(String id) {
		return accountQueryDAO.getAccountQueryById(id);
	}
}
