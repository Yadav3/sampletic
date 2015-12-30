package com.taico.android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.taico.android.EntertainmentTickets.CalendarAdapter;
import com.taico.android.util.Utility;

public class TravelConcierge extends Activity {

	Utility util;
	TextView txt_trip_details, txt_bottom, txt_flights, txt_hotel,
			txt_from_state, txt_to_state, txt_rooms, txt_guest, txt_round_trip,
			txt_one_way, txt_when, txt_checkin, et_checkindt, txt_checkout,
			et_checkout1, date_of_from, date_of_from1, date_of_from12,
			date_of_from13;
	LinearLayout ll_booking_flight, ll_booking_hotel;
	ScrollView scr_id;
	Button request_ticket_btn,btn_home;
	EditText et_name, et_client, et_email, et_dayPhone, et_evgPhone, et_city,
			et_tocity, et_airport, et_toairport, et_where;
	String str_flighttype = "Round-trip", str_from_state = "", str_to_state = "",
			str_rooms="", str_gust = "", str_departdate = "",
			str_returndate = "",str_button="0",str_checkindate="",str_checkoutdate="", flight_hotel_url="";
	ImageView	menu ;
	RelativeLayout favLayout,cartLayout;
	     
	
//////for calender
	public GregorianCalendar month, itemmonth;// calendar instances.

	public CalendarAdapter adapter;// adapter instance
	LinearLayout rLayout;
	ArrayList<String> date;
	TextView title;
	String strDate,strDate1,strDate2;
	 int today;
	//for adapter
	
	public  List<String> dayString;
	private View previousView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travel_concierge);

		util = new Utility(TravelConcierge.this);
		
		
		setUi();
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TravelConcierge.this.finish();
 
			} 
		});
		
		txt_flights.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/***  Flights related data */
				et_city.setText("");
				et_tocity.setText("");
				et_airport.setText("");
				et_toairport.setText("");
				str_from_state="";
				str_to_state="";
				str_flighttype="";
				str_departdate="";
				str_returndate="";
				
				///////////
				
				/***  Hotel related data */
				et_where.setText("");
				str_checkindate="";
				str_checkoutdate="";
				str_rooms="";
				str_gust="";
				
				try
				{
					Calendar cal_t = Calendar.getInstance();
					 SimpleDateFormat sdf_t = new SimpleDateFormat("MM/dd/yyyy");
					 date_of_from1.setText(""+sdf_t.format(cal_t.getTime()));
					 date_of_from13.setText(""+sdf_t.format(cal_t.getTime()));
					 et_checkindt.setText(""+sdf_t.format(cal_t.getTime()));
					 et_checkout1.setText(""+sdf_t.format(cal_t.getTime()));
//				     str_date=""+sdf_t.format(cal_t.getTime());
					
				}catch(Exception e)
				{
					
				}
				
				
				str_button="0";
				ll_booking_flight.setVisibility(View.VISIBLE);
				ll_booking_hotel.setVisibility(View.GONE);
				txt_flights.setBackgroundColor(Color.WHITE);
				txt_hotel.setBackgroundColor(Color.parseColor("#e8e8e8"));
				scr_id.scrollTo(0, request_ticket_btn.getBottom());

			}
		});

		txt_hotel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/***  Flights related data */
				et_city.setText("");
				et_tocity.setText("");
				et_airport.setText("");
				et_toairport.setText("");
				str_from_state="";
				str_to_state="";
				str_flighttype="";
				str_departdate="";
				str_returndate="";
				
				///////////
				
				/***  Hotel related data */
				et_where.setText("");
				str_checkindate="";
				str_checkoutdate="";
				str_rooms="";
				str_gust="";
				
				try
				{
					Calendar cal_t = Calendar.getInstance();
					 SimpleDateFormat sdf_t = new SimpleDateFormat("MM/dd/yyyy");
					 date_of_from1.setText(""+sdf_t.format(cal_t.getTime()));
					 date_of_from13.setText(""+sdf_t.format(cal_t.getTime()));
					 et_checkindt.setText(""+sdf_t.format(cal_t.getTime()));
					 et_checkout1.setText(""+sdf_t.format(cal_t.getTime()));
//				     str_date=""+sdf_t.format(cal_t.getTime());
					
				}catch(Exception e)
				{
					
				}
				
				str_button="1";
				ll_booking_flight.setVisibility(View.GONE);
				ll_booking_hotel.setVisibility(View.VISIBLE);
				txt_hotel.setBackgroundColor(Color.WHITE);
				txt_flights.setBackgroundColor(Color.parseColor("#e8e8e8"));
