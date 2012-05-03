package com.saarang;
/*
 * Read icon Adapter Before this
 * Very similar to IconAdapter
 * It fills the ListActivity with the events in the category selected
 */
import android.content.Context;
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


public class EventAdapter extends BaseAdapter{
	
	public Context c;
	public TextView iconText ;
	public int eventId ;
	gallery_manager sources = new gallery_manager();	
	public EventAdapter(Context c)
	{
		this(c,0);
		this.c = c;
	}
	public EventAdapter(Context c , int event_id)
	{
		eventId = event_id;
		this.c = c;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
		EventView eventView = new EventView(c);
		int randombutton = (int)(Math.random()*1000)%16 ;
		Log.e("random button number" , randombutton+"");
		eventView.setIconDescriptionText(sources.eventNameHash.get(sources.eventids[eventId][position]));
		return eventView ;
	}
	
	
	@Override
    
    public int getCount() {
      if (eventId!=-1)
    	return sources.eventids[eventId].length;
      else
    	  return 0;
    }

	@Override
	public Object getItem(int arg0) {

		return arg0;
	}
	@Override
	public long getItemId(int arg0) {

		return arg0;
	}
		
}