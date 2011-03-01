package com.ultrawise.android.bank.webservices.implement.account_management01;

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

	public String[] performGetAccType() {
		// TODO Auto-generated method stub
		return all.getAccType();
	}

	public String[] performGetBindAcc(String UserNo, String accType) {
		// TODO Auto-generated method stub
		return all.getBindAcc(UserNo, accType);
	}

	public String performGetCost() {
		// TODO Auto-generated method stub
		return all.getCost();
	}

	public String[] performGetNet() {
		// TODO Auto-generated method stub
		return all.getNet();
	}

	public String performGetNickName(String account) {
		// TODO Auto-generated method stub
		return all.getNickName(account);
	}

	// 功能0101
	public String performGetUserNo() {
		// TODO
		return all.getUserNo();
	}

}
