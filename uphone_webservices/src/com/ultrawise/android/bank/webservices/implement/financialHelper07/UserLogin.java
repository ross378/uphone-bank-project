package com.ultrawise.android.bank.webservices.implement.financialHelper07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserLogin {
	
	public static List<String> createExtraCode(){
		
		List<String> result = new ArrayList<String>();
		Random ranBuild = new Random();
		int ran = ranBuild.nextInt(899) + 100;
		result.add(String.valueOf(ran));
		return result;
	}

}