//				scr_id.scrollTo(0, txt_flights.getTop());
				scr_id.scrollTo(0, request_ticket_btn.getBottom()); 
			}
		});

		txt_from_state.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showList(txt_from_state, util.statesDataS(), "Select  state",1);
			}
		});

		txt_to_state.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showList(txt_to_state, util.statesDataS(), "Select  state",2);
			}
		});

		txt_rooms.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showList(txt_rooms, util.roomsNGuestDataMethod(10),
						"Select room",3);
			}
		});

		txt_guest.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showList(txt_guest, util.roomsNGuestDataMethod(10),
						"Select  guest",4);
			}
		});

		txt_round_trip.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str_flighttype="Round-trip";
				txt_round_trip.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.radio_checked, 0, 0, 0);
				txt_one_way.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.radio_unchecked, 0, 0, 0);
			}
		});

		txt_one_way.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str_flighttype="One-way";
				txt_round_trip.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.radio_unchecked, 0, 0, 0);
				txt_one_way.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.radio_checked, 0, 0, 0);
			}
		});
		
		
		request_ticket_btn.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (util.IsNetConnected(TravelConcierge.this)) {
//	fnSendTravelConceigeEmail/	authToken,txtEmail,	txtName,txtClient,txtDayPhone,txtEveningPhone,fromcity,	tocity,	
// fromairport,toairport,flighttype,fromcity,fromstate,fromairport,tocity,	tostate,toairport,departdate,returndate,hotelinfo,	checkindate,checkoutdate,rooms,	guests,

				      if(str_button.equalsIgnoreCase("0"))
				      { 
				    	         if (  et_email.getText().toString().length() == 0
									|| et_name.getText().toString().length() == 0
									|| et_client.getText().toString().length() == 0
									|| et_dayPhone.getText().toString().length() == 0
									|| et_evgPhone.getText().toString().length() == 0
									|| et_city.getText().toString().length() == 0
							    	|| et_tocity.getText().toString().length() == 0
							    	|| et_airport.getText().toString().length() == 0
							    	|| et_toairport.toString().length() == 0
								    || str_flighttype.length() == 0
								    || str_from_state.length() == 0
								    || str_to_state.length() == 0
								    || str_departdate.length() == 0
									|| str_returndate.length() == 0)
									 {
								util.inputValidation("Please enter all the fields");

							} else {
//									Matcher matcher3 = Constants.pattern1
//											.matcher(email_txt.getText().toString().trim());
//									
									final String email = et_email.getText().toString().trim();
									if (!util.isValidEmail(email)) {
										util.inputValidation("Please enter valid email id");
									}
									else
									{

											if (et_dayPhone.length() < 10) {
												util.inputValidation("Please enter valid day phone number");
											} else {
												new TravelConciergeTasks().execute();
												/*if (num_of_tickets.length() < 0) {
													util.inputValidation("Please select tickets");
												}
												else
												{
													
													if (str_state.length() < 0) {
														util.inputValidation("Please select state");
													}
													else
													{
														 new TravelConciergeTasks().execute();
													}
												}*/
												
												
												
												
											}
									}
									
									
								} 
				      }
				      else if(str_button.equalsIgnoreCase("1"))
				      { 
				    	
				    	         if (  et_email.getText().toString().length() == 0
									|| et_name.getText().toString().length() == 0
									|| et_client.getText().toString().length() == 0
									|| et_dayPhone.getText().toString().length() == 0
									|| et_evgPhone.getText().toString().length() == 0
									|| et_where.getText().toString().length() == 0
								    || str_checkindate.length() == 0
								    || str_checkoutdate.length() == 0
								    || str_rooms.length() == 0
									|| str_gust.length() == 0)
									 {
								util.inputValidation("Please enter all the fields");

							} else {
//									Matcher matcher3 = Constants.pattern1
//											.matcher(email_txt.getText().toString().trim());
//									
									final String email = et_email.getText().toString().trim();
									if (!util.isValidEmail(email)) {
										util.inputValidation("Please enter valid email id");
									}
									else
									{

											if (et_dayPhone.length() < 10) {
												util.inputValidation("Please enter valid day phone number");
											} else {
												new TravelConciergeTasks().execute();
												/*if (num_of_tickets.length() < 0) {
													util.inputValidation("Please select tickets");
												}
												else
												{
													
													if (str_state.length() < 0) {
														util.inputValidation("Please select state");
													}
													else
													{
														 new TravelConciergeTasks().execute();
													}
												}*/
												
												
												
												
											}
									}
									
									
								}
				      }

			} else {
				util.showAlertNoInternetService(TravelConcierge.this);
			}
			}
		});
		
		try
		{
			Calendar cal_t = Calendar.getInstance();
			 SimpleDateFormat sdf_t = new SimpleDateFormat("MM/dd/yyyy");
			 date_of_from1.setText(""+sdf_t.format(cal_t.getTime()));
			 date_of_from13.setText(""+sdf_t.format(cal_t.getTime()));
			 et_checkindt.setText(""+sdf_t.format(cal_t.getTime()));
			 et_checkout1.setText(""+sdf_t.format(cal_t.getTime()));
//		     str_date=""+sdf_t.format(cal_t.getTime());
			
		}catch(Exception e)
		{
			
		}
		
		date_of_from1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cal_dialog(date_of_from1,1);
			}
		});
		
