package com.ultrawise.android.bank.view;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class WebTools {
	
	private final static String SERVICE_ADDRESS = "http://10.1.1.103:8080/webservices/aaa/dd";

    public static List<String> connectHttp(int funNo, List<String> viewParams) {
		String result = "error";
		
		// apache 方法
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(SERVICE_ADDRESS);
		// 使用NameValuePair来保存要传递的Post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String transfParam = null;
		if(funNo == 0)
		{
			transfParam = funNo + ":";
		}else if(funNo == 1)
		{
			
			transfParam = funNo + ":";
		}else if(funNo == 2)
		{
			transfParam = funNo + ":";
		}else if(funNo == 3)
		{
			
			transfParam = funNo + ":" + viewParams.get(0) + ":" + viewParams.get(1);
			
		}else 
		{
			transfParam = funNo + ":" + viewParams.get(0) + ":" + viewParams.get(1) + ":" + viewParams.get(2);
			
		}
		// 添加要传递的参数
		params.add(new BasicNameValuePair("value", Base64.encode(transfParam)));
		HttpEntity httpentity;
		try {
			// 设置字符集
			httpentity = new UrlEncodedFormEntity(params, "utf-8");
			httppost.setEntity(httpentity);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpResponse response;
		try {

			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertStreamToString(instream);// 转字符串
				try {
					JSONObject json = new JSONObject(result);
					if(funNo == 0)
					{
						result = json.getString("extracode");
					}else if(funNo == 1)
					{
						result = json.getString("depositeRates");
					}else if(funNo == 2)
					{
						result = json.getString("loanRates");
					}else if(funNo == 3)
					{
						result = json.getString("exchangerates");
					}else
					{
						result = json.getString("userLogin");
					}
				    
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// 解密和解析JSON;
				instream.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httppost.abort();
			httpclient.getConnectionManager().shutdown();
		}

		return WebTools.stringToList(result);
	}
    
    private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

    public static List<String> stringToList(String str){
    	Log.d("jjjj", str);
    	List<String>  result = new ArrayList<String>();
    	String[] strArr = Base64.decode(str).split(":");
    	for(int i = 0; i < strArr.length; i ++)
    	{
    		result.add(strArr[i]);
    		
    	}
    	return result;
    }

}
