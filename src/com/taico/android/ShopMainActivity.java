package com.taico.android;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.squareup.picasso.Picasso;
import com.taico.android.RangeSeekBar.OnRangeSeekBarChangeListener;
import com.taico.android.obj.ItemsObj;
import com.taico.android.util.Utility;

public class ShopMainActivity extends Activity {
	GridView grid,grid1,search_gridview;
	String[] web = { "Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",
			"Salvatore Ferragamo W chain Tote Safari",

	};
	int[] imageId = { R.drawable.bag3, R.drawable.bag3, R.drawable.bag3,
			R.drawable.bag3, R.drawable.bag3,

	};
	GridviewAdapter mAdapter; 
	GoGridviewAdapter GomAdapter; 
	SearchGridviewAdapter search_Adapter;
	Button btn_home,search_icn_btn,menus;
	InputStream is = null;
	String line = "",str_sort="",sort_values="",str_range="5 - 3006",str_serarch_tex="",results="",str_sort_name="High to Low";
	EditText edit_from,edit_to,search_txt;
	int start_no=0,search_page_start_no=0,go_start=1,def_start_no=1; 
	int page=1,page1=1,Start_no=1,Start_no1=0,search_page=1,go_page=1,item_counts=0,item_counts1=0,item_counts2=0,tot_cnt=0;;

	ArrayList<ItemsObj> shopslist_array_data;
	ArrayList<ItemsObj> shopslist_array_data1;
	Utility util;
	ViewGroup layout;
	RelativeLayout favLayout, cartLayout, top_lay, top_rrr,search_lay,rrr_cat,rrr_shp,logo_rrr;
	ImageView menu,imageView1;
	TextView textView1, textView2, tv_cat_menu, txt_pr, txt_prval, txt_sorts,txt_go,txt_wish_cnt,txt_crt_cnt,bottomtxt,search_icon;
	LinearLayout id_sort,lll_show_logo;
	private ArrayList<String> listingName;
	private ArrayList<String> listProduct_id;
	private ArrayList<String> listProduct_img;
	private ArrayList<Integer> listProduct_price;
	private ArrayList<Integer> listProduct_admin_price;
	private ArrayList<String> listProduct_dec;
	 
	private ArrayList<String> search_listingName;
	private ArrayList<String> search_listProduct_id;
	private ArrayList<String> search_listProduct_img;
	private ArrayList<Integer> search_listProduct_price;
	private ArrayList<Integer> search_listProduct_admin_price;
	@SuppressWarnings("unused")
	private ArrayList<String> search_listProduct_dec;
	Boolean search_flg=false, men_flg=false,load_flg=true,load_flg1=true,load_flg2=true; 
	   LinearLayout menlayout;
	    Animation animationFadeIn,animationtop,animation;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_grid);

//		Constants.parients_id.clear();
//		Constants.result_json.clear();
//		Constants.parients_id.add("1");
		  animationtop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
		 
		util = new Utility(ShopMainActivity.this);
//		str_range =Constants.CLIENTMINPOINTS+" - "+Constants.CLIENTMAXPOINTS;
		btn_home = (Button) findViewById(R.id.btn_home);
		menus= (Button) findViewById(R.id.menus);
		search_icon = (TextView) findViewById(R.id.search_icon);
		search_icn_btn = (Button) findViewById(R.id.search_icn_btn);
		search_icon.setVisibility(View.VISIBLE);
		search_icn_btn.setVisibility(View.VISIBLE); 
		
//		edit_from = (EditText) findViewById(R.id.edit_from);
//		edit_to = (EditText) findViewById(R.id.edit_to);
//		search_txt = (EditText) findViewById(R.id.search_txt);
		
		menu = (ImageView) findViewById(R.id.menu);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		search_lay = (RelativeLayout) findViewById(R.id.search_lay);
		menlayout= (LinearLayout) findViewById(R.id.menlayout); 
		menlayout.setVisibility(View.GONE);
		
		/*menu.setVisibility(View.INVISIBLE);
		btn_home.setVisibility(View.INVISIBLE); 
		favLayout.setVisibility(View.INVISIBLE);
		cartLayout.setVisibility(View.INVISIBLE); */
		
		 txt_wish_cnt = (TextView) findViewById(R.id.txt_wish_cnt1);
			txt_crt_cnt = (TextView) findViewById(R.id.txt_crt_cnt1);
			txt_wish_cnt.setText(Constants.wishlistcount);
			txt_crt_cnt.setText(Constants.itemCount); 
			bottomtxt= (TextView) findViewById(R.id.bottomtxt);
			txt_wish_cnt.setText(Constants.wishlistcount);
			txt_crt_cnt.setText(Constants.itemCount); 
			txt_wish_cnt.setTypeface(Constants.ProximaNova_Regular);
			txt_crt_cnt.setTypeface(Constants.ProximaNova_Regular); 

		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		tv_cat_menu = (TextView) findViewById(R.id.tv_cat_menu);
		
		rrr_cat = (RelativeLayout) findViewById(R.id.rrr_cat);
		rrr_shp = (RelativeLayout) findViewById(R.id.rrr_shp);
		
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			rrr_shp.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			rrr_shp.setVisibility(View.VISIBLE);
		}
		else
		{
			rrr_shp.setVisibility(View.GONE);
		}
		
		/** Change the app background color and font color in application*/
		if(Constants.GETBUTTONCOLOR.length()>0&&Constants.GETBUTTONFONTCOLOR.length()>0)
		{
			tv_cat_menu.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
			rrr_cat.setBackgroundColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			tv_cat_menu.setBackgroundColor(Color.parseColor("#"+Constants.GETBUTTONCOLOR));
			
			
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
			tv_cat_menu.setTextColor(Color.WHITE); 
			tv_cat_menu.setBackgroundResource(R.color.orange);
			rrr_cat.setBackgroundResource(R.color.orange);
			
			txt_wish_cnt.setTextColor(Color.WHITE);
			txt_crt_cnt.setTextColor(Color.WHITE);
			txt_wish_cnt.setBackgroundResource(R.drawable.red_circle);
			txt_crt_cnt.setBackgroundResource(R.drawable.red_circle); 
			
		}
		 
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
		  animation = AnimationUtils.loadAnimation(ShopMainActivity.this, R.anim.slide_in_top);
  	  animation.setAnimationListener(new AnimationListener() {
			   
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				imageView1.setVisibility(View.INVISIBLE);  
		    	  logo_rrr.setVisibility(View.INVISIBLE);   
//		    	  btn_home.setVisibility(View.INVISIBLE); 
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
	
//    }
//  }, 3000);
	
	
	
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
		
		
		if (util.IsNetConnected(ShopMainActivity.this)) {
			new Getuserpoints().execute();
	 } else {
	 util.showAlertNoInternetService(ShopMainActivity.this);
	 } 

		txt_wish_cnt.setText(Constants.wishlistcount);
		txt_crt_cnt.setText(Constants.itemCount);
		txt_wish_cnt.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt.setTypeface(Constants.ProximaNova_Regular);
		bottomtxt.setTypeface(Constants.ProximaNova_Regular);
		
		btn_home.setText(R.string.shop);  
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		btn_home.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShopMainActivity.this,DashBorad.class));
				ShopMainActivity.this.finish(); 

			}
		});   
		   
		menus.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				menlayout.removeAllViews();
				if(men_flg)
				{
					men_flg=false; 
					menlayout.setVisibility(View.GONE);
				
					
				}else
				{
					menlayout.setVisibility(View.VISIBLE);
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
							startActivity(new Intent(ShopMainActivity.this, Home.class));
							ShopMainActivity.this.finish();
						}
					});
					
					tv_menu1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(ShopMainActivity.this, DashBorad.class));
							ShopMainActivity.this.finish();
						}
					});
					
					tv_menu2.setOnClickListener(new OnClickListener() {
									 
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(ShopMainActivity.this, Login.class));
										ShopMainActivity.this.finish();
									}
								});
					
					menlayout.addView(mLinearView); 
				}
			}
		});
		
		top_lay = (RelativeLayout) findViewById(R.id.ray_header); 

		top_lay.setBackgroundColor(Login.bg_color);

		 top_rrr = (RelativeLayout) findViewById(R.id.top_rrr);

		top_rrr.setBackgroundColor(Login.bg_color);

		grid = (GridView) findViewById(R.id.gridview);
		grid.setBackgroundColor(Login.bg_color);
		
		grid1 = (GridView) findViewById(R.id.gridview1);
		grid1.setBackgroundColor(Login.bg_color);
		
		search_gridview = (GridView) findViewById(R.id.search_gridview);
		search_gridview.setBackgroundColor(Login.bg_color);
		
		
		search_gridview.setVisibility(View.GONE);
		grid1.setVisibility(View.GONE);
		grid.setVisibility(View.VISIBLE);

	
		Start_no = 1;

		shopslist_array_data = new ArrayList<ItemsObj>();
		shopslist_array_data1 = new ArrayList<ItemsObj>();

		/** Default Product listing functionality logic  */
		 if ( util.IsNetConnected(ShopMainActivity.this)) {
			 search_gridview.setVisibility(View.GONE);
			 grid1.setVisibility(View.GONE);
				grid.setVisibility(View.VISIBLE);
				def_start_no=1;
				//		new ShopsListing().execute();
			 new ProductListing().execute(); 
//				webserviceCall() ;
 
		 } else {
		 util.showAlertNoInternetService(ShopMainActivity.this);
		 }
		 
		  
		  
