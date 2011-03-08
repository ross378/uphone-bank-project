package com.ultrawise.android.bank.consum_webservices;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class TransferWebservicesClient{
	private static final String TAG = "Transfer";
	private static final String SERVICE_ADDRESS = "http://192.168.0.106:8080/webservices/transws/do";

	public static List<String> connectHttp (String funNo, List<String> Value){
		String result = "error";
		List<String> lstValue = new ArrayList<String>();
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(SERVICE_ADDRESS);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("value",setParams(funNo,Value)));
		
		HttpEntity httpentity;
		
		try{
			httpentity = new UrlEncodedFormEntity(params,"utf-8");
			httppost.setEntity(httpentity);
			
		}catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpResponse response;
		
		try{
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream instream = entity.getContent();
				result = convertStreamToString(instream);
				lstValue = doDecode(parseJSON(result));
			}
			
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httppost.abort();
			httpclient.getConnectionManager().shutdown();
		}
		return lstValue;
	}

	
	public static String setParams(String funNo, List<String> value){
		String params = funNo;
		if(value.size()!=0){
			for(int i = 0; i<value.size(); i++){
				params += ":" + value.get(i);
			}
		}
		return doEncode(params);		
	}
	
	public static String doEncode(String jiami){
		return Base64.encode(jiami, "utf-8");
	}

	public static String convertStreamToString(InputStream is){
		BufferedReader reader= new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try{
			while((line = reader.readLine()) != null){
				sb.append(line+"\n");
			}
		}catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "===========convert Stream to String error=============");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	private static List<String> parseJSON(String value){
		List<String> lstValue = new ArrayList<String>();
		try{
			JSONObject json = new JSONObject(value);
			JSONArray namejson = json.names();
			JSONArray valArray = json.toJSONArray(namejson);
			for (int i = 0; i < valArray.length(); i++) {
				lstValue.add(valArray.getString(i));
			}
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "============parse JSON error==========");
		} finally {
			Log.e(TAG, "============parse JSON done==========");
		}
		return lstValue;
	}
	public static List<String> doDecode(List<String> lstMiWen) {
		List<String> lstMingWen = new ArrayList<String>();
		if (lstMiWen.size() != 0) {
			for (String value : lstMiWen) {
				lstMingWen.add(Base64.decode(value));
			}
		} else {
			Log.e(TAG, "===========doDecode lstMiWen size == 0===============");
		}
		return lstMingWen;
	}
}