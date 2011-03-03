package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.util.ArrayList;
import java.util.List;

import com.ultrawise.android.bank.webservices.base.account_management01.IAll;

/**
 * 从txt文件里面读取数据，返回数据
 * 
 * @author hosolo
 * 
 */
public class All implements IAll {

	/**
	 * 功能号 0102
	 */
	public List<String> getAccType() {
		// TODO 获取账户类型
		List<String> lstStr = new ArrayList<String>();
		lstStr.add("储蓄卡");
		lstStr.add("信用卡");

		return lstStr;
	}

	public String[] getBindAcc(String UserNo, String accType) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getNet() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNickName(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 功能号0101
	 */
	public List<String> getUserNo() {
		// 获取用户号
		return null;
	}

}
