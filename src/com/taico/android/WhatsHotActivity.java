package com.taico.android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.obj.ItemsObj;
import com.taico.android.util.Utility;

public class WhatsHotActivity extends Activity {
	GridView grid;
	String[] web = { "Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",

	};
	int[] imageId = { R.drawable.bag3, R.drawable.bag3, R.drawable.bag3,
			R.drawable.bag3, R.drawable.bag3,

	};
	private GridviewAdapter mAdapter;
	private ArrayList<String> listingName;
	private ArrayList<String> listProduct_id;
	private ArrayList<String> listProduct_img;
	private ArrayList<String> listProduct_dec;
	int page=1;
	ArrayList<ItemsObj> whatslist_array_data;
	Utility util;
	WhatsHotAdapter adapter;
	ViewGroup layout;
	RelativeLayout favLayout, cartLayout, top_lay, top_lay1, rrr, top_ray,rrr_wht,logo_rrr;
	TextView textView1, textView2,txt_wish_cnt,txt_crt_cnt;
	Button btn_home,menus;
	LinearLayout menlayout_wht,lll_show_logo;
	Boolean men_flg=false;
	ImageView menu,imageView1;
	Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whats_hot);

		util = new Utility(WhatsHotActivity.this);
  
		whatslist_array_data = new ArrayList<ItemsObj>();
		 
		
		btn_home = (Button) findViewById(R.id.btn_home);
		menus = (Button) findViewById(R.id.menus);
		menlayout_wht= (LinearLayout) findViewById(R.id.menlayout_wht);
		menlayout_wht.setVisibility(View.GONE);
		
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		txt_wish_cnt = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt = (TextView) findViewById(R.id.txt_crt_cnt1);
		txt_wish_cnt.setText(Constants.wishlistcount);
		txt_crt_cnt.setText(Constants.itemCount);
		txt_wish_cnt.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt.setTypeface(Constants.ProximaNova_Regular); 
		
		rrr_wht = (RelativeLayout) findViewById(R.id.rrr_wht);
		
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			rrr_wht.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			rrr_wht.setVisibility(View.VISIBLE);
		}
		else
		{
			rrr_wht.setVisibility(View.GONE);
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
		
		top_lay1 = (RelativeLayout) findViewById(R.id.top_lay1);
		top_lay1.setBackgroundColor(Login.bg_color);
		
		top_lay = (RelativeLayout) findViewById(R.id.ray_header);
		top_lay.setBackgroundColor(Login.bg_color);

		rrr = (RelativeLayout) findViewById(R.id.rrr);
		// rrr.setBackgroundColor(Login.bg_color);

		grid = (GridView) findViewById(R.id.gridview);
		grid.setBackgroundColor(Login.bg_color);

		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		 
	/*	String url="";
		if(Constants.MAINLOGO.contains("http"))
			{ 
//			url=Constants.MAINLOGO;
			url="http://stg.incentiveweb.com/"+Constants.DIRECTORYs+"/images/"+Constants.MAINLOGO;
			
		} 
		else 
		{
			url="http://stg.incentiveweb.com/"+Constants.DIRECTORYs+"/images/"+Constants.MAINLOGO;
		}
		 
		Picasso.with(getApplicationContext())
		.load(url.replace("\'", "%20").trim())
		.placeholder(R.drawable.login_logo)
		.error(R.drawable.bag3).fit().into(btn_home);*/
		
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
		
		
	
 
		txt_wish_cnt.setText(Constants.wishlistcount);
		txt_crt_cnt.setText(Constants.itemCount);
		
	
		textView1
				.setText(Html
						.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"
								+Constants.str_points_incart
								+ "</strong></b></font></body></html>"));

		textView2
				.setText(Html
						.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"
								+ Constants.str_points_left
								+ "</strong></b></font></body></html>"));

		textView1.setTypeface(Constants.ProximaNova_Regular);

		textView2.setTypeface(Constants.ProximaNova_Regular);
		
		 
		btn_home.setText(R.string.whats_hot); 
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(WhatsHotActivity.this,DashBorad.class));
				WhatsHotActivity.this.finish(); 

			}
		}); 
		
		menus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				menlayout_wht.removeAllViews();
				if(men_flg)
				{
					men_flg=false; 
					menlayout_wht.setVisibility(View.GONE);
				
					
				}else
				{
					menlayout_wht.setVisibility(View.VISIBLE);
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
							startActivity(new Intent(WhatsHotActivity.this, Home.class));
							WhatsHotActivity.this.finish();
						}
					});
					
					tv_menu1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(WhatsHotActivity.this, DashBorad.class));
							WhatsHotActivity.this.finish();
						}
					});
					
					tv_menu2.setOnClickListener(new OnClickListener() {
								 	 
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(WhatsHotActivity.this, Login.class));
										WhatsHotActivity.this.finish(); 
									}
								});
					 
					menlayout_wht.addView(mLinearView); 
				}
			}
		});

		/*
		 * CustomGrid1 adapter = new CustomGrid1(WhatsHotActivity.this, web,
		 * imageId);
		 * 
		 * grid.setAdapter(adapter);
		 */
		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(WhatsHotActivity.this, "You Clicked at "
				// +web[+ position], Toast.LENGTH_SHORT).show();
				Constants.productID=listProduct_id.get(position);
				if(Constants.productID.length()>0)
				{
					startActivity(new Intent(WhatsHotActivity.this, Details.class));
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

		favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(WhatsHotActivity.this,
						WishListActivity.class));
				WhatsHotActivity.this.finish();

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
		
				startActivity(new Intent(WhatsHotActivity.this,
						CartActvity.class));
				WhatsHotActivity.this.finish();
			}
		});

		

		 if ( util.IsNetConnected(WhatsHotActivity.this)) {
			 
		new WhatsHotListing().execute();

		 } else {
		 util.showAlertNoInternetService(WhatsHotActivity.this);
		 }
		 
		 if (util.IsNetConnected(WhatsHotActivity.this)) {
				new Getuserpoints().execute();
		 } else {
		 util.showAlertNoInternetService(WhatsHotActivity.this);
		 } 
		 
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
			  animation = AnimationUtils.loadAnimation(WhatsHotActivity.this, R.anim.slide_in_top);
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
	textView1.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_incart+"</strong></b></font></body></html>"));
	textView2.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_left+"</strong></b></font></body></html>"));
 
	txt_wish_cnt.setText(Constants.wishlistcount);
	txt_crt_cnt.setText(Constants.itemCount);
}


