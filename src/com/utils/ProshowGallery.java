package com.utils;
/*
 * Vivek has no fundeas to document this. But as i can see, this is a very cup code
 * The whole proshow display will be changed this time
 */
import com.saarang.R;
import com.saarang.R.drawable;
import com.saarang.R.id;
import com.saarang.R.layout;
import com.saarang.R.styleable;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProshowGallery extends Activity {
	/*
	 * Have a correspoding text field image on click in this array and we can
	 * display nice effects
	 */
	/*
	 * Other alternative is having a text view in proshow.xml and display
	 * normally. Can be decided later
	 */
	Integer[] pics = { R.drawable.proshow1, R.drawable.proshow2,
			R.drawable.proshow3, R.drawable.proshow4,

	};
	ImageView imageView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proshow);

		Gallery ga = (Gallery) findViewById(R.id.Gallery01);
		ga.setAdapter(new ImageAdapter(this));
		Log.e("created gallery", "pixtures");
		imageView = (ImageView) findViewById(R.id.ImageView01);

		ga.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView t = (TextView) (findViewById(R.id.TextView01));

				t.setText("Proshow" + (arg2 + 1));
				imageView.setImageResource(pics[arg2]);

			}

		});

	}

	public class ImageAdapter extends BaseAdapter {

		private Context ctx;
		int imageBackground;

		public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(
					R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		public int getCount() {

			return pics.length;
		}

		public Object getItem(int arg0) {

			return arg0;
		}

		public long getItemId(int arg0) {

			return arg0;
		}

		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ImageView iv = new ImageView(ctx);
			iv.setImageResource(pics[arg0]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(150, 120));
			iv.setBackgroundResource(imageBackground);
			return iv;
		}

	}
}