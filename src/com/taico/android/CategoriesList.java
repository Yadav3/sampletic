package com.taico.android;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taico.android.Items.SubCategory;
import com.taico.android.Items.SubCategory.ItemList;
import com.taico.android.obj.ItemsObj;
import com.taico.android.util.Utility;
@SuppressLint({ "InflateParams", "ClickableViewAccessibility" })
@SuppressWarnings("unused")
public class CategoriesList extends Activity {
	private ArrayList<Items> mainList;
	private ArrayList<SubCategory> subArrayList1;
	private ArrayList<SubCategory> subArrayList2;
	private ArrayList<SubCategory> subArrayList3;
	private ArrayList<SubCategory> subArrayList4;
	private ArrayList<SubCategory> subArrayList5; 
	private ArrayList<String> mains_listing; 
	private ArrayList<String> mains_listing_Id; 
	private ArrayList<String> sub_mains_listing; 
	private ArrayList<String> sub_mains_listingId; 
	private ArrayList<String> subs_mains_listing; 
	
	ArrayList<ItemList> subArrayListItem1,subArrayListItem2,subArrayListItem3,subArrayListItem4,subArrayListItem5,subArrayListItem6;
	
	private LinearLayout mLinearListView;
	boolean isFirstViewClick = false;
	boolean isSecondViewClick = false;
	Utility util;
	 LinearLayout mLinearScrollSecond;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories_list);
		mLinearListView = (LinearLayout) findViewById(R.id.linear_ListView);
		 
		util = new Utility(CategoriesList.this);
		
		// Make array list one is for mainlist and other is for sublist
		mains_listing = new ArrayList<String>();
		mains_listing_Id = new ArrayList<String>();
		sub_mains_listing = new ArrayList<String>();
		sub_mains_listingId = new ArrayList<String>();
		subs_mains_listing = new ArrayList<String>();
		
		
		if (util.IsNetConnected(CategoriesList.this)) {
			if(Constants.intentFlag == 1){
				new CategoriesListTasks().execute();	
			}else{
//				new CategoriesListTasks1().execute();
			}
			
			 } else {
			 util.showAlertNoInternetService(CategoriesList.this);
			 }
		
		for (int i = 0; i < 6; i++) {
			sub_mains_listing.add("sub_mains_listing"+i); 
		}
		
		/*for (int i = 0; i < 10; i++) {
			mains_listing.add("Mobiles"+i); 
		}
		
		for (int i = 0; i < 6; i++) {
			sub_mains_listing.add("sub_mains_listing"+i); 
		}
		
		for (int i = 0; i < 5; i++) {
			subs_mains_listing.add("subs_mains_listing"+i); 
		}*/
		
		mainList = new ArrayList<Items>();
		subArrayList1 = new ArrayList<SubCategory>();
		subArrayList2 = new ArrayList<SubCategory>();
		subArrayList3 = new ArrayList<SubCategory>();
		subArrayList4 = new ArrayList<SubCategory>();
		subArrayList5 = new ArrayList<SubCategory>();
		
		// This arraylists are used to put items in sublists
		 subArrayListItem1 = new ArrayList<ItemList>();
		 subArrayListItem2 = new ArrayList<ItemList>();
		 subArrayListItem3 = new ArrayList<ItemList>();
		 subArrayListItem4 = new ArrayList<ItemList>();
		 subArrayListItem5 = new ArrayList<ItemList>();
		 subArrayListItem6 = new ArrayList<ItemList>();  
		
		// Add main categories in Mainlists along with their items it
