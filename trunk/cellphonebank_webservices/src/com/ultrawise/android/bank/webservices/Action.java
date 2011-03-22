package com.ultrawise.android.bank.webservices;

import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ultrawise.android.bank.base.IQuery;

public class Action {
	private static Action mAction;
	private IQuery mAccount;

	protected Action() {
		mAccount = new Account();
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
		List<String> lst = mAccount.getAccType();
		JSONObject jsonObj = new JSONObject();
		// 和顺序没有关系的可以直接添加
		for (String s : lst) {
			try {
				jsonObj.put(s, s);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return jsonObj;
	}

	/**
	 * 传入账户号，取得账户管理所需要得账户信息
	 * 
	 * @param account
	 * @return 账户信息
	 */
	public JSONObject performGetAccInfo(String account) {
		Map<String, String> map = mAccount.getAccInfoOnAccMana(account);
		JSONObject jsonObj = new JSONObject();
		// 如果和顺序有关系，则分别加入键值对
		try {
			jsonObj.put("nickname", map.get("nickname"));
			jsonObj.put("state", map.get("state"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}
}
