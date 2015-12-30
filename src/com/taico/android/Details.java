package com.taico.android;

import java.util.ArrayList;
import java.util.Iterator;

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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.taico.android.util.Utility;

public class Details extends Activity {
	TouchImageView imgflag;
	ImageView img_wallet,add_to_wislist,menu,imageView1; 
	RelativeLayout favLayout, cartLayout, ray_header, rrr_ttt,bottom_rel,rrr,logo_rrr;
	Button btn_home,menus;
	LinearLayout menlayout_det,l4;
	Boolean men_flg=false;
	TextView textView1, textView2, title, txt_pt, txt_ptval, txt_pid,reset_txt,
			txt_pidval, txt_body, txt_qty, add_to_cart, txt_wish_cnt1,
			txt_crt_cnt1, txt_redmore, tv_select1, tv_select3, tv_select5,
			tv_select7, tv_select9,tv_select,tv_select2,tv_select4,tv_select6,tv_select8;
	LinearLayout lll_Size,lll_Color,lll_Fit,lll_select4,lll_select5,lll_show_logo;
	String url="",child_id="",child_price=""; 
	Utility util;
	String dec="";
	RelativeLayout liner_layout;
	ArrayList<String> arr_Fit,arr_Color,arr_Size,arr_productChildrenPrice,arr_productChildren,arr_attributes,arr_attributes_nam,arr_Size1,arr_Size2,arr_Size3;
	String str_Fit, str_Color, str_Size, str_productChildrenPrice,
			str_productChildren, str_key_fit = "", str_key_color = "",
			str_key_size = "", str_key_fit1 = "", str_key_color1 = "",
			str_key_size1 = "",str_productChildren_id="",str_key="",str_key1="";
	String[] temp_Fit;
	String[] temp_Color;
	String[] temp_Size;
	String[] temp_productChildrenPrice;
	String[] temp_productChildren; 
	EditText txt_cnt;
	Animation animation;
	
	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		util = new Utility(Details.this);
		
