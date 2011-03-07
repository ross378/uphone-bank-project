package com.ultrawise.android.bank.webservices.implement.financialHelper07;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import it.sauronsoftware.base64.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path ("/aaa")
public class FinacialHelperWebServices {
	
	private static final int USER_LOGIN = 0;
	private static final int FUNCTION_DEPOSITE = 1;
	private static final int FUNCTION_LOAN = 2;
	private static final int FUNCTION_EXCHANGE = 3;
	private static final int LOGIN_CHECKING = 10;
	private static String checkingCode = "";
	
	@Consumes("application/x-www-form-urlencoded")//接收表单数据
	@Path("/dd")
	@Produces("application/json")//返回json数据
	@POST
	public JSONObject responseClientRequest(@FormParam("value") String clientParams){
		
		JSONObject jsonResult = new JSONObject();
	
		String[] str = (Base64.decode(clientParams, "utf-8")).split(":");
		
		int funNo = Integer.parseInt(str[0]);
		System.out.println("--------length----------" + str.length);
		
//		System.out.println(funNo);
//		System.out.println(str[1]);
//		System.out.println(str[2]);
//		String[] str = {"zhangsan", "1234"};
//		int funNo = 10;
		
	
		switch(funNo)
		{
			case USER_LOGIN:
			{
				List<String> rand = UserLogin.createExtraCode();
				FinacialHelperWebServices.checkingCode = rand.get(0);
				jsonResult = DataHandle.stringConvertJSON("extracode", rand);
				break;
			}
			case FUNCTION_DEPOSITE:
			{			
				InputStream inStream = ConnectTxtFileHelper.createTxtStream("derate.txt");
				List<String> seekResult = DepositeRates.readRates(inStream);
				jsonResult = DataHandle.stringConvertJSON("depositeRates", seekResult);
				break;
			}
			case FUNCTION_LOAN:
			{
				InputStream inStream = ConnectTxtFileHelper.createTxtStream("loanrate.txt");
				List<String> seekResult = LoanRates.readRates(inStream);
				jsonResult = DataHandle.stringConvertJSON("loanrates", seekResult);
				break;
			}
			case FUNCTION_EXCHANGE:
			{
				InputStream inStream = ConnectTxtFileHelper.createTxtStream("exchangerate.txt");
				List<String> seekResult = ExchangeRates.readRates(inStream, str);
				jsonResult = DataHandle.stringConvertJSON("exchangerates", seekResult);
				break;
			}
			case LOGIN_CHECKING:
			{
				System.out.println("---------" + funNo);
				System.out.println(str[0]);
				System.out.println(str[1]);
				System.out.println(str[2]);
				System.out.println(str[3]);
				List<String> backInfo = new ArrayList<String>();
				if(str[3].equals(FinacialHelperWebServices.checkingCode))
				{
					InputStream inStream = ConnectTxtFileHelper.createTxtStream("Users.txt");
					backInfo = UserLogin.checkingUserLogin(inStream, str);
					jsonResult = DataHandle.stringConvertJSON("userLogin", backInfo);
				}else
				{
					backInfo.add("false");
					backInfo.add("extracode error!");
					jsonResult = DataHandle.stringConvertJSON("userLogin", backInfo);
				}
				System.out.println(backInfo.toString());
				List<String> rand = UserLogin.createExtraCode();
				FinacialHelperWebServices.checkingCode = rand.get(0);
				break;
			}
		}
		
		
		return jsonResult;
	}

}