//		 grid.setOnScrollListener(new OnScrollListener(){
//			    @Override
//			    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
//			       {
//			        if(firstVisibleItem + visibleItemCount >= totalItemCount){
//			            // End has been reached
//			        	
////			        	if(bottomtxt.getVisibility()==View.VISIBLE)
////			        	{
////			        		bottomtxt.setVisibility(View.GONE);
////			        	}
////			        	else 
////			        	{
//////			        		bottomtxt.setVisibility(View.VISIBLE);
////			        	}
//			        	
//			        }
//			    }
//
//			    @Override
//			    public void onScrollStateChanged(AbsListView view, int scrollState){
//
//			    }
//			});
		 
		 
		
		txt_pr = (TextView) findViewById(R.id.txt_pr);
		txt_prval = (TextView) findViewById(R.id.txt_prval);
//		txt_sorts = (TextView) findViewById(R.id.txt_sorts);
//		txt_go = (TextView) findViewById(R.id.txt_go);
		
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
		tv_cat_menu.setTypeface(Constants.ProximaNova_Regular);
		txt_pr.setTypeface(Constants.ProximaNova_Regular);
		txt_prval.setTypeface(Constants.ProximaNova_Regular);
		
		tv_cat_menu.setOnClickListener(new OnClickListener() {
			
	 		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Constants.intentFlag = 1;
				Constants.parients_id.clear(); 
				Constants.result_json.clear();
				Constants.Level=1;
				startActivity(new Intent(ShopMainActivity.this,Shops.class)); 
				
//				startActivity(new Intent(ShopMainActivity.this,CategoriesMainList.class));
 
//				startActivity(new Intent(ShopMainActivity.this,CategoriesList.class)); 
			}
		});

		// CustomGrid adapter = new CustomGrid(ShopMainActivity.this, web,
		// imageId);

//		grid.setAdapter(adapter);
		
		/** Default Product listing click functionality logic  */
		grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Constants.productID=listProduct_id.get(position);
				if(Constants.productID.length()>0)
				{
//					Toast.makeText(getApplicationContext(), ""+Constants.productID, Toast.LENGTH_LONG).show();
					startActivity(new Intent(ShopMainActivity.this, Details.class));
				}

			}
		});
		 
		/** select range and sorts Product listing click functionality logic  */
		grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Constants.productID=listProduct_id.get(position);
				if(Constants.productID.length()>0)
				{
					startActivity(new Intent(ShopMainActivity.this, Details.class));
				}

			}
		}); 

		/**  searching  Product listing click functionality logic  */
		search_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("search_listProduct_id before"+search_listProduct_id);
				Constants.productID=search_listProduct_id.get(position);
				System.out.println("search_listProduct_id after"+Constants.productID); 
				if(Constants.productID.length()>0)
				{
					startActivity(new Intent(ShopMainActivity.this, Details.class));
				}

			}
		});
		// add RangeSeekBar to pre-defined layout
		layout = (ViewGroup) findViewById(R.id.layout);
