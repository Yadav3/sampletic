package com.taico.android;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.obj.ItemsObj;
import com.taico.android.util.Utility;

@SuppressLint("DefaultLocale")
public class MyAccount_History extends Activity {
	ArrayList<String> tabs = new ArrayList<String>();
	LinearLayout tab_bar;
	int tab_position, size_action, screenWidth;
	HorizontalScrollView hs;
	public static ArrayList<ItemsObj> account_history;
	public static ArrayList<ItemsObj> order_status;
	Account_history_ListAdapter adapter;
	Account_history_ListAdapter1 adapter1;
	ListView account_list, account_list1;
	Button btn_home,menus;
	LinearLayout menlayout_acc,account_history_layout,tracking_layout,change_password_layout,lll_show_logo;
	Boolean men_flg=false;;;
	RelativeLayout favLayout, cartLayout,ray_header,logo_rrr;
	TextView textView1, textView2, submit_txt, txt_wish_cnt, txt_crt_cnt,
			head_date, head_title, head_ordernum, head_points, head_ordertype,
			head_comments,head_date2,head_title2,head_ordernum2,head_qty,head_productnum,head_trackingnum,head_help,txt_change_password,tv_change_password,txt_tracking,tv_tracking,txt_account_history,tv_account_history;
	RelativeLayout change_pwd_rel,rrr;
	EditText edt_old_pwd, edt_new_pwd, edt_new_re_enter_pwd;
	LinearLayout rr_cart_menu1, title_linear,title_linear2;
	Utility util;
	ImageView menu,imageView1;
	public static String str_order_id="";
	Animation animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_history);
  
		util = new Utility(MyAccount_History.this);  
		
		account_history = new ArrayList<ItemsObj>();
		order_status = new ArrayList<ItemsObj>();
		
		txt_wish_cnt = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt = (TextView) findViewById(R.id.txt_crt_cnt1);
		txt_wish_cnt.setText(Constants.wishlistcount);
		txt_crt_cnt.setText(Constants.itemCount);
		txt_wish_cnt.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt.setTypeface(Constants.ProximaNova_Regular);
		
		rrr = (RelativeLayout) findViewById(R.id.rrr);
		
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			rrr.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			rrr.setVisibility(View.VISIBLE);
		}
		else
		{
			rrr.setVisibility(View.GONE);
		}
		

		/** Change the app background color and font color in application*/
		if(Constants.GETBUTTONCOLOR.length()>0&&Constants.GETBUTTONFONTCOLOR.length()>0)
		{
			
			  GradientDrawable gd = (GradientDrawable)
					  txt_wish_cnt.getBackground().getCurrent(); gd.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd.setStroke(0, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
			 
			 GradientDrawable gd1 = (GradientDrawable)
					 txt_crt_cnt.getBackground().getCurrent(); gd1.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd1.setStroke(0, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
			 
			 txt_wish_cnt.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
			 txt_crt_cnt.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
		}
		else
		{ 
			  
			txt_wish_cnt.setTextColor(Color.WHITE);
			txt_crt_cnt.setTextColor(Color.WHITE);
			txt_wish_cnt.setBackgroundResource(R.drawable.red_circle);
			txt_crt_cnt.setBackgroundResource(R.drawable.red_circle); 
			
		}
		 
		head_date = (TextView) findViewById(R.id.head_date);
		head_title = (TextView) findViewById(R.id.head_title);
		head_ordernum = (TextView) findViewById(R.id.head_ordernum);
		head_points = (TextView) findViewById(R.id.head_points);
		head_ordertype = (TextView) findViewById(R.id.head_ordertype);
		head_comments = (TextView) findViewById(R.id.head_comments);
		head_date2 = (TextView) findViewById(R.id.head_date2); 
		head_title2 = (TextView) findViewById(R.id.head_title2);
		head_ordernum2 = (TextView) findViewById(R.id.head_ordernum2);
		head_qty = (TextView) findViewById(R.id.head_qty);
		head_productnum = (TextView) findViewById(R.id.head_productnum);
		head_trackingnum = (TextView) findViewById(R.id.head_trackingnum);
		head_help = (TextView) findViewById(R.id.head_help); 
 		
		head_date.setTypeface(Constants.ProximaNova_Regular);
		head_title.setTypeface(Constants.ProximaNova_Regular);
	  	head_ordernum.setTypeface(Constants.ProximaNova_Regular);
		head_points.setTypeface(Constants.ProximaNova_Regular);
		head_ordertype.setTypeface(Constants.ProximaNova_Regular);
  		head_comments.setTypeface(Constants.ProximaNova_Regular);
		head_date2.setTypeface(Constants.ProximaNova_Regular);
		head_title2.setTypeface(Constants.ProximaNova_Regular);
		head_ordernum2.setTypeface(Constants.ProximaNova_Regular);
		head_qty.setTypeface(Constants.ProximaNova_Regular);
		head_productnum.setTypeface(Constants.ProximaNova_Regular);
		head_trackingnum.setTypeface(Constants.ProximaNova_Regular);
		head_help.setTypeface(Constants.ProximaNova_Regular); 
		
		change_pwd_rel = (RelativeLayout) findViewById(R.id.change_pwd_rel);
		change_pwd_rel.setBackgroundColor(Login.bg_color);
		ray_header = (RelativeLayout) findViewById(R.id.ray_header);
		ray_header.setBackgroundColor(Login.bg_color);
		
		title_linear= (LinearLayout) findViewById(R.id.title_linear);
		title_linear2= (LinearLayout) findViewById(R.id.title_linear2);
		
		rr_cart_menu1 = (LinearLayout) findViewById(R.id.rr_cart_menu1);
		rr_cart_menu1.setBackgroundColor(Login.bg_color);
		
		
		
 
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		
		
		if (util.IsNetConnected(MyAccount_History.this)) {
			new Getuserpoints().execute();
	 } else {
	 util.showAlertNoInternetService(MyAccount_History.this);
	 } 

		edt_old_pwd = (EditText) findViewById(R.id.edt_old_pwd);
		edt_new_pwd = (EditText) findViewById(R.id.edt_new_pwd);
		edt_new_re_enter_pwd = (EditText) findViewById(R.id.edt_new_re_enter_pwd);
		
		submit_txt = (TextView) findViewById(R.id.submit_txt);

		edt_old_pwd.setTypeface(Constants.ProximaNova_Regular);
		edt_new_pwd.setTypeface(Constants.ProximaNova_Regular);
		edt_new_re_enter_pwd.setTypeface(Constants.ProximaNova_Regular);
		
		submit_txt.setTypeface(Constants.ProximaNova_Regular);

		textView1.setTypeface(Constants.ProximaNova_Regular);
		textView2.setTypeface(Constants.ProximaNova_Regular);
		btn_home = (Button) findViewById(R.id.btn_home);
		btn_home.setText(R.string.my_account);
		menus = (Button) findViewById(R.id.menus);
		menlayout_acc= (LinearLayout) findViewById(R.id.menlayout_acc);
		menlayout_acc.setVisibility(View.GONE);
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
 
		
		tab_bar = (LinearLayout) findViewById(R.id.tab_bar);
		hs = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		account_list = (ListView) findViewById(R.id.account_list);
		account_list1 = (ListView) findViewById(R.id.account_list1);
		account_list.setVisibility(View.VISIBLE);
		account_list.setBackgroundColor(Login.bg_color);
		account_list1.setBackgroundColor(Login.bg_color); 
		tabs.add("Account history"); 
		tabs.add("Help/Tracking");
		tabs.add("Change password");
//		initialiseTabHost(); 
		
		account_history_layout= (LinearLayout) findViewById(R.id.account_history_layout);
		tracking_layout= (LinearLayout) findViewById(R.id.tracking_layout);
		change_password_layout= (LinearLayout) findViewById(R.id.change_password_layout);
		
		txt_account_history = (TextView) findViewById(R.id.txt_account_history);
		tv_account_history = (TextView) findViewById(R.id.tv_account_history);
		txt_account_history.setTypeface(Constants.ProximaNova_Regular);
		tv_account_history.setTypeface(Constants.ProximaNova_Regular);
		
		txt_tracking = (TextView) findViewById(R.id.txt_tracking);
		tv_tracking = (TextView) findViewById(R.id.tv_tracking);
		txt_tracking.setTypeface(Constants.ProximaNova_Regular);
		tv_tracking.setTypeface(Constants.ProximaNova_Regular);
		
		txt_change_password = (TextView) findViewById(R.id.txt_change_password);
		tv_change_password = (TextView) findViewById(R.id.tv_change_password);
		txt_change_password.setTypeface(Constants.ProximaNova_Regular);
		tv_change_password.setTypeface(Constants.ProximaNova_Regular); 
	
		
		if ( util.IsNetConnected(MyAccount_History.this)) {
//			setToslist(0);
			new Account_HistoryTask().execute();
			

		} else {
			util.showAlertNoInternetService(MyAccount_History.this);
		}
		
		account_history_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
//				if (tab_position == 0) { 
					change_pwd_rel.setVisibility(View.GONE);
					account_list.setVisibility(View.VISIBLE);
					account_list1.setVisibility(View.GONE);
					title_linear.setVisibility(View.VISIBLE);
					title_linear2.setVisibility(View.GONE);
					tv_account_history.setVisibility(View.VISIBLE);
					tv_tracking.setVisibility(View.INVISIBLE);
					tv_change_password.setVisibility(View.INVISIBLE); 
//					if (util.IsNetConnected(MyAccount_History.this)) {

//					if (account_history.size() > 0) {
						adapter = new Account_history_ListAdapter(MyAccount_History.this,
								R.layout.my_account_row, account_history);

						adapter.notifyDataSetChanged();
						account_list.setAdapter(adapter);
//					}
//					else
//					{
//						new Account_HistoryTask().execute(); 
//					}
//					}
//					else
//					{
//						util.showAlertNoInternetService(MyAccount_History.this);
//					}
//				}
			

			}
		});

		tracking_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
