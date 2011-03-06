package com.ultrawise.android.bank.webservices.implement.financialHelper07;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoanRates {
	
	public static List<String> readRates(InputStream inStream){
		
		List<String> ratesData = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inStream);
			NodeList mode = doc.getElementsByTagName("mode");
			Node short_loanrates = mode.item(0);
			NodeList time = short_loanrates.getChildNodes();
			ratesData.add(time.item(1).getTextContent());
			ratesData.add(time.item(2).getTextContent());
			Node long_loanrates = mode.item(1);
			time = long_loanrates.getChildNodes();
			ratesData.add(time.item(1).getTextContent());
			ratesData.add(time.item(2).getTextContent());		
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
