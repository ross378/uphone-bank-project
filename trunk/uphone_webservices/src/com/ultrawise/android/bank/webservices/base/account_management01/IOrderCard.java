package com.ultrawise.android.bank.webservices.base.account_management01;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

public interface IOrderCard {

	/**
	 * 设为预约状态
	 * 
	 * @author hosolo
	 * @param account
	 * @return true/false
	 */
	public boolean setOrderCard(String account);

	/**
	 * 获取预约信息
	 * 
	 * @author hosolo
	 * @param account
	 * @return 预约的信息
	 */
	public List<String> getOrderInfo(String account);

	/**
	 * 获取换卡的网点
	 * 
	 * @author hosolo
	 * @return 一堆的网点
	 */
	public List<String> getNet();

	/**
	 * 获取网点的地址
	 * 
	 * @author hosolo
	 * @param net
	 * @return 一个诡异的地址
	 */
	public String getAddress(String net);

	/**
	 * 获取未预约的账号
	 * 
	 * @author hosolo
	 * @param userNo
	 * @param accType
	 * @return 一堆未预约的账号
	 */
	public List<String> getUnorderAcc(String userNo, String accType);
}
