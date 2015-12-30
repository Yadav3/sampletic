package com.taico.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
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
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.taico.android.util.Utility;

public class EntertainmentTickets extends Activity {
 
	Utility util;
	TextView entertainment_dec_txt,entertainment_dec_txt1,txt_ticket,event_state,date_of_event_txt,date_of_event_txt1;
	EditText nam_txt,client_txt,email_txt,day_phone_txt,e_phone_txt,event_txt,event_city_txt,preferred_seating_txt;
	Button request_ticket_btn,btn_home;
	String num_of_tickets="",str_state="",str_date="";
	ImageView	menu ;
	RelativeLayout favLayout,cartLayout;
	
	////// for calender
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
		setContentView(R.layout.entertainment_tickets);

		util = new Utility(EntertainmentTickets.this); 
		 
		getUIElements();
		
		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EntertainmentTickets.this.finish();

			} 
		});
		
		request_ticket_btn.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (util.IsNetConnected(EntertainmentTickets.this)) {
					     if ( 
					           nam_txt.getText().toString().length() == 0
							|| client_txt.getText().toString().length() == 0
							|| email_txt.getText().toString().length() == 0
							|| day_phone_txt.getText().toString().length() == 0
							|| e_phone_txt.getText().toString().length() == 0
							|| event_txt.getText().toString().length() == 0
					    	|| event_city_txt.getText().toString().length() == 0
					    	|| preferred_seating_txt.getText().toString().length() == 0
					    	|| num_of_tickets.toString().length() == 0
						    || str_state.toString().length() == 0) 
							 {
						util.inputValidation("Please enter all the fields");

					} else {
//							Matcher matcher3 = Constants.pattern1
//									.matcher(email_txt.getText().toString().trim());
//							
							final String email = email_txt.getText().toString().trim();
							if (!util.isValidEmail(email)) {
								util.inputValidation("Please enter valid email id");
							}
							else
							{

									if (day_phone_txt.length() < 10) {
										util.inputValidation("Please enter valid day phone number");
									} else {
										
										if (num_of_tickets.length() < 0) {
											util.inputValidation("Please select tickets");
										}
										else
										{
											
											if (str_state.length() < 0) {
												util.inputValidation("Please select state");
											}
											else
											{
												 new EntertainmentTicketsTasks().execute();
											}
										}
										
										
										
										
									}
							}
							
							
						}
					
 
				} else {
					util.showAlertNoInternetService(EntertainmentTickets.this);
				}
			}
		});
		
	txt_ticket.setOnClickListener(new OnClickListener() {
		
		@SuppressLint("InflateParams")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showList(txt_ticket,util.roomsNGuestDataMethod(15),"How many tickets would you like",1); 
		
		}
	});
	
	event_state.setOnClickListener(new OnClickListener() {
		
		@SuppressLint("InflateParams") 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showList(event_state,util.statesDataS(),"Select  state",2); 
		
		}
	});
	try
	{
		Calendar cal_t = Calendar.getInstance();
		 SimpleDateFormat sdf_t = new SimpleDateFormat("MM/dd/yyyy");
	     date_of_event_txt1.setText(""+sdf_t.format(cal_t.getTime()));
	     str_date=""+sdf_t.format(cal_t.getTime());
		
	}catch(Exception e)
	{
		
	}
	
	
	date_of_event_txt1.setOnClickListener(new OnClickListener() {
		
		@SuppressLint("InflateParams") 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final Dialog dialog = new Dialog(EntertainmentTickets.this);
			// android.R.style.Theme_Translucent_NoTitleBar);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
			dialog.setContentView(R.layout.calendar);

			Locale.setDefault(Locale.US);

			rLayout = (LinearLayout)dialog. findViewById(R.id.text);
			month = (GregorianCalendar) GregorianCalendar.getInstance();
			itemmonth = (GregorianCalendar) month.clone();


			adapter = new CalendarAdapter(EntertainmentTickets.this, month);

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
//					desc = new ArrayList<String>();
					date = new ArrayList<String>();
					((CalendarAdapter) parent.getAdapter()).setSelected(v);
					String selectedGridDate = dayString.get(position);
//					System.out.println("selectedGridDate"+selectedGridDate);
					String[] separatedTime = selectedGridDate.split("-");
					String gridvalueString = separatedTime[2].replaceFirst("^0*",
							"");
					
					// taking last part of date. ie; 2 from 2012-12-02.
					int gridvalue = Integer.parseInt(gridvalueString);
//					Toast.makeText(getApplicationContext(), gridvalue, 200).show();
					// navigate to next or previous month on clicking offdays.
					
					
					if ((gridvalue > 10) && (position < 8)) {
//						setPreviousMonth();
						refreshCalendar();
					} else if ((gridvalue < 7) && (position > 28)) {
//						setNextMonth();
						refreshCalendar();
					}else if(title.getText().toString().equals(strDate) && gridvalue< today){
					
//					if(gridvalue< today){
						refreshCalendar();
//					}
						
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
						    date_of_event_txt1.setText(""+outputDateFormat.format(tempDate));
						    str_date=""+outputDateFormat.format(tempDate);
						  } catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}


				}

			});
			
			dialog.show();		
		}
	});
	
	}
	private void getUIElements() {
		// TODO Auto-generated method stub
		entertainment_dec_txt = (TextView)findViewById(R.id.entertainment_dec_txt);
		entertainment_dec_txt1 = (TextView)findViewById(R.id.entertainment_dec_txt1);
		date_of_event_txt = (TextView)findViewById(R.id.date_of_event_txt);
		date_of_event_txt1 = (TextView)findViewById(R.id.date_of_event_txt1); 
		txt_ticket = (TextView)findViewById(R.id.txt_ticket);
		event_state = (TextView)findViewById(R.id.event_state);
		nam_txt = (EditText)findViewById(R.id.nam_txt);
		client_txt = (EditText)findViewById(R.id.client_txt);
		email_txt = (EditText)findViewById(R.id.email_txt);
		day_phone_txt = (EditText)findViewById(R.id.day_phone_txt);
		e_phone_txt = (EditText)findViewById(R.id.e_phone_txt);
		event_txt = (EditText)findViewById(R.id.event_txt);
		event_city_txt = (EditText)findViewById(R.id.event_city_txt);
		preferred_seating_txt = (EditText)findViewById(R.id.preferred_seating_txt);
		request_ticket_btn = (Button)findViewById(R.id.request_ticket_btn);
		
		entertainment_dec_txt.setTypeface(Constants.ProximaNova_Regular);
		entertainment_dec_txt1.setTypeface(Constants.ProximaNova_Regular);
		date_of_event_txt.setTypeface(Constants.ProximaNova_Regular);
		date_of_event_txt1.setTypeface(Constants.ProximaNova_Regular);
		txt_ticket.setTypeface(Constants.ProximaNova_Regular);
		event_state.setTypeface(Constants.ProximaNova_Regular);
		nam_txt.setTypeface(Constants.ProximaNova_Regular);
		client_txt.setTypeface(Constants.ProximaNova_Regular);
		email_txt.setTypeface(Constants.ProximaNova_Regular);
		day_phone_txt.setTypeface(Constants.ProximaNova_Regular);
		e_phone_txt.setTypeface(Constants.ProximaNova_Regular);
		event_txt.setTypeface(Constants.ProximaNova_Regular);
		event_city_txt.setTypeface(Constants.ProximaNova_Regular);
		preferred_seating_txt.setTypeface(Constants.ProximaNova_Regular);
		request_ticket_btn.setTypeface(Constants.ProximaNova_Regular);
		
		
		String des="<html><body><p> We are upgrading our Ticket website with the latest selections and   technology.&nbsp; In the meantime, we have provided you with a personal   ticket concierge.&nbsp; Remember you can request a ticket to any sporting and   entertainment event including Ballet, Opera, Theater, Comedy, Las Vegas   and Theater.&nbsp; <br>" +
				"Please fill out your request and we will have one of our experts acquire your tickets <br></p><p> Hours of operation <span>10AM to 6PM</span> eastern time <span>Monday</span> - <span>Friday</span></p>" +
			 	"<p> Although   our ticket coordinator usually gets back to you within a few hours of   submission during business hours, there may be times when volume is   high, so please allow a full day for our coordinator to get back to you.</p></body></html>";

		entertainment_dec_txt1.setText(Html
				.fromHtml(des)); 
		
		nam_txt.setText(Constants.FIRSTNAME);
		email_txt.setText(Constants.EMAIL);
		day_phone_txt.setText(Constants.DAYPHONE);
		e_phone_txt.setText(Constants.EVENINGPHONE); 
		client_txt.setText(Constants.CLIENTNAME);
		
			menu = (ImageView) findViewById(R.id.menu);
			favLayout = (RelativeLayout) findViewById(R.id.favLayout);
		cartLayout = (RelativeLayout) findViewById(R.id.cartLayout);

		menu.setVisibility(View.GONE);
		favLayout.setVisibility(View.GONE);
		cartLayout.setVisibility(View.GONE);
		
		btn_home = (Button) findViewById(R.id.btn_home);
		btn_home.setText("Back"); 
		btn_home.setTypeface(Constants.ProximaNova_Regular);
	}
	

	protected void showList(final TextView txt_from_state2, final ArrayList<String> states,String heading,final int flg) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = getLayoutInflater();
		View convertView = (View) inflater.inflate(R.layout.dialog,
				null);
		// displaying alert dialog with list of
		// numbers
		final Dialog	alertDialog = new Dialog(EntertainmentTickets.this);
		alertDialog.setContentView(convertView);

		alertDialog.setTitle(heading);  

		ListView lv = (ListView) convertView
				.findViewById(R.id.listView);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
				EntertainmentTickets.this,
				android.R.layout.simple_list_item_1, states); 
		lv.setAdapter(adapter1);

		
		lv.setOnItemClickListener(new OnItemClickListener() {

			// TODO Auto-generated method stub

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
//				state_codes=Constants.states_ids.get(position);
				txt_from_state2.setText(states.get(position));
				if(flg==1)
				{
					num_of_tickets=states.get(position);
				} else if(flg==2)
				{
//					str_state=states.get(position);
					str_state=Constants.states.get(position);
				} 
		 		else
				{
					num_of_tickets="";
					str_state="";
				}
				alertDialog.dismiss();
			}
		});
		alertDialog.show();

	} 
	
	
	private class EntertainmentTicketsTasks extends AsyncTask<String, Void, String> {

		ProgressDialog myProgressDialog;

		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog = new ProgressDialog(EntertainmentTickets.this);
			myProgressDialog.setMessage("please wait ...");
			myProgressDialog.show();
		} 
		@Override
		protected String doInBackground(String... params) {
			return WebServiceCalls.getJSONString(Constants.MAIN_HOST
					+ "method=fnSendEntertainmentTicketEmail&AuthToken="+Constants.AUTH_TOKEN 
					+ "&txtName="+nam_txt.getText().toString()
					+ "&txtClient="+ client_txt.getText().toString()
					+ "&txtDayPhone="+ day_phone_txt.getText().toString() 
					+ "&txtEveningPhone="+ e_phone_txt.getText().toString() 
					+ "&txtEmail="+ email_txt.getText().toString() 
					+ "&tocity="+ event_city_txt.getText().toString() 
					+ "&tostate="+ str_state
					+ "&txtEventlookingfor="+ event_txt.getText().toString()
					+ "&txtHowmanytickets="+ num_of_tickets.toString() 
					+ "&txtDateofEvent="+ str_date
					+ "&txtAnypreferedseating="+ preferred_seating_txt.getText().toString()); 
//			fnSendEntertainmentTicketEmail/authToken,txtName,txtClient,txtDayPhone,txtEveningPhone,txtEmail,tocity,tostate,txtEventlookingfor,txtHowmanytickets,txtDateofEvent,txtAnypreferedseating

		}  

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			try {
				try {
System.out.println("fnSendEntertainmentTicketEmail"+result); 
					if (null == result || result.length() == 0) {

					} else {
						
						if(result.contains("false"))
						{
							AlertDialog.Builder altDialog = new AlertDialog.Builder(EntertainmentTickets.this);
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
							
							AlertDialog.Builder altDialog = new AlertDialog.Builder(EntertainmentTickets.this);
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