//		Example usage as Integer range

		RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(500, 1000,
				this);
		seekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {
			@Override
			public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar,
					Integer minValue, Integer maxValue) {
				// handle the changed range values
				txt_prval.setText("" + minValue + " - " + maxValue);
			}
		});

		layout.addView(seekBar);
		
		
		// Example usage as Date range create RangeSeekBar as Date range between 1950-12-01 and now
	/*	Date minDate = new SimpleDateFormat("yyyy-MM-dd").parse("1950-12-01");
		Date maxDate = new Date();
		RangeSeekBar<Long> seekBar = new RangeSeekBar<Long>(minDate.getTime(), maxDate.getTime(), context);
		seekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Long>() {
		        @Override
		        public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Long minValue, Long maxValue) {
		                // handle changed range values
		                Log.i(TAG, "User selected new date range: MIN=" + new Date(minValue) + ", MAX=" + new Date(maxValue));
		        }
		});

		// add RangeSeekBar to pre-defined layout
		ViewGroup layout = (ViewGroup) findViewById(<your-layout-id>);
		layout.addView(seekBar);
	*/
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
				startActivity(new Intent(ShopMainActivity.this,
						WishListActivity.class));
				ShopMainActivity.this.finish();
			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShopMainActivity.this,
						CartActvity.class));
				ShopMainActivity.this.finish();

			}
		});

		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShopMainActivity.this, DashBorad.class));
				ShopMainActivity.this.finish();

			}
		});

		search_icn_btn.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				LayoutInflater inflater = getLayoutInflater();
				View mLinearView = (View) inflater.inflate(R.layout.search_layout,
  						null);

				// displaying alert dialog with list of
				// numbers
				final Dialog	alertDialog1 = new Dialog(ShopMainActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
				alertDialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
				alertDialog1.setContentView(mLinearView);
 
				search_txt = (EditText)mLinearView.findViewById(R.id.search_txt);
				edit_from = (EditText) mLinearView.findViewById(R.id.edit_from);
				edit_to = (EditText)mLinearView.findViewById(R.id.edit_to); 
				txt_go = (TextView)mLinearView.findViewById(R.id.txt_go);
				txt_sorts = (TextView)mLinearView.findViewById(R.id.txt_sorts);
				TextView txt_fromw = (TextView)mLinearView.findViewById(R.id.txt_from);
				TextView	txt_tow  = (TextView)mLinearView.findViewById(R.id.txt_to);
				txt_fromw.setTypeface(Constants.ProximaNova_Regular);
				txt_tow.setTypeface(Constants.ProximaNova_Regular);
				txt_sorts.setText(str_sort_name);
				Button dialog_btn_bak = (Button)mLinearView.findViewById(R.id.dialog_btn_bak);
				Button	search_Btn = (Button)mLinearView.findViewById(R.id.search_Btn);
				 
				dialog_btn_bak.setTypeface(Constants.ProximaNova_Regular);
				txt_sorts.setTypeface(Constants.ProximaNova_Regular);
				edit_from.setTypeface(Constants.ProximaNova_Regular);
				edit_to.setTypeface(Constants.ProximaNova_Regular);
				txt_go.setTypeface(Constants.ProximaNova_Regular);
				search_txt.setTypeface(Constants.ProximaNova_Regular);
				
				RelativeLayout rel_high_to_low = (RelativeLayout)mLinearView.findViewById(R.id.rel_high_to_low);
				RelativeLayout rel_low_to_high = (RelativeLayout)mLinearView.findViewById(R.id.rel_low_to_high);
				  id_sort = (LinearLayout)mLinearView.findViewById(R.id.id_sort); 
				  
				TextView txt_high_to_low = (TextView)mLinearView.findViewById(R.id.txt_high_to_low);
				TextView txt_low_to_high = (TextView)mLinearView.findViewById(R.id.txt_low_to_high);
				txt_high_to_low.setTypeface(Constants.ProximaNova_Regular);
				txt_low_to_high.setTypeface(Constants.ProximaNova_Regular);
				
				
				rel_high_to_low.setOnClickListener(new OnClickListener() {
										
										@Override
										public void onClick(View v) {
											// TODO Auto-generated method stub
											
											Start_no=1;
											go_page=1;
											go_start=1;
											
											search_gridview.setVisibility(View.GONE);
											grid.setVisibility(View.GONE);
											grid1.setVisibility(View.VISIBLE);
											
											alertDialog1.dismiss();
											str_sort_name="High to Low";
											txt_sorts.setText(str_sort_name);
											
											str_sort="desc";
											id_sort.setVisibility(View.GONE);
											/*if(position==0)
											{
												str_sort="desc";
											} else if(position==1)
											{*/
//												str_sort="asc";
//											}  
											if ( util.IsNetConnected(ShopMainActivity.this)) {
												alertDialog1.dismiss();
//												sort_values="&sortOrder="+str_sort;
												sort_values="&pointRange="+edit_from.getText().toString()+" - "+edit_to.getText().toString()+"&sortOrder="+str_sort; 
													 new ProductListing1().execute();

												 } else {
												 util.showAlertNoInternetService(ShopMainActivity.this);
												 }
											
										}
									});
					
				rel_low_to_high.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							Start_no=1;
							go_page=1;
							go_start=1;
							
							search_gridview.setVisibility(View.GONE);
							grid.setVisibility(View.GONE);
							grid1.setVisibility(View.VISIBLE);
							alertDialog1.dismiss();
							str_sort_name="Low to High"; 
							txt_sorts.setText(str_sort_name);
							id_sort.setVisibility(View.GONE);
							str_sort="asc";
//							}  
							if ( util.IsNetConnected(ShopMainActivity.this)) {
								alertDialog1.dismiss();
//								sort_values="&sortOrder="+str_sort;
								sort_values="&pointRange="+edit_from.getText().toString()+" - "+edit_to.getText().toString()+"&sortOrder="+str_sort; 
									 new ProductListing1().execute();

								 } else {
								 util.showAlertNoInternetService(ShopMainActivity.this);
								 }
						}
					});
				
				dialog_btn_bak.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog1.dismiss();
					}
				});
				search_Btn.setTypeface(Constants.ProximaNova_Regular);
				search_Btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Start_no = 1;
						search_page_start_no=0;
						search_page=1;
					
						str_serarch_tex = search_txt.getText().toString();
						if (str_serarch_tex.length() > 0) {
							search_gridview.setVisibility(View.VISIBLE);
							grid1.setVisibility(View.GONE);
							grid.setVisibility(View.GONE);
							if (util.IsNetConnected(ShopMainActivity.this)) {
								alertDialog1.dismiss();
								new ProductSearchListing().execute();
								
								InputMethodManager imm = (InputMethodManager) ShopMainActivity.this
										.getSystemService(Context.INPUT_METHOD_SERVICE);
								imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
										0);

							} else {
								util.showAlertNoInternetService(ShopMainActivity.this);
							}
						} else {
							util.inputValidation("Enter search keyword");
						}

					}
				});
				
				
			     /** Searching functionality logic  */
				search_txt.setOnEditorActionListener(new OnEditorActionListener() {
				    @Override
				    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_SEARCH) {
							// do your stuff here too
							Start_no = 1;
							search_page_start_no=0;
							search_page=1;
							
							str_serarch_tex = search_txt.getText().toString();
							if (str_serarch_tex.length() > 0) {
								search_gridview.setVisibility(View.VISIBLE);
								grid1.setVisibility(View.GONE);
								grid.setVisibility(View.GONE);
								if (util.IsNetConnected(ShopMainActivity.this)) {
									alertDialog1.dismiss();
									new ProductSearchListing().execute();
									
									InputMethodManager imm = (InputMethodManager) ShopMainActivity.this
											.getSystemService(Context.INPUT_METHOD_SERVICE);
									imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
											0);

								} else {
									util.showAlertNoInternetService(ShopMainActivity.this);
								}
							} else {
								util.inputValidation("Enter search keyword");
								
							}

						}
				        return false;
				    }
				});
				
				
				/** Product listing with range functionality logic  */
				txt_go.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub 
						 
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//						imm.hideSoftInputFromWindow(search_txt.getWindowToken(), 0);
						imm.hideSoftInputFromWindow(null, 0);
						
						Start_no=1;
						go_page=1;
						go_start=1;
						str_range=edit_from.getText().toString()+" - "+edit_to.getText().toString();
				 		if ( util.IsNetConnected(ShopMainActivity.this)) {
							
							search_gridview.setVisibility(View.GONE);
							grid.setVisibility(View.GONE);
							grid1.setVisibility(View.VISIBLE);
							if(edit_from.getText().toString().length()<0||edit_to.getText().toString().length()<0)
							{
								util.inputValidation("Enter from and to value");
							}
							else
							{
								alertDialog1.dismiss();
								sort_values="&pointRange="+edit_from.getText().toString()+" - "+edit_to.getText().toString()+"&sort="+str_sort; 
								
								 new ProductListing1().execute();
								 
								 /*InputMethodManager imm = (InputMethodManager) ShopMainActivity.this
											.getSystemService(Context.INPUT_METHOD_SERVICE);
									imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
											0);*/
								
							}
							
							

						 } else {
						 util.showAlertNoInternetService(ShopMainActivity.this);
						 }
					}
				});
				
				/** Product listing with sorts functionality logic  */
				txt_sorts.setOnClickListener(new OnClickListener() {
					 
					@SuppressLint("InflateParams")
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						
						if(id_sort.getVisibility()==View.VISIBLE)
						{
							id_sort.setVisibility(View.GONE);
						}
						else if(id_sort.getVisibility()==View.GONE)
						{
							id_sort.setVisibility(View.VISIBLE);  
						}
						/*Start_no=1;
						go_page=1;
						go_start=1;
						ArrayList<String> sorts = new ArrayList<String>();
						sorts.clear();
						sorts.add("High to Low");
						sorts.add("Low to High");
						
						search_gridview.setVisibility(View.GONE);
						grid.setVisibility(View.GONE);
						grid1.setVisibility(View.VISIBLE);

						LayoutInflater inflater = getLayoutInflater();
						View convertView = (View) inflater.inflate(R.layout.dialog,
								null);
						
						// displaying alert dialog with list of numbers
						final Dialog	alertDialog = new Dialog(ShopMainActivity.this);
						alertDialog.setContentView(convertView);

						alertDialog.setTitle("select");
		 
						ListView lv = (ListView) convertView
								.findViewById(R.id.listView);
		 
						ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
								ShopMainActivity.this,
								android.R.layout.simple_list_item_1, sorts);
						lv.setAdapter(adapter1);
						lv.setOnItemClickListener(new OnItemClickListener() {

							// TODO Auto-generated method stub

							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1,
									int position, long arg3) {
								// TODO Auto-generated method stub
								if(position==0)
								{
									str_sort="desc";
								} else if(position==1)
								{
									str_sort="asc";
								}  
								if ( util.IsNetConnected(ShopMainActivity.this)) {
									alertDialog1.dismiss();
//									sort_values="&sortOrder="+str_sort;
									sort_values="&pointRange="+edit_from.getText().toString()+" - "+edit_to.getText().toString()+"&sortOrder="+str_sort; 
										 new ProductListing1().execute();

									 } else {
									 util.showAlertNoInternetService(ShopMainActivity.this);
									 }
								
								alertDialog.dismiss();
							}
						});
		 
						alertDialog.show();*/
					}
				});
				alertDialog1.show();
				
				
				
