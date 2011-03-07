package com.ultrawise.android.bank.webservices.implement.credit04;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.commons.collections.Transformer;
import com.ultrawise.android.bank.webservices.base.credit04.ICancleCredit;

public class CancleCard implements ICancleCredit{

	private String path = Thread.currentThread().getContextClassLoader()
	.getResource("").getPath();
    private BufferedReader br;
    private String line;
    private StringBuffer data = new StringBuffer(); 
	public String CancleCardByOrderid(String[] orderid) {
		// TODO Auto-generated method stub
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
			NodeList n1 = doc.getElementsByTagName("accout");
			int b=0;
			for(int i = 0; i < n1.getLength();i++ ){
				Node my_node = n1.item(i);
				NamedNodeMap norderid=my_node.getAttributes();
				//System.out.println("xiao===="+norderid.getNamedItem("id").getNodeValue()+"----"+orderid[1]);
				if(norderid.getNamedItem("id").getNodeValue().equals(orderid[1])&&
				  doc.getElementsByTagName("actpwd").item(i).getFirstChild().getNodeValue().equals(orderid[2]))
				{
					Node parentNode = n1.item(i); 
					NodeList n2 = parentNode.getChildNodes();  
					for(int j=0;j<n2.getLength();j++){    
						String modifyNode= n2.item(j).getNodeName();  
						System.out.println(modifyNode);
						if(modifyNode.equalsIgnoreCase("state")){    
							n2.item(j).getTextContent();
							n2.item(j).setTextContent("0");
							b++;
							break;
						}
					}
					
					break;
				}
			}
			System.out.println("b="+b);
			if(b==1)
			{
				TransformerFactory tFactory =TransformerFactory.newInstance();
				javax.xml.transform.Transformer transformer=null;
				try {
					transformer = tFactory.newTransformer();
				} catch (TransformerConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DOMSource source = new DOMSource(doc);//得到DOM源
				String path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
				StreamResult result = new StreamResult(new java.io.File(path+"/accout.txt"));
				try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("true");
				return "true";
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
		System.out.println("false");
		return "false";
	}
	public String ActiveCardByOrderid(String[] orderid) {
		// TODO Auto-generated method stub
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
			NodeList n1 = doc.getElementsByTagName("accout");
			int b=0;
			for(int i = 0; i < n1.getLength();i++ ){
				Node my_node = n1.item(i);
				NamedNodeMap norderid=my_node.getAttributes();
				//System.out.println("xiao===="+norderid.getNamedItem("id").getNodeValue()+"----"+orderid[1]);
				if(norderid.getNamedItem("id").getNodeValue().equals(orderid[1])&&
				  doc.getElementsByTagName("actpwd").item(i).getFirstChild().getNodeValue().equals(orderid[2]))
				{
					Node parentNode = n1.item(i); 
					NodeList n2 = parentNode.getChildNodes();  
					for(int j=0;j<n2.getLength();j++){    
						String modifyNode= n2.item(j).getNodeName();  
						System.out.println(modifyNode);
						if(modifyNode.equalsIgnoreCase("state")){    
							n2.item(j).setTextContent("1");
							b++;
							break;
						}
					}
					
					break;
				}
			}
			System.out.println("b="+b);
			if(b==1)
			{
				TransformerFactory tFactory =TransformerFactory.newInstance();
				javax.xml.transform.Transformer transformer=null;
				try {
					transformer = tFactory.newTransformer();
				} catch (TransformerConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DOMSource source = new DOMSource(doc);//得到DOM源
				String path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
				StreamResult result = new StreamResult(new java.io.File(path+"/accout.txt"));
				try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("true");
				return "true";
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
		System.out.println("false");
		return "false";
	}
    
}
