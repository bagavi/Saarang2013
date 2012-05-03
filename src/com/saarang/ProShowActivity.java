package com.saarang;
/*
 * This will also be changed this time
 * Basic Activity .. is called when the proshow button is clicked on the main page
 * Used to populate the ProShow Page
 * Cause the contents of this  page is decided before(unlike other events :P) this is hardcoded
 * just launch the proshow page
 */
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ProShowActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("gen view created", "created");
		setContentView(R.layout.proshow);

	}
}