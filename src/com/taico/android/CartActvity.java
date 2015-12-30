package com.taico.android;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
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
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.util.Utility;
 
@SuppressLint("InflateParams")
public class CartActvity extends Activity {
	// LinearLayout shops_layout,my_account_layout;
	TextView continue_txt, continue_txtS, total_points_txt,total_points_txt_Tax, tv_txt, engTxt, txt_nam1, tv_select, txt_nam2, tv_select2,
	txt_wish_cnt1, txt_crt_cnt1, tv_txt23;
	ListView carts_list;
	Integer[] array;
	ArrayList<String> tax = new ArrayList<String>();
	ArrayList<String> tax1 = new ArrayList<String>();
	ArrayList<Double> subtot = new ArrayList<Double>();
	CustomCartAdapter	cAdapter;
	Dialog alertDialog;
	double totalprice = 0;       
	int subTotal = 0;
	// public static String[] cart_unit_price = { "36", "45"};
	public static String TAG_TOTAL_PRICE = "";
	public static String TAG_UNIT_PRICE = "";
	public static String TAG_QTY_PRICE = "";

	public HashMap<String, String> itemWithQuantity = new HashMap<String, String>();
	public HashMap<String, String> itemWithPrice = new HashMap<String, String>();
	HashMap<String, String> product = new HashMap<String, String>();
	RelativeLayout favLayout, cartLayout, ray_header, rrr,rrr333,tv_r_lay,logo_rrr;
	ImageView menu,imageView1;
	Button btn_home, cart_clear, menus;
	Boolean flg = false, men_flg = false;
	LinearLayout liner_lay, menlayout_cart,liner_lay1,lll_show_logo;
	ArrayList<String> cart_unit_price;
	ArrayList<String> cart_title;
	ArrayList<String> cart_id;
	ArrayList<String> cart_img;
	ArrayList<String> cart_default_qty;
	ArrayList<String> cart_wholesale_price;
	Utility util;
	String productChildID = "", str_qty = "1", is_gift = "0",
			istax_click = "0", iscompany_click = "0";
	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart);

		util = new Utility(CartActvity.this);

		cart_unit_price = new ArrayList<String>();
		cart_title = new ArrayList<String>();
		cart_id = new ArrayList<String>();
		cart_img = new ArrayList<String>();
		cart_default_qty = new ArrayList<String>();
		cart_wholesale_price = new ArrayList<String>();

		tax.clear();
		tax1.clear();
		tax.add("Yes");
		tax.add("No");

		tax1.add("Home");
		tax1.add("Business");

		txt_wish_cnt1 = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt1 = (TextView) findViewById(R.id.txt_crt_cnt1);
		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount);
		txt_wish_cnt1.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt1.setTypeface(Constants.ProximaNova_Regular);

		btn_home = (Button) findViewById(R.id.btn_home);
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		menus = (Button) findViewById(R.id.menus);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		rrr333 = (RelativeLayout) findViewById(R.id.rrr333);
		menu = (ImageView) findViewById(R.id.menu);
		
