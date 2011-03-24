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
import com.ultrawise.android.bank.implement.AccSystem;
import com.ultrawise.android.bank.implement.Account;

public class Action {
	private static Action mAction;
	private IQuery mAccount;
	private IAccSystem mAccSystem;

	protected Action() {
		// mAccount = new Account();
		mAccSystem = new AccSystem();
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
	 * 取得账户类型，不包括定期账户
	 * 
	 * @return
	 */
	public JSONObject performGetAccType() {
		return Helper.wrapUp(mAccSystem.getAccType());
	}

	/**
	 * 取得所有账户信息，带有别名
	 * 
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
	 * @param account
	 * @return 账户信息
	 */
	public JSONObject performGetAccInfo(String account) {
		// Map<String, String> map = mAccount.getAccInfoOnAccMana(account);
		JSONObject jsonObj = new JSONObject();
		// // 如果和顺序有关系，则分别加入键值对
		// try {
		// jsonObj.put("nickname", map.get("nickname"));
		// jsonObj.put("state", map.get("state"));
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return jsonObj;
	}
}
