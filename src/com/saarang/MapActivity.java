package com.saarang;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParserException;

import com.android.Map.DisplayState;
import com.android.Map.InertiaScroller;
import com.android.Map.MapDisplay;
import com.android.Map.MapDisplay.MapImageTooLargeException;
import com.custommapsapp.android.kml.GroundOverlay;
import com.custommapsapp.android.kml.KmlFile;
import com.custommapsapp.android.kml.KmlInfo;
import com.custommapsapp.android.kml.KmlParser;
import com.custommapsapp.android.kml.KmzFile;
import com.saarang.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MapActivity extends Activity {
    /** Called when the activity is first created. */
	  private MapDisplay mapDisplay;
	  private InertiaScroller inertiaScroller;
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.map);
        Log.e("Map", "Activity Started!");
    	mapDisplay = (MapDisplay) findViewById(R.id.mapDisplay);
    	if(mapDisplay==null) Log.e("", "R.id.mapDisplay not found!!!");
    	DisplayState displayState = new DisplayState();
    	mapDisplay.setDisplayState(displayState);
    	inertiaScroller = new InertiaScroller(mapDisplay);
    	
        ImageButton zoomIn = (ImageButton) findViewById(R.id.zoomIn);
        ImageButton zoomOut = (ImageButton) findViewById(R.id.zoomOut);
        Log.e("", "zoomin:=" + zoomIn.toString());
        zoomIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapDisplay.zoomMap(2.0f);
			}
		});

        zoomOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapDisplay.zoomMap(0.5f);
			}
		});

//    	Intent intent = getIntent();
//      String action = intent.getAction();
        //Uri data = intent.getData();
        //String localPath = data.getPath();
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
    
    private void launchSelectMap(File localFile) throws XmlPullParserException, IOException, MapImageTooLargeException  {
    	Log.e("", "launchSelectMap");
    	GroundOverlay selected = null;
		selected = parseLocalFile(localFile);
		if(selected != null) Log.e("", "selected.getImage" + selected.getImage()); 
		mapDisplay.setMap(selected);
		Log.e("MapActivity", "Map set");
    }

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
	}
}