package com.ultrawise.android.bank.webservices;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ultrawise.android.bank.Enum.AccState;
import com.ultrawise.android.bank.Helper.Helper;
import com.ultrawise.android.bank.base.IAccSystem;
import com.ultrawise.android.bank.base.IQuery;
import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.implement.AccSystem;
import com.ultrawise.android.bank.implement.Account;
import com.ultrawise.android.bank.implement.CurrentDeposit;

public class Action {
	private static Action mAction;
	private IQuery mAccount;
	private IAccSystem mAccSystem;
	private CurrentDeposit mCurrentDepositt;

	protected Action() {
		// mAccount = new Account();
		mAccSystem = new AccSystem();
		mCurrentDepositt = new CurrentDeposit();
	}

	/**
	 * 取得Action对象，可以用于调用不同功能
	 * 
	 * @return Action对象
	 */
	public static Action getAction() {
		if (mAction == null) {
			return new Action();
		}
		return mAction;
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
			AccState accState) {
		return Helper.wrapUp(mAccSystem.getAccWithNickName(userId, accType,
				accState));

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
		jsonObj = Helper.wrapUp(mCurrentDepositt.getAccInfo(account));
		return jsonObj;
	}

	/**
	 * 功能号：0104
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
			AccState accState) {
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
	public JSONObject performExtraCode() {
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

	// /**
	// * 0117
	// *
	// * @author hosolo
	// * @param userId
	// * @return
	// */
	// public JSONObject performGetPaymentInfo(String userId) {
	// return Helper.wrapUp(mAccSystem.getPaymentInfo(userId));
	// }

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
		jsonObj = Helper.wrapUp(mCurrentDepositt.getTargetPhoneInfo(account,
				password, amtph, amtnum));
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
	public JSONObject performGetTransfeAct(String account, String password,
			String amtph, double amtnum) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mCurrentDepositt.transfeAct(account, password,
				amtph, amtnum));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param id
	 * @return
	 */
	public JSONObject performGetComeQueryInfo(String id) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mCurrentDepositt.getComeQueryInfo(id));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param id
	 * @return
	 */
	public JSONObject performGetListQueryInfo(String id) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mCurrentDepositt.getListQueryInfo(id));
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
		jsonObj = Helper.wrapUp(mCurrentDepositt.setDetail(serNo, detail));
		return jsonObj;
	}

	/**
	 * 
	 * @author 王 亭 2011-3-24
	 * @param id
	 * @param paymentNam
	 * @return
	 */
	public JSONObject performGetPaymentHisInfo(String paymentNam, String id) {

		JSONObject jsonObj = new JSONObject();

		jsonObj = Helper.wrapUp(mCurrentDepositt.getPaymentHisInfo(paymentNam,
				id));
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
			String paymentActNo, String paymentActPasswd, String paymentNum) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mCurrentDepositt.recharge(paymentName,
				paymentAmt, paymentActNo, paymentActPasswd, paymentNum));
		return jsonObj;
	}

	public JSONObject performGetPaymentInfo(String id) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = Helper.wrapUp(mCurrentDepositt.getPaymentInfo(id));
		return jsonObj;
	}
}
