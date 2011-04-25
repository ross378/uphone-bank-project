package com.ultrawise.android.bank.webservices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONObject;

import com.ultrawise.android.bank.Enum.EAccState;
import com.ultrawise.android.bank.Enum.ECoin;
import com.ultrawise.android.bank.Enum.ERateType;
import com.ultrawise.android.bank.Helper.Helper;
import com.ultrawise.android.bank.base.IAccSystem;
import com.ultrawise.android.bank.base.ICreditCard;
import com.ultrawise.android.bank.base.IHelper;
import com.ultrawise.android.bank.base.IQuery;
import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.base.IUpdate;
import com.ultrawise.android.bank.implement.AccSystem;
import com.ultrawise.android.bank.implement.FinaHelper;

public class Action {
	private static Action mAction;
	private IQuery mQuery;
	private IAccSystem mAccSystem;
	// private CurrentDeposit mCurrentDepositt;
	private IUpdate mUpdate;
	private ITrans mTrans;
	private ICreditCard mCc;
	private IHelper mhelper;

	private Action() {
		this.mAccSystem = new AccSystem();
		this.mhelper = new FinaHelper();
	}

	private Action(IQuery query, IUpdate update, ITrans trans, ICreditCard cc) {
		this.mAccSystem = new AccSystem();
		this.mhelper = new FinaHelper();
		this.mQuery = query;
		this.mUpdate = update;
		this.mTrans = trans;
		this.mCc = cc;
	}

	/**
	 * 获取无参数构造器，这个构造器不能提供单例
	 * 
	 * @return
	 */
	public static Action getNewAction() {
		return new Action();
	}

	/**
	 * 取得Action对象，可以用于调用不同功能
	 * 
	 * @return Action对象
	 */
	public static Action getAction(IQuery query, IUpdate update, ITrans trans,
			ICreditCard cc) {
		return new Action(query, update, trans, cc);
	}

	// ----------------------------系统
public JSONObject performGetUserInfo(String userId){
	return Helper.wrapUp(mAccSystem.getUserInfo(userId));
}
	
	/**
	 * 功能号:0101
	 * 
	 * @author hosolo
	 * @return
	 */
	public JSONObject performGetAccType() {
		return Helper.wrapUp(mAccSystem.getAccType());
	}

	/**
	 * 功能号：0103
	 * 
	 * @author hosolo
	 * @param userId
	 * @param accType
	 * @param accState
	 * @return
	 */
	public JSONObject performGetAccWithNickName(String userId, String accType,
			EAccState accState) {
		return Helper.wrapUp(mAccSystem.getAccWithNickName(userId, accType,
				accState));

	}

	/**
	 * 
	 * @author hosolo
	 * @param userId
	 * @param accNo
	 * @param accType
	 * @param accNickName
	 * @param accPwd
	 * @return
	 */
	public JSONObject performAddAcc(String userId, String accNo,
			String accType, String accNickName, String accPwd) {
		return Helper.wrapUp(mAccSystem.addAcc(userId, accNo, accType,
				accNickName, accPwd));
	}

	/**
	 * 0105
	 * 
	 * @author hosolo
	 * @param userId
	 * @param accType
	 * @param accState
	 * @return
	 */
	public JSONObject performGetAcc(String userId, String accType,
			EAccState accState) {
		return Helper.wrapUp(mAccSystem.getAcc(userId, accType, accState));
	}

	/**
	 * 0106
	 * 
	 * @author hosolo
	 * @return
	 */
	public JSONObject performGetAccTypeAll() {
		return Helper.wrapUp(mAccSystem.getAccTypeAll());
	}

	/**
	 * 0107
	 * 
	 * @author hosolo
	 * @return
	 */
	public JSONObject performGetAccTypeOnCreditCard() {
		return Helper.wrapUp(mAccSystem.getAccTypeOnCreditCard());
	}

	/**
	 * 0108
	 * 
	 * @author hosolo
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public JSONObject performGetComeHistory(String userId, String startDate,
			String endDate) {
		return Helper.wrapUp(mAccSystem.getComeHistory(userId, startDate,
				endDate));
	}

	/**
	 * 0109
	 * 
	 * @author hosolo
	 * @param costType
	 * @return
	 */
	public JSONObject performGetCost(String costType) {
		return Helper.wrapUp(mAccSystem.getCost(costType));
	}

	/**
	 * 0110
	 * 
	 * @author hosolo
	 * @param userId
	 * @return
	 */
	public JSONObject performGetCreditCard(String userId) {
		return Helper.wrapUp(mAccSystem.getCreditCard(userId));
	}

	/**
	 * 0111
	 * 
	 * @author hosolo
	 * @return
	 */
	public JSONObject performGetExtraCode() {
		return Helper.wrapUp(mAccSystem.getExtraCode());
	}

