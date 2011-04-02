package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.base.ICreditCard;
import com.ultrawise.android.bank.base.IQuery;
import com.ultrawise.android.bank.base.ITrans;
import com.ultrawise.android.bank.base.IUpdate;
import com.ultrawise.bank.implement.dao.DataAccessModel;

public class CreditCard extends Account implements ITrans, IUpdate, ICreditCard {
	// -------转账---------
	public HashMap<String, String> getPaymentHisInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getPaymentInfo(String userid, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getRechargeInfo(String paymentName,
			String paymentActNo, double paymentAmt, String paymentPhoneNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getTargetPhoneInfo(String account,
			String password, String amtph, double amtnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,
			String paymentNum, String operator) {

		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> payment(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,
			String charger) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean setDetail(String serNo, String detail) {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String, String> transfeAct(String account, String password,
			String amtph, double amtnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getComeQueryInfo(String type,String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getListQueryInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	// -------转账---------

	// -------更新---------
	public boolean deleAcc(String accNo) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"creditCard", "orderid", accNo, "bind", "0");
	}

	public boolean lossRegister(String accNo) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"creditCard", "orderid", accNo, "loss", "0");
	}

	public boolean setActActive(String accNo, String accPwd) {
		// TODO Auto-generated method stub
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", accNo);
		if (accPwd.equals(accInfo.get("pwd"))) {
			return DataAccessModel.newInstances().createUpdataTools().updata(
					"accout", "orderid", accNo, "activation", "1");
		} else {
			return false;
		}
	}

	public boolean setBind(String accNo) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"creditCard", "orderid", accNo, "bind", "1");
	}

	public boolean setNickName(String accNo, String name) {
		// TODO Auto-generated method stub
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"creditCard", "orderid", accNo, "aliss", name);
	}

	public boolean setOrderCard(String accNo, String aliss, String reason,
			String net, String netaddress, double cost) {
		// TODO Auto-generated method stub
		DataAccessModel.newInstances().createInsertTools().insertThree(
				"appointmentform", "orderid:" + accNo, "aliss:" + aliss,
				"reason:" + reason, "net:" + net, "netaddress:" + netaddress,
				"cost:" + String.valueOf(cost));
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"accout", "orderid", accNo, "orderstate", "1");
	}

	// -------更新---------

	// -------信用卡专用---------
	public boolean creditRepayment(String account, String password,
			double payamt) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean destroyCard(String userName, String creditCardNo,
			String idNo, String cellPhone, String pwd) {
		// TODO Auto-generated method stub
		// 如果用户名，证件号，电话，密码都正确
		//
		//
		//
		//
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"creditCard", "orderid", creditCardNo, "state", "0");
	}

	public boolean openCard(String userName, String creditCardNo,
			String availbDate, String idNo, String cellPhone, String tel,
			String pwd) {
		// TODO Auto-generated method stub
		// 如果所有条件都正确的话
		//
		//
		//
		//
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"creditCard", "orderid", creditCardNo, "state", "1");
	}

	public HashMap<String, String> getCreditRepaymentInfor(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	// -------信用卡专用---------

	// -------查询---------
	@Override
	public boolean acctIsActive(String paymentActNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<String, String> getAccInfo(String acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNickName(String acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getOrderInfo(String acc) {
		// TODO Auto-generated method stub
		return null;
	}
	// -------查询---------

}
