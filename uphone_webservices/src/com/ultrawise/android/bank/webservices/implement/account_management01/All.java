package com.ultrawise.android.bank.webservices.implement.account_management01;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ultrawise.android.bank.webservices.base.account_management01.IAll;

/**
 * 从txt文件里面读取数据，返回数据
 * 
 * @author hosolo
 * 
 */
public class All implements IAll {

	private String path = Thread.currentThread().getContextClassLoader()
			.getResource("").getPath();;
	private BufferedReader br;
	private String line;
	private StringBuffer data = new StringBuffer();

	/**
	 * 功能号 0102
	 */
	public List<String> getAccType() {
		// TODO 获取账户类型
		List<String> lstStr = new ArrayList<String>();

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path + "paypal.txt"),"utf-8"));
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
			}
			ByteArrayInputStream stream = new ByteArrayInputStream(data
					.toString().getBytes("utf-8"));

			StringBuffer sb = new StringBuffer();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(stream);
			NodeList nl = doc.getElementsByTagName("tyname");// 获取所有的账户种类节点
			for (int i = 0; i < nl.getLength(); i++) {
				String value = nl.item(i).getFirstChild().getNodeValue();//获取节点的值
				lstStr.add(value);
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

		// lstStr.add("信用卡");
		// lstStr.add("储蓄卡");
		return lstStr;
	}

	public String[] getBindAcc(String UserNo, String accType) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getNet() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNickName(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 功能号0101
	 */
	public List<String> getUserNo() {
		// 获取用户号
		return null;
	}

}
