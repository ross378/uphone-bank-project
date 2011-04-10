package com.ultrawise.android.bank.consum_webservices;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ultrawise.android.bank.view.account_management.ActiveAccountSelect;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class AccManaConWebservices {
	private final static String TAG = "AccountManagement";
	private final static String URL_PATH = "http://10.1.1.36:8080/webservices/amws/do";

	/**
	 * 连接服务器
	 * 
	 * @author hosolo
	 * @param funNo功能号
	 * @param value参数
	 * @return JSONArray 可以直接使用
	 */
	public static List<String> connectHttp(Activity activity, String funNo,
			List<String> value) {
		String result = "error";
		List<String> lstValue = new ArrayList<String>();

		// apache 方法
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(URL_PATH);
		// 使用NameValuePair来保存要传递的Post参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 添加要传递的参数
		params.add(new BasicNameValuePair("value", setParams(funNo, value)));
		HttpEntity httpentity;
		HttpResponse response;
		try {
			// 设置字符集
			httpentity = new UrlEncodedFormEntity(params, "utf-8");
			httppost.setEntity(httpentity);
			response = httpclient.execute(httppost);
			System.out.println();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertStreamToString(instream);// 转字符串
				lstValue = doDecode(parseJSON(result));// 解密和解析JSON;
				instream.close();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(activity, "服务器未连接", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} finally {
			httppost.abort();
			httpclient.getConnectionManager().shutdown();
		}

		return lstValue;
	}

	/**
	 * 设置需要传输的参数
	 * 
	 * @author hosolo
	 * @param funNo功能号
	 * @param value参数
	 * @return 格式话过的参数，like 0101:yes:haha
	 */
	private static String setParams(String funNo, List<String> value) {
		String params = funNo;
		if (value != null) {
			if (value.size() != 0) {
				for (int i = 0; i < value.size(); i++) {
					params += ":" + value.get(i);
				}
			}
		}
		return doEncode(params);
	}

	/**
	 * 解析JSON，解出来的是密文
	 * 
	 * @author hosolo
	 * @param value
	 * @return 解析完的数据是密码数据
	 */
	private static List<String> parseJSON(String value) {
		List<String> lstValue = new ArrayList<String>();
		if (!value.equals("") || value == null) {
			try {
				// Parsing
				JSONObject json = new JSONObject(value);
				JSONArray nameArray = json.names();
				JSONArray valArray = json.toJSONArray(nameArray);
				if (valArray != null) {
					for (int i = 0; i < valArray.length(); i++) {
						lstValue.add(valArray.getString(i));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e(TAG, "============parse JSON error==========");
			}
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

	/**
	 * 加密，相当与重新把数据包装成list
	 * 
	 * @param msg
	 * @return 每个加密的字符串
	 */
	private static String doEncode(String strMingWen) {

		return Base64.encode(strMingWen, "utf-8");

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
				lstMingWen.add(Base64.decode(value));// 解密
			}
		} else {
			Log.e(TAG, "===========doDecode lstMiWen size == 0===============");
		}
		return lstMingWen;
	}

	/**
	 * 暂不实现
	 * 
	 * @author hosolo
	 * @param activity
	 * @return
	 */
	public static String readUrl(Activity activity) {
		String urlPath = "";
		String filePath = activity.getFilesDir().getPath();
		Log.v(TAG, "===============" + filePath);
		try {
			String path = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path + "/url.txt"), "utf-8"));
			String line;
			StringBuffer data = new StringBuffer();
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
			}
			ByteArrayInputStream stream = new ByteArrayInputStream(data
					.toString().getBytes("utf-8"));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(stream);
			NodeList nl = doc.getElementsByTagName("url");// 获取url节点
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);// 获取节点的值
				NamedNodeMap nnm = node.getAttributes();
				node = nnm.getNamedItem("id");
				String funNo = node.getNodeValue();// 获取url属性id的值（即功能号）
				if (funNo.equals("01")) {
					urlPath = node.getTextContent();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlPath;
	}
}
