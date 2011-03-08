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

public class QuerySever{
	  private final static String SERVICE_ADDRESS = "http://10.1.1.36:8080/webservices/query/do/";

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
        				//服务器返回的密文JSON：→解析JSON  →转换 为明文     ←返回明文ListMing
        				ListMing=parseJSON(result);
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
        			JSONArray valArray = json.toJSONArray(nameArray);
        			String strMiWen=valArray.getString(0);//得到密文字符串strMiWen
        			
        			String strMingWen=Base64.decode(strMiWen,"UTF-8");//解析为明文
        			String[] strArr=strMingWen.split(":");
        			for(int i=0;i<strArr.length;i++)
        			{
        				lstValue.add(strArr[i]);
        				System.out.println("客户端解析后的明文"+strArr[i]);
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
   }