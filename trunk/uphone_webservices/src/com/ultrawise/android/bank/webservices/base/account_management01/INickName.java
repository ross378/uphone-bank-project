package com.ultrawise.android.bank.webservices.base.account_management01;

import org.codehaus.jettison.json.JSONObject;

public interface INickName {

	/**
	 * 设置账户别名
	 * 
	 * @author hosolo
	 * @param account
	 * @param nickName
	 * @return true/flase
	 */
	public String setNickName(String account, String nickName);

}
