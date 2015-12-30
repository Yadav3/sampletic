package com.taico.android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.obj.ItemsObj;
import com.taico.android.util.Utility;

public class WishListActivity extends Activity {
	
	ListView wishlist;
	MySimpleArrayAdapter1 adapter1;
	String[] data={"inda b Pocket Wallet- Ravinia Black","india b Pocket Wallet- Ravinia Black","inda b Pocket Wallet- Ravinia Black"};
//	Integer[] image={R.drawable.wallet,R.drawable.wallet};
	Button btn_home,menus,wishL_clear;
	LinearLayout menlayout_wis,lll_show_logo;
	Boolean men_flg=false;;
	RelativeLayout favLayout,cartLayout,ray_header,top_ray,top_ray1,rrr_wis,logo_rrr;
	TextView txt_clear,txt_wish_cnt1,txt_crt_cnt1,textView1,textView2; 
	ArrayList<ItemsObj> wishlist_array_data;
	Utility util;
	String paroduce_child_id="",wishlists_id="",Wishitemcost="";
	ImageView menu,imageView1;
	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wish_listing);
		
		util = new Utility(WishListActivity.this);
	 	    
		txt_wish_cnt1 = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt1 = (TextView) findViewById(R.id.txt_crt_cnt1);
		txt_wish_cnt1.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt1.setTypeface(Constants.ProximaNova_Regular);
		menus = (Button) findViewById(R.id.menus);
		wishL_clear = (Button) findViewById(R.id.wishL_clear);
		menlayout_wis= (LinearLayout) findViewById(R.id.menlayout_wis);
		menlayout_wis.setVisibility(View.GONE);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		 
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount); 
		
		rrr_wis = (RelativeLayout) findViewById(R.id.rrr_wis);
		
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			textView1.setVisibility(View.GONE);
			textView2.setVisibility(View.GONE);
//			rrr_wis.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
//			rrr_wis.setVisibility(View.VISIBLE);
			textView1.setVisibility(View.VISIBLE); 
			textView2.setVisibility(View.VISIBLE);
		}
		else
		{
//			rrr_wis.setVisibility(View.GONE);
			textView1.setVisibility(View.GONE);
			textView2.setVisibility(View.GONE);
		}
		
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
		 
		
		
		if (util.IsNetConnected(WishListActivity.this)) {
					new Getuserpoints().execute();
			 } else {
			 util.showAlertNoInternetService(WishListActivity.this);
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

textView1.setTypeface(Constants.ProximaNova_Regular);

textView2.setTypeface(Constants.ProximaNova_Regular);
		
		
		ray_header = (RelativeLayout)findViewById(R.id.ray_header);
		ray_header.setBackgroundColor(Login.bg_color);
		
		top_ray = (RelativeLayout)findViewById(R.id.top_ray);
		top_ray.setBackgroundColor(Login.bg_color);
		
		top_ray1 = (RelativeLayout)findViewById(R.id.top_ray1);
		top_ray1.setBackgroundColor(Login.bg_color); 
		
	
		
		wishlist_array_data = new ArrayList<ItemsObj>();
		
		 txt_clear= (TextView)findViewById(R.id.txt_clear);
		 txt_clear.setTypeface(Constants.ProximaNova_Regular); 
	  	
		btn_home= (Button)findViewById(R.id.btn_home);
		btn_home.setText(R.string.wish_list);
		 btn_home.setTypeface(Constants.ProximaNova_Regular);
		 
		
		wishlist=(ListView)findViewById(R.id.wishlist);
		wishlist.setBackgroundColor(Login.bg_color);
//		adapter=new MySimpleArrayAdapter(this,data,image);
//		wishlist.setAdapter(adapter);
		wishlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				startActivity(new Intent(WishListActivity.this,Details.class));
				 
				 
				/*ItemsObj itemsObj = (ItemsObj)wishlist_array_data.get(arg2);
				Constants.productID=itemsObj.getListing_id();
//				Toast.makeText(getApplicationContext(), "true"+Constants.productID, Toast.LENGTH_LONG).show();
				if(Constants.productID.length()>0)
				{
					startActivity(new Intent(WishListActivity.this, Details.class));
				}*/
				 
			/*	try
				{ 
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					 Login.current_date_time = df.format(Calendar.getInstance().getTime());
				}catch(Exception e)
				{
				}
				if(	util.current_dat(Login.pref.getString(Login.Key_GET_LST_SERVICE_CALL, ""),  Login.current_date_time))
				{
					Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_LONG).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_LONG).show();
				}*/

			}
		});
