package com.ultrawise.android.bank.webservices.implement.account_query02;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("/hello")
public class AccountSevericeQuery {
	
	@GET
	@Produces( { "application/json" })
	@Path(value = "/login/{account}/{pwd}")
	public JSONObject login(@PathParam("account") String account,
			@PathParam("pwd") String pwd) {
		JSONObject jsonObj = new JSONObject();
		try {
			//jsonObj.put("welcome", AccountQueryManager.getInstance().getAccountQueryById(account));	
			jsonObj.put("welcome","用户名");
			jsonObj.put("password",AccountQueryManager.getInstance().getAccountQueryById(2));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObj;
	}
}