//				if (tab_position == 1) {
					change_pwd_rel.setVisibility(View.GONE);
					account_list1.setVisibility(View.VISIBLE);
					account_list.setVisibility(View.GONE);
					title_linear2.setVisibility(View.VISIBLE);
					title_linear.setVisibility(View.GONE);
					
					tv_account_history.setVisibility(View.INVISIBLE);
					tv_tracking.setVisibility(View.VISIBLE);
					tv_change_password.setVisibility(View.INVISIBLE);
					
					if (util.IsNetConnected(MyAccount_History.this)) {

						if (order_status.size() > 0) {
							adapter1 = new Account_history_ListAdapter1(MyAccount_History.this,
									R.layout.tracking, order_status);
							adapter1.notifyDataSetChanged();
							account_list1.setAdapter(adapter1);
						} else {
							new OrderStatusTask().execute();
						}
					
						
						

					} else {
						util.showAlertNoInternetService(MyAccount_History.this);
					}
					
//				}
				

			}
		});
 
		change_password_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
//				if (tab_position == 2) { 
					account_list1.setVisibility(View.GONE);
					account_list.setVisibility(View.GONE);
					change_pwd_rel.setVisibility(View.VISIBLE);
					title_linear.setVisibility(View.GONE);
					title_linear2.setVisibility(View.GONE);
					
					tv_account_history.setVisibility(View.INVISIBLE); 
					tv_tracking.setVisibility(View.INVISIBLE);
					tv_change_password.setVisibility(View.VISIBLE);