		arr_attributes= new ArrayList<String>();
		arr_attributes_nam= new ArrayList<String>();
		arr_Fit= new ArrayList<String>();
		arr_Color= new ArrayList<String>();
		arr_Size= new ArrayList<String>();
		arr_productChildrenPrice= new ArrayList<String>();
		arr_productChildren= new ArrayList<String>();
		arr_Size1= new ArrayList<String>();
		arr_Size2= new ArrayList<String>();
		arr_Size3= new ArrayList<String>();
		
		 
		txt_wish_cnt1 = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt1 = (TextView) findViewById(R.id.txt_crt_cnt1);
		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount);
		txt_wish_cnt1.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt1.setTypeface(Constants.ProximaNova_Regular);
		txt_redmore = (TextView) findViewById(R.id.txt_redmore);
		menus = (Button) findViewById(R.id.menus);
		menlayout_det= (LinearLayout) findViewById(R.id.menlayout_det);
		menlayout_det.setVisibility(View.GONE);
		btn_home = (Button) findViewById(R.id.btn_home); 
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		bottom_rel = (RelativeLayout) findViewById(R.id.bottom_rel);
		rrr = (RelativeLayout) findViewById(R.id.rrr);
		
		
		
		lll_Fit = (LinearLayout) findViewById(R.id.lll_Fit);
		lll_Size = (LinearLayout) findViewById(R.id.lll_Size);
		lll_Color = (LinearLayout) findViewById(R.id.lll_Color);
		lll_select4 = (LinearLayout) findViewById(R.id.lll_select4);
		lll_select5 = (LinearLayout) findViewById(R.id.lll_select5); 
		
		
		reset_txt= (TextView) findViewById(R.id.reset_txt);
		tv_select= (TextView) findViewById(R.id.tv_select);
		tv_select2= (TextView) findViewById(R.id.tv_select2); 
		tv_select4= (TextView) findViewById(R.id.tv_select4);  
		tv_select6= (TextView) findViewById(R.id.tv_select6);  
		tv_select8= (TextView) findViewById(R.id.tv_select8);
		
		
		tv_select1= (TextView) findViewById(R.id.tv_select1);
		tv_select3= (TextView) findViewById(R.id.tv_select3); 
		tv_select5= (TextView) findViewById(R.id.tv_select5);  
		tv_select7= (TextView) findViewById(R.id.tv_select7);  
		tv_select9= (TextView) findViewById(R.id.tv_select9);  
		
		
		reset_txt.setTypeface(Constants.ProximaNova_Regular);
		tv_select.setTypeface(Constants.ProximaNova_Regular);
		tv_select1.setTypeface(Constants.ProximaNova_Regular);
		tv_select2.setTypeface(Constants.ProximaNova_Regular);
		tv_select3.setTypeface(Constants.ProximaNova_Regular);
		tv_select4.setTypeface(Constants.ProximaNova_Regular);
		tv_select5.setTypeface(Constants.ProximaNova_Regular);
		tv_select6.setTypeface(Constants.ProximaNova_Regular);
		tv_select7.setTypeface(Constants.ProximaNova_Regular);
		tv_select8.setTypeface(Constants.ProximaNova_Regular);
		tv_select9.setTypeface(Constants.ProximaNova_Regular);
		
		reset_txt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				child_id="";
				child_price="";
				if(arr_attributes.size()==1)
		    	{
//		    	String one = arr_attributes.get(0);
//		    	String[] one_temp= one.split("sai");
//		    	arr_attributes_nam.add(one_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
		    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
		    	
		    	}
				
				if(arr_attributes.size()==2)
		    	{
		    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
		    	tv_select3.setText("select a "+arr_attributes_nam.get(1));
		    	
		    	}
				if(arr_attributes.size()==3)
		    	{
		    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
		    	tv_select3.setText("select a "+arr_attributes_nam.get(1));
		    	tv_select5.setText("select a "+arr_attributes_nam.get(2));
		    	
		    	}
				if(arr_attributes.size()==4)
		    	{
		    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
		    	tv_select3.setText("select a "+arr_attributes_nam.get(1));
		    	tv_select5.setText("select a "+arr_attributes_nam.get(2));
		    	tv_select7.setText("select a "+arr_attributes_nam.get(3));
		    	
		     	}
				if(arr_attributes.size()==5)
		    	{
		    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
		    	tv_select3.setText("select a "+arr_attributes_nam.get(1));
		    	tv_select5.setText("select a "+arr_attributes_nam.get(2));
		    	tv_select7.setText("select a "+arr_attributes_nam.get(3));
		    	tv_select9.setText("select a "+arr_attributes_nam.get(4));
		    	
		    	}
				
			}
		});
		
		
		tv_select1.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = getLayoutInflater();
				View convertView = (View) inflater.inflate(R.layout.dialog,
  						null);

				// displaying alert dialog with list of numbers
				final Dialog	alertDialog1 = new Dialog(Details.this);
				alertDialog1.setContentView(convertView);

				alertDialog1.setTitle("select a "+arr_attributes_nam.get(0));

				ListView lv = (ListView) convertView
						.findViewById(R.id.listView);
 
				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
						Details.this,
						android.R.layout.simple_list_item_1, arr_Fit);
				lv.setAdapter(adapter1);
				lv.setOnItemClickListener(new OnItemClickListener() {

					// TODO Auto-generated method stub
 
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
						 	int position, long arg3) {
						tv_select1.setText(arr_Fit.get(position)); 
						String one = arr_attributes.get(0);
				    	String[] one_temp= one.split("sai");
						
						str_productChildren_id=arr_productChildren.get(position);
						str_key=""+arr_attributes_nam.get(0);
						str_key1=""+arr_Fit.get(position); 
						alertDialog1.dismiss();
						if (util.IsNetConnected(Details.this)) {
							new ProductChildAtrributies(tv_select1,arr_Fit.get(position),one_temp[0]).execute();
							 } else {
							 util.showAlertNoInternetService(Details.this);
							 }
					}
				});

				alertDialog1.show();
			}
		});
		 
	
			tv_select3.setOnClickListener(new OnClickListener() {
	
				@SuppressLint("InflateParams")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LayoutInflater inflater = getLayoutInflater();
					View convertView = (View) inflater.inflate(R.layout.dialog,
							null);
	
					// displaying alert dialog with list of
					// numbers
					final Dialog alertDialog1 = new Dialog(Details.this);
					alertDialog1.setContentView(convertView);
	
					alertDialog1.setTitle("select a "+arr_attributes_nam.get(1));
	
					ListView lv = (ListView) convertView
							.findViewById(R.id.listView);
	
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
							Details.this, android.R.layout.simple_list_item_1,
							arr_Color);
					lv.setAdapter(adapter1);
					lv.setOnItemClickListener(new OnItemClickListener() {
	
						// TODO Auto-generated method stub
	
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							tv_select3.setText(arr_Color.get(position));
  	 						str_productChildren_id=arr_productChildren.get(position);
							String one = arr_attributes.get(1);
					    	String[] one_temp= one.split("sai");
							
							str_key=""+arr_attributes_nam.get(1);
							str_key1=""+arr_Color.get(position); 
							alertDialog1.dismiss();
							if (util.IsNetConnected(Details.this)) {
								new ProductChildAtrributies(tv_select3,arr_Color.get(position),one_temp[0]).execute();
								 } else {
								 util.showAlertNoInternetService(Details.this);
								 }
						}
					});
	
					alertDialog1.show();
				}
			});
	
			tv_select5.setOnClickListener(new OnClickListener() {
	
				@SuppressLint("InflateParams")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LayoutInflater inflater = getLayoutInflater();
					View convertView = (View) inflater.inflate(R.layout.dialog,
							null);
	
					// displaying alert dialog with list of
					// numbers
					final Dialog alertDialog1 = new Dialog(Details.this);
					alertDialog1.setContentView(convertView);
	
					alertDialog1.setTitle("select a "+arr_attributes_nam.get(2));
	
					ListView lv = (ListView) convertView
							.findViewById(R.id.listView);
	
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
							Details.this, android.R.layout.simple_list_item_1,
							arr_Size);
					lv.setAdapter(adapter1);
					lv.setOnItemClickListener(new OnItemClickListener() {
	
						// TODO Auto-generated method stub
	 
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							tv_select5.setText(arr_Size.get(position));
							str_productChildren_id=arr_productChildren.get(position);
							String one = arr_attributes.get(2);
					    	String[] one_temp= one.split("sai");
							
							str_key=""+arr_attributes_nam.get(2);
							str_key1=""+arr_Size.get(position);  
							alertDialog1.dismiss();
							if (util.IsNetConnected(Details.this)) {
								new ProductChildAtrributies(tv_select5,arr_Size.get(position),one_temp[0]).execute();
								 } else {
								 util.showAlertNoInternetService(Details.this);
								 }
						}
					});
	
					alertDialog1.show();
				}
			});
