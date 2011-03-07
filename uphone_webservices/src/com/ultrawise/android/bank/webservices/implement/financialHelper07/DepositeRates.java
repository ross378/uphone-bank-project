package com.ultrawise.android.bank.webservices.implement.financialHelper07;



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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;


public class DepositeRates {
	
	
public static List<String> readRates(InputStream inStream){
		
		List<String> ratesData = new ArrayList<String>();
		StringBuffer sb=new StringBuffer();
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(inStream);
			
			NodeList rates_type = doc.getElementsByTagName("rates_type");
			Node current_deposite = rates_type.item(0);	
			NodeList time = current_deposite.getChildNodes();
			ratesData.add(time.item(1).getTextContent());

			Node dingqi_deposite = rates_type.item(1);
			NodeList deposite_mode = dingqi_deposite.getChildNodes();
			for(int i = 0; i < deposite_mode.getLength(); i ++)
			{
				Node modeNo = deposite_mode.item(i);
				time = modeNo.getChildNodes();
				for(int j = 1; j < time.getLength(); j ++)
				{
					ratesData.add(time.item(j).getTextContent());
				}
			}
			
			
			Node inform_deposite = rates_type.item(2);
			time = inform_deposite.getChildNodes();
			ratesData.add(time.item(1).getTextContent());
			ratesData.add(time.item(3).getTextContent());
			
			Node accord_deposite = rates_type.item(3);
			time = accord_deposite.getChildNodes();
			ratesData.add(time.item(1).getTextContent());
			
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
		List<String> back = new ArrayList<String>();
		String str;
		for(int i = 0; i < ratesData.size(); i ++)
		{
			str = ratesData.get(i).trim();
			if(str.endsWith("%"))
			{
				back.add(str);
			}
		}
		System.out.println("one" + back);
		return back;		
	}
}
