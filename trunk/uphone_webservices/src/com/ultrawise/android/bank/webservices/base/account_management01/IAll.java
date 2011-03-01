package com.ultrawise.android.bank.webservices.base.account_management01;

import org.codehaus.jettison.json.JSONObject;

public interface IAll {
	public final static String USER_NO="userno";
	/**
	 * 获取用户号
	 * 
	 * @author hosolo
	 * @return user no
	 */
	public String getUserNo();

	/**
	 * 获取所有的账户类型
	 * 
	 * @author hosolo
	 * @return array account type
	 */
	public String[] getAccType();

	/**
	 * 获取已经绑定的账户
	 * 
	 * @author hosolo
	 * @param accType
	 * @return array account
	 */
	public String[] getBindAcc(String UserNo, String accType);

	/**
	 * 获取已经绑定的账户别名
	 * 
	 * @author hosolo
	 * @param account
	 * @return nick name
	 */
	public String getNickName(String account);

	/**
	 * 获取网点地址
	 * 
	 * @author hosolo
	 * @return array net
	 */
	public String[] getNet();

	/**
	 * 获取工本费用
	 * 
	 * @author hosolo
	 * @return cost
	 */
	public String getCost();
}