date_of_from13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cal_dialog(date_of_from13,2); 
			}
		});


et_checkindt.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		cal_dialog(et_checkindt,3); 
	}
});

et_checkout1.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		cal_dialog(et_checkout1,4);  
	}
});

	}
	
	

	protected void cal_dialog(final TextView txt,final int flg) {
		final Dialog dialog = new Dialog(TravelConcierge.this);
		// android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		dialog.setContentView(R.layout.calendar);

		Locale.setDefault(Locale.US);

		rLayout = (LinearLayout)dialog. findViewById(R.id.text);
		month = (GregorianCalendar) GregorianCalendar.getInstance();
		itemmonth = (GregorianCalendar) month.clone();


		adapter = new CalendarAdapter(TravelConcierge.this, month);

		GridView gridview = (GridView) dialog.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		 title = (TextView)dialog. findViewById(R.id.title);
		
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		RelativeLayout previous = (RelativeLayout) dialog.findViewById(R.id.previous);
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
       strDate = sdf.format(cal.getTime());
       
       SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM yyyy");
       strDate1 = sdf1.format(cal.getTime());
       
       SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
       strDate2 = sdf2.format(cal.getTime());
        System.out.println("Current date in String Format: " + strDate);
        System.out.println("Current date in String Format1: " + strDate1);
        String sampleString = strDate1;
        
        String strTodaydate= strDate2;
        String[] day_date = sampleString.split(" ");
        System.out.println("Current date in String Format2: " + strTodaydate);
        
        
        today=Integer.parseInt(day_date[0]);
        System.out.println("The number of dates is: " + today);

        
        previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(title.getText().toString().equals(strDate)){
				
				
				refreshCalendar();
				}else{
					setPreviousMonth();
					refreshCalendar();
				}
			}
		});
        
        RelativeLayout next = (RelativeLayout) dialog.findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();

			}
		});
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// removing the previous view if added
				if (((LinearLayout) rLayout).getChildCount() > 0) {
					((LinearLayout) rLayout).removeAllViews();
				}
//				desc = new ArrayList<String>();
				date = new ArrayList<String>();
				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = dayString.get(position);
//				System.out.println("selectedGridDate"+selectedGridDate);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");
				
				// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
