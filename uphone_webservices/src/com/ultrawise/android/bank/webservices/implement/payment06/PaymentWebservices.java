package com.ultrawise.android.bank.webservices.implement.payment06;

import java.util.Date;

import it.sauronsoftware.base64.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/payment")
public class PaymentWebservices {
	private static int functionNo = 0;
	private JSONObject json = null;
	String	monthName=null;
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	@POST
	public JSONObject doPost(@FormParam("value") String anything){
		String mingWen = Base64.decode(anything,"utf-8");
		System.out.println("传递过来的明文："+mingWen);
		String[] values = mingWen.split(":");
		try {
			functionNo = Integer.parseInt(values[0]);
			if(values.length >1)
				monthName=values[1];
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("传入的功能号不是数字");
		}
		
		switch (functionNo){
		case 601:{
			monthName=values[1];
			PaymentDetail detail=new PaymentDetail();
			mingWen=detail.getPaymentNameAndMoney("zhangsan", monthName);
			System.out.println(">>>>>>>>>>><<<<<<<<<<<"+mingWen);
			json=wrapUp(mingWen);
			break;
		      }
		case 60100:{
			monthName=values[1];
			System.out.println(monthName);
			PaymentDetail detail=new PaymentDetail();
			mingWen=detail.getPaymentDetail("zhangsan","水费", monthName);
			json=wrapUp(mingWen);
			break;
		      }
		case 60101:{
			monthName=values[1];
			PaymentDetail detail=new PaymentDetail();
			mingWen=detail.getPaymentDetail("zhangsan", "电费",monthName);
			json=wrapUp(mingWen);
			break;
		      }
		case 60102:{
			monthName=values[1];
			PaymentDetail detail=new PaymentDetail();
			mingWen=detail.getPaymentDetail("zhangsan", "煤气费",monthName);
			json=wrapUp(mingWen);
			break;
		      }
		case 60103:{
			monthName=values[1];
			PaymentDetail detail=new PaymentDetail();
			mingWen=detail.getPaymentDetail("zhangsan", "房租费",monthName);
			json=wrapUp(mingWen);
			break;
		      }
		case 60301:{
			PaymentLastMonth  paymentLastMonth = new PaymentLastMonth();
			Date dateNow = paymentLastMonth.StringToDate(values[2]);
			Date beforeDate = paymentLastMonth.StringToDate(values[3]);
			mingWen = paymentLastMonth.getLastPaymentName(values[1], dateNow, beforeDate);
			json = wrapUp(mingWen);
			break;
		}
		case 60302:{
			PaymentLastMonth  paymentLastMonth = new PaymentLastMonth();
			Date dateNow = paymentLastMonth.StringToDate(values[2]);
			Date beforeDate = paymentLastMonth.StringToDate(values[3]);
			int fileName = Integer.parseInt(values[4]);
			int flag = Integer.parseInt(values[5]);
			mingWen = paymentLastMonth.getLastPaymentDetail(values[1], dateNow, beforeDate, fileName, flag);
			System.out.println("传回去的明文："+mingWen);
			json = wrapUp(mingWen);
			break;
		}
		
		case 60401:{
			Paymentmanage manage = new Paymentmanage();
			mingWen = manage.getPaymentmanageName();
			json = wrapUp(mingWen);
			break;
		}
		case 60402:{
			Paymentmanage manage = new Paymentmanage();
			mingWen = manage.updatePayment(values[1], values[2]);
			json = wrapUp(mingWen);
			break;
		}
			
		}
		
		return json;
	}
	
	private JSONObject wrapUp(String value) {
		JSONObject wrapJsonObj = new JSONObject();
		value = Base64.encode(value,"utf-8");
		try {
			wrapJsonObj.put("miwen", value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wrapJsonObj;
	}
}
