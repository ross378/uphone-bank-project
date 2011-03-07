package com.ultrawise.android.bank.webservices.implement.credit04;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ultrawise.android.bank.webservices.base.credit04.IPayCredit;

public class PayCredit implements IPayCredit{
	private String path = Thread.currentThread().getContextClassLoader()
	.getResource("").getPath();
    private BufferedReader br;
    private String line;
    private StringBuffer data = new StringBuffer(); 
   ArrayList<String> list=new ArrayList<String>();
	public String PayDetailByNo(String orderid) {
		// TODO Auto-generated method stub
		data=new StringBuffer();
		list.clear();
		String str="";
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path + "/accout.txt"),"utf-8"));
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
				//System.out.println(line);
			}
			ByteArrayInputStream stream = new ByteArrayInputStream(data
					.toString().getBytes("utf-8"));
			//StringBuffer sb = new StringBuffer();
			DocumentBuilderFactory dbff = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbb=dbff.newDocumentBuilder();//获得解析器
			Document doc=dbb.parse(stream);//获得根结点
			NodeList n1 = doc.getElementsByTagName("accout");
			for(int i = 0; i < n1.getLength();i++){
				Node my_node = n1.item(i);
				NamedNodeMap norderid=my_node.getAttributes();
				if( norderid.getNamedItem("id").getNodeValue().equals(orderid))
				{
					
                    String ss=PayUser(norderid.getNamedItem("userid").getNodeValue());
					list.add(orderid);
					list.add(ss);
					 list.add(doc.getElementsByTagName("repayment").item(i).getFirstChild().getNodeValue());
					 list.add(doc.getElementsByTagName("minpayment").item(i).getFirstChild().getNodeValue());
					 list.add(doc.getElementsByTagName("duedata").item(i).getFirstChild().getNodeValue());
				     break;
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
		for(int i=0;i<list.size();i++)
		{
			str+=list.get(i)+":";
		}
		System.out.println(str);
		return str;
	}
   public String PayUser(String userid)
   {
	   data=new StringBuffer();
		list.clear();
		String str="";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path + "/Users.txt"),"utf-8"));
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
				//System.out.println(line);
			}
			ByteArrayInputStream stream = new ByteArrayInputStream(data
					.toString().getBytes("utf-8"));
			//StringBuffer sb = new StringBuffer();
			DocumentBuilderFactory dbff = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbb=dbff.newDocumentBuilder();//获得解析器
			Document doc=dbb.parse(stream);//获得根结点
			NodeList n1 = doc.getElementsByTagName("user");
			for(int i = 0; i < n1.getLength();i++){
				Node my_node = n1.item(i);
				NamedNodeMap norderid=my_node.getAttributes();
				if( norderid.getNamedItem("userid").getNodeValue().equals(userid))
				{
				    return doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();	 
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
		return str;
   }
}
