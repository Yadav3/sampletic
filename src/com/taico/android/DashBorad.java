package com.taico.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.util.Utility;

@SuppressLint("InflateParams")
public class DashBorad extends Activity {
//LinearLayout home_layout,shops_layout,my_account_layout,whats_hot_layout,carts_layout,wish_list_layout;
TextView textView1,textView2;
	GridView grid;
	String[] web = {
			"Shop",
			"Cart",
		    "Home",
			"My account",
			"My Wish List",
			"What's hot",
			
			
	} ;

	
	int[] imageId = {
			R.drawable.home,
			R.drawable.new_wish_list,
			R.drawable.wish_list,
			R.drawable.my_account,
			R.drawable.cardashboard_cart,
			R.drawable.shop,
			
	};
	List<String> list;
	Utility util;
	ViewGroup layout;
	RelativeLayout favLayout,cartLayout,top_lay,rrr;
	ImageView imageView1; 
	
	Button menus;
	LinearLayout menlayout_hom;
	Boolean men_flg=false;
	 
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ee);
		final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
		util = new Utility(DashBorad.this);
		
		list = new ArrayList<String>(Arrays.asList(web));

		if (Constants.SHOWWISHLIST == 0) {
			list.remove("My Wish List");
		}
		/*else  if (Constants.SHOWWISHLIST == 1) {
			list.add("Wish list");
		}*/
		
		if (Constants.SHOWADDTOCARTBUTTON == 0) {
			list.remove("Cart");
		}
		/*else  if (Constants.SHOWADDTOCARTBUTTON == 1) {
			list.add("Cart");
		}*/
		
		/*if (Constants.admin.equalsIgnoreCase("0") ) {
			list.remove("My account");
		}*/
		/*else  if (Constants.admin.equalsIgnoreCase("1")) {
			list.add("My account");
		}*/
		
	
		imageView1 = (ImageView)findViewById(R.id.imageView1);
		
//		http://stg.incentiveweb.com/"directory"/images/"mainlogo"
		
		String url="";
//		http://f.tqn.com/y/graphicssoft/1/S/n/p/5/ST_animated_turkey01.gif
//		Constants.MAINLOGO ="http://f.tqn.com/y/graphicssoft/1/S/n/p/5/ST_animated_turkey01.gif";
		
		if(Constants.MAINLOGO.contains("http"))
			{ 
			url=Constants.MAINLOGO;
//			url="http://stg.incentiveweb.com/"+Constants.DIRECTORYs+"/images/"+Constants.MAINLOGO;
			
			
			Picasso.with(getApplicationContext())
			.load(url.replace("\'", "%20").replace(" ", "%20"))
			.placeholder(R.drawable.login_logo)
			.error(R.drawable.bag3).fit().into(imageView1);
			/*if(url.contains(".gif"))
			{ 
				imageView1.setImageBitmap(util.getBitmap(Constants.MAINLOGO));
			} 
			else
			{
				ImageLoader imageLoader = ImageLoader.getInstance();
				DisplayImageOptions options = new DisplayImageOptions.Builder()
						.cacheInMemory(true).cacheOnDisc(true)
						.resetViewBeforeLoading(true)
						.showImageForEmptyUri(R.drawable.login_logo)
						.showImageOnFail(R.drawable.login_logo)
						.showImageOnLoading(R.drawable.login_logo).build(); 
				
				imageLoader.displayImage(
						url.replace("\'", "%20")
								.trim(), imageView1, options);
			}*/
			
		}
		else 
		{
			url=Constants.logo_url+Constants.DIRECTORYs+"/images/"+Constants.MAINLOGO;
//			url="https://upload.wikimedia.org/wikipedia/en/b/bc/Torus_with_cross-hatched_wireframe.gif";
			Picasso.with(getApplicationContext())
			.load(url.replace("\'", "%20").replace(" ", "%20"))
			.placeholder(R.drawable.login_logo)
			.error(R.drawable.bag3).fit().into(imageView1);
			/*if(url.contains(".gif"))
			{ 
				imageView1.setImageBitmap(util.getBitmap(Constants.MAINLOGO));
			} 
			else
			{
				ImageLoader imageLoader = ImageLoader.getInstance();
				DisplayImageOptions options = new DisplayImageOptions.Builder()
						.cacheInMemory(true).cacheOnDisc(true)
						.resetViewBeforeLoading(true)
						.showImageForEmptyUri(R.drawable.login_logo)
						.showImageOnFail(R.drawable.login_logo)
						.showImageOnLoading(R.drawable.login_logo).build(); 
				
				imageLoader.displayImage(
						url.replace("\'", "%20")
								.trim(), imageView1, options);
			}*/
		}
		 
