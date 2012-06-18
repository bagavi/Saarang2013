package com.saarang;

/*
 * Simple Activity....The name is very misleading sorry for that!
 * Called when the event button is clicked on the main activity(saarang activity)
 * Populates the List of category of events. Eg Dance, Workshop etc
 * The list is selectable and will give the user list of events a particluar category
 * Eg Under Quiz - Buzzer quiz, Daily Quiz etc 
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class DepartmentActivity extends ListActivity {
	private int eventId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IconAdapter iconadapter = new IconAdapter(this);
		setListAdapter(iconadapter);
		Log.e("List populated", "Adapter set");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.coordlist:
			Bundle extras = new Bundle();
			extras.putInt("eventId", this.eventId);
			Intent in = new Intent(this, CordListActivity.class);
			in.putExtras(extras);
			startActivity(in);
			break;
		case R.id.maps:
			Intent intent = new Intent(this, MapActivity.class);
			startActivity(intent);
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
}
