package com.adapters;

import java.io.IOException;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.util.Log;

import com.database.DatabaseHelper;
import com.utils.gallery_manager;

/*
 * This class will add all the events as calendar events on the phone.
 * Need to add location and timings of the events in the database so that we can add it in the event
 * As of now gives a standard starting and ending time.
 * Location if available can be taken added as an extra in the Intent
 * 
 */

public class CalendarAdapter {
	
	public Context cnt;
	private DatabaseHelper myDbHelper;
	private Cursor mCursor;
	private int noEvents;
	private String name;
	private String location;
	private String introduction;
	
	
	public CalendarAdapter(Context c)
	{
		this.cnt = c;
		
		myDbHelper = new DatabaseHelper(this.cnt);
		noEvents = gallery_manager.noEvents;

		try {

			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
			for( int i=1; i<=noEvents; i++)
			{
				this.addEvent(i);
			}
			
			
			

		} catch (IOException ioe)

		{
			Log.i("CalendarAdapter","DatabaseException");
		}

	}
	
	public void addEvent(int eventId)
	{
		
		name = gallery_manager.eventNameHash.get(eventId);
		
		mCursor = myDbHelper.fetchDescription(eventId);
		introduction = mCursor.getString(3);
		
		if (introduction.equalsIgnoreCase("null")) {
			introduction = "Sorry .. No introduction has been given for this events."
					+ " Please Contact the Coordinator for details";
		}
		
		
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(2012, 0, 19, 7, 30);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2012, 0, 19, 8, 30);
		Intent intent = new Intent(Intent.ACTION_INSERT)
		        .setData(Events.CONTENT_URI)
		        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
		        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
		        .putExtra(Events.TITLE, name)
		        .putExtra(Events.DESCRIPTION,introduction);
		        //.putExtra(Events.EVENT_LOCATION, "Location")
				
		cnt.startActivity(intent);
		        
		
	}


}
