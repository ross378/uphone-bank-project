package com.ultrawise.android.bank.webservices.base.account_management01;

import org.codehaus.jettison.json.JSONObject;

public interface IAccountAdd {

	/**
	 * 添加账户
	 * 
	 * @author hosolo
	 * @param accountType
	 * @param account
	 * @param accountNickName
	 * @param password
	 * @return true/false
	 */
	public String addAccount(String userNo, String accId, String accountType,
			String account, String accountNickName, String password);

}
