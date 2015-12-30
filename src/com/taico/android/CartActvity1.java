package com.taico.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taico.android.util.Utility;

@SuppressLint("InflateParams")
public class CartActvity1 extends Activity {
//LinearLayout shops_layout,my_account_layout;
	TextView continue_txt,total_points_txt;
	ListView carts_list;
	Integer[] array;
	ArrayList<String> tax = new ArrayList<String>();
	ArrayList<String> tax1 = new ArrayList<String>();
	Dialog alertDialog;
	double totalprice = 0; 
	double subTotal=0;
//	public static String[] cart_unit_price = { "36", "45"};
	public static String TAG_TOTAL_PRICE = "";
	public static String TAG_UNIT_PRICE = "";
	public static String TAG_QTY_PRICE = "";
	
	public HashMap<String, String> itemWithQuantity = new HashMap<String, String>();
	public HashMap<String, String> itemWithPrice = new HashMap<String, String>();
	HashMap<String, String> product = new HashMap<String, String>();
	RelativeLayout favLayout,cartLayout,ray_header;
	ImageView menu;
	Button btn_home;
	TextView	tv_txt,engTxt,txt_nam1,tv_select,txt_nam2,tv_select2,txt_wish_cnt1,txt_crt_cnt1;
	Boolean flg=false;
	LinearLayout liner_lay;
	ArrayList<String> cart_unit_price;
	ArrayList<String> cart_title;
	ArrayList<String> cart_id;
	ArrayList<String> cart_img;
	ArrayList<String> cart_default_qty;
	Utility util;
	String productChildID="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart);
		
		util = new Utility(CartActvity1.this);
		
		cart_unit_price = new ArrayList<String>();
		cart_title = new ArrayList<String>();
		cart_id = new ArrayList<String>();
		cart_img = new ArrayList<String>();
		cart_default_qty = new ArrayList<String>();
		
		/*cart_unit_price.add("33");
		cart_unit_price.add("36");
		cart_unit_price.add("39");*/
		
		tax.clear();
		tax1.clear();
		tax.add("YES");
		tax.add("NO");
		 
		tax1.add("HOME");
		tax1.add("BUSINESS");
		
		
		 
		
		txt_wish_cnt1 = (TextView) findViewById(R.id.txt_wish_cnt1);
		txt_crt_cnt1 = (TextView) findViewById(R.id.txt_crt_cnt1);

		txt_wish_cnt1.setText(Constants.wishlistcount);
		txt_crt_cnt1.setText(Constants.itemCount);
		 
		liner_lay=(LinearLayout)findViewById(R.id.liner_lay);
		liner_lay.setBackgroundColor(Login.bg_color);
		
		ray_header=(RelativeLayout)findViewById(R.id.ray_header);
		ray_header.setBackgroundColor(Login.bg_color);
		
		
		carts_list=(ListView)findViewById(R.id.carts_list);
		carts_list.setBackgroundColor(Login.bg_color); 
		
		continue_txt= (TextView)findViewById(R.id.continue_txt);
		total_points_txt= (TextView)findViewById(R.id.total_points_txt);
		
		tv_txt= (TextView)findViewById(R.id.tv_txt);
		engTxt= (TextView)findViewById(R.id.engTxt);
		txt_nam1= (TextView)findViewById(R.id.txt_nam1);
		txt_nam2= (TextView)findViewById(R.id.txt_nam2);
		tv_select= (TextView)findViewById(R.id.tv_select);
		tv_select2= (TextView)findViewById(R.id.tv_select2);
		
		tv_txt.setText(Html
				.fromHtml("<html><body><font  size='1'color='#ffffff'>Points in Cart&nbsp&nbsp&nbsp</font><font color='#EA2828'><b><strong>"
						+ Constants.str_points_incart
						+ "</strong></b></font></body></html>"));		  
		
		continue_txt.setTypeface(Constants.ProximaNova_Regular); 
		tv_txt.setTypeface(Constants.ProximaNova_Regular); 
		total_points_txt.setTypeface(Constants.ProximaNova_Regular);
		engTxt.setTypeface(Constants.ProximaNova_Regular);
		txt_nam1.setTypeface(Constants.ProximaNova_Regular); 
		txt_nam2.setTypeface(Constants.ProximaNova_Regular); 
		tv_select.setTypeface(Constants.ProximaNova_Regular); 
		tv_select2.setTypeface(Constants.ProximaNova_Regular); 
		
		tv_select.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = getLayoutInflater();
				View convertView = (View) inflater.inflate(R.layout.dialog,
						null);

				// displaying alert dialog with list of
				// numbers
				final Dialog	alertDialog1 = new Dialog(CartActvity1.this);
				alertDialog1.setContentView(convertView);

				alertDialog1.setTitle(getResources().getString(
						R.string.preference));

