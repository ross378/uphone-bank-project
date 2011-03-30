package com.ultrawise.android.bank.Helper;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Helper {

	public static JSONObject wrapUp(double dbl) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("double", String.valueOf(dbl));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}

	/**
	 * 把list包装成json object
	 * 
	 * @param list
	 * @return
	 */
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

	/**
	 * 把map包装成json object
	 * 
	 * @param map
	 * @return
	 */
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

	/**
	 * 把字符串包装成json object
	 * 
	 * @param str
	 * @return
	 */
	public static JSONObject wrapUp(String str) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put(str, str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}

	/**
	 * 把boolean变量包装成json object
	 * 
	 * @param result
	 * @return
	 */
	public static JSONObject wrapUp(boolean result) {
		JSONObject jsonObj = new JSONObject();
		if (result) {
			try {

				jsonObj.put("result", "true");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				jsonObj.put("result", "false");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonObj;
	}
	
	/**
	 * 获取系统的当前时间
	 * @author 王   亭
	 * 2011-3-29
	 * @return
	 */
	public static String getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		
		String now = year + "-" + month + "-" + day;
		
		return now;
	}
}