//			}

			}
		});
		
		submit_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/* String usr= "lady t";
				  String pwd= "merid569";
				  String psd= "password";
				  String pad= "password3";*/
				 
				String pwd =edt_new_pwd.getText().toString();
				if (util.IsNetConnected(MyAccount_History.this)) {
					if (edt_old_pwd.getText().toString().length() == 0
							|| pwd.length() == 0|| edt_new_re_enter_pwd.getText().toString().length() == 0) {
						util.inputValidation("Please Enter Old password ,new password and re entered password");
					} else {
						if((edt_old_pwd.getText().toString().equals(Constants.PASSWORD))) 
						  {
							if((pwd.length()>=6)&&(pwd.matches(".*\\d+.*"))) 
							  { 
								///////////////////////////////////////////////////////////
								if((pwd.equalsIgnoreCase(edt_new_re_enter_pwd.getText().toString())))
								  { 
									 if((pwd.equalsIgnoreCase(Constants.str_users_name)))
									  {
										  System.out.println("passowrd not same as user name");  
										  util.inputValidation("new passowrd not same as user name");
									  }
									  else 
									  { 
										  if((pwd.equals(Constants.PASSWORD)))
										  {
											  System.out.println("new passowrd not same as current passowrd"); 
											  util.inputValidation("new passowrd dose not contain as current passowrd");
										  }
										  else 
										  {  
											  if((pwd.contains(("password")))) 
											  {
												  System.out.println("passowrd not same as password"); 
												  util.inputValidation("new passowrd does not contians as word password");
											  }
											  else
											  {
												  new ChangePasswordTask().execute();
											  }
										  }
										  
									  }
								  }
								else
								{
									  util.inputValidation("new passowrd and conform password are not same");
								}
								 
								  /////////////////////////////////////////////////
							  } 
							  else
							  {
								  util.inputValidation("Password length 6 or more and contains at least one digit");
							  }
						  }
						else
						{
//							 util.inputValidation("old passowrd not same as user name");
							util.inputValidation("Entered password and old password are not same");
						}
						  
					}
				} else {
					util.showAlertNoInternetService(MyAccount_History.this);
				}
			}
		});

	
		 
		btn_home.setText(R.string.my_account);  
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyAccount_History.this,DashBorad.class));
				MyAccount_History.this.finish(); 

			}
		}); 
		 
		menus.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				menlayout_acc.removeAllViews();
				if(men_flg)
				{
					men_flg=false; 
					menlayout_acc.setVisibility(View.GONE);
				
					
				}else
				{
					menlayout_acc.setVisibility(View.VISIBLE);
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
							startActivity(new Intent(MyAccount_History.this, Home.class));
							MyAccount_History.this.finish();
						}
					});
					
					tv_menu1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(MyAccount_History.this, DashBorad.class));
							MyAccount_History.this.finish();
						}
					}); 
					
					tv_menu2.setOnClickListener(new OnClickListener() {
								 	 
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(MyAccount_History.this, Login.class));
										MyAccount_History.this.finish(); 
									}
								}); 
					 
					menlayout_acc.addView(mLinearView); 
				}
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
				/*
				 * startActivity(new Intent(MyAccount_History.this,
				 * WishListActivity.class));
				 */
				MyAccount_History.this.finish();

			}
		});

		favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyAccount_History.this,
						WishListActivity.class));
				MyAccount_History.this.finish();

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyAccount_History.this,
						CartActvity.class));
				MyAccount_History.this.finish();

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
			  animation = AnimationUtils.loadAnimation(MyAccount_History.this, R.anim.slide_in_top);
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
	
	private class Getuserpoints extends AsyncTask<String, Void, String> {

//		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
//			myProgressDialog = new ProgressDialog(MyAccount_History.this);
//			myProgressDialog.setMessage("please wait ...");
//			myProgressDialog.show();
		}
  
		@Override
		protected String doInBackground(String... params) {
			
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST+"method=fnGetUserPoints&AuthToken="+Constants.AUTH_TOKEN+"&Userid="+Constants.User_Id);
		 	
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
//					if (null != myProgressDialog && myProgressDialog.isShowing()) {
//						myProgressDialog.dismiss();
//					}
 
					if (null == result || result.length() == 0) {
						
					} 
					else
					{  
						
						JSONObject jObject = new JSONObject(result);
						JSONObject jsonDataobj =jObject.getJSONObject("DATA");
						
						if(jsonDataobj.has("points"))
						{
							Constants.str_points_incart=jsonDataobj.getInt("points");
						}
						if(jsonDataobj.has("pointsLeft"))
						{
							Constants.str_points_left=jsonDataobj.getInt("pointsLeft"); 
						}
						if(jsonDataobj.has("itemCount"))
						{
							Constants.itemCount=""+jsonDataobj.getInt("itemCount");
						}
						if(jsonDataobj.has("wishlistcount"))
						{
							Constants.wishlistcount=""+jsonDataobj.getInt("wishlistcount");
						}
					textView1
						.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_incart
										+ "</strong></b></font></body></html>"));
 
				textView2
						.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_left
										+ "</strong></b></font></body></html>"));
						txt_wish_cnt.setText(Constants.wishlistcount);
						txt_crt_cnt.setText(Constants.itemCount);
					}
					
				