private class Getuserpoints extends AsyncTask<String, Void, String> {

//	ProgressDialog myProgressDialog;

	protected void onPreExecute() {
		super.onPreExecute();
//		myProgressDialog = new ProgressDialog(WishListActivity.this);
//		myProgressDialog.setMessage("please wait ...");
//		myProgressDialog.show();
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
//				if (null != myProgressDialog && myProgressDialog.isShowing()) {
//					myProgressDialog.dismiss();
//				}

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
				
					
					txt_wish_cnt.setText(Constants.wishlistcount);
					txt_crt_cnt.setText(Constants.itemCount);
				}
				
			
//				if (myProgressDialog.isShowing())
//					myProgressDialog.dismiss();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

//			if (myProgressDialog.isShowing())
//				myProgressDialog.dismiss();
			// }

			
		} catch (Exception e) {
//			if (myProgressDialog.isShowing())
//				myProgressDialog.dismiss();
		}

	}
}

	private class WhatsHotListing extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(WhatsHotActivity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			/*
			 * return WebServiceCalls.getJSONString(Constants.host_nam +
			 * "Getgalleries");
			 */
			return "res";

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					whatslist_array_data.clear();
					if (null != myProgressDialog
							&& myProgressDialog.isShowing()) {
						myProgressDialog.dismiss();
					}

					if (null == result || result.length() == 0) {

					} else {
						prepareList();
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
	
	
	public void prepareList() {
		if (page == 1) {
			listingName = new ArrayList<String>();
			listProduct_id = new ArrayList<String>();
			listProduct_img = new ArrayList<String>();
			listProduct_dec = new ArrayList<String>();
		} 
   
	if(Constants.WhatsHotists_jsonArray.length()>0)
	{
		/*JSONObject jObject = new JSONObject(result);
		JSONObject jsonDataobj =jObject.getJSONObject("DATA");
		Constants.str_points_incart=""+jsonDataobj.getString("points");*/
		
		for (int i = 0; i <Constants.WhatsHotists_jsonArray.length(); i++) {
			try {
				JSONObject jObject = Constants.WhatsHotists_jsonArray.getJSONObject(i);
				if(jObject.has("NAME"))
				{
					listingName.add(jObject.getString("NAME"));
				}
				else
				{
					listingName.add("null");
				}
			 	if(jObject.has("PRODUCTID"))
				{
					listProduct_id.add(jObject.getString("PRODUCTID"));
				}
			 	else
			 	{
			 		listProduct_id.add("0");
			 	}
				/*if(jObject.has("IMAGEURL"))
				{
					listProduct_img.add(jObject.getString("IMAGEURL"));
				}
				else
				{
					listProduct_img.add(jObject.getString("0"));
				}*/
				
		 		
				if(jObject.has("IMAGEURL"))
				{
					listProduct_img.add(jObject.getString("IMAGEURL"));
				}
				else
				{
					listProduct_img.add("0");
				}
				
				if(jObject.has("DESCRIPTION"))
				{
					listProduct_dec.add(jObject.getString("DESCRIPTION"));
				}
				else
				{
					listProduct_dec.add("null");
				}
				
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (page == 1) {
			mAdapter = new GridviewAdapter(this, listingName, listProduct_id);
			grid.setAdapter(mAdapter);
		} else {

			int currentPosition = grid.getFirstVisiblePosition();

			/*System.out.println("value" + currentPosition + ":"
					+ listFlag.size());*/
			grid.setSelection(currentPosition + 4);
		}
	}
	
	else
	{
		AlertDialog.Builder altDialog = new AlertDialog.Builder(WhatsHotActivity.this);
		 altDialog.setMessage("There are no items  in WhatsHot"); 
		altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				startActivity(new Intent(WhatsHotActivity.this,DashBorad.class));
			}
		});
		altDialog.show();
	}
		 
		/*listingName.add("Salvatore Ferragamo W chain Tote Safari");
		listingName.add("Salvatore Ferragamo W chain Tote Safari");
		listingName.add("Salvatore Ferragamo W chain Tote Safari");
		listingName.add("Salvatore Ferragamo W chain Tote Safari");
		listingName.add("Salvatore Ferragamo W chain Tote Safari");*/

		
	/*	listProduct_id.add(R.drawable.bag3);
		listProduct_id.add(R.drawable.bag3);
		listProduct_id.add(R.drawable.bag3);
		listProduct_id.add(R.drawable.bag3);
		listProduct_id.add(R.drawable.bag3); */
 
		/*listFlag.add(R.drawable.bag3);
		listFlag.add(R.drawable.bag3);
		listFlag.add(R.drawable.bag3);
		listFlag.add(R.drawable.bag3);
		listFlag.add(R.drawable.bag3);*/
 
		
	}
	
	@SuppressLint("InflateParams")
	public class GridviewAdapter extends BaseAdapter {
		private ArrayList<String> listnam;
		@SuppressWarnings("unused")
		private ArrayList<String> listId;
		private Activity activity;

		public GridviewAdapter(Activity activity,
				ArrayList<String> listCountry, ArrayList<String> listFlag) {
			super();
			this.listnam = listCountry;
			this.listId = listFlag;
			this.activity = activity;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listnam.size();
		}

		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return listnam.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
//			ViewHolder view;
//			LayoutInflater inflator = activity.getLayoutInflater();
//			System.out.println("position" + position + ":" + (getCount() - 1));
			/*if (position == getCount() - 1) {

				Toast.makeText(WhatsHotActivity.this, "page" + page,
						Toast.LENGTH_LONG).show();
				// if(page==1){
				page++;
//				prepareList();
	
				new WhatsListing().execute();

			}*/
			View grid;
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {

				grid = new View(activity);
				grid = inflater.inflate(R.layout.row_grid, null);
				LinearLayout ltop_lay = (LinearLayout) grid
						.findViewById(R.id.llay_top);
				LinearLayout lltop_lay1 = (LinearLayout) grid
						.findViewById(R.id.lllay_top1);
				@SuppressWarnings("unused")
				LinearLayout lltop_lay2 = (LinearLayout) grid
						.findViewById(R.id.lllay_top2);

				RelativeLayout bott_rrr = (RelativeLayout) grid
						.findViewById(R.id.bott_rrr);

				bott_rrr.setVisibility(View.GONE);

				// lltop_lay2.setBackgroundColor(Login.bg_color);
				ltop_lay.setBackgroundColor(Login.bg_color);
				lltop_lay1.setBackgroundColor(Login.bg_color);
				bott_rrr.setBackgroundColor(Login.bg_color);

				TextView textView = (TextView) grid
						.findViewById(R.id.txt_title);
				
				ImageView imageView = (ImageView) grid
						.findViewById(R.id.web_image);
				textView.setText(listnam.get(position)); 
//				imageView.setImageResource(listFlag.get(position));
				
				
				
			

				// download and display image from url
				String url="";
				if(listProduct_img.get(position).contains("http"))
	 			{
					url=listProduct_img.get(position).replace(" ", "%20");
				}
				else
				{
					url=Constants.prouducts_url+listProduct_img.get(position).replace(" ", "%20");
				}
				
				Picasso.with(getApplicationContext())
				.load(url.replace("\'", "%20").trim())
				.placeholder(R.drawable.login_logo)
				.error(R.drawable.bag3).fit().into(imageView);
				
				/*ImageLoader imageLoader = ImageLoader.getInstance();
				DisplayImageOptions options = new DisplayImageOptions.Builder()
						.cacheInMemory(true).cacheOnDisc(true)
						.resetViewBeforeLoading(true)
						.showImageForEmptyUri(R.drawable.bag3)
						.showImageOnFail(R.drawable.bag3)
						.showImageOnLoading(R.drawable.bag3).build();
				
				imageLoader.displayImage(
						url.replace("\'", "%20")
								.trim(), imageView, options);*/

				textView.setTypeface(Constants.ProximaNova_Regular);
			
			} else {
				grid = (View) convertView; 
			}

			return grid;
		}

	}

	public class CustomGrid1 extends BaseAdapter {
		private Context mContext;
		private final String[] web;
		private final int[] Imageid;

		public CustomGrid1(Context c, String[] web, int[] Imageid) {
			mContext = c;
			this.Imageid = Imageid;
			this.web = web;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return web.length;
		}

		@Override
		public Object getItem(int position) {
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
		public View getView(int position, View convertView, ViewGroup parent) {
			View grid;
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {

				grid = new View(mContext);
				grid = inflater.inflate(R.layout.row_grid, null);
				LinearLayout ltop_lay = (LinearLayout) grid
						.findViewById(R.id.llay_top);
				LinearLayout lltop_lay1 = (LinearLayout) grid
						.findViewById(R.id.lllay_top1);
				@SuppressWarnings("unused")
				LinearLayout lltop_lay2 = (LinearLayout) grid
						.findViewById(R.id.lllay_top2);

				RelativeLayout bott_rrr = (RelativeLayout) grid
						.findViewById(R.id.bott_rrr);

				bott_rrr.setVisibility(View.GONE);

				// lltop_lay2.setBackgroundColor(Login.bg_color);
				ltop_lay.setBackgroundColor(Login.bg_color);
				lltop_lay1.setBackgroundColor(Login.bg_color);
				bott_rrr.setBackgroundColor(Login.bg_color);

				TextView textView = (TextView) grid
						.findViewById(R.id.txt_title);
				ImageView imageView = (ImageView) grid
						.findViewById(R.id.web_image);
				textView.setText(web[position]);
				imageView.setImageResource(Imageid[position]);

				textView.setTypeface(Constants.ProximaNova_Regular);
			
			} else {
				grid = (View) convertView;
			}

			return grid;
		}
	}

	public class WhatsHotAdapter extends ArrayAdapter<ItemsObj> {

		private Activity activity;
		private List<ItemsObj> items;
		@SuppressWarnings("unused")
		private ItemsObj objBean;
		@SuppressWarnings("unused")
		private int row;
		Bitmap bimg;
		String strQty = "0";

		public WhatsHotAdapter(Activity act, int resource,
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
		public View getView(int position, View convertView, ViewGroup parent) {
			View grid;
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {

				grid = new View(activity);
				grid = inflater.inflate(R.layout.row_grid, null);
				LinearLayout ltop_lay = (LinearLayout) grid
						.findViewById(R.id.llay_top);
				LinearLayout lltop_lay1 = (LinearLayout) grid
						.findViewById(R.id.lllay_top1);
				@SuppressWarnings("unused")
				LinearLayout lltop_lay2 = (LinearLayout) grid
						.findViewById(R.id.lllay_top2);

				RelativeLayout bott_rrr = (RelativeLayout) grid
						.findViewById(R.id.bott_rrr);

				bott_rrr.setVisibility(View.GONE);

				// lltop_lay2.setBackgroundColor(Login.bg_color);
				ltop_lay.setBackgroundColor(Login.bg_color);
				lltop_lay1.setBackgroundColor(Login.bg_color);
				bott_rrr.setBackgroundColor(Login.bg_color);

				TextView textView = (TextView) grid
						.findViewById(R.id.txt_title);
				ImageView imageView = (ImageView) grid
						.findViewById(R.id.web_image);
				textView.setText(web[position]);
				imageView.setImageResource(imageId[position]);

				textView.setTypeface(Constants.ProximaNova_Regular);

				/*
				 * GradientDrawable gd = (GradientDrawable)
				 * lltop_lay2.getBackground().getCurrent();
				 * gd.setColor(Color.parseColor("#e4eaee")); gd.setStroke(1,
				 * Color.GREEN, 0, 0);
				 */

			} else {
				grid = (View) convertView;
			}

			return grid;
		}

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub 
		super.onBackPressed();
		startActivity(new Intent(WhatsHotActivity.this, DashBorad.class));
		WhatsHotActivity.this.finish();
	}
}
