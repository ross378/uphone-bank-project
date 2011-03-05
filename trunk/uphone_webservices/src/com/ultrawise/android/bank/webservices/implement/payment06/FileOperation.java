package com.ultrawise.android.bank.webservices.implement.payment06;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FileOperation {
		
	//读取的文件后返回一个Document对象
	public static Document getFileDocument(String fileName){
		StringBuffer data = new StringBuffer();
		String line = "";
		ByteArrayInputStream stream = null;
		Document doc = null;
		try {
			String path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path+"\\"+fileName+".txt"),"utf-8"));
			while((line=br.readLine())!=null){
				data.append(line+"\n");
			}
			stream=new ByteArrayInputStream(data.toString().getBytes("utf-8"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			doc=db.parse(stream);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}
	
	
	//将Document对象保存到文件中
	public static boolean saveDocument(Document doc,String fileName){
		TransformerFactory tFactory =TransformerFactory.newInstance();
		Transformer transformer=null;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		DOMSource source = new DOMSource(doc);
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		StreamResult result = new StreamResult(new java.io.File(path+"\\"+fileName+".txt"));
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