//		Inseams Available	Waist Sizes Available
			tv_select7.setOnClickListener(new OnClickListener() {
	
				@SuppressLint("InflateParams")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LayoutInflater inflater = getLayoutInflater();
					View convertView = (View) inflater.inflate(R.layout.dialog,
							null);
	
					// displaying alert dialog with list of
					// numbers
					final Dialog alertDialog1 = new Dialog(Details.this);
					alertDialog1.setContentView(convertView);
	
					alertDialog1.setTitle("select a "+arr_attributes_nam.get(3));
	
					ListView lv = (ListView) convertView
							.findViewById(R.id.listView);
	
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
							Details.this, android.R.layout.simple_list_item_1,
							arr_Size1);
					lv.setAdapter(adapter1);
					lv.setOnItemClickListener(new OnItemClickListener() {
	
						// TODO Auto-generated method stub
	
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
//							tv_select7.setText(arr_productChildrenPrice.get(position));
							
							tv_select7.setText(arr_Size1.get(position));
							str_productChildren_id=arr_productChildren.get(position);
							String one = arr_attributes.get(3);
					    	String[] one_temp= one.split("sai");
							str_key=""+arr_attributes_nam.get(3);
							str_key1=""+arr_Size1.get(position);  
							alertDialog1.dismiss();
							if (util.IsNetConnected(Details.this)) {
								new ProductChildAtrributies(tv_select7,arr_Size1.get(position),one_temp[0]).execute();
								 } else {
								 util.showAlertNoInternetService(Details.this);
								 }
							
							
							alertDialog1.dismiss();
						}
					});
	
					alertDialog1.show();
				}
			});


			tv_select9.setOnClickListener(new OnClickListener() {
				
				@SuppressLint("InflateParams")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LayoutInflater inflater = getLayoutInflater();
					View convertView = (View) inflater.inflate(R.layout.dialog,
							null);
	
					// displaying alert dialog with list of
					// numbers
					final Dialog alertDialog1 = new Dialog(Details.this);
					alertDialog1.setContentView(convertView);
	
					alertDialog1.setTitle("select a "+arr_attributes_nam.get(4)); 
	
					ListView lv = (ListView) convertView
							.findViewById(R.id.listView);
	
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
							Details.this, android.R.layout.simple_list_item_1,
							arr_Size2); 
					lv.setAdapter(adapter1);
					lv.setOnItemClickListener(new OnItemClickListener() {
	
						// TODO Auto-generated method stub
	
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
//							tv_select9.setText(arr_productChildren.get(position));
							
							tv_select9.setText(arr_Size2.get(position));
							str_productChildren_id=arr_productChildren.get(position);
							String one = arr_attributes.get(4);
					    	String[] one_temp= one.split("sai");
							
							str_key=""+arr_attributes_nam.get(4); 
							str_key1=""+arr_Size2.get(position);  
							alertDialog1.dismiss();
							if (util.IsNetConnected(Details.this)) {
								new ProductChildAtrributies(tv_select9,arr_Size2.get(position),one_temp[0]).execute();
								 } else {
								 util.showAlertNoInternetService(Details.this);
								 }
							
							alertDialog1.dismiss();
						}
					}); 
	
					alertDialog1.show();
				}
			});

		
		txt_redmore.setTypeface(Constants.ProximaNova_Regular);

		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount);

		liner_layout = (RelativeLayout) findViewById(R.id.liner_layout);
		liner_layout.setBackgroundColor(Login.bg_color);

		ray_header = (RelativeLayout) findViewById(R.id.ray_header);
		ray_header.setBackgroundColor(Login.bg_color);

		rrr_ttt = (RelativeLayout) findViewById(R.id.rrr_ttt);
		rrr_ttt.setBackgroundColor(Login.bg_color);

		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		title = (TextView) findViewById(R.id.title);
		txt_pt = (TextView) findViewById(R.id.txt_pt);
		txt_ptval = (TextView) findViewById(R.id.txt_ptval);
		txt_pid = (TextView) findViewById(R.id.txt_pid);
		txt_pidval = (TextView) findViewById(R.id.txt_pidval);
		txt_body = (TextView) findViewById(R.id.txt_body); 
		txt_qty = (TextView) findViewById(R.id.txt_qty);

		txt_cnt = (EditText) findViewById(R.id.txt_cnt);
//		txt_cnt.setText("1");
		add_to_cart = (TextView) findViewById(R.id.add_to_cart); 
		add_to_wislist = (ImageView) findViewById(R.id.add_to_wislist);
		
		add_to_cart.setTypeface(Constants.ProximaNova_Regular);
		
		/*if(Constants.GETBUTTONFONTCOLOR.length()>0)
		{
			txt_pt.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
			txt_pid.setTextColor(Color.parseColor("#"+Constants.GETBUTTONFONTCOLOR));
		}
		else
		{ 
			txt_pt.setTextColor(Color.parseColor("#d87822")); 
			txt_pid.setTextColor(Color.parseColor("#d87822")); 
			
		}*/
//		l4= (LinearLayout)findViewById(R.id.l4);
		/** Show product points in application*/
		if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("0"))
		{
			txt_pt.setVisibility(View.GONE);
			txt_ptval.setVisibility(View.GONE);
			rrr.setVisibility(View.GONE);
		}
		else if(Constants.CLIENTSHOWPRODUCTPOINTS.equalsIgnoreCase("1"))
		{
			txt_pt.setVisibility(View.VISIBLE);
			txt_ptval.setVisibility(View.VISIBLE);
			rrr.setVisibility(View.VISIBLE);
		}
		else
		{ 
			txt_pt.setVisibility(View.GONE);
			txt_ptval.setVisibility(View.GONE);
			rrr.setVisibility(View.GONE);
		}
		
		
		
		
		
		
		add_to_cart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if (util.IsNetConnected(Details.this)) {
					 if(txt_cnt.getText().toString().length()>0)
					 {
						 if(arr_Fit.size()==1&&arr_Color.size()==0&&arr_Size.size()==0&&arr_Size1.size()==0&&arr_Size2.size()==0&&arr_productChildren.size()==1)
						 {
							 child_id="";
							 if (child_id.length() > 0) {
								 new Addtocart().execute();
								} else {
									util.inputValidation("Please select option");
								}
							 
						 }
						 else  if(arr_Fit.size()==0&&arr_Color.size()==0&&arr_Size.size()==0&&arr_Size1.size()==0&&arr_Size2.size()==0&&arr_productChildren.size()==1)
						 {
							 child_id=arr_productChildren.get(0);
							 if (child_id.length() > 0) {
								 new Addtocart().execute();
								} else {
									util.inputValidation("Please select option");
								}
						 }
						 
						 else
						 {
							 if (child_id.length() > 0) {
								 new Addtocart().execute();
								} else {
									util.inputValidation("Please select option");
								}
						 }
						 
					 }
					 else
					 {
						 util.inputValidation("Please enter quantity");
					 }
					
						
						 } else {
						 util.showAlertNoInternetService(Details.this);
						 }
			}
		});
		
		add_to_wislist.setOnClickListener(new OnClickListener() {
					
					@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (util.IsNetConnected(Details.this)) {
					
					
					 if(arr_Fit.size()==1&&arr_Color.size()==0&&arr_Size.size()==0&&arr_Size1.size()==0&&arr_Size2.size()==0&&arr_productChildren.size()==1)
					 {
						 child_id="";
						 if (child_id.length() > 0) {
							 new AddIiemtoWishList().execute();
							} else {
								util.inputValidation("Please select option");
							}
						 
					 }
					 
					 else  if(arr_Fit.size()==0&&arr_Color.size()==0&&arr_Size.size()==0&&arr_Size1.size()==0&&arr_Size2.size()==0&&arr_productChildren.size()==1)
					 {
						 child_id=arr_productChildren.get(0);
						 if (child_id.length() > 0) {
							 new AddIiemtoWishList().execute();
							} else {
								util.inputValidation("Please select option");
							}
					 }
					 
					 else
					 {
						 if (child_id.length() > 0) {
							 new AddIiemtoWishList().execute();
							} else {
								util.inputValidation("Please select option");
							}
					 }
					 
					/*if (child_id.length() > 0) {
						new AddIiemtoWishList().execute();
					} else {
						util.inputValidation("Please select option");
					}*/
				} else {
					util.showAlertNoInternetService(Details.this);
				}
			}
				});

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
			title.setTypeface(Constants.ProximaNova_Regular);
			txt_pt.setTypeface(Constants.ProximaNova_Regular);
			txt_ptval.setTypeface(Constants.ProximaNova_Regular);

			txt_pid.setTypeface(Constants.ProximaNova_Regular);
			txt_pidval.setTypeface(Constants.ProximaNova_Regular);
			txt_body.setTypeface(Constants.ProximaNova_Regular);
			txt_qty.setTypeface(Constants.ProximaNova_Regular);
			txt_cnt.setTypeface(Constants.ProximaNova_Regular);
		add_to_cart.setTypeface(Constants.ProximaNova_Regular);

	
		btn_home.setText(R.string.back);
		 btn_home.setTypeface(Constants.ProximaNova_Regular);

		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Details.this.finish();

			}
		});
		 

		img_wallet = (ImageView) findViewById(R.id.img_wallet);
		img_wallet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				final Dialog dialog = new Dialog(Details.this,android.R.style.Theme_Translucent_NoTitleBar);
