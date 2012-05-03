package com.saarang;

/*
 * A very simple Activity and very badly written(Vivek did not write this :P)
 * Dont read this now cuasde we will be changing it completely this time
 */
import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SponsActivity extends Activity {

	Integer[] pics = { R.drawable.spons1, R.drawable.spons2, R.drawable.spons3,
			R.drawable.spons4, R.drawable.spons5, R.drawable.spons6,
			R.drawable.spons7, R.drawable.spons8, R.drawable.spons9,
			R.drawable.spons10, R.drawable.spons11, R.drawable.spons12,
			R.drawable.spons13, R.drawable.spons14, R.drawable.spons15,
			R.drawable.spons16, R.drawable.spons17,

	};
	String text[] = { "spons1", "spons2", "spons3", "spons4", "spons5",
			"spons6", "spons7", "spons8", "spons9", "spons10", "spons11",
			"spons12", "spons13", "spons14", "spons15", "spons16", "spons17", };
	ImageView imageView1, imageView2, imageView3, imageView4, imageView5,
			imageView6, imageView7, imageView8, imageView9, imageView10,
			imageView11, imageView12, imageView13, imageView14, imageView15,
			imageView16, imageView17;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sponscategories);

		imageView1 = (ImageView) findViewById(R.id.ImageView01);
		imageView2 = (ImageView) findViewById(R.id.ImageView02);
		imageView3 = (ImageView) findViewById(R.id.ImageView03);
		imageView4 = (ImageView) findViewById(R.id.ImageView04);
		imageView5 = (ImageView) findViewById(R.id.ImageView5);
		imageView6 = (ImageView) findViewById(R.id.ImageView6);
		imageView7 = (ImageView) findViewById(R.id.ImageView7);
		imageView8 = (ImageView) findViewById(R.id.ImageView8);
		imageView9 = (ImageView) findViewById(R.id.ImageView9);
		imageView10 = (ImageView) findViewById(R.id.ImageView10);
		imageView11 = (ImageView) findViewById(R.id.ImageView11);
		imageView12 = (ImageView) findViewById(R.id.ImageView12);
		imageView13 = (ImageView) findViewById(R.id.ImageView13);
		imageView14 = (ImageView) findViewById(R.id.ImageView14);
		imageView15 = (ImageView) findViewById(R.id.ImageView15);
		imageView16 = (ImageView) findViewById(R.id.ImageView16);
		imageView17 = (ImageView) findViewById(R.id.ImageView17);

		imageView1.setImageResource(pics[0]);
		imageView2.setImageResource(pics[1]);
		imageView3.setImageResource(pics[2]);
		imageView4.setImageResource(pics[3]);
		imageView5.setImageResource(pics[4]);

		imageView6.setImageResource(pics[5]);
		imageView7.setImageResource(pics[6]);
		imageView8.setImageResource(pics[7]);
		imageView9.setImageResource(pics[8]);
		imageView10.setImageResource(pics[9]);
		imageView11.setImageResource(pics[10]);
		imageView12.setImageResource(pics[11]);
		imageView13.setImageResource(pics[12]);
		imageView14.setImageResource(pics[13]);
		imageView15.setImageResource(pics[14]);
		imageView16.setImageResource(pics[15]);
		imageView17.setImageResource(pics[16]);

		imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				Toast.makeText(context, text[0], Toast.LENGTH_SHORT).show();

			}

		});

		imageView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				Toast.makeText(context, text[1], Toast.LENGTH_SHORT).show();

			}

		});

		imageView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				Toast.makeText(context, text[2], Toast.LENGTH_SHORT).show();

			}

		});

		imageView4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				Toast.makeText(context, text[3], Toast.LENGTH_SHORT).show();

			}

		});

		imageView5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				Toast.makeText(context, text[4], Toast.LENGTH_SHORT).show();

			}

		});

		/*
		 * imageView6.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[5],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * imageView7.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[6],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * imageView8.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[7],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * imageView9.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[8],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * imageView10.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[9],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * imageView11.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[10],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * imageView12.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[11],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * 
		 * imageView13.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[12],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * imageView14.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[13],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * imageView15.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[14],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * imageView16.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[15],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 * 
		 * 
		 * 
		 * imageView17.setOnClickListener(new OnClickListener() {
		 * 
		 * 
		 * @Override public void onClick(View v) { Context context =
		 * getApplicationContext(); Toast.makeText(context, text[16],
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * });
		 */

	}

}
