package com.taico.android;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.util.Utility;

public class Home extends Activity {
//LinearLayout shops_layout,my_account_layout;
	RelativeLayout favLayout,cartLayout,top_lay,ray_lay,hor,logo_rrr;
	Button btn_home,menus;
	TextView txt_start_shoping, txt_my_accounts, txt_logout, txt_name, txt_wel,
			txt_ptvalue, txt_pt, txt_ptleftval, txt_ptleft,txt_wish_cnt,txt_crt_cnt,txt_hom_msg;
	ScrollView scr_lay;
	LinearLayout menlayout_hom,lll_show_logo;
	Utility util;
	Boolean men_flg=false;
	ImageView menu,imageView1;
	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home); 
		 
		util = new Utility(Home.this);
		
		txt_hom_msg= (TextView) findViewById(R.id.txt_hom_msg);
		txt_wish_cnt = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt = (TextView) findViewById(R.id.txt_crt_cnt1);
		txt_wish_cnt.setText(Constants.wishlistcount);
		txt_crt_cnt.setText(Constants.itemCount);
		txt_wish_cnt.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt.setTypeface(Constants.ProximaNova_Regular);
		txt_hom_msg.setTypeface(Constants.ProximaNova_Regular);
		
		menus = (Button) findViewById(R.id.menus); 
		btn_home = (Button) findViewById(R.id.btn_home);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		menlayout_hom= (LinearLayout) findViewById(R.id.menlayout_hom);
		menlayout_hom.setVisibility(View.GONE);
		
		/*String url="";
		if(Constants.MAINLOGO.contains("http"))
			{ 
			url=Constants.MAINLOGO;
//			url="http://stg.incentiveweb.com/"+Constants.DIRECTORYs+"/images/"+Constants.MAINLOGO;
			
		} 
		else 
		{
			url="http://stg.incentiveweb.com/"+Constants.DIRECTORYs+"/images/"+Constants.MAINLOGO;
		}
		 
		Picasso.with(getApplicationContext())
		.load(url.replace("\'", "%20").replace(" ", "%20"))
		.placeholder(R.drawable.login_logo)
		.error(R.drawable.bag3).fit().into(btn_home); */
		
		/*ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.login_logo)
				.showImageOnFail(R.drawable.login_logo)
				.showImageOnLoading(R.drawable.login_logo).build(); 
		
		imageLoader.displayImage(
				url.replace("\'", "%20")
						.trim(), btn_home, options);*/

		
		
		if (util.IsNetConnected(Home.this)) {
			new Getuserpoints().execute();
	 } else {
	 util.showAlertNoInternetService(Home.this);
	 } 
		
		top_lay = (RelativeLayout) findViewById(R.id.ray_header);
		ray_lay = (RelativeLayout) findViewById(R.id.ray_lay);
		scr_lay = (ScrollView) findViewById(R.id.scr_lay);
		top_lay.setBackgroundColor(Login.bg_color);
		ray_lay.setBackgroundColor(Login.bg_color);
		scr_lay.setBackgroundColor(Login.bg_color);
		 
		hor = (RelativeLayout) findViewById(R.id.hor);
		
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			hor.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			hor.setVisibility(View.VISIBLE);
		}
		else
		{
			hor.setVisibility(View.GONE);
		}
		 
		txt_start_shoping= (TextView)findViewById(R.id.txt_start_shoping);
		txt_my_accounts= (TextView)findViewById(R.id.txt_my_accounts);
		txt_logout= (TextView)findViewById(R.id.txt_logout);
		
		txt_name= (TextView)findViewById(R.id.txt_name);
		txt_name.setText(Constants.FIRSTNAME); 
