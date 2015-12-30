package com.taico.android;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.taico.android.util.Utility;

@SuppressWarnings("deprecation")
public class Login extends Activity {
	Button signin_btn;
	TextView txt_forgot_pwd;
	EditText editEmail, editPassword;
	public static SharedPreferences pref;
	public static Editor editor;
	public static String Key_GET_USER_ID = "USER_ID";
	public static String Key_GET_LST_SERVICE_CALL = "SERVICE_CALL";
	public static String Key_GET_AUTHTOKEN = "AUTHTOKEN_TOKEN";
	public static String current_date_time = "";
	public static int bg_color = Color.parseColor("#e4eaee");
	Utility util;
	TelephonyManager telephonyManager;
	HttpResponse response;
	private String strUser, strPwd;
	public static String strUserId, strPasswrd;
	public static String strIMEINo="";
 
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			Constants.whats_ids.clear();
		/*	DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
			.cacheOnDisc(true).cacheInMemory(true)
			.imageScaleType(ImageScaleType.EXACTLY)
			.displayer(new FadeInBitmapDisplayer(300)).build();

	ImageLoaderConfiguration imageloader_config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
			.memoryCache(new WeakMemoryCache())
			.discCacheSize(100 * 1024 * 1024).build();

	ImageLoader.getInstance().init(imageloader_config);*/

		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Constants.device_id = telephonyManager.getDeviceId();

		util = new Utility(Login.this);
		Constants.ProximaNova_Regular = Typeface.createFromAsset(
				getApplicationContext().getAssets(), "GothamBook.ttf");

//		bg_color = Color.parseColor("#e4eaee");
	    bg_color=Color.parseColor("#ffffff");
		pref = getApplicationContext().getSharedPreferences(
				"Tico_session_times", MODE_PRIVATE);
		editor = pref.edit();
		
		 
  
		/*
		 * SplashScreen.editor1.putString(SplashScreen.Key_GET_LANGUAGE_ID, "" +
		 * SplashScreen.flg); SplashScreen.editor1.commit();
		 * 
		 * SplashScreen.pref1.getString(SplashScreen.Key_GET_LANGUAGE_ID, "")
		 */

		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			current_date_time = df.format(Calendar.getInstance().getTime());

		} catch (Exception e) {

		}

		 

		editEmail = (EditText) findViewById(R.id.editEmail);
		editPassword = (EditText) findViewById(R.id.editPassword);

		editEmail.setTypeface(Constants.ProximaNova_Regular);
		editPassword.setTypeface(Constants.ProximaNova_Regular);
