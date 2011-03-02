package com.ultrawise.android.bank.webservices.implement.account_query02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import com.ultrawise.android.bank.webservices.base.account_Query02.IAccountQueryInfo;


public class AccountDAOQuery implements IAccountQueryInfo{

	public String getAccountQueryById(int id) {
		// TODO Auto-generated method stub
		return getById(id);
	}

	public String getAccountQueryById(String account) {
		// TODO Auto-generated method stub
		return "您好啊";
	}
	private String getById(int id)
	{
		StringBuffer data=new StringBuffer();
		String line=null;
			
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(
						new FileInputStream("D:\\test1.txt")));
			while((line=br.readLine())!=null){
				data.append(line+"\n");
				
				System.out.println("输入数据："+data.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		return data.toString();
	}

}