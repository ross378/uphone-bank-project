package com.ultrawise.android.bank.webservices.base.account_management01;

import org.codehaus.jettison.json.JSONObject;

public interface IAccountDel {

	/**
	 * 删除账户
	 * 
	 * @author hosolo
	 * @param bindAccount
	 * @return true/false
	 */
	public String deleteAccount(String bindAccount);

}