//				Toast.makeText(getApplicationContext(), gridvalue, 200).show();
				// navigate to next or previous month on clicking offdays.
				
				
				if ((gridvalue > 10) && (position < 8)) {
//					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
//					setNextMonth();
					refreshCalendar();
				}else if(title.getText().toString().equals(strDate) && gridvalue< today){
				
//				if(gridvalue< today){
					refreshCalendar();
//				}
					
				}else{
					dialog.dismiss();
					System.out.println("selectedGridDate"+selectedGridDate);
					
					
					try
					 {
					    String currentDate = selectedGridDate;//"2014-10-01 00:00:00.0";
					    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    Date tempDate=simpleDateFormat.parse(currentDate);
					    SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy");           
					    System.out.println("Output date is = "+outputDateFormat.format(tempDate));
					    txt.setText(""+outputDateFormat.format(tempDate));
					    if(flg==1)
					    {
					    	str_departdate=""+outputDateFormat.format(tempDate);
					    }
					    else if(flg==2)
					    {
					    	str_returndate=""+outputDateFormat.format(tempDate);
					    }
					    else if(flg==3)
					    {
					    	str_checkindate=""+outputDateFormat.format(tempDate);
					    }
					    else if(flg==4)
					    {
					    	str_checkoutdate=""+outputDateFormat.format(tempDate);
					    }
					    else
					    {
					    	
					    }
//					    str_date=""+outputDateFormat.format(tempDate);
					  } catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}


			}

		});
		
		dialog.show();
		
	}

	private void setUi() {
		// TODO Auto-generated method stub
		
		scr_id = (ScrollView) findViewById(R.id.scr_id);
		txt_trip_details = (TextView) findViewById(R.id.txt_trip_details);
		txt_bottom = (TextView) findViewById(R.id.txt_bottom);
		txt_flights = (TextView) findViewById(R.id.txt_flights);
		txt_hotel = (TextView) findViewById(R.id.txt_hotel);
		txt_from_state = (TextView) findViewById(R.id.txt_from_state);
		txt_to_state = (TextView) findViewById(R.id.txt_to_state);
		txt_rooms = (TextView) findViewById(R.id.txt_rooms);
		txt_guest = (TextView) findViewById(R.id.txt_guest);
		txt_round_trip = (TextView) findViewById(R.id.txt_round_trip);
		txt_one_way = (TextView) findViewById(R.id.txt_one_way);
		date_of_from = (TextView) findViewById(R.id.date_of_from);
		date_of_from1 = (TextView) findViewById(R.id.date_of_from1);
		date_of_from12 = (TextView) findViewById(R.id.date_of_from12);
		date_of_from13 = (TextView) findViewById(R.id.date_of_from13);
		txt_when = (TextView) findViewById(R.id.txt_when);
		txt_checkin = (TextView) findViewById(R.id.txt_checkin);
		et_checkindt = (TextView) findViewById(R.id.et_checkindt);
		txt_checkout = (TextView) findViewById(R.id.txt_checkout);
		et_checkout1 = (TextView) findViewById(R.id.et_checkout1);
		request_ticket_btn = (Button) findViewById(R.id.request_ticket_btn);
		ll_booking_flight = (LinearLayout) findViewById(R.id.ll_booking_flight);
		ll_booking_hotel = (LinearLayout) findViewById(R.id.ll_booking_hotel);

		et_name = (EditText) findViewById(R.id.et_name);
		et_client = (EditText) findViewById(R.id.et_client);
		et_email = (EditText) findViewById(R.id.et_email);
		et_dayPhone = (EditText) findViewById(R.id.et_dayPhone);
		et_evgPhone = (EditText) findViewById(R.id.et_evgPhone);
		et_city = (EditText) findViewById(R.id.et_city);
		et_tocity = (EditText) findViewById(R.id.et_tocity);
		et_airport = (EditText) findViewById(R.id.et_airport);
		et_toairport = (EditText) findViewById(R.id.et_toairport);
		et_where = (EditText) findViewById(R.id.et_where);
		
		txt_trip_details.setTypeface(Constants.ProximaNova_Regular);
		txt_bottom.setTypeface(Constants.ProximaNova_Regular);
		txt_flights.setTypeface(Constants.ProximaNova_Regular);
		txt_hotel.setTypeface(Constants.ProximaNova_Regular);
		txt_from_state.setTypeface(Constants.ProximaNova_Regular);
		txt_to_state.setTypeface(Constants.ProximaNova_Regular);
		txt_rooms.setTypeface(Constants.ProximaNova_Regular);
		txt_guest.setTypeface(Constants.ProximaNova_Regular);
		txt_round_trip.setTypeface(Constants.ProximaNova_Regular);
		txt_one_way.setTypeface(Constants.ProximaNova_Regular);
		date_of_from.setTypeface(Constants.ProximaNova_Regular);
		date_of_from1.setTypeface(Constants.ProximaNova_Regular);
		date_of_from12.setTypeface(Constants.ProximaNova_Regular);
		date_of_from13.setTypeface(Constants.ProximaNova_Regular);
		txt_when.setTypeface(Constants.ProximaNova_Regular);
		txt_checkin.setTypeface(Constants.ProximaNova_Regular);
		et_checkindt.setTypeface(Constants.ProximaNova_Regular);
		txt_checkout.setTypeface(Constants.ProximaNova_Regular);
		et_checkout1.setTypeface(Constants.ProximaNova_Regular);
		request_ticket_btn.setTypeface(Constants.ProximaNova_Regular);
		et_name.setTypeface(Constants.ProximaNova_Regular);
		et_client.setTypeface(Constants.ProximaNova_Regular);
		et_email.setTypeface(Constants.ProximaNova_Regular);
		et_dayPhone.setTypeface(Constants.ProximaNova_Regular);
		et_evgPhone.setTypeface(Constants.ProximaNova_Regular);
		et_city.setTypeface(Constants.ProximaNova_Regular);
		et_tocity.setTypeface(Constants.ProximaNova_Regular);
		et_airport.setTypeface(Constants.ProximaNova_Regular);
		et_toairport.setTypeface(Constants.ProximaNova_Regular);
		et_where.setTypeface(Constants.ProximaNova_Regular);
		
		et_name.setText(Constants.FIRSTNAME);
		et_email.setText(Constants.EMAIL);
		et_dayPhone.setText(Constants.DAYPHONE);
		et_evgPhone.setText(Constants.EVENINGPHONE);  
		et_client.setText(Constants.CLIENTNAME); 
		

		String trip_details = "<html><body><p>Want to take a trip?  You can use your points for a travel package of your choice.</p><p>Please fill out the form(s) and <strong style='font-weight:bold;'>one of our travel experts will call you to book your package</strong>.</p>"
				+ "<p>Hours of operation 11AM to 6PM eastern time Monday - Friday.</p><p>Although our travel coordinator usually gets back to you within a few hours of submission during business hours, there may be times when volume is high, so please allow a full day for our coordinator to get back to you.</p>"
				+ "<p style='font-style:italic;'>* = required field</p></body></html>";

		String bottom_txt = "<html><body><p style='font-weight:bold;'><strong style='font-weight:bold;'>*Trips do not qualify for any frequent flyer miles/programs.</strong></p><p style='font-weight:bold;'><strong style='font-weight:bold;'>*Once you book your reservation, to change flights/hotel room/car rental you must contact the airlines/hotel/rental agency directly. Charges may apply.</strong></p></body></html>";
		txt_trip_details.setText(Html.fromHtml(trip_details));
		txt_bottom.setText(Html.fromHtml(bottom_txt));
		
		menu = (ImageView) findViewById(R.id.menu);
		favLayout = (RelativeLayout) findViewById(R.id.favLayout);
	cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);
 
	menu.setVisibility(View.GONE);
	favLayout.setVisibility(View.GONE);
	cartLayout.setVisibility(View.GONE);
	
	btn_home = (Button) findViewById(R.id.btn_home);
	btn_home.setText("Back"); 
	btn_home.setTypeface(Constants.ProximaNova_Regular);
		