//		imageView1.startAnimation(animationFadeIn); 
		
		/*new Handler().postDelayed(new Runnable() {
		      @Override
		      public void run() {
		    	  imageView1.setVisibility(View.INVISIBLE);
		      }
		    }, 30000);*/
		
		/* if (util.IsNetConnected(DashBorad.this)) {
			 
			
		 } else {
		 util.showAlertNoInternetService(DashBorad.this);
		 }*/
		
		top_lay= (RelativeLayout)findViewById(R.id.top_lay);
		rrr= (RelativeLayout)findViewById(R.id.rrr);
		top_lay.setBackgroundColor(Login.bg_color);
		
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
		
		textView1=(TextView)findViewById(R.id.textView1);
		textView2=(TextView)findViewById(R.id.textView2);
	
		textView1.setTypeface(Constants.ProximaNova_Regular); 
		textView2.setTypeface(Constants.ProximaNova_Regular); 
		
		
		 if (util.IsNetConnected(DashBorad.this)) {
			 if(Constants.whats_ids.size()>0)
			 {
				   
			 }
			 else{
				new GetWhatsHot().execute();
			 }
						new Getuserpoints().execute();
				 } else {
				 util.showAlertNoInternetService(DashBorad.this);
				 }
			web = list.toArray(new String[0]);
		
		CustomGrid1 adapter = new CustomGrid1(DashBorad.this, web, imageId);
		grid=(GridView)findViewById(R.id.gridview);
				grid.setAdapter(adapter);
				grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                                    int position, long id) {
//		                Toast.makeText(WhatsHotActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
//						startActivity(new Intent(DashBorad1.this,Details.class));
//
		            	if(web[+ position].equalsIgnoreCase("Home"))
		            	{ 
		            		startActivity(new Intent(DashBorad.this,Home.class));
//		            		startActivity(new Intent(DashBorad.this,EntertainmentTickets.class)); 
//		            		DashBorad.this.finish();
		            	}
		            	if(web[+ position].equalsIgnoreCase("What's hot"))
		            	{
		            		startActivity(new Intent(DashBorad.this,WhatsHotActivity.class));
//		            		DashBorad.this.finish();
		            	}
		            	if(web[+ position].equalsIgnoreCase("My Wish List"))
		            	{
		            		startActivity(new Intent(DashBorad.this,WishListActivity.class));
//		            		DashBorad.this.finish(); 
		            	}
		            	if(web[+ position].equalsIgnoreCase("My account"))
		            	{
		            		startActivity(new Intent(DashBorad.this,MyAccount_History.class));
//		            		DashBorad.this.finish();
		            	}
		            	if(web[+ position].equalsIgnoreCase("Cart"))
		            	{
		            		startActivity(new Intent(DashBorad.this,CartActvity.class));
//		            		DashBorad.this.finish();
		            	}
		            	if(web[+ position].equalsIgnoreCase("Shop"))
		            	{
		            		Constants.parients_id.clear();
		            		Constants.result_json.clear();
		            		Constants.intentFlag = 1;
		            		Constants.flg_bak_button=1;
		            		Constants.Catid="0";
		            		startActivity(new Intent(DashBorad.this,ShopMainActivity.class));
//		            		DashBorad.this.finish();
		            	}
		             		
		            	 
		            }
		        });
				
				menus = (Button) findViewById(R.id.menus);
				menlayout_hom= (LinearLayout) findViewById(R.id.menlayout_hom);
				menlayout_hom.setVisibility(View.GONE);
				
				
				menus.setOnClickListener(new OnClickListener() {

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
							tv_menu1.setTypeface(Constants.ProximaNova_Regular);
							tv_menu2.setTypeface(Constants.ProximaNova_Regular);
							tv_menu1.setVisibility(View.GONE);
							View view_id1 = (View)mLinearView.findViewById(R.id.view_id1);
							view_id1.setVisibility(View.GONE); 
							
							View view_id11 = (View)mLinearView.findViewById(R.id.view_id11);
							view_id11.setVisibility(View.GONE); 
					 		
							TextView tv_hom1 = (TextView)mLinearView.findViewById(R.id.tv_hom1);
							tv_hom1.setVisibility(View.GONE); 
							
							tv_hom1.setTypeface(Constants.ProximaNova_Regular);
							tv_hom1.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									startActivity(new Intent(DashBorad.this, Home.class));
									DashBorad.this.finish();
								}
							});
							
							tv_menu2.setOnClickListener(new OnClickListener() {
										 	  
											@Override
											public void onClick(View v) {
												// TODO Auto-generated method stub
												
												startActivity(new Intent(DashBorad.this, Login.class));
												DashBorad.this.finish(); 
											}
										});
							 
							menlayout_hom.addView(mLinearView); 
						}
					}
				});
				
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		textView1.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_incart+"</strong></b></font></body></html>"));
		textView2.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_left+"</strong></b></font></body></html>"));
	
	}
	
	private class GetWhatsHot extends AsyncTask<String, Void, String> { 

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(DashBorad.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
  
		@Override
		protected String doInBackground(String... params) {
			System.out.println("fnGetWhatsHot"+Constants.MAIN_HOST+"method=fnGetWhatsHot&AuthToken="+Constants.AUTH_TOKEN+"&clientID="+Constants.clientID+"&clientRatio="+Constants.clientRatio+"&CLIENTMAXPOINTS="+Constants.CLIENTMAXPOINTS+"&CLIENTMINPOINTS="+Constants.CLIENTMINPOINTS);
			
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST+"method=fnGetWhatsHot&AuthToken="+Constants.AUTH_TOKEN+"&clientID="+Constants.clientID+"&clientRatio="+Constants.clientRatio+"&CLIENTMAXPOINTS="+Constants.CLIENTMAXPOINTS+"&CLIENTMINPOINTS="+Constants.CLIENTMINPOINTS);

 
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
						Constants.whats_ids.clear();
						JSONObject jObject = new JSONObject(result);
						Constants.WhatsHotists_jsonArray = jObject.getJSONArray("DATA");
						Constants.whats_ids.add(""+Constants.WhatsHotists_jsonArray); 
						 if(Constants.WhatsHotists_jsonArray.length()>0)
							{
								 
							} 
						 else
						 {
							 list.remove("What's hot");
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
//			myProgressDialog = new ProgressDialog(DashBorad.this);
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
						if(jObject.has("DATA"))
						{
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
						
						}
						
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
	
	public class CustomGrid1 extends BaseAdapter{
		  private Context mContext;
		  private final String[] web;
		  @SuppressWarnings("unused")
		private final int[] Imageid; 

		    public CustomGrid1(Context c,String[] web,int[] Imageid ) {
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

			@SuppressLint("NewApi")
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View grid;
				LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
		        if (convertView == null) {  
		        	
		        	grid = new View(mContext);
					grid = inflater.inflate(R.layout.eee, null);
				 
					LinearLayout lin_top=(LinearLayout)grid.findViewById(R.id.lin_top);
					@SuppressWarnings("unused")
					LinearLayout shops_layout=(LinearLayout)grid.findViewById(R.id.shops_layout);
					lin_top.setBackgroundColor(Login.bg_color);
		        	TextView textView = (TextView) grid.findViewById(R.id.hrd_mgmt_txt);
		        	textView.setText(web[position]); 
		        	
		        	textView.setTypeface(Constants.ProximaNova_Regular);  
//		        	textView.setCompoundDrawablesWithIntrinsicBounds(0, 
//		        			Imageid[position], 0, 0);
//		        	
	 	        	 	if(web[position].equalsIgnoreCase("Home"))
		        	{
			        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);

		        	}
		        	else if(web[position].equalsIgnoreCase("What's Hot"))
		        	{
			        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.new_whats_hot, 0, 0);

		        	}   
		        	else if(web[position].equalsIgnoreCase("My Wish List"))
		        	{
			        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.new_wish_list, 0, 0);

		        	}
		        	else if(web[position].equalsIgnoreCase("My Account"))
		        	{
			        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_account, 0, 0);

		        	}
		        	else if(web[position].equalsIgnoreCase("Cart"))
		        	{
			        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cardashboard_cart, 0, 0);

		        	}
		        	else if(web[position].equalsIgnoreCase("Shop"))
		        	{
			        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shop, 0, 0);

		        	}
		        } else {
		        	grid = (View) convertView;
		        }
				
				return grid;
			}
	}
	@Override
	public void onBackPressed() {
	 	// TODO Auto-generated method stub  
//		super.onBackPressed(); 
//		DashBorad.this.finish();
//		moveTaskToBack(true);

	}
}
