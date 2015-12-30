package com.taico.android;

import java.util.regex.Matcher;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.taico.android.util.Utility;

public class ForgotPassword extends Activity {
	// LinearLayout shops_layout,my_account_layout;
	Button btn_back;
	EditText user_txt, email_txt, mob_txt;
	TextView submit_txt;
	Utility util;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_password);

		util = new Utility(ForgotPassword.this);

		submit_txt = (TextView) findViewById(R.id.submit_txt);
		submit_txt.setTypeface(Constants.ProximaNova_Regular);

		user_txt = (EditText) findViewById(R.id.user_txt);
		email_txt = (EditText) findViewById(R.id.email_txt);
		mob_txt = (EditText) findViewById(R.id.mob_txt);

		user_txt.setTypeface(Constants.ProximaNova_Regular);
		email_txt.setTypeface(Constants.ProximaNova_Regular);
		mob_txt.setTypeface(Constants.ProximaNova_Regular);

		btn_back = (Button) findViewById(R.id.btn_back);
		btn_back.setText("Forgot Password");
		btn_back.setTypeface(Constants.ProximaNova_Regular);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ForgotPassword.this.finish();

			}
		});

		submit_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (util.IsNetConnected(ForgotPassword.this)) {
					if (email_txt.getText().toString().length() == 0) {
						util.inputValidation("Please Enter E-mail ");
					} else {

						Matcher matcher1 = Constants.pattern1.matcher(email_txt
								.getText().toString().trim());

						if (matcher1.matches()) {

								 new AsyncForgotPwd().execute();
						} else {
							util.inputValidation("Please enter valid email id");
						}

					}
				} else {
					util.showAlertNoInternetService(ForgotPassword.this);
				}

			}
		});

	}
	
	private class AsyncForgotPwd extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog; 

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(ForgotPassword.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
 
		@Override
		protected String doInBackground(String... params) {
			
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST+"method=fnForgotPassword&email="+email_txt.getText().toString());
		 	
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					if (null != myProgressDialog && myProgressDialog.isShowing()) {
						myProgressDialog.dismiss();
					}
 
					if (null == result || result.length() == 0) {
						
					} 
					else
					{  
						
//						System.out.println(result);
						JSONObject jObject = new JSONObject(result);
								AlertDialog.Builder altDialog = new AlertDialog.Builder(ForgotPassword.this);
								 altDialog.setMessage(""+jObject.getString("DATA")); 
								altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.dismiss();
										if (myProgressDialog.isShowing())
											myProgressDialog.dismiss();
									}
								});
								altDialog.show();
						
					
					}
					
				
					if (myProgressDialog.isShowing())
						myProgressDialog.dismiss();
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (myProgressDialog.isShowing())
					myProgressDialog.dismiss();
				// }

				
			} catch (Exception e) {
				if (myProgressDialog.isShowing())
					myProgressDialog.dismiss();
			}

		}
	}
}