//				search_lay.removeAllViews();
//				if(search_flg)
//				{
//					search_flg=false; 
//					search_lay.removeAllViews();
//					search_gridview.setVisibility(View.GONE);
//					grid1.setVisibility(View.GONE);
//					grid.setVisibility(View.VISIBLE); 
//					
//				}else
//				{
//					search_flg=true;
//					LayoutInflater inflater = null;
//					inflater = (LayoutInflater) getApplicationContext()
//							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//					View mLinearView = inflater.inflate(R.layout.search_layout, null);
//					search_txt = (EditText)mLinearView.findViewById(R.id.search_txt);
//					edit_from = (EditText) mLinearView.findViewById(R.id.edit_from);
//					edit_to = (EditText)mLinearView.findViewById(R.id.edit_to); 
//					txt_go = (TextView)mLinearView.findViewById(R.id.txt_go);
//					txt_sorts = (TextView)mLinearView.findViewById(R.id.txt_sorts);
//					
//					txt_sorts.setTypeface(Constants.ProximaNova_Regular);
//					edit_from.setTypeface(Constants.ProximaNova_Regular);
//					edit_to.setTypeface(Constants.ProximaNova_Regular);
//					txt_go.setTypeface(Constants.ProximaNova_Regular);
//					search_txt.setTypeface(Constants.ProximaNova_Regular);
//					search_lay.addView(mLinearView); 
//				} 
			}
		});
		
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
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
 
			txt_wish_cnt.setText(Constants.wishlistcount);
			txt_crt_cnt.setText(Constants.itemCount);
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
	
	/** Default Product listing AsyncTask functionality logic  */
	private class ProductListing extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			if(load_flg)
			{
			myProgressDialog = new ProgressDialog(ShopMainActivity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.setCancelable(false);
//			myProgressDialog.getWindow().setGravity(Gravity.BOTTOM);
			myProgressDialog.show();
//			,R.style.MyTheme
//			load_flg=false;
			bottomtxt.setVisibility(View.GONE); 
			} 
			else 
			{ 
//				myProgressDialog = new ProgressDialog(ShopMainActivity.this,R.style.MyTheme);
//				myProgressDialog.setMessage("please wait ...");
//				myProgressDialog.setCancelable(false);
//				myProgressDialog.getWindow().setGravity(Gravity.BOTTOM);
//				myProgressDialog.show(); 
				bottomtxt.setVisibility(View.VISIBLE);
			}
		} 
 
		@Override
		protected String doInBackground(String... params) {
			String uuu =Constants.MAIN_HOST
					+ "method=fnGetProducts&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&admin="
					+ Constants.admin + "&Limit=10" 
					+"&Catid="+Constants.Catid+"&start="+def_start_no;
//			+"&sortOrder="+str_sort
			System.out.println("fnGetProducts"+uuu);
			return WebServiceCalls.getJSONString(uuu);   
			
//			+"&pointRange="+str_range+"&sortBy="+str_sort
			
		/*	String ip = "http://stg.incentiveweb.com/webservices/TaicoWSController.cfc?method=fnGetProducts" +
					"&AuthToken=MTA3NjI6OTExMzQ4MTAwOTg3MjU1OjUxNzNGMkFGLTUwNTYtQTA2My1FNEIyM0E3REZGQjQ0NjI2" +
					"&clientID=5" +
					"&clientRatio=1.0" +
					"&admin=1" +
					"&Limit=10" +
					"&Catid=0"+"&start="+start_no+"&sortBy=asc"; */
  
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

					} else {
						if (page == 1) {
							listingName = new ArrayList<String>();
							listProduct_id = new ArrayList<String>();
							listProduct_img = new ArrayList<String>();
							listProduct_dec = new ArrayList<String>();
							listProduct_price = new ArrayList<Integer>();
							listProduct_admin_price = new ArrayList<Integer>();
						
						}
						JSONObject jObject = new JSONObject(result);
						JSONObject jObject1 = jObject.getJSONObject("DATA");
						JSONArray	ProductList_jsonArray = jObject1.getJSONArray("data");
											 
//						
//						if(jObject1.has("count"))
//						{
							item_counts = jObject1.getInt("count");
//						}
//						else
//						{
//							item_counts = 0;
//						}
//						prepareList(ProductList_jsonArray);
					if(ProductList_jsonArray.length()>0) 
					{
						
						/*JSONObject jObject = new JSONObject(result);
	 					JSONObject jsonDataobj =jObject.getJSONObject("DATA");
						Constants.str_points_incart=""+jsonDataobj.getString("points");*/
						 
						for (int i = 0; i <ProductList_jsonArray.length(); i++) {
							try {
								JSONObject jObject11 = ProductList_jsonArray.getJSONObject(i);
								if(jObject11.has("headline"))
								{
									listingName.add(jObject11.getString("headline"));
								}
								else
								{
									listingName.add("null");
								}
								if(jObject11.has("productID"))
								{
									listProduct_id.add(jObject11.getString("productID"));
								}
								else
								{
									listProduct_id.add("0");
								}
								if(jObject11.has("price"))
								{
									listProduct_price.add(jObject11.getInt("price"));
								}
								else
								{
									listProduct_price.add(0);
								}
								if(jObject11.has("adminPrice"))
								{ 
									listProduct_admin_price.add(jObject11.getInt("adminPrice"));
								}
								else
								{
									listProduct_admin_price.add(0); 
								}
								if(jObject11.has("imageURL"))
								{
									 
  										listProduct_img.add(jObject11.getString("imageURL").replace(" ", "%20"));
									
								}
								else
								{
									listProduct_img.add(jObject.getString("0"));
								}
								
						 		
								
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//							tot_cnt=listProduct_id.size()+tot_cnt;
						}
						
						System.out.println("arrDfault"+listProduct_id);
						System.out.println("arrDfaultimg"+listProduct_img); 
						
						if (page == 1) {
							mAdapter = new GridviewAdapter(ShopMainActivity.this, listProduct_id);
							grid.setAdapter(mAdapter);
							if(listProduct_id.size()==1){
								bottomtxt.setVisibility(View.GONE); 
							}
							} else {
								if(listProduct_id.size()==1){
									bottomtxt.setVisibility(View.GONE); 
								}
							int currentPosition = grid.getFirstVisiblePosition();
							mAdapter.notifyDataSetChanged();
							grid.setSelection(currentPosition + 4);
							}
						
//						if (page == 1) {
//							shop_lists_adapter = new ShopListAdapter(ShopMainActivity.this, listingName, listProduct_id);
//							grid.setAdapter(shop_lists_adapter);
//						} else {
//				 
//							int currentPosition = grid.getFirstVisiblePosition();
//							shop_lists_adapter.notifyDataSetChanged();
//
//							/*System.out.println("value" + currentPosition + ":"
//								+ listFlag.size());*/
//							grid.setSelection(currentPosition + 4);
//						}
					}
					else
					{
						/*AlertDialog.Builder altDialog = new AlertDialog.Builder(ShopMainActivity.this);
						 altDialog.setMessage("No items are in Shop"); 
						altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
	 						@Override
	 						public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
								startActivity(new Intent(ShopMainActivity.this,DashBorad.class));
							}
						});
						altDialog.show();*/
//						bottomtxt.setVisibility(View.GONE); 
						 
					} 
		 			}
					if(load_flg)
					{
					if (myProgressDialog.isShowing())
						myProgressDialog.dismiss();
					load_flg=false;
					}
					else
					{ 
						bottomtxt.setVisibility(View.GONE); 
					}
//					if (myProgressDialog.isShowing())
//						myProgressDialog.dismiss();
					
					if(listProduct_id.size()==1){
						bottomtxt.setVisibility(View.GONE); 
					}

				} catch (Exception e) {
					e.printStackTrace();
					item_counts = 0;
					bottomtxt.setVisibility(View.GONE); 
					if(listProduct_id.size()==1){
						bottomtxt.setVisibility(View.GONE); 
					}
//					if (myProgressDialog.isShowing())
//						myProgressDialog.dismiss();
				}

