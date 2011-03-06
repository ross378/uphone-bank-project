package com.ultrawise.android.bank.webservices.implement.financialHelper07;

import it.sauronsoftware.base64.Base64;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class DataHandle {
	
	public static JSONObject stringConvertJSON(String key, List<String> valueArray){
		
		StringBuffer sb = new StringBuffer();
		JSONObject json = new JSONObject();
	
		for(int i = 0; i < valueArray.size(); i ++)
		{
			if(i == 0)
			{
				sb.append(valueArray.get(i));
			}else
			{
				sb.append(":" + valueArray.get(i));
			}
		}
		String value = doEncode(sb.toString());	
			try {
				json.put(key, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return json;
	}
	
	public static String doEncode(String cleartext){
		
		return Base64.encode(cleartext);
	}

}
