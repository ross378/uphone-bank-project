package com.ultrawise.android.bank.webservices.implement.financialHelper07;

import java.io.InputStream;
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
	
	@Consumes("application/x-www-form-urlencoded")//接收表单数据
	@Path("/dd")
	@Produces("application/json")//返回json数据
	@POST
	public JSONObject responseClientRequest(@FormParam("value") String clientParams){
		
		JSONObject jsonResult = new JSONObject();
		
		String[] str = (Base64.decode(clientParams, "utf-8")).split(":");
		int funNo = Integer.parseInt(str[0]);
		switch(funNo)
		{
			case USER_LOGIN:
			{
				List<String> rand = UserLogin.createExtraCode();
				jsonResult = DataHandle.stringConvertJSON("extracode", rand);
				try {
					System.out.println(Base64.decode(jsonResult.getString("extracode")));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case FUNCTION_DEPOSITE:
			{			
				InputStream inStream = ConnectTxtFileHelper.createTxtStream("derate.txt");
				List<String> seekResult = DepositeRates.readRates(inStream);
				jsonResult = DataHandle.stringConvertJSON("depositeRates", seekResult);
				try {
					System.out.println(jsonResult.getString("depositeRates"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case FUNCTION_LOAN:
			{
				InputStream inStream = ConnectTxtFileHelper.createTxtStream("loanrate.txt");
				List<String> seekResult = LoanRates.readRates(inStream);
				jsonResult = DataHandle.stringConvertJSON("loanrates", seekResult);
				try {
					System.out.println(jsonResult.getString("loanrates"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case FUNCTION_EXCHANGE:
			{
				InputStream inStream = ConnectTxtFileHelper.createTxtStream("exchangerate.txt");
				List<String> seekResult = ExchangeRates.readRates(inStream, str);
				jsonResult = DataHandle.stringConvertJSON("exchangerates", seekResult);
				try {
					System.out.println(jsonResult.getString("loanrates"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		System.out.println("hello");
		
		return jsonResult;
	}

}
