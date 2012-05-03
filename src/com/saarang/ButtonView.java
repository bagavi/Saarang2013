package com.saarang;


import com.saarang.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ButtonView extends LinearLayout {

	private Button button;
	private TextView ButtonText;
	private String EventName ;
	public ButtonView(Context context) {
		super(context);

		loadViews();
	}

	public ButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.image_holder, this);

		loadViews();
	}

	private void loadViews() {
		
	
		ButtonText = (TextView)findViewById(R.id.tvName);
		button = (Button)findViewById(R.id.myButton);
		ButtonText.setText("hey waasup");
	    
	}

	public void setButtonImage(Drawable title) {
		//button.setImageDrawable(title);
	}

	public void setButtonWidth(int width){
		button.setWidth(width);
	}
	public void setHeroName(String name) {
		ButtonText.setText(name);
	}
	
	public void setEventName(String name){
		EventName = name ;
	}
		
	public int measureHeight(){
		return button.getHeight();
	}
	
	
}