//		 
//		Login.editor.putString(Login.Key_GET_LST_SERVICE_CALL, "" );
//		Login.editor.commit();
//		
//		Login.pref.getString(Login.Key_GET_LST_SERVICE_CALL, "");
	/*if(	util.current_dat(Login.pref.getString(Login.Key_GET_LST_SERVICE_CALL, ""),  Login.current_date_time))
	{
		Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_LONG).show();
	}
	else
	{
		Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_LONG).show();
	}*/
//		 
		if ( util.IsNetConnected(WishListActivity.this)) {
			new WishListTask().execute();
		} else {
			util.showAlertNoInternetService(WishListActivity.this);
		}
		
		btn_home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(WishListActivity.this, DashBorad.class)); 
				WishListActivity.this.finish();
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

		favLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//              TODO Auto-generated method stub
//				startActivity(new Intent(WishListActivity.this,	WishListActivity.class));
//				WishListActivity.this.finish();
			}
		}); 

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(WishListActivity.this, CartActvity.class));
				WishListActivity.this.finish();
			}
		});
 
		btn_home.setText(R.string.wish_list);  
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(WishListActivity.this,DashBorad.class));
				WishListActivity.this.finish(); 

			}
		}); 
		 
		menus.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				menlayout_wis.removeAllViews();
				if(men_flg)
				{
					men_flg=false; 
					menlayout_wis.setVisibility(View.GONE);
				
					
				}else
				{
					menlayout_wis.setVisibility(View.VISIBLE);
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
							startActivity(new Intent(WishListActivity.this, Home.class));
							WishListActivity.this.finish();
						}
					});
					
					tv_menu1.setOnClickListener(new OnClickListener() { 
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(WishListActivity.this, DashBorad.class));
							WishListActivity.this.finish();
						}
					}); 
					
					tv_menu2.setOnClickListener(new OnClickListener() {
								 	 
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(WishListActivity.this, Login.class));
										WishListActivity.this.finish(); 
									}
								});
					 
					menlayout_wis.addView(mLinearView); 
				}
			}
		});
		
		
		wishL_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (util.IsNetConnected(WishListActivity.this)) {

					AlertDialog.Builder altDialog = new AlertDialog.Builder(
							WishListActivity.this);
					altDialog
							.setMessage("Would you like to delete all items from  Wish List?");

					altDialog.setNeutralButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									new DeleteAllWishListTask().execute();

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
					util.showAlertNoInternetService(WishListActivity.this);
				}

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
			  animation = AnimationUtils.loadAnimation(WishListActivity.this, R.anim.slide_in_top);
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
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount);
	}
	
	
	
	private class DeleteAllWishListTask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(WishListActivity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
 
		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnDeleteAllWishListItem&AuthToken=" + Constants.AUTH_TOKEN
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

							textView1.setText(Html
									.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"
											+ Constants.str_points_incart
											+ "</strong></b></font></body></html>"));
							textView2.setText(Html
									.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"
											+ Constants.str_points_left
											+ "</strong></b></font></body></html>"));

							txt_wish_cnt1.setText(Constants.wishlistcount);
							txt_crt_cnt1.setText(Constants.itemCount);

							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									WishListActivity.this);
							altDialog.setMessage("There are no items  in  Wish List ");
							altDialog.setNeutralButton("OK",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
											startActivity(new Intent(
													WishListActivity.this,
													DashBorad.class));
										}
									});
							altDialog.show();
							wishlist.setVisibility(View.GONE);
						} else {

							JSONObject jsonObject = jObject
									.getJSONObject("ERRORS");

							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									WishListActivity.this);
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
													WishListActivity.this,
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
	
	private class WishListTask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(WishListActivity.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetWishListItems&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid=" + Constants.User_Id +"&Clientid="+Constants.clientID+"&Clientratio="
					+ Constants.clientRatio + "&clienttaxpercent="
					+ Constants.clienttaxpercent ); 

		/*	AuthToken
			userid 
			Clientid
			Clientratio
			clienttaxpercent
		 					*/
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					

					if (null == result || result.length() == 0) {

					} else {
						wishlist_array_data.clear();
						JSONObject jObject = new JSONObject(result);
						JSONArray	wish_list_jsonArrays = jObject.getJSONArray("DATA");
						if(wish_list_jsonArrays.length()>0)
						{ 
				 			for (int i = 0; i <wish_list_jsonArrays.length(); i++) {
								JSONObject jsonObject = wish_list_jsonArrays.getJSONObject(i);
								ItemsObj objItem = new ItemsObj();
									
								if(jsonObject.has("IMAGEURL"))
								{
									objItem.setListing_img(jsonObject.getString("IMAGEURL").replace(" ", "%20"));
								}
								else
								{
									objItem.setListing_img("0");
								}
								if(jsonObject.has("USERUNITFINALCOST"))
								{
									objItem.setListing_points(""+jsonObject.getInt("USERUNITFINALCOST")); 
								}
								else
								{
									objItem.setListing_points("0"); 
								}
								if(jsonObject.has("PRODUCTTITLE"))
								{
									objItem.setListing_title(jsonObject.getString("PRODUCTTITLE"));
								}
								else
								{
									objItem.setListing_title("null");
								}
								if(jsonObject.has("ID"))
								{
									objItem.setListing_id(jsonObject.getString("ID"));
								} 
								else
								{
									objItem.setListing_id("0");
								}
								
								if(jsonObject.has("PRODUCTCHILDID"))
			 					{
									objItem.setProductChildID(jsonObject.getString("PRODUCTCHILDID"));
								} 
								else
								{
									objItem.setProductChildID("0");
								}
				 				
								wishlist_array_data.add(objItem);
							}
							 
							adapter1 = new MySimpleArrayAdapter1(WishListActivity.this,
									R.layout.row_wish3, wishlist_array_data);
							wishlist.setAdapter(adapter1);
		 					
	 			 	
						}
	 					
	 					
	 					else
						{ 
							AlertDialog.Builder altDialog = new AlertDialog.Builder(WishListActivity.this);
							 altDialog.setMessage("There are no items  in WishList"); 
							altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									startActivity(new Intent(WishListActivity.this,DashBorad.class));
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
	
	
	public class MySimpleArrayAdapter1 extends ArrayAdapter<ItemsObj> {

		private Activity activity;
		private List<ItemsObj> items;
		private ItemsObj objBean;
		@SuppressWarnings("unused")
		private int row;
		Bitmap bimg;
		String strQty = "0";
 
		public MySimpleArrayAdapter1(Activity act, int resource,
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
	
		@SuppressLint("InflateParams")
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			MyViewHolder mViewHolder;
			LayoutInflater inflator = activity.getLayoutInflater();
			objBean = items.get(position);
			
			if (convertView == null) {
				mViewHolder = new MyViewHolder();

					convertView = inflator.inflate(R.layout.row_wish3,
							null);
				
					mViewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.img_wallet);
					mViewHolder.img_delet = (ImageView) convertView.findViewById(R.id.img_delet);
					
					mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.title);
					
					
					mViewHolder.txt_ptval = (TextView) convertView.findViewById(R.id.txt_ptval);
					
					
					mViewHolder.txt_pt = (TextView) convertView.findViewById(R.id.txt_pt);
					mViewHolder.txt_per = (TextView) convertView.findViewById(R.id.txt_per);
					mViewHolder.txt_go = (TextView) convertView.findViewById(R.id.txt_go);
					mViewHolder.txt_move_to_cart = (TextView) convertView.findViewById(R.id.txt_move_to_cart);
					mViewHolder.txt_delet = (TextView) convertView.findViewById(R.id.txt_delet);
					
					mViewHolder.tvTitle.setTypeface(Constants.ProximaNova_Regular); 
					mViewHolder.txt_ptval.setTypeface(Constants.ProximaNova_Regular); 
					mViewHolder.txt_pt.setTypeface(Constants.ProximaNova_Regular); 
					mViewHolder.txt_per.setTypeface(Constants.ProximaNova_Regular); 
					mViewHolder.txt_go.setTypeface(Constants.ProximaNova_Regular); 
					mViewHolder.txt_move_to_cart.setTypeface(Constants.ProximaNova_Regular); 
					mViewHolder.txt_delet.setTypeface(Constants.ProximaNova_Regular); 
				 	 
					mViewHolder.lin_lay= (RelativeLayout) convertView.findViewById(R.id.lin_lay);
					mViewHolder.lin_lay.setBackgroundColor(Login.bg_color);
					
					 
					

				convertView.setTag(mViewHolder);
			} else {
				mViewHolder = (MyViewHolder) convertView.getTag();
			}
			
			mViewHolder.tvTitle.setText(objBean.getListing_title().replace("\n", "")); 
			mViewHolder.txt_ptval.setText(objBean.getListing_points());
			// download and display image from url
			String url="";
			if(objBean.getListing_img().contains("http"))
 			{
				url=objBean.getListing_img();
			}
			else
			{ 
				url=Constants.prouducts_url+objBean.getListing_img();
			}
			 
			Picasso.with(getApplicationContext())
			.load(url.replace(" ", "%20").trim())
			.placeholder(R.drawable.login_logo)
			.error(R.drawable.bag3).fit().into(mViewHolder.ivIcon); 
			System.out.println("url"+url);
			/*ImageLoader imageLoader = ImageLoader.getInstance();
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.cacheInMemory(true).cacheOnDisc(true)
					.resetViewBeforeLoading(true)
					.showImageForEmptyUri(R.drawable.bag3)
					.showImageOnFail(R.drawable.bag3)
					.showImageOnLoading(R.drawable.bag3).build();
			
			imageLoader.displayImage(
					url.replace("\'", "%20")
							.trim(), mViewHolder.ivIcon, options);*/
			 
			mViewHolder.img_delet .setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
//                  TODO Auto-generated method stub
					if (util.IsNetConnected(WishListActivity.this)) {
						ItemsObj itemsObj = (ItemsObj) items.get(position);
//						Toast.makeText(getApplicationContext(), objBean.getProductChildID(), Toast.LENGTH_LONG).show();
						 paroduce_child_id=itemsObj.getProductChildID();
						wishlists_id=itemsObj.getListing_id();
						
						AlertDialog.Builder altDialog = new AlertDialog.Builder(
								WishListActivity.this);
						altDialog
								.setMessage("Would you like to delete the item from wishlist?");

						altDialog.setNeutralButton("Yes",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										
									wishlist_array_data
												.remove(position); 
										 
//									items
//									.remove(position); 
										
										
										new DeleteWishListItem().execute();
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
						util.showAlertNoInternetService(WishListActivity.this);
					}
					 
					 
					 
					
				}  
			});
			
			mViewHolder.txt_go.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 if (util.IsNetConnected(WishListActivity.this)) {
						 
						 ItemsObj itemsObj = (ItemsObj) items.get(position);
						 paroduce_child_id=itemsObj.getProductChildID();
							wishlists_id=itemsObj.getListing_id(); 
							Wishitemcost=itemsObj.getListing_points();
							
						 AlertDialog.Builder altDialog = new AlertDialog.Builder(WishListActivity.this);
							altDialog
									.setMessage("Would you like to move the item from wishlist to cart?"); 
							
							altDialog.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
//									items
//												.remove(position); 
//									
									wishlist_array_data
									.remove(position); 
											new MoveWishListItem().execute();
											
								}
							});
							altDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss(); 
								}
							});
							altDialog.show();
							 }
					 else {
						 util.showAlertNoInternetService(WishListActivity.this);
						 }
					
					
					
				}
			});
			
		
			
			return convertView;
		}

		private class MyViewHolder {
			
			TextView tvTitle,txt_ptval,txt_pt,txt_per,txt_go,txt_move_to_cart,txt_delet;
			ImageView ivIcon,img_delet;
			RelativeLayout lin_lay; 
			
		}

	}
	
	private class DeleteWishListItem extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(WishListActivity.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnDeleteWishListItem&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid=" + Constants.User_Id 
					+ "&productChildID="+paroduce_child_id
					+ "&wishlistid="+wishlists_id); 
			 
