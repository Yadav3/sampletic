package com.taico.android;

import java.util.ArrayList;
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
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.obj.ItemsObj;
import com.taico.android.util.Utility;

@SuppressLint({ "InflateParams", "DefaultLocale" })
public class Shops extends Activity {
ArrayList<String> tabs = new ArrayList<String>();
LinearLayout tab_bar,lin_scr,menlayout,lll_show_logo;
int tab_position,size_action,screenWidth;
HorizontalScrollView hs;
RelativeLayout favLayout,cartLayout,rrr_category,logo_rrr;
Shops_ListAdapter adapter;
ListView shops_list;
Button btn_home,menus;
TextView textView1,textView2,txt_wish_cnt,txt_crt_cnt;
Utility util;
ScrollView top_scr;
Boolean  men_flg=false;
ImageView menu,imageView1;
Animation animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shops); 
		
		util = new Utility(Shops.this);
		
		menlayout= (LinearLayout) findViewById(R.id.menlayout); 
		menlayout.setVisibility(View.GONE);
		menus= (Button) findViewById(R.id.menus);
		textView1=(TextView)findViewById(R.id.textView1);
		textView2=(TextView)findViewById(R.id.textView2);
		txt_wish_cnt = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt = (TextView) findViewById(R.id.txt_crt_cnt1);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout); 
		
		rrr_category = (RelativeLayout) findViewById(R.id.rrr_category); 
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			rrr_category.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			rrr_category.setVisibility(View.VISIBLE);
		}
		else
		{
			rrr_category.setVisibility(View.GONE);
		}
		 
		txt_wish_cnt.setText(Constants.wishlistcount);
		txt_crt_cnt.setText(Constants.itemCount);
		txt_wish_cnt.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt.setTypeface(Constants.ProximaNova_Regular);
	textView1.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_incart+"</strong></b></font></body></html>"));
		
		textView2.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_left+"</strong></b></font></body></html>"));

		 
		textView1.setTypeface(Constants.ProximaNova_Regular);
		textView2.setTypeface(Constants.ProximaNova_Regular);
		
