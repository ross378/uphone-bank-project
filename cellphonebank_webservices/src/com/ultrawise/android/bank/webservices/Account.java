package com.ultrawise.android.bank.webservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrawise.android.bank.base.IQuery;

public class Account implements IQuery {
	public List<String> getAccType() {
		// TODO Auto-generated method stub
		List<String> lst = new ArrayList<String>();
		lst.add("储蓄卡");
		lst.add("信用卡");
		return lst;
	}

	public Map<String, String> getAccInfoOnAccMana(String account) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("nickname", "我的储蓄卡");
		map.put("state", "正常");
		return map;
	}
}
