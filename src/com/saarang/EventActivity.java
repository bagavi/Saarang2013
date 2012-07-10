package com.saarang;

/*
 * Read DepartmentActivity before this 
 * Same as dept activity with little difference
 * It has to pass the event_category "and" the click position to the EventInfoActivity
 * Eg for buzzer quiz, it passes 2( which corresponds to Quiz) and 1(which corrsponds to buzzer quiz)
 * Eg2 For (8,2) implies Music(8) and Western music(2)  
 */
import com.adapters.EventAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class EventActivity extends ListActivity {

	private int EventId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		EventId = extras.getInt("EventId");
		Log.e("list Activity ", "event Id " + EventId);
		EventAdapter iconadapter = new EventAdapter(this, EventId);
		setListAdapter(iconadapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.e("Major Event id name", EventId + "");
		Log.e("Sub event id name ", position + "");

		Intent intent = new Intent(EventActivity.this, EventInfoActivity.class);
		intent.putExtra("MajorEventId", EventId);
		intent.putExtra("EventId", position);
		Log.e("Test ", "To start EvenInfoActivity");
		startActivity(intent);
	}
}