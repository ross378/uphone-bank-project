package com.urltrawise.android.bank.webservices.implement.account_management01;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.urltrawise.android.bank.webservices.base.account_management01.IAccountInfo;

@Path("/")
public class AccountInfo implements IAccountInfo {
	@GET
	@Produces("application/json")
	@Path(value = "/getactacctype00001")
	public JSONObject getActAccType() {
		// TODO Auto-generated method stub
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("welcome", "hello,json");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}

}
