package com.ultrawise.android.bank.base;

import java.util.HashMap;

public interface ICreditCard {
	/**
	 * 信用卡开卡
	 * @param userInfo   
	 * @return 			  开卡是否成功
	 */
	public boolean openCard(HashMap<String,String> userInfo);
	
	/**
	 * 信用卡销卡
	 * @param userInfo		
	 * @return				销卡是否成功
	 */
	public boolean destroyCard(HashMap<String,String> userInfo);
	
	/**
	 * 查询信用卡的还款信息
	 * @param account	
	 * @return			信用卡的还款信息
	 */
	public HashMap<String,String> getCreditRepaymentInfor(String account);
}
