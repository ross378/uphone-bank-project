package com.ultrawise.android.bank.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IQuery {
	/**
	 * 
	 * @param acc   
	 * @return   账号的别名
	 */
	public String getNickName(String acc);
	
	/**
	 * 账户管理，通过账户号查找账户的信息
	 * 
	 * @param account
	 * @return 账户信息键值对
	 */
	public HashMap<String,String> getAccInfo(String acc);
	
	/**
	 * 
	 * @param acc  
	 * @return	账户的预约信息键值对
	 */
	public HashMap<String,String> getOrderInfo(String acc);

	/**
	 * 	自助缴费时的激活判断
	 * @param paymentActNo  
	 * @return   是否激活
	 */
	public boolean acctIsActive(String paymentActNo);
	
	/**
	 * 转账模块
	 * @param id  
	 * @return	某条明细的信息  键值对
	 */
	public HashMap<String,String> getListQueryInfo(String id);
	
	/**
	 * 转账模块
	 * @param id  
	 * @return 某条来向账的信息  键值对
	 */
	public HashMap<String,String> getComeQueryInfo(String id);
	
	
}