//		txt_name.setText("Theresa");
		if(Constants.FIRSTNAME.length()>0)
		{
			txt_name.setVisibility(View.VISIBLE);
		}
		else
		{ 
			txt_name.setVisibility(View.GONE);
		}
		txt_hom_msg.setText(Html.fromHtml(Constants.HPMESSAGE));
		
		if(Constants.HPMESSAGE.length()>0)
		{
			txt_hom_msg.setVisibility(View.VISIBLE);
		}
		else
		{  
			txt_hom_msg.setVisibility(View.GONE);
		}
		
		txt_wel= (TextView)findViewById(R.id.txt_wel);
		txt_ptvalue= (TextView)findViewById(R.id.txt_ptvalue);
		txt_ptvalue.setText(""+Constants.str_points_incart);  
		
		txt_pt= (TextView)findViewById(R.id.txt_pt);
		txt_ptleftval= (TextView)findViewById(R.id.txt_ptleftval);
		txt_ptleftval.setText(""+Constants.str_points_left);
		txt_ptleft= (TextView)findViewById(R.id.txt_ptleft);
		
		txt_start_shoping.setTypeface(Constants.ProximaNova_Regular);
		txt_my_accounts.setTypeface(Constants.ProximaNova_Regular);
		txt_logout.setTypeface(Constants.ProximaNova_Regular);
		
		txt_name.setTypeface(Constants.ProximaNova_Regular);
		txt_wel.setTypeface(Constants.ProximaNova_Regular);
		txt_ptvalue.setTypeface(Constants.ProximaNova_Regular);
	 	
		txt_pt.setTypeface(Constants.ProximaNova_Regular);
		txt_ptleftval.setTypeface(Constants.ProximaNova_Regular);
		txt_ptleft.setTypeface(Constants.ProximaNova_Regular);
		
		 
		 /* GradientDrawable gd = (GradientDrawable)
				  txt_start_shoping.getBackground().getCurrent(); gd.setColor(Color.BLUE);
		 gd.setStroke(2, Color.GREEN, 0, 0);*/
		 
		
		txt_start_shoping.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Constants.Catid="0";
				startActivity(new Intent(Home.this,
						ShopMainActivity.class)); 
				Home.this.finish();

			}
		});
		
		/** Change the app background color and font color in application*/
		if(Constants.GETBUTTONCOLOR.length()>0&&Constants.GETBUTTONFONTCOLOR.length()>0)
		{
			  GradientDrawable gd = (GradientDrawable)
			  txt_start_shoping.getBackground().getCurrent(); gd.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
	 gd.setStroke(2, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
	 
	 GradientDrawable gd1 = (GradientDrawable)
			 txt_my_accounts.getBackground().getCurrent(); gd1.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
	 gd1.setStroke(2, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
	 
	 txt_start_shoping.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
		txt_my_accounts.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
		
		
		
			
			  GradientDrawable gd11 = (GradientDrawable)
					  txt_wish_cnt.getBackground().getCurrent(); gd11.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd11.setStroke(0, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
			  
			 GradientDrawable gd111 = (GradientDrawable)
					 txt_crt_cnt.getBackground().getCurrent(); gd111.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd111.setStroke(0, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
			 
			 txt_wish_cnt.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
			 txt_crt_cnt.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
		
		}
		else
		{
			/* GradientDrawable gd = (GradientDrawable)
					  txt_start_shoping.getBackground().getCurrent(); gd.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd.setStroke(2, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);
			 
			 GradientDrawable gd1 = (GradientDrawable)
					 txt_my_accounts.getBackground().getCurrent(); gd1.setColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			 gd1.setStroke(2, Color.parseColor("#"+Constants.GETBUTTONCOLOR), 0, 0);*/
			  
			txt_start_shoping.setBackgroundResource(R.drawable.home_rect_orange);
			txt_my_accounts.setBackgroundResource(R.drawable.home_rect_orange);
			 txt_start_shoping.setTextColor(Color.WHITE);
				txt_my_accounts.setTextColor(Color.WHITE); 
				
				txt_wish_cnt.setTextColor(Color.WHITE);
				txt_crt_cnt.setTextColor(Color.WHITE); 
				txt_wish_cnt.setBackgroundResource(R.drawable.red_circle);
				txt_crt_cnt.setBackgroundResource(R.drawable.red_circle); 
		}
		/*if (Constants.admin.equalsIgnoreCase("0") ) {
			txt_my_accounts.setVisibility(View.GONE);
		}
		else
		{
			txt_my_accounts.setVisibility(View.VISIBLE); 
		}*/
		txt_my_accounts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Home.this,
						MyAccount_History.class));
				Home.this.finish();

			}
		});
		 
		txt_logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Constants. AUTH_TOKEN="";
				Constants.itemCount="0";
				Constants.wishlistcount="0";
				Constants.jsn_res="";
				Constants.clientRatio="";
				Constants.CLIENTMAXPOINTS="";
				Constants.CLIENTMINPOINTS="";
				Constants.clientID="";
				Constants.User_Id=0;
				Constants.SHOWWISHLIST=0;
				Constants.SHOWADDTOCARTBUTTON=0;
				Login.editor.clear();
				Login.editor.commit();
				startActivity(new Intent(Home.this,
						Login.class));
				Home.this.finish();
 
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
				// TODO Auto-generated method stub
				startActivity(new Intent(Home.this,
						WishListActivity.class));
				Home.this.finish();

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Home.this, CartActvity.class));
				Home.this.finish();
			}
		});

		btn_home.setText(R.string.home);  
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Home.this,DashBorad.class));
				Home.this.finish(); 

			}
		}); 
		
		
		menus.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				menlayout_hom.removeAllViews();
				if(men_flg)
				{
					men_flg=false; 
					menlayout_hom.setVisibility(View.GONE);
				
					
				}else
				{
					menlayout_hom.setVisibility(View.VISIBLE);
					men_flg=true;
					LayoutInflater inflater = null;
					inflater = (LayoutInflater) getApplicationContext()
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View mLinearView = inflater.inflate(R.layout.menus_layout, null);
					TextView tv_menu1 = (TextView)mLinearView.findViewById(R.id.tv_menu1);
					TextView tv_menu2 = (TextView)mLinearView.findViewById(R.id.tv_menu2);
					TextView tv_hom1 = (TextView)mLinearView.findViewById(R.id.tv_hom1);
					tv_hom1.setTypeface(Constants.ProximaNova_Regular);
					View view_id11 = (View)mLinearView.findViewById(R.id.view_id11);
					view_id11.setVisibility(View.GONE);
					tv_hom1.setVisibility(View.GONE); 
					 
				/*	tv_hom1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(Home.this, DashBorad.class));
							Home.this.finish();
						}
					});*/
					
					tv_menu1.setTypeface(Constants.ProximaNova_Regular);
					tv_menu2.setTypeface(Constants.ProximaNova_Regular);
					tv_menu1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(Home.this, DashBorad.class));
							Home.this.finish();
						}
					});
					
					tv_menu2.setOnClickListener(new OnClickListener() {
								 	 
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(Home.this, Login.class));
										Home.this.finish();
									}
								});
					 
					menlayout_hom.addView(mLinearView); 
				}
			}
		});
		lll_show_logo = (LinearLayout) findViewById(R.id.lll_show_logo);
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
		 	
			
//			new Handler().postDelayed(new Runnable() {
//		      @Override
//		      public void run() {
//		    	  imageView1.startAnimation(animationtop); 
//		    	  logo_rrr.startAnimation(animationtop);
		    	  animation = AnimationUtils.loadAnimation(Home.this, R.anim.slide_in_top);
		    	  animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						imageView1.setVisibility(View.INVISIBLE);  
				    	  logo_rrr.setVisibility(View.INVISIBLE);   
				    	//  btn_home.setVisibility(View.INVISIBLE); 
				    	
				    
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
		  	
//		      }
//		    }, 3000);
			
			
			
			lll_show_logo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					  btn_home.setVisibility(View.INVISIBLE); 
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
//			myProgressDialog = new ProgressDialog(Home.this);
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
//						textView1.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart&nbsp&nbsp&nbsp</font><font color='#EA2828'><b><strong>"+Constants.str_points_incart+"</strong></b></font></body></html>"));
//						textView2.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left&nbsp&nbsp&nbsp</font><font color='#EA2828'><b><strong>"+Constants.str_points_left+"</strong></b></font></body></html>"));
//					
						
						txt_wish_cnt.setText(Constants.wishlistcount);
						txt_crt_cnt.setText(Constants.itemCount);
						  
						txt_ptvalue.setText(""+Constants.str_points_incart);  
						txt_ptleftval.setText(""+Constants.str_points_left);
					}
					
//				
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
		startActivity(new Intent(Home.this, DashBorad.class));
		Home.this.finish();
	}
}
/*
 * GradientDrawable gd = (GradientDrawable)
 * pickDate.getBackground().getCurrent(); gd.setColor(Color.BLUE);
 * //gd.setStroke(2, Color.GREEN, 0, 0);
 */