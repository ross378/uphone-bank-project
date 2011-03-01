package com.ultrawise.android.bank.webservices.base.account_management01;

import org.codehaus.jettison.json.JSONObject;

public interface IBind {

	/**
	 * 账户绑定
	 * @author hosolo
	 * @param unbindAccount password
	 * @return true/false
	 */
	public String setBind(String unbindAccount,String password);
}
