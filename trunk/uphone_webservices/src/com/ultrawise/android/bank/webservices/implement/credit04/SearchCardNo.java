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

import com.ultrawise.android.bank.webservices.base.credit04.ICreditNo;

public class SearchCardNo implements ICreditNo{
	private String path = Thread.currentThread().getContextClassLoader()
	.getResource("").getPath();
    private BufferedReader br;
    private String line;
    private StringBuffer data = new StringBuffer(); 
   ArrayList<String> list=new ArrayList<String>();
	public String SearchNoByCardPyte(String type) {
		// TODO Auto-generated method stub
		
		String paypal=Paypal(type);
		data=new StringBuffer();
		String str="";
		if(paypal==null)
		{
		  return "false";
		}
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
			NodeList n1 = doc.getElementsByTagName("actype");
			for(int i = 0; i < n1.getLength();i++){
				Node my_node = n1.item(i);
				if( my_node.getFirstChild().getNodeValue().equals(paypal))
				{
					Node parentNode =my_node.getParentNode(); 
					NamedNodeMap norderid=parentNode.getAttributes();
					list.add(norderid.getNamedItem("id").getNodeValue());
					list.add(":");
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
		for(int i=0;i<list.size()-1;i++)
		{
			str+=list.get(i);
		}
		return str;
	}
    public String Paypal(String type)
    {
    	try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path + "/paypal.txt"),"utf-8"));
			while ((line = br.readLine()) != null) {
				data.append(line + "\n");
				//System.out.println(line);
			}
			ByteArrayInputStream stream = new ByteArrayInputStream(data
					.toString().getBytes("utf-8"));

			//StringBuffer sb = new StringBuffer();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db=dbf.newDocumentBuilder();//获得解析器
			Document doc=db.parse(stream);//获得根结点
			NodeList n1 = doc.getElementsByTagName("tyname");
			for(int i = 0; i < n1.getLength();i++ ){
				Node my_node = n1.item(i);
				if( my_node.getFirstChild().getNodeValue().equals(type))
				{
					Node parentNode =my_node.getParentNode(); 
					NamedNodeMap norderid=parentNode.getAttributes();
					return norderid.getNamedItem("id").getNodeValue(); 
				}	
			}	
    	}catch(Exception e)
    	{
    		System.out.println("dfd出错啦");
    	}
    	
    	return null;
    }
}
