package com.ultrawise.android.bank.base;

public interface IUpdate {

	/**
	 * 设置账户的别名
	 * 
	 * @param accNo
	 * @param name
	 * @return 是否设置成功
	 */
	boolean setNickName(String accNo, String name);

	/**
	 * 账户绑定
	 * 
	 * @param account
	 * @return 是否绑定成功
	 */
	boolean setBind(String accNo,String password);

	/**
	 * 预约换卡
	 * 
	 * @param accNo
	 * @return 是否预约成功
	 */
	boolean setOrderCard(String accNo, String aliss, String reason,
			String net, String netaddress,double cost);

	/**
	 * 挂失账户
	 * 
	 * @param accNo
	 * @return 是否挂失成功
	 */
	boolean lossRegister(String accNo);

	/**
	 * 删除账户
	 * 
	 * @param accNo
	 * @return 是否删除成功
	 */
	boolean deleAcc(String accNo);

	/**
	 * 激活账户
	 * 
	 * @param accNo
	 * @param accPwd
	 * @return 是否激活成功
	 */
	boolean setActActive(String accNo, String accPwd);

}
