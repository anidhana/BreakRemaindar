package com.ebook.breakremaindar1;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity{
    
	private EditText e1,e2;
	
	private int hour;
    private int minute;
    private int hour1;
    private int minute1;
    TimePickerDialog timePickerDialog,timePickerDialog1;
    private static final int TIME_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID1=1;
    final static int RQS_1 = 1;
    /** Called when the activity is first created. */
	//final String days[] ={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
 //   boolean[] itemsChecked = new boolean[days.length];
    EditText e3,e4;
	//private long startTime = 30 * 1000;
    //final int nowDay=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    // long alarm1;
    //boolean alarmSet = false;
    Calendar c = Calendar.getInstance();
    int month;
    int week;
    int year;
    String str; 
    TextView tv;
    long t;
    int day1;
    //Date date =new Date();0
    //c.setTime(date);
    final CharSequence[] items={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Staturday"};
	boolean[] itemsChecked = new boolean[items.length];
	String selectedTech;
    
    
    protected void  onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);
    	   setContentView(R.layout.activity_main);
    		firstbuttonListener();
    		secondbuttonListener();
    		
    		

    		//mp=MediaPlayer.create(getBaseContext(), R.raw.anbe);
    		 //mp.start();
    				//mplayer=MediaPlayer.create(this,com.ebook.breakremainder.R.raw.anbe);
    	    // mplayer.setLooping(true);	
    	    //	Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    		// r1 = RingtoneManager.getRingtone(getApplicationContext(), notification);
    		
    		 }
    public void showDialog(View v)
    {
    	     	 
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("Select Days");
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            	 selectedTech="Alarm Set On : " ;
                for (int i = 0; i < items.length; i++) {
                if (itemsChecked[i]) {
                    
                	selectedTech=selectedTech+items[i]+" ";
                    itemsChecked[i]=false;
                }
            }
             TextView tv=(TextView)findViewById(R.id.textView1);
             tv.setText(selectedTech);
              day1 = Integer.parseInt(tv.getText().toString());
            }
        });
    	
    	builder.setMultiChoiceItems(items, new boolean[]{false,false,false,false,false,false,false}, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					itemsChecked[which]=isChecked;	
			}
		});
    	builder.show();
    }
    	   // TODO Auto-generated method stub
    		//Toast.makeText(this, "Alarm ", Toast.LENGTH_LONG).show();
    		// alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,Calendar.getInstance(),editresult3,intent); 
    		private void secondbuttonListener() {
    			// TODO Auto-generated method stub
    			
    		e2=(EditText)findViewById(R.id.eText2);
    		e2.setOnClickListener(new OnClickListener() {
    			
    		@Override
    		public void onClick(View arg0) {
    		// TODO Auto-generated method stub
    		//showDialog(TIME_DIALOG_ID1);
    		openTimePickerDialog1(false);
    		}
    		private void openTimePickerDialog1(boolean is24r) {
    						// TODO Auto-generated method stub
    		 Calendar calendar = Calendar.getInstance();
    		 timePickerDialog = new TimePickerDialog(MainActivity.this,onTimeSetListener,
    		
    		//calendar.get(Calendar.DAY_OF_WEEK),	 
    				 
    		 calendar.get(Calendar.HOUR_OF_DAY), 
    		 calendar.get(Calendar.MINUTE), is24r);
    		 timePickerDialog.setTitle("Set Alarm Time");  
    		 timePickerDialog.show();
    	     }
    		  OnTimeSetListener onTimeSetListener = new OnTimeSetListener(){
    		  @Override
    		  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    			 Calendar calNow = Calendar.getInstance();
    			 Calendar calSet = (Calendar) calNow.clone();
    			 
    			// calSet.set(Calendar.DAY_OF_WEEK,day1);
    			 
    	        // calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
    			 calSet.set(Calendar.MINUTE, minute);
    			 calSet.set(Calendar.SECOND, 0);
    			 calSet.set(Calendar.MILLISECOND, 0);
    			 e2.setText(new StringBuilder().append(pad(hourOfDay)).append(":").append(pad(minute)));
    			 if(calSet.compareTo(calNow) <= 0){
    			    //Today Set time passed, count to tomorrow
    			 calSet.add(Calendar.DATE, 1);
    			 calSet.add(Calendar.DATE,1);
    			 
    			  }
    			   setAlarm(calSet);
    			  }};
    		
    			private void setAlarm(Calendar targetCal) {
    							// TODO Auto-generated method stub
    				//  Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    		
    					 Intent intent = new Intent(getBaseContext(), Alarm.class);
    					  PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
    	     			  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    					  alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    					 // @SuppressWarnings("deprecation")
    					 // Notification notifyObj=new Notification(R.drawable.ic_launcher,
    			          //          "Notification message!",targetCal.getTimeInMillis());
    					//Set default vibration
    					 // notifyObj.defaults |= Notification.DEFAULT_VIBRATE;
    					  //Set default notification sound
    					 // notifyObj.defaults |= Notification.DEFAULT_SOUND;
    					}				
    		          });
    			   }

    		private void firstbuttonListener() {
    			// TODO Auto-generated method stub
    			e1=(EditText)findViewById(R.id.eText1);
    			e1.setOnClickListener(new OnClickListener() {
    	        @Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				//showDialog(TIME_DIALOG_ID);
    				openTimePickerDialog(false);
    				}
    	         });
    		    	}
    		private void openTimePickerDialog(boolean is24r){
    		   Calendar calendar = Calendar.getInstance();
    		   timePickerDialog1 = new TimePickerDialog(MainActivity.this,onTimeSetListener1, 
    	       calendar.get(Calendar.HOUR_OF_DAY), 
    		   calendar.get(Calendar.MINUTE),is24r);
    		   timePickerDialog1.setTitle("Set Alarm Time");  
    	    	timePickerDialog1.show();
    			 }
    		 OnTimeSetListener onTimeSetListener1 = new OnTimeSetListener(){
    	 @Override
    		  public void onTimeSet(TimePicker view1, int hourOfDay1, int minute1) {
    			   Calendar calNow = Calendar.getInstance();
    			   Calendar calSet = (Calendar) calNow.clone();
    			   
    			   //calSet.set(Calendar.DAY_OF_WEEK,day1);
    			   
    			   calSet.set(Calendar.HOUR_OF_DAY, hourOfDay1);
    			   calSet.set(Calendar.MINUTE, minute1);
    			   calSet.set(Calendar.SECOND, 0);
    			   calSet.set(Calendar.MILLISECOND, 0);
    			   e1.setText(new StringBuilder().append(pad(hourOfDay1)).append(":").append(pad(minute1)));
    			
    			     
    			   //e3.setText(new StringBuilder().append(minute));  
    			   	   
    			   if(calSet.compareTo(calNow) <= 0){
    			    //Today Set time passed, count to tomorrow
    			    calSet.add(Calendar.DATE,1);
    			    
    			   }
    			   setAlarm(calSet);
    			  }};

    			private void setAlarm(Calendar targetCal) {
    				// TODO Auto-generated method stub
    					//notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	         
    			 Intent intent = new Intent(getBaseContext(), Alarm.class);
    			 PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(),RQS_1, intent, 0);
    			//
    		//	 calendar.set(Calendar.DAY_OF_WEEK,getDay());
    		//	    calendar.set(Calendar.HOUR_OF_DAY, 23);
    			//    calendar.set(Calendar.MINUTE, 10);
    			 
    			 //NotificationManager notifyObj=( NotificationManager)getSystemService(Context.ALARM_SERVICE);
    			AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    			alarmManager.set(AlarmManager.RTC_WAKEUP,targetCal.getTimeInMillis(), pendingIntent);
    				}
    				// alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,Calendar.getInstance(),1000 * starttime,intent); 
    				// alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
    					//        1000 * 60 * 20, alarmIntent);
    				  //Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    				  //Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
    				  //r.play();
    				//mplayer.start();
    				 // r1.play();
    				
    				  
    				 // Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    				  //  Ringtone ringtone = RingtoneManager.getRingtone(getBaseContext(), uri);
    				   // ringtone.play();	 
    			
    			 private TimePickerDialog.OnTimeSetListener timePickerListener=  new TimePickerDialog.OnTimeSetListener() 
    			 {
    				 @SuppressWarnings("deprecation")
    				public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
    	            hour= selectedHour;
    	            minute = selectedMinute;
    	              showDialog(TIME_DIALOG_ID);
    	          
    		  			}
    		    };
    			private TimePickerDialog.OnTimeSetListener timePickerListener1 =  new TimePickerDialog.OnTimeSetListener() {
    		    	   @SuppressWarnings("deprecation")
    				public void onTimeSet(TimePicker vieww, int selectedHourr, int selectedMinutee) {
    		    	       hour1 = selectedHourr;
    		    	       minute1 = selectedMinutee;
    		    	// set current time into 
    		    	        showDialog(TIME_DIALOG_ID1);
    		    	       	    	   	            
    		    	   }
    		    	};
    		          private static String pad(int c) {
    	               if (c >= 10)
    	            	 return String.valueOf(c);
    	                else
    	               return "0" + String.valueOf(c);
    	         }
    	     
    	 public Dialog onCreateDialog(int id, OnClickListener OnClickListener) {
    		 
    		switch (id)
    		{
    	 case TIME_DIALOG_ID:
    	//set the picker as current time
    		 
    	 return new TimePickerDialog(this, timePickerListener, hour, minute,false);

    	 
    	 case TIME_DIALOG_ID1:
    		 
    	 return new TimePickerDialog(this, timePickerListener1, hour1, minute1,true);
    	 
    	  }	
    	 return null;
    	 }
    	 
    	 public void startAlert(View view)
    	 {
    	     EditText e3 = (EditText) findViewById(R.id.editText3);
    	    // EditText e4 = (EditText) findViewById(R.id.editText4);
    	     Long i = Long.parseLong(e3.getText().toString());
    	   //  Long a = Long.parseLong(e4.getText().toString());

    	     Intent intent = new Intent(this, Alarm.class);
    	     
    	   //  PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
    	  //  AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
    	   //  alarmManager1.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + a, pendingIntent1);
    	     PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
    	     AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    	     alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 60000), pendingIntent);
    	     Toast.makeText(this, "Alarm set in " + i + " Minutes",Toast.LENGTH_LONG).show();
    	     
    	   //  Toast.makeText(this, "Alarm set both Break Interval "+ i +" Minutes" + " And Break Time" + a + "Minutes", Toast.LENGTH_LONG).show();
    	     		
    	 }}
    	   