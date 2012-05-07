package com.saarang.adapter_database;

/*
 * @author Bharath M R
 * catchmrbharath@gmail.com
 * This class is an custom cursor adapter.
 */


import java.util.HashMap;


import com.saarang.R;
import com.saarang.gallery_manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.UserDictionary.Words;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class CordListAdapter extends CursorAdapter {
	
	private Cursor cursor;
	private Context mContext;
	private final LayoutInflater inflator;
	
	public CordListAdapter(Context context, Cursor c) {
		super(context, c);
		inflator = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	/*
	 * This method maps the contents on the cursor onto the listview. The layout is defined in
	 * item_list_view. The textview and the image view are inflated.
	 */
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView) view.findViewById(R.id.cordname);
		TextView number = (TextView) view.findViewById(R.id.cordphone);
		TextView eventName = (TextView) view.findViewById(R.id.cordEvent);
		//android:inputType="textCapSentences"
		if(cursor!=null){
			String namestr = cursor.getString(1);
			name.setText(namestr);
			String phonestr = cursor.getString(2);
			number.setText(phonestr);
			int eventId = cursor.getInt(3);
			Log.e("Adapter" ,""+eventId);
			Log.e("Adapter" ,gallery_manager.eventNameHash.get(eventId));
			eventName.setText(gallery_manager.eventNameHash.get(eventId)); // Sets the vent name of the coord
		}	
	}

	/*inflates the layout xml file */
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final View view = inflator.inflate(R.layout.coord_list_item,parent,false);
		return view;

	}
}