package com.taico.android;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DashBorad1 extends Activity {
	GridView grid;
	String[] web = {
		    "Home",
			"What's hot",
			"Wish list",
			"My account",
			"Cart",
			"Shop",
			
			
	} ;
	int[] imageId = {
			R.drawable.home,
			R.drawable.new_wish_list,
			R.drawable.wish_list,
			R.drawable.my_account,
			R.drawable.cardashboard_cart,
			R.drawable.shop,
			
			
			
			
	};
	ViewGroup layout;
	RelativeLayout favLayout,cartLayout;
	ImageView menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ee);
		
		CustomGrid1 adapter = new CustomGrid1(DashBorad1.this, web, imageId);
		grid=(GridView)findViewById(R.id.gridview);
				grid.setAdapter(adapter);
				grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                                    int position, long id) {
//		                Toast.makeText(WhatsHotActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
//						startActivity(new Intent(DashBorad1.this,Details.class));
//
		            }
		        });
				
				

	}

	public class CustomGrid1 extends BaseAdapter{
		  private Context mContext;
		  private final String[] web;
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

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View grid;
				LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
		        if (convertView == null) {  
		        	
		        	grid = new View(mContext);
					grid = inflater.inflate(R.layout.eee, null);
					
		        	TextView textView = (TextView) grid.findViewById(R.id.hrd_mgmt_txt);
		        	textView.setText(web[position]);
		        	
		        	
		        	textView.setCompoundDrawablesWithIntrinsicBounds(0,
		        			Imageid[position], 0, 0);
		        	 
					/*	ImageView imageView = (ImageView)grid.findViewById(R.id.web_image);
		        	textView.setText(web[position]);
		        	imageView.setImageResource(Imageid[position]);
		        	RelativeLayout  bott_rrr=(RelativeLayout) grid.findViewById(R.id.bott_rrr);
		        	bott_rrr.setVisibility(View.GONE);*/
		        	
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

}
