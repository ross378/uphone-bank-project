package com.ultrawise.android.bank.base;

import java.util.HashMap;

public interface ITrans {
	/**
	 * 手机到手机的转账
	 * @param account	
	 * @param password	
	 * @param amtph		
	 * @param amtnum	
	 * @return			转账的详细信息	
	 */
	public HashMap<String,String> getTargetPhoneInfo(String account,String password,String amtph,double amtnum);
	
	/**
	 * 确认转账
	 * @param account	
	 * @param password	
	 * @param amtph		
	 * @param amtnum	
	 * @return			转账返回的详细信息
	 */
	public HashMap<String,String> transfeAct(String account,String password,String amtph,double amtnum);
	
	/**
	 * 某条明细的查询
	 * @param id	
	 * @return		某条明细的详细信息
	 */
	public HashMap<String,String> getListQueryInfo(String type,String id);
	
	/**
	 * 某条来账的查询
	 * @param id	
	 * @return		某条来账的详细信息
	 */
	public HashMap<String,String> getComeQueryInfo(String type,String id);
	
	/**
	 * 设置记录的描述信息
	 * @param serNo		
	 * @param detail	
	 * @return	                      是否设置成功
	 */
	public boolean setDetail(String serNo,String detail);
	
	/**
	 * 查询某条历史缴费记录的详细信息
	 * @param id 		历史缴费记录的id
	 * @return			历史缴费记录的详细记录
	 */
	public HashMap<String,String> getPaymentHisInfo(String id);
	
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
	 * @param paymentNum  	缴费的目标号
	 * @return
	 */
	public HashMap<String,String> recharge(String paymentName,double paymentAmt,String paymentActNo,String paymentActPasswd,String paymentNum,String operator);
	
	/**
	 * 根据未缴费项的id查询详细信息
	 * @param id
	 * @return	缴费项的详细信息
	 */
	public HashMap<String,String> getPaymentInfo(String userid,String id);
	/**
	 * 自助缴费的付款
	 * @author 王   亭
	 * 2011-3-31
	 * @param paymentName
	 * @param paymentAmt
	 * @param paymentActNo
	 * @param paymentActPasswd
	 * @param paymentNum
	 * @param charger
	 * @return
	 */
	public HashMap<String, String> payment(String paymentName,double paymentAmt,String paymentActNo,String paymentActPasswd,String charger);
}
