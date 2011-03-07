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

import com.ultrawise.android.bank.webservices.base.credit04.ISearchAccountPyte;

public class SearchPyte implements ISearchAccountPyte{
	private String path = Thread.currentThread().getContextClassLoader()
	.getResource("").getPath();
    private BufferedReader br;
    private String line;
    private StringBuffer data ; 
   String list="";
	public String Search() {
		// TODO Auto-generated method stub
		data=new StringBuffer();
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
			for(int i = 0; i < n1.getLength();){
				list=list+n1.item(i).getFirstChild().getNodeValue();
	             System.out.println(n1.item(i).getFirstChild().getNodeValue());
	             i++;
	             if(i!=n1.getLength())
	             {
	            	 list+=":";
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
		
		return list;
	}
	public String SearchBindNo() {
		// TODO Auto-generated method stub
		data=new StringBuffer();
		String list1="";
		ArrayList<String> ls=new ArrayList<String>();
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
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db=dbf.newDocumentBuilder();//获得解析器
			Document doc=db.parse(stream);//获得根结点
			NodeList n1 = doc.getElementsByTagName("bind");
			
			for(int i = 0; i < n1.getLength();i++){
				Node my_node = n1.item(i);
				if( my_node.getFirstChild().getNodeValue().equals("是"))
				{
					Node parentNode =my_node.getParentNode(); 
					NamedNodeMap norderid=parentNode.getAttributes();
					ls.add(norderid.getNamedItem("id").getNodeValue());
					ls.add(":");
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
		for(int i=0;i<ls.size()-1;i++)
		{
			list1+=ls.get(i);
		}
		return list1;
	}
}
