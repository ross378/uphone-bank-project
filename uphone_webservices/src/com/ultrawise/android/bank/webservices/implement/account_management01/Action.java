package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ultrawise.android.bank.webservices.base.account_management01.IAccountAdd;
import com.ultrawise.android.bank.webservices.base.account_management01.IAccountDel;
import com.ultrawise.android.bank.webservices.base.account_management01.IAccountInfo;
import com.ultrawise.android.bank.webservices.base.account_management01.IAccountLoss;
import com.ultrawise.android.bank.webservices.base.account_management01.IAccountPre;
import com.ultrawise.android.bank.webservices.base.account_management01.IAll;
import com.ultrawise.android.bank.webservices.base.account_management01.IBind;
import com.ultrawise.android.bank.webservices.base.account_management01.INickName;
import com.ultrawise.android.bank.webservices.base.account_management01.IOrderCard;

/**
 * 相当于一个包装类，将所有不是list的全部转成list
 * 
 * @author hosolo
 * 
 */
public class Action {

	IAll all;
	IAccountAdd accountAdd;
	IAccountDel accountDel;
	IAccountInfo accountInfo;
	IAccountLoss accountLoss;
	IAccountPre accountPre;
	IBind bind;
	INickName nickName;
	IOrderCard orderCard;

	public Action(IAll all, IAccountAdd accountAdd, IAccountDel accountDel,
			IAccountInfo accountInfo, IAccountLoss accountLoss,
			IAccountPre accountPre, IBind bind, INickName nickName,
			IOrderCard orderCard) {
		this.all = all;
		this.accountAdd = accountAdd;
		this.accountDel = accountDel;
		this.accountInfo = accountInfo;
		this.accountLoss = accountLoss;
		this.accountPre = accountPre;
		this.bind = bind;
		this.nickName = nickName;
		this.orderCard = orderCard;
	}

	// 功能0101
	public List<String> performGetAccType() {
		// TODO Auto-generated method stub
		return all.getAccType();
	}

	// 功能0102
	public List<String> performGetBindAcc(String UserNo, String accType) {
		// TODO Auto-generated method stub
		return all.getBindAcc(UserNo, accType);
	}

	// 功能0103
	public List<String> performGetAccInfo(String account) {
		return accountInfo.getAccInfo(account);
	}

	// 功能0104
	public List<String> performGetOrderInfo(String account) {
		return orderCard.getOrderInfo(account);
	}

	// 功能0105
	public List<String> performGetUnbindAcc(String userNo, String accTypeName) {
		return bind.getUnBindAcc(userNo, accTypeName);
	}

	// 功能0106
	public List<String> performSetBind(String unbindAccount, String password) {
		List<String> lstBool = new ArrayList<String>();
		boolean isDone = bind.setBind(unbindAccount, password);
		if (isDone)
			lstBool.add("true");
		else
			lstBool.add("false");

		return lstBool;
	}

	// 功能0107
	public List<String> performSetLoss(String unlossAccount) {
		List<String> lstBool = new ArrayList<String>();
		boolean isDone = accountLoss.setLoss(unlossAccount);
		if (isDone)
			lstBool.add("true");
		else
			lstBool.add("false");

		return lstBool;

	}

	// 功能0108
	public List<String> performGetNickName(String account) {
		List<String> lstStr = new ArrayList<String>();
		String nickName = all.getNickName(account);
		lstStr.add(nickName);
		return lstStr;
	}

	// 功能0109
	public List<String> performGetlossCost() {
		// TODO Auto-generated method stub
		List<String> lstStr = new ArrayList<String>();
		lstStr.add(all.getLossCost());
		return lstStr;
	}

	// 功能0110
	public List<String> performGetNet() {
		// TODO Auto-generated method stub
		return orderCard.getNet();
	}

	// 功能0111
	public List<String> performGetNetAddress(String net) {
		// TODO
		List<String> lstStr = new ArrayList<String>();
		lstStr.add(orderCard.getAddress(net));
		return lstStr;
	}

	// 功能0112
	public List<String> performSetOrderCard(String account) {
		boolean isDone = orderCard.setOrderCard(account);
		List<String> lstBool = new ArrayList<String>();
		if (isDone)
			lstBool.add("true");
		else
			lstBool.add("false");

		return lstBool;
	}

	// 功能0113
	public List<String> performGetUnorderAcc(String userNo, String accType) {
		return orderCard.getUnorderAcc(userNo, accType);

	}

	// 功能0114
	public List<String> performGetPreAcc(String userNo) {
		String str = accountPre.getPreAccount(userNo);
		List<String> lstStr = new ArrayList<String>();
		lstStr.add(str);
		return lstStr;
	}

	// 功能0115
	public List<String> performSetPreAcc(String userNo, String account) {
		boolean isDone = accountPre.setPreAccount(userNo, account);
		List<String> lstBool = new ArrayList<String>();
		if (isDone)
			lstBool.add("true");
		else
			lstBool.add("false");

		return lstBool;
	}

	// 功能0116
	public List<String> performGetUnpreAcc(String userNo) {
		return accountPre.getUnpreAccount(userNo);
	}

	// 功能0117
	public List<String> performAddAcc(String userNo, String accTypeName,
			String account, String accountNickName, String password) {
		boolean isDone = accountAdd.addAccount(userNo, accTypeName, account,
				accountNickName, password);
		List<String> lstBool = new ArrayList<String>();
		if (isDone)
			lstBool.add("true");
		else
			lstBool.add("false");

		return lstBool;
	}

	// 功能0118
	public List<String> performDeleteAccount(String bindAccount) {
		boolean isDone = accountDel.deleteAccount(bindAccount);
		List<String> lstBool = new ArrayList<String>();
		if (isDone)
			lstBool.add("true");
		else
			lstBool.add("false");

		return lstBool;
	}

	// 功能0119
	public List<String> performSetNickName(String account, String name) {
		// TODO Auto-generated method stub
		boolean isDone = nickName.setNickName(account, name);
		List<String> lstBool = new ArrayList<String>();
		if (isDone)
			lstBool.add("true");
		else
			lstBool.add("false");

		return lstBool;
	}

}
