package com.views;
/*
 * Read IconView and Adapter before this
 * Same as EventView 
 * Made so that there is decoupling.. in case we want to change the looks (or functionality) of this page
 * Currently it is SAME
 */
import com.saarang.R;
import com.saarang.R.id;
import com.saarang.R.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventView extends LinearLayout {
	public TextView iconText;
	public Context c ;
	public EventView(Context context) {
		this(context, null);
	}

	public EventView(Context context, AttributeSet attr) {
		super(context, attr);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.basic_event, this);
		this.c = context;
		loadViews();

	}

	private void loadViews() {

		iconText = (TextView) findViewById(R.id.eventName);
		iconText.setFocusable(false);
		iconText.setFocusableInTouchMode(false);
        Typeface tf = Typeface.createFromAsset(c.getAssets(),"Qlassik_TB.otf");  
        iconText.setTypeface(tf);
		
	}

	public void setIconDescriptionText(String description) {
		iconText.setText(description);
	}

}
