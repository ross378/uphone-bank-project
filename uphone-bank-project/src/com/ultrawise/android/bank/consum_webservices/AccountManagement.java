package com.ultrawise.android.bank.consum_webservices;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class AccountManagement {
	private final static String TAG = "AccountManagement";
	private final static String SERVICE_ADDRESS = "http://10.1.111.9:8080/webservices/amws/";

	// private String value;// like: 0101:A43lEnOCi80lxEne

	/**
	 * 连接服务器
	 * 
	 * @author hosolo
	 * @param funNo功能号
	 * @param value参数
	 * @return JSONArray 可以直接使用
	 */
	public JSONArray connectHttp(String funNo, String[] value) {
		String result = "error";
		JSONArray jsonArray = new JSONArray();
		// apache 方法
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(SERVICE_ADDRESS);
		// 使用NameValuePair来保存要传递的Post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 添加要传递的参数
		params.add(new BasicNameValuePair("value", setParams(funNo, value)));

		// 设置字符集
		HttpEntity httpentity;
		try {
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
				result = convertStreamToString(instream);
				jsonArray = parseJSON(result);
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

		return jsonArray;
	}

	/**
	 * 设置需要传输的参数
	 * 
	 * @author hosolo
	 * @param funNo功能号
	 * @param value参数
	 * @return 格式话过的参数，like 0101:yes:haha
	 */
	private String setParams(String funNo, String[] value) {
		String params = funNo;
		for (int i = 0; i <= value.length; i++) {
			params += ":" + value[i];
		}
		return params;
	}

	/**
	 * 解析JSON
	 * 
	 * @author hosolo
	 * @param value
	 * @return
	 */
	private static JSONArray parseJSON(String value) {

		try {
			// Parsing
			JSONObject json = new JSONObject();
			JSONArray nameArray = json.names();
			JSONArray valArray = json.toJSONArray(nameArray);
			return valArray;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "============parse JSON error==========");
		} finally {
			Log.e(TAG, "============parse JSON done==========");
		}
		return null;
	}

	/**
	 * 将流转换成String
	 * 
	 * @author hosolo
	 * @param is
	 * @return String
	 */
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
			Log.e(TAG, "==============convert Stream to String error");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
