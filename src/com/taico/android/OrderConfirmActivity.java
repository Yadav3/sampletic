package com.taico.android;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class OrderConfirmActivity extends Activity {
	
	LinearLayout linear,rrr_scr;
	Button btn_home,btn_go_to_dashboard;
	RelativeLayout favLayout,cartLayout,ray_header;
	ImageView menu;
	ScrollView scrollView1;
	TextView txt_success,txt_change,txt_thank,txt_tracking,txt_please,txt_order,txt_in,txt_we;
	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_confirm); 
	 	
		ray_header=(RelativeLayout)findViewById(R.id.ray_header);
		ray_header.setBackgroundColor(Login.bg_color);
		scrollView1=(ScrollView)findViewById(R.id.scrollView1);
		scrollView1.setBackgroundColor(Login.bg_color);
		rrr_scr=(LinearLayout)findViewById(R.id.rrr_scr);
		txt_success=(TextView)findViewById(R.id.txt_success);
		txt_change=(TextView)findViewById(R.id.txt_change); 
		txt_thank=(TextView)findViewById(R.id.txt_thank);
		txt_tracking=(TextView)findViewById(R.id.txt_tracking);
		txt_please=(TextView)findViewById(R.id.txt_please);
		txt_order=(TextView)findViewById(R.id.txt_order);
		txt_in=(TextView)findViewById(R.id.txt_in);
		txt_we=(TextView)findViewById(R.id.txt_we);
		
		rrr_scr.setBackgroundColor(Login.bg_color);
		 
		linear=(LinearLayout) findViewById(R.id.linear1);
		txt_success.setText(Constants.FIRSTNAME+", your order was successfully submitted:");
		txt_success.setTypeface(Constants.ProximaNova_Regular); 
		txt_change.setTypeface(Constants.ProximaNova_Regular); 
		txt_thank.setTypeface(Constants.ProximaNova_Regular);  
		txt_tracking.setTypeface(Constants.ProximaNova_Regular);  
		txt_please.setTypeface(Constants.ProximaNova_Regular);  
		txt_order.setTypeface(Constants.ProximaNova_Regular); 
		txt_we.setTypeface(Constants.ProximaNova_Regular);  
		 
		btn_home= (Button)findViewById(R.id.btn_home);
		btn_home.setText(R.string.back);
		btn_home.setTypeface(Constants.ProximaNova_Regular); 
		
		btn_go_to_dashboard = (Button)findViewById(R.id.btn_go_to_dashboard);
		btn_go_to_dashboard.setTypeface(Constants.ProximaNova_Regular); 
	 	
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
				startActivity(new Intent(OrderConfirmActivity.this,DashBorad.class)); 
				OrderConfirmActivity.this.finish();

			}
		});
		 
		btn_go_to_dashboard.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) { 
				// TODO Auto-generated method stub
				startActivity(new Intent(OrderConfirmActivity.this,DashBorad.class)); 
				OrderConfirmActivity.this.finish();

			}
		});
		 
		try { 
			linear.removeAllViews();
	        
//	        JSONObject jObject = new JSONObject(loadJSONFromAsset());
	        JSONObject jObject = new JSONObject(ShippingAddress.res);
			
			
			
			if(jObject.has("DATA"))
			{
				JSONObject jObject1 = jObject.getJSONObject("DATA");
				if(jObject1.has("PRODUCTS"))
				{
					JSONArray jsonArray = jObject1.getJSONArray("PRODUCTS");
					
					if(jsonArray.length()>0)
					{
						for(int i=0;i<jsonArray.length();i++){
							LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
							
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							
							View v=inflater.inflate(R.layout.custom_row, null);
							TextView pro= (TextView)v.findViewById(R.id.txt_pro);
			 			TextView ord= (TextView)v.findViewById(R.id.txt_ord);
			 			
			 			
						if(jsonObject.has("TITLE"))	
						{
							pro.setText(jsonObject.getString("TITLE"));
						}
						if(jsonObject.has("ORDERNUMBER"))	
						{
							ord.setText(jsonObject.getString("ORDERNUMBER"));
						}
							pro.setTypeface(Constants.ProximaNova_Regular); 
							ord.setTypeface(Constants.ProximaNova_Regular); 
							
							linear.addView(v);
							}
						
						
						if(Constants.SINGLEORDERED.equalsIgnoreCase("0") && Constants.SINGLEITEM.equalsIgnoreCase("1"))
						{
							Constants.SINGLEORDERED="1";
							/*AlertDialog.Builder altDialog = new AlertDialog.Builder(OrderConfirmActivity.this);
							altDialog.setCancelable(false);
							altDialog
									.setMessage("Your order successfully placed, you are redirect to login page"); 
							
							altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									
									 startActivity(new Intent(OrderConfirmActivity.this,Login.class));
									 OrderConfirmActivity.this.finish(); 
								}
							});
							altDialog.show();*/
						}
					}
				}
			}
			
			
	        
	        
	       
		}catch(Exception e)
		{
			
		}
		
		
	}
	
	public String loadJSONFromAsset() {
	    String json = null;
	    try {
	        InputStream is = OrderConfirmActivity.this.getAssets().open("Taico Webservices.txt");
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        json = new String(buffer, "UTF-8");
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
	}
}
