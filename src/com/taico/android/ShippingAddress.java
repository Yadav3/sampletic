package com.taico.android;

import java.util.regex.Matcher;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.util.Utility;

public class ShippingAddress extends Activity {
	Button btn_home,menus;
	RelativeLayout favLayout,cartLayout,ray_header,logo_rrr; 
	ImageView menu,imageView1;
	LinearLayout rrr_scr,lll_show_logo;
	TextView place_order_txt,txt_wish_cnt1,txt_crt_cnt1,state_name_txt0,policy_Txt,policy_Txt1,static_txt1,static_txt2;
	EditText user_txt, first_name_txt, last_name_txt, 
	company_name_txt, 
			floor_txt, addrs1_txt, addrs2_txt, addrs3_txt, zip_txt, cit_txt,
			state_name_txt, day_phone_txt, email_txt,user_nam_txt,pwd_txt,zip_txt0,f_nam_txt,l_nam_txt,e_phone_txt,day_phone_txt0,e_phone_txt0;
	ScrollView scrollView1;  
	boolean flg=false,men_flg=false;;
	String str_policy="0";
	Utility util;
	String state_codes="",day_phn="",evn_phn="",zip_cod="",str_ISERROR="",state_codes1="";
	public static String res="";
	LinearLayout liner_lay,menlayout_shipping;
	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shipping_address_new); 
		util = new Utility(ShippingAddress.this);
		Constants.states.clear();
		Constants.states_ids.clear(); 
		statesData();
		
		if(Constants.STATE.length()>0)
		{
			String upper = Constants.STATE.toUpperCase();
		int retval=Constants.states_ids.indexOf(upper); 
		state_codes1=Constants.states.get(retval); 
		  state_codes=Constants.STATE;
		} 
		else
		{ 
			state_codes1=""; 
			state_codes="";  
		} 
		static_txt1 = (TextView) findViewById(R.id.static_txt1);
		static_txt2 = (TextView) findViewById(R.id.static_txt2);
		 
		static_txt1.setTypeface(Constants.ProximaNova_Regular);
		static_txt2.setTypeface(Constants.ProximaNova_Regular);
		
		txt_wish_cnt1 = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt1 = (TextView) findViewById(R.id.txt_crt_cnt1);

		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount);
		
		txt_wish_cnt1.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt1.setTypeface(Constants.ProximaNova_Regular);
		
		/** Change the app background color and font color in application*/
		if(Constants.GETBUTTONCOLOR.length()>0&&Constants.GETBUTTONFONTCOLOR.length()>0)
		{
			
			  GradientDrawable gd = (GradientDrawable)
					  txt_wish_cnt1.getBackground().getCurrent(); gd.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd.setStroke(0, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
			 
			 GradientDrawable gd1 = (GradientDrawable)
					 txt_crt_cnt1.getBackground().getCurrent(); gd1.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd1.setStroke(0, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
			 
			 txt_wish_cnt1.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
			 txt_crt_cnt1.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
		}
		else
		{  
			
			txt_wish_cnt1.setTextColor(Color.WHITE);
			txt_crt_cnt1.setTextColor(Color.WHITE); 
			txt_wish_cnt1.setBackgroundResource(R.drawable.red_circle);
			txt_crt_cnt1.setBackgroundResource(R.drawable.red_circle); 
			
		}
		 
		btn_home= (Button)findViewById(R.id.btn_home);
		btn_home.setText(R.string.shipping_address);
		btn_home.setTypeface(Constants.ProximaNova_Regular); 
		menu = (ImageView) findViewById(R.id.menu);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		
		ray_header=(RelativeLayout)findViewById(R.id.ray_header);
		ray_header.setBackgroundColor(Login.bg_color);
		 
		rrr_scr=(LinearLayout)findViewById(R.id.rrr_scr);
		rrr_scr.setBackgroundColor(Login.bg_color);
		 
		scrollView1=(ScrollView)findViewById(R.id.scrollView1);
		scrollView1.setBackgroundColor(Login.bg_color);
		 
		
		policy_Txt= (TextView)findViewById(R.id.policy_Txt);
		policy_Txt1= (TextView)findViewById(R.id.policy_Txt1);
		state_name_txt0= (TextView)findViewById(R.id.state_name_txt0);
		addrs1_txt= (EditText)findViewById(R.id.addrs1_txt);
		addrs2_txt= (EditText)findViewById(R.id.addrs2_txt);
		addrs3_txt= (EditText)findViewById(R.id.addrs3_txt);
		zip_txt= (EditText)findViewById(R.id.zip_txt);
		zip_txt0= (EditText)findViewById(R.id.zip_txt0);
		cit_txt= (EditText)findViewById(R.id.cit_txt);
		state_name_txt= (EditText)findViewById(R.id.state_name_txt);
		day_phone_txt= (EditText)findViewById(R.id.day_phone_txt);
		day_phone_txt0= (EditText)findViewById(R.id.day_phone_txt0);
		
		email_txt= (EditText)findViewById(R.id.email_txt);
		user_nam_txt= (EditText)findViewById(R.id.user_nam_txt);
		pwd_txt= (EditText)findViewById(R.id.pwd_txt);
		f_nam_txt= (EditText)findViewById(R.id.f_nam_txt);
		l_nam_txt= (EditText)findViewById(R.id.l_nam_txt);
		e_phone_txt= (EditText)findViewById(R.id.e_phone_txt);
		e_phone_txt0= (EditText)findViewById(R.id.e_phone_txt0);
		/*user_txt= (EditText)findViewById(R.id.user_txt);
		email_txt= (EditText)findViewById(R.id.email_txt);
		first_name_txt= (EditText)findViewById(R.id.first_name_txt);
		last_name_txt= (EditText)findViewById(R.id.last_name_txt);
		company_name_txt= (EditText)findViewById(R.id.company_name_txt);
		floor_txt= (EditText)findViewById(R.id.floor_txt);*/
		 
		state_name_txt0.setTypeface(Constants.ProximaNova_Regular); 
		zip_txt0.setTypeface(Constants.ProximaNova_Regular); 
		
//		state_name_txt1.setVisibility(View.VISIBLE);
//		zip_txt1.setVisibility(View.VISIBLE);
//		 
		e_phone_txt.setTypeface(Constants.ProximaNova_Regular);
		e_phone_txt0.setTypeface(Constants.ProximaNova_Regular);
		addrs1_txt.setTypeface(Constants.ProximaNova_Regular); 
		addrs2_txt.setTypeface(Constants.ProximaNova_Regular); 
		addrs3_txt.setTypeface(Constants.ProximaNova_Regular); 
		zip_txt.setTypeface(Constants.ProximaNova_Regular); 
		cit_txt.setTypeface(Constants.ProximaNova_Regular); 
		state_name_txt.setTypeface(Constants.ProximaNova_Regular); 
		day_phone_txt.setTypeface(Constants.ProximaNova_Regular); 
		day_phone_txt0.setTypeface(Constants.ProximaNova_Regular); 
		email_txt.setTypeface(Constants.ProximaNova_Regular); 
		user_nam_txt.setTypeface(Constants.ProximaNova_Regular); 
		pwd_txt.setTypeface(Constants.ProximaNova_Regular); 
		f_nam_txt.setTypeface(Constants.ProximaNova_Regular);
		l_nam_txt.setTypeface(Constants.ProximaNova_Regular);
		
		policy_Txt.setTypeface(Constants.ProximaNova_Regular);
		policy_Txt1.setTypeface(Constants.ProximaNova_Regular);
		
//		user_nam_txt.setText(Constants.str_users_name);
//		pwd_txt.setText(Constants.PASSWORD);
		f_nam_txt.setText(Constants.FIRSTNAME);
		l_nam_txt.setText(Constants.FIRSTNAME);
		addrs1_txt.setText(Constants.ADDRESS1);
		addrs2_txt.setText(Constants.ADDRESS2);
		addrs3_txt.setText(Constants.ADDRESS3);
//		state_name_txt1
//		zip_txt
//		zip_txt1 
		cit_txt.setText(Constants.CITY);
		state_name_txt.setText(Constants.STATE);
		day_phone_txt.setText(Constants.DAYPHONE);
		e_phone_txt.setText(Constants.EVENINGPHONE);
		state_name_txt0.setText(Constants.STATE);
		day_phone_txt0.setText(Constants.DAYPHONE);
		e_phone_txt0.setText(Constants.EVENINGPHONE);
		
		email_txt.setText(Constants.EMAIL);
		
		/** Change the label if isCompanyReq is 0 = Floor/Room/Suite other wise 1= Company name in application*/
		if(Constants.isCompanyReq.equalsIgnoreCase("0"))
		{
			addrs3_txt.setHint("Floor/Room/Suite");
		}
		else if(Constants.isCompanyReq.equalsIgnoreCase("1"))
		{
			addrs1_txt.setHint("Company name");
		}
		
		/** Hide/show the labels if isOthercountry is 0  other wise 1 in application*/
		if(Constants.isOthercountry.equalsIgnoreCase("0")) 
		{
			zip_txt0.setVisibility(View.VISIBLE);
			state_name_txt0.setVisibility(View.VISIBLE);
			day_phone_txt0.setVisibility(View.VISIBLE);
			e_phone_txt0.setVisibility(View.VISIBLE);
			
			zip_txt.setVisibility(View.GONE);
			state_name_txt.setVisibility(View.GONE);
			day_phone_txt.setVisibility(View.GONE);
			e_phone_txt.setVisibility(View.GONE);
		}else
		{
			zip_txt0.setVisibility(View.GONE);
			state_name_txt0.setVisibility(View.GONE);
			day_phone_txt0.setVisibility(View.GONE);
			e_phone_txt0.setVisibility(View.GONE);
			 
			zip_txt.setVisibility(View.VISIBLE);
			state_name_txt.setVisibility(View.VISIBLE);
			day_phone_txt.setVisibility(View.VISIBLE);
			e_phone_txt.setVisibility(View.VISIBLE);
		}
		
		policy_Txt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flg){
					flg=false;
					policy_Txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check_unselect, 0,
						0, 0);
					str_policy="0";
				}
		 		else
				{ 
					flg=true;
					policy_Txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check_selected, 0,
							0, 0);
					 
					str_policy="1"; 
				}
			}
		});
		
		policy_Txt1.setText(Html
				.fromHtml("<html><body><font  size='1'color='#000000'>The address information above is accurate and I have read and understand the&nbsp</font><font color='#EA2828'><strong>"
						+ "Return Policy"
						+ "</strong></font></body></html>"));
		
		policy_Txt1.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	state_name_txt0.setText(state_codes1);
		state_name_txt0.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				LayoutInflater inflater = getLayoutInflater();
				View convertView = (View) inflater.inflate(R.layout.dialog,
						null);
				// displaying alert dialog with list of
				// numbers
				final Dialog	alertDialog = new Dialog(ShippingAddress.this);
				alertDialog.setContentView(convertView);

				alertDialog.setTitle(getResources().getString(
						R.string.select_state)); 

				ListView lv = (ListView) convertView
						.findViewById(R.id.listView);

				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
						ShippingAddress.this,
						android.R.layout.simple_list_item_1, Constants.states); 
				lv.setAdapter(adapter1);

				
				lv.setOnItemClickListener(new OnItemClickListener() {
 
					// TODO Auto-generated method stub

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						state_codes=Constants.states_ids.get(position);
						state_name_txt0.setText(Constants.states.get(position));
						alertDialog.dismiss();
					}
				});
				alertDialog.show();

			
			}
		});
		 
		

		/** Show SHOWWISHLIST/SHOWADDTOCARTBUTTON  in application*/
		if(Constants.SHOWWISHLIST==1)
		{
			favLayout.setVisibility(View.VISIBLE);
		}else if(Constants.SHOWWISHLIST==0)
		{
			favLayout.setVisibility(View.GONE);
		}
 
		if(Constants.SHOWADDTOCARTBUTTON==1)
		{
			cartLayout.setVisibility(View.VISIBLE);
		}else if(Constants.SHOWADDTOCARTBUTTON==0)
		{
			cartLayout.setVisibility(View.GONE);
		}
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*startActivity(new Intent(ShippingAddress.this,
						WishListActivity.class));*/
				ShippingAddress.this.finish();

			}
		});
		
		 
		menu.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ShippingAddress.this, DashBorad.class));
				ShippingAddress.this.finish();
			}
		});

		favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShippingAddress.this,
						WishListActivity.class));
				ShippingAddress.this.finish();

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShippingAddress.this, CartActvity.class));
				ShippingAddress.this.finish();

			}
		}); 

		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShippingAddress.this, DashBorad.class));
				ShippingAddress.this.finish();

			}
		}); 
		 menus = (Button) findViewById(R.id.menus);
		menlayout_shipping= (LinearLayout) findViewById(R.id.menlayout_shipping); 
		menlayout_shipping.setVisibility(View.GONE);
		menus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				menlayout_shipping.removeAllViews();
				if(men_flg)
				{
					men_flg=false; 
					menlayout_shipping.setVisibility(View.GONE);
				
					
				}else
				{
					menlayout_shipping.setVisibility(View.VISIBLE);
					men_flg=true;
					LayoutInflater inflater = null;
					inflater = (LayoutInflater) getApplicationContext()
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View mLinearView = inflater.inflate(R.layout.menus_layout, null);
					TextView tv_menu1 = (TextView)mLinearView.findViewById(R.id.tv_menu1);
					TextView tv_menu2 = (TextView)mLinearView.findViewById(R.id.tv_menu2);
					tv_menu1.setTypeface(Constants.ProximaNova_Regular);
					tv_menu2.setTypeface(Constants.ProximaNova_Regular);
					
					TextView tv_hom1 = (TextView)mLinearView.findViewById(R.id.tv_hom1);
					tv_hom1.setTypeface(Constants.ProximaNova_Regular);
					tv_hom1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(ShippingAddress.this, Home.class));
							ShippingAddress.this.finish();
						}
					});
					
					tv_menu1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(ShippingAddress.this, DashBorad.class));
							ShippingAddress.this.finish();
						}
					});
					 
					tv_menu2.setOnClickListener(new OnClickListener() {
									 
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(ShippingAddress.this, Login.class));
										ShippingAddress.this.finish();
									}
								});
					 
					menlayout_shipping.addView(mLinearView); 
				}
			}
		});
		
		place_order_txt= (TextView)findViewById(R.id.place_order_txt);
		place_order_txt.setTypeface(Constants.ProximaNova_Regular); 
		place_order_txt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (str_policy.equalsIgnoreCase("1")) {
					

					if (user_nam_txt.getText().toString().equalsIgnoreCase(Constants.str_users_name)) {
						
						if (pwd_txt.getText().toString().equalsIgnoreCase(Constants.PASSWORD)) {
							Matcher matcher1 = Constants.pattern1.matcher(email_txt	.getText().toString().trim());
							if (matcher1.matches()) {
								
								if(Constants.isOthercountry.equalsIgnoreCase("0")) 
								{
									
									if(day_phone_txt0.length()<10)
									{
										util.inputValidation("Please enter valid day phone number");						}
									else
									{
										day_phn=day_phone_txt0.getText().toString();
										evn_phn=e_phone_txt0.getText().toString();
										zip_cod=zip_txt0.getText().toString();
								 		
										if (util.IsNetConnected(ShippingAddress.this)) {
											new CheckOutCarts().execute();
//											startActivity(new Intent(ShippingAddress.this,OrderConfirmActivity.class)); 
//											ShippingAddress.this.finish();
											 } else {
											 util.showAlertNoInternetService(ShippingAddress.this);
											 }
									}
								}else if(Constants.isOthercountry.equalsIgnoreCase("1")) 
								{
									 
									if(day_phone_txt.length()<10)
									{
										util.inputValidation("Please enter valid day phone number");						} 
									else
									{ 
										day_phn=day_phone_txt.getText().toString();
										evn_phn=e_phone_txt.getText().toString();
										zip_cod=zip_txt.getText().toString();
										state_codes=state_name_txt.getText().toString();
										
										if (util.IsNetConnected(ShippingAddress.this)) {
											new CheckOutCarts().execute();
//											startActivity(new Intent(ShippingAddress.this,OrderConfirmActivity.class)); 
//											ShippingAddress.this.finish();
											 } else {
											 util.showAlertNoInternetService(ShippingAddress.this);
											 }
									}
								}
								
							
								
								 
							} else {
								util.inputValidation("Please enter valid email id");
							}
						}
						else
						{
							util.inputValidation("password  not valid");
						}
						
						
					}else
					{
						util.inputValidation("User name is not valid");	
					}
					
				}else
				{
					util.inputValidation("please check  terms and conditions");
				}
				
				
			
