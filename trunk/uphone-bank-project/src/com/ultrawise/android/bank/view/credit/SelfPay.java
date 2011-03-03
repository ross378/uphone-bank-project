package com.ultrawise.android.bank.view.credit;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ultrawise.android.bank.view.ABankMain;
import com.ultrawise.android.bank.view.FinancialConsultation;
import com.ultrawise.android.bank.view.account_query.AccountQuery;
import com.ultrawise.android.bank.view.credit.ActivateCard.ActivateCardButtonListener;
import com.ultrawise.android.bank.view.transfer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SelfPay extends Activity {
	TextView  tvCredit;
	Intent intent;
	ImageView btnCoustom;
	ImageView btnMain;
	Button nextButton=null;
	Button paya;
	Button payaa;
	TextView creditpaya;
	TextView creditpayaa;
	ImageView btnReturn;
	EditText creditname;
/*	private String serviceAddress = "http://10.1.1.102:8080/credit/services";
	private String requestParameters;*/
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.selepay);
	        ImageView iv_now = (ImageView)this.findViewById(R.id.btnCoustom);
	        iv_now.setVisibility(View.GONE);
		    	intent = new Intent();
		         tvCredit= (TextView)this.findViewById(R.id.class_first);
		        tvCredit.setText("首页>信用卡>信用卡还款 ");
		        tvCredit.setTextSize(13);
		        tvCredit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						intent=new Intent();
						 intent.setClass(SelfPay.this, CreditView.class);
						 SelfPay.this.startActivity(intent);
					}
				});
		        tvCredit.setVisibility(View.VISIBLE);
               creditname=(EditText)findViewById(R.id.creditNameEdit);
		        creditpaya=(TextView)findViewById(R.id.creditpaya);
		        creditpaya.setText("12324333453443543");
		        creditpayaa=(TextView)findViewById(R.id.creditpayaa);
		        creditpayaa.setText("12324333453443544");
		        paya=(Button)findViewById(R.id.selfPaya);
		        paya.setText("还款");
		        payaa=(Button)findViewById(R.id.selfPayaa);
		        payaa.setText("还款");
		        paya.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						/*JSONArray json = Login("42:6432554325423456");
						
						try {
							System.out.println(json.get(0));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						intent = new Intent();
						intent.setClass(SelfPay.this, SelfPayDetail.class);
						SelfPay.this.startActivity(intent);
					}
				});
		        payaa.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						intent = new Intent();
						intent.setClass(SelfPay.this, SelfPayDetail.class);
						SelfPay.this.startActivity(intent);
					}
				});
	        //设置底部按钮
			btnCoustom = (ImageView) this.findViewById(R.id.btnMain);
			btnCoustom.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent=new Intent();
					intent.setClass(SelfPay.this, ABankMain.class);
					SelfPay.this.startActivity(intent);
				}
			});
			btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(SelfPay.this,  FinancialConsultation.class);
					SelfPay.this.startActivity(intent);
					finish();
				}
			});
			   // 返回键设定
			btnReturn = (ImageView) this.findViewById(R.id.returnToPre);
			btnReturn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					onBackPressed();
					finish();
				}
			});
		               	//btnCoustom.setVisibility(View.VISIBLE);
		btnMain = (ImageView) this.findViewById(R.id.btnHelper);
			btnMain.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent=new Intent();
					intent.setClass(SelfPay.this, ABankMain.class);
					SelfPay.this.startActivity(intent);
				}
			});
			  //获得下一步按钮对象，并设置其鼠标单击事件监听
		      nextButton=(Button)this.findViewById(R.id.selfPay_next);
		        nextButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						intent = new Intent();
						intent.setClass(SelfPay.this, SelfPayDetail.class);
						SelfPay.this.startActivity(intent);
					}
				});
	    }
	/* private JSONArray Login(String msg) {
			// Get the public interface's value by path
			// It's look like http://localhost:8080/hello/login/solo/123
			String strMi = Base64.encode(msg);
			System.out.println("加密后："+strMi);
			requestParameters = "/creditpay/paydetail/"+strMi+"/";
			HttpGet httpget = new HttpGet(serviceAddress + requestParameters);
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			try {
				
				response = httpclient.execute(httpget);

				// Just log,don't focus on it
				Log.i("REST:Response Status line", response.getStatusLine()
						.toString());
				
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String result = convertStreamToString(instream);
					JSONObject json = new JSONObject(result);

					// Parsing
					JSONArray nameArray = json.names();
					JSONArray valArray = json.toJSONArray(nameArray);

					instream.close();
					return valArray;

				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				httpget.abort();
				//httppost.abort();
				httpclient.getConnectionManager().shutdown();
			}
			return null;
		}

		// Convert InputStream to String
		private static String convertStreamToString(InputStream is) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}*/
}
