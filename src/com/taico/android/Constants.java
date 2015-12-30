package com.taico.android;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;

import com.taico.android.obj.ItemsObj;

import android.graphics.Typeface;

public class Constants {
 
	public static String device_id; 
	
	public static Typeface ProximaNova_Regular, ProximaNova_Light, ProximaNova_Bold;
	public static String str_users_name="",PASSWORD="",FIRSTNAME="",LASTNAME="",ADDRESS1="",ADDRESS2="",ADDRESS3="",CITY="",STATE="",DAYPHONE="",EVENINGPHONE="",EMAIL="",CLIENTNAME="",SINGLEORDERED="";	
//	public static String host_nam = "http://testesh.eventsnow.com/WebServices/";
//	public static Pattern pattern1 = Pattern
//			.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");
	
	public static Pattern pattern1 = Pattern
			.compile( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	  

	 
	public static String MAIN_HOST ="http://stg.incentiveweb.com/webservices/TaicoWSController.cfc?";
	public static String prouducts_url = "http://stg.incentiveweb.com/products/";
	public static String logo_url = "http://stg.incentiveweb.com/";
//	public static String MAIN_HOST1 ="http://stg.incentiveweb.com/webservices/TaicoWSController.cfc?method=fnGetWhatsHot&AuthToken=MTA3NjI6MzU3NjQ3MDUwODEzNDQwOjQ5QzE1MDc4LTUwNTYtQTA2My1FNDI1NjUyRDMwNzAyNjBF&User={\"clientID\":5,\"clientRatio\":1.0,\"CLIENTMAXPOINTS\":0,\"CLIENTMINPOINTS\":0}";
  
	public static JSONArray WhatsHotists_jsonArray=null;
	public static String AUTH_TOKEN = "", itemCount = "0", wishlistcount = "0",
			jsn_res = "", clientRatio = "0", CLIENTMAXPOINTS = "0",
			CLIENTMINPOINTS = "0", clientID = "", admin = "1", DIRECTORYs = "",
			MAINLOGO = "",Catid="0",productID="",clientOrderApproval="0",
			clienttaxpercent="0",CLIENTALLOWTAX="",SINGLEITEM="",
			isTaxed="",isCompanyReq="0",isGift="0",isOthercountry="0",COMPANYNAME="",GETBUTTONCOLOR="",GETBUTTONFONTCOLOR="",CLIENTSHOWPRODUCTPOINTS="$",HPMESSAGE="";
	public static int User_Id, SHOWWISHLIST, SHOWADDTOCARTBUTTON, Level = 1, 
			parentid = 1, child_id = 1, intentFlag = 1, limit = 10,flg_bak_button=1,str_points_incart=0,str_points_left=0;
	public static ArrayList<ItemsObj> shops_history = new ArrayList<ItemsObj>();

	 public static ArrayList<String> parients_id = new ArrayList<String>();
	public static ArrayList<String> result_json = new ArrayList<String>();
	public static ArrayList<String> whats_ids = new ArrayList<String>(); 
	public static ArrayList<String> states = new ArrayList<String>();
	public static ArrayList<String> states_ids = new ArrayList<String>();
	public static ArrayList<JSONArray> loads_arr = new ArrayList<JSONArray>();
	
	 public static ArrayList<String> roomsNGuestDataS = new ArrayList<String>();
}
  