//				ShippingAddress.this.finish();
			}
		});
		
		 
		menu = (ImageView) findViewById(R.id.menu);
		  final Animation   animationtop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
	        imageView1 = (ImageView)findViewById(R.id.imageView1);
			logo_rrr = (RelativeLayout)findViewById(R.id.logo_rrr);
			String url="";
			if(Constants.MAINLOGO.contains("http"))
				{ 
				url=Constants.MAINLOGO;
				Picasso.with(getApplicationContext())
				.load(url.replace("\'", "%20").replace(" ", "%20"))
				.placeholder(R.drawable.login_logo)
				.error(R.drawable.bag3).fit().into(imageView1);
				
			}
			else 
			{
				url=Constants.logo_url+Constants.DIRECTORYs+"/images/"+Constants.MAINLOGO;
				Picasso.with(getApplicationContext())
				.load(url.replace("\'", "%20").replace(" ", "%20"))
				.placeholder(R.drawable.login_logo)
				.error(R.drawable.bag3).fit().into(imageView1);
			}
		 	
			/*new Handler().postDelayed(new Runnable() {
		      @Override
		      public void run() {
		    	  imageView1.startAnimation(animationtop);
		    	  logo_rrr.startAnimation(animationtop);
		    	  imageView1.setVisibility(View.INVISIBLE);  
		    	  logo_rrr.setVisibility(View.INVISIBLE);   
		    	  menu.setVisibility(View.VISIBLE);
		  		btn_home.setVisibility(View.VISIBLE); 
		  		favLayout.setVisibility(View.VISIBLE);
		  		cartLayout.setVisibility(View.VISIBLE);  
		      }
		    }, 3000);*/
			
			lll_show_logo = (LinearLayout) findViewById(R.id.lll_show_logo);
			  animation = AnimationUtils.loadAnimation(ShippingAddress.this, R.anim.slide_in_top);
	    	  animation.setAnimationListener(new AnimationListener() {
				  
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub
					imageView1.setVisibility(View.INVISIBLE);  
			    	  logo_rrr.setVisibility(View.INVISIBLE);   
//			    	  btn_home.setVisibility(View.INVISIBLE); 
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					  menu.setVisibility(View.VISIBLE);
				  		btn_home.setVisibility(View.VISIBLE); 
				  		favLayout.setVisibility(View.VISIBLE);
				  		cartLayout.setVisibility(View.VISIBLE); 
				  		
				  		lll_show_logo.setVisibility(View.VISIBLE); 
				}
			});
	    	  
	    	  imageView1.setAnimation(animation);
	    	  imageView1.startAnimation(animation); 
	    	   
	    	  logo_rrr.setAnimation(animation);
	    	  logo_rrr.startAnimation(animation); 
	  	
