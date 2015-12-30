package com.taico.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomGrid extends BaseAdapter{
	  private Context mContext;
	  private final String[] web;
	  private final int[] Imageid; 

	    public CustomGrid(Context c,String[] web,int[] Imageid ) {
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
				
				LinearLayout ltop_lay= (LinearLayout) grid.findViewById(R.id.llay_top);
				LinearLayout lltop_lay1= (LinearLayout) grid.findViewById(R.id.lllay_top1);
				
				@SuppressWarnings("unused")
				
				LinearLayout lltop_lay2= (LinearLayout) grid.findViewById(R.id.lllay_top2);

				RelativeLayout bott_rrr= (RelativeLayout) grid.findViewById(R.id.bott_rrr);
			
				
//			 	bott_rrr.setVisibility(View.GONE);
//				  
//				lltop_lay2.setBackgroundColor(Login.bg_color);
				ltop_lay.setBackgroundColor(Login.bg_color);
				lltop_lay1.setBackgroundColor(Login.bg_color);
				bott_rrr.setBackgroundColor(Login.bg_color);
				
	        	TextView textView = (TextView) grid.findViewById(R.id.txt_title);
	        	TextView textView1 = (TextView) grid.findViewById(R.id.txt_title1);
	        	TextView textView2 = (TextView) grid.findViewById(R.id.txt_title2);
	        	ImageView imageView = (ImageView)grid.findViewById(R.id.web_image);
	        	textView.setText(web[position]);
	        	imageView.setImageResource(Imageid[position]);
	        
	        	 
	        	textView.setTypeface(Constants.ProximaNova_Regular); 
	        	textView1.setTypeface(Constants.ProximaNova_Regular); 
	        	textView2.setTypeface(Constants.ProximaNova_Regular); 
	        	
	        	/* 	if(web[position].equalsIgnoreCase("Home"))
	        	{
		        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);

	        	}
	        	else if(web[position].equalsIgnoreCase("What's Hot"))
	        	{
		        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.whats_hot, 0, 0);

	        	}
	        	else if(web[position].equalsIgnoreCase("Wish List"))
	        	{
		        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.wish_list, 0, 0);

	        	}
	        	else if(web[position].equalsIgnoreCase("My Account"))
	        	{
		        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.my_account, 0, 0);

	        	}
	        	else if(web[position].equalsIgnoreCase("Cart"))
	        	{
		        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cart, 0, 0);

	        	}
	        	else if(web[position].equalsIgnoreCase("Shop"))
	        	{
		        	textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shop, 0, 0);

	        	}*/
	        } else {
	        	grid = (View) convertView;
	        }
			
			return grid;
		}
}
