package com.saarang;

/*

 * This activity displays the coord info..
 * This Activity can be used in various ways.. very versatile
 * Major functions
 * 		It can fetch Coord details for a given evetn( through EventId)
 * 		If not even id is given ,fetches all coord details
 * It fetches the coord details in a cursor and then displays it in a list and
 * each item in the list when clicked opens dialogue box which gives him three options 
 */
import java.io.IOException;


import com.saarang.adapter_database.CordListAdapter;
import com.saarang.adapter_database.DatabaseHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.views.*;

public class CordListActivity extends Activity {
	public int eventId;
    /** The list view */
    private MyListView mListView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.coordlist);
		Bundle extras = getIntent().getExtras();
		eventId = extras.getInt("eventId", -1);
		mListView = (MyListView)findViewById(R.id.my_list);
		Log.e("Test", "Entered Coord List Activity");
		Log.e("list Activity ", "event Id " + eventId);

		DatabaseHelper myDbHelper = new DatabaseHelper(this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("database not created");
		}
		myDbHelper.openDataBase();

		Cursor cursor;
		// Fetch all coords if the event id is -1(implies no event is specified)
		if (eventId == -1) {
			cursor = myDbHelper.fetchAllCords();
		}
		// Fetch Coord details of the given event id
		else {
			cursor = myDbHelper.fetchCordDetails(eventId);
		}
		startManagingCursor(cursor);
		CordListAdapter adapter = new CordListAdapter(this, cursor);
		mListView.setAdapter(adapter);
		/*
        mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {

				TextView phoneView = (TextView) v.findViewById(R.id.cordphone);
				String phone = (String) phoneView.getText();

				final String uri = "tel:" + phone;
				final String messageUri = "sms:" + phone;
				AlertDialog.Builder builder = new AlertDialog.Builder(getParent());
				builder.setMessage("Do you want to call or message the coordinator?")
						.setCancelable(true).setPositiveButton("Call",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										Intent intent = new Intent(Intent.ACTION_CALL,
												Uri.parse(uri));
										startActivity(intent);
									}
								}).setNegativeButton("Message",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										Intent intent = new Intent(Intent.ACTION_VIEW,
												Uri.parse(messageUri));
										startActivity(intent);
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
				
			}
        	
        }) ;

		 */
	}
}