//  
//		editEmail.setText("lady t");
//		editPassword.setText("merida"); 

		signin_btn = (Button) findViewById(R.id.signin_btn);
		signin_btn.setTypeface(Constants.ProximaNova_Regular);
		signin_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				 if (util.IsNetConnected(Login.this)) {
				if (editEmail.getText().toString().length() == 0
						|| editPassword.getText().toString().length() == 0) {
					util.inputValidation("Please Enter User name and Password");
				} else {

					@SuppressWarnings("unused")
					Matcher matcher1 = Constants.pattern1.matcher(editEmail
							.getText().toString().trim());

//					if (matcher1.matches()) {
 
						strUser = editEmail.getText().toString();
						strPwd = editPassword.getText().toString();
						new AsyLogins().execute();
						// startActivity(new Intent(Login.this,
						// DashBorad.class));
						// Login.this.finish();
//					} else {
//						util.inputValidation("Please enter valid email id");
//					}

				}
				 } else {
				 util.showAlertNoInternetService(Login.this);
				 }

			}
		});

		txt_forgot_pwd = (TextView) findViewById(R.id.txt_forgot_pwd);
		txt_forgot_pwd.setTypeface(Constants.ProximaNova_Regular);
		txt_forgot_pwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Login.this, ForgotPassword.class));

			}
		});

	}
	 
	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = getApplicationContext().getAssets().open(
					"Taico Webservices.txt");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

	 
	
	private class AsyLogins extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog; 

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(Login.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
 
		@Override
		protected String doInBackground(String... params) {
			
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST+"method=fnUserLogin&udid="+Constants.device_id+"&username="+strUser.toString().replace(" ", "%20")+"&password="+strPwd.toString());
		 	
 
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
						if(result.contains("ERRORS"))
						{
							JSONObject jObject1 = jObject.getJSONObject("ERRORS");
							
								AlertDialog.Builder altDialog = new AlertDialog.Builder(Login.this);
								 altDialog.setMessage("Invalid Username (or) Password."); 
//								 +jObject1.getString("MESSAGE")
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
						else
						{

							JSONObject jsonDataobj =jObject.getJSONObject("DATA");
							if(jObject.getString("AUTHTOKEN").length()>0)
							{
								if(jObject.has("AUTHTOKEN"))
								{
									Constants.AUTH_TOKEN=""+ jObject.getString("AUTHTOKEN");
								}
								 
								if(jsonDataobj.has("ID"))
								{
									Constants.User_Id=jsonDataobj.getInt("ID");
								}
								if(jsonDataobj.has("USERNAME"))
								{
									Constants.str_users_name=""+ jsonDataobj.getString("USERNAME");
								}
								if(jsonDataobj.has("PASSWORD"))
								{
									Constants.PASSWORD=jsonDataobj.getString("PASSWORD"); 
								}
								if(jsonDataobj.has("FIRSTNAME"))
								{
									Constants.FIRSTNAME=jsonDataobj.getString("FIRSTNAME"); 
								}
								if(jsonDataobj.has("LASTNAME"))
								{
									Constants.LASTNAME=jsonDataobj.getString("LASTNAME"); 
								}
								if(jsonDataobj.has("SINGLEITEM"))
								{ 
									Constants.SINGLEITEM=""+jsonDataobj.getInt("SINGLEITEM");
								}
								
								if(jsonDataobj.has("SHOWADDTOCARTBUTTON"))
								{
									Constants.SHOWADDTOCARTBUTTON=jsonDataobj.getInt("SHOWADDTOCARTBUTTON");
								}
								
								if(jsonDataobj.has("SHOWWISHLIST"))
								{
									Constants.SHOWWISHLIST=jsonDataobj.getInt("SHOWWISHLIST");
								}
								 
								
								if(jsonDataobj.has("ADMIN"))
								{
									Constants.admin=jsonDataobj.getString("ADMIN");
								}
								if(jsonDataobj.has("DIRECTORY"))
								{
									Constants.DIRECTORYs=jsonDataobj.getString("DIRECTORY");
								}
								if(jsonDataobj.has("MAINLOGO"))
								{
									Constants.MAINLOGO=jsonDataobj.getString("MAINLOGO");
								}
								if(jsonDataobj.has("CLIENTORDERAPPROVAL"))
								{
									Constants.clientOrderApproval=""+jsonDataobj.getInt("CLIENTORDERAPPROVAL");
								}
								if(jsonDataobj.has("CLIENTTAXPERCENT"))
								{
									Constants.clienttaxpercent=""+jsonDataobj.getString("CLIENTTAXPERCENT");
								} 
								
								if(jsonDataobj.has("CLIENTALLOWTAX"))
								{ 
									Constants.CLIENTALLOWTAX=""+jsonDataobj.getInt("CLIENTALLOWTAX");
								}
								
								if(jsonDataobj.has("ADDRESS1"))
								{ 
									Constants.ADDRESS1=jsonDataobj.getString("ADDRESS1");
								}
								if(jsonDataobj.has("ADDRESS2"))
								{ 
									Constants.ADDRESS2=jsonDataobj.getString("ADDRESS2");
								}
								if(jsonDataobj.has("ADDRESS3"))
								{ 
									Constants.ADDRESS3=jsonDataobj.getString("ADDRESS3");
								}
								if(jsonDataobj.has("CITY"))
								{ 
									Constants.CITY=jsonDataobj.getString("CITY");
								}
								if(jsonDataobj.has("STATE"))
								{ 
									Constants.STATE=jsonDataobj.getString("STATE");
								}
								if(jsonDataobj.has("DAYPHONE"))
								{ 
									Constants.DAYPHONE=jsonDataobj.getString("DAYPHONE");
								}
								if(jsonDataobj.has("EVENINGPHONE"))
								{ 
									Constants.EVENINGPHONE=jsonDataobj.getString("EVENINGPHONE");
								}
								if(jsonDataobj.has("EMAIL"))
								{ 
									Constants.EMAIL=jsonDataobj.getString("EMAIL");
								}
								if(jsonDataobj.has("ISOTHERCNTRY"))
								{ 
									Constants.isOthercountry=""+jsonDataobj.getInt("ISOTHERCNTRY");
								}
								
								/*if(jsonDataobj.has("CLIENTMINPOINTS"))
								{ 
									Constants.CLIENTMINPOINTS=""+jsonDataobj.getInt("CLIENTMINPOINTS");
								}
								if(jsonDataobj.has("ISOTHERCNTRY"))
								{ 
									Constants.isOthercountry=""+jsonDataobj.getInt("ISOTHERCNTRY");
								}*/
								
								if(jsonDataobj.has("CLIENTID"))
								{ 
									Constants.clientID=""+jsonDataobj.getInt("CLIENTID");
								}
								if(jsonDataobj.has("CLIENTRATIO"))
								{ 
									Constants.clientRatio=""+jsonDataobj.getString("CLIENTRATIO"); 
								}
								
								if(jsonDataobj.has("CLIENTMINPOINTS"))
								{ 
									Constants.CLIENTMINPOINTS=""+jsonDataobj.getInt("CLIENTMINPOINTS");
								}
								
								if(jsonDataobj.has("CLIENTMAXPOINTS"))
								{ 
									Constants.CLIENTMAXPOINTS=""+jsonDataobj.getInt("CLIENTMAXPOINTS");
								}
								if(jsonDataobj.has("COMPANYNAME"))
								{ 
									Constants.COMPANYNAME=jsonDataobj.getString("COMPANYNAME");
								}
								
								if(jsonDataobj.has("GETBUTTONFONTCOLOR"))
								{ 
									Constants.GETBUTTONFONTCOLOR=jsonDataobj.getString("GETBUTTONFONTCOLOR");
								}
								else
								{
									Constants.GETBUTTONFONTCOLOR=""; 
								}
								
								if(jsonDataobj.has("GETBUTTONCOLOR"))
								{ 
									Constants.GETBUTTONCOLOR=jsonDataobj.getString("GETBUTTONCOLOR");
								}
								else
								{
									Constants.GETBUTTONCOLOR=""; 
								}
								
								
								if(jsonDataobj.has("CLIENTSHOWPRODUCTPOINTS"))
								{ 
									Constants.CLIENTSHOWPRODUCTPOINTS=jsonDataobj.getString("CLIENTSHOWPRODUCTPOINTS");
								}
								else
								{
									Constants.CLIENTSHOWPRODUCTPOINTS="$"; 
								}
								
								
								if(jsonDataobj.has("HPMESSAGE"))
								{ 
									Constants.HPMESSAGE=jsonDataobj.getString("HPMESSAGE");
								}
								else
								{
									Constants.HPMESSAGE=""; 
								}
								
								if(jsonDataobj.has("CLIENTNAME"))
								{ 
									Constants.CLIENTNAME=jsonDataobj.getString("CLIENTNAME");
								}
								else
								{
									Constants.CLIENTNAME=""; 
								}
								
								if(jsonDataobj.has("SINGLEORDERED"))
								{ 
									Constants.SINGLEORDERED=jsonDataobj.getString("SINGLEORDERED");
								}
								else
								{ 
									Constants.SINGLEORDERED=""; 
								}
								
								
								
								
								
						/*
								JSONObject obj = new JSONObject();

								obj.put("clientID", ""+jsonDataobj.getString("CLIENTID"));
	 							obj.put("clientRatio", ""+jsonDataobj.getString("CLIENTRATIO"));
								obj.put("CLIENTMAXPOINTS:", ""+jsonDataobj.getString("CLIENTMAXPOINTS"));
								obj.put("CLIENTMINPOINTS:", ""+jsonDataobj.getString("CLIENTMINPOINTS"));
								

								System.out.print("obj"+obj);*/
								  
								/** presently not using */	 		 
							/*	 
								Login.editor.putString(
										Login.Key_GET_LST_SERVICE_CALL, ""
												+ current_date_time);
								Login.editor.putString(Login.Key_GET_AUTHTOKEN, ""
										+ jObject.getString("AUTHTOKEN"));

								Login.editor.putString(Login.Key_GET_USER_ID, ""
										+ jsonDataobj.getInt("ID"));
								Login.editor.commit();*/
								
							 startActivity(new Intent(Login.this,DashBorad.class));
							 Login.this.finish();
						}
						
						
						}
					
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
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		  
		Intent intent = new Intent(Login.this, Login.class);
		// set the new task and clear flags
//		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
		 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);		
		moveTaskToBack(true);
		
		
		
	}
} 

/*
 * GradientDrawable gd = (GradientDrawable)
 * pickDate.getBackground().getCurrent(); gd.setColor(Color.BLUE);
 * //gd.setStroke(2, Color.GREEN, 0, 0);
 */