//				if (myProgressDialog.isShowing())
//					myProgressDialog.dismiss();
				// }

			} catch (Exception e) {
				item_counts = 0;
				bottomtxt.setVisibility(View.GONE); 
				if(listProduct_id.size()==1){
					bottomtxt.setVisibility(View.GONE); 
				}
//				if (myProgressDialog.isShowing())
//					myProgressDialog.dismiss();
			}

		}
	}
	/** Product Search Listing AsyncTask functionality logic  */
	private class ProductSearchListing extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			if(load_flg1){
			myProgressDialog = new ProgressDialog(ShopMainActivity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
			bottomtxt.setVisibility(View.GONE);
			}
			else 
			{
				bottomtxt.setVisibility(View.VISIBLE);
			}
		} 

		@Override
		protected String doInBackground(String... params) {
			
			String strr= Constants.MAIN_HOST
			+ "method=fnSearchProducts&AuthToken=" + Constants.AUTH_TOKEN
			+ "&clientid=" + Constants.clientID + "&clientratio="
			+ Constants.clientRatio +"&Catid="+Constants.Catid+"&Keywords="+str_serarch_tex+"&limit=20"+"&start="+search_page_start_no+"&admin="+Constants.admin+"&pointRange="+str_range+"&sort="+str_sort;
			System.out.println("fnSearchProducts "+strr);
				
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnSearchProducts&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientid=" + Constants.clientID + "&clientratio="
					+ Constants.clientRatio 
					+"&Catid="+Constants.Catid+"&Keywords="+str_serarch_tex+"&limit=10"+"&start="+search_page_start_no+"&admin="+Constants.admin+"&pointRange="+str_range+"&sortOrder="+str_sort);  
	
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {

					if (null == result || result.length() == 0) {

					} else {
						if (search_page == 1) {
							search_listingName = new ArrayList<String>();
							search_listProduct_id = new ArrayList<String>();
							search_listProduct_img = new ArrayList<String>();
							search_listProduct_dec = new ArrayList<String>();
							search_listProduct_price = new ArrayList<Integer>();
							search_listProduct_admin_price = new ArrayList<Integer>();
						 
						}
						JSONObject jObject = new JSONObject(result);
						JSONObject jObject1 = jObject.getJSONObject("DATA");
						JSONArray	ProductList_jsonArray = jObject1.getJSONArray("data");
						
						item_counts1 = jObject1.getInt("count");
//						prepareList(ProductList_jsonArray);
					if(ProductList_jsonArray.length()>0) 
					{
						
						for (int i = 0; i <ProductList_jsonArray.length(); i++) {
							try {
								JSONObject jObject11 = ProductList_jsonArray.getJSONObject(i);
								if(jObject11.has("headline"))
								{
									search_listingName.add(jObject11.getString("headline"));
								}
								else
								{
									search_listingName.add("null");
								}
								if(jObject11.has("productID"))
								{
									search_listProduct_id.add(jObject11.getString("productID"));
								}
								else
								{
									search_listProduct_id.add("0");
								}
								if(jObject11.has("price"))
								{
									search_listProduct_price.add(jObject11.getInt("price"));
								}
								else
								{
									search_listProduct_price.add(0);
								}
								if(jObject11.has("adminPrice"))
								{
									search_listProduct_admin_price.add(jObject11.getInt("adminPrice"));
								}
								else
								{
									search_listProduct_admin_price.add(0);
								}
								if(jObject11.has("imageURL"))
								{
									search_listProduct_img.add(jObject11.getString("imageURL").replace(" ", "%20"));
								}
								else
								{
									search_listProduct_img.add("0"); 
								}
								/*if(jObject11.getString("imageURL").length()>0)
								{
									search_listProduct_img.add(jObject11.getString("imageURL"));
								}
								else
								{
									search_listProduct_img.add("0");
								}*/
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						 System.out.println("search_listProduct_id"+search_listProduct_id);
						 
						if (search_page == 1) {
							search_Adapter = new SearchGridviewAdapter(ShopMainActivity.this, search_listingName);
							search_gridview.setAdapter(search_Adapter);
						} else {
				 
							int currentPosition = search_gridview.getFirstVisiblePosition();

							/*System.out.println("value" + currentPosition + ":"
									+ listFlag.size());*/
							search_gridview.setSelection(currentPosition + 4);
						}
					}
		 			}

				} catch (Exception e) {
					e.printStackTrace();
					item_counts1=0;
					bottomtxt.setVisibility(View.GONE); 
				}
				if(item_counts1==1)
				{
					bottomtxt.setVisibility(View.GONE);
				}
if(load_flg1){
				if (myProgressDialog.isShowing())
					myProgressDialog.dismiss();
				load_flg1=false;
				 }
else
{
	
	bottomtxt.setVisibility(View.GONE);
}

			} catch (Exception e) {
				item_counts1=0;
				if(load_flg1){
					if (myProgressDialog.isShowing())
						myProgressDialog.dismiss();
					load_flg1=false;
					 }
	else
	{
		bottomtxt.setVisibility(View.GONE);
	}
			}
			bottomtxt.setVisibility(View.GONE); 

		}
	} 
	
	/** Product  Listing for range and sorts AsyncTask functionality logic  */
	private class ProductListing1 extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			if(load_flg2)
			{
			myProgressDialog = new ProgressDialog(ShopMainActivity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
			bottomtxt.setVisibility(View.GONE);
			}
			else
			{
				bottomtxt.setVisibility(View.VISIBLE);
			}
		}

		@Override
		protected String doInBackground(String... params) { 

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetProducts&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&admin="
					+ Constants.admin + "&Limit=10"+"&Catid="+Constants.Catid
					+sort_values+"&Start="+go_start+"&Keywords="+str_serarch_tex);
