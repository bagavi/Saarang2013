package com.saarang;

/*
 * Uses basic_icon.xml from layout/ of inflate 
 * Has a Imagebutton and a TextView, will be extened further 
 * Easy Code 
 * Used mainly to put text on image button
 * Sets the Focusable status false so that on click can be listened in the adapter class
 */

import com.saarang.R;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IconView extends LinearLayout {
	public ImageButton icon;
	public TextView iconText;
	public Context c;

	public IconView(Context context) {
		this(context, null);
	}

	public IconView(Context context, AttributeSet attr) {
		super(context, attr);
		this.c = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.basic_icon, this);
		loadViews();

	}

	private void loadViews() {
		iconText = (TextView) findViewById(R.id.iconDescription);
		iconText.setFocusable(false);
		iconText.setFocusableInTouchMode(false);
		// Font of the text
		Typeface tf = Typeface.createFromAsset(c.getAssets(), "Qlassik_TB.otf");
		iconText.setTypeface(tf);
	}

	public void setIconDescriptionText(String description) {
		iconText.setText(description);
	}
}