//		Toast.makeText(getApplicationContext(), ""+Constants.Level, Toast.LENGTH_LONG).show();
		txt_wish_cnt.setText(Constants.wishlistcount);
		txt_crt_cnt.setText(Constants.itemCount);
		
		tab_bar=(LinearLayout)findViewById(R.id.tab_bar);
		lin_scr=(LinearLayout)findViewById(R.id.lin_scr);
		top_scr=(ScrollView)findViewById(R.id.top_scr);
		
		
		hs=(HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
		hs.setVisibility(View.GONE);
		shops_list=(ListView)findViewById(R.id.shops_list);
		btn_home=(Button)findViewById(R.id.btn_home);
		btn_home.setText(R.string.category_menu); 
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		btn_home.setOnClickListener(new OnClickListener() {
  
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Shops.this,ShopMainActivity.class));
				Shops.this.finish(); 

			}
		});
		/*for (int i = 0; i <=9; i++) {
			tabs.add("Title"+i);
		}*/
		
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
		
		
		
		
		tabs.add("Watchs");
		tabs.add("Iphons");
		tabs.add("Bag");
		initialiseTabHost();
		
		
		if (util.IsNetConnected(Shops.this)) {
			if(Constants.intentFlag == 1){
				new CategoriesListTasks().execute();	
			}else{
				new CategoriesListTasks1().execute();
			}
			
			 } else {
			 util.showAlertNoInternetService(Shops.this);
			 }
		
		shops_list.setOnItemClickListener(new OnItemClickListener() {
 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Constants.intentFlag = 1;
				ItemsObj itemsObj = (ItemsObj) Constants.shops_history
						.get(arg2);
				/*objItem.setCategory_tit(jObject1.getString("LABEL"));
				objItem.setParientId(jObject1.getString("PARENTID"));
				objItem.setCategoryId(jObject1.getString("CATEGORYID")); */
//				Toast.makeText(getApplicationContext(), "Level :"+Constants.Level+"\n Category_tit :"+itemsObj.getCategory_tit()+"\n ParientId :"+itemsObj.getParientId()+"\n CategoryId :"+itemsObj.getCategoryId(), Toast.LENGTH_LONG).show();
				Constants.child_id=Integer.parseInt(itemsObj.getParientId());
				Constants.parentid = Integer.parseInt(itemsObj.getCategoryId());
				Constants.parients_id.add(itemsObj.getParientId().toString());  
//				Toast.makeText(getApplicationContext(), ""+Constants.parients_id, Toast.LENGTH_LONG).show();
			
				if(Constants.parentid == 13525)
				{
					
				}else if(Constants.parentid == 13775)
				{
					
				}
				else
				{
					Constants.Level++;
					startActivity(new Intent(Shops.this,Shops.class)); 
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
				// TODO Auto-generated method stub
				startActivity(new Intent(Shops.this,
						WishListActivity.class));
				Shops.this.finish();
			}
		});
 
		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Shops.this,
						CartActvity.class));
				Shops.this.finish();

			}
		});
		
		menus.setOnClickListener(new OnClickListener() {

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
							startActivity(new Intent(Shops.this, Home.class));
							Shops.this.finish();
						}
					});
					
					
					
					tv_menu1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(Shops.this, DashBorad.class));
							Shops.this.finish();
						}
					});
					
					tv_menu2.setOnClickListener(new OnClickListener() {
									  
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(Shops.this, Login.class));
										Shops.this.finish();
									}
								});
					
					menlayout.addView(mLinearView); 
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
			  animation = AnimationUtils.loadAnimation(Shops.this, R.anim.slide_in_top);
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
			top_scr.smoothScrollBy(10, 30);
		}
 
	
	@SuppressLint("DefaultLocale")
	@SuppressWarnings("unused")
	private void initialiseTabHost() {

		try {

//			runOnUiThread(new Runnable() {
//
//				@Override
//				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < tabs.size(); i++) {
						String name = tabs.get(i).substring(0, 1).toUpperCase()
								+ tabs.get(i).substring(1).toLowerCase();

						LayoutInflater inflater = null;
						inflater = (LayoutInflater) getApplicationContext()
								.getSystemService(
										Context.LAYOUT_INFLATER_SERVICE);
						View view = inflater.inflate(
								R.layout.whats_hot_row, null);
 
						final TextView title = ((TextView) view
								.findViewById(R.id.title));
						final TextView tv_bg = ((TextView) view
								.findViewById(R.id.tv_bg));

//						title.setTypeface(Constants
//								.getTf_lato_regular(PreviousCartItemsActivity.this));
//
//						title.setText(name);
//						title.setTag(name);
						tab_bar.addView(view);
						final int j = i;

						System.out.println("tab_position" + tab_position);
						/*if (tab_position == i) {
							tv_bg.setVisibility(View.VISIBLE);

						} else {
							tv_bg.setVisibility(View.GONE);

						}*/

						title.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								runOnUiThread(new Runnable() {
									@Override
									public void run() {

										tab_position = j;
										for (int k = 0; k < tab_position; k++) {

											LinearLayout viewGroup = (LinearLayout) tab_bar
													.getChildAt(k);

											size_action = size_action
													+ viewGroup.getWidth();

										}

										centerTabItem(tab_position,
												size_action, title.getWidth());
										size_action = 0;
										tab_bar.removeAllViews();
										initialiseTabHost();

//										new GetGroupChildItems()
//												.execute(tab_position);
									}
								});
							}
						});

					}
//				}
//			});

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