//			+"&Start="+Start_no1
   
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {

					if (null == result || result.length() == 0) {

					} else {
						if (go_page == 1) {
							listingName.clear(); 
							listProduct_id.clear();
							listProduct_img.clear();
							listProduct_dec.clear();
							listProduct_price.clear();
							listProduct_admin_price.clear();
						
						}
						JSONObject jObject = new JSONObject(result);
						JSONObject jObject1 = jObject.getJSONObject("DATA");
						JSONArray	ProductList_jsonArray = jObject1.getJSONArray("data");
					
						item_counts2= jObject1.getInt("count");
//						prepareList(ProductList_jsonArray);
					if(ProductList_jsonArray.length()>0) 
					{
						
						/*JSONObject jObject = new JSONObject(result);
						JSONObject jsonDataobj =jObject.getJSONObject("DATA");
						Constants.str_points_incart=""+jsonDataobj.getString("points");*/
						 
						for (int i = 0; i <ProductList_jsonArray.length(); i++) {
							try {
								JSONObject jObject11 = ProductList_jsonArray.getJSONObject(i);
								if(jObject11.has("headline"))
								{
									listingName.add(jObject11.getString("headline"));
								}
								else
								{
									listingName.add("null");
								}
								if(jObject11.has("productID"))
								{
									listProduct_id.add(jObject11.getString("productID"));
								}
								else
								{
									listProduct_id.add("0");
								}
								if(jObject11.has("price"))
								{
									listProduct_price.add(jObject11.getInt("price"));
								}
								else
								{
									listProduct_price.add(0);
								}
								if(jObject11.has("adminPrice"))
								{
									listProduct_admin_price.add(jObject11.getInt("adminPrice"));
								}
								else
								{
									listProduct_admin_price.add(0);
								}
								/*if(jObject.has("IMAGEURL"))
								{
									listProduct_img.add(jObject.getString("IMAGEURL"));
								}
								else
								{
									listProduct_img.add(jObject.getString("0"));
								}*/
								
						 		
								if(jObject11.getString("imageURL").length()>0)
								{
									listProduct_img.add(jObject11.getString("imageURL"));
								}
								else
								{
									listProduct_img.add("0");
								}
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						 System.out.println("go_lists"+listProduct_id); 
						if (go_page == 1) {
							GomAdapter = new GoGridviewAdapter(ShopMainActivity.this, listingName);
							grid1.setAdapter(GomAdapter);
						} else {
				 
							int currentPosition = grid1.getFirstVisiblePosition();

							/*System.out.println("value" + currentPosition + ":"
									+ listFlag.size());*/
							grid1.setSelection(currentPosition + 4);
						}
					}
		 			}
					if(load_flg2){
						if (myProgressDialog.isShowing())
							myProgressDialog.dismiss();
						load_flg2=false;
						 }
		else
		{
			bottomtxt.setVisibility(View.GONE);
		}

				} catch (Exception e) {
					item_counts2=0;
					e.printStackTrace();
					bottomtxt.setVisibility(View.GONE); 
				}

				

			} catch (Exception e) {
				item_counts2=0;
				bottomtxt.setVisibility(View.GONE); 
				if(load_flg2){
					if (myProgressDialog.isShowing())
						myProgressDialog.dismiss();
					load_flg2=false;
					 }
	else
	{
		bottomtxt.setVisibility(View.GONE);
	}
			}

		}
	}
	
	/** default Product  Listing Adapter functionality logic  */
	@SuppressLint("InflateParams")
	public class GridviewAdapter extends BaseAdapter {
	private Activity activity;

	public GridviewAdapter(Activity activity,
	ArrayList<String> listCountry) {
	super();
	this.activity = activity;
	}

	@Override
	public int getCount() {
	// TODO Auto-generated method stub
	return listProduct_id.size();
	}

	@Override
	public String getItem(int position) {
	// TODO Auto-generated method stub
	return listProduct_id.get(position);
	}
	  
	@Override
	public long getItemId(int position) {
	// TODO Auto-generated method stub
	return 0;
	}
	/*@Override
	  public int getViewTypeCount() {

	   return getCount();
	  }
 
	  @Override
	  public int getItemViewType(int position) {

	   return position;
	  }*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder view;
	LayoutInflater inflator = activity.getLayoutInflater();
	
	System.out.println("position" + position + ":" + (getCount()-1));
	
	if(listProduct_id.size()<=item_counts)
	{
	if (position == getCount()-1) { 
			page++;
			start_no=start_no+10;
			def_start_no=def_start_no+10;
			 new ProductListing().execute();
		}
//	else
//	{
//		bottomtxt.setVisibility(View.GONE); 
//	}
	}
//	else
//	{
//		bottomtxt.setVisibility(View.GONE); 
//}  

	if (convertView == null) {
	view = new ViewHolder();
	convertView = inflator.inflate(R.layout.row_grid, null);        
	
	LinearLayout ltop_lay = (LinearLayout) convertView
			.findViewById(R.id.llay_top);
	LinearLayout lltop_lay1 = (LinearLayout) convertView
			.findViewById(R.id.lllay_top1);
	@SuppressWarnings("unused")
	LinearLayout lltop_lay2 = (LinearLayout) convertView
			.findViewById(R.id.lllay_top2);

	RelativeLayout bott_rrr = (RelativeLayout) convertView
			.findViewById(R.id.bott_rrr);

	bott_rrr.setVisibility(View.VISIBLE);

	// lltop_lay2.setBackgroundColor(Login.bg_color);
	ltop_lay.setBackgroundColor(Login.bg_color);
	lltop_lay1.setBackgroundColor(Login.bg_color);
	bott_rrr.setBackgroundColor(Login.bg_color);

	view.textView = (TextView) convertView
			.findViewById(R.id.txt_title);
	
	view.txt_title1= (TextView) convertView
			.findViewById(R.id.txt_title1);
	
	
	view.txt_title32= (TextView) convertView
			.findViewById(R.id.txt_title2);
	
	view.imageView = (ImageView) convertView
			.findViewById(R.id.web_image);
	 
	

	convertView.setTag(view);
	} else {
	view = (ViewHolder) convertView.getTag();

	}
	 
     
	view.textView.setText(listingName.get(position)); 
	view.txt_title1.setText("Points: "+listProduct_price.get(position)); 
	
	/*if(Constants.GETBUTTONFONTCOLOR.length()>0)
	{
		view.txt_title1.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
	}
	else
	{
		view.txt_title1.setTextColor(Color.parseColor("#d87822")); 
		
	}*/
	
	if(listProduct_admin_price.get(position)==0)
	{
		view.txt_title32.setText(""); 
	}
	else
	{
		view.txt_title32.setText("$ "+listProduct_admin_price.get(position)); 
	}
	
	/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			view.txt_title1.setVisibility(View.GONE);
			view.txt_title32.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			view.txt_title1.setVisibility(View.VISIBLE);
			view.txt_title32.setVisibility(View.VISIBLE);
		}
		else
		{
			view.txt_title1.setVisibility(View.GONE);
			view.txt_title32.setVisibility(View.GONE);
		}
	

	view.textView.setTypeface(Constants.ProximaNova_Regular);
	view.txt_title1.setTypeface(Constants.ProximaNova_Regular);
	view.txt_title32.setTypeface(Constants.ProximaNova_Regular);
	

	// download and display image from url
