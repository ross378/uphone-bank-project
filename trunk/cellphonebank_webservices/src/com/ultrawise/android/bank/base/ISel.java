package com.ultrawise.android.bank.base;

import java.util.Date;
import java.util.HashMap;

public interface ISel {
	/**
	 * 查看自助缴费的信息
	 * @param paymentName   
	 * @param paymentActNo	
	 * @param paymentAmt	
	 * @param paymentPhoneNum 
	 * @return  缴费项目的信息  键值对
	 */
	public HashMap<String,String> getRechargeInfo(String paymentName,String paymentActNo,double paymentAmt,String paymentPhoneNum);
	
	/**
	 * 确认自助缴费
	 * @param paymentName   缴费项目名称
	 * @param paymentAmt	缴费金额
	 * @param paymentActNo	缴费账号
	 * @param paymentActPasswd  缴费账号密码
	 * 
	 * id  
	 * @return
	 */
	public HashMap<String,String> recharge(String paymentName,double paymentAmt,String paymentActNo,String paymentActPasswd);
	
	/**
	 * 修改缴费项目的状态
	 * @param paymentName	缴费项目的名称
	 * @param statue		缴费项目的状态
	 * @return    			状态修改是否修改成功
	 */
	public boolean updatePaymentState(String paymentName,String statue);
	
	/**
	 * 
	 * @param userid					用户号
	 * @param paymentDateNow			查询的结束时间
	 * @param paymentDateBeforeAMonth	查询的开始时间
	 * @return    						在查询的时间段内的历史缴费记录
	 */
	public HashMap<String,String> getPaymentHistory(String userid,Date paymentDateNow,Date paymentDateBeforeAMonth);
	
	/**
	 * 查询某条历史缴费记录的详细信息
	 * @param id 		历史缴费记录的id
	 * @return			历史缴费记录的详细记录
	 */
	public HashMap<String,String> getPaymentHisInfo(String id);
}	
