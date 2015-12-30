package com.taico.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class OrderConformationActivity extends Activity {
	Button btn_home;
	RelativeLayout favLayout,cartLayout;
	ImageView menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_back);
		
		btn_home= (Button)findViewById(R.id.btn_home);
		btn_home.setText(R.string.back);
		
	 	
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
				OrderConformationActivity.this.finish();

			}
		});
		
		/*favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FeedBackActivity.this,
						WishListActivity.class));

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FeedBackActivity.this, CartActvity.class));

			}
		});

		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FeedBackActivity.this, DashBorad.class));

			}
		});*/
		
	}
}
