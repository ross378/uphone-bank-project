package com.ultrawise.android.bank.webservices.implement.financialHelper07;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConnectTxtFileHelper {
	
	public static InputStream createTxtStream(String filePath){
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath() + filePath;
		StringBuffer data=new StringBuffer();
		String line=null;
		ByteArrayInputStream stream = null;
		BufferedReader br = null;
		try{
			br=new BufferedReader(new InputStreamReader(new FileInputStream(path)));

			while((line=br.readLine())!=null){
				data.append(line+"\n");
				
			}
			stream=new ByteArrayInputStream(data.toString().getBytes());
		}catch(FileNotFoundException e1){
			e1.printStackTrace();
		}catch(IOException e2){
			e2.printStackTrace();
		}	
		return stream;
	}

}
