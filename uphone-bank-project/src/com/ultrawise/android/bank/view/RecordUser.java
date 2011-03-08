package com.ultrawise.android.bank.view;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RecordUser {
	public static void saveFile(OutputStream outStream,String userinfor){
		userinfor = Base64.encode(userinfor); 
    	try {
			outStream.write(userinfor.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
	
	public static String getFile(InputStream inStream){
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    	byte[] data = null;
    	byte[] b = new byte[1024];
    	try {
			int len = -1;
			while((len=inStream.read(b)) != -1){
				outStream.write(b, 0, len);
			}
			data = outStream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				outStream.close();
				inStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		String result = Base64.decode(new String(data));
    	return result;
    }
}