//		  final Animation   animationtop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
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
			 animation = AnimationUtils.loadAnimation(CartActvity.this, R.anim.slide_in_top);
			 animation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub
					imageView1.setVisibility(View.INVISIBLE);  
			    	  logo_rrr.setVisibility(View.INVISIBLE);  
			  //  	  btn_home.setVisibility(View.INVISIBLE); 
			    
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
	    	  
	    	  lll_show_logo.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 imageView1.startAnimation(animation); 
				    	  logo_rrr.startAnimation(animation);
				    	  imageView1.setVisibility(View.INVISIBLE);  
				    	  logo_rrr.setVisibility(View.INVISIBLE);   
				    	  menu.setVisibility(View.VISIBLE);
				  		btn_home.setVisibility(View.VISIBLE); 
				  		favLayout.setVisibility(View.VISIBLE);
				  		cartLayout.setVisibility(View.VISIBLE); 
				  		lll_show_logo.setVisibility(View.VISIBLE); 
					}
				});
		
		// rrr=(RelativeLayout)findViewById(R.id.rrr);
				ray_header = (RelativeLayout) findViewById(R.id.ray_header);
				ray_header.setBackgroundColor(Login.bg_color);

				carts_list = (ListView) findViewById(R.id.carts_list);
				carts_list.setBackgroundColor(Login.bg_color);

				continue_txtS = (TextView) findViewById(R.id.continue_txtS);
				// total_points_txt= (TextView)findViewById(R.id.total_points_txt);
				// total_points_txt_Tax=
				// (TextView)findViewById(R.id.total_points_txt_Tax);

				tv_txt = (TextView) findViewById(R.id.tv_txt);
				tv_txt23 = (TextView) findViewById(R.id.tv_txt23);
				// engTxt= (TextView)findViewById(R.id.engTxt);
				// txt_nam1= (TextView)findViewById(R.id.txt_nam1);
				// txt_nam2= (TextView)findViewById(R.id.txt_nam2);
				// tv_select= (TextView)findViewById(R.id.tv_select);
				// tv_select2= (TextView)findViewById(R.id.tv_select2);
				cart_clear = (Button) findViewById(R.id.cart_clear);
		
		tv_r_lay = (RelativeLayout) findViewById(R.id.tv_r_lay);
		
		/** Show product points in application*/
		
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
		tv_r_lay.setVisibility(View.VISIBLE);
			tv_txt.setVisibility(View.GONE);
			tv_txt23.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			tv_r_lay.setVisibility(View.VISIBLE);
			tv_txt.setVisibility(View.VISIBLE);
			tv_txt23.setVisibility(View.VISIBLE);
		}
		else
		{
			tv_r_lay.setVisibility(View.VISIBLE);
			tv_txt.setVisibility(View.GONE);
			tv_txt23.setVisibility(View.GONE);
		}
		
		/** Change the app background color and font color in application*/
		
		if(Constants.GETBUTTONCOLOR.length()>0&&Constants.GETBUTTONFONTCOLOR.length()>0)
		{
			GradientDrawable gd = (GradientDrawable) txt_wish_cnt1
					.getBackground().getCurrent();
			gd.setColor(Color.parseColor("#" + Constants.GETBUTTONCOLOR));
			gd.setStroke(0, Color.parseColor("#" + Constants.GETBUTTONCOLOR),
					0, 0);

			GradientDrawable gd1 = (GradientDrawable) txt_crt_cnt1
					.getBackground().getCurrent();
			gd1.setColor(Color.parseColor("#" + Constants.GETBUTTONCOLOR));
			gd1.setStroke(0, Color.parseColor("#" + Constants.GETBUTTONCOLOR),
					0, 0);

			txt_wish_cnt1.setTextColor(Color.parseColor("#"
					+ Constants.GETBUTTONFONTCOLOR));
			txt_crt_cnt1.setTextColor(Color.parseColor("#"
					+ Constants.GETBUTTONFONTCOLOR));
		}
		else
		{ 
			
			txt_wish_cnt1.setTextColor(Color.WHITE);
			txt_crt_cnt1.setTextColor(Color.WHITE);
			txt_wish_cnt1.setBackgroundResource(R.drawable.red_circle);
			txt_crt_cnt1.setBackgroundResource(R.drawable.red_circle); 
 			
		}

		

		if (util.IsNetConnected(CartActvity.this)) {
			new Getuserpoints().execute();
		} else {
			util.showAlertNoInternetService(CartActvity.this);
		}

		continue_txtS.setTypeface(Constants.ProximaNova_Regular);
		tv_txt.setTypeface(Constants.ProximaNova_Regular);
		tv_txt23.setTypeface(Constants.ProximaNova_Regular);
		cart_clear.setTypeface(Constants.ProximaNova_Regular);

		cart_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (util.IsNetConnected(CartActvity.this)) {

					AlertDialog.Builder altDialog = new AlertDialog.Builder(
							CartActvity.this);
					altDialog
							.setMessage("Would you like to delete all items from cart?");

					altDialog.setNeutralButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									new DeleteCartsTask().execute();

								}
							});
					altDialog.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					altDialog.show();
					

				} else {
					util.showAlertNoInternetService(CartActvity.this);
				}

			}
		});

		continue_txtS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				final Dialog dialog = new Dialog(CartActvity.this,
						android.R.style.Theme_Translucent_NoTitleBar);
				// android.R.style.Theme_Translucent_NoTitleBar);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.cart_calculation_layout);
				engTxt = (TextView) dialog.findViewById(R.id.engTxt);
				txt_nam1 = (TextView) dialog.findViewById(R.id.txt_nam1);
				txt_nam2 = (TextView) dialog.findViewById(R.id.txt_nam2);
				tv_select = (TextView) dialog.findViewById(R.id.tv_select);
				tv_select2 = (TextView) dialog.findViewById(R.id.tv_select2);
				total_points_txt = (TextView) dialog
						.findViewById(R.id.total_points_txt);
				total_points_txt_Tax = (TextView) dialog
						.findViewById(R.id.total_points_txt_Tax);
				continue_txt = (TextView) dialog
						.findViewById(R.id.continue_txt);
				Button btn_bak = (Button) dialog.findViewById(R.id.btn_bak); 
				btn_bak.setTypeface(Constants.ProximaNova_Regular);
				btn_bak.setText("Cart");
				rrr = (RelativeLayout) dialog.findViewById(R.id.rrr);
				liner_lay = (LinearLayout) dialog.findViewById(R.id.liner_lay);
				liner_lay1 = (LinearLayout) dialog
						.findViewById(R.id.liner_lay1);
				liner_lay.setBackgroundColor(Login.bg_color);

				liner_lay1.setBackgroundColor(Login.bg_color);

				engTxt.setTypeface(Constants.ProximaNova_Regular);
				txt_nam1.setTypeface(Constants.ProximaNova_Regular);
				txt_nam2.setTypeface(Constants.ProximaNova_Regular);
				tv_select.setTypeface(Constants.ProximaNova_Regular);
				tv_select2.setTypeface(Constants.ProximaNova_Regular);
				total_points_txt.setTypeface(Constants.ProximaNova_Regular);
				total_points_txt_Tax.setTypeface(Constants.ProximaNova_Regular);
				continue_txt.setTypeface(Constants.ProximaNova_Regular);

				total_points_txt.setText("Total Points "
						+ Constants.str_points_incart);
				total_points_txt_Tax.setText("Taxes 0");
				total_points_txt.setTypeface(Constants.ProximaNova_Regular);
	 			total_points_txt_Tax.setTypeface(Constants.ProximaNova_Regular);

	 			/** Show or not drop downs of tax and office/home in application*/
	 			
				if (Constants.CLIENTALLOWTAX.equals("0")) {
					liner_lay1.setVisibility(View.GONE);
					Constants.isTaxed = "";
				} else if (Constants.CLIENTALLOWTAX.equals("1")) {
					liner_lay1.setVisibility(View.VISIBLE);
				}

				btn_bak.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

				continue_txt.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Constants.isGift = is_gift;
						if (Constants.CLIENTALLOWTAX.equals("0")) {
							Constants.isTaxed = "0";
							Constants.isCompanyReq = "0";
							startActivity(new Intent(CartActvity.this,
									ShippingAddress.class));

						} else if (Constants.CLIENTALLOWTAX.equals("1")) {
							if (istax_click.equalsIgnoreCase("0")
									|| iscompany_click.equalsIgnoreCase("0")) {
								util.inputValidation("Please select your preference");
	 						} else {

								// Constants.isTaxed=istax_click;
								// Constants.isCompanyReq=iscompany_click;
								//
								startActivity(new Intent(CartActvity.this,
										ShippingAddress.class));

							}
						}
					}
				});

				engTxt.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (flg) {
							flg = false;
							engTxt.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.check_unselect, 0, 0, 0);
							is_gift = "0";
						} else {
							flg = true;
							engTxt.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.check_selected, 0, 0, 0);

							is_gift = "1";
						}

					}

				});

				tv_select.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						LayoutInflater inflater = getLayoutInflater();
						View convertView = (View) inflater.inflate(
								R.layout.dialog, null);

						// displaying alert dialog with list of
						// numbers
						final Dialog alertDialog1 = new Dialog(CartActvity.this);
						alertDialog1.setContentView(convertView);

						alertDialog1.setTitle(getResources().getString(
								R.string.preference));

						ListView lv = (ListView) convertView
								.findViewById(R.id.listView);

						ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
								CartActvity.this,
	 							android.R.layout.simple_list_item_1, tax);
						lv.setAdapter(adapter1);
						lv.setOnItemClickListener(new OnItemClickListener() {

							// TODO Auto-generated method stub

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								System.out.println("cart_wholesale_price"
										+ cart_wholesale_price);
								System.out.println("cart_default_qty"
										+ cart_default_qty);

								if (position == 0) {
									Constants.isTaxed = "1";
									istax_click = "1";

									double val = 0;

									/** Tax calculation Formula */

									for (int i = 0; i < cart_wholesale_price
											.size(); i++) {
										
//										
//										double d = Double.parseDouble(""+jObject11.getString("DOLLARS"));
//										double dbl=Math.ceil(d);
										
										val = Double
												.parseDouble(cart_wholesale_price
														.get(i))
												* Double.parseDouble(cart_default_qty
														.get(i)) + val;
									}

									double val3 = Double
											.parseDouble(Constants.clienttaxpercent) / 100;
									double val1 = val * val3;
									double val2 = val1
											* Double.parseDouble(Constants.clientRatio);

									double fin_val = Math.ceil(val2)
											+ Double.parseDouble(""
													+ Constants.str_points_incart);
									int b = (int) fin_val;
									int c = (int) val2;
									total_points_txt.setText("Total Points: "
											+ b);
									total_points_txt_Tax.setText("Taxes: " + c);
 
								} else {
									Constants.isTaxed = "0";
									istax_click = "2";
									// tv_txt.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart&nbsp&nbsp&nbsp</font><font color='#EA2828'><b><strong>"+Constants.str_points_incart+"</strong></b></font></body></html>"));
									total_points_txt_Tax.setText("Taxes: 0");
									total_points_txt.setText("Total Points: "
											+ Constants.str_points_incart);
								}

								tv_select.setText(tax.get(position));

								alertDialog1.dismiss();
							}
						});

						alertDialog1.show();
					}
				});

				tv_select2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						LayoutInflater inflater = getLayoutInflater();
						View convertView = (View) inflater.inflate(
								R.layout.dialog, null);

						// displaying alert dialog with list of
						// numbers
						final Dialog alertDialog2 = new Dialog(CartActvity.this);
						alertDialog2.setContentView(convertView);

						alertDialog2.setTitle(getResources().getString(
								R.string.preference));

						ListView lv = (ListView) convertView
								.findViewById(R.id.listView);

						ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
								CartActvity.this,
								android.R.layout.simple_list_item_1, tax1);
						lv.setAdapter(adapter1);
						lv.setOnItemClickListener(new OnItemClickListener() {

							// TODO Auto-generated method stub

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int position, long arg3) {

								tv_select2.setText(tax1.get(position));

								if (position == 0) {
									iscompany_click = "1";
									Constants.isCompanyReq = "0";
								} else {
									iscompany_click = "2";
									Constants.isCompanyReq = "1";
								}

								alertDialog2.dismiss();
							}
						});

						alertDialog2.show();
					}
				});

				dialog.show();

			}
		});
		btn_home.setText(R.string.cart);
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(CartActvity.this, DashBorad.class));
				CartActvity.this.finish();
			}
		});
		menlayout_cart = (LinearLayout) findViewById(R.id.menlayout_cart);
		menlayout_cart.setVisibility(View.GONE);
		menus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				menlayout_cart.removeAllViews();
				if (men_flg) {
					men_flg = false;
					menlayout_cart.setVisibility(View.GONE);

				} else {
					menlayout_cart.setVisibility(View.VISIBLE);
					men_flg = true;
					LayoutInflater inflater = null;
					inflater = (LayoutInflater) getApplicationContext()
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View mLinearView = inflater.inflate(R.layout.menus_layout,
							null);
					TextView tv_menu1 = (TextView) mLinearView
							.findViewById(R.id.tv_menu1);
					TextView tv_menu2 = (TextView) mLinearView
							.findViewById(R.id.tv_menu2);
					tv_menu1.setTypeface(Constants.ProximaNova_Regular);
					tv_menu2.setTypeface(Constants.ProximaNova_Regular);
					 
					TextView tv_hom1 = (TextView)mLinearView.findViewById(R.id.tv_hom1);
					tv_hom1.setTypeface(Constants.ProximaNova_Regular);
					tv_hom1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(CartActvity.this, Home.class));
							CartActvity.this.finish();
						}
					});
					
					tv_menu1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(CartActvity.this,
									DashBorad.class));
							CartActvity.this.finish();
						}
					});

					tv_menu2.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(CartActvity.this,
									Login.class));
							CartActvity.this.finish();
						}
					});

					menlayout_cart.addView(mLinearView);
				}
			}
		});
		
		/** Show SHOWWISHLIST/SHOWADDTOCARTBUTTON  in application*/
		if (Constants.SHOWWISHLIST == 1) {
			favLayout.setVisibility(View.VISIBLE);
		} else if (Constants.SHOWWISHLIST == 0) {
			favLayout.setVisibility(View.GONE);
		}

		if (Constants.SHOWADDTOCARTBUTTON == 1) {
			cartLayout.setVisibility(View.VISIBLE);
		} else if (Constants.SHOWADDTOCARTBUTTON == 0) {
			cartLayout.setVisibility(View.GONE);
		}

		favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(CartActvity.this,
						WishListActivity.class));
				CartActvity.this.finish();

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		if (util.IsNetConnected(CartActvity.this)) {
			subtot.clear();
			new CartsListask().execute();
		} else {
			util.showAlertNoInternetService(CartActvity.this);
		}
		
		
		int a=Constants.str_points_left;
        if(a>0)
        {
            System.out.println(a+" is positive");
//            continue_txtS.setClickable(true);
            rrr333.setVisibility(View.VISIBLE);
        }
        else if(a<0)
        {
            System.out.println(a+" is negative");
//            continue_txtS.setClickable(false);
            rrr333.setVisibility(View.GONE);
            AlertDialog.Builder altDialog = new AlertDialog.Builder(
					CartActvity.this);
			altDialog
					.setMessage("You do not have enough points for this order.Please delete the quantity");

			altDialog.setNeutralButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dialog,
								int which) {

							dialog.dismiss();

						}
					});
			
			altDialog.show();
        }
        else
        {
            System.out.println(a+" is equal to zero");
            
//            continue_txtS.setClickable(false);
            rrr333.setVisibility(View.GONE);
            
          /*  AlertDialog.Builder altDialog = new AlertDialog.Builder(
					CartActvity.this);
			altDialog
					.setMessage("You do not have enough points for this order.Please delete the quantity");

			altDialog.setNeutralButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dialog,
								int which) {

							dialog.dismiss();

						}
					});
			
			altDialog.show();*/
        }
        
      

	}

	private class Getuserpoints extends AsyncTask<String, Void, String> {

		// ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			// myProgressDialog = new ProgressDialog(CartActvity.this);
			// myProgressDialog.setMessage("please wait ...");
			// myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetUserPoints&AuthToken="
					+ Constants.AUTH_TOKEN + "&Userid=" + Constants.User_Id);

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					// if (null != myProgressDialog &&
					// myProgressDialog.isShowing()) {
					// myProgressDialog.dismiss();
					// }

					if (null == result || result.length() == 0) {

					} else {

						JSONObject jObject = new JSONObject(result);
						JSONObject jsonDataobj = jObject.getJSONObject("DATA");

						if (jsonDataobj.has("points")) {
							Constants.str_points_incart = jsonDataobj
									.getInt("points");
						}
						if (jsonDataobj.has("pointsLeft")) {
							Constants.str_points_left = jsonDataobj
									.getInt("pointsLeft");
						}
						if (jsonDataobj.has("itemCount")) {
							Constants.itemCount = ""
									+ jsonDataobj.getInt("itemCount");
						}
						if (jsonDataobj.has("wishlistcount")) {
							Constants.wishlistcount = ""
									+ jsonDataobj.getInt("wishlistcount");
						}

						txt_wish_cnt1.setText(Constants.wishlistcount);
						txt_crt_cnt1.setText(Constants.itemCount);

						tv_txt.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_incart
										+ "</strong></b></font></body></html>"));
						tv_txt23.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_left
										+ "</strong></b></font></body></html>"));
						
							
							
					

					}

					// if (myProgressDialog.isShowing())
					// myProgressDialog.dismiss();

				} catch (Exception e) {
					e.printStackTrace();
				}

				// if (myProgressDialog.isShowing())
				// myProgressDialog.dismiss();

			} catch (Exception e) {
				// if (myProgressDialog.isShowing())
				// myProgressDialog.dismiss();
			}

		}
	}

	public void updateQtyTextView() {
		try {

			if (subtot.size() > 0) {
				double v_3 = 0;
				for (int i = 0; i < subtot.size(); i++) {
					v_3 = subtot.get(i) + v_3;
				}
				total_points_txt.setText("Total Points: " + v_3 + "/-");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class CustomCartAdapter extends BaseAdapter {
		// String[] text;
		ArrayList<String> text;
		Context context;
		LayoutInflater inflater = null;

		ArrayList<String> cart_unit_price1;
		ArrayList<String> cart_title1;
		ArrayList<String> cart_id1;
		ArrayList<String> cart_img1;
		ArrayList<String> cart_default_qty1;
		ArrayList<String> cart_wholesale_price1;

		public CustomCartAdapter(Context con, ArrayList<String> cart1,
				ArrayList<String> cart2, ArrayList<String> cart3,
				ArrayList<String> cart4, ArrayList<String> cart5,
				ArrayList<String> cart6) {
			// TODO Auto-generated constructor stub
			super();
			// cart_title,cart_id,cart_img,cart_default_qty,cart_unit_price
			this.cart_title1 = cart1;
			this.cart_id1 = cart2;
			this.cart_img1 = cart3;
			this.cart_default_qty1 = cart4;
			this.cart_unit_price1 = cart5;
			this.cart_wholesale_price1 = cart5;
			context = con;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
 
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cart_title1.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		// ////////////////////////////////
		/*
		 * @Override public String getItem(int position) { // TODO
		 * Auto-generated method stub return text.get(position); }
		 */

		/*
		 * @Override public int getViewTypeCount() {
		 * 
		 * return getCount(); }
		 * 
		 * @Override public int getItemViewType(int position) {
		 * 
		 * return position; }
		 */
		// ///////////////////////////////
		public class Holder {
			TextView tv_qty_price, tv_unit_price, tv_subtotal_price,
					tv_label_name, tv_unit_text, tv_qty_text, tv_subtotal_text,
					tv_close;
			ImageView iv_label;
			RelativeLayout rr_close;
			LinearLayout ll_unit,ll_qty,ll_subtotal;
		}

		@SuppressLint({ "ViewHolder", "InflateParams", "CutPasteId" })
		@Override
		public View getView(final int position, View rowView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final Holder holder;
			if (rowView == null) {
				// View rowView;
				rowView = inflater.inflate(R.layout.shopping_cart_row, null);
				holder = new Holder();
				holder.iv_label = (ImageView) rowView
						.findViewById(R.id.iv_label);

				holder.tv_unit_text = (TextView) rowView
						.findViewById(R.id.tv_unit_text);
				holder.tv_qty_text = (TextView) rowView
						.findViewById(R.id.tv_qty_text);
				holder.tv_subtotal_text = (TextView) rowView
						.findViewById(R.id.tv_subtotal_text);

				// tv_qty_price

				holder.tv_qty_price = (TextView) rowView
						.findViewById(R.id.tv_qty_price);
				holder.tv_unit_price = (TextView) rowView
						.findViewById(R.id.tv_unit_price);
				holder.tv_subtotal_price = (TextView) rowView
						.findViewById(R.id.tv_subtotal_price);
				holder.tv_label_name = (TextView) rowView
						.findViewById(R.id.tv_label_name);
				holder.rr_close = (RelativeLayout) rowView
						.findViewById(R.id.rr_close);
				holder.tv_close = (TextView) rowView
						.findViewById(R.id.tv_close);
				
				holder.ll_unit = (LinearLayout) rowView
						.findViewById(R.id.ll_unit);
				
				holder.ll_qty = (LinearLayout) rowView
						.findViewById(R.id.ll_qty);
				
				holder.ll_subtotal = (LinearLayout) rowView
						.findViewById(R.id.ll_subtotal);

				holder.tv_qty_price.setTypeface(Constants.ProximaNova_Regular);
				holder.tv_unit_price.setTypeface(Constants.ProximaNova_Regular);
				holder.tv_subtotal_price
						.setTypeface(Constants.ProximaNova_Regular);
				holder.tv_label_name.setTypeface(Constants.ProximaNova_Regular);

				holder.tv_unit_text.setTypeface(Constants.ProximaNova_Regular);
				holder.tv_qty_text.setTypeface(Constants.ProximaNova_Regular);
				holder.tv_subtotal_text
						.setTypeface(Constants.ProximaNova_Regular);

			} else {
				holder = (Holder) rowView.getTag();
			}

			try {

				// System.out.println("size of array" + itemWithQuantity + ":"
				// + itemWithQuantity.get("" + position));
				//

				holder.tv_label_name.setText(""
						+ cart_title1.get(position).replace("\n", ""));
				System.out.println("tit" + cart_title1.get(position));

				TAG_UNIT_PRICE = cart_unit_price1.get(position);
				System.out.println("uit" + cart_unit_price1.get(position));
				// TAG_UNIT_PRICE=cart_unit_price[position];

				holder.tv_unit_price.setText(TAG_UNIT_PRICE);
				holder.tv_qty_price.setText(""
						+ cart_default_qty1.get(position));
				System.out.println("qty" + cart_default_qty1.get(position));

				TAG_QTY_PRICE = cart_default_qty1.get(position); 
				holder.tv_qty_price.setText(TAG_QTY_PRICE);
				subTotal = Integer.parseInt(TAG_UNIT_PRICE)
						* Integer.parseInt(TAG_QTY_PRICE);

				holder.tv_subtotal_price.setText(String.valueOf(subTotal));

				// download and display image from url
				String url = "";
				if (cart_img1.get(position).contains("http")) {
					url = cart_img1.get(position);
				} else {
					url = Constants.prouducts_url
							+ cart_img1.get(position);
				}
         
				/*
				 * ImageLoader imageLoader = ImageLoader.getInstance();
				 * DisplayImageOptions options = new
				 * DisplayImageOptions.Builder()
				 * .cacheInMemory(true).cacheOnDisc(true)
				 * .resetViewBeforeLoading(true)
				 * .showImageForEmptyUri(R.drawable.bag3)
				 * .showImageOnFail(R.drawable.bag3)
				 * .showImageOnLoading(R.drawable.bag3).build();
				 * 
				 * imageLoader.displayImage( url.replace("\'", "%20") .trim(),
				 * holder.iv_label, options);
				 */
				
				/** Show product points in application*/
				if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
				{
					holder.ll_unit.setVisibility(View.INVISIBLE);
					holder.ll_subtotal.setVisibility(View.INVISIBLE);
				}
				else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
				{
					holder.ll_unit.setVisibility(View.VISIBLE);
					holder.ll_subtotal.setVisibility(View.VISIBLE);
				}
				else 
				{ 
					holder.ll_unit.setVisibility(View.INVISIBLE);
					holder.ll_subtotal.setVisibility(View.INVISIBLE);
				}

				Picasso.with(getApplicationContext())
						.load(url.replace("\'", "%20"))
						.placeholder(R.drawable.login_logo)
						.error(R.drawable.bag3).fit().into(holder.iv_label);

				holder.rr_close.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Autodfsf-generated method stub
						if (util.IsNetConnected(CartActvity.this)) {
							productChildID = cart_id1.get(position);
							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									CartActvity.this);
							altDialog
									.setMessage("Would you like to delete item from cart?");

							altDialog.setNeutralButton("Yes",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {

											 
											cart_title.remove(position);
											cart_id.remove(position);
											cart_img.remove(position);
											cart_default_qty.remove(position);
											cart_unit_price.remove(position);
											cart_wholesale_price.remove(position);
											
											new DeleteCartItem().execute();
											

										}
									});
							altDialog.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									});
							altDialog.show();
						} else {
							util.showAlertNoInternetService(CartActvity.this);
						}

					}
				});

			} catch (Exception e) {
				holder.tv_qty_price.setText("0");
				holder.tv_subtotal_price.setText("0");
				e.printStackTrace();
			}
			holder.tv_qty_price.setOnClickListener(new OnClickListener() {

				@SuppressLint("InflateParams")
				@Override
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// String start = (String) product.get(TAG_MINQTY);
					// String end = (String) product.get(TAG_MAXQTY);

					productChildID = cart_id1.get(position);
  
       				int start1 = 1;
					int end1 = 9;
					final int posi = position;

		 			System.out.println("size of array" + itemWithQuantity);

					int size = end1 - start1 + 2;
					array = new Integer[size];
					// array[0] = 1;
					for (int i = 0; i < size; i++) {
						array[i] = start1++;
					}

					LayoutInflater inflater = getLayoutInflater();
					View convertView = (View) inflater.inflate(R.layout.dialog,
							null);

					// displaying alert dialog with list of
					// numbers
					alertDialog = new Dialog(CartActvity.this);
					alertDialog.setContentView(convertView);

					alertDialog.setTitle(getResources().getString(
							R.string.selectQty));

					ListView lv = (ListView) convertView
							.findViewById(R.id.listView);

					ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(
							CartActvity.this,
							android.R.layout.simple_list_item_1, array);
					lv.setAdapter(adapter1);
					lv.setOnItemClickListener(new OnItemClickListener() {

						// TODO Auto-generated method stub

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							// TODO Auto-generated method stub
							holder.tv_qty_price.setText(Integer
									.toString(array[position]));
							str_qty = "" + array[position];
							try {

								if (array[position] == 0) {
									String val = itemWithQuantity
											.get("" + posi);
									System.out.println("position" + position
											+ val + array[position]);
									itemWithQuantity.remove("" + posi);
									itemWithPrice.remove("" + posi);
									// itemWithName.remove("" + posi);

									subTotal = 0;
									holder.tv_subtotal_price.setText(String
											.valueOf(subTotal));
								} else {
									itemWithQuantity.put("" + posi, ""
											+ array[position]);
									// itemWithId.put("" + posi, "" +
									// CatId[posi]);

									itemWithPrice.put("" + posi,
											cart_unit_price1.get(posi));
									// itemWithName.put("" + posi,
									// dishes[posi]);

									subTotal = Integer
											.parseInt(cart_unit_price1
													.get(posi))
											* array[position];
									System.out.println("priceeeee"
											+ cart_unit_price1.get(posi) + ""
											+ array[position]);
									holder.tv_subtotal_price.setText(String
											.valueOf(subTotal));
									//
								}
								if (util.IsNetConnected(CartActvity.this)) {
									subtot.clear();
									new UpdateCartsListask().execute();
								} else {
									util.showAlertNoInternetService(CartActvity.this);
								}
								// subtot.add(subTotal);
								// updateQtyTextView();

							} catch (Exception e) {
								e.printStackTrace();
							}

							alertDialog.dismiss();
						}
					});

					alertDialog.show();

				}
			});

			rowView.setTag(holder);

			return rowView;
		}

	}

	private class CartsListask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(CartActvity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetCartItems&AuthToken=" + Constants.AUTH_TOKEN
					+ "&userid=" + Constants.User_Id + "&Clientid="
					+ Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&clienttaxpercent="
					+ Constants.clienttaxpercent);

			/* 
			 * AuthToken userid Clientid Clientratio clienttaxpercent
			 */

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					cart_unit_price.clear();
					cart_title.clear();
					cart_id.clear();
					cart_img.clear();
					cart_default_qty.clear();
					cart_wholesale_price.clear();

					if (null == result || result.length() == 0) {

					} else {
						JSONObject jObject = new JSONObject(result);
						JSONArray carts_jsonArrays = jObject
								.getJSONArray("DATA");

						if (carts_jsonArrays.length() > 0) {
							// liner_lay.setVisibility(View.VISIBLE);
							// rrr.setVisibility(View.VISIBLE);
							carts_list.setVisibility(View.VISIBLE);
							@SuppressWarnings("unused")
							double intval = 0;
							for (int i = 0; i < carts_jsonArrays.length(); i++) {
								JSONObject jsonObject = carts_jsonArrays
										.getJSONObject(i);

								if (jsonObject.has("PRODUCTTITLE")) {
									cart_title.add(""
											+ jsonObject
													.getString("PRODUCTTITLE"));
								} else {
									cart_title.add("null");
								}
								if (jsonObject.has("PRODUCTID")) {
									cart_id.add(""
											+ jsonObject
													.getString("PRODUCTCHILDID"));
								} else {
									cart_id.add("0");
								}
								if (jsonObject.has("IMAGEURL")) {
									cart_img.add(""
											+ jsonObject.getString("IMAGEURL")
													.replace(" ", "%20"));
								} else {
									cart_img.add("0");
								}
								if (jsonObject.has("QUANTITY")) {
									cart_default_qty.add(""
											+ jsonObject.getString("QUANTITY"));
								} else {
									cart_default_qty.add("0");
								}
								if (jsonObject.has("USERUNITFINALCOST")) {
									cart_unit_price
											.add(""
													+ jsonObject
															.getInt(("USERUNITFINALCOST")));
								} else {
									cart_unit_price.add("0");
								}

								if (jsonObject.has("UNITWHOLESALECOST")) {
									cart_wholesale_price
											.add(""
													+ jsonObject
															.getInt("UNITWHOLESALECOST"));

								} else {
									cart_wholesale_price.add("0");
								}

								totalprice = Double.parseDouble(cart_unit_price
										.get(i))
										* Double.parseDouble(cart_default_qty
												.get(i)) + totalprice;
								// intval =
								// Double.parseDouble(cart_wholesale_price.get(i))
								// * Double.parseDouble(cart_default_qty.get(i))
								// +intval;
								// Math.ceil(intval)
							}
							
								cAdapter = new CustomCartAdapter(
									CartActvity.this, cart_title, cart_id,
									cart_img, cart_default_qty,
									cart_unit_price, cart_wholesale_price);
							carts_list.setAdapter(cAdapter);

						} else {
							// util.inputValidation("No cart items available");
							// liner_lay.setVisibility(View.GONE);
							// rrr.setVisibility(View.GONE);
							carts_list.setVisibility(View.GONE);
							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									CartActvity.this);
							altDialog.setMessage("There are no items in cart");
							altDialog.setNeutralButton("OK",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											startActivity(new Intent(
													CartActvity.this,
													DashBorad.class));
										}
									});
							altDialog.show();

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

	private class DeleteCartsTask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(CartActvity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
 
		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnDeleteCart&AuthToken=" + Constants.AUTH_TOKEN
					+ "&userid=" + Constants.User_Id);

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {

					if (null == result || result.length() == 0) {

					} else {
						// JSONObject jObject = new JSONObject(result);

						JSONObject jObject = new JSONObject(result);

						if (jObject.getString("SUCCESS").toString()
								.equalsIgnoreCase("true")) {
							JSONObject jsonDataobj = jObject
									.getJSONObject("DATA");

							if (jsonDataobj.has("points")) {
								Constants.str_points_incart = jsonDataobj
										.getInt("points");
							}
							if (jsonDataobj.has("pointsLeft")) {
								Constants.str_points_left = jsonDataobj
										.getInt("pointsLeft");
							}
							if (jsonDataobj.has("itemCount")) {
								Constants.itemCount = ""
										+ jsonDataobj.getInt("itemCount");
							}
							if (jsonDataobj.has("wishlistcount")) {
								Constants.wishlistcount = ""
										+ jsonDataobj.getInt("wishlistcount");
							}

							tv_txt.setText(Html
									.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"
											+ Constants.str_points_incart
											+ "</strong></b></font></body></html>"));
							tv_txt23.setText(Html
									.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"
											+ Constants.str_points_left
											+ "</strong></b></font></body></html>"));

							txt_wish_cnt1.setText(Constants.wishlistcount);
							txt_crt_cnt1.setText(Constants.itemCount);

							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									CartActvity.this);
							altDialog.setMessage("There are no items  in  cart ");
							altDialog.setNeutralButton("OK",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											startActivity(new Intent(
													CartActvity.this,
													DashBorad.class));
										}
									});
							altDialog.show();
							carts_list.setVisibility(View.GONE);
						} else {

							JSONObject jsonObject = jObject
									.getJSONObject("ERRORS");

							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									CartActvity.this);
							altDialog.setMessage(""
									+ jsonObject.getString("MESSAGE"));
							altDialog.setNeutralButton("OK",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											startActivity(new Intent(
													CartActvity.this,
													DashBorad.class));
										}
									});
							altDialog.show();

						}

						// liner_lay.setVisibility(View.GONE);
						// rrr.setVisibility(View.GONE);

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

	private class UpdateCartsListask extends AsyncTask<String, Void, String> {

//		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
//			myProgressDialog = new ProgressDialog(CartActvity.this);
//			myProgressDialog.setMessage("please wait ...");
//			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnUpdateCartItem&AuthToken="
					+ Constants.AUTH_TOKEN + "&userid=" + Constants.User_Id
					+ "&productchildid=" + productChildID + "&quantity="
					+ str_qty);

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {

					if (null == result || result.length() == 0) {

					} else {
						// JSONObject jObject = new JSONObject(result);

						JSONObject jObject = new JSONObject(result);
						JSONObject jsonDataobj = jObject.getJSONObject("DATA");

						if (jsonDataobj.has("points")) {
							Constants.str_points_incart = jsonDataobj
									.getInt("points");
						}
						if (jsonDataobj.has("pointsLeft")) {
							Constants.str_points_left = jsonDataobj
									.getInt("pointsLeft");
						}
						if (jsonDataobj.has("itemCount")) {
							Constants.itemCount = ""
									+ jsonDataobj.getInt("itemCount");
						}
						if (jsonDataobj.has("wishlistcount")) {
							Constants.wishlistcount = ""
									+ jsonDataobj.getInt("wishlistcount");
						}

						tv_txt.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_incart
										+ "</strong></b></font></body></html>"));
						tv_txt23.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_left
										+ "</strong></b></font></body></html>"));

						txt_wish_cnt1.setText(Constants.wishlistcount);
						txt_crt_cnt1.setText(Constants.itemCount);

						
						if (util.IsNetConnected(CartActvity.this)) {
							subtot.clear();
							new CartsListask().execute(); 
						} else {
							util.showAlertNoInternetService(CartActvity.this);
						}
