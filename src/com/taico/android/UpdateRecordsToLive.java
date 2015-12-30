package com.taico.android;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.taico.android.util.Utility;

public class UpdateRecordsToLive extends Service {
	int mStartMode,def_start_no=1;

	IBinder mBinder;
//	ConnectivityReceiver conn;
	Cursor cursor1;
	JSONArray CategoryArray;
//	DBHelper helper;
	Utility util;
	boolean mAllowRebind;

	@Override
	public void onCreate() {

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

//		conn = new ConnectivityReceiver(getApplicationContext());
		try {
//			helper = new DBHelper(getApplicationContext());
//			helper.createDataBase();
//			helper.openDataBase();
			util = new Utility(UpdateRecordsToLive.this); 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 if ( util.IsNetConnected(UpdateRecordsToLive.this)) {
			 Constants.loads_arr.clear();
			 for (int i = 1; i <=3; i++) { 
				 def_start_no=i;
				 Toast.makeText(getApplicationContext(), "num"+def_start_no, Toast.LENGTH_LONG).show();
	 			 new ProductListingBacground().execute();
			}
			Toast.makeText(getApplicationContext(), "siz"+Constants.loads_arr.size(), Toast.LENGTH_LONG).show();
 
		 } else {
			  
		 }
		/*if (conn.checkInternetConnection()) {
			String qry = "SELECT * FROM UsersStagingPoints where sync_status=0";

			cursor1 = helper.getReadableDatabase().rawQuery(qry, null);

			if (cursor1.getCount() > 0) {

				new InsertMultipleEmpStagesTask().execute();

			}
		}*/
//		long currentTimeMillis = System.currentTimeMillis(); 
//		long nextUpdateTimeMillis = currentTimeMillis + 1// min
//				* DateUtils.MINUTE_IN_MILLIS;
//		Intent serviceIntent = new Intent(this, UpdateRecordsToLive.class); 
//		PendingIntent pi = PendingIntent.getService(this, 131313,
//				serviceIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//		// //
//		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE); 
//		am.set(AlarmManager.RTC_WAKEUP, nextUpdateTimeMillis, pi);

		return mStartMode;
	}

	/** A client is binding to the service with bindService() */
	@Override
	public IBinder onBind(Intent intent) {

		return mBinder;
	}

	/** Called when all clients have unbound with unbindService() */
	@Override
	public boolean onUnbind(Intent intent) {
		return mAllowRebind;
	}

	/** Called when a client is binding to the service with bindService() */
	@Override
	public void onRebind(Intent intent) {

	}

	/** Called when The service is no longer used and is being destroyed */
	@Override
	public void onDestroy() {

	} 
	
	/** Default Product listing AsyncTask functionality logic  */
	private class ProductListingBacground extends AsyncTask<String, Void, String> {

//		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
//			myProgressDialog = new ProgressDialog(ShopMainActivity.this);
//			myProgressDialog.setMessage("please wait ...");
//			myProgressDialog.setCancelable(false);
//			myProgressDialog.getWindow().setGravity(Gravity.BOTTOM);
//			myProgressDialog.show();
//			,R.style.MyTheme
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
			System.out.println("service uuu"+uuu);
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
//					if (null != myProgressDialog
//							&& myProgressDialog.isShowing()) {
//						myProgressDialog.dismiss();
//					}

					if (null == result || result.length() == 0) {

					} else {
//						if (page == 1) {
//							listingName = new ArrayList<String>();
//							listProduct_id = new ArrayList<String>();
//							listProduct_img = new ArrayList<String>();
//							listProduct_dec = new ArrayList<String>();
//							listProduct_price = new ArrayList<String>();
//							listProduct_admin_price = new ArrayList<String>();
//						
//						}
						JSONObject jObject = new JSONObject(result);
						JSONObject jObject1 = jObject.getJSONObject("DATA");
						JSONArray	ProductList_jsonArray = jObject1.getJSONArray("data");
											 
//						prepareList(ProductList_jsonArray);
					if(ProductList_jsonArray.length()>0) 
					{ 
						Constants.loads_arr.add(ProductList_jsonArray); 
						System.out.println("service ddd"+Constants.loads_arr);
						/*System.out.println("arrDfault"+listProduct_id);
						System.out.println("arrDfaultimg"+listProduct_img); 
						
						if (page == 1) {
							mAdapter = new GridviewAdapter(ShopMainActivity.this, listProduct_id);
							grid.setAdapter(mAdapter);
							} else {

							int currentPosition = grid.getFirstVisiblePosition();
							mAdapter.notifyDataSetChanged();
							grid.setSelection(currentPosition + 4);
							}*/
						
					}
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
			}

		}
	}

	class InsertMultipleEmpStagesTask extends
			AsyncTask<String, Integer, String> {

		ProgressDialog pDialog;
		JSONObject jObject;
		String strJson;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {

			System.out.println("called orders1");

			try {

				if (cursor1.getCount() > 0) {
					@SuppressWarnings("unused")
					JSONObject Transactions = new JSONObject();
					CategoryArray = new JSONArray();

					/*if (cursor1.moveToFirst()) {

						do {
							JSONObject Categories = new JSONObject();
 
							Categories.put(
									"empid",
									cursor1.getString(
											cursor1.getColumnIndex("userid"))
											.toString());

							Categories
									.put("date",
											cursor1.getString(
													cursor1.getColumnIndex("time_stamp"))
													.toString());

							Categories
									.put("latitude",
											cursor1.getString(
													cursor1.getColumnIndex("lattitude"))
													.toString());

							Categories
									.put("longitude",
											cursor1.getString(
													cursor1.getColumnIndex("longitude"))
													.toString());

							Categories
									.put("status",
											cursor1.getString(
													cursor1.getColumnIndex("boarding_status"))
													.toString());

							CategoryArray.put(Categories);

						} while (cursor1.moveToNext());

						Transactions.put("employes", CategoryArray);

						strJson = CategoryArray.toString();
					}*/
				}

			} catch (Exception e) {
				Log.e("exception : ", e.getMessage());
			}
 
//			return conn.PostJSONString("getEmployeOfflineStages", strJson);
			return  strJson;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				Log.e("Result : ", result);
				if (null == result || result.length() == 0) {

				} else {
					Log.e("Result service : ", result);

//					JSONObject mainObject = new JSONObject(result);
//
//					if (Integer.parseInt(mainObject.optString("errcode")) == 0) {
//						helper.updateEmpStagingrows();
//
//					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