@SuppressWarnings("deprecation")
	public void centerTabItem(int position, int length, int length1) {

		screenWidth = Shops.this.getWindowManager()
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


private class CategoriesListTasks  extends AsyncTask<String, Void, String> {

	ProgressDialog myProgressDialog;

	protected void onPreExecute() {
		super.onPreExecute();
		myProgressDialog = new ProgressDialog(Shops.this);
		myProgressDialog.setMessage("please wait ...");
		myProgressDialog.show(); 
	}
 
	@Override
	protected String doInBackground(String... params) {
		
		if(Constants.Level==1)
		{
			Constants.parentid=1;
		}
		String cat=Constants.MAIN_HOST
				+ "method=fnGetCategories&AuthToken=" + Constants.AUTH_TOKEN
				+ "&clientID=" + Constants.clientID + "&Level="
				+ Constants.Level + "&parentid="
				+ Constants.parentid;
		System.out.println("cat"+cat); 
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetCategories&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&Level="
					+ Constants.Level + "&parentid="
					+ Constants.parentid);

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
					 Constants.shops_history.clear();
					 Constants.result_json.add(result);
					 
					JSONObject jObject = new JSONObject(result);
					if(jObject.has("DATA"))
					{
						JSONArray	Categorieslists_jsonArray = jObject.getJSONArray("DATA");
						 if(Categorieslists_jsonArray.length()>0)
							{
							 System.out.println("if result:"+result);
							 
							
								for (int j = 0; j < Categorieslists_jsonArray.length(); j++) { 
									JSONObject jObject1 = Categorieslists_jsonArray.getJSONObject(j);
									ItemsObj objItem = new ItemsObj();
								
									if(jObject1.has("LABEL"))
									{
										objItem.setCategory_tit(jObject1.getString("LABEL"));
										objItem.setParientId(jObject1.getString("PARENTID"));
										objItem.setCategoryId(jObject1.getString("CATEGORYID")); 
//										objItem.setBack_categoryId(jObject1.getString("CATEGORYID")); 
									}
									
//									objItem.setCategory_tit("Beams Seat Belt ");
									

									Constants.shops_history.add(objItem);
								}
							
								
								for (int i = 0; i < Constants.shops_history.size(); i++) {
									LayoutInflater inflater = null;
									inflater = (LayoutInflater) getApplicationContext()
											.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
									View mLinearView = inflater.inflate(R.layout.shpos_row, null);
									
									final TextView tv_txt = (TextView)mLinearView.findViewById(R.id.tv_txt);
									final TextView tv_vie1 = (TextView)mLinearView.findViewById(R.id.tv_vie1);
									final TextView tv_vie2 = (TextView)mLinearView.findViewById(R.id.tv_vie2);
									final Button button1 = (Button)mLinearView.findViewById(R.id.button1);
									final Button button2 = (Button)mLinearView.findViewById(R.id.button2);
									
								/*	Constants.child_id=Integer.parseInt(itemsObj.getParientId());
									Constants.parentid = Integer.parseInt(itemsObj.getCategoryId());
									Constants.parients_id.add(itemsObj.getParientId().toString()); */
									
								ItemsObj	objBean = Constants.shops_history.get(i);
								tv_txt.setText(objBean.getCategory_tit());
								tv_vie1.setText(objBean.getCategoryId());
								tv_vie2.setText(objBean.getParientId());
								tv_txt.setTypeface(Constants.ProximaNova_Regular); 
								 
								
								button1.setOnClickListener(new OnClickListener() {
												
												@Override
												public void onClick(View v) {
													// TODO Auto-generated method stub
													Constants.intentFlag = 1;
//													ItemsObj itemsObj = (ItemsObj) Constants.shops_history
//															.get(tv_txt.getId());
													/*Constants.child_id=Integer.parseInt(itemsObj.getParientId());
													Constants.parentid = Integer.parseInt(itemsObj.getCategoryId());
													Constants.parients_id.add(itemsObj.getParientId().toString());*/  
//													Constants.child_id=Integer.parseInt(itemsObj.getParientId());
//													Toast.makeText(getApplicationContext(), ""+tv_vie1.getText().toString(), Toast.LENGTH_LONG).show(); 
													Constants.parentid = Integer.parseInt(tv_vie1.getText().toString());
													Constants.parients_id.add(tv_vie2.getText().toString());
													Constants.Catid=""+Constants.parentid; 
													if(Constants.parentid == 13525)
													{
														startActivity(new Intent(Shops.this,TravelConcierge.class)); 
													}else if(Constants.parentid == 13524)
													{
														
													}
													else
													{
														Constants.Level++;
														startActivity(new Intent(Shops.this,EntertainmentTickets.class)); 
													}
												
												}
											});
								 
								button2.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										Constants.Level=1;
										
										
										Constants.intentFlag = 1;
//										ItemsObj itemsObj = (ItemsObj) Constants.shops_history
//												.get(tv_txt.getId());
						 				
										/*Constants.child_id=Integer.parseInt(itemsObj.getParientId());
										Constants.parentid = Integer.parseInt(itemsObj.getCategoryId());
										Constants.parients_id.add(itemsObj.getParientId().toString());*/  
//										Constants.child_id=Integer.parseInt(itemsObj.getParientId());
										Constants.parentid = Integer.parseInt(tv_vie1.getText().toString());
										Constants.parients_id.add(tv_vie2.getText().toString());
										
										try {
//												Toast.makeText(getApplicationContext(), "levels ended", Toast.LENGTH_LONG).show();
												
												
												if(Constants.parentid == 13525)
												{
													startActivity(new Intent(Shops.this,TravelConcierge.class)); 
												}else if(Constants.parentid == 13524)
												{
													startActivity(new Intent(Shops.this,EntertainmentTickets.class)); 
												}
												else
												{
													Constants.Catid=""+Constants.parentid;
													Constants.flg_bak_button=2;
													Shops.this.finish();
													startActivity(new Intent(Shops.this,ShopMainActivity.class));
													
												}
											
										} catch (Exception e) {
											// TODO: handle exception
										}
									}
								});
									
									lin_scr.addView(mLinearView); 
								}
								
								
								/*	adapter = new Shops_ListAdapter(Shops.this,
											R.layout.shpos_row, Constants.shops_history);
						  
								adapter.notifyDataSetChanged();
								shops_list.setAdapter(adapter);*/
							} 
						 else
						 {
							 try {
							 System.out.println("else result:"+result);
//								Toast.makeText(getApplicationContext(), "levels ended", Toast.LENGTH_LONG).show();
								if(Constants.parentid == 13525)
								{
								}else if(Constants.parentid == 13775)
								{
								}
								else
								{
									Constants.Catid=""+Constants.parentid;
									Constants.flg_bak_button=2;
									Shops.this.finish();
									startActivity(new Intent(Shops.this,ShopMainActivity.class));
									
								}
								
								
							/*	final Dialog dialog = new Dialog(Shops.this,android.R.style.Theme_Translucent_NoTitleBar);
								// android.R.style.Theme_Translucent_NoTitleBar);
								dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								dialog.setContentView(R.layout.mapdialogue1);
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
								
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
								
								
								
								dialog.show();*/
							 }
							 catch(Exception e)
							 {
								 
							 }
//							 Shops.this.finish();
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

/*@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(Constants.flg_bak_button==1)
		{
			
		}else if(Constants.flg_bak_button==2)
			{
			adapter.notifyDataSetChanged();
			shops_list.setAdapter(adapter);
			}
		
	}*/
private class CategoriesListTasks1  extends AsyncTask<String, Void, String> {

	ProgressDialog myProgressDialog;

	protected void onPreExecute() {
		super.onPreExecute();
		myProgressDialog = new ProgressDialog(Shops.this);
		myProgressDialog.setMessage("please wait ...");
		myProgressDialog.show();
	}
 
	@Override
	protected String doInBackground(String... params) {
		
		/*if(Constants.Level==1)
		{
			Constants.parentid=1;
		}
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetCategories&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&Level="
					+ Constants.Level + "&parentid="
					+ Constants.parentid);*/
		
		return Constants.result_json.get(Constants.result_json.size()-1);

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
					 Constants.shops_history.clear();
//					 Constants.result_json.clear();
//					 Constants.result_json.add(result);
					 
					JSONObject jObject = new JSONObject(result);
					JSONArray	Categorieslists_jsonArray = jObject.getJSONArray("DATA");
					 if(Categorieslists_jsonArray.length()>0)
						{
						 System.out.println("if result:"+result);
						 
						
							for (int j = 0; j < Categorieslists_jsonArray.length(); j++) { 
								JSONObject jObject1 = Categorieslists_jsonArray.getJSONObject(j);
								ItemsObj objItem = new ItemsObj();
							
								if(jObject1.has("LABEL"))
								{
									objItem.setCategory_tit(jObject1.getString("LABEL"));
									objItem.setParientId(jObject1.getString("PARENTID"));
									objItem.setCategoryId(jObject1.getString("CATEGORYID")); 
//									objItem.setBack_categoryId(jObject1.getString("CATEGORYID")); 
								}
								
//								objItem.setCategory_tit("Beams Seat Belt ");
								

								Constants.shops_history.add(objItem);
							}
							 
							for (int i = 0; i < Constants.shops_history.size(); i++) {
								LayoutInflater inflater = null;
								inflater = (LayoutInflater) getApplicationContext()
										.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
								View mLinearView = inflater.inflate(R.layout.shpos_row, null);
								final TextView tv_txt = (TextView)mLinearView.findViewById(R.id.tv_txt);
								final TextView tv_vie1 = (TextView)mLinearView.findViewById(R.id.tv_vie1);
								final TextView tv_vie2 = (TextView)mLinearView.findViewById(R.id.tv_vie2);
								final TextView button1 = (Button)mLinearView.findViewById(R.id.button1);
							ItemsObj	objBean = Constants.shops_history.get(i);
							tv_txt.setText(objBean.getCategory_tit());
							tv_vie1.setText(objBean.getCategoryId());
							tv_vie2.setText(objBean.getParientId());
							tv_txt.setId(i);
							
							tv_txt.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Constants.Level++;
							 		
									Constants.intentFlag = 1;
//									ItemsObj itemsObj = (ItemsObj) Constants.shops_history
//											.get(tv_txt.getId());
					 				
									
								/*	Constants.child_id=Integer.parseInt(itemsObj.getParientId());
									Constants.parentid = Integer.parseInt(itemsObj.getCategoryId());
									Constants.parients_id.add(itemsObj.getParientId().toString());  */
									
//									Constants.child_id=Integer.parseInt(itemsObj.getParientId());
									Constants.parentid = Integer.parseInt(tv_vie1.getText().toString());
									Constants.parients_id.add(tv_vie2.getText().toString());
									
//									Toast.makeText(getApplicationContext(), ""+Constants.parients_id, Toast.LENGTH_LONG).show();
									
//									startActivity(new Intent(Shops.this,Shops.class)); 
									
									
									Constants.Catid=""+Constants.parentid;
									Constants.flg_bak_button=2;
									Shops.this.finish();
									startActivity(new Intent(Shops.this,ShopMainActivity.class));
								}
							});
							 
							
							button1.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Constants.Level++;
									Constants.intentFlag = 1;
//								
								
//									ItemsObj itemsObj = (ItemsObj) Constants.shops_history
//											.get(tv_txt.getId());
					 				
									/*Constants.child_id=Integer.parseInt(itemsObj.getParientId());
									Constants.parentid = Integer.parseInt(itemsObj.getCategoryId());
									Constants.parients_id.add(itemsObj.getParientId().toString());*/  
									
//									Constants.child_id=Integer.parseInt(itemsObj.getParientId());
//									Toast.makeText(getApplicationContext(), "bac"+tv_vie1.getText().toString(), Toast.LENGTH_LONG).show(); 
									Constants.parentid = Integer.parseInt(tv_vie1.getText().toString());
									Constants.parients_id.add(tv_vie2.getText().toString());
									
									
									startActivity(new Intent(Shops.this,Shops.class)); 
								
								}
							});
							
							
							
								lin_scr.addView(mLinearView); 
							}
								/*adapter = new Shops_ListAdapter(Shops.this,
										R.layout.shpos_row, Constants.shops_history);
					  
							adapter.notifyDataSetChanged();
							shops_list.setAdapter(adapter);*/
						} 
					 else
					 {
						 try {
							 System.out.println("else result:"+result);
//								Toast.makeText(getApplicationContext(), "levels ended", Toast.LENGTH_LONG).show();
								Constants.Catid=""+Constants.parentid;
								Constants.flg_bak_button=2;
								Shops.this.finish();
								startActivity(new Intent(Shops.this,ShopMainActivity.class));
								/*return WebServiceCalls.getJSONString(Constants.MAIN_HOST
										+ "method=fnGetProducts&AuthToken=" + Constants.AUTH_TOKEN
										+ "&clientID=" + Constants.clientID + "&clientRatio="
										+ Constants.clientRatio + "&admin="
										+ Constants.admin + "&Limit="
										+ Constants.limit+"&Catid="+Constants.Catid);*/
						} catch (Exception e) {
							// TODO: handle exception
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

public class Shops_ListAdapter extends ArrayAdapter<ItemsObj> {

	private Activity activity;
	private List<ItemsObj> items;
	private ItemsObj objBean;
	@SuppressWarnings("unused")
	private int row;
	Bitmap bimg;
	ViewHolder holder;
	String strQty = "0";
/*	AQuery androidAQuery = new AQuery(getContext());

	Utility utils = new Utility(getContext());*/

	public Shops_ListAdapter(Activity act, int resource,
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
		TextView dat_txt, dat_txt1, contant_txt, order_txt1,points_txt1,ordrer_type_txt1;
		ImageView iv;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflator = activity.getLayoutInflater();
		objBean = items.get(position);
		if (convertView == null) {
			holder = new ViewHolder();

//			if (Constants.selectLanguage.equalsIgnoreCase("3")) {
				convertView = inflator.inflate(R.layout.shpos_row,
						null);
//			} else {
//				convertView = inflator.inflate(R.layout.item_layout, null);
//			}
 
			holder.dat_txt = (TextView) convertView
					.findViewById(R.id.tv_txt);
			/*holder.dat_txt1 = (TextView) convertView
					.findViewById(R.id.date_txt1);
			holder.contant_txt = (TextView) convertView
					.findViewById(R.id.contant_txt);
			 holder.order_txt1 = (TextView)
			 convertView.findViewById(R.id.order_no_txt1);
			holder.points_txt1 = (TextView) convertView
					.findViewById(R.id.points_txt1);
			holder.ordrer_type_txt1 = (TextView) convertView
					.findViewById(R.id.order_type__txt1);*/

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.dat_txt.setText(objBean.getCategory_tit());
		
		/*holder.dat_txt1.setText(objBean.getDate_str1());

		holder.contant_txt.setText(objBean.getDec_str());

		holder.order_txt1.setText(objBean.getOrder_no_str());

		holder.points_txt1.setText(objBean.getPoints_str());

		holder.ordrer_type_txt1.setText(objBean.getOrder_type_str());*/

		

		

		return convertView;
	}

}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.taico_main, menu);
		return true;
	}
 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			Constants.intentFlag = 2;
			Constants.Level--;
			if(Constants.Level==1)
			{  
				Constants.Level=1;
//				Constants.parentid=1;
				Constants.parentid=Integer.parseInt(Constants.parients_id.get(Constants.parients_id.size()-1));
				startActivity(new Intent(Shops.this,Shops.class));
//				new CategoriesListTasks1().execute();
				 
			}if(Constants.Level==0)
			{
				Constants.Level=1; 
				Constants.parentid=1;
				startActivity(new Intent(Shops.this,ShopMainActivity.class));
				Shops.this.finish();
				
			}
			else  
			{
				Constants.parentid=Integer.parseInt(Constants.parients_id.get(Constants.parients_id.size()-1));
				Constants.parients_id.remove(Constants.parients_id.size()-1);
				
//				Toast.makeText(getApplicationContext(), "after"+Constants.parients_id.size(), Toast.LENGTH_SHORT).show();
				Constants.result_json.remove(Constants.result_json.size()-1);
				startActivity(new Intent(Shops.this,Shops.class)); 
//				new CategoriesListTasks1().execute();
				
			}
//			Toast.makeText(getApplicationContext(), ""+Constants.Level, Toast.LENGTH_LONG).show();
			
		 
			/*if (util.IsNetConnected(Shops.this)) {
				new CategoriesListTasks().execute();
				 } else {
				 util.showAlertNoInternetService(Shops.this);
				 }*/
		}
}
