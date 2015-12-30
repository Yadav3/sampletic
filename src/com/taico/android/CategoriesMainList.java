package com.taico.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class CategoriesMainList extends Activity {

	TextView txt;
	
	@SuppressWarnings("unused")
	private ArrayList<String> mainList;
	private LinearLayout mLinearListView;
	boolean isFirstViewClick = false;
	boolean isSecondViewClick = false;
	boolean isThirdViewClick = false;
	boolean isFourthViewClick = false;
	boolean isFifthViewClick = false;
	boolean isSixthViewClick = false;
	boolean isSeventhViewClick = false;
	boolean isEighthViewClick = false;
	boolean isNinthViewClick = false;
	boolean isTenthViewClick = false;
	
	public static String TAG_DATA="DATA";
	public static String TAG_LABEL="LABEL";
	public static String TAG_CATEGORYID="CATEGORYID";
	private ProgressDialog progressDialog;

	@SuppressLint("InflateParams")
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categories_main_list);
 
		String parentid1 = "1";
		String result = null;
		Context context = CategoriesMainList.this;
		TextView tv = (TextView) findViewById(R.id.txt);
		mLinearListView = (LinearLayout) findViewById(R.id.linear_ListView);
		ProgressDialog dialog = ProgressDialog.show(CategoriesMainList.this, "","Please wait for few seconds...", true);
		AsyncTask<String, String, String> obj1 = new DemoAsyTask().execute("1", parentid1);
		try {
			result = obj1.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		JSONObject jsonobject;
		try {
			jsonobject = new JSONObject(result);
			JSONArray firstArray = jsonobject.getJSONArray("DATA");
			System.out.println("firstArray size" + firstArray.length());
			if (firstArray.length() != 0) {
			for (int i = 0; i < firstArray.length(); i++) {

				View mLinearView = (View) ((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_first, null);
				final TextView mFirstLevelName = (TextView) mLinearView.findViewById(R.id.textViewName);
				final ImageView mImageArrowFirst = (ImageView) mLinearView.findViewById(R.id.imageFirstArrow);
				final LinearLayout mLinearScrollSecond = (LinearLayout) mLinearView.findViewById(R.id.linear_scroll);
				final RelativeLayout linearFirst = (RelativeLayout) mLinearView.findViewById(R.id.linearFirst);
				
				mLinearScrollSecond.setVisibility(View.GONE);
				mImageArrowFirst.setBackgroundResource(R.drawable.arw_lt);

				JSONObject firstobj = firstArray.getJSONObject(i);
				String l1 = firstobj.getString("LABEL");
				String cid1 = firstobj.getString("CATEGORYID");
 
//				mFirstLevelName.setOnTouchListener(new OnTouchListener() {
				mFirstLevelName.setOnClickListener(new OnClickListener() {
					@SuppressLint("ShowToast")
					@Override
					public void onClick(View v) {
						System.out.println("you clicked" + v.getTag());
						System.out.println("you clicked"+ ((TextView) v).getText());
						String parentid = v.getTag().toString();
						System.out.println("parentid" + parentid);

						if (isFirstViewClick == false) {
							isFirstViewClick = true;
							mImageArrowFirst.setBackgroundResource(R.drawable.arw_down);
							mLinearScrollSecond.setVisibility(View.VISIBLE);
							mLinearScrollSecond.removeAllViews();
							AsyncTask<String, String, String> obj2=null;
							ProgressDialog dialog = ProgressDialog.show(CategoriesMainList.this, "","Please wait for few seconds...", true);
							 obj2= new DemoAsyTask().execute("2", parentid);
							String result2 = null;
							JSONObject jsonobject2=null;
							JSONArray secondArray = null;
							try { 
								result2 = obj2.get();
								jsonobject2 = new JSONObject(result2);
								secondArray = jsonobject2.getJSONArray("DATA");
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}

							System.out.println("secondArray size"+ secondArray.length());
						
							if (secondArray.length() != 0) {
								for (int j = 0; j < secondArray.length(); j++) {
									View mLinearView2 = (View) ((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_second, null);

									final TextView mSecondLevelName = (TextView) mLinearView2.findViewById(R.id.textViewTitle);
									final ImageView mImageArrowSecond = (ImageView) mLinearView2.findViewById(R.id.imageSecondArrow);
									final LinearLayout mLinearScrollThird = (LinearLayout) mLinearView2.findViewById(R.id.linear_scroll_third);
									mLinearScrollThird.setVisibility(View.GONE);
									mImageArrowSecond.setBackgroundResource(R.drawable.arw_lt);

									JSONObject secondobj;
									String l2 = null;
									String cid2 = null;
									try {
										secondobj = secondArray.getJSONObject(j);
										l2 = secondobj.getString("LABEL");
										cid2 = secondobj.getString("CATEGORYID");
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
//									mSecondLevelName.setOnTouchListener(new OnTouchListener() {
									mSecondLevelName.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											System.out.println("you clicked" + v.getTag());
											System.out.println("you clicked"+ ((TextView) v).getText());
											String parentid2 = v.getTag().toString();
											System.out.println("parentid2--" + parentid2);

											if (isSecondViewClick == false) {
												isSecondViewClick = true;
												mImageArrowSecond.setBackgroundResource(R.drawable.arw_down);
												mLinearScrollThird.setVisibility(View.VISIBLE);
												mLinearScrollThird.removeAllViews();
												AsyncTask<String, String, String> obj3=null;
												ProgressDialog dialog = ProgressDialog.show(CategoriesMainList.this, "","Please wait for few seconds...", true);
												 obj3= new DemoAsyTask().execute("3", parentid2);
												String result3 = null;
												JSONObject jsonobject3=null;
												JSONArray thirdArray = null;
												try {
													result3 = obj3.get();
													jsonobject3= new JSONObject(result3);
													thirdArray = jsonobject3.getJSONArray("DATA");
												} catch (InterruptedException e) {
													e.printStackTrace();
												} catch (ExecutionException e) {
													e.printStackTrace();
												} catch (JSONException e) {
													e.printStackTrace();
												}
												
												System.out.println("thirdArray size"+ thirdArray.length());
											

												if (thirdArray.length() != 0) {
													//third level
													for(int k=0;k<thirdArray.length();k++)
													{
														View mLinearView3 = (View) ((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_third, null);

														final TextView mThirdLevelName = (TextView) mLinearView3.findViewById(R.id.textViewItemName);
														final ImageView mImageArrowThird = (ImageView) mLinearView3.findViewById(R.id.imageThirdArrow);
														final LinearLayout mLinearScrollFourth= (LinearLayout) mLinearView3.findViewById(R.id.linear_scroll_fourth);
														mLinearScrollFourth.setVisibility(View.GONE);
														mImageArrowThird.setBackgroundResource(R.drawable.arw_lt);
														
														JSONObject thirdobj;
														String l3 = null;
														String cid3 = null;
														try {
															thirdobj = thirdArray.getJSONObject(k);
															l3 = thirdobj.getString("LABEL");
															System.out.println("l3"+l3);
															cid3 = thirdobj.getString("CATEGORYID");
															System.out.println("cat"+cid3);
														} catch (JSONException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														
//													mThirdLevelName.setOnClickListener(new OnClickListener() {
//														
//														@Override
//														public void onClick(View v) {
//															// TODO Auto-generated method stub
//															
//														}
//													});
														mThirdLevelName.setOnClickListener(new OnClickListener() {
														@SuppressLint("ShowToast")
														@Override
														public void onClick(View v) {
															System.out.println("you clicked" + v.getTag());
															System.out.println("you clicked"+ ((TextView) v).getText());
															String parentid4 = v.getTag().toString();
															System.out.println("parentid4--" + parentid4);

															if (isThirdViewClick == false) {
																isThirdViewClick = true;
																mImageArrowThird.setBackgroundResource(R.drawable.arw_down);
																mLinearScrollFourth.setVisibility(View.VISIBLE);
																AsyncTask<String, String, String> obj4=null;
																ProgressDialog dialog = ProgressDialog.show(CategoriesMainList.this, "","Please wait for few seconds...", true);

																 obj4= new DemoAsyTask().execute("4", parentid4);
																String result4 = null;
																JSONObject jsonobject4=null;
																JSONArray fourthArray = null;
																try {
																	result4 = obj4.get();
																	jsonobject4= new JSONObject(result4);
																	fourthArray = jsonobject4.getJSONArray("DATA");
																} catch (InterruptedException e) {
																	e.printStackTrace();
																} catch (ExecutionException e) {
																	e.printStackTrace();
																} catch (JSONException e) {
																	e.printStackTrace();
																}
																
																System.out.println("fourthArray size"+ fourthArray.length());

																if (fourthArray.length() != 0) {
																	
																	for(int p=0;p<fourthArray.length();p++)
																	{
																		View mLinearView4 = (View) ((LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_fourth, null);

																		final TextView mFourthLevelName = (TextView) mLinearView4.findViewById(R.id.textAvailable);
																		final ImageView mImageArrowFourth = (ImageView) mLinearView4.findViewById(R.id.imageFourthArrow);
																		final LinearLayout mLinearScrollFifth= (LinearLayout) mLinearView4.findViewById(R.id.linear_scroll_fifth);
																		mLinearScrollFifth.setVisibility(View.GONE);
																		mImageArrowFourth.setBackgroundResource(R.drawable.arw_lt);
																		
																		JSONObject fourthobj;
																		String l4 = null;
																		String cid4 = null;
																		try {
																			fourthobj = fourthArray.getJSONObject(p);
																			l4 = fourthobj.getString("LABEL");
																			System.out.println("l4"+l4);
																			cid4 = fourthobj.getString("CATEGORYID");
																			System.out.println("cat4"+cid4);
																		} catch (JSONException e) {
																			// TODO Auto-generated catch block
																			e.printStackTrace();
																		}
																		//add
																		
																		
																		//add
																		mFourthLevelName.setText(l4);
																		mFourthLevelName.setTag(cid4);
																		mLinearScrollFourth.addView(mLinearView4);
																	}	
																} else {
																	Toast.makeText(getApplicationContext(),"empty children int this row item", 1).show();
																}
																dialog.dismiss();
															} else {
																isThirdViewClick = false;
																mImageArrowThird.setBackgroundResource(R.drawable.arw_lt);
																mLinearScrollFourth.setVisibility(View.GONE);
															}
															if (null != progressDialog && progressDialog.isShowing()) {
																progressDialog.dismiss();
															}
														}
													});
																
													
													mThirdLevelName.setText(l3);
													mThirdLevelName.setTag(cid3);
													mLinearScrollThird.addView(mLinearView3);
												}
													
												} else {
													Toast.makeText(getApplicationContext(),"empty children int this row item", 1).show();
												}
												dialog.dismiss();
											} else {
												isSecondViewClick = false;
												mImageArrowSecond.setBackgroundResource(R.drawable.arw_lt);
												mLinearScrollThird.setVisibility(View.GONE);
											}
										}
									});
									
									
									mSecondLevelName.setText(l2);
									mSecondLevelName.setTag(cid2);
									mLinearScrollSecond.addView(mLinearView2);
									if (null != progressDialog && progressDialog.isShowing()) {
										progressDialog.dismiss();
									}
								}
							} else {
								Toast.makeText(getApplicationContext(),"empty children int this row item", 1).show();
							}
							dialog.dismiss();
						} else {
							isFirstViewClick = false;
							mImageArrowFirst.setBackgroundResource(R.drawable.arw_lt);
							mLinearScrollSecond.setVisibility(View.GONE);
						}
					}
				});
				mFirstLevelName.setText(l1);
				mFirstLevelName.setTag(cid1);
				mLinearListView.addView(mLinearView);
				if (null != progressDialog && progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
			}
			}
			
			else
			{
				
			}
			dialog.dismiss();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public class DemoAsyTask  extends AsyncTask<String, String, String> {

		
		JSONObject jsonResponse;
		String uri="http://stg.incentiveweb.com/webservices/TaicoWSController.cfc?method=fnGetCategories&AuthToken=MTA3NjI6OTExMzQ4MTAwOTg3MjU1OjUxNzNGMkFGLTUwNTYtQTA2My1FNEIyM0E3REZGQjQ0NjI2&clientID=5&Level=";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(CategoriesMainList.this);
			progressDialog.setMessage("Loading ...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();

		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			String level=params[0].toString();
			System.out.println("Demo level"+level);
			String parentid=params[1].toString();
			System.out.println("Demo parent id"+parentid);
			HttpClient httpclient = new DefaultHttpClient();
//			HttpPost httppost = new HttpPost("http://stg.incentiveweb.com/webservices/TaicoWSController.cfc?method=fnGetCategories&AuthToken=MTA3NjI6OTExMzQ4MTAwOTg3MjU1OjUxNzNGMkFGLTUwNTYtQTA2My1FNEIyM0E3REZGQjQ0NjI2&clientID=5&Level=1&parentid=1");
//			HttpPost httppost = new HttpPost(uri+"1&parentid=1");
			HttpPost httppost = new HttpPost(uri+level+"&parentid="+parentid);
			String result = null;
			try {
				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, HTTP.UTF_8).trim();

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
				
				
			System.out.println("result1" + result);
			if (null != progressDialog && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			
		}
	}
}
