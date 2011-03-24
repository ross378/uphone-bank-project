package com.ultrawise.android.bank.Helper;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Helper {

	public static JSONObject wrapUp(List<String> list) {
		JSONObject jsonObj = new JSONObject();
		/* 和顺序没有关系的可以直接添加 */
		for (String s : list) {
			try {
				jsonObj.put(s, s);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return jsonObj;
	}

	public static JSONObject wrapUp(Map<String, String> map) {
		JSONObject jsonObj = new JSONObject();
		for (Entry<String, String> kv : map.entrySet()) {
			try {
				jsonObj.put(kv.getKey(), kv.getValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonObj;
	}
}
