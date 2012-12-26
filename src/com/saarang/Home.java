package com.saarang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/*
 * This is supposed to be the home page for the app.
 * Consists of all the rings in different Image views. ( Can be clubbed into a single Image View)
 * Each ring will connect to differnet page of the app.
 * White - Proshow
 * Blue  - Event
 * 
 *  Animation done using 9oldsandroid, mimics all the view animation available from 3.0+ to all the old Android versions! (Jake Wharton! \m/)
 */
public class Home extends Activity {
    /** Called when the activity is first created. */
	
	ImageView OrangeView, GreenView, BlueView, WhiteView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_layout);
        
        
        
        OrangeView = (ImageView) findViewById(R.id.imageView3);
        GreenView = (ImageView) findViewById(R.id.imageView2);
        BlueView = (ImageView) findViewById(R.id.imageView5);
        WhiteView = (ImageView) findViewById(R.id.imageView6);
        
        InitAnim();
        KeyAnim();
        
        
        OrangeView.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				float X = event.getX() - v.getWidth()/2;
				float Y = event.getY() - v.getHeight()/2;
				
				double radius = Math.sqrt(X*X+Y*Y);
				
				Log.i("TOUCH",Double.toString(radius));
				Intent intent;
				if(radius <= 35){
					Log.i("Touch","White");
					intent = new Intent(Home.this, MapActivity.class);
					startActivity(intent);
					//White
//					Toast toast = Toast.makeText(Home.this,"White",Toast.LENGTH_SHORT);
//					toast.setDuration(1);
					//toast.show();
				}
				else if(radius <= 60){
					//Blue
					intent = new Intent(Home.this, SponsActivity.class);
					startActivity(intent);
				}
				else if(radius <= 90){
					//Green
					intent = new Intent(Home.this, ProShowActivity.class);
					startActivity(intent);
				}
				else if(radius <= 150){
					//Orange
					intent = new Intent(Home.this, EventsPage.class);
					startActivity(intent);
					overridePendingTransition(R.anim.slide_left_in,
							R.anim.slide_left_out);
				}
				return false;
			}
									
		});
    }
    
      
        
    public void InitAnim(){
    	
    	ObjectAnimator RotatAnim = ObjectAnimator.ofFloat(OrangeView, "rotation", 150);
    	RotatAnim.setDuration(1);
    	RotatAnim.start();
    	
    	RotatAnim = ObjectAnimator.ofFloat(GreenView, "rotation", 200);
    	RotatAnim.setDuration(1);
    	RotatAnim.start();
    	
    	RotatAnim = ObjectAnimator.ofFloat(BlueView, "rotation", 300);
    	RotatAnim.setDuration(1);
    	RotatAnim.start();
    	
    	ObjectAnimator FadeAnim = ObjectAnimator.ofFloat(WhiteView, "alpha", 0);
    	FadeAnim.setDuration(1);
    	FadeAnim.start();
    	
    	AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    	am.playSoundEffect(AudioManager.FX_KEY_CLICK);
    }
    
    public void KeyAnim()
    {	
    	ObjectAnimator RotatAnim1 = ObjectAnimator.ofFloat(OrangeView, "rotation", 0);
    	RotatAnim1.setDuration(1500);
    	RotatAnim1.setInterpolator(new DecelerateInterpolator());
    	
    	ObjectAnimator RotatAnim2 = ObjectAnimator.ofFloat(GreenView, "rotation", 360);
    	RotatAnim2.setDuration(1500);
    	RotatAnim2.setInterpolator(new DecelerateInterpolator());
    	
    	ObjectAnimator RotatAnim3 = ObjectAnimator.ofFloat(BlueView, "rotation", 0);
    	RotatAnim3.setDuration(1500);
    	RotatAnim3.setInterpolator(new DecelerateInterpolator());
    	
    	ObjectAnimator FadeAnim = ObjectAnimator.ofFloat(WhiteView, "alpha", 100);
    	FadeAnim.setDuration(1000);
    	
    	AnimatorSet LockAnim = new AnimatorSet();
    	
    	LockAnim.play(RotatAnim1);
    	LockAnim.play(RotatAnim2).after(500);
    	LockAnim.play(RotatAnim3).after(1200);
    	LockAnim.play(FadeAnim).after(1500);
    	
    	ObjectAnimator Scalex1 = ObjectAnimator.ofFloat(OrangeView, "scaleX", 2);
    	ObjectAnimator Scalex2 = ObjectAnimator.ofFloat(GreenView, "scaleX", 2);
    	ObjectAnimator Scalex3 = ObjectAnimator.ofFloat(BlueView, "scaleX", 2);
    	ObjectAnimator Scalex4 = ObjectAnimator.ofFloat(WhiteView, "scaleX", 2);
    	ObjectAnimator Scaley1 = ObjectAnimator.ofFloat(OrangeView, "scaleY", 2);
    	ObjectAnimator Scaley2 = ObjectAnimator.ofFloat(GreenView, "scaleY", 2);
    	ObjectAnimator Scaley3 = ObjectAnimator.ofFloat(BlueView, "scaleY", 2);
    	ObjectAnimator Scaley4 = ObjectAnimator.ofFloat(WhiteView, "scaleY", 2);
    	
    	AnimatorSet ScaleAnim = new AnimatorSet();
    	ScaleAnim.play(Scalex1).with(Scalex2).with(Scalex3).with(Scalex4).with(Scaley4).with(Scaley1).with(Scaley2).with(Scaley3);
    	ScaleAnim.setInterpolator(new AccelerateDecelerateInterpolator());
    	ScaleAnim.setDuration(1);
    	
    	ScaleAnim.start();
    	LockAnim.setStartDelay(100);
    	LockAnim.start();    	

    }   
}