//		fnSendTravelConceigeEmail/	authToken,txtEmail,	txtName,txtClient,txtDayPhone,txtEveningPhone,fromcity,	tocity,	fromairport,toairport,flighttype,fromcity,fromstate,fromairport,tocity,	tostate,toairport,departdate,returndate,hotelinfo,	checkindate,checkoutdate,rooms,	guests,
	}

	protected void showList(final TextView txt_from_state2,
			final ArrayList<String> states, String heading,final int flg) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = getLayoutInflater();
		View convertView = (View) inflater.inflate(R.layout.dialog, null);
		// displaying alert dialog with list of
		// numbers
		final Dialog alertDialog = new Dialog(TravelConcierge.this);
		alertDialog.setContentView(convertView);

		alertDialog.setTitle(heading);

		ListView lv = (ListView) convertView.findViewById(R.id.listView);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
				TravelConcierge.this, android.R.layout.simple_list_item_1,
				states);
		lv.setAdapter(adapter1);

		lv.setOnItemClickListener(new OnItemClickListener() {

			// TODO Auto-generated method stub

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// state_codes=Constants.states_ids.get(position);
				txt_from_state2.setText(states.get(position));
				if(flg==1)
				{
					str_from_state=Constants.states.get(position);
				}
				else if(flg==2)
				{
					str_to_state=Constants.states.get(position);
				}else if(flg==3)
				{
					str_rooms=states.get(position);
				}else if(flg==4)
				{
					str_gust=states.get(position);
				}
				else
				{
					str_from_state="";
					str_to_state="";
					str_rooms="";
					str_gust="";
				}
				
				
				alertDialog.dismiss();
			}
		});
		alertDialog.show();

	}
	
	private class TravelConciergeTasks extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(TravelConcierge.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		} 
		@Override
		protected String doInBackground(String... params) {
			
				 flight_hotel_url=Constants.MAIN_HOST
				+ "method=fnSendTravelConceigeEmail" 
				+ "&AuthToken="+Constants.AUTH_TOKEN 
				+ "&txtEmail="+et_email.getText().toString()
				+ "&txtName="+ et_name.getText().toString()
				+ "&txtClient="+ et_client.getText().toString() 
				+ "&txtDayPhone="+ et_dayPhone.getText().toString() 
				+ "&txtEveningPhone="+ et_evgPhone.getText().toString() 
				+ "&fromcity="+ et_city.getText().toString() 
				+ "&tocity="+et_tocity.getText().toString()
				+ "&fromairport="+ et_airport.getText().toString()
				+ "&toairport="+ et_toairport.getText().toString() 
				+ "&fromstate="+ str_from_state
				+ "&tostate="+ str_to_state.toString()
				+ "&flighttype="+ str_flighttype
				+ "&departdate="+ str_departdate
		 		+ "&returndate="+ str_returndate.toString()
		 		+ "&hotelinfo="+ et_where.getText().toString() 
				+ "&checkindate="+str_checkindate
				+ "&checkoutdate="+str_checkoutdate.toString()
				+ "&rooms="+ str_rooms.toString() 
				+ "&guests="+ str_gust; 
				 
			return WebServiceCalls.getJSONString(flight_hotel_url);  

		}  

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try { 
System.out.println("fnSendTravelConceigeEmail"+result); 
					if (null == result || result.length() == 0) {

					} else {
						
						if(result.contains("false"))
						{
							AlertDialog.Builder altDialog = new AlertDialog.Builder(TravelConcierge.this);
							 altDialog.setMessage("Submitted failed"); 
							altDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
									if (myProgressDialog.isShowing())
										myProgressDialog.dismiss();
								}
							});
							altDialog.show();
						}else if(result.contains("true"))
						{
							
							AlertDialog.Builder altDialog = new AlertDialog.Builder(TravelConcierge.this);
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
	
	
	protected void setNextMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMaximum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) + 1),
					month.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) + 1);
		}

	}

	protected void setPreviousMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}

	}

