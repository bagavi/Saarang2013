package com.saarang;

/*
 * This Activty is called one any of the list in the category-->eventlist is CLICKED
 * Read Database Helper classes before reading this
 * This code must be read at last(r near last)
 * It uses the event_description_layout.. fills the event name , event information and then events format
 * Cause Saarang 2012 design team(They cupped) couldnt give us pictures of each event we have not included the pictures 
 * specific to each event
 * NEVER include prize money of each event and stuff cause that keeps changing and we pain you   
 */
import android.app.Activity;
import android.bluetooth.BluetoothClass.Device.Major;
import android.os.Bundle;
import java.io.IOException;

import com.database.DatabaseHelper;
import com.utils.gallery_manager;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;

public class EventInfoActivity extends Activity {

	private int RowNum;
	private DatabaseHelper myDbHelper;
	private Cursor mCursor;
	private String name;
	private int category;
	public int eventid;
	private TextView mDescriptionTextView;
	private TextView mIntroLabel;
	private String introduction;
	private String eventFormat;
	private ImageButton mIntroduction;
	private ImageButton mEventFormat;
	private int mPrize;
	private TextView prizeMoney;
	gallery_manager g = new gallery_manager();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_description_layout);
		
		// Extracting the data from Intent
		Bundle extras = getIntent().getExtras();
		RowNum = extras.getInt("EventId", 1);
		category = extras.getInt("MajorEventId");
		eventid = g.eventids[category][RowNum];
		Log.e("event id in the info page", "event Id " + RowNum);

		// Database part
		myDbHelper = new DatabaseHelper(this);

		try {

			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
			Log.e("Event Id = ", eventid + "");
			mCursor = myDbHelper.fetchDescription(eventid);
			eventFormat = mCursor.getString(2);
			introduction = mCursor.getString(3);
			if (introduction.equalsIgnoreCase("null")) {
				introduction = "Sorry .. No introduction has been given for this events."
						+ " Please Contact the Coordinator for details";
			}
			if (eventFormat.equalsIgnoreCase("null")) {
				eventFormat = "Sorry .. Format has not been given has been given for this events."
						+ " Please Contact the Coordinator for details";
			}
			Log.e("check", "event description " + introduction);

		} catch (IOException ioe)

		{
			eventFormat = "Test Event";
			introduction = "This is test in which you have to do nothing";
		}

		// The images are being set
		name = g.eventNameHash.get(eventid);

		TextView t = (TextView) findViewById(R.id.event_category);
		t.setText(name);

		mDescriptionTextView = (TextView) findViewById(R.id.eventDescription);
		mIntroLabel = (TextView) findViewById(R.id.introLabel);

		mIntroduction = (ImageButton) findViewById(R.id.intro);
		mEventFormat = (ImageButton) findViewById(R.id.eventFormat);
		mDescriptionTextView.setText(introduction);

		EventListener mEventListener = new EventListener();
		mIntroduction.setOnClickListener(mEventListener);
		mEventFormat.setOnClickListener(mEventListener);

		myDbHelper.close();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.coordlist:
			Bundle extras = new Bundle();
			/*
			 * Mapping has to be yet done then pass event id in the gallery
			 * manager and then obtain the map id
			 */
			
			extras.putInt("eventId", g.CoordMap[this.eventid-1]);
			Log.e("The  original id" , "" +eventid);
			Log.e("The Coord map id" , "" +g.CoordMap[this.eventid -1]);
			Intent in = new Intent(this, CordListActivity.class);
			in.putExtras(extras);
			startActivity(in);
			break;

		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	private class EventListener implements OnClickListener {

		public void onClick(View v) {
			mEventFormat.setSelected(false);
			mEventFormat.setSelected(false);
			ImageButton temp = (ImageButton) v;
			v.setSelected(true);
			if (v.getId() == R.id.intro) {
				mDescriptionTextView.setText(introduction);
				mIntroLabel.setText("Introduction");
			} else {
				mDescriptionTextView.setText(eventFormat);
				mIntroLabel.setText("Event Format");
			}

		}

	}

}
