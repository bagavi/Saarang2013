package com.saarang;

/**
 * This is the Map Activity, duh.  
 * After parsing /mnt/sdcard/CustomMaps/iitm1.kmz 
 * it uses classes from the packages com.utils and com.kml 
 * to help with handling the png, panning etc.
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.utils.DisplayState;
import com.utils.GeoToImageConverter;
import com.utils.Globals;
import com.utils.ImageToScreenConverter;
import com.utils.InertiaScroller;
import com.utils.MapDisplay;
import com.utils.MapDisplay.MapImageTooLargeException;
import com.adapters.EventAdapter;
import com.database.DatabaseHelper;
import com.kml.GroundOverlay;
import com.kml.KmlFile;
import com.kml.KmlInfo;
import com.kml.KmlParser;
import com.kml.KmzFile;

public class MapActivity extends Activity {
    /** Called when the activity is first created. */
	private DatabaseHelper myDbHelper;
	private Cursor mCursor;
	private MapDisplay mapDisplay;
	private InertiaScroller inertiaScroller;
	private View testView1;
	private View testView2;
	private View testView3;
	private Globals g = Globals.getInstance();
	private MenuItem[] menuItem;
	private int[] eventID;
	private Cursor mCursor0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.map);
    		Log.e("Map", "Activity Started!");
    	
    	mapDisplay = (MapDisplay) findViewById(R.id.mapDisplay);
    	if(mapDisplay==null) 
    		Log.e("", "R.id.mapDisplay not found!!!");
    	testView1 = findViewById(R.id.testBtn1);
    	testView2 = findViewById(R.id.testBtn2);
    	testView3 = findViewById(R.id.testBtn3);
    	
    	DisplayState displayState = new DisplayState();
    	mapDisplay.setDisplayState(displayState);
    	inertiaScroller = new InertiaScroller(mapDisplay);
    	
    	myDbHelper = new DatabaseHelper(this);
    	try {
			Log.e("Map", "DBHelper Created");
			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
		} catch (IOException ioe) {}

    	
    	ImageButton zoomIn = (ImageButton) findViewById(R.id.zoomIn);
        ImageButton zoomOut = (ImageButton) findViewById(R.id.zoomOut);
        
        zoomIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapDisplay.zoomMap(2.0f);
				alignButtons(g, testView1);
				alignButtons(g, testView2);
				alignButtons(g, testView3);
			}
		});

        zoomOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapDisplay.zoomMap(0.5f);
				alignButtons(g, testView1);
				alignButtons(g, testView2);
				alignButtons(g, testView3);
			}
		});
        
        mapDisplay.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Log.e("Map", "Clicked " + g.getLocation() + ":D");
				alignButtons(g, testView1);
				alignButtons(g, testView2);
				alignButtons(g, testView3);
				//testView.performClick(); //Doesn't work for some reason. The context menu doesn't open fully
				return false;
			}
		});
        
        testView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("Map", "Pin clicked");
				registerForContextMenu(testView1);
				//testView.showContextMenu();
				openContextMenu(testView1);
				//unregisterForContextMenu(testView);
				//VenueAdapter venueAdapter = new VenueAdapter();
				//setListAdapter(venueAdapter);
				}
		});
    
        testView2.setOnClickListener(new View.OnClickListener() {
    			@Override
    			public void onClick(View v) {
    				Log.e("Map", "Pin clicked");
    				registerForContextMenu(testView2);
    				openContextMenu(testView2);
    				}
    		});
        
        testView3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("Map", "Pin clicked");
				registerForContextMenu(testView3);
				openContextMenu(testView3);
				}
		});
        
        // Ensure this file is present in your phone/emulator
        String localPath = "/mnt/sdcard/CustomMaps/iitm1.kmz";
        File localFile = new File(localPath);
		try {
			launchSelectMap(localFile);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MapImageTooLargeException e) {
			e.printStackTrace();
		}
    }
    
    // Caution: works only with length three
    private int[] tokenize(String now) {
    	String[] temp = new String[3];
		temp = now.split(" ");
		
		int[] timeInfo = new int[3];
		for (int i = 0; i < 3; i++)
			timeInfo[i] = Integer.parseInt(temp[i]);
		
		return timeInfo;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
    	super.onCreateContextMenu(menu, v, menuInfo);
    	Calendar c = Calendar.getInstance(TimeZone.getDefault());
//		String now = "" + c.getTime().getDate() + " " + c.getTime().getHours() + "" + c.getTime().getMinutes();
		String now = "1" + " " + c.getTime().getHours() + " " + c.getTime().getMinutes();
		int[] timeInfo = new int[3];
		timeInfo = tokenize(now);
		
		// Assuming max 10 locations
		menuItem = new MenuItem[30];
		eventID = new int[20];
		Arrays.fill(eventID, -1);
		
		// The first two arguements of menu.add are used to encode stuff, which gets decoded in onContextI....
		int row;
    	if(v == testView1) {
    		menu.setHeaderTitle("GC");
    		mCursor0 = myDbHelper.fetchDescription("GC", timeInfo);
    		if (mCursor0 == null)
    			Log.e("Map", "Whoops!");
//    		Log.e("Map", now + " " + mCursor0.getColumnCount() + " " + mCursor0.getCount());
    		String event;
    		int[] eventTime = new int[3];
    		for (row = 0; row < mCursor0.getCount(); row++) {
    			eventID[row] = mCursor0.getInt(0);
    			event = mCursor0.getString(1);
    			eventTime = tokenize(mCursor0.getString(2));
    			if (eventTime[0] > timeInfo[0]) {
    				if (eventTime[2] == 0)
    					menuItem[row] = menu.add(row, v.getId(), 0, event + " 2mrw at " + eventTime[1] + ":00");
    				else menuItem[row] = menu.add(row, v.getId(), 0, event + " 2mrw at " + eventTime[1] + ":" + eventTime[2]);
    			} else if(eventTime[0] < timeInfo[0]);
    			else {
    				if (eventTime[2] == 0)
    					menuItem[row] = menu.add(row, v.getId(), 0, event + " at " + eventTime[1] + ":00");
    				else menuItem[row] = menu.add(row, v.getId(), 0, event + " at " + eventTime[1] + ":" + eventTime[2]);
    			}
    			mCursor0.moveToNext();
    		}
    		menuItem[2] = menu.add(row, v.getId(), 0, "View all events at GC");
    	}
    	else if(v == testView2) {
    		menu.setHeaderTitle("ICSR");
    		mCursor0 = myDbHelper.fetchDescription("ICSR", timeInfo);
    		if (mCursor0 == null)
    			Log.e("Map", "Whoops!");
//    		Log.e("Map", now + " " + mCursor0.getColumnCount() + " " + mCursor0.getCount());
    		String event;
    		int[] eventTime = new int[3];
    		for (row = 0; row < mCursor0.getCount(); row++) {
    			eventID[2 + row] = mCursor0.getInt(0);
    			event = mCursor0.getString(1);
    			eventTime = tokenize(mCursor0.getString(2));
    			if (eventTime[0] > timeInfo[0]) {
    				if (eventTime[2] == 0)
    					menuItem[3 + row] = menu.add(row, v.getId(), 0, event + " 2mrw at " + eventTime[1] + ":00");
    				else menuItem[3 + row] = menu.add(row, v.getId(), 0, event + " 2mrw at " + eventTime[1] + ":" + eventTime[2]);
    			} else if(eventTime[0] < timeInfo[0]);
    			else {
    				if (eventTime[2] == 0)
    					menuItem[3 + row] = menu.add(row, v.getId(), 0, event + " at " + eventTime[1] + ":00");
    				else menuItem[3 + row] = menu.add(row, v.getId(), 0, event + " at " + eventTime[1] + ":" + eventTime[2]);
    			}
    			mCursor0.moveToNext();
    		}

			menuItem[3 + 2] = menu.add(row, v.getId(), 0, "View all events at ICSR");
    	}
    	else if(v == testView3) {
    		menu.setHeaderTitle("LIB");  
    		mCursor0 = myDbHelper.fetchDescription("LIB", timeInfo);
    		if (mCursor0 == null)
    			Log.e("Map", "Whoops!");
//    		Log.e("Map", now + " " + mCursor0.getColumnCount() + " " + mCursor0.getCount());
    		String event;
    		int[] eventTime = new int[3];
    		for (row = 0; row < mCursor0.getCount(); row++) {
    			eventID[4 + row] = mCursor0.getInt(0);
    			event = mCursor0.getString(1);
    			eventTime = tokenize(mCursor0.getString(2));
    			if (eventTime[0] > timeInfo[0]) {
    				if (eventTime[2] == 0)
    					menuItem[6 + row] = menu.add(row, v.getId(), 0, event + " 2mrw at " + eventTime[1] + ":00");
    				else menuItem[6 + row] = menu.add(row, v.getId(), 0, event + " 2mrw at " + eventTime[1] + ":" + eventTime[2]);
    			} else if(eventTime[0] < timeInfo[0]);
    			else {
    				if (eventTime[2] == 0)
    					menuItem[6 + row] = menu.add(row, v.getId(), 0, event + " at " + eventTime[1] + ":00");
    				else menuItem[6 + row] = menu.add(row, v.getId(), 0, event + " at " + eventTime[1] + ":" + eventTime[2]);
    			}
    			mCursor0.moveToNext();
    		}
        	menuItem[6 + 2] = menu.add(row, v.getId(), 0, "View all events at LIB");
    	}
    }  
  
    @Override  
    public boolean onContextItemSelected(MenuItem item) {
    	int id = item.getItemId();
//    	Log.e("Map", "id = " + id + " grpid = " + item.getGroupId());
    	for (int i = 0; i < 30; i++) {
    		if (menuItem[i] == null)
    			continue;
    		if (menuItem[i].getItemId() == id && menuItem[i].getGroupId() == item.getGroupId()) {
    			Log.e("Map", "Peace");
    			if ((i + 1)%3 == 0) {
    					switch (i + 1) {
						case 3: Log.e("Map", "Go to GC");
								break;
						case 6: Log.e("Map", "Go to ICSR");
								break;
						case 9: Log.e("Map", "Go to LIB");
								break;
						default: break;
    					}
    					break;
    			}
    			Log.e("Map", "event_id = " + eventID[(int) Math.ceil(i*(2.0)/3)]);
    			break;
    		}
    	}
    	return true;  
    }  
  
    public void alignButtons(Globals g, View v) {
    	float screen[] = new float[2];
    	float image[] = new float[2];
    	float geo[] = new float[2];        
    	
    	Log.e("Map", "Aligning buttons");
    	DisplayMetrics metrics = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams vg_lp = v.getLayoutParams();
        RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
    	
        //This copying is required because of the way the convert functions work
        if(v == testView1) {
        	geo[0] = g.GC[0];
        	geo[1] = g.GC[1];
        }
        else if(v == testView2) {
        	geo[0] = g.ICSR[0];
        	geo[1] = g.ICSR[1];
        }
        else if(v == testView3) {
        	geo[0] = g.LIB[0];
        	geo[1] = g.LIB[1];
        }
    	image = GeoToImageConverter.convertGeoToImageCoordinates(geo);
    	screen = ImageToScreenConverter.convertImageToScreenCoordinates(image);
    	rl_lp.height = (int) (30*metrics.density);
		rl_lp.width = (int) (35*metrics.density);
    	rl_lp.leftMargin = (int) (screen[0] - 0.25*rl_lp.width);
		rl_lp.topMargin = (int) (screen[1] - 0.75*rl_lp.height);
		v.requestLayout();
        v.setLayoutParams(rl_lp);		
	}

    // Launches map from that file after parsing it
    private void launchSelectMap(File localFile) throws XmlPullParserException, IOException, MapImageTooLargeException  {
    	Log.e("", "launchSelectMap");
    	GroundOverlay selected = null;
		selected = parseLocalFile(localFile);
		if(selected != null) Log.e("", "selected.getImage" + selected.getImage()); 
		mapDisplay.setMap(selected);
		Log.e("MapActivity", "Map set");
    }

    // Parses that file into a map
	private GroundOverlay parseLocalFile(File mapFile) throws XmlPullParserException, IOException {
		Collection<KmlInfo> siblings = findKmlData(mapFile.getParentFile());
		Log.e("", "siblings.size" + siblings.size());
		KmlParser parser = new KmlParser();
		for( KmlInfo sibling : siblings ) {
//			Log.e("", "Check!!! " + sibling.getFile().getName());
			if( sibling.getFile().getName().equals(mapFile.getName()) ) {
				Log.e("", "" + mapFile.getName());
				Iterable<GroundOverlay> overlays = parser.readFile(sibling.getKmlReader());
				Iterator<GroundOverlay> iter = overlays.iterator();
				if( iter.hasNext() ) {
					GroundOverlay map = iter.next();
					map.setKmlInfo(sibling);
					Log.e("", "parseLocalFile returns map");
					return map;
				}
			}
		}
		return null;
	}

	private Collection<KmlInfo> findKmlData(File directory) throws ZipException, IOException {
		Collection<KmlInfo> kmlData = new ArrayList<KmlInfo>();
		if( directory == null || !directory.exists() || !directory.isDirectory() ) {
			return kmlData;
		}
		File[] files = directory.listFiles();
		for( File file : files) {
			if( file.getName().endsWith(".kml") ) {
				kmlData.add(new KmlFile(file));	
			} else if( file.getName().endsWith(".kmz") ) {
				Log.e("", "Check " + file.getName());
				ZipFile kmzFile;
				kmzFile = new ZipFile(file);
				Enumeration<? extends ZipEntry> kmzContents = kmzFile.entries();
				while( kmzContents.hasMoreElements() ) {
					ZipEntry kmzItem = kmzContents.nextElement();
					Log.e("", "Check!" + kmzItem.getName());
					if( kmzItem.getName().endsWith(".kml") ) {
						kmlData.add(new KmzFile(kmzFile, kmzItem));
//						Log.e("", "" + kmlData.size());
					}
				}
	
			}
		}
		return kmlData;
	}
	
	@Override
	  protected void onResume() {
		Log.e("", "Le resumed");
		super.onResume();
		Log.e("", "Le super resumed");
		mapDisplay.centerOnMapCenterLocation();
		alignButtons(g, testView1);
		alignButtons(g, testView2);
		alignButtons(g, testView3);
	}
}