package com.ultrawise.android.bank.base;

import java.util.List;
import java.util.Map;

import com.ultrawise.android.bank.Enum.EAccState;

public interface IAccSystem {
	/**
	 * 添加账户，需要用户号，账户号，账户类型，账户别名，账户密码
	 * 
	 * @param userid
	 * @param accNo
	 * @param accType
	 * @param accNickName
	 * @param accPwd
	 * @return 是否添加成功
	 */
	boolean addAcc(String userId, String accNo, String accType,
			String accNickName, String accPwd);

	/**
	 * 设置首选账户
	 * 
	 * @param userId
	 * @param accNo
	 * @return 是否设置成功
	 */
	boolean setPreAcc(String userId, String accNo);

	/**
	 * 取得所有账户类型
	 * 
	 * @return 账户类型列表
	 */
	List<String> getAccTypeAll();

	/**
	 * 取得首选账户
	 * 
	 * @param userId
	 * @return 首选账户
	 */
	String getPreAcc(String userId);

	/**
	 * 取得账户类型，除去定期账户
	 * 
	 * @return 账户类型列表
	 */
	List<String> getAccType();

	/**
	 * 根据账户类型取得所有账户，需要给出要什么条件的，比如是否绑定的，是否激活的等
	 * 
	 * @param accType
	 * @param accState
	 * @return 账户列表
	 */
	List<String> getAcc(String userId, String accType, EAccState accState);

	// List<String> getBindAcc(String accType);

	/**
	 * 取得已绑定的信用卡
	 * 
	 * @param userId
	 * @return 信用卡列表
	 */
	List<String> getBindCreditCard(String userId);

	/**
	 * 取得所有的信用卡
	 * 
	 * @param userId
	 * @return 信用卡列表
	 */
	List<String> getCreditCard(String userId);

	/**
	 * 取得常用的信用卡
	 * 
	 * @param userId
	 * @return 信用卡列表
	 */
	List<String> getUsedCreditCard(String userId);

	/**
	 * 取得信用卡的账户类型，除去定期储蓄，信用卡
	 * 
	 * @return 账户类型类型
	 */
	List<String> getAccTypeOnCreditCard();

	/**
	 * 获取待缴费项目的名称，例如：三月水费，五月电费，保险费等
	 * 
	 * @param userId
	 * @return 待缴费项目的列表
	 */
	List<String> getPaymentName(String userId);

	/**
	 * 取得便捷缴费项目名称，像手机充值，Q币充值
	 * 
	 * @return 便捷缴费项目名称列表
	 */
	List<String> getSelServiceName();

	/**
	 * 取得某用户的便捷缴费项目名称，如果便捷缴费项目和用户有关，就实现这个
	 *暂时不实现
	 * @param userId
	 * @return 便捷缴费项目名称列表
	 */
	List<String> getSelServiceNameByUserId(String userId);

	/**
	 * 取得所有可以开通或关闭的缴费项目，像平安保险，水费，电费等
	 * 
	 * @return 项目列表
	 */
	List<String> getPaymentNameOnMana();

	/**
	 * 根据便捷项目名称取得运营商
	 * 
	 * @param paymentName
	 * @return 运营商列表
	 */
	List<String> getOperator(String paymentId);

	/**
	 * 取得明细历史记录列表，比如：<2011.1.1,支出>
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return 流水号和信息的键值对
	 */
	Map<String, String> getListHistory(String userId, String startDate,
			String endDate);

	/**
	 * 取得来账历史记录列表，比如：<2011.1.2,转账>
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return 流水号和信息的键值对
	 */
	Map<String, String> getComeHistory(String userId, String startDate,
			String endDate);

	/**
	 * 取得待缴费历史记录列表，比如：<三月份水费水费>
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return 流水号和信息的键值对
	 */
	Map<String, String> getPaymentHistory(String userId, String startDate,
			String endDate);

	/**
	 * 取得证件类型
	 * 
	 * @return
	 */
	List<String> getIdType();

	/**
	 * 用户登录，验证用户密码和附加码
	 * 
	 * @param userId
	 * @param userPwd
	 * @param exCode
	 * @return 是否登录成功
	 */
	boolean login(String userId, String userPwd, String exCode);

	/**
	 * 取得验证码
	 * 
	 * @return
	 */
	String getExtraCode();

	/**
	 * 获取网点
	 * 
	 * @return
	 */
	List<String> getNet();

	/**
	 * 获取网点的地址
	 * 
	 * @param net
	 * @return
	 */
	String getNetAddress(String net);

	/**
	 * 取得工本费，需要传入什么情况下的，比如，在挂失情况下的工本费，在预约情况下的工本费
	 * 
	 * @param costType
	 * @return
	 */
	String getCost(String costType);

	/**
	 * 更新项目开通或关闭状态
	 * 
	 * @param payName
	 * @param state
	 * @return
	 */
	boolean updatePaymentState(String payName, String state);

	// /**
	// *
	// * @param userId
	// * @return
	// */
	// Map<String, String> getPaymentInfo(String userId);

	/**
	 * 取得账号，返回的账号带有别名
	 * 
	 * @return 返回键值对，<我的储蓄卡，440301198810282153>
	 */
	Map<String, String> getAccWithNickName(String userId, String accType,
			EAccState accState);

}
