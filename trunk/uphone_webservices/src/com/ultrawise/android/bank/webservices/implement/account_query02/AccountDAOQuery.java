package com.ultrawise.android.bank.webservices.implement.account_query02;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ultrawise.android.bank.webservices.base.account_Query02.IAccountQueryInfo;


public class AccountDAOQuery implements IAccountQueryInfo{
	private String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();;
	

	/**
	 * 功能号 021 查找账户类型
	 */
	public List<String> getAccType() {
		// TODO 获取账户类型
		List<String> lstStr = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance() ;
			DocumentBuilder db=dbf.newDocumentBuilder();
	        Document doc=db.parse(path + "paypal.txt");
			NodeList list=doc.getElementsByTagName("paypal");// 获取所有的账户种类节点
			for(int i = 0; i < list.getLength();i++ ){
				Node node = list.item(i);
				NamedNodeMap nnm=list.item(i).getAttributes();
				String attrValue=nnm.item(0).getNodeValue();//获得属性值
				String typeValue=null;
				lstStr.add(attrValue);
//				System.out.println(attrValue);
					NodeList n2 = node.getChildNodes();
					for(int j=1;j<n2.getLength();j=j+2){
//						System.out.println(n2.item(j).getFirstChild().getNodeValue());
						typeValue=n2.item(j).getFirstChild().getNodeValue();
						lstStr.add(typeValue);
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
		return lstStr;
	}	

	/**
	 * 功能号 022 按账户类型查找账户号
	 */
	public List<String> getAccountQueryByType(String type) {
		// TODO 获得账户号
		List<String> lstStr = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance() ;
			DocumentBuilder db=dbf.newDocumentBuilder();
	        Document doc=db.parse(path + "accout.txt");
			NodeList list=doc.getElementsByTagName("accout");// 获取所有的账户种类节点
			for(int i = 0; i < list.getLength();i++ ){
				Node node = list.item(i);
				NamedNodeMap nnm=list.item(i).getAttributes();
				String attrValue=nnm.item(0).getNodeValue();//获得属性值
				
					NodeList n2 = node.getChildNodes();
					for(int j=1;j<n2.getLength();j=j+2){
						if(n2.item(j).getFirstChild().getNodeValue().equals(type.trim()))
						{
							lstStr.add(attrValue);
						}
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
		return lstStr;
	 }
	
	
	/**
	 * 获得账户号下的全部信息
	 * 功能号 023
	 * 参数 账户号id
	 */
	public List<String> getAccountQueryById(String id) {
		List<String> lstStr = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance() ;
			DocumentBuilder db=dbf.newDocumentBuilder();
	        Document doc=db.parse(path + "accout.txt");
			NodeList list=doc.getElementsByTagName("accout");// 获取所有的账户种类节点
			
			//获得属性和属性 值
			for(int i = 0; i < list.getLength();i++ ){
				Node node = list.item(i);
				NamedNodeMap nnm=list.item(i).getAttributes();
				String attrValue=nnm.item(0).getNodeValue();//获得属性值
				if(attrValue.equals(id.trim())){
					NodeList n2 = node.getChildNodes();
					for(int j=1;j<n2.getLength();j=j+2){
						lstStr.add(n2.item(j).getFirstChild().getNodeValue());
					}
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
		return lstStr;
	}

	
	/**
	 * 获得两个时间段下的全部信息
	 * 功能号 024
	 * 参数 开始时间 start 结束时间end
	 */
	public List<String> getByTime(String no,String start, String end) {
		List<String> lstStr = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance() ;
			DocumentBuilder db=dbf.newDocumentBuilder();
	        Document doc=db.parse(path + "Transfers.txt");
			NodeList list=doc.getElementsByTagName("trans");// 获取所有的账户种类节点
			
			
			String timeStart="2011-3-7";
			String timeend="2011-3-19";
			
			Date date1 = Date.valueOf(timeStart);
			Date date2 = Date.valueOf(timeend);
			
			
			
			//获得属性和属性 值
			for(int i = 0; i < list.getLength();i++ ){
				Node node = list.item(i);
				NamedNodeMap nnm=list.item(i).getAttributes();
				String attrValue1=nnm.item(0).getNodeValue();//获得属性值
				String attrValue2=nnm.item(1).getNodeValue();//获得属性值
					NodeList n2 = node.getChildNodes();
					if(attrValue1.equals(no.trim())) 
					{
						String time=n2.item(1).getFirstChild().getNodeValue();
						Date date3 = Date.valueOf(time);

						if(date3.before(date2)&&date3.after(date1)){
						lstStr.add(attrValue2);
						lstStr.add(time);
					}
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
		return lstStr;
	}

	
	
	/**
	 * 获得操作下的全部信息
	 * 功能号 025
	 * 参数 账户id,操作类型例如：(12120114,"转账")
	 */
	public List<String> getByTimeAcct(String no, String type) {
		List<String> lstStr = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance() ;
			DocumentBuilder db=dbf.newDocumentBuilder();
	        Document doc=db.parse(path + "Transfers.txt");
			NodeList list=doc.getElementsByTagName("trans");// 获取所有的账户种类节点
			
			
			for(int i = 0; i < list.getLength();i++ ){
				Node node = list.item(i);
				NamedNodeMap nnm=list.item(i).getAttributes();
				String attrValue1=nnm.item(0).getNodeValue();//获得属性值
				String attrValue2=nnm.item(1).getNodeValue();//获得属性值
					NodeList n2 = node.getChildNodes();
					if(attrValue1.equals(no)&&attrValue2.equals(type)) 
					{
					for(int j=1;j<n2.getLength();j=j+2){
						lstStr.add(n2.item(j).getFirstChild().getNodeValue());
						System.out.println(n2.item(j).getFirstChild().getNodeValue());
					}
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
		return lstStr;
	}
}