package com.ultrawise.android.bank.webservices.base.account_management01;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

public interface IAccountPre {
	/**
	 * 设置首选账户
	 * 
	 * @author hosolo
	 * @param account
	 * @return true/false
	 */
	public boolean setPreAccount(String userNo,String account);

	/**
	 * 获取首选账户
	 * 
	 * @author hosolo
	 * @param userNo
	 * @return pre account
	 */
	public String getPreAccount(String userNo);

	/**
	 * 获取未设置为首选账户
	 * 
	 * @author hosolo
	 * @param userNo
	 * @return array unpre account
	 */
	public List<String> getUnpreAccount(String userNo);

}