//	protected void showToast(String string) {
//		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
//	}

	public void refreshCalendar() {
		

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
//		handler.post(calendarUpdater); // generate some calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	
	public class CalendarAdapter extends BaseAdapter {
		private Context mContext;

		private java.util.Calendar month;
		public GregorianCalendar pmonth; // calendar instance for previous month
		/**
		 * calendar instance for previous month for getting complete view
		 */
		public GregorianCalendar pmonthmaxset;
		private GregorianCalendar selectedDate;
		int firstDay;
		int maxWeeknumber;
		int maxP;
		int calMaxP;
		int lastWeekDay;
		int leftDays;
		int mnthlength;
		String itemvalue, curentDateString;
		SimpleDateFormat df;

		private ArrayList<String> items;
 
		public CalendarAdapter(Context c, GregorianCalendar monthCalendar) {
			dayString = new ArrayList<String>();
			Locale.setDefault(Locale.US);
			month = monthCalendar;
			selectedDate = (GregorianCalendar) monthCalendar.clone();
			mContext = c;
			month.set(GregorianCalendar.DAY_OF_MONTH, 1);
			this.items = new ArrayList<String>();
			df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			curentDateString = df.format(selectedDate.getTime());
			refreshDays();
		}

		public void setItems(ArrayList<String> items) {
			for (int i = 0; i != items.size(); i++) {
				if (items.get(i).length() == 1) {
					items.set(i, "0" + items.get(i));
				}
			}
			this.items = items;
		}

		public int getCount() {
			return dayString.size();
		}

		public Object getItem(int position) {
			return dayString.get(position);
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new view for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			TextView dayView;
			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				LayoutInflater vi = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.calendar_item, null);

			}
			dayView = (TextView) v.findViewById(R.id.date);
			// separates daystring into parts.
			String[] separatedTime = dayString.get(position).split("-");
			// taking last part of date. ie; 2 from 2012-12-02
			String gridvalue = separatedTime[2].replaceFirst("^0*", "");
			// checking whether the day is in current month or not.
			if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
				// setting offdays to white color.
				dayView.setTextColor(Color.TRANSPARENT);
				dayView.setClickable(false);
				dayView.setFocusable(false);
//				dayView.setOnTouchListener(false);
				
				
			} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
				dayView.setTextColor(Color.TRANSPARENT);
				dayView.setClickable(false);
				dayView.setFocusable(false);
				
				
			} else if(title.getText().toString().equals(strDate)){
				
				if((Integer.parseInt(gridvalue) < today)){
				
				dayView.setTextColor(Color.WHITE);
				dayView.setClickable(false);
				dayView.setFocusable(false);
				}
			}else {
				// setting curent month's days in blue color.
				dayView.setTextColor(Color.BLACK);
			}

			if (dayString.get(position).equals(curentDateString)) {
				setSelected(v);
				previousView = v;
			} else {
				v.setBackgroundResource(R.drawable.list_item_background);
			}
			dayView.setText(gridvalue);

			// create date string for comparison
			String date = dayString.get(position);

			if (date.length() == 1) {
				date = "0" + date;
			}
			String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
			if (monthStr.length() == 1) {
				monthStr = "0" + monthStr;
			}

			// show icon if date is not empty and it exists in the items array
			ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
			if (date.length() > 0 && items != null && items.contains(date)) {
				iw.setVisibility(View.VISIBLE);
			} else {
				iw.setVisibility(View.INVISIBLE);
			}
			return v;
		}

		public View setSelected(View view) {
			if (previousView != null) {
				previousView.setBackgroundResource(R.drawable.list_item_background);
			}
			previousView = view;
			view.setBackgroundResource(R.drawable.button_shape1);
			return view;
		}

		public void refreshDays() {
			// clear items
			items.clear();
			dayString.clear();
			Locale.setDefault(Locale.US);
			pmonth = (GregorianCalendar) month.clone();
			// month start day. ie; sun, mon, etc
			firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
			// finding number of weeks in current month.
			maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
			// allocating maximum row number for the gridview.
			mnthlength = maxWeeknumber * 7;
			maxP = getMaxP(); // previous month maximum day 31,30....
			calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
			/**
			 * Calendar instance for getting a complete gridview including the three
			 * month's (previous,current,next) dates.
			 */
			pmonthmaxset = (GregorianCalendar) pmonth.clone();
			/**
			 * setting the start date as previous month's required date.
			 */
			pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

			/**
			 * filling calendar gridview.
			 */
			for (int n = 0; n < mnthlength; n++) {

				itemvalue = df.format(pmonthmaxset.getTime());
				pmonthmaxset.add(GregorianCalendar.DATE, 1);
				dayString.add(itemvalue);

			}
		}

		private int getMaxP() {
			int maxP;
			if (month.get(GregorianCalendar.MONTH) == month
					.getActualMinimum(GregorianCalendar.MONTH)) {
				pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
						month.getActualMaximum(GregorianCalendar.MONTH), 1);
			} else {
				pmonth.set(GregorianCalendar.MONTH,
						month.get(GregorianCalendar.MONTH) - 1);
			}
			maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

			return maxP;
		}
	}

}