//						
//						startActivity(new Intent(CartActvity.this,
//								CartActvity.class));
 
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

	private class DeleteCartItem extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(CartActvity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnDeleteCartItem&AuthToken="
					+ Constants.AUTH_TOKEN + "&userid=" + Constants.User_Id
					+ "&productChildID=" + productChildID);

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {

					if (null == result || result.length() == 0) {

					} else {
						// System.out.println("delete"+result);
						// Toast.makeText(getApplicationContext(), result,
						// Toast.LENGTH_LONG).show();
						JSONObject jObject = new JSONObject(result);
						JSONObject jsonDataobj = jObject.getJSONObject("DATA");

						if (jsonDataobj.has("points")) {
							Constants.str_points_incart = jsonDataobj
									.getInt("points");
						}
						if (jsonDataobj.has("pointsLeft")) {
							Constants.str_points_left = jsonDataobj
									.getInt("pointsLeft");
						}
						if (jsonDataobj.has("itemCount")) {
							Constants.itemCount = ""
									+ jsonDataobj.getInt("itemCount");
						}
						if (jsonDataobj.has("wishlistcount")) {
							Constants.wishlistcount = ""
			   						+ jsonDataobj.getInt("wishlistcount");
						}
						tv_txt.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_incart
										+ "</strong></b></font></body></html>"));
						tv_txt23.setText(Html
								.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"
										+ Constants.str_points_left
										+ "</strong></b></font></body></html>"));

						txt_wish_cnt1.setText(Constants.wishlistcount);
						txt_crt_cnt1.setText(Constants.itemCount);
						
						cAdapter.notifyDataSetChanged();  
//						cart_title
						if(cart_title.size()==0)
						{
						AlertDialog.Builder altDialog = new AlertDialog.Builder(
								CartActvity.this);
						altDialog.setMessage("There are no items in cart");
						altDialog.setNeutralButton("OK",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialog,
											int which) {
										dialog.dismiss();
										startActivity(new Intent(
												CartActvity.this,
												DashBorad.class));
									}
								});
						altDialog.show();
						carts_list.setVisibility(View.GONE);
						}
//						cAdapter.notifyAll();
//						carts_list.notifyAll();
//
//						startActivity(new Intent(CartActvity.this,
//								CartActvity.class));
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
		startActivity(new Intent(CartActvity.this, DashBorad.class));
		CartActvity.this.finish();
	}
}
