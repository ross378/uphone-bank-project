package com.ultrawise.android.bank.webservices.implement.payment06;

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
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	@POST
	public JSONObject doPost(@FormParam("value") String anything){
		String mingWen = Base64.decode(anything,"utf-8");
		String[] values = mingWen.split(":");
		try {
			functionNo = Integer.parseInt(values[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("传入的功能号不是数字");
		}
		
		switch (functionNo){
		case 60401:
			Paymentmanage manage = new Paymentmanage();
			mingWen = manage.getPaymentmanageName();
			json = wrapUp(mingWen);
			break;
			
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
