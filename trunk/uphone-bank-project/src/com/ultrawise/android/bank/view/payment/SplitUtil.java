package com.ultrawise.android.bank.view.payment;

import org.json.JSONArray;
import org.json.JSONException;
public class SplitUtil {
	public static String[] JsonToString(JSONArray json){
		String[] overStr=null;
		try {
			String str = json.getString(0);
			overStr=str.split(":");			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return overStr;
	}
}