//	String url="";
	if(listProduct_img.get(position).contains("http"))
		{
//		url=listProduct_img.get(position);
		
		Picasso.with(getApplicationContext())
		.load(listProduct_img.get(position).replace("\'", "%20").trim())
		.placeholder(R.drawable.login_logo)
		.error(R.drawable.bag3).fit().into(view.imageView);  
		
		/*ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.bag3)
				.showImageOnFail(R.drawable.bag3)
				.showImageOnLoading(R.drawable.bag3).build();
		
		imageLoader.displayImage(
				listProduct_img.get(position).replace("\'", "%20").trim(), view.imageView, options);*/
		
//		System.out.println("img"+listProduct_img.get(position));
	}
	else
	{
//		url="http://stg.incentiveweb.com/products/"+listProduct_img.get(position);
		
		Picasso.with(getApplicationContext())
		.load(Constants.prouducts_url+listProduct_img.get(position).replace("\'", "%20").trim())
		.placeholder(R.drawable.login_logo)
		.error(R.drawable.bag3).fit().into(view.imageView);
		
		/*ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.resetViewBeforeLoading(true)
				.showImageForEmptyUri(R.drawable.bag3)
				.showImageOnFail(R.drawable.bag3)
				.showImageOnLoading(R.drawable.bag3).build();
		
		imageLoader.displayImage(
				"http://stg.incentiveweb.com/products/"+listProduct_img.get(position).replace("\'", "%20")
						.trim(), view.imageView, options);*/
//		System.out.println("img els"+listProduct_img.get(position));
	}

