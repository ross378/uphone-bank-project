package com.urltrawise.android.bank.webservices.base.account_management01;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jettison.json.JSONObject;


public interface IAccountInfo {
	
	public JSONObject getActAccType();
	
}
