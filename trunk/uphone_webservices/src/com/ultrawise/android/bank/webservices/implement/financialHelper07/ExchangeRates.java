package com.ultrawise.android.bank.webservices.implement.financialHelper07;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ExchangeRates {
	
	public static List<String> readRates(InputStream inStream, String[] condition){
		List<String> ratesData = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inStream);
			NodeList nodeL = doc.getElementsByTagName(condition[1]);
			ratesData.add(nodeL.item(0).getChildNodes().item(1).getTextContent());
			nodeL = doc.getElementsByTagName(condition[2]);
			ratesData.add(nodeL.item(0).getChildNodes().item(1).getTextContent());
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
		return ratesData;
	}

}
