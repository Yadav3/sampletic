package com.taico.android.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.taico.android.Constants;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

@SuppressWarnings("deprecation")
@SuppressLint("SimpleDateFormat")
public class Utility {
	public Context mContext;

	public Utility(Context context) {
		mContext = context;
	}
	String baseUrl1 = "http://enowdev.etg.net/Webservices2/";
	String baseUrl2 = "http://enowdev.etg.net/WebServices3/";

	


	public boolean IsNetConnected(Context context) {
		boolean isConnected = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++) {

					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						isConnected = true;
					}
				}
		}
		System.out.println("Internet Connection is: " + isConnected);
		return isConnected;
	}

	public Bitmap getBitmap(String url) {    //pass the complete URL of the web service query
        Bitmap bmp = null;
        try {
            HttpClient client = new DefaultHttpClient();
            URI imageURI = new URI(url);
            HttpGet req = new HttpGet();
            req.setURI(imageURI);
           HttpResponse response = client.execute(req);
            bmp = BitmapFactory.decodeStream(response.getEntity().getContent());    //BitmapFactory decodes the InputStream of the HttpResponse
       } catch (URISyntaxException e) {        //catch those exceptions
           Log.e("ERROR",e.getMessage());
       } catch (ClientProtocolException e) {
           Log.e("ERROR",e.getMessage());
       } catch (IllegalStateException e) {
           Log.e("ERROR",e.getMessage());
      } catch (IOException e) {
           Log.e("ERROR",e.getMessage());
       }
       return bmp;
   }
	
	public void showAlertNoInternetService(Context context) {
		AlertDialog.Builder altDialog = new AlertDialog.Builder(context);
		altDialog
				.setMessage("Sorry, Network is not available. Please try again later"); 
		
		altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		altDialog.show();
	}

	


