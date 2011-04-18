package com.ultrawise.android.bank.implement;

import java.util.HashMap;

import com.ultrawise.android.bank.Helper.Helper;
import com.ultrawise.android.bank.base.IAccSystem;
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
		IAccSystem accSystem = new AccSystem();
		HashMap<String, String> targetPhoneInfo = new HashMap<String, String>();
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", account);
		if (password.equals(accInfo.get("actpwd"))) {
			HashMap<String, String> userInfo = DataAccessModel.newInstances()
					.createQueryTools().query("userInfo", "phnum", amtph);
			if (userInfo != null) {
				double balance = Double.parseDouble(accInfo.get("balance"));
				if (balance >= amtnum) {
					targetPhoneInfo.put("cost", accSystem.getCost("转账"));
					targetPhoneInfo.put("username", userInfo.get("userName"));
				} else {
					targetPhoneInfo.put("error", "您的余额不足");
				}
			} else {
				targetPhoneInfo.put("error", "对方手机号没有开通手机银行");
			}
		} else {
			targetPhoneInfo.put("error", "密码错误");
		}
		return targetPhoneInfo;
	}

	public HashMap<String, String> recharge(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,
			String paymentNum, String operator) {
		HashMap<String, String> recharge = new HashMap<String, String>();
		// 判断账号是否已经激活
		boolean isActive = acctIsActive(paymentActNo);
		if (!isActive) {
			// 账号没有激活，则激活账号
			DataAccessModel.newInstances().createUpdataTools().updata(
					"creditCard", "orderid:" + paymentActNo, "activation", "1");
		}
		// 根据账号 获取账号的信息
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools()
				.query("creditCard", "orderid", paymentActNo);
		// 判断输入的密码是否正确
		if (paymentActPasswd.equals(temp.get("actpwd"))) {
			// 获取账号的余额
			double balance = Double.parseDouble(temp.get("balance"));
			balance -= paymentAmt;
			// 根据缴费名称获取缴费的合同号
			HashMap<String, String> temp1 = DataAccessModel.newInstances()
					.createQueryTools().query("patype", "name", paymentName);
			String serNo = temp1.get("dunum");
			// 插入一条充值记录
			DataAccessModel.newInstances().createInsertTools().insertThree(
					"rechargeform", "id:2", "userid:" + temp.get("userid"),
					"name:" + paymentName, "credit:" + paymentAmt,
					"creqqnum:" + paymentNum,
					"date:" + Helper.getCurrentTime(), "creno:" + serNo,
					"operator:" + operator, "account:" + paymentActNo);
			// 更新账号的余额
			DataAccessModel.newInstances().createUpdataTools().updata(
					"creditCard", "orderid", paymentActNo, "balance",
					String.valueOf(balance));
			recharge.put("serNo", serNo);
			recharge.put("result", "true");
		} else {
			recharge.put("error", "密码错误");
		}
		return recharge;
	}

	public HashMap<String, String> payment(String paymentName,
			double paymentAmt, String paymentActNo, String paymentActPasswd,
			String charger) {
		HashMap<String, String> payment = new HashMap<String, String>();
		boolean isActive = acctIsActive(paymentActNo);
		if (!isActive) {
			DataAccessModel.newInstances().createUpdataTools().updata(
					"creditCard", "orderid:" + paymentActNo, "activation", "1");
		}
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools()
				.query("creditCard", "orderid", paymentActNo);
		if (paymentActPasswd.equals(temp.get("actpwd"))) {
			double balance = Double.parseDouble(temp.get("balance"));
			balance -= paymentAmt;
			HashMap<String, String> temp1 = DataAccessModel.newInstances()
					.createQueryTools().query("pendingform", "name",
							paymentName);
			String serNo = temp1.get("dunum");
			DataAccessModel.newInstances().createInsertTools().insertThree(
					"paymentform", "id:2", "userid:" + temp.get("userid"),
					"name:" + paymentName, "dunum:" + serNo,
					"damout:" + paymentAmt, "date:" + Helper.getCurrentTime(),
					"charger:" + charger, "account:" + paymentActNo);
			DataAccessModel.newInstances().createUpdataTools().updata(
					"creditCard", "orderid", paymentActNo, "balance",
					String.valueOf(balance));
			payment.put("serNo", serNo);
			payment.put("result", "true");
		} else {
			payment.put("error", "密码错误");
		}
		return payment;
	}

	public boolean setDetail(String serNo, String detail) {
		return DataAccessModel.newInstances().createUpdataTools().updata(
				"transfers", "id", serNo, "description", detail);
	}

	public HashMap<String, String> transfeAct(String account, String password,
			String amtph, double amtnum) {
		HashMap<String, String> transInfo = new HashMap<String, String>();
		// 转出人 的 账号信息
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid", account);
		// 转出人的信息
		HashMap<String, String> outuser = DataAccessModel.newInstances()
				.createQueryTools().query("userInfo", "userid",
						accInfo.get("userid"));
		// 转入人的信息
		HashMap<String, String> userInfo = DataAccessModel.newInstances()
				.createQueryTools().query("userInfo", "phnum", amtph);
		String userid = userInfo.get("userid");
		// 转入人的账号信息
		HashMap<String, String> accInfo1 = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "userid", userid);
		// 判断转出人输入的密码是否正确
		if (password.equals(accInfo.get("actpwd"))) {
			double balance = CurrentDeposit.transfer(amtnum, accInfo, outuser,
					userInfo, accInfo1);
			transInfo.put("result", "转账成功");
			transInfo.put("balance", String.valueOf(balance));
		} else {
			transInfo.put("result", "密码错误");
		}

		return transInfo;
	}
	
	public HashMap<String, String> transfeActToAct(String account,
			String password, String amtAct, double amtnum) {
		HashMap<String, String> transInfo = new HashMap<String, String>();
		// 转出人 的 账号信息
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", account);
		// 转出人的信息
		HashMap<String, String> outuser = DataAccessModel.newInstances()
				.createQueryTools().query("userInfo", "userid",
						accInfo.get("userid"));
		// 转入人的账号信息
		HashMap<String, String> accInfo1 = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", amtAct);
		// 转入人的信息
		HashMap<String, String> userInfo = DataAccessModel.newInstances()
				.createQueryTools().query("userInfo", "userid", accInfo1.get("userid"));
		// 判断转出人输入的密码是否正确
		if (password.equals(accInfo.get("actpwd"))) {
			double balance = CurrentDeposit.transfer(amtnum, accInfo, outuser, userInfo,
					accInfo1);
			transInfo.put("result", "转账成功");
			transInfo.put("balance", String.valueOf(balance));
		} else {
			transInfo.put("result", "密码错误");
		}
		
		return transInfo;
	}

	public HashMap<String, String> getComeQueryInfo(String type, String id) {
		HashMap<String, String> comeQueryInfo = new HashMap<String, String>();
		HashMap<String, String> temp = null;
		// 根据不同的来账的类型 在不同的表中查找来账记录
		if ("汇款".equals(type)) {
			temp = DataAccessModel.newInstances().createQueryTools().query(
					"remit", "id", id);
		} else {
			temp = DataAccessModel.newInstances().createQueryTools().query(
					"transfers", "id", id);
		}
		// 根据账号 得到 账号的类型id
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools().query("accout", "orderid",
						temp.get("outant"));
		// 根据账号id 得到 账号的类型名称
		HashMap<String, String> accType = DataAccessModel.newInstances()
				.createQueryTools()
				.query("paypal", "id", accInfo.get("actype"));
		comeQueryInfo.put("date", temp.get("date"));
		comeQueryInfo.put("amount", temp.get("amount"));
		comeQueryInfo.put("outsub", temp.get("outsub"));
		comeQueryInfo.put("outant", temp.get("outant"));
		comeQueryInfo.put("type", accType.get("tyname"));
		return comeQueryInfo;
	}

	public HashMap<String, String> getListQueryInfo(String type, String id) {
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

	public boolean setBind(String accNo,String password) {
		// TODO Auto-generated method stub
		HashMap<String, String> accHashMap = DataAccessModel.newInstances().createQueryTools().query("creditCard", "orderid",accNo);
		if (password.equals(accHashMap.get("actpwd"))) {
			return DataAccessModel.newInstances().createUpdataTools().updata(
					"creditCard", "orderid", accNo, "bind", "1");
		}
		return false;
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
				.createQueryTools().query("creditCard", "orderid", tarAcc);
		HashMap<String, String> inuser = DataAccessModel.newInstances()
				.createQueryTools().query("userInfo", "userid",
						accInfo.get("userid"));

		// 判断转出人输入的密码是否正确
		if (password.equals(accInfo.get("actpwd"))) {
			// double balance = CurrentDeposit.transfer(payamt, accInfo,
			// outuser,
			// inuser, accInfo1);
			transInfo.put("result", "true");
			// transInfo.put("balance", String.valueOf(balance));
		} else {
			transInfo.put("result", "false");
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
		HashMap<String, String> accInfo = DataAccessModel.newInstances()
				.createQueryTools()
				.query("creditCard", "orderid", paymentActNo);
		if ("1".equals(accInfo.get("activation"))) {
			return true;
		}
		return false;
	}

	@Override
	public HashMap<String, String> getAccInfo(String acc) {
		HashMap<String, String> accInfo = new HashMap<String, String>();
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", acc);
		HashMap<String, String> accTypeHashMap = DataAccessModel.newInstances()
				.createQueryTools().query("paypal", "id", temp.get("actype"));
		if (temp != null) {
			accInfo.put("账户", temp.get("orderid"));
			accInfo.put("账户别名", temp.get("aliss"));
			accInfo.put("账户类型", accTypeHashMap.get("tyname"));
			accInfo.put("币种", temp.get("montype"));
			accInfo.put("信用额度", temp.get("integrity"));
			accInfo.put("可用度", temp.get("availability"));
			accInfo.put("预借现金可用度", temp.get("payment"));
			accInfo.put("每月的账单日", temp.get("billdate"));
			accInfo.put("本期应还款额", temp.get("repayment"));
			accInfo.put("本期最低还款额", temp.get("minpayment"));
			accInfo.put("本期到期还款日", temp.get("duedata"));
			accInfo.put("账户状态", "预约换卡");
			accInfo.put("是否绑定", temp.get("bind").equals("1")?"是":"否");
			accInfo.put("开户行", temp.get("openbank"));
			accInfo.put("开户日", temp.get("opendate"));
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
		HashMap<String, String> temp = DataAccessModel.newInstances()
				.createQueryTools().query("appointmentform", "orderid", acc);
		if (temp != null) {
			orderInfo.put("账号", temp.get("orderid"));
			orderInfo.put("账户别名", temp.get("aliss"));
			orderInfo.put("更换原因", temp.get("reason"));
			orderInfo.put("领卡网点", temp.get("net"));
			orderInfo.put("网点地址", temp.get("netaddress"));
			orderInfo.put("工本费用", temp.get("cost"));
		} else {
			orderInfo.put("not find", "该账号没有预约信息");
		}
		return orderInfo;
	}

	public boolean verifyPassword(String account, String password) {
		HashMap<String, String> accHashMap = DataAccessModel.newInstances()
				.createQueryTools().query("creditCard", "orderid", account);
		if (password.equals(accHashMap.get("actpwd"))) {
			return true;
		}

		return false;
	}
	// -------查询---------

}
