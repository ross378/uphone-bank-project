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

	public HashMap<String, String> getComeQueryInfo(String type, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getListQueryInfo(String type,String id) {
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
	public HashMap<String, String> creditRepayment(String account,
			String password, String tarAcc, double payamt) {
		// TODO Auto-generated method stub
		HashMap<String, String> transInfo = new HashMap<String, String>();
		// 转出人 的 账号信息
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", account);
		// 转出人的信息
		HashMap<String, String> outuser = DataAccessModel.newInstances()
				.createQueryTools().query("userInfo", "userid",
						accInfo.get("userid"));
		// 转入人信息
		HashMap<String, String> accInfo1 = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", tarAcc);
		HashMap<String, String> inuser = DataAccessModel.newInstances()
				.createQueryTools().query("userInfo", "userid",
						accInfo.get("userid"));

		// 判断转出人输入的密码是否正确
		if (password.equals(accInfo.get("actpwd"))) {
			double balance = CurrentDeposit.transfer(payamt, accInfo, outuser,
					inuser, accInfo1);
			transInfo.put("result", "转账成功");
			transInfo.put("balance", String.valueOf(balance));
		} else {
			transInfo.put("result", "密码错误");
		}
		return transInfo;
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
		throw new UnsupportedOperationException(
				"CreditCard.java -> getCreditRepaymentInfor");
	}

	// -------信用卡专用---------

	// -------查询---------
	@Override
	public boolean acctIsActive(String paymentActNo) {
		HashMap<String,String> accInfo = DataAccessModel.newInstances().createQueryTools().query("creditCard", "orderid",paymentActNo);
		if("1".equals(accInfo.get("activation"))){
			return true;
		}
		return false;
	}

	@Override
	public HashMap<String, String> getAccInfo(String acc) {
		HashMap<String, String> accInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", acc);
		HashMap<String, String> accTypeHashMap = DataAccessModel.newInstances().createQueryTools().query("paypal", "id",temp.get("actype"));
		if (temp != null) {
			accInfo.put("account", temp.get("orderid"));
			accInfo.put("accType", accTypeHashMap.get("tyname"));
			accInfo.put("montype", temp.get("montype"));
			accInfo.put("integrity", temp.get("integrity"));
			accInfo.put("availability", temp.get("availability"));
			accInfo.put("payment", temp.get("payment"));
			accInfo.put("billdate", temp.get("billdate"));
			accInfo.put("repayment", temp.get("repayment"));
			accInfo.put("minpayment", temp.get("minpayment"));
			accInfo.put("duedata",temp.get("duedata") );
		}
		return accInfo;
	}

	@Override
	public String getNickName(String acc) {
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", acc);
		return accInfo.get("aliss");
	}

	@Override
	public HashMap<String, String> getOrderInfo(String acc) {
		HashMap<String, String> orderInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances().createQueryTools().query("appointmentform", "orderid",acc);
		if(temp != null){
			orderInfo.put("account", temp.get("orderid"));
			orderInfo.put("aliss", temp.get("aliss"));
			orderInfo.put("reason", temp.get("reason"));
			orderInfo.put("net", temp.get("net"));
			orderInfo.put("netaddress", temp.get("netaddress"));
			orderInfo.put("cost", temp.get("cost"));
		}else{
			orderInfo.put("not find", "该账号没有预约信息");
		}
		return orderInfo;
	}
	// -------查询---------

}
