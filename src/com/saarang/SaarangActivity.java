package com.saarang;


/*
 * This is the first activity
 * 5 image buttons
 * Added clickListeners to all the 5 buttons
 * Check the id of the button clicked and then launch the corresponding intent
 */

import com.saarang.R;

import java.io.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SaarangActivity extends Activity {
	ImageButton Events;
	ImageButton ProShow;
	ImageButton Map;
	ImageButton Sponsors;
	ImageButton Coord_Info;
	public String dispval = ""; //Ignore this variable

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ProShow = (ImageButton) findViewById(R.id.ProShow);
		Events = (ImageButton) findViewById(R.id.Events);
		Coord_Info = (ImageButton) findViewById(R.id.Coord_Info);
		Map = (ImageButton) findViewById(R.id.Map);
		Sponsors = (ImageButton) findViewById(R.id.Sponsors);
		ProShow.setOnClickListener(ImageButtonListener);
		Events.setOnClickListener(ImageButtonListener);
		Coord_Info.setOnClickListener(ImageButtonListener);
		Map.setOnClickListener(ImageButtonListener);
		Sponsors.setOnClickListener(ImageButtonListener);

	}
	//private OnClickListener test = new 
	private OnClickListener ImageButtonListener = new OnClickListener() {
		
		public void onClick(View v) {
			Integer id = v.getId();
			dispval = id.toString();
			Log.v("test", dispval);
			Intent intent;
			switch (id) {
			case R.id.ProShow:
				intent = new Intent(SaarangActivity.this, ProShowActivity.class);
				startActivity(intent);

				break;
			case R.id.Events:
				intent = new Intent(SaarangActivity.this,
						DepartmentActivity.class);
				intent.putExtra("DEP_NAME", "Events");
				intent.putExtra("DEP_ID", 2);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_left_in,
						R.anim.slide_left_out);
				break;

			case R.id.Coord_Info:
				Log.e("Test", "Coord_Info");
				intent = new Intent(SaarangActivity.this,
						CordListActivity.class);
				intent.putExtra("eventId", -1);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_left_in,
						R.anim.slide_left_out);
				break;

			case R.id.Map:
				Log.e("", "Map clicked, will start activity	");
				intent = new Intent(SaarangActivity.this, MapActivity.class);
				startActivity(intent);
				break;

			case R.id.Sponsors:
				intent = new Intent(SaarangActivity.this, SponsActivity.class);
				startActivity(intent);
				break;

			}
		}
	};



	/*
	 *This function used only for debugging purpose, ignore it on first read
	 */
	@Override
	public Dialog onCreateDialog(int id) {

		AlertDialog.Builder timeDialog = new AlertDialog.Builder(this);
		timeDialog.setMessage(dispval);
		return timeDialog.create();
	}
}