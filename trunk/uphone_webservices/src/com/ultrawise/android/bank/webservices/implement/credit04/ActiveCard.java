package com.ultrawise.android.bank.webservices.implement.credit04;

import com.ultrawise.android.bank.webservices.base.credit04.ICreditLook;

public class ActiveCard implements ICreditLook{
        String creditNo;
        String password;
        public ActiveCard(String creditNo1,String password1)
        {
           creditNo=creditNo1;
           password=password1;
        }
		public String lookup(String creditNo, String password) {
			// TODO Auto-generated method stub
			System.out.println("dsffd");
			return password;
		}
        
        
}