	/**
	 * 0112
	 * 
	 * @author hosolo
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public JSONObject performGetListHistory(String userId, String startDate,
			String endDate) {
		return Helper.wrapUp(mAccSystem.getListHistory(userId, startDate,
				endDate));
	}

	/**
	 * 0113
	 * 
	 * @author hosolo
	 * @return
	 */
	public JSONObject performGetNet() {
		return Helper.wrapUp(mAccSystem.getNet());
	}

	/**
	 * 0114
	 * 
	 * @author hosolo
	 * @param net
	 * @return
	 */
	public JSONObject performGetNetAddress(String net) {
		return Helper.wrapUp(mAccSystem.getNetAddress(net));
	}

	/**
	 * 0115
	 * 
	 * @author hosolo
	 * @param paymentName
	 * @return
	 */
	public JSONObject performGetOperator(String paymentName) {
		return Helper.wrapUp(mAccSystem.getOperator(paymentName));
	}

	/**
	 * 0116
	 * 
	 * @author hosolo
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public JSONObject performGetPaymentHistory(String userId, String startDate,
			String endDate) {
		return Helper.wrapUp(mAccSystem.getPaymentHistory(userId, startDate,
				endDate));
	}

	/**
	 * 0117
	 * 
	 * @author hosolo
	 * @param userId
	 * @return
	 */
	public JSONObject performGetBindCreditCard(String userId) {
		return Helper.wrapUp(mAccSystem.getBindCreditCard(userId));
	}

	/**
	 * 0118
	 * 
	 * @author hosolo
	 * @param userId
	 * @return
	 */
	public JSONObject performGetPaymentName(String userId) {
		return Helper.wrapUp(mAccSystem.getPaymentName(userId));
	}

	/**
	 * 0119
	 * 
	 * @author hosolo
	 * @return
	 */
	public JSONObject performGetPaymentNameOnMana() {
		return Helper.wrapUp(mAccSystem.getPaymentNameOnMana());
	}

	/**
	 * 0120
	 * 
	 * @author hosolo
	 * @param userId
	 * @return
	 */
	public JSONObject performGetPreAcc(String userId) {
		return Helper.wrapUp(mAccSystem.getPreAcc(userId));
	}

	/**
	 * 0121
	 * 
	 * @author hosolo
	 * @return
	 */
	public JSONObject performGetSelServiceName() {
		return Helper.wrapUp(mAccSystem.getSelServiceName());
	}

	/**
	 * 0122
	 * 
	 * @author hosolo
	 * @param userId
	 * @return
	 */
	public JSONObject performGetSelServiceNameByUserId(String userId) {
		return Helper.wrapUp(mAccSystem.getSelServiceNameByUserId(userId));
	}

	/**
	 * 0123
	 * 
	 * @author hosolo
	 * @param userId
	 * @return
	 */
	public JSONObject performGetUsedCreditCard(String userId) {
		return Helper.wrapUp(mAccSystem.getUsedCreditCard(userId));
	}

	/**
	 * 0124
	 * 
	 * @author hosolo
	 * @param userId
	 * @param userPwd
	 * @param exCode
	 * @return
	 */
	public JSONObject performLogin(String userId, String userPwd, String exCode) {
		return Helper.wrapUp(mAccSystem.login(userId, userPwd, exCode));
	}

	/**
	 * 0125
	 * 
	 * @author hosolo
	 * @param userId
	 * @param accNo
	 * @return
	 */
	public JSONObject performSetPreAcc(String userId, String accNo) {
		return Helper.wrapUp(mAccSystem.setPreAcc(userId, accNo));
	}

	/**
	 * 0126
	 * 
	 * @author hosolo
	 * @param payName
	 * @param state
	 * @return
	 */
	public JSONObject performUpdatePaymentState(String payName, String state) {
		return Helper.wrapUp(mAccSystem.updatePaymentState(payName, state));
	}