//			AuthToken 
//			productChildID
//			wishlistid
			

  
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					if (null != myProgressDialog
							&& myProgressDialog.isShowing()) {
						myProgressDialog.dismiss();
					}

					if (null == result || result.length() == 0) {

					} else {
						JSONObject jObject = new JSONObject(result);
						System.out.println("jObject"+jObject); 
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
//						adapter1 = new MySimpleArrayAdapter1(WishListActivity.this,
//	 							R.layout.row_wish3, wishlist_array_data);
						txt_wish_cnt1.setText(Constants.wishlistcount); 
						txt_crt_cnt1.setText(Constants.itemCount); 
						
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
						adapter1.notifyDataSetChanged(); 
						
//						startActivity(new Intent(WishListActivity.this, WishListActivity.class)); 
			 			
						if(wishlist_array_data.size()==0)
						{
						AlertDialog.Builder altDialog = new AlertDialog.Builder(WishListActivity.this);
						 altDialog.setMessage("There are no items  in WishList"); 
						altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								startActivity(new Intent(WishListActivity.this,DashBorad.class));
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
	
	
	private class MoveWishListItem extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(WishListActivity.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnMoveToCart&AuthToken="+Constants.AUTH_TOKEN
					+ "&userid=" + Constants.User_Id 
					+ "&productChildID="+paroduce_child_id
					+ "&Wishitemcost="+Wishitemcost
					+ "&wishlistid="+wishlists_id); 
			 
//			AuthToken 
//			productChildID
//			wishlistid
			

 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					if (null != myProgressDialog
							&& myProgressDialog.isShowing()) {
						myProgressDialog.dismiss();
					}

					if (null == result || result.length() == 0) {

					} else {
						JSONObject jObject = new JSONObject(result);
						System.out.println("jObject"+jObject); 
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
						adapter1.notifyDataSetChanged();
//						startActivity(new Intent(WishListActivity.this, WishListActivity.class)); 
						
						txt_wish_cnt1.setText(Constants.wishlistcount); 
						txt_crt_cnt1.setText(Constants.itemCount); 
						
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
						
						
						if(wishlist_array_data.size()==0)
						{
						AlertDialog.Builder altDialog = new AlertDialog.Builder(WishListActivity.this);
						 altDialog.setMessage("There are no items  in WishList"); 
						altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								startActivity(new Intent(WishListActivity.this,DashBorad.class));
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
	
	private class Getuserpoints extends AsyncTask<String, Void, String> {

//		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
//			myProgressDialog = new ProgressDialog(WishListActivity.this);
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
						textView1.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_incart+"</strong></b></font></body></html>"));
						textView2.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_left+"</strong></b></font></body></html>"));
					
						
						txt_wish_cnt1.setText(Constants.wishlistcount);
						txt_crt_cnt1.setText(Constants.itemCount);
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
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub 
		super.onBackPressed(); 
		startActivity(new Intent(WishListActivity.this, DashBorad.class));
		WishListActivity.this.finish();
	}
}