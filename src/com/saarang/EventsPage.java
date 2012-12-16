package com.saarang;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.adapters.EventImgAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class EventsPage extends Activity {

	ImageView[] ImageViews = new ImageView[7];
	float h, w, iw, ih;
	EventImgAdapter adp = new EventImgAdapter();
	int[][] EventCategories = { {R.drawable.dance, R.drawable.speaking, R.drawable.lit, R.drawable.music, R.drawable.quizzing},
								{R.drawable.thespian, R.drawable.finearts, R.drawable.lecdems, R.drawable.unwind, R.drawable.others}};
	int[][] BackEventCategories = { {R.drawable.bdance, R.drawable.bspeaking, R.drawable.blit, R.drawable.bmusic, R.drawable.bquizzing},
			{R.drawable.bthespian,R.drawable.bthespian, R.drawable.bfine, R.drawable.blecdems, R.drawable.bunwind, R.drawable.bothers}};
	
	private int page = 0;
	private int status = 0;	// 0=> Event Categories on Display. 1=>Events on Display.

	private float[] EvenXlist = new float[8];
	private float[] EvenYlist = new float[8];
	private float[] OddXlist = new float[7];
	private float[] OddYlist = new float[7];
	ImageView[] EventViews = new ImageView[8];

	@TargetApi(13)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events);

		Bitmap test = BitmapFactory.decodeResource(getResources(),EventCategories[0][0]);
		iw = test.getWidth();
		ih = test.getHeight();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		w = size.x;
		h = size.y;
		int id0 = R.id.imageView1;
		for (int j = 0; j < 7; j++) {
			ImageViews[j] = (ImageView) findViewById(id0);
			id0++;
		}

		/*
		 * Note the Scale being Multiplies because, all the hard coded values
		 * have been done on a 650x1000 Screen
		 */
		float[] OddX = { 0, -350 * w / 650, -350 * w / 650, -200 * w / 650,
				-200 * w / 650, -10 * w / 650, -10 * w / 650 };
		float[] OddY = { 0, 200 * h / 1000, -200 * h / 1000, -100 * h / 1000,
				100 * h / 1000, -300 * h / 1000, 300 * h / 1000 };

		float[] EvenX = { 0, 0, -450 * w / 650, -450 * w / 650, -250 * w / 650,
				-250 * w / 650, -10 * w / 650, -10 * w / 650 };
		float[] EvenY = { -150 * h / 1000, 150 * h / 1000, 300 * h / 1000,
				-300 * h / 1000, -100 * h / 1000, 100 * h / 1000,
				-400 * h / 1000, 350 * h / 1000 };

		OddXlist = OddX;
		OddYlist = OddY;

		EvenXlist = EvenX;
		EvenYlist = EvenY;

		LoadImages();
		InitialAnim();

		ImageViews[0].setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(status == 0){
					animateToCard(0);
					if(page == 1) {
						page = 0;
					}
				} else {
					if(page == 0)
						ResetChips();
				}
				Log.i("TAG", "ImageView 0");
				return false;
			}
		});

		ImageViews[1].setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("TAG", "ImageView 1");
				if(status == 0)
					animateToCard(1);
				else
					ResetChips();
				return false;
			}
		});

		ImageViews[2].setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("TAG", "ImageView 2");
				if(status == 0)
					animateToCard(2);
				else
					ResetChips();
				return false;
			}
		});

		ImageViews[3].setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("TAG", "ImageView 3");
				if(status == 0)
					animateToCard(3);
				else
					ResetChips();
				return false;
			}
		});

		ImageViews[4].setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("TAG", "ImageView 4");
				if(status == 0)
					animateToCard(4);
				else
					ResetChips();
				return false;
			}
		});

		ImageViews[5].setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("TAG", "ImageView 5");
				if(status == 0){
						if(page == 0) {
							Thread T = new Thread(new Runnable() {
								@Override
								public void run() {
									try {
										Thread.sleep(650);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									page = 1;
								}
							});
							T.start();
							
						} 
						animateToCard(5);
				} else {
					ResetChips();
				}

				return false;
			}
		});

		ImageViews[6].setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("TAG", "ImageView 6");
				if(status == 1)
					ResetChips();
				return false;
			}
		});

		EventViews[0] = (ImageView) findViewById(R.id.Ev1);
		EventViews[1] = (ImageView) findViewById(R.id.Ev2);
		EventViews[2] = (ImageView) findViewById(R.id.Ev3);
		EventViews[3] = (ImageView) findViewById(R.id.Ev4);
		EventViews[4] = (ImageView) findViewById(R.id.Ev5);
		EventViews[5] = (ImageView) findViewById(R.id.Ev6);
		EventViews[6] = (ImageView) findViewById(R.id.Ev7);
		EventViews[7] = (ImageView) findViewById(R.id.Ev8);
		
		for(int k = 0; k < 8; k++){
			EventViews[k].setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					/***********************************************************/
					// Have to add calls to the Event Page.
					/************************************************************/
					return false;
				}
			});
		}


	}

	/*
	 * The Initial S motion of Cards TODO Adjust the timings....
	 */
	private void InitialAnim() {
		Log.e("LOG", Float.toString((w - ImageViews[1].getWidth() - 10)));
		AnimatorSet H1 = new AnimatorSet();
		H1.playTogether(
				ObjectAnimator.ofFloat(ImageViews[1], "translationX", -380 * w
						/ 650),
				ObjectAnimator.ofFloat(ImageViews[2], "translationX", -380 * w
						/ 650),
				ObjectAnimator.ofFloat(ImageViews[3], "translationX", -380 * w
						/ 650),
				ObjectAnimator.ofFloat(ImageViews[4], "translationX", -380 * w
						/ 650),
				ObjectAnimator.ofFloat(ImageViews[5], "translationX", -380 * w
						/ 650));

		H1.setDuration(300);
		H1.start();

		AnimatorSet V1 = new AnimatorSet();
		V1.playTogether(ObjectAnimator.ofFloat(ImageViews[2], "translationY",
				350 * h / 1000), ObjectAnimator.ofFloat(ImageViews[3],
				"translationY", 350 * h / 1000), ObjectAnimator.ofFloat(
				ImageViews[4], "translationY", 350 * h / 1000), ObjectAnimator
				.ofFloat(ImageViews[5], "translationY", 350 * h / 1000));

		V1.setDuration(300);
		V1.setStartDelay(300);
		V1.start();

		AnimatorSet H2 = new AnimatorSet();
		H2.playTogether(ObjectAnimator.ofFloat(ImageViews[3], "translationX",
				(0) * w / 650), ObjectAnimator.ofFloat(ImageViews[4],
				"translationX", 0 * w / 650), ObjectAnimator.ofFloat(
				ImageViews[5], "translationX", 0 * w / 650));

		H2.setDuration(300);
		H2.setStartDelay(600);
		H2.start();

		AnimatorSet V2 = new AnimatorSet();
		V2.playTogether(ObjectAnimator.ofFloat(ImageViews[4], "translationY",
				650 * h / 1000), ObjectAnimator.ofFloat(ImageViews[5],
				"translationY", 650 * h / 1000));

		V2.setDuration(300);
		V2.setStartDelay(900);
		V2.start();

		AnimatorSet H3 = new AnimatorSet();
		H3.playTogether(ObjectAnimator.ofFloat(ImageViews[5], "translationX",
				-380 * w / 650));

		H3.setDuration(300);
		H3.setStartDelay(1200);
		H3.start();
	}

	/*
	 * Loading Images initially, ie the first set of events TODO:- Put all image
	 * ids in an array and Use it to generate the images
	 */
	private void LoadImages() {
		for(int j = 0; j < 5; j++) {
			ImageViews[j].setImageResource(EventCategories[0][j]);
		}
		ImageViews[5].setImageResource(R.drawable.more); // TODO Set to more
		
		ObjectAnimator[] scale = new ObjectAnimator[6];
		for(int j = 0; j < 6; j++) {
			scale[j] = ObjectAnimator.ofFloat(ImageViews[j], "scale", 3f);
		}
	}

	private void ResetChips() {
		ObjectAnimator[] Obj = new ObjectAnimator[24];	//Max 8
		for(int k = 0;  k < 8; k++) {
			Obj[k] = ObjectAnimator.ofFloat(EventViews[k], "TranslationX", 0);
		}
		for(int k = 0;  k < 8; k++) {
			Obj[k+8] = ObjectAnimator.ofFloat(EventViews[k], "TranslationY", 0);
		}
		for(int k = 0;  k < 8; k++) {
			Obj[k+16] = ObjectAnimator.ofFloat(EventViews[k], "alpha", 0);
		}
		
		AnimatorSet Reset = new AnimatorSet();
		Reset.playTogether(Obj);
		Reset.start();
		
		ObjectAnimator MoveBack[] = new ObjectAnimator[15];
		MoveBack[0] = ObjectAnimator.ofFloat(ImageViews[0], "translationX", 0);
		MoveBack[1] = ObjectAnimator.ofFloat(ImageViews[1], "translationX", -380*w/650);
		MoveBack[2] = ObjectAnimator.ofFloat(ImageViews[2], "translationX", -380*w/650);
		MoveBack[3] = ObjectAnimator.ofFloat(ImageViews[3], "translationX", 0*w/650);
		MoveBack[4] = ObjectAnimator.ofFloat(ImageViews[4], "translationX", 0*w/650);
		MoveBack[5] = ObjectAnimator.ofFloat(ImageViews[5], "translationX", -380*w/650);
		MoveBack[6] = ObjectAnimator.ofFloat(ImageViews[6], "translationX", 0*w/650);
		MoveBack[7] = ObjectAnimator.ofFloat(ImageViews[0], "translationY", 0);
		MoveBack[8] = ObjectAnimator.ofFloat(ImageViews[1], "translationY", 0);
		MoveBack[9] = ObjectAnimator.ofFloat(ImageViews[2], "translationY", (350 * h / 1000));
		MoveBack[10] = ObjectAnimator.ofFloat(ImageViews[3], "translationY", (350 * h / 1000));
		MoveBack[11] = ObjectAnimator.ofFloat(ImageViews[4], "translationY", (650 * h / 1000));
		MoveBack[12] = ObjectAnimator.ofFloat(ImageViews[5], "translationY", (650 * h / 1000));
		MoveBack[13] = ObjectAnimator.ofFloat(ImageViews[6], "translationY", (0 * h / 1000));
		MoveBack[14] = ObjectAnimator.ofFloat(ImageViews[6], "alpha", 0);
		
		AnimatorSet ResetCards = new AnimatorSet();
		ResetCards.playTogether(MoveBack);
		ResetCards.start();
		//
		Thread T = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(610);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				status = 0;
			}
		});
		T.start();
		
	}
	/*
	 * To be called when the more tab is selected
	 */
	private void LoadImages2() {
		for(int j = 0; j < 5; j++) {
			ImageViews[j+1].setImageResource(EventCategories[1][j]);
		}
		ImageViews[0].setImageResource(R.drawable.back); // TODO Set to more
		
		ObjectAnimator[] scale = new ObjectAnimator[6];
		for(int j = 0; j < 6; j++) {
			scale[j] = ObjectAnimator.ofFloat(ImageViews[j], "scale", 1.3f);
		}
		
		
	}

	private void RevSanim() {
		
		AnimatorSet H1 = new AnimatorSet();
		H1.playTogether(
				ObjectAnimator.ofFloat(ImageViews[1], "translationX", 0),
				ObjectAnimator.ofFloat(ImageViews[2], "translationX", 0),
				ObjectAnimator.ofFloat(ImageViews[3], "translationX", 0),
				ObjectAnimator.ofFloat(ImageViews[4], "translationX", 0),
				ObjectAnimator.ofFloat(ImageViews[0], "translationX", 0));

		H1.setDuration(300);
		H1.setStartDelay(300);
		H1.start();

		AnimatorSet V1 = new AnimatorSet();
		V1.playTogether(
				ObjectAnimator.ofFloat(ImageViews[2], "translationY", 350 * h / 1000),
				ObjectAnimator.ofFloat(ImageViews[3], "translationY", 350 * h / 1000),
				ObjectAnimator.ofFloat(ImageViews[1], "translationY", 350 * h / 1000),
				ObjectAnimator.ofFloat(ImageViews[0], "translationY", 350 * h / 1000));

		V1.setDuration(300);
		V1.setStartDelay(300 + 300);
		V1.start();

		AnimatorSet H2 = new AnimatorSet();
		H2.playTogether(
				ObjectAnimator.ofFloat(ImageViews[2], "translationX", -380 * w / 650),
				ObjectAnimator.ofFloat(ImageViews[1], "translationX", -380 * w / 650),
				ObjectAnimator.ofFloat(ImageViews[0], "translationX", -380 * w / 650));

		H2.setDuration(300);
		H2.setStartDelay(600 + 300);
		H2.start();

		AnimatorSet V2 = new AnimatorSet();
		V2.playTogether(
				ObjectAnimator.ofFloat(ImageViews[1], "translationY", 0 * h / 1000),
				ObjectAnimator.ofFloat(ImageViews[0], "translationY", 0 * h / 1000));

		V2.setDuration(300);
		V2.setStartDelay(900 + 300);
		V2.start();

		AnimatorSet H3 = new AnimatorSet();
		H3.playTogether(
				ObjectAnimator.ofFloat(ImageViews[0], "translationX", 0));

		H3.setDuration(300);
		H3.setStartDelay(1200 + 300);
		H3.start();

	}
	/*
	 * Animations on Click TODO Timings Add the back card after each
	 */
	private void animateToCard(int i) {
		float targetX, targetY;

		switch (i) {
		case 0:
			targetX = 0;
			targetY = 0;
			break;
		case 1:
			targetX = (-380 * w / 650);
			targetY = 0;
			break;
		case 2:
			targetX = (-380 * w / 650);
			targetY = (350 * h / 1000);
			break;
		case 3:
			targetX = 0;
			targetY = (350 * h / 1000);
			break;
		case 4:
			targetX = 0;
			targetY = (650 * h / 1000);
			break;
		case 5:
			targetX = (-380 * w / 650);
			targetY = (650 * h / 1000);
			break;
		default:
			targetX = 0;
			targetY = 0;
			break;

		}

		int k;
		ObjectAnimator[] CloseInAnim = new ObjectAnimator[12];
		for(k = 0; k < 6; k++) {
			CloseInAnim[k] = ObjectAnimator.ofFloat(ImageViews[k], "translationX", targetX);
		}
		for(k = 0; k < 6; k++) {
			CloseInAnim[k+6] = ObjectAnimator.ofFloat(ImageViews[k], "translationY", targetY);
		}
		AnimatorSet CloseIn = new AnimatorSet();
		CloseIn.playTogether(CloseInAnim);
		CloseIn.setDuration(300);
		CloseIn.start();
		//First the cards close in to the selected card.
		
		float ftargetX = (-500 * w / 650);
		float ftargetY = (350 * h / 1000);
		
		ObjectAnimator[] MoveInAnim = new ObjectAnimator[12];
		for(k = 0; k < 6; k++) {
			MoveInAnim[k] = ObjectAnimator.ofFloat(ImageViews[k], "translationX", ftargetX);
		}
		for(k = 0; k < 6; k++) {
			MoveInAnim[k+6] = ObjectAnimator.ofFloat(ImageViews[k], "translationY", ftargetY);
		}
		
		AnimatorSet Move = new AnimatorSet();
		Move.playTogether(MoveInAnim);
		Move.setDuration(300);
		Move.setStartDelay(300);
		
		if ((i != 5 && page ==0) || (i != 0 && page == 1 )) {
			status = 1;
			Move.start();
			Log.e("Tag", "Card Id" + i);
			int NoOfEvents = adp.getNo(page, i);
			Log.e("TAG", "NO of events " + NoOfEvents);
			boolean n;
			if (NoOfEvents % 2 == 0) {
				n = true;
			} else
				n = false;

			float[] TarX = n ? EvenXlist : OddXlist;
			float[] TarY = n ? EvenYlist : OddYlist;
			
			Log.i("TAG", "No Of Events in this Category" + Integer.toString(NoOfEvents));
			
			ObjectAnimator[] objanimX = new ObjectAnimator[NoOfEvents];
			ObjectAnimator[] objanimY = new ObjectAnimator[NoOfEvents];
			ObjectAnimator[] fadeout = new ObjectAnimator[NoOfEvents];
			for (int j = 0; j < NoOfEvents; j++) {
				EventViews[j].setImageResource(adp.getEvent(page, i, j));
				objanimX[j] = ObjectAnimator.ofFloat(EventViews[j], "translationX", TarX[j]);
				objanimY[j] = ObjectAnimator.ofFloat(EventViews[j], "translationY", TarY[j]);
				fadeout[j] = ObjectAnimator.ofFloat(EventViews[j], "alpha", 100);
			}

			
			AnimatorSet EventSet = new AnimatorSet();
			EventSet.playTogether(objanimX);
			EventSet.playTogether(objanimY);
			EventSet.playTogether(fadeout);
			EventSet.setDuration(300);
			EventSet.setStartDelay(0);
			EventSet.start();
			
			
			
			/*
			 * Adding back button functional
			 */
			//TODO
			ImageViews[6].setImageResource(BackEventCategories[page][i]);
			ObjectAnimator FadeAnim = ObjectAnimator.ofFloat(ImageViews[6], "alpha", 0);
	    	FadeAnim.start();
			AnimatorSet overlay = new AnimatorSet();
			overlay.playSequentially(ObjectAnimator.ofFloat(ImageViews[6],  "translationX", ftargetX),
								ObjectAnimator.ofFloat(ImageViews[6],  "translationY", ftargetY),
								ObjectAnimator.ofFloat(ImageViews[6], "alpha", 100));
			overlay.setStartDelay(600);
			overlay.start();
		} else if( i ==5){
			LoadImages2();
			RevSanim();
		}  else if( i == 0) {
			LoadImages();
			InitialAnim();
			page = 0;
		}

		/*
		 * Load the Poker Chips for the events...
		 */
	}

}