	// ----------------------------查询
	public JSONObject performAcctIsActive(String actNo) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mQuery.acctIsActive(actNo));
	}

	/**
	 * 传入账户号，取得账户管理所需要得账户信息
	 * 
	 * @author 王亭
	 * @param account
	 * @return 账户信息
	 */
	public JSONObject performGetAccInfo(String account) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mQuery.getAccInfo(account));
		return jsonObj;
	}

	public JSONObject performGetNickName(String acc) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mQuery.getNickName(acc));
	}

	public JSONObject performGetOrderInfo(String acc) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mQuery.getOrderInfo(acc));
	}

	// ----------------------------转账
	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param id
	 * @return
	 */
	public JSONObject performGetComeQueryInfo(String type, String id) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.getComeQueryInfo(type, id));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param id
	 * @return
	 */
	public JSONObject performGetListQueryInfo(String type,String id) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.getListQueryInfo(type,id));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param id
	 * @param paymentNam
	 * @return
	 */
	public JSONObject performGetPaymentHisInfo(String id) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.getPaymentHisInfo(id));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-28
	 * @param id
	 * @return
	 */
	public JSONObject performGetPaymentInfo(String userid, String id) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.getPaymentInfo(userid, id));
		return jsonObj;
	}

	public JSONObject performGetRechargeInfo(String paymentName,
			String paymentActNo, double paymentAmt, String paymentPhoneNum) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mTrans.getRechargeInfo(paymentName, paymentActNo,
				paymentAmt, paymentPhoneNum));
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param account
	 * @param password
	 * @param amtph
	 * @param amtnum
	 * @return
	 */
	public JSONObject performGetTargetPhoneInfo(String account,
			String password, String amtph, double amtnum) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.getTargetPhoneInfo(account, password,
				amtph, amtnum));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param paymentName
	 * @param paymentAmt
	 * @param paymentActNo
	 * @param paymentActPasswd
	 * @param paymentNum
	 * @return
	 */
	public JSONObject performRecharge(String paymentName, double paymentAmt,
			String paymentActNo, String paymentActPasswd, String paymentNum,
			String operator) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.recharge(paymentName, paymentAmt,
				paymentActNo, paymentActPasswd, paymentNum, operator));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-31
	 * @param paymentName
	 * @param paymentAmt
	 * @param paymentActNo
	 * @param paymentActPasswd
	 * @param paymentNum
	 * @param charger
	 * @return
	 */
	public JSONObject performPayment(String paymentName, double paymentAmt,
			String paymentActNo, String paymentActPasswd, String charger) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.payment(paymentName, paymentAmt,
				paymentActNo, paymentActPasswd, charger));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param serNo
	 * @param detail
	 * @return
	 */
	public JSONObject performSetDetail(String serNo, String detail) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.setDetail(serNo, detail));
		return jsonObj;
	}
	
	public JSONObject performVerifyPassword(String account,String password){
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mQuery.verifyPassword(account, password));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param account
	 * @param password
	 * @param amtph
	 * @param amtnum
	 * @return
	 */
	public JSONObject performTransfeAct(String account, String password,
			String amtph, double amtnum) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.transfeAct(account, password, amtph,
				amtnum));
		return jsonObj;
	}
	
	public JSONObject preformTransfeActToAct(String account, String password,
			String amtAct, double amtnum){
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mTrans.transfeActToAct(account, password, amtAct,
				amtnum));
		return jsonObj;
	}

	// ---------------------------- 更新，修改
	public JSONObject performDeleAcc(String accNo) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mUpdate.deleAcc(accNo));
	}

	public JSONObject performLossRegister(String accNo) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mUpdate.lossRegister(accNo));
	}

	public JSONObject performSetActActive(String accNo, String accPwd) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mUpdate.setActActive(accNo, accPwd));
	}

	public JSONObject performSetBind(String accNo,String password) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mUpdate.setBind(accNo,password));
	}

	public JSONObject performSetNickName(String accNo, String name) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mUpdate.setNickName(accNo, name));
	}

	public JSONObject performSetOrderCard(String accNo, String aliss,
			String reason, String net, String netaddress, double cost) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mUpdate.setOrderCard(accNo, aliss, reason, net,
				netaddress, cost));
	}

	// ----------------------------信用卡专用
	public JSONObject performCreditRepayment(String account, String password,
			String tarAcc, double payamt) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mCc.creditRepayment(account, password, tarAcc,
				payamt));
	}

	public JSONObject performDestroyCard(String userName, String creditCardNo,
			String idNo, String cellPhone, String pwd) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mCc.destroyCard(userName, creditCardNo, idNo,
				cellPhone, pwd));
	}

	public JSONObject performGetCreditRepaymentInfor(String account) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mCc.getCreditRepaymentInfor(account));
	}

	public JSONObject performOpenCard(String userName, String creditCardNo,
			String availbDate, String idNo, String cellPhone, String tel,
			String pwd) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mCc.openCard(userName, creditCardNo, availbDate,
				idNo, cellPhone, tel, pwd));
	}

	// ----------------------------助手
	public JSONObject performGetExchangeResult(double currencyDenomination,
			ECoin sourceCurrencyType, ECoin destinationCurrencyType) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mhelper.getExchangeResult(currencyDenomination,
				sourceCurrencyType, destinationCurrencyType));
	}

	public JSONObject performGetRate(ERateType type) {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mhelper.getRate(type));
	}

	public JSONObject performGetMoneyType() {
		// TODO Auto-generated method stub
		return Helper.wrapUp(mhelper.getMoneyType());
	}
	
}