//	view.textView.setText(listProduct_id.get(position));

	return convertView;
	}
	}
	public static class ViewHolder {
	public ImageView imgViewFlag,imageView;
	public TextView txtViewTitle,textView,txt_title1,txt_title32;
	}
	
	
	/**Range and sorts*/
	@SuppressLint("InflateParams")
	public class GoGridviewAdapter extends BaseAdapter {
	private Activity activity;

	public GoGridviewAdapter(Activity activity,
	ArrayList<String> listCountry) {
	super();
	this.activity = activity;
	}

	@Override
	public int getCount() {
	// TODO Auto-generated method stub
	return listingName.size();
	}

	@Override
	public String getItem(int position) {
	// TODO Auto-generated method stub
	return listingName.get(position);
	}
	 
	@Override
	public long getItemId(int position) {
	// TODO Auto-generated method stub
	return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GoViewHolder view;
	LayoutInflater inflator = activity.getLayoutInflater();
	
	System.out.println("position" + position + ":" + (getCount()-1));
	if(listProduct_id.size()<=item_counts2)
	{
	if (position == getCount()-1) {

			go_page++;
			go_start=go_start+10;
			 new ProductListing1().execute();
		}

	}
 
	if (convertView == null) {
	view = new GoViewHolder();
	convertView = inflator.inflate(R.layout.row_grid, null);
	
	LinearLayout ltop_lay = (LinearLayout) convertView
			.findViewById(R.id.llay_top);
	LinearLayout lltop_lay1 = (LinearLayout) convertView
			.findViewById(R.id.lllay_top1);
	@SuppressWarnings("unused")
	LinearLayout lltop_lay2 = (LinearLayout) convertView
			.findViewById(R.id.lllay_top2);

	RelativeLayout bott_rrr = (RelativeLayout) convertView
			.findViewById(R.id.bott_rrr);

	bott_rrr.setVisibility(View.VISIBLE);

	// lltop_lay2.setBackgroundColor(Login.bg_color);
	ltop_lay.setBackgroundColor(Login.bg_color);
	lltop_lay1.setBackgroundColor(Login.bg_color);
	bott_rrr.setBackgroundColor(Login.bg_color);

	view.textView = (TextView) convertView
			.findViewById(R.id.txt_title);
	
	view.txt_title1= (TextView) convertView
			.findViewById(R.id.txt_title1);
	
	
	view.txt_title32= (TextView) convertView
			.findViewById(R.id.txt_title2);
	
	view.imageView = (ImageView) convertView
			.findViewById(R.id.web_image);


	convertView.setTag(view);
	} else {
	view = (GoViewHolder) convertView.getTag();

	}
	view.textView.setText(listingName.get(position)); 
	view.txt_title1.setText("Points: "+listProduct_price.get(position)); 
	
	/*if(Constants.GETBUTTONFONTCOLOR.length()>0)
	{
		view.txt_title1.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
	}
	else
	{
		view.txt_title1.setTextColor(Color.parseColor("#d87822")); 
		
	}*/
	
//	if(listProduct_admin_price.size()>0)
//	{
//	}

		
		if(listProduct_admin_price.get(position)==0)
		{
			view.txt_title32.setText(""); 
		}
		else
		{
			view.txt_title32.setText("$ "+listProduct_admin_price.get(position)); 
		}
		
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			view.txt_title1.setVisibility(View.GONE);
			view.txt_title32.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			view.txt_title1.setVisibility(View.VISIBLE);
			view.txt_title32.setVisibility(View.VISIBLE);
		}
		else
		{
			view.txt_title1.setVisibility(View.GONE);
			view.txt_title32.setVisibility(View.GONE);
		}

	view.textView.setTypeface(Constants.ProximaNova_Regular);
	view.txt_title1.setTypeface(Constants.ProximaNova_Regular);
	view.txt_title32.setTypeface(Constants.ProximaNova_Regular);

	// download and display image from url
	String url="";
	if(listProduct_img.get(position).contains("http"))
		{
		url=listProduct_img.get(position);
	}
	else
	{
		url=Constants.prouducts_url+listProduct_img.get(position);
	}
	
	Picasso.with(getApplicationContext())
	.load(url.replace("\'", "%20").trim())
	.placeholder(R.drawable.login_logo)
	.error(R.drawable.bag3).fit().into(view.imageView);
	
	/*ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheInMemory(true).cacheOnDisc(true)
			.resetViewBeforeLoading(true)
			.showImageForEmptyUri(R.drawable.bag3)
			.showImageOnFail(R.drawable.bag3)
			.showImageOnLoading(R.drawable.bag3).build();
	
	imageLoader.displayImage(
			url.replace("\'", "%20")
					.trim(), view.imageView, options);*/

//	view.textView.setText(listProduct_id.get(position));

	return convertView;
	}
	}
	public static class GoViewHolder {
	public ImageView imgViewFlag,imageView;
	public TextView txtViewTitle,textView,txt_title1,txt_title32;
	}
	
	/** SearchGridviewAdapter */
	
	@SuppressLint("InflateParams")
	public class SearchGridviewAdapter extends BaseAdapter {
	private Activity activity;

	public SearchGridviewAdapter(Activity activity,
	ArrayList<String> listCountry) {
	super();
	this.activity = activity;
	}

	@Override
	public int getCount() {
	// TODO Auto-generated method stub
	return search_listingName.size();
	}

	@Override
	public String getItem(int position) {
	// TODO Auto-generated method stub
	return search_listingName.get(position);
	}
	 
	@Override
	public long getItemId(int position) {
	// TODO Auto-generated method stub
	return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SearchViewHolder view; 
	LayoutInflater inflator = activity.getLayoutInflater();
	
	System.out.println("position" + position + ":" + (getCount()-1));
	if(search_listProduct_id.size()<=item_counts1)
	{
	if (position == getCount()-1) {

		
			search_page++;
			search_page_start_no=search_page_start_no+10;
			if(item_counts1==1)
			{
				bottomtxt.setVisibility(View.GONE);
			}else{
		 new ProductSearchListing().execute();
			}
		}
	}

	if (convertView == null) {
	view = new SearchViewHolder();
	convertView = inflator.inflate(R.layout.row_grid, null);
	
	LinearLayout ltop_lay = (LinearLayout) convertView
			.findViewById(R.id.llay_top);
	LinearLayout lltop_lay1 = (LinearLayout) convertView
			.findViewById(R.id.lllay_top1);
	@SuppressWarnings("unused")
	LinearLayout lltop_lay2 = (LinearLayout) convertView
			.findViewById(R.id.lllay_top2);

	RelativeLayout bott_rrr = (RelativeLayout) convertView
			.findViewById(R.id.bott_rrr);

	bott_rrr.setVisibility(View.VISIBLE);

	// lltop_lay2.setBackgroundColor(Login.bg_color);
	ltop_lay.setBackgroundColor(Login.bg_color);
	lltop_lay1.setBackgroundColor(Login.bg_color);
	bott_rrr.setBackgroundColor(Login.bg_color);

	view.textView = (TextView) convertView
			.findViewById(R.id.txt_title);
	
	view.txt_title1= (TextView) convertView
			.findViewById(R.id.txt_title1);
	
	
	view.txt_title32= (TextView) convertView
			.findViewById(R.id.txt_title2);
	
	view.imageView = (ImageView) convertView
			.findViewById(R.id.web_image);
	 
	     
	

	convertView.setTag(view);
	} else {
	view = (SearchViewHolder) convertView.getTag();

	}
 
//	view.textView.setText(search_listProduct_id.get(position));
	 
	view.textView.setText(search_listingName.get(position)); 
	view.txt_title1.setText("Points: "+search_listProduct_price.get(position)); 
	
	/*if(Constants.GETBUTTONFONTCOLOR.length()>0)
	{
		view.txt_title1.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
	}
	else
	{
		view.txt_title1.setTextColor(Color.parseColor("#d87822")); 
		
	}*/
//	if(search_listProduct_admin_price.size()>0)
//	{
//	}
	
		
		if(search_listProduct_admin_price.get(position)==0)
		{
			view.txt_title32.setText(""); 
		}
		else
		{
			view.txt_title32.setText("$ "+search_listProduct_admin_price.get(position)); 
		}
		
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			view.txt_title1.setVisibility(View.GONE);
			view.txt_title32.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			view.txt_title1.setVisibility(View.VISIBLE);
			view.txt_title32.setVisibility(View.VISIBLE);
		}
		else
		{
			view.txt_title1.setVisibility(View.GONE);
			view.txt_title32.setVisibility(View.GONE);
		}

	view.textView.setTypeface(Constants.ProximaNova_Regular);
	view.txt_title1.setTypeface(Constants.ProximaNova_Regular);
	view.txt_title32.setTypeface(Constants.ProximaNova_Regular);

	// download and display image from url
	String url="";
	if(search_listProduct_img.get(position).contains("http"))
		{
		url=search_listProduct_img.get(position);
	}
	else
	{
		url=Constants.prouducts_url+search_listProduct_img.get(position);
	}
	
	Picasso.with(getApplicationContext())
	.load(url.replace("\'", "%20").trim())
	.placeholder(R.drawable.login_logo)
	.error(R.drawable.bag3).fit().into(view.imageView);
	
	/*ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheInMemory(true).cacheOnDisc(true)
			.resetViewBeforeLoading(true)
			.showImageForEmptyUri(R.drawable.bag3)
			.showImageOnFail(R.drawable.bag3)
			.showImageOnLoading(R.drawable.bag3).build();
	
	imageLoader.displayImage(
			url.replace("\'", "%20")
					.trim(), view.imageView, options);*/



	return convertView;
	}
	}
	public static class SearchViewHolder {
	public ImageView imgViewFlag,imageView;
	public TextView txtViewTitle,textView,txt_title1,txt_title32;
	}
	
		

	

 	@Override
	public void onBackPressed() {
	 	// TODO Auto-generated method stub 
		super.onBackPressed(); 
//		if(Constants.flg_bak_button==1)
//		{
		page=1;
		Constants.limit=10;
		listingName.clear();
		listProduct_id .clear();
		listProduct_img.clear();
		listProduct_dec.clear();
		listProduct_price.clear();
		listProduct_admin_price.clear();
			startActivity(new Intent(ShopMainActivity.this, DashBorad.class));
//		}
//		else if(Constants.flg_bak_button==1)
//		{
//			ShopMainActivity.this.finish();
//		}
	
//		ShopMainActivity.this.finish();
	}
}
