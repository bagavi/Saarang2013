package com.saarang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.database.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

// This activity is supposed to list all hotels along with distances.
// By default, it is sorted by distance, but if for some reason you want
// to sort by name, make byDistance false 
public class HospiActivity extends Activity {
	private DatabaseHelper myDbHelper;
	private Cursor mCursor;
	private boolean byDistance = true;
	private String[] hotels, distance;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.hospi_main);
		
		List<HashMap<String, String>> hList = new ArrayList<HashMap<String, String>>();
		
		myDbHelper = new DatabaseHelper(this);
		try {
			Log.e("Hospi", "DBHelper Created");
			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
		} catch (IOException ioe) {}

		mCursor = myDbHelper.fetchHotels(byDistance);
		if (mCursor == null)
			Log.e("Hospi", "Please sleep at GC");
		
		int nRows = mCursor.getCount();
		hotels = new String[nRows];
		distance = new String[nRows];
		
		for (int row = 0; row < nRows; row++) {
			hotels[row] = mCursor.getString(0);
			distance[row] = mCursor.getString(1);
			mCursor.moveToNext();
			
			HashMap<String, String> hMap = new HashMap<String, String>();
			hMap.put("name", hotels[row]);
			hMap.put("distance", "" + distance[row] + "km");
			hList.add(hMap);
		}
		
		String[] params = {"name", "distance"};
		int[] placeValues = {R.id.hotel_name, R.id.hotel_distance};
		SimpleAdapter sAdapter = new SimpleAdapter(getBaseContext(), hList, R.layout.hospi_listview, params, placeValues);
		
		ListView lView = (ListView) findViewById(R.id.hotel_listview);
		lView.setAdapter(sAdapter);
		
		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
				RelativeLayout relativeLayout = (RelativeLayout) container;
				TextView hotelName = (TextView) relativeLayout.getChildAt(0);
				Log.e("Hospi", "" + hotelName.getText().toString());
				Intent intent;
				intent = new Intent(HospiActivity.this, HotelActivity.class);
				intent.putExtra("HOTEL_NAME", hotelName.getText());
				startActivity(intent);
			}
		};
		lView.setOnItemClickListener(itemClickListener);
	}
}
