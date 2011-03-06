package com.ultrawise.android.bank.webservices.base.financialHelper07;

import java.io.InputStream;

import org.codehaus.jettison.json.JSONObject;

public interface AccessXml {
	
	public JSONObject readRates(InputStream inStream);
	
	

}
