package com.urltrawise.android.bank.webservices.severice.account_query02;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.urltrawise.android.bank.webservices.manager.account_query02.AccountQueryManager;

@Path("/hello")
public class AccountSevericeQuery {
	
	@GET
	@Produces( { "application/json" })
	@Path(value = "/login/{account}/{pwd}")
	public JSONObject login(@PathParam("account") String account,
			@PathParam("pwd") String pwd) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("welcome", AccountQueryManager.getInstance().getAccountQueryById(account));	
			jsonObj.put("password", pwd);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObj;
	}
}
