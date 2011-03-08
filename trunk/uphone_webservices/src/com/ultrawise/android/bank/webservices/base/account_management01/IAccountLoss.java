package com.ultrawise.android.bank.webservices.base.account_management01;

import org.codehaus.jettison.json.JSONObject;

public interface IAccountLoss {
	/**
	 * 设置挂失
	 * 
	 * @author hosolo
	 * @param unlossAccount
	 * @return true/false
	 */
	public boolean setLoss(String unlossAccount);
}
