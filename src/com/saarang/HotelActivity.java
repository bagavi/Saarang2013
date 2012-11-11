package com.saarang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.database.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

// This activity lists all possible details of the clicked hotel
// There is option to place call
public class HotelActivity extends Activity {
	private String HOTEL_NAME;
	private DatabaseHelper myDbHelper;
	private Cursor mCursor;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Bundle extras = getIntent().getExtras();
		HOTEL_NAME = extras.getCharSequence("HOTEL_NAME").toString();
		Log.e("Hotel", "" + HOTEL_NAME);
		
		setContentView(R.layout.hospi_main);

		List<HashMap<String, String>> hList = new ArrayList<HashMap<String, String>>();
		
		myDbHelper = new DatabaseHelper(this);
		try {
			Log.e("Hospi", "DBHelper Created");
			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
		} catch (IOException ioe) {}

		mCursor = myDbHelper.fetchHotels(HOTEL_NAME);
		if (mCursor == null)
			Log.e("Hotel", "Please sleep at GC");

		Log.e("Hotel", "" + mCursor.getColumnCount() + " " + mCursor.getCount());
//		Log.e("Hotel", "" + mCursor.getString(0));
		for (int col = 0; col < mCursor.getColumnCount(); col++) {
//			Log.e("Hotel", "" + col);
			String s = mCursor.getString(col);
			HashMap<String, String> hMap = new HashMap<String, String>();
			
			switch (col) {
			case 0: hMap.put("key", "");
					break;
			case 1: hMap.put("key", "Address");
					break;
			case 2: hMap.put("key", "Phone\n(Click to Call)");
					break;
			case 3: hMap.put("key", "Distance");
					break;
			case 4: hMap.put("key", "Bus");
					break;
			case 5: hMap.put("key", "Cost of Single Room Non-AC");
					break;
			case 6: hMap.put("key", "Cost of Single Room AC");
					break;
			case 7: hMap.put("key", "Cost of Double Room Non-AC");
					break;
			case 8: hMap.put("key", "Cost of Double Room AC");
					break;
			}
			
			if (col==3)
				hMap.put("value", "" + s + "km");
			else
				hMap.put("value", s);
			
			hList.add(hMap);
		}
		
		String[] params = {"key", "value"};
		int[] placeValues = {R.id.hotel_name, R.id.hotel_distance};
		SimpleAdapter sAdapter = new SimpleAdapter(getBaseContext(), hList, R.layout.hospi_listview, params, placeValues);
		
		ListView lView = (ListView) findViewById(R.id.hotel_listview);
		lView.setAdapter(sAdapter);
		
		OnItemClickListener itemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
				RelativeLayout relativeLayout = (RelativeLayout) container;
				TextView key = (TextView) relativeLayout.getChildAt(0);
				TextView value = (TextView) relativeLayout.getChildAt(1);
				Log.e("Hotel", "" + key.getText().toString() + " " + value.getText().toString());
				if (key.getText().toString().startsWith("Phone")) {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:" + value.getText().toString()));
					startActivity(callIntent);
				}
			}
		};
		lView.setOnItemClickListener(itemClickListener);

	}
	
}
