package com.ultrawise.android.bank.webservices.base.account_management01;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

public interface IAll {
	public final static String USER_NO = "userno";

	/**
	 * 获取用户号
	 * 
	 * @author hosolo
	 * @return user no
	 */
	public List<String> getUserNo();

	/**
	 * 获取所有的账户类型
	 * 
	 * @author hosolo
	 * @return array account type
	 */
	public List<String> getAccType();

	/**
	 * 获取已经绑定的账户
	 * 
	 * @author hosolo
	 * @param accType
	 * @return array account
	 */
	public List<String> getBindAcc(String UserNo, String accType);

	/**
	 * 获取已经绑定的账户别名
	 * 
	 * @author hosolo
	 * @param account
	 * @return nick name
	 */
	public String getNickName(String account);

	/**
	 * 获取挂失工本费用
	 * 
	 * @author hosolo
	 * @return cost
	 */
	public String getLossCost();

	/**
	 * 获取预约工本费
	 * 
	 * @author hosolo
	 * @return
	 */
	public String getOrderCost();
}