//	      }
//	    }, 3000);
		
		
		
		lll_show_logo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 imageView1.startAnimation(animationtop); 
		    	  logo_rrr.startAnimation(animationtop);
		    	  imageView1.setVisibility(View.INVISIBLE);  
		    	  logo_rrr.setVisibility(View.INVISIBLE);   
		    	  menu.setVisibility(View.VISIBLE);
		  		btn_home.setVisibility(View.VISIBLE); 
		  		favLayout.setVisibility(View.VISIBLE);
		  		cartLayout.setVisibility(View.VISIBLE); 
		  		lll_show_logo.setVisibility(View.VISIBLE); 
			}
		});
		
	}
	  
	
	private class CheckOutCarts extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(ShippingAddress.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		} 

		@Override
		protected String doInBackground(String... params) { 
			System.out.println("State"+state_codes);
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnCheckout&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid="+Constants.User_Id +"&Clientid="+Constants.clientID+"&Clientratio="
					+ Constants.clientRatio + "&Clienttaxpercent="+ Constants.clienttaxpercent
					+ "&clientOrderApproval="+ Constants.clientOrderApproval
					+ "&singleitem="+ Constants.SINGLEITEM
					+ "&isGift="+ Constants.isGift
					+ "&isTaxed="+ Constants.isTaxed
					+ "&Username="+ user_nam_txt.getText().toString()
					+ "&Password="+ pwd_txt.getText().toString()
					+ "&Firstname="+ f_nam_txt.getText().toString()
					+ "&Lastname="+ l_nam_txt.getText().toString()
					+ "&address1="+addrs1_txt.getText().toString()
					+ "&address2="+addrs2_txt.getText().toString()
					+ "&address3="+addrs3_txt.getText().toString()
					+ "&City="+cit_txt.getText().toString()
					+ "&Zipcode="+zip_cod
					+ "&State="+state_codes
					+ "&Dayphone="+day_phn
					+ "&Eveningphone="+evn_phn
					+ "&Email="+email_txt.getText().toString()
					+ "&Iscompanyreq="+Constants.isCompanyReq
					+ "&Isothercntry="+Constants.isOthercountry
					); 
 
		/*	AuthToken ,userid,Clientid,Clientratio,Clienttaxpercent,clientOrderApproval,singleitem,isGift--,isTaxed--,Username,Password,Firstname,Lastname,address1,address2
		 * address3,City,Zipcode--,State,Dayphone,Eveningphone,Email,Iscompanyreq--,Isothercntry--,*/
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {

					if (null == result || result.length() == 0) {

					} else { 
						res=result;
						JSONObject jObject = new JSONObject(result);
					
						if(jObject.has("DATA"))
						{
							JSONObject jObject1 = jObject.getJSONObject("DATA");
							str_ISERROR=jObject1.getString("ISERROR");
							
							if(str_ISERROR.equalsIgnoreCase("false"))
							{
								startActivity(new Intent(ShippingAddress.this,OrderConfirmActivity.class)); 

							}else if(str_ISERROR.equalsIgnoreCase("true"))
							{
//								util.inputValidation(""+jObject1.getString("MESSAGE"));
								
								AlertDialog.Builder altDialog = new AlertDialog.Builder(ShippingAddress.this);
								 altDialog.setMessage(""+jObject1.getString("MESSAGE")); 
								altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.dismiss();
										if (myProgressDialog.isShowing())
											myProgressDialog.dismiss();
									}
								});
								altDialog.show();
								
//								Toast.makeText(getApplicationContext(), ""+jObject1.getString("MESSAGE"), Toast.LENGTH_LONG).show();
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
	
	private void statesData() {
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
		
	}
}