@SuppressLint("SimpleDateFormat")
public Boolean current_dat(String Lst_updated,String Current_date_tme)
{ 
Boolean session_tim=false;
	try {
//		String lst_updated = "08/19/2015 11:5:00";
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
 
		Date lst_updated_time = null;
		Date current_date_tme = null;
		
		@SuppressWarnings("unused")
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//		String current_date_time = df.format(Calendar.getInstance().getTime());
		current_date_tme = format.parse(Current_date_tme);
		
		lst_updated_time = format.parse(Lst_updated);

		//in milliseconds
		long diff = current_date_tme.getTime() - lst_updated_time.getTime();
		
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

	
		
//		System.out.print( date + " date,\n ");
//		
//		System.out.print(c.getTime() + " time, ");
		System.out.print(diffDays + "days, \n");
		System.out.print(diffHours + "hours,\n");
		System.out.print(diffMinutes + "minutes\n");
		System.out.print(diffSeconds + "seconds.");
		
		/* if (diffDays <=0) {
			 
			 if(diffHours<=0)
			 {
				 System.err.println("Difference in number of days (2) : " + diffDays);
		            if (diffMinutes <=5) {
		            	session_tim=true;
		            	System.err.println(diffMinutes+"if");
		            }
		            else
		            {
		            	session_tim=false;
		            	System.err.println(diffMinutes+"else");
		            }
			 }
			 else
			 {
				 session_tim=false;
			 }
	           
	        } else {
	        	session_tim=false;
	            System.err.println(">24");
	        } */
		
		if (diffDays <=0) {
			 System.err.println(diffDays+" diffDays if");
			 if(diffHours>=0)
			 {
				 System.err.println(diffHours+" diffHours if");
		            if (diffMinutes <=3) {
		            	session_tim=true;
		            	System.err.println(diffMinutes+" diffMinutes if");
		            }
		            else
		            {
		            	session_tim=false;
		            	System.err.println(diffMinutes+" diffMinutes else");
		            }
			 }
			 else
			 {
				 System.err.println(diffHours+" diffHours else");
				 session_tim=false;
			 }
	           
	        } else {
	        	session_tim=false;
	            System.err.println(diffDays+"  diffDays else");
	        } 
 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return session_tim;
	
}



 
	public void inputValidation(String sss) {
		AlertDialog.Builder altDialog = new AlertDialog.Builder(mContext);
		 altDialog.setMessage(sss); 
		altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		altDialog.show();
	}

	public void dialogExample() {

		AlertDialog.Builder altDialog = new AlertDialog.Builder(mContext);
		altDialog.setMessage("No data from server!"); // here add your message
		altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		altDialog.show();
	}
	
	public ArrayList<String> roomsNGuestDataMethod(int num) {
		Constants.roomsNGuestDataS.clear();
		for (int i = 1; i <=num; i++) {
			Constants.roomsNGuestDataS.add(""+i);
		}
		if(num>=15)
		{
			
		}
		else
		{
			Constants.roomsNGuestDataS.add("Over 10");
		}
		
		return Constants.roomsNGuestDataS;
	}
	
	public ArrayList<String> statesDataS() {
		Constants.states.clear();
		
		Constants.states.add("Alabama");
		Constants.states.add("Alaska");
		Constants.states.add("Arizona");
		Constants.states.add("Arkansas");
		Constants.states.add("California");
		Constants.states.add("Colorado");
		Constants.states.add("Connecticut");
		Constants.states.add("Delaware");
		Constants.states.add("District Of Columbia");
		Constants.states.add("Florida");
		Constants.states.add("Georgia");
		Constants.states.add("Hawaii");
		Constants.states.add("Idaho");
		Constants.states.add("Illinois");
		Constants.states.add("Indiana");
		Constants.states.add("Iowa");
		Constants.states.add("Kansas");
		Constants.states.add("Kentucky");
		Constants.states.add("Louisiana");
		Constants.states.add("Maine");
		Constants.states.add("Maryland");
		Constants.states.add("Massachusetts");
		Constants.states.add("Michigan");
		Constants.states.add("Minnesota");
		Constants.states.add("Mississippi");
		Constants.states.add("Missouri");
		Constants.states.add("Montana");
		Constants.states.add("Nebraska");
		Constants.states.add("Nevada");
		Constants.states.add("New Hampshire");
		Constants.states.add("New Jersey");
		Constants.states.add("New Mexico");
		Constants.states.add("New York");
		Constants.states.add("North Carolina");
		Constants.states.add("North Dakota");
		Constants.states.add("Ohio");
		Constants.states.add("Oklahoma");
		Constants.states.add("Oregon");
		Constants.states.add("Pennsylvania");
		Constants.states.add("Rhode Island");
		Constants.states.add("South Carolina");
		Constants.states.add("South Dakota");
		Constants.states.add("Tennessee");
		Constants.states.add("Texas");
		Constants.states.add("Utah");
		Constants.states.add("Vermont");
		Constants.states.add("Virginia");
		Constants.states.add("Washington");
		Constants.states.add("West Virginia");
		Constants.states.add("Wisconsin");
		Constants.states.add("Wyoming"); 
	////////////////////////////////////////////////////////
		
		Constants.states_ids.clear();
		Constants.states_ids.add("AL");
		Constants.states_ids.add("AK");
		Constants.states_ids.add("AZ");
		Constants.states_ids.add("AR");
		Constants.states_ids.add("CA");
		Constants.states_ids.add("CO");
		Constants.states_ids.add("CT");
		Constants.states_ids.add("DE");
		Constants.states_ids.add("DC");
		Constants.states_ids.add("FL");
		Constants.states_ids.add("GA");
		Constants.states_ids.add("HI");
		Constants.states_ids.add("ID");
		Constants.states_ids.add("IL");
		Constants.states_ids.add("IN");
		Constants.states_ids.add("IA");
		Constants.states_ids.add("KS");
		Constants.states_ids.add("KY");
		Constants.states_ids.add("LA");
		Constants.states_ids.add("ME");
		Constants.states_ids.add("MD");
		Constants.states_ids.add("MA");
		Constants.states_ids.add("MI");
		Constants.states_ids.add("MN");
		Constants.states_ids.add("MS");
		Constants.states_ids.add("MO");
		Constants.states_ids.add("MT");
		Constants.states_ids.add("NE");
		Constants.states_ids.add("NV");
		Constants.states_ids.add("NH");
		Constants.states_ids.add("NJ");
		Constants.states_ids.add("NM");
		Constants.states_ids.add("NY");
		Constants.states_ids.add("NC");
		Constants.states_ids.add("ND");
		Constants.states_ids.add("OH");
		Constants.states_ids.add("OK");
		Constants.states_ids.add("OR");
		Constants.states_ids.add("PA");
		Constants.states_ids.add("RI");
		Constants.states_ids.add("SC");
		Constants.states_ids.add("SD");
		Constants.states_ids.add("TN");
		Constants.states_ids.add("TX");
		Constants.states_ids.add("UT");
		Constants.states_ids.add("VT");
		Constants.states_ids.add("VA");
		Constants.states_ids.add("WA");
		Constants.states_ids.add("WV");
		Constants.states_ids.add("WI");
		Constants.states_ids.add("WY");
		
		return Constants.states;
		
	}
	
	// validating email id
			public boolean isValidEmail(String email) {
				String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				Pattern pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher matcher = pattern.matcher(email);
				return matcher.matches();
			}
}
