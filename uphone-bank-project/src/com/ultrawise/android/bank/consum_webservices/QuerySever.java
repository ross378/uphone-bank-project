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
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuerySever{
	  private final static String SERVICE_ADDRESS = "http://192.168.182.1:8080/webservices/query/do/";

	  // private String value;// like: 0101:A43lEnOCi80lxEne

        	/**
        	 * 连接服务器
        	 * 
        	 * @author hosolo
        	 * @param funNo功能号
        	 * @param value参数
        	 * @return 返回从服务器接收回来的数据
        	 */
        	public static List<String> connectHttp(String funNo, String[] value) {
        	    List<String> ListMing=null;
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

        		//接收服务器返回的数据
        		HttpResponse response;

        		try {
        			//
        			response = httpclient.execute(httppost);//向服务器发送请求
        			
        			HttpEntity entity = response.getEntity();//得到服务器传回来的数据
        			if (entity != null) {
        				InputStream instream = entity.getContent();
        				String result = convertStreamToString(instream);//将服务器返回的流转换为字符串
        				//服务器返回的密文JSON
        				List<String> listMi=parseJSON(result);
        				//解析为明文
        				ListMing=doDecode(listMi);
        				
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

        		return ListMing;
        	}

        	/**
        	 * 设置需要传输的参数
        	 * 
        	 * @author 
        	 * @param funNo功能号
        	 * @param value参数
        	 * @return 
        	 */
        	private static String setParams(String funNo, String[] value) {
        		String params = funNo + ":" ;
        		if(value!=null)
        		{
	        		for (int i = 0; i < value.length; i++) {
	        			params +=value[i];
	        		}
        		}
        		return Base64.encode(params, "utf-8");//加密
        	}
        	
        	
        	/**
        	 * 解析JSON，解出来的是密文
        	 * 
        	 * @author 
        	 * @param value
        	 * @return 解析完的数据是密文数据
        	 */
        	private static List<String> parseJSON(String value) {
        		List<String> lstValue = new ArrayList<String>();
        		try {
        			// Parsing
        			JSONObject json = new JSONObject(value);
        			JSONArray nameArray = json.names();
        			
        			for (int i = 0; i < nameArray.length(); i++) {
        			
        				lstValue.add(json.get(nameArray.getString(i)).toString());
        				//System.out.println(json.get(nameArray.getString(i)).toString()+"888888");
        			}

        		} catch (JSONException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} finally {
        	  }
        		return lstValue;
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
        		} finally {
        			try {
        				is.close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		}
        		return sb.toString();
        	}
        	
        /**
       	 * 解密
       	 * 
       	 * @param lstMiWen
       	 * @return 明文
       	 */
       	public static List<String> doDecode(List<String> lstMiWen) {
       		List<String> lstMingWen = new ArrayList<String>();
       		if (lstMiWen.size() != 0) {
       			for (String value : lstMiWen) {
       				lstMingWen.add(Base64.decode(value,"utf-8"));// 解密
       			}
       		} else {
       		}
       		return lstMingWen;
       	}
   }