//					if (myProgressDialog.isShowing())
//						myProgressDialog.dismiss();
					
				} catch (Exception e) {
					e.printStackTrace();
				}

//				if (myProgressDialog.isShowing())
//					myProgressDialog.dismiss();
				// }

				
			} catch (Exception e) {
//				if (myProgressDialog.isShowing())
//					myProgressDialog.dismiss();
			}

		}
	}

	@SuppressLint({ "InflateParams", "DefaultLocale" })
	private void initialiseTabHost() {

		try {

			// runOnUiThread(new Runnable() {
			//
			// @Override
			// public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < tabs.size(); i++) {
				String name = tabs.get(i);
//						tabs.get(i).substring(0, 1).toUpperCase()+ tabs.get(i).substring(1).toLowerCase();

				LayoutInflater inflater = null;
				inflater = (LayoutInflater) getApplicationContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.actionbar_custom_row,
			 			null);
			 	/*LinearLayout title_layout1= ((LinearLayout) view
						.findViewById(R.id.title_layout1));
				title_layout1.setBackgroundColor(Login.bg_color);*/
				final TextView title = ((TextView) view
						.findViewById(R.id.title));
				final TextView tv_bg = ((TextView) view
						.findViewById(R.id.tv_bg));
				title.setTypeface(Constants.ProximaNova_Regular);

				// title.setTypeface(Constants
				// .getTf_lato_regular(PreviousCartItemsActivity.this));
				//
				title.setText(name);
				title.setTag(name);
				tab_bar.addView(view);
				final int j = i;

				System.out.println("tab_position" + tab_position);
				if (tab_position == i) {
					tv_bg.setVisibility(View.VISIBLE);

				} else {
					tv_bg.setVisibility(View.GONE);

				}
				setToslist(0);
				title.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								// Toast.makeText(getApplicationContext(), ""+j,
								// Toast.LENGTH_LONG).show();
								tab_position = j;

								for (int k = 0; k < tab_position; k++) {

									LinearLayout viewGroup = (LinearLayout) tab_bar
											.getChildAt(k);

									size_action = size_action
											+ viewGroup.getWidth();

								}

								centerTabItem(tab_position, size_action,
										title.getWidth());
								size_action = 0;
								tab_bar.removeAllViews(); 
								initialiseTabHost();
								setToslist(tab_position);
								// new GetGroupChildItems()
								// .execute(tab_position);
							}

						}); 
					}
				});

			}
			setToslist(0);
			// }
			// });

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void setToslist(int tab_position) {
		if (tab_position == 0) { 
			change_pwd_rel.setVisibility(View.GONE);
			account_list.setVisibility(View.VISIBLE);
			account_list1.setVisibility(View.GONE);
			title_linear.setVisibility(View.VISIBLE);
			title_linear2.setVisibility(View.GONE);
//			if (util.IsNetConnected(MyAccount_History.this)) {

//			if (account_history.size() > 0) {
				adapter = new Account_history_ListAdapter(MyAccount_History.this,
						R.layout.my_account_row, account_history);

				adapter.notifyDataSetChanged();
				account_list.setAdapter(adapter);
//			}
//			else
//			{
//				new Account_HistoryTask().execute(); 
//			}
//			}
//			else
//			{
//				util.showAlertNoInternetService(MyAccount_History.this);
//			}
		}
		if (tab_position == 1) {
			change_pwd_rel.setVisibility(View.GONE);
			account_list1.setVisibility(View.VISIBLE);
			account_list.setVisibility(View.GONE);
			title_linear2.setVisibility(View.VISIBLE);
			title_linear.setVisibility(View.GONE);
			
			if (util.IsNetConnected(MyAccount_History.this)) {

				if (order_status.size() > 0) {
					adapter1 = new Account_history_ListAdapter1(MyAccount_History.this,
							R.layout.tracking, order_status);
					adapter1.notifyDataSetChanged();
					account_list1.setAdapter(adapter1);
				} else {
					new OrderStatusTask().execute();
				}
			
				
				

			} else {
				util.showAlertNoInternetService(MyAccount_History.this);
			}
			
		}
		if (tab_position == 2) { 
			account_list1.setVisibility(View.GONE);
			account_list.setVisibility(View.GONE);
			change_pwd_rel.setVisibility(View.VISIBLE);
			title_linear.setVisibility(View.GONE);
			title_linear2.setVisibility(View.GONE);
			
			
			

		}
	}

	@SuppressWarnings("deprecation")
	public void centerTabItem(int position, int length, int length1) {

		screenWidth = MyAccount_History.this.getWindowManager()
				.getDefaultDisplay().getWidth();

		final int leftX = length;
		int newX = 0;

		newX = leftX + (length1 / 2) - (screenWidth / 2);

		System.out.println("values " + length + ":" + length1 + ":"
				+ screenWidth + ":" + newX);
		if (newX < 0) {
			newX = 0;
		}
		hs.scrollTo(newX, 0);

	}

	public class Account_history_ListAdapter extends ArrayAdapter<ItemsObj> {

		private Activity activity;
		private List<ItemsObj> items;
		private ItemsObj objBean;
		@SuppressWarnings("unused")
		private int row;
		Bitmap bimg;
		ViewHolder holder;
		String strQty = "0";

		/*
		 * AQuery androidAQuery = new AQuery(getContext());
		 * 
		 * Utility utils = new Utility(getContext());
		 */

		public Account_history_ListAdapter(Activity act, int resource,
				List<ItemsObj> arrayList) {
			super(act, resource, arrayList);
			this.activity = act;
			this.row = resource;
			this.items = arrayList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
		}

		@Override
		public ItemsObj getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public class ViewHolder {
			TextView dat_txt, dat_txt1, contant_txt, order_txt1, points_txt1,
					ordrer_type_txt1,comments_txt;
			ImageView iv;
			RelativeLayout ray_top;
		}

		@SuppressWarnings("deprecation")
		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			LayoutInflater inflator = activity.getLayoutInflater();
			objBean = items.get(position);
			if (convertView == null) {
				holder = new ViewHolder();

				// if (Constants.selectLanguage.equalsIgnoreCase("3")) {
				convertView = inflator.inflate(R.layout.my_account_row, null);
				// } else {
				// convertView = inflator.inflate(R.layout.item_layout, null);
				// }
				holder.ray_top= (RelativeLayout) convertView
						.findViewById(R.id.ray_top);
			 	
				holder.ray_top.setBackgroundColor(Login.bg_color);
				
				holder.dat_txt = (TextView) convertView
						.findViewById(R.id.date_txt);
				holder.dat_txt1 = (TextView) convertView
						.findViewById(R.id.date_txt1);
				holder.contant_txt = (TextView) convertView
						.findViewById(R.id.contant_txt);
				holder.order_txt1 = (TextView) convertView
  					.findViewById(R.id.order_no_txt1);
				holder.points_txt1 = (TextView) convertView
						.findViewById(R.id.points_txt1);
				holder.ordrer_type_txt1 = (TextView) convertView
						.findViewById(R.id.order_type__txt1);
				
				holder.comments_txt = (TextView) convertView
						.findViewById(R.id.comments_txt);
				
				holder.dat_txt.setTypeface(Constants.ProximaNova_Regular);
				holder.dat_txt1.setTypeface(Constants.ProximaNova_Regular);
				holder.contant_txt.setTypeface(Constants.ProximaNova_Regular);
				holder.order_txt1.setTypeface(Constants.ProximaNova_Regular);
				holder.points_txt1.setTypeface(Constants.ProximaNova_Regular);
				holder.comments_txt.setTypeface(Constants.ProximaNova_Regular);
				holder.ordrer_type_txt1.setTypeface(Constants.ProximaNova_Regular); 

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			
		 	  
			  holder.contant_txt.setText(objBean.getMy_acc_product_name().replace("\n", "").replace("null", ""));
//			  String dat=objBean.getMy_acc_date();
//			  temp = dat.split(delimiter);
//			  holder.dat_txt.setText(temp[0].substring(0, 4).replace("null", ""));
//			  holder.dat_txt1.setText(temp[1].substring(0, 3).replace("null", ""));
			  if(objBean.getMy_acc_date().length()>0)
			  {
				  try{
					  Date d=new Date(objBean.getMy_acc_date());
					  holder.dat_txt.setText(""+d.getDate());
					  holder.dat_txt1.setText(""+(String)android.text.format.DateFormat.format("MMM", d));
				  }catch(Exception e){
					  
				  }
				
			  }
			  holder.comments_txt.setText(objBean.getMy_acc_comments().replace("null", "")); 
			  if(objBean.getMy_acc_typ().replace("null", "").contains("order"))
				{
					holder.points_txt1.setText("-"+objBean.getMy_acc_points().replace("null", ""));
				}
				else
				{
					holder.points_txt1.setText(objBean.getMy_acc_points().replace("null", ""));
				}
			  holder.ordrer_type_txt1.setText(objBean.getMy_acc_typ().replace("null", ""));
			  holder.order_txt1.setText(objBean.getMy_acc_OrderNum().replace("null", ""));
			
			return convertView;
		}

	}

	public class Account_history_ListAdapter1 extends ArrayAdapter<ItemsObj> {

		private Activity activity;
		private List<ItemsObj> items;
		private ItemsObj objBeans;
		@SuppressWarnings("unused")
		private int row;
		Bitmap bimg;
		ViewHolder holder;
		String strQty = "0";
 
		/*
		 * AQuery androidAQuery = new AQuery(getContext());
		 * 
		 * Utility utils = new Utility(getContext());
		 */

		public Account_history_ListAdapter1(Activity act, int resource,
				List<ItemsObj> arrayList) {
			super(act, resource, arrayList);
			this.activity = act;
			this.row = resource;
			this.items = arrayList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
		}

		@Override
		public ItemsObj getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public class ViewHolder {
			TextView dat_txt, dat_txt1, contant_txt, order_txt1, points_txt1,
					ordrer_type_txt1, helt_txt1, tracking_no, tracking_no1,
					helt_txt;
			ImageView iv; 
			RelativeLayout ray_top;
		}
 
		@SuppressWarnings("deprecation")
		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final ViewHolder holder;
			LayoutInflater inflator = activity.getLayoutInflater();
			objBeans = items.get(position);
			if (convertView == null) {
				holder = new ViewHolder();

				// if (Constants.selectLanguage.equalsIgnoreCase("3")) {
				convertView = inflator.inflate(R.layout.tracking,
						null);
				// } else {
				// convertView = inflator.inflate(R.layout.item_layout, null);
				// }
				 
				holder.ray_top = (RelativeLayout) convertView
						.findViewById(R.id.ray_top);
				
				holder.ray_top.setBackgroundColor(Login.bg_color);

				holder.dat_txt = (TextView) convertView
						.findViewById(R.id.date_txt);
				holder.dat_txt1 = (TextView) convertView
						.findViewById(R.id.date_txt1);
				holder.contant_txt = (TextView) convertView
						.findViewById(R.id.contant_txt);
				holder.order_txt1 = (TextView) convertView
						.findViewById(R.id.order_no_txt1);
				holder.points_txt1 = (TextView) convertView
						.findViewById(R.id.points_txt1);
				holder.ordrer_type_txt1 = (TextView) convertView
						.findViewById(R.id.order_type__txt1);

				holder.tracking_no = (TextView) convertView
						.findViewById(R.id.tracking_no);

				holder.tracking_no1 = (TextView) convertView
						.findViewById(R.id.tracking_no1);

				holder.helt_txt = (TextView) convertView
						.findViewById(R.id.helt_txt);

				holder.helt_txt1 = (TextView) convertView
						.findViewById(R.id.helt_txt1);
 
				holder.dat_txt.setTypeface(Constants.ProximaNova_Regular);
				holder.dat_txt1.setTypeface(Constants.ProximaNova_Regular);
				holder.contant_txt.setTypeface(Constants.ProximaNova_Regular);
				holder.order_txt1.setTypeface(Constants.ProximaNova_Regular);
				holder.points_txt1.setTypeface(Constants.ProximaNova_Regular);
				holder.ordrer_type_txt1
						.setTypeface(Constants.ProximaNova_Regular);
				holder.helt_txt1.setTypeface(Constants.ProximaNova_Regular);

				holder.tracking_no.setTypeface(Constants.ProximaNova_Regular);
				holder.tracking_no1.setTypeface(Constants.ProximaNova_Regular);
				holder.helt_txt.setTypeface(Constants.ProximaNova_Regular);
			

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			
			 
			@SuppressWarnings("unused")
			String[] temp;
			 
//			   delimiter 
			 /* String delimiter = ",";
//			   given string will be split by the argument delimiter provided. 
			  String str3 = objBeans.getDate_str1().replace("[", "").replace("]", "").replace("\"", "");
			    
			  temp = str3.split(delimiter);  
			    holder.dat_txt.setText(temp[6].substring(0, 4).replace("null", ""));
				holder.dat_txt1.setText(temp[5].substring(0, 3).replace("null", ""));
//				holder.contant_txt.setText(temp[3].substring(0, 9).replace("null", "").replace("\n", "")+"...");
				 holder.ordrer_type_txt1.setText(temp[4].replace("null", ""));
				holder.order_txt1.setText(temp[1].replace("null", "")); 
				holder.points_txt1.setText(temp[2].replace("null", "")); 
				holder.tracking_no1.setText(temp[11].replace("null", "")); 
//				holder.ordrer_type_txt1.setText(temp[11].replace("null", "")); 
*/
			 if(objBeans.getMy_acc_date1().length()>0)
			  {
				  try{
					  Date dat=new Date(objBeans.getMy_acc_date1());
					  holder.dat_txt.setText(""+dat.getDate());
  					  holder.dat_txt1.setText(""+(String)android.text.format.DateFormat.format("MMM", dat));
				  }catch(Exception e){
					   
				  }
			  } 
			 
			 holder.contant_txt.setText(objBeans.getMy_acc_product_name1().replace("null", "").replace("\n", ""));
			 holder.order_txt1.setText(objBeans.getMy_acc_OrderNum1().replace("null", "").replace("\n", ""));
			 holder.points_txt1.setText(objBeans.getMy_acc_Qty().replace("null", ""));
			 holder.ordrer_type_txt1.setText(objBeans.getMy_acc_product_number().replace("null", ""));
			 holder.tracking_no1.setText(objBeans.getMy_acc_tracking_number().replace("null", "")); 
			 
			holder.helt_txt1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					str_order_id=holder.order_txt1.getText().toString();
					startActivity(new Intent(MyAccount_History.this,
							FeedBackActivity.class));
				}
			});

			return convertView;
		}

	}
	
	
	private class Account_HistoryTask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(MyAccount_History.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
 
		@Override
		protected String doInBackground(String... params) {
System.out.println("fnGetAccountHistory"+Constants.MAIN_HOST
					+ "method=fnGetAccountHistory&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid="+Constants.User_Id);
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetAccountHistory&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid="+Constants.User_Id ); 

		/*	AuthToken
			userid
			Sortby (optional)
			Sortorder (optional)

		 					*/
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					

					if (null == result || result.length() == 0) {

					} else {
						account_history.clear();
						JSONObject jObject = new JSONObject(result);
//						JSONObject jObject1 =jObject.getJSONObject("DATA");
						JSONArray	wish_list_jsonArrays = jObject.getJSONArray("DATA");
						
						
						if(wish_list_jsonArrays.length()>0)
						{ 
				 			for (int i = 0; i <wish_list_jsonArrays.length(); i++) {
				 				JSONObject jObject11 = wish_list_jsonArrays.getJSONObject(i);
				 				
//								String srt =wish_list_jsonArrays.getString(i);
//								System.out.println(""+srt);
//								ItemsObj objItem = new ItemsObj();
//								account_history.add(objItem);
//								
								
//								for (int j = 0; j < 10; j++) {
									ItemsObj objItem = new ItemsObj();
//									objItem.setDate_str(srt);
									/*objItem.setDate_str1("JULY");
									objItem.setDec_str("Beams Seat Belt Hardware Mounting Kit for 2 Plint Non-Retractable Seat Belts");
									objItem.setOrder_no_str("97DG");
									objItem.setOrder_type_str("10.00");
									objItem.setPoints_str("Tax");*/
									
									if(jObject11.has("DATE")){
										objItem.setMy_acc_date(""+jObject11.getString("DATE"));
										}
										
										if(jObject11.has("TYPE")){
											objItem.setMy_acc_typ(""+jObject11.getString("TYPE")); 
											}
										
										if(jObject11.has("DOLLARS")){
											
											if(jObject11.getString("DOLLARS").length()>0)
											{
											double d = Double.parseDouble(""+jObject11.getString("DOLLARS"));
											double dbl=Math.ceil(d);
											objItem.setMy_acc_points(""+dbl);  
											}
											else
											{
												objItem.setMy_acc_points("0.0");  
											}
											}
										
										if(jObject11.has("PRODNAME")){
											objItem.setMy_acc_product_name(""+jObject11.getString("PRODNAME"));
											}
										 
										if(jObject11.has("COMMENTS")){
											objItem.setMy_acc_comments(""+jObject11.getString("COMMENTS"));
											}
										
										if(jObject11.has("ORDER_NUMBER")){
											objItem.setMy_acc_OrderNum(""+jObject11.getString("ORDER_NUMBER")); 
											}

									account_history.add(objItem);
//								}
							}
				 			
				 			account_list.setVisibility(View.VISIBLE);
				 			adapter = new Account_history_ListAdapter(MyAccount_History.this,
									R.layout.my_account_row, account_history);

							adapter.notifyDataSetChanged();
							account_list.setAdapter(adapter);
//							setToslist(0);
							
				 	
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
	
	
	private class OrderStatusTask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(MyAccount_History.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetOrderStatus&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid="+Constants.User_Id ); 

		/*	AuthToken
			userid
			all (optional)
		 					*/
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					

					if (null == result || result.length() == 0) {

					} else {
						order_status.clear();
						JSONObject jObject = new JSONObject(result);
//						JSONObject jObject1 =jObject.getJSONObject("DATA");
						JSONArray	wish_list_jsonArrays = jObject.getJSONArray("DATA");
						
						if(wish_list_jsonArrays.length()>0)
						{ 
				 			for (int i = 0; i <wish_list_jsonArrays.length(); i++) {
				 				/*for (int i = 0; i <ProductList_jsonArray.length(); i++) {
									try {
										JSONObject jObject11 = ProductList_jsonArray.getJSONObject(i);
									}*/
				 				JSONObject jObject11 = wish_list_jsonArrays.getJSONObject(i);
				 				 
//								String srt =wish_list_jsonArrays.getString(i);
								
									ItemsObj objItem = new ItemsObj();
//									objItem.setDate_str1(srt);
									
									if(jObject11.has("ORDERDATE")){
									objItem.setMy_acc_date1(""+jObject11.getString("ORDERDATE"));
									}
									
									if(jObject11.has("QUANTITY")){
										objItem.setMy_acc_Qty(""+jObject11.getString("QUANTITY"));  
										}
									
									if(jObject11.has("SKU")){
										objItem.setMy_acc_product_number(""+jObject11.getString("SKU"));  
										}
									
									if(jObject11.has("PRODNAME")){
										objItem.setMy_acc_product_name1(""+jObject11.getString("PRODNAME"));
										}
									
									if(jObject11.has("ORDERNUMBER")){
										objItem.setMy_acc_OrderNum1(""+jObject11.getString("ORDERNUMBER")); 
										}
									if(jObject11.has("TRACKINGNUMBER")){
										objItem.setMy_acc_tracking_number(""+jObject11.getString("TRACKINGNUMBER")); 
										}
								 
									order_status.add(objItem);
//								}
							}
							 
				 			
				 			adapter1 = new Account_history_ListAdapter1(MyAccount_History.this,
									R.layout.tracking, order_status);
							adapter1.notifyDataSetChanged();
							account_list1.setAdapter(adapter1);
				 	
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
	
	
	
	private class ChangePasswordTask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(MyAccount_History.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnUpdatePassword&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid="+Constants.User_Id+ "&clientid="+Constants.clientID+ "&Password="+edt_new_pwd.getText().toString()); 

		/*	AuthToken
			userid
			clientid
			Password
		 					*/
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					

					if (null == result || result.length() == 0) {

					} else {
						
						if (result.contains("true")) {
							util.inputValidation("Password has been changed successfully");
							edt_new_pwd.setText("");
						}
						else{
							
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
	 	// TODO Auto-generated method stub 
		super.onBackPressed(); 
		startActivity(new Intent(MyAccount_History.this, DashBorad.class));
		MyAccount_History.this.finish();
	}
	
}