//		for (int i = 0; i < mains_listing.size(); i++) {
//			mainList.add(new Items(mains_listing.get(i), subArrayList1));
//		}
		mainList.add(new Items("Mobiles", subArrayList1));
		mainList.add(new Items("Accessories", subArrayList2));
		
		 
			subArrayList1.add(new SubCategory("Motorola", subArrayListItem1));

		// Add items means arrylist
		subArrayListItem1.add(new ItemList("Moto X", "29999"));
		subArrayListItem1.add(new ItemList("Moto G", "12999"));
		subArrayListItem1.add(new ItemList("Moto E", "6999"));
		
		
		subArrayList2.add(new SubCategory("Covers", subArrayListItem2));
		subArrayList2.add(new SubCategory("Headphones", subArrayListItem3));
		
		subArrayListItem2.add(new ItemList("FlipCover", "599"));
		subArrayListItem2.add(new ItemList("pouch", "249")); 
		subArrayListItem2.add(new ItemList("BackCover", "499"));
		
		subArrayListItem3.add(new ItemList("Wired Headphones", "399"));
		subArrayListItem3.add(new ItemList("Wireless Headphones", "1999"));

	}

	private class CategoriesListTasks  extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(CategoriesList.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}
	 
		@Override
		protected String doInBackground(String... params) {
			
			if(Constants.Level==1)
			{
				Constants.parentid=1;
			}
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
						JSONArray	Categorieslists_jsonArray = jObject.getJSONArray("DATA");
						 if(Categorieslists_jsonArray.length()>0)
							{
							 System.out.println("if result:"+result);
							 
							
								for (int j = 0; j < Categorieslists_jsonArray.length(); j++) { 
									JSONObject jObject1 = Categorieslists_jsonArray.getJSONObject(j);
									ItemsObj objItem = new ItemsObj();
								
									if(jObject1.has("LABEL"))
									{
										mains_listing.add(jObject1.getString("LABEL"));
									}
									if(jObject1.has("LABEL"))
									{
									mains_listing_Id.add(jObject1.getString("CATEGORYID"));
									}
								}
								
//								FirstLevel();
								
								for (int i = 0; i < mains_listing.size(); i++) {
									LayoutInflater inflater = null;
									inflater = (LayoutInflater) getApplicationContext()
											.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
									View mLinearView = inflater.inflate(R.layout.row_first, null);
									final TextView mProductName = (TextView) mLinearView
											.findViewById(R.id.textViewName);
									final RelativeLayout mLinearFirstArrow = (RelativeLayout) mLinearView
											.findViewById(R.id.linearFirst);
									final ImageView mImageArrowFirst = (ImageView) mLinearView
											.findViewById(R.id.imageFirstArrow);
									 mLinearScrollSecond = (LinearLayout) mLinearView
											.findViewById(R.id.linear_scroll);
						 			// the checkes if menu is already opened or not
									if (isFirstViewClick == false) {
										mLinearScrollSecond.setVisibility(View.GONE);
										mImageArrowFirst.setBackgroundResource(R.drawable.arw_lt);
									} else {
										
										mLinearScrollSecond.setVisibility(View.VISIBLE);
										mImageArrowFirst.setBackgroundResource(R.drawable.arw_down);
									}
									
									
									
									final String name = mains_listing.get(i);
									mProductName.setText(name);
									
									final String nameId = mains_listing_Id.get(i);
									
									mLinearFirstArrow.setOnClickListener(new OnClickListener() {
										 
										@Override
										public void onClick(View arg0) {
											// TODO Auto-generated method stub 
											// Add arrylist in category
											Constants.Level=2;
											Constants.parentid=Integer.parseInt(nameId);
											Toast.makeText(getApplicationContext(), ""+nameId+"\n"+Constants.Level, Toast.LENGTH_LONG).show();
											
										/*	if (util.IsNetConnected(CategoriesList.this)) {
												sub_mains_listing.clear();
												sub_mains_listingId.clear();
												new CategoriesListTasks1().execute();	
												
											
											 } else {
											 util.showAlertNoInternetService(CategoriesList.this);
											 } */
											
											
											for (int j = 0; j < sub_mains_listing.size(); j++) {
												LayoutInflater inflater2 = null;
												inflater2 = (LayoutInflater) getApplicationContext()
														.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
												View mLinearView2 = inflater2
														.inflate(R.layout.row_second, null);

												TextView mSubItemName = (TextView) mLinearView2
														.findViewById(R.id.textViewTitle);
												final RelativeLayout mLinearSecondArrow = (RelativeLayout) mLinearView2
														.findViewById(R.id.linearSecond);
												final ImageView mImageArrowSecond = (ImageView) mLinearView2
														.findViewById(R.id.imageSecondArrow);
												final LinearLayout mLinearScrollThird = (LinearLayout) mLinearView2
														.findViewById(R.id.linear_scroll_third);
												// checkes if menu is already opened or not
												if (isSecondViewClick == false) {
													mLinearScrollThird.setVisibility(View.GONE);
													mImageArrowSecond.setBackgroundResource(R.drawable.arw_lt);
												} else {
													mLinearScrollThird.setVisibility(View.VISIBLE);
													mImageArrowSecond
															.setBackgroundResource(R.drawable.arw_down);
												}
												/*final String catName = mainList.get(i).getmSubCategoryList()
														.get(j).getpSubCatName();
												mSubItemName.setText(catName);*/
												final String catName = sub_mains_listing.get(j);
												mSubItemName.setText(catName);
												
												// Handles onclick effect on list item 
												mLinearSecondArrow.setOnTouchListener(new OnTouchListener() {
													@SuppressLint("ClickableViewAccessibility")
													@Override
													public boolean onTouch(View v, MotionEvent event) {
														if (isSecondViewClick == false) {
															isSecondViewClick = true;
															mImageArrowSecond
																	.setBackgroundResource(R.drawable.arw_down);
															mLinearScrollThird.setVisibility(View.VISIBLE);
														} else {
															isSecondViewClick = false;
															mImageArrowSecond
																	.setBackgroundResource(R.drawable.arw_lt);
															mLinearScrollThird.setVisibility(View.GONE);
														}
														Toast.makeText(getApplicationContext(), catName, Toast.LENGTH_LONG).show();
														
														
														/*for (int k = 0; k < mainList.get(i).getmSubCategoryList()
																.get(j).getmItemListArray().size(); k++) {*/
														for (int k = 0; k < subs_mains_listing.size(); k++) {
															LayoutInflater inflater3 = null;
															inflater3 = (LayoutInflater) getApplicationContext()
																	.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
															View mLinearView3 = inflater3.inflate(R.layout.row_third,
																	null);

															final TextView mItemName = (TextView) mLinearView3
																	.findViewById(R.id.textViewItemName);
															/*	TextView mItemPrice = (TextView) mLinearView3
																	.findViewById(R.id.textViewItemPrice);
															final String itemName = mainList.get(i)
																	.getmSubCategoryList().get(j).getmItemListArray()
																	.get(k).getItemName();
															final String itemPrice = mainList.get(i)
																	.getmSubCategoryList().get(j).getmItemListArray()
																	.get(k).getItemPrice();*/
															mItemName.setText(subs_mains_listing.get(k));
															mItemName.setOnClickListener(new OnClickListener() {
																
																@Override
																public void onClick(View v) {
																	// TODO Auto-generated method stub
																	Toast.makeText(getApplicationContext(), mItemName.getText().toString(), Toast.LENGTH_LONG).show();
																}
															}); 
															
//															mItemPrice.setText(itemPrice);
															mLinearScrollThird.addView(mLinearView3);
														}
														
														
														
														
														
														return false;
													}
												});
												
												mLinearScrollSecond.addView(mLinearView2);

											}
											
						////////////////////////////////////////////////////////////////////////////////
											
											if (isFirstViewClick == false) {
												isFirstViewClick = true;
												mImageArrowFirst
														.setBackgroundResource(R.drawable.arw_down);
												mLinearScrollSecond.setVisibility(View.VISIBLE);
												
												// Adds data into second row
							/////////////////////////////////////////////////////////////////////////////////////	
												
											} else {
												isFirstViewClick = false;
												mImageArrowFirst
														.setBackgroundResource(R.drawable.arw_lt);
												mLinearScrollSecond.setVisibility(View.GONE);
											}
											
											
										
										}
									});
							
									mLinearListView.addView(mLinearView);
								}
							} 
						 else
						 {
							 System.out.println("else result:"+result);
								Toast.makeText(getApplicationContext(), "levels ended", Toast.LENGTH_LONG).show();

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
	
	
	private void FirstLevel() {
		// TODO Auto-generated method stub
		for (int i = 0; i < mains_listing.size(); i++) {
			LayoutInflater inflater = null;
			inflater = (LayoutInflater) getApplicationContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View mLinearView = inflater.inflate(R.layout.row_first, null);
			final TextView mProductName = (TextView) mLinearView
					.findViewById(R.id.textViewName);
			final RelativeLayout mLinearFirstArrow = (RelativeLayout) mLinearView
					.findViewById(R.id.linearFirst);
			final ImageView mImageArrowFirst = (ImageView) mLinearView
					.findViewById(R.id.imageFirstArrow);
			 mLinearScrollSecond = (LinearLayout) mLinearView
					.findViewById(R.id.linear_scroll);
 			// the checkes if menu is already opened or not
			if (isFirstViewClick == false) {
				mLinearScrollSecond.setVisibility(View.GONE);
				mImageArrowFirst.setBackgroundResource(R.drawable.arw_lt);
			} else {
				
				mLinearScrollSecond.setVisibility(View.VISIBLE);
				mImageArrowFirst.setBackgroundResource(R.drawable.arw_down);
			}
			
			
			
			final String name = mains_listing.get(i);
			mProductName.setText(name);
			
			final String nameId = mains_listing_Id.get(i);
			
			mLinearFirstArrow.setOnClickListener(new OnClickListener() {
				 
				@SuppressLint("ClickableViewAccessibility")
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub 
					// Add arrylist in category
					Constants.Level=2;
					Constants.parentid=Integer.parseInt(nameId);
					Toast.makeText(getApplicationContext(), ""+nameId+"\n"+Constants.Level, Toast.LENGTH_LONG).show();
					
				/*	if (util.IsNetConnected(CategoriesList.this)) {
						sub_mains_listing.clear();
						sub_mains_listingId.clear();
						new CategoriesListTasks1().execute();	
						
					
					 } else {
					 util.showAlertNoInternetService(CategoriesList.this);
					 } */
					
					
					for (int j = 0; j < sub_mains_listing.size(); j++) {
						LayoutInflater inflater2 = null;
						inflater2 = (LayoutInflater) getApplicationContext()
								.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View mLinearView2 = inflater2
								.inflate(R.layout.row_second, null);

						TextView mSubItemName = (TextView) mLinearView2
								.findViewById(R.id.textViewTitle);
						final RelativeLayout mLinearSecondArrow = (RelativeLayout) mLinearView2
								.findViewById(R.id.linearSecond);
						final ImageView mImageArrowSecond = (ImageView) mLinearView2
								.findViewById(R.id.imageSecondArrow);
						final LinearLayout mLinearScrollThird = (LinearLayout) mLinearView2
								.findViewById(R.id.linear_scroll_third);
						// checkes if menu is already opened or not
						if (isSecondViewClick == false) {
							mLinearScrollThird.setVisibility(View.GONE);
							mImageArrowSecond.setBackgroundResource(R.drawable.arw_lt);
						} else {
							mLinearScrollThird.setVisibility(View.VISIBLE);
							mImageArrowSecond
									.setBackgroundResource(R.drawable.arw_down);
						}
						/*final String catName = mainList.get(i).getmSubCategoryList()
								.get(j).getpSubCatName();
						mSubItemName.setText(catName);*/
						final String catName = sub_mains_listing.get(j);
						mSubItemName.setText(catName);
						
						// Handles onclick effect on list item 
						mLinearSecondArrow.setOnTouchListener(new OnTouchListener() {
							@Override
							public boolean onTouch(View v, MotionEvent event) {
								if (isSecondViewClick == false) {
									isSecondViewClick = true;
									mImageArrowSecond
											.setBackgroundResource(R.drawable.arw_down);
									mLinearScrollThird.setVisibility(View.VISIBLE);
								} else {
									isSecondViewClick = false;
									mImageArrowSecond
											.setBackgroundResource(R.drawable.arw_lt);
									mLinearScrollThird.setVisibility(View.GONE);
								}
								Toast.makeText(getApplicationContext(), catName, Toast.LENGTH_LONG).show();
								
								
								/*for (int k = 0; k < mainList.get(i).getmSubCategoryList()
										.get(j).getmItemListArray().size(); k++) {*/
								for (int k = 0; k < subs_mains_listing.size(); k++) {
									LayoutInflater inflater3 = null;
									inflater3 = (LayoutInflater) getApplicationContext()
											.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
									View mLinearView3 = inflater3.inflate(R.layout.row_third,
											null);

									final TextView mItemName = (TextView) mLinearView3
											.findViewById(R.id.textViewItemName);
									/*	TextView mItemPrice = (TextView) mLinearView3
											.findViewById(R.id.textViewItemPrice);
									final String itemName = mainList.get(i)
											.getmSubCategoryList().get(j).getmItemListArray()
											.get(k).getItemName();
									final String itemPrice = mainList.get(i)
											.getmSubCategoryList().get(j).getmItemListArray()
											.get(k).getItemPrice();*/
									mItemName.setText(subs_mains_listing.get(k));
									mItemName.setOnClickListener(new OnClickListener() {
										
										@Override
										public void onClick(View v) {
											// TODO Auto-generated method stub
											Toast.makeText(getApplicationContext(), mItemName.getText().toString(), Toast.LENGTH_LONG).show();
										}
									}); 
									
//									mItemPrice.setText(itemPrice);
									mLinearScrollThird.addView(mLinearView3);
								}
								
								
								
								
								
								return false;
							}
						});
						
						mLinearScrollSecond.addView(mLinearView2);

					}
					
////////////////////////////////////////////////////////////////////////////////
					
					if (isFirstViewClick == false) {
						isFirstViewClick = true;
						mImageArrowFirst
								.setBackgroundResource(R.drawable.arw_down);
						mLinearScrollSecond.setVisibility(View.VISIBLE);
						
						// Adds data into second row
	/////////////////////////////////////////////////////////////////////////////////////	
						
					} else {
						isFirstViewClick = false;
						mImageArrowFirst
								.setBackgroundResource(R.drawable.arw_lt);
						mLinearScrollSecond.setVisibility(View.GONE);
					}
					
					
				
				}
			});
	
			mLinearListView.addView(mLinearView);
		}	
	}
	 
	protected void secondLevel(LinearLayout mLinearScrollSecond, ArrayList<String> sub_mains_listing2) {
		if(sub_mains_listing2.size()>0)
		{
			for (int j = 0; j < sub_mains_listing2.size(); j++) {
				LayoutInflater inflater2 = null;
				inflater2 = (LayoutInflater) getApplicationContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View mLinearView2 = inflater2
						.inflate(R.layout.row_second, null);

				TextView mSubItemName = (TextView) mLinearView2
						.findViewById(R.id.textViewTitle);
				final RelativeLayout mLinearSecondArrow = (RelativeLayout) mLinearView2
						.findViewById(R.id.linearSecond);
				final ImageView mImageArrowSecond = (ImageView) mLinearView2
						.findViewById(R.id.imageSecondArrow);
				final LinearLayout mLinearScrollThird = (LinearLayout) mLinearView2
						.findViewById(R.id.linear_scroll_third);
				// checkes if menu is already opened or not
				if (isSecondViewClick == false) {
					mLinearScrollThird.setVisibility(View.GONE);
					mImageArrowSecond.setBackgroundResource(R.drawable.arw_lt);
				} else {
					mLinearScrollThird.setVisibility(View.VISIBLE);
					mImageArrowSecond
							.setBackgroundResource(R.drawable.arw_down);
				}
				/*final String catName = mainList.get(i).getmSubCategoryList()
						.get(j).getpSubCatName();
				mSubItemName.setText(catName);*/
				final String catName = sub_mains_listing2.get(j);
				mSubItemName.setText(catName);
				
				// Handles onclick effect on list item 
				mLinearSecondArrow.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if (isSecondViewClick == false) {
							isSecondViewClick = true;
							mImageArrowSecond
									.setBackgroundResource(R.drawable.arw_down);
							mLinearScrollThird.setVisibility(View.VISIBLE);
						} else {
							isSecondViewClick = false;
							mImageArrowSecond
									.setBackgroundResource(R.drawable.arw_lt);
							mLinearScrollThird.setVisibility(View.GONE);
						}
						Toast.makeText(getApplicationContext(), catName, Toast.LENGTH_LONG).show();
						
						return false;
					}
				});
				
				mLinearScrollSecond.addView(mLinearView2);

			}
			
		}
		
		else
		{
			Toast.makeText(getApplicationContext(), "else in seccond", Toast.LENGTH_LONG).show();
		}
	}

	private class CategoriesListTasks1  extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(CategoriesList.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show(); 
		}
	 
		@Override
		protected String doInBackground(String... params) {
			
			
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
										sub_mains_listing.add(jObject1.getString("LABEL"));
									}
									if(jObject1.has("LABEL"))
									{
									sub_mains_listingId.add(jObject1.getString("CATEGORYID"));
									}
								}
								
								secondLevel(mLinearScrollSecond,sub_mains_listing);
							} 
						  
						 else
						 {
							 System.out.println("else result:"+result);
								Toast.makeText(getApplicationContext(), "levels ended", Toast.LENGTH_LONG).show();
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
	
}

