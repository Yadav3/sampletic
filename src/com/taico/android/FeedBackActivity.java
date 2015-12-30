package com.taico.android;

import java.util.regex.Matcher;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taico.android.util.Utility;

public class FeedBackActivity extends Activity {
	Button btn_home, send_btn;
	RelativeLayout favLayout, cartLayout, ray_header;
	LinearLayout feeds_ray_out;
	ImageView menu; 
	Utility util;
	TextView txt_wish_cnt1, txt_crt_cnt1;
	EditText feed_frm_txt, feed_to_txt, feed_sub_txt, feed_floor_txt, feed_company_name_txt,
	feed_day_phone_txt, feed_email_txt, feed_even_txt, feed_user_txt, feed_Order_number_txt,
	feed_user_nam_txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_back);

		util = new Utility(FeedBackActivity.this);

		txt_wish_cnt1 = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt1 = (TextView) findViewById(R.id.txt_crt_cnt1);

		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount);
		
		txt_wish_cnt1.setTypeface(Constants.ProximaNova_Regular);
		txt_crt_cnt1.setTypeface(Constants.ProximaNova_Regular);
		
		ray_header = (RelativeLayout) findViewById(R.id.ray_header);
		ray_header.setBackgroundColor(Login.bg_color);

		feeds_ray_out = (LinearLayout) findViewById(R.id.feeds_ray_out);
		feeds_ray_out.setBackgroundColor(Login.bg_color); 

		feed_frm_txt = (EditText) findViewById(R.id.feed_frm_txt);
		feed_to_txt = (EditText) findViewById(R.id.feed_to_txt);
		feed_sub_txt = (EditText) findViewById(R.id.feed_sub_txt);
		feed_floor_txt = (EditText) findViewById(R.id.feed_floor_txt);
		feed_company_name_txt = (EditText) findViewById(R.id.feed_company_name_txt);
		feed_day_phone_txt = (EditText) findViewById(R.id.feed_day_phone_txt);
		feed_email_txt = (EditText) findViewById(R.id.feed_email_txt);
		feed_even_txt = (EditText) findViewById(R.id.feed_even_txt); 
		feed_user_txt = (EditText) findViewById(R.id.feed_user_txt);
		feed_Order_number_txt = (EditText) findViewById(R.id.feed_Order_number_txt); 
//		user_nam_txt = (EditText) findViewById(R.id.user_nam_txt);
 
//		user_nam_txt.setText(Constants.str_users_name); 
		feed_user_txt.setText(Constants.FIRSTNAME); 
		feed_email_txt.setText(Constants.EMAIL);
		feed_day_phone_txt.setText(Constants.DAYPHONE);
//		even_txt.setText(Constants.EVENINGPHONE);
//		company_name_txt.setText(Constants.COMPANYNAME); 
		feed_Order_number_txt.setText(MyAccount_History.str_order_id);
 
		feed_frm_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_to_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_sub_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_floor_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_company_name_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_day_phone_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_email_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_even_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_user_txt.setTypeface(Constants.ProximaNova_Regular);
		feed_Order_number_txt.setTypeface(Constants.ProximaNova_Regular);
