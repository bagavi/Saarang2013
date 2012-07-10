package com.saarang;

/*
 * Read IconView before this
 * It has ImageButton and TextView
 * Has two construct 
 * 		default constructor
 * 		another accepts the event_id ( here it is category basically)
 * 		Fills the listActivity with the main category of events 
 * 
 */
import android.content.Context;

import java.lang.Math;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.saarang.gallery_manager;
import com.views.IconView;


public class IconAdapter extends BaseAdapter{
	
	public Context c;
	public ImageButton icon;
	public TextView iconText ;
	public int eventId ;
	gallery_manager IconData = new gallery_manager();	
	public IconAdapter(Context c)
	{
		this(c,0);
		this.c = c;
	}
	public IconAdapter(Context c , int event_id)
	{
		eventId = event_id;
	}
	
    public View getView(int position, View convertView, ViewGroup parent) {
		
		IconView iconview = new IconView(c);
		int randombutton = (int)(Math.random()*1000)%16 ;
		iconview.setIconDescriptionText(IconData.EventCategoryText[position]);
		iconview.setOnClickListener(new OnItemClickListener(position)); 
		return iconview ;
	}
	
	
	public int getCount() {
    	return IconData.EventCategoryText.length;
	}
	public Object getItem(int arg0) {

		return arg0;
	}
	public long getItemId(int arg0) {

		return arg0;
	}
	
	private class  OnItemClickListener implements OnClickListener {
		
		private int mposition;
		
		public OnItemClickListener(int position) {
			mposition = position;
		}	
		
		public void onClick(View arg0) {
			Log.e("click problems",""+mposition);
			Intent intent = new Intent(c , EventActivity.class);
			intent.putExtra("EventId", mposition);
			c.startActivity(intent);

		}
		
	}
}