				ListView lv = (ListView) convertView
						.findViewById(R.id.listView);
 
				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
						CartActvity1.this,
						android.R.layout.simple_list_item_1, tax);
				lv.setAdapter(adapter1);
				lv.setOnItemClickListener(new OnItemClickListener() {

					// TODO Auto-generated method stub

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
					
						tv_select.setText(tax.get(position));

						alertDialog1.dismiss();
					}
				});

				alertDialog1.show();
			}
		});
		
		tv_select2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = getLayoutInflater();
				View convertView = (View) inflater.inflate(R.layout.dialog,
						null);
 
				// displaying alert dialog with list of
				// numbers
				final Dialog	alertDialog2 = new Dialog(CartActvity1.this);
				alertDialog2.setContentView(convertView);

				alertDialog2.setTitle(getResources().getString(
						R.string.preference));

				ListView lv = (ListView) convertView
						.findViewById(R.id.listView);
 
				ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
						CartActvity1.this,
						android.R.layout.simple_list_item_1, tax1);
				lv.setAdapter(adapter1);
				lv.setOnItemClickListener(new OnItemClickListener() {

					// TODO Auto-generated method stub

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
					
						tv_select2.setText(tax1.get(position));

						alertDialog2.dismiss();
					}
				}); 

				alertDialog2.show();
			}
		});
		
		
		engTxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flg){
					flg=false;
				engTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check_unselect, 0,
						0, 0);
				}
		 		else
				{ 
					flg=true;
					engTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check_selected, 0,
							0, 0);
				}
				 
				}
			
		});
		
		continue_txt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(CartActvity1.this,ShippingAddress.class));
			}
		});
		
	
		 
		
		btn_home= (Button)findViewById(R.id.btn_home);
		btn_home.setText(R.string.cart);
		 btn_home.setTypeface(Constants.ProximaNova_Regular);
		menu = (ImageView) findViewById(R.id.menu);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
		
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

		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CartActvity1.this.finish();

			}
		}); 
		
		
		favLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(CartActvity1.this,
						WishListActivity.class));
				CartActvity1.this.finish();

			}
		});

		cartLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(CartActvity1.this, CartActvity1.class));
				CartActvity1.this.finish();

			}
		});

		menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(CartActvity1.this, DashBorad.class));
				CartActvity1.this.finish();

			}
		});
		
		if (util.IsNetConnected(CartActvity1.this)) {
			new CartsListask().execute();
			 } else {
			 util.showAlertNoInternetService(CartActvity1.this);
			 }
		
	}
	public void updateQtyTextView() {
		try {
			// adapter.notifyDataSetChanged();
			// setMyOrder();
			Iterator<Map.Entry<String, String>> it = itemWithQuantity
					.entrySet().iterator();
			int selectedItemCount = 0;
			totalprice = 0;
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				selectedItemCount = selectedItemCount
						+ Integer.parseInt(pairs.getValue().trim());
				totalprice = totalprice
						+ (Double
								.parseDouble(itemWithPrice.get(pairs.getKey())) * Integer
								.parseInt(pairs.getValue().trim()));
				// it.remove(); // avoids a ConcurrentModificationException
			}

//			if (totalprice > 0) {
//				next.setImageResource(R.drawable.active);
//			} else {
//				next.setImageResource(R.drawable.inactive);
//			}

//			cart_num.setText(selectedItemCount + "");
			// cart_itemnumTxt.setText(selectedItemCount + "");
			if (totalprice > 0) {
//				totalAmt.setVisibility(View.VISIBLE);
//
//				next.setVisibility(View.VISIBLE);
				total_points_txt.setText("Total Points: "+totalprice + "/-");
				// }

				TAG_TOTAL_PRICE = String.valueOf(totalprice);

			} else {
				Spanned text = Html.fromHtml("0/-");
 
				total_points_txt.setText("Total Points: "+text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public class CustomCartAdapter extends BaseAdapter {
//		String[] text;
		ArrayList<String> text;
		
		Context context;
 
		LayoutInflater inflater = null;

		public CustomCartAdapter(Context con, ArrayList<String> cart_item_text ) {
			// TODO Auto-generated constructor stub
			text = cart_item_text;
			context = con;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return text.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}
 
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public class Holder {
			TextView tv_qty_price, tv_unit_price, tv_subtotal_price,
					tv_label_name, tv_unit_text,tv_qty_text,tv_subtotal_text,tv_close;
			ImageView iv_label;
			RelativeLayout rr_close;
		}
 
		@SuppressLint({ "ViewHolder", "InflateParams", "CutPasteId" })
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			final Holder holder = new Holder();
			View rowView;
			rowView = inflater.inflate(R.layout.shopping_cart_row, null);
			
			holder.iv_label= (ImageView) rowView.findViewById(R.id.iv_label); 
			
			holder.tv_unit_text= (TextView) rowView.findViewById(R.id.tv_qty_price);
			holder.tv_qty_text= (TextView) rowView.findViewById(R.id.tv_qty_text);
			holder.tv_subtotal_text= (TextView) rowView.findViewById(R.id.tv_subtotal_text);
			
//			tv_qty_price
			
			holder.tv_qty_price = (TextView) rowView.findViewById(R.id.tv_qty_price);
			holder.tv_unit_price = (TextView) rowView.findViewById(R.id.tv_unit_price);
			holder.tv_subtotal_price = (TextView) rowView.findViewById(R.id.tv_subtotal_price);
			holder.tv_label_name = (TextView) rowView.findViewById(R.id.tv_label_name);
			holder.rr_close = (RelativeLayout) rowView.findViewById(R.id.rr_close);
			 
			
			holder.tv_qty_price.setTypeface(Constants.ProximaNova_Regular); 
			holder.tv_unit_price.setTypeface(Constants.ProximaNova_Regular); 
			holder.tv_subtotal_price.setTypeface(Constants.ProximaNova_Regular); 
			holder.tv_label_name.setTypeface(Constants.ProximaNova_Regular); 
			
			holder.tv_unit_text.setTypeface(Constants.ProximaNova_Regular); 
			holder.tv_qty_text.setTypeface(Constants.ProximaNova_Regular); 
			holder.tv_subtotal_text.setTypeface(Constants.ProximaNova_Regular); 
			
			holder.tv_label_name.setText(""+cart_title.get(position));
			
			TAG_UNIT_PRICE=cart_unit_price.get(position);

//			TAG_UNIT_PRICE=cart_unit_price[position];

			holder.tv_unit_price.setText(TAG_UNIT_PRICE);
			holder.tv_qty_price.setText(""+cart_default_qty.get(position));
			try{
				System.out.println("size of array"+itemWithQuantity+":"+itemWithQuantity.get(""+position));
				
//				TAG_QTY_PRICE= itemWithQuantity.get(""+cart_default_qty.get(position));
				TAG_QTY_PRICE= cart_default_qty.get(position);
			holder.tv_qty_price.setText(TAG_QTY_PRICE);
			
			subTotal = Double.parseDouble(TAG_UNIT_PRICE)*Integer.parseInt(TAG_QTY_PRICE);
			
			holder.tv_subtotal_price.setText(String.valueOf(subTotal));
//			updateQtyTextView();
			
			 
			
			// download and display image from url
			String url="";
			if(cart_img.get(position).contains("http"))
 			{
				url=cart_img.get(position);
			}
			else
			{
				url="http://stg.incentiveweb.com/products/"+cart_img.get(position);
			}
			
			/*ImageLoader imageLoader = ImageLoader.getInstance();
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.cacheInMemory(true).cacheOnDisc(true)
					.resetViewBeforeLoading(true)
					.showImageForEmptyUri(R.drawable.bag3)
					.showImageOnFail(R.drawable.bag3)
					.showImageOnLoading(R.drawable.bag3).build();
			
			imageLoader.displayImage(
					url.replace("\'", "%20")
							.trim(), holder.iv_label, options);*/
			 
			Picasso.with(getApplicationContext())
			.load(url.replace("\'", "%20"))
			.placeholder(R.drawable.login_logo)
			.error(R.drawable.bag3).fit().into(holder.iv_label);
			
			
			holder.tv_close= (TextView) rowView.findViewById(R.id.tv_close);
			holder.tv_close.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Autodfsf-generated method stub
					
					productChildID=cart_id.get(position);
					if (util.IsNetConnected(CartActvity1.this)) {
						new DeleteCartItem().execute();
						 } else {
						 util.showAlertNoInternetService(CartActvity1.this);
						 }
				}
			});
			
			}catch(Exception e){
				holder.tv_qty_price.setText("0");
				holder.tv_subtotal_price.setText("0");
				e.printStackTrace();
			}
			final int posi = position;
			holder.tv_qty_price.setOnClickListener(new OnClickListener() {
				
			
				@SuppressLint("InflateParams")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub 

//					String start = (String) product.get(TAG_MINQTY);
//					String end = (String) product.get(TAG_MAXQTY);
					int start1 = 1;
					int end1 =9;
					
					System.out.println("size of array"+itemWithQuantity);

					int size = end1 - start1 + 2;
					array = new Integer[size];
//					array[0] = 1;
					for (int i = 0; i < size; i++) {
						array[i] = start1++;
					}

					LayoutInflater inflater = getLayoutInflater();
					View convertView = (View) inflater.inflate(R.layout.dialog,
							null);

					// displaying alert dialog with list of
					// numbers
					alertDialog = new Dialog(CartActvity1.this);
					alertDialog.setContentView(convertView);

					alertDialog.setTitle(getResources().getString(
							R.string.selectQty));

					ListView lv = (ListView) convertView
							.findViewById(R.id.listView);

					ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(
							CartActvity1.this,
							android.R.layout.simple_list_item_1, array);
					lv.setAdapter(adapter1);
					lv.setOnItemClickListener(new OnItemClickListener() {

						// TODO Auto-generated method stub

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							// TODO Auto-generated method stub

							System.out.println("posi " + posi);

							System.out.println("position " + position);

							System.out.println("number"
									+ Integer.toString(array[position]));
							
						
							holder.tv_qty_price.setText(Integer
									.toString(array[position]));
							
//							int fgh=array[position]*Integer.parseInt(cart_unit_price[position]);
							
//							holder.tv_subtotal_price.setText(String.valueOf(fgh));
							
							
							try {

								if (array[position] == 0) {
									String val = itemWithQuantity
											.get("" + posi);
									System.out.println("position" + position
											+ val + array[position]);
									itemWithQuantity.remove("" + posi);
//									itemWithId.remove("" + posi);
									itemWithPrice.remove("" + posi);
//									itemWithName.remove("" + posi);
									
									subTotal = 0.0;
									holder.tv_subtotal_price.setText(String.valueOf(subTotal));
								} else {
									itemWithQuantity.put("" + posi, ""
											+ array[position]);
//									itemWithId.put("" + posi, "" + CatId[posi]);

									itemWithPrice.put("" + posi, cart_unit_price.get(posi));
//									itemWithName.put("" + posi, dishes[posi]);
									
									subTotal = Double.parseDouble(cart_unit_price.get(posi))*array[position];
									System.out.println("priceeeee"+cart_unit_price.get(posi)+""+array[position]);
									holder.tv_subtotal_price.setText(String.valueOf(subTotal));
//									
								}

								updateQtyTextView();
							} catch (Exception e) {
								e.printStackTrace();
							}

							alertDialog.dismiss();
						}
					});

					alertDialog.show();

				}
			});

			
			return rowView;
		}

	} 
	
	
	
	private class CartsListask extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(CartActvity1.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnGetCartItems&AuthToken=" + Constants.AUTH_TOKEN
					+ "&userid=" + Constants.User_Id +"&Clientid="+Constants.clientID+"&clientRatio="
					+ Constants.clientRatio + "&clienttaxpercent="
					+ Constants.clienttaxpercent ); 

		/*	AuthToken
			userid
			Clientid
			Clientratio
			clienttaxpercent*/
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					cart_unit_price.clear();
					cart_title.clear();
					cart_id.clear();
					cart_img.clear();
					cart_default_qty.clear();

					if (null == result || result.length() == 0) {

					} else {
						JSONObject jObject = new JSONObject(result);
						JSONArray	carts_jsonArrays = jObject.getJSONArray("DATA");
						if(carts_jsonArrays.length()>0)
						{
				 			for (int i = 0; i <carts_jsonArrays.length(); i++) {
								JSONObject jsonObject = carts_jsonArrays.getJSONObject(i);
								if(jsonObject.has("IMAGEURL"))
								{
									cart_img.add(""+jsonObject.getString("IMAGEURL"));
									
								}
								if(jsonObject.has("USERUNITFINALCOST"))
								{
									cart_unit_price.add(""+jsonObject.getString("USERUNITFINALCOST"));
									
								}
								if(jsonObject.has("PRODUCTTITLE"))
								{
									cart_title.add(""+jsonObject.getString("PRODUCTTITLE"));
									
								}
								if(jsonObject.has("QUANTITY"))
								{
									cart_default_qty.add(""+jsonObject.getString("QUANTITY"));
									
								}
								if(jsonObject.has("PRODUCTID"))
								{
									cart_id.add(""+jsonObject.getString("PRODUCTCHILDID"));
									
								} 
							}
							
							
							carts_list.setAdapter(new CustomCartAdapter(CartActvity1.this, cart_unit_price));	 

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
	
	
	private class DeleteCartItem extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(CartActvity1.this); 
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnDeleteCartItem&AuthToken=" + Constants.AUTH_TOKEN
					+ "&userid=" + Constants.User_Id +"&productChildID="+productChildID ); 

		/*	AuthToken
		Userid
		productChildID
		 */
 
		} 

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
					

					if (null == result || result.length() == 0) {

					} else {
//						System.out.println("delete"+result);
//						Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
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
	
	
	@Override
	public void onBackPressed() {
	 	// TODO Auto-generated method stub 
		super.onBackPressed(); 
		startActivity(new Intent(CartActvity1.this, DashBorad.class));
		CartActvity1.this.finish();
	}
}