//		user_nam_txt.setTypeface(Constants.ProximaNova_Regular);

		send_btn = (Button) findViewById(R.id.send_btn);
		btn_home = (Button) findViewById(R.id.btn_home);
		btn_home.setText(R.string.feed_back); 
		btn_home.setTypeface(Constants.ProximaNova_Regular);
		send_btn.setTypeface(Constants.ProximaNova_Regular);

		menu = (ImageView) findViewById(R.id.menu);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);

		menu.setVisibility(View.GONE);
		favLayout.setVisibility(View.GONE);
		cartLayout.setVisibility(View.GONE);

		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FeedBackActivity.this.finish();

			} 
		});
		menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(FeedBackActivity.this, DashBorad.class));
				FeedBackActivity.this.finish();
			}
		});

		/*
		 * if(Constants.SHOWWISHLIST==1) {
		 * favLayout.setVisibility(View.VISIBLE); }else
		 * if(Constants.SHOWWISHLIST==0) { favLayout.setVisibility(View.GONE); }
		 * 
		 * if(Constants.SHOWADDTOCARTBUTTON==1) {
		 * cartLayout.setVisibility(View.VISIBLE); }else
		 * if(Constants.SHOWADDTOCARTBUTTON==0) {
		 * cartLayout.setVisibility(View.GONE); }
		 */

		favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(FeedBackActivity.this,
						WishListActivity.class));

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FeedBackActivity.this,
						CartActvity.class));

			}
		});

		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(FeedBackActivity.this, DashBorad.class));

			}
		});

		send_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (util.IsNetConnected(FeedBackActivity.this)) {
//					frm_txt.getText().toString().length() == 0
//							|| to_txt.getText().toString().length() == 0
//							  
					     if ( 
							 feed_floor_txt.getText().toString().length() == 0
							|| feed_day_phone_txt.getText().toString().length() == 0
							|| feed_email_txt.getText().toString().length() == 0
							|| feed_even_txt.getText().toString().length() == 0
							|| feed_user_txt.getText().toString().length() == 0
							|| feed_Order_number_txt.getText().toString().length() == 0)
							 {
//					    	 feed_sub_txt.getText().toString().length() == 0
//					    	 || feed_company_name_txt.getText().toString().length() == 0
//						|| user_nam_txt.getText().toString().length() == 0)
						util.inputValidation("Please enter all the fields");

					} else {
						if (feed_user_txt.getText().toString()
								.equalsIgnoreCase(Constants.FIRSTNAME)) {

							Matcher matcher1 = Constants.pattern1
									.matcher(feed_email_txt.getText().toString()
											.trim());
							if (matcher1.matches()) {

								if (feed_day_phone_txt.length() < 10) {
									util.inputValidation("Please enter valid day phone number");
								} else {
									if (util.IsNetConnected(FeedBackActivity.this)) {
										 new FedBckTasks().execute();
									} else {
										util.showAlertNoInternetService(FeedBackActivity.this);
									}
								}
							} else {
								util.inputValidation("Please enter valid email id");
							}
						}
 
						else {
							util.inputValidation("Entered first name is not same");
						}
					}

				} else {
					util.showAlertNoInternetService(FeedBackActivity.this);
				}
			}

		});

	}

	private class FedBckTasks extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(FeedBackActivity.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		} 
//		adelea@taico.com,customerserviceincentiweb@yahoo.com,customerservice@incentiweb.com
		@Override
		protected String doInBackground(String... params) {
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnSendFeedbackEmail&AuthToken="
					+ Constants.AUTH_TOKEN + "&From="
					+ Constants.EMAIL + "&To=adelea@taico.com" 
					+"&Subject="
					+ feed_sub_txt.getText().toString() + "&Comment="
					+ feed_floor_txt.getText().toString() + "&Company="
					+ feed_company_name_txt.getText().toString() + "&dayPhone="
					+ feed_day_phone_txt.getText().toString() + "&Email="
					+ feed_email_txt.getText().toString() + "&eveningPhone="
					+ feed_even_txt.getText().toString() + "&Name="
					+ feed_user_txt.getText().toString() + "&orderNumber="
					+ MyAccount_History.str_order_id + "&userName="
					+ feed_user_txt.getText().toString()); 

		}  

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {

					if (null == result || result.length() == 0) {

					} else {
						
						if(result.contains("false"))
						{
//							startActivity(new Intent(FeedBackActivity.this,OrderConfirmActivity.class)); 
 
						}else if(result.contains("true"))
						{
							
							AlertDialog.Builder altDialog = new AlertDialog.Builder(FeedBackActivity.this);
							 altDialog.setMessage("Submitted successfully"); 
							altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									if (myProgressDialog.isShowing())
										myProgressDialog.dismiss();
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
}