//				 android.R.style.Theme_Translucent_NoTitleBar);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.mapdialogue1);

				
				  Button closeBtN = (Button) dialog.findViewById(R.id.closeBtn);
				  closeBtN.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
 
				imgflag = (TouchImageView) dialog.findViewById(R.id.view_image);
//				imgflag.setImageResource(R.drawable.bag3);
				
				Picasso.with(getApplicationContext())
				.load(url.replace("\'", "%20").replace(" ", "%20"))
				.placeholder(R.drawable.login_logo)
				.error(R.drawable.bag3).fit().into(imgflag);
				
			/*	ImageLoader imageLoader = ImageLoader.getInstance();
				DisplayImageOptions options = new DisplayImageOptions.Builder()
						.cacheInMemory(true).cacheOnDisc(true)
						.resetViewBeforeLoading(true)
						.showImageForEmptyUri(R.drawable.bag3)
						.showImageOnFail(R.drawable.bag3)
						.showImageOnLoading(R.drawable.bag3).build(); 
				
				imageLoader.displayImage(
						url.replace("\'", "%20")
								.trim(), imgflag, options);*/

				dialog.show();
			}
		});
//		Toast.makeText(getApplicationContext(), ""+Constants.productID, Toast.LENGTH_LONG).show();
		 if (util.IsNetConnected(Details.this)) {
				new ProductAttributes().execute();
				new ProductDetails().execute();
			
				 } else {
				 util.showAlertNoInternetService(Details.this);
				 }
		  
		 txt_redmore.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) { 
					// TODO Auto-generated method stub
					final Dialog dialog = new Dialog(Details.this,android.R.style.Theme_Translucent_NoTitleBar);
					// android.R.style.Theme_Translucent_NoTitleBar);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.readmores);
 
					TextView detail_texs =(TextView)dialog.findViewById(R.id.detail_tex);
					 Button closeBtN = (Button) dialog.findViewById(R.id.closeBtn);
					detail_texs
					.setText(Html
							.fromHtml("<html><body>"+dec+"</body></html>"));
					 
					detail_texs.setTypeface(Constants.ProximaNova_Regular);
					 
					
					closeBtN.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					
					dialog.show();
				}
			});

			/** Show SHOWWISHLIST/SHOWADDTOCARTBUTTON  in application*/
		 
		if(Constants.SHOWWISHLIST==1)
		{
			favLayout.setVisibility(View.VISIBLE);
			add_to_wislist.setVisibility(View.VISIBLE);
		}else if(Constants.SHOWWISHLIST==0)
		{
			favLayout.setVisibility(View.GONE);
			add_to_wislist.setVisibility(View.GONE); 
		}
 
		if(Constants.SHOWADDTOCARTBUTTON==1)
		{
			cartLayout.setVisibility(View.VISIBLE);
			bottom_rel.setVisibility(View.VISIBLE);
		}else if(Constants.SHOWADDTOCARTBUTTON==0)
		{
			cartLayout.setVisibility(View.GONE);
			bottom_rel.setVisibility(View.GONE);      
		}
		
		/*if(Constants.SINGLEORDERED.equalsIgnoreCase("0") && Constants.SINGLEITEM.equalsIgnoreCase("0"))
		{
			bottom_rel.setVisibility(View.VISIBLE); 
		}
		else if(Constants.SINGLEORDERED.equalsIgnoreCase("0") && Constants.SINGLEITEM.equalsIgnoreCase("1"))
		{
			bottom_rel.setVisibility(View.VISIBLE); 
		}
		else*/ if(Constants.SINGLEORDERED.equalsIgnoreCase("1") && Constants.SINGLEITEM.equalsIgnoreCase("1"))
		{
			bottom_rel.setVisibility(View.GONE);
			
			util.inputValidation("You have already ordered your one gift");
			
		}
		else
		{
			bottom_rel.setVisibility(View.VISIBLE); 
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

		favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Details.this, WishListActivity.class));
				Details.this.finish();

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Details.this, CartActvity.class));
				Details.this.finish();

			}
		});

		menus.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				menlayout_det.removeAllViews();
				if(men_flg)
				{
					men_flg=false; 
					menlayout_det.setVisibility(View.GONE);
				
					
				}else 
				{
					menlayout_det.setVisibility(View.VISIBLE);
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
							startActivity(new Intent(Details.this, Home.class));
							Details.this.finish();
						}
					});
					
					tv_menu1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(Details.this, DashBorad.class));
							Details.this.finish();
						}
					}); 
					 
					tv_menu2.setOnClickListener(new OnClickListener() {
								 	 
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										startActivity(new Intent(Details.this, Login.class));
										Details.this.finish(); 
									}
								}); 
					 
					menlayout_det.addView(mLinearView); 
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
			  animation = AnimationUtils.loadAnimation(Details.this, R.anim.slide_in_top);
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
	
	private class ProductAttributes extends AsyncTask<String, Void, String> {
 
		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(Details.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
//			Constants.productID="94261";
			System.out.println("fnGetProductAttributes"+Constants.MAIN_HOST
					+ "method=fnGetProductAttributes&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&productID="
					+ Constants.productID );
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetProductAttributes&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&productID="
					+ Constants.productID ); 

  
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
						System.out.println("result fnGetProductAttributes"+result);
						arr_attributes.clear();
						arr_attributes_nam.clear();
						JSONObject jObject = new JSONObject(result.replace("\n", "").replace("\'", "").replace("/'", ""));
						JSONObject jObject1 = jObject.getJSONObject("DATA");
						JSONObject jObject12 = jObject1.getJSONObject("attributes");
						 
					    	/* Map<String,String> map = new HashMap<String,String>();*/
						    @SuppressWarnings("rawtypes")
							Iterator iter = jObject12.keys();
						    while(iter.hasNext()){
						        String key = (String)iter.next();
						        String value = jObject12.getString(key);
//						        System.out.println("key "+key);
//						        System.out.println("value "+value); 
//						        map.put(key,value);
						        arr_attributes.add(key+"sai"+value);
				            	}
						 
//						    Toast.makeText(getApplicationContext(), "siz"+arr_attributes.size(), Toast.LENGTH_LONG).show();
						    if(arr_attributes.size()>0)
						    {
						    	
						    	if(arr_attributes.size()==1)
						    	{
						    	String one = arr_attributes.get(0);
						    	String[] one_temp= one.split("sai");
						    	arr_attributes_nam.add(one_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
						    	
						    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
								
						    	
						    	str_Fit=one_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//						    	str_Fit = jObject12.getString("Fit").replace("[", "").replace("]", "").replace("\"", "");
								
								if(str_Fit.contains(","))
								{
									temp_Fit = str_Fit.split(",");
									
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Fit.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Fit.add(str_Fit); 
								}
								
						    	}
						    	
						    	
						    	if(arr_attributes.size()==2)
						    	{
						    	String one = arr_attributes.get(0);
						    	String[] one_temp= one.split("sai");
						    	arr_attributes_nam.add(one_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
						    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
								
						    	
						    	str_Fit=one_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//						    	str_Fit = jObject12.getString("Fit").replace("[", "").replace("]", "").replace("\"", "");
								
								if(str_Fit.contains(","))
								{
									temp_Fit = str_Fit.split(",");
									
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Fit.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Fit.add(str_Fit); 
								}
								  
								String two = arr_attributes.get(1);
						    	String[] two_temp= two.split("sai");
						    	arr_attributes_nam.add(two_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
								tv_select3.setText("select a "+arr_attributes_nam.get(1));
								
						    	
						    	str_Color=two_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Color = jObject12.getString("Color").replace("[", "").replace("]", "").replace("\"", "");
								if(str_Color.contains(","))
  								{
									temp_Fit = str_Color.split(",");
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Color.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Color.add(str_Color);
								}
								
						    	}
						    	
						    	
						    	if(arr_attributes.size()==3)
						    	{
						    	String one = arr_attributes.get(0);
						    	String[] one_temp= one.split("sai");
						    	arr_attributes_nam.add(one_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
						    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
						    	str_Fit=one_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//						    	str_Fit = jObject12.getString("Fit").replace("[", "").replace("]", "").replace("\"", "");
								
								if(str_Fit.contains(","))
								{
									temp_Fit = str_Fit.split(",");
									
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Fit.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Fit.add(str_Fit); 
								}
								  
								String two = arr_attributes.get(1);
						    	String[] two_temp= two.split("sai");
						    	arr_attributes_nam.add(two_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
								tv_select3.setText("select a "+arr_attributes_nam.get(1));
						    	
						    	str_Color=two_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Color = jObject12.getString("Color").replace("[", "").replace("]", "").replace("\"", "");
								if(str_Color.contains(","))
								{
									temp_Fit = str_Color.split(",");
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Color.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Color.add(str_Color);
								}
								
								
								String three = arr_attributes.get(2);
						    	String[] three_temp= three.split("sai");
						    	arr_attributes_nam.add(three_temp[0].replace("[", "").replace("]", "").replace("\"", "")); 
								tv_select5.setText("select a "+arr_attributes_nam.get(2));
						    	
						    	str_Size=three_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Size = jObject12.getString("Size").replace("[", "").replace("]", "").replace("\"", ""); 
								
								if(str_Size.contains(","))
								{
									temp_Size = str_Size.split(",");
									  for(int i =0; i < temp_Size.length ; i++)
									  {
										  arr_Size.add(temp_Size[i]);
									  }
								}
								else
								{
									arr_Size.add(str_Size);
								}
								
						    	}
						    	
						    	
						    	if(arr_attributes.size()==4)
						    	{
						    		//one
						    	String one = arr_attributes.get(0);
						    	String[] one_temp= one.split("sai");
						    	arr_attributes_nam.add(one_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
						    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
						    	str_Fit=one_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//						    	str_Fit = jObject12.getString("Fit").replace("[", "").replace("]", "").replace("\"", "");
								
								if(str_Fit.contains(","))
								{
									temp_Fit = str_Fit.split(",");
									
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Fit.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Fit.add(str_Fit); 
								}
								  //two
								String two = arr_attributes.get(1);
						    	String[] two_temp= two.split("sai");
						    	arr_attributes_nam.add(two_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
						    	
								tv_select3.setText("select a "+arr_attributes_nam.get(1));
								
						    	str_Color=two_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Color = jObject12.getString("Color").replace("[", "").replace("]", "").replace("\"", "");
								if(str_Color.contains(","))
								{
									temp_Fit = str_Color.split(",");
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Color.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Color.add(str_Color);
								}
								
								//three
								String three = arr_attributes.get(2);
						    	String[] three_temp= three.split("sai");
						    	arr_attributes_nam.add(three_temp[0].replace("[", "").replace("]", "").replace("\"", "")); 
								tv_select5.setText("select a "+arr_attributes_nam.get(2));
						    	str_Size=three_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Size = jObject12.getString("Size").replace("[", "").replace("]", "").replace("\"", ""); 
								
								if(str_Size.contains(","))
								{
									temp_Size = str_Size.split(",");
									  for(int i =0; i < temp_Size.length ; i++)
									  {
										  arr_Size.add(temp_Size[i]);
									  }
								}
								else
								{
									arr_Size.add(str_Size);
								}
								
								//four
								String four = arr_attributes.get(3);
						    	String[] four_temp= four.split("sai");
						    	arr_attributes_nam.add(four_temp[0].replace("[", "").replace("]", "").replace("\"", "")); 
								tv_select7.setText("select a "+arr_attributes_nam.get(3));
						    String	str4=four_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Size = jObject12.getString("Size").replace("[", "").replace("]", "").replace("\"", ""); 
								
								if(str4.contains(","))
								{
									temp_Size = str4.split(",");
									  for(int i =0; i < temp_Size.length ; i++)
									  {
										  arr_Size1.add(temp_Size[i]);
									  }
								}
								else
								{
									arr_Size1.add(str4);
								}
								
								
								
						    	}
						    	
						    	
						    	if(arr_attributes.size()==5)
						    	{
						    		//one
						    	String one = arr_attributes.get(0);
						    	String[] one_temp= one.split("sai");
						    	arr_attributes_nam.add(one_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
						    	tv_select1.setText("select a "+arr_attributes_nam.get(0));
						    	str_Fit=one_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//						    	str_Fit = jObject12.getString("Fit").replace("[", "").replace("]", "").replace("\"", "");
								
								if(str_Fit.contains(","))
								{
									temp_Fit = str_Fit.split(",");
									
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Fit.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Fit.add(str_Fit); 
								}
								  //two
								String two = arr_attributes.get(1);
						    	String[] two_temp= two.split("sai");
						    	arr_attributes_nam.add(two_temp[0].replace("[", "").replace("]", "").replace("\"", ""));
								tv_select3.setText("select a "+arr_attributes_nam.get(1));
						    	str_Color=two_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Color = jObject12.getString("Color").replace("[", "").replace("]", "").replace("\"", "");
								if(str_Color.contains(","))
								{
									temp_Fit = str_Color.split(",");
									  for(int i =0; i < temp_Fit.length ; i++)
									  {
										  arr_Color.add(temp_Fit[i]);
									  }
								}
								else
								{
									arr_Color.add(str_Color);
								}
								
								//three
								String three = arr_attributes.get(2);
						    	String[] three_temp= three.split("sai");
						    	arr_attributes_nam.add(three_temp[0].replace("[", "").replace("]", "").replace("\"", "")); 
								tv_select5.setText("select a "+arr_attributes_nam.get(2));
						    	str_Size=three_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Size = jObject12.getString("Size").replace("[", "").replace("]", "").replace("\"", ""); 
								
								if(str_Size.contains(","))
								{
									temp_Size = str_Size.split(",");
									  for(int i =0; i < temp_Size.length ; i++)
									  {
										  arr_Size.add(temp_Size[i]);
									  }
								}
								else
								{
									arr_Size.add(str_Size);
								}
								
								//four
								String four = arr_attributes.get(3);
						    	String[] four_temp= four.split("sai");
						    	arr_attributes_nam.add(four_temp[0].replace("[", "").replace("]", "").replace("\"", "")); 
								tv_select7.setText("select a "+arr_attributes_nam.get(3));
						    String	str4=four_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Size = jObject12.getString("Size").replace("[", "").replace("]", "").replace("\"", ""); 
								
								if(str4.contains(","))
								{
									temp_Size = str4.split(",");
									  for(int i =0; i < temp_Size.length ; i++)
									  {
										  arr_Size1.add(temp_Size[i]);
									  }
								}
								else
								{
									arr_Size1.add(str4);
								}
								 
								
								//five
								String five = arr_attributes.get(4);
						    	String[] five_temp= five.split("sai");
						    	arr_attributes_nam.add(five_temp[0].replace("[", "").replace("]", "").replace("\"", "")); 
								tv_select9.setText("select a "+arr_attributes_nam.get(4));
						    String	str5=five_temp[1].replace("[", "").replace("]", "").replace("\"", "");
//								str_Size = jObject12.getString("Size").replace("[", "").replace("]", "").replace("\"", ""); 
								
								if(str5.contains(","))
								{ 
									temp_Size = str5.split(",");
									  for(int i =0; i < temp_Size.length ; i++)
									  {
										  arr_Size2.add(temp_Size[i]);
									  }
								}
								else 
								{
									arr_Size2.add(str5);
								}
								
								
						    	}
						    	
						    	
								
						    }
						    else
						    {
						    	lll_Fit.setVisibility(View.GONE);
								lll_Color.setVisibility(View.GONE);
								lll_Size.setVisibility(View.GONE);
								lll_select4.setVisibility(View.GONE);
								lll_select5.setVisibility(View.GONE);
								reset_txt.setVisibility(View.GONE);
						    }
					/*	if(jObject12.has("Fit"))
						{
							str_Fit = jObject12.getString("Fit").replace("[", "").replace("]", "").replace("\"", "");
							
							if(str_Fit.contains(","))
							{
								temp_Fit = str_Fit.split(",");
								   print substrings 
								  for(int i =0; i < temp_Fit.length ; i++)
								  {
									  arr_Fit.add(temp_Fit[i]);
								  }
							}
							else
							{
								arr_Fit.add(str_Fit); 
							}
						}
						
						if(jObject12.has("Color"))
						{
							str_Color = jObject12.getString("Color").replace("[", "").replace("]", "").replace("\"", "");
							if(str_Color.contains(","))
							{
								temp_Fit = str_Color.split(",");
								   print substrings 
								  for(int i =0; i < temp_Fit.length ; i++)
								  {
									  arr_Color.add(temp_Fit[i]);
								  }
							}
							else
							{
								arr_Color.add(str_Color);
							}
							
						}
						if(jObject12.has("Size"))
						{
							str_Size = jObject12.getString("Size").replace("[", "").replace("]", "").replace("\"", ""); 
							
							if(str_Size.contains(","))
							{
								temp_Size = str_Size.split(",");
								   print sub strings 
								  for(int i =0; i < temp_Size.length ; i++)
								  {
									  arr_Size.add(temp_Size[i]);
								  }
							}
							else
							{
								arr_Size.add(str_Size);
							}
						} 
						*/
						if(jObject1.has("productChildrenPrice"))
						{
							str_productChildrenPrice = jObject1.getString("productChildrenPrice").replace("[", "").replace("]", ""); 
							
							if(str_productChildrenPrice.contains(","))
							{
								temp_productChildrenPrice = str_productChildrenPrice.split(",");
//								   print substrings 
								  for(int i =0; i < temp_productChildrenPrice.length ; i++)
								  {
									  arr_productChildrenPrice.add(temp_productChildrenPrice[i]);
								  }
							}
							else
							{
								arr_productChildrenPrice.add(str_productChildrenPrice);
								child_price=str_productChildrenPrice;
								txt_ptval.setText(""+child_price);  
							}
						}
						if(jObject1.has("productChildren"))
						{
							str_productChildren = jObject1.getString("productChildren").replace("[", "").replace("]", ""); 
							
							if(str_productChildren.contains(","))
							{
								temp_productChildren = str_productChildren.split(",");
								  /* print substrings */
								  for(int i =0; i < temp_productChildren.length ; i++)
								  {
									  arr_productChildren.add(temp_productChildren[i]);
								  }
							}
							else 
							{
								arr_productChildren.add(str_productChildren);
								child_id=str_productChildren; 

							}
						}
						
						if(arr_Fit.size()>0)
						{
							lll_Fit.setVisibility(View.VISIBLE);
							reset_txt.setVisibility(View.VISIBLE);
							tv_select.setText(""+arr_attributes_nam.get(0)+"s Available");
						}else
						{
							lll_Fit.setVisibility(View.GONE);
							reset_txt.setVisibility(View.GONE);
						}
						
						if(arr_Color.size()>0)
						{
							lll_Color.setVisibility(View.VISIBLE);
							reset_txt.setVisibility(View.VISIBLE);
							tv_select2.setText(""+arr_attributes_nam.get(1)+"s Available");
						}else
						{
							lll_Color.setVisibility(View.GONE);
							reset_txt.setVisibility(View.GONE);
						}
						  
						if(arr_Size.size()>0)
						{
							lll_Size.setVisibility(View.VISIBLE);
							reset_txt.setVisibility(View.VISIBLE);
							tv_select4.setText(""+arr_attributes_nam.get(2)+"s Available");
						}else
						{
							lll_Size.setVisibility(View.GONE); 
							reset_txt.setVisibility(View.GONE);
						}
						
						
						if(arr_Size1.size()>0)
						{
							lll_select4.setVisibility(View.VISIBLE);
							reset_txt.setVisibility(View.VISIBLE);
							tv_select4.setText(""+arr_attributes_nam.get(3)+"s Available");
						}else
						{
							lll_select4.setVisibility(View.GONE); 
							reset_txt.setVisibility(View.GONE);
						}
						
						
						if(arr_Size2.size()>0)
						{
							lll_select5.setVisibility(View.VISIBLE);
							reset_txt.setVisibility(View.VISIBLE);
							tv_select4.setText(""+arr_attributes_nam.get(4)+"s Available");
						}else
						{
							lll_select5.setVisibility(View.GONE); 
							reset_txt.setVisibility(View.GONE); 
						}
						 
						if(arr_Fit.size()>0||arr_Color.size()>0||arr_Size.size()>0||arr_Size1.size()>0||arr_Size2.size()>0)
						{
							reset_txt.setVisibility(View.VISIBLE);
						}
						else
						{
							reset_txt.setVisibility(View.GONE);
						}
						
					  /*lll_Fit.setVisibility(View.GONE);
						lll_Color.setVisibility(View.GONE);
						lll_Size.setVisibility(View.GONE);
						lll_select4.setVisibility(View.GONE);
						lll_select5.setVisibility(View.GONE);*/
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
	
	private class ProductDetails extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(Details.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			System.out.println("fnGetProductDetails"+Constants.MAIN_HOST
					+ "method=fnGetProductDetails&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&productID="
					+ Constants.productID);

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetProductDetails&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&productID="
					+ Constants.productID ); 

  
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
						
						if(jObject.has("DATA"))
						{
							JSONObject jObject1 = jObject.getJSONObject("DATA");
							
								
								if(jObject1.has("PRODUCTID"))
								{
									
									if(jObject1.getString("PRODUCTID").length()>0)
									{
										txt_pidval.setText(""+jObject1.getString("PRODUCTID"));
										
										if(jObject1.has("NAME"))
										{
											title.setText(""+jObject1.getString("NAME"));
										}
										
										if(jObject1.has("PRICE"))
										{
											txt_ptval.setText(""+jObject1.getString("PRICE"));
										}
										
										if(jObject1.has("DESCRIPTION"))
										{
											 
											dec=jObject1.getString("DESCRIPTION").toString();
											txt_body
											.setText(Html
													.fromHtml("<html><body>"+dec+"</body></html>"));
										}
										
										
										if(jObject1.has("IMAGEURL"))
										{
											// download and display image from url
											
											if(jObject1.getString("IMAGEURL").contains("http"))
								 			{
												url=jObject1.getString("IMAGEURL");
											}
											else
											{
												url=Constants.prouducts_url+jObject1.getString("IMAGEURL");
											}
											 
											Picasso.with(getApplicationContext())
											.load(url.replace("\'", "%20").replace(" ", "%20")) 
											.placeholder(R.drawable.login_logo)
											.error(R.drawable.bag3).fit().into(img_wallet);
										 	
											/*ImageLoader imageLoader = ImageLoader.getInstance();
											DisplayImageOptions options = new DisplayImageOptions.Builder()
													.cacheInMemory(true).cacheOnDisc(true)
													.resetViewBeforeLoading(true)
													.showImageForEmptyUri(R.drawable.login_logo)
													.showImageOnFail(R.drawable.login_logo)
													.showImageOnLoading(R.drawable.login_logo).build(); 
											
											imageLoader.displayImage(
													url.replace("\'", "%20")
															.trim(), img_wallet, options);*/
										}
										
									}
									else
									{
										if (myProgressDialog.isShowing())
											myProgressDialog.dismiss();
										txt_pidval.setText(""); 
//										 util.inputValidation("This product is no longer available");
										 
										 AlertDialog.Builder altDialog = new AlertDialog.Builder(Details.this);
											altDialog
													.setMessage("This product is no longer available"); 
											
											altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog, int which) {
													dialog.dismiss();
													Details.this.finish();
												}
											});
											altDialog.show();
									}
								
									
								}
								else
								{
									if (myProgressDialog.isShowing())
										myProgressDialog.dismiss();
									txt_pidval.setText(""); 
//									 util.inputValidation("This product is no longer available");
									 
									AlertDialog.Builder altDialog = new AlertDialog.Builder(Details.this);
									altDialog
											.setMessage("This product is no longer available"); 
										
										altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												dialog.dismiss();
												Details.this.finish();
											}
										});
										altDialog.show();
									
								}
							
							
							
						}
						else
						{
							
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
	
	
	private class ProductChildAtrributies extends AsyncTask<String, Void, String> {
 
		ProgressDialog myProgressDialog;
      TextView  cat_txt;
      String nam="",cat_nam="";
      
		public ProductChildAtrributies(TextView  txt,String str,String str1) {
			// TODO Auto-generated constructor stub
			this.cat_txt = txt;
			this.nam=str;
			this.cat_nam=str1; 
 
		}

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(Details.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			System.out.println("fnGetProductIndChildAttributes"+Constants.MAIN_HOST
					+ "method=fnGetProductIndChildAttributes&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&productID="+ Constants.productID
					+ "&productChildID="+str_productChildren_id
					+ "&productspeckey="+ str_key
					+ "&productspecvalue="+str_key1);
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetProductIndChildAttributes&AuthToken=" + Constants.AUTH_TOKEN
					+ "&clientID=" + Constants.clientID + "&clientRatio="
					+ Constants.clientRatio + "&productID="+ Constants.productID
					+ "&productChildID="+str_productChildren_id
					+ "&productspeckey="+ str_key
					+ "&productspecvalue="+str_key1); 
//			clientid=5
//			&clientratio=1
//			&productId=1735744
//			&productChildID=3645202
//			&productspeckey=Size
//			&productspecvalue=One%20size 
  
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
//						Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
						System.out.println("result..."+result);
						JSONObject jObject = new JSONObject(result);
						 //  {"SUCCESS":true,"DATA":{"attributes":{},"productChildrenPrice":[2985.0],"productChildren":[621484],"isAvailable":true}}
						if(jObject.has("SUCCESS"))
						{
							if(jObject.getBoolean("SUCCESS"))
							{
								JSONObject jObject1 = jObject.getJSONObject("DATA");
								if(jObject1.has("isAvailable"))
								{
									if(jObject1.getBoolean("isAvailable"))
									{
									child_id=jObject1.getString("productChildren").replace("[", "").replace("]", "").replace("\"", "");
									child_price=jObject1.getString("productChildrenPrice").replace("[", "").replace("]", "").replace("\"", ""); 
									txt_ptval.setText(""+child_price); 
									System.out.println("child_id"+child_id);
									System.out.println("child_price"+child_price); 
//									Toast.makeText(getApplicationContext(), "it available this", Toast.LENGTH_LONG).show();
									cat_txt.setText(""+nam); 
								}
									else
									{ child_id="";
									child_price="";
										txt_ptval.setText(""); 
										cat_txt.setText("select a "+cat_nam);  
										Toast.makeText(getApplicationContext(), "Not available this", Toast.LENGTH_LONG).show();
									}
								}
								else
								{
									
								}
							}
							else
							{
								
							}
							
						}
						else
						{
							
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
	
	private class Addtocart extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(Details.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
 
		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnAddToCart&AuthToken="+Constants.AUTH_TOKEN
					+ "&productChildID="+child_id
					+ "&quantity="+txt_cnt.getText().toString()+"&Price="+child_price
					+ "&userid="+Constants.User_Id + "&clientratio="
					+ Constants.clientRatio); 
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
						textView1.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_incart+"</strong></b></font></body></html>"));
						textView2.setText(Html.fromHtml("<html><body><font  size='1'color='#ffffff'>Points Left: &nbsp</font><font color='#ffffff'><b><strong>"+Constants.str_points_left+"</strong></b></font></body></html>"));
					
						txt_wish_cnt1.setText(Constants.wishlistcount);
						txt_crt_cnt1.setText(Constants.itemCount);
						
						util.inputValidation("Product has been added to your cart");
//						 child_id="";
//						 child_price="";
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
	
	private class AddIiemtoWishList extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(Details.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnAddToWishList&AuthToken="+Constants.AUTH_TOKEN
					+ "&productChildID="+child_id
					+ "&quantity="+txt_cnt.getText().toString()+"&Price="+child_price
					+ "&userid="+Constants.User_Id + "&productID="	+ Constants.productID+"&clientOrderApproval="+Constants.clientOrderApproval); 

  
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
						System.out.println("wishlists"+jObject); 
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
						
						util.inputValidation("Product has been added to your wish list");
//						 child_id="";
//						 child_price="";
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

}
