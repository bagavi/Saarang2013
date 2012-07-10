package com.database;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
/*
 * This is standard way of defining a database helper class
 * Read a sample DatabaseHelper class before reading this cause
 * in the sample version there are few columns   
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.saarang/databases/";
	private static String DB_NAME = "event_detail.db";
	private static String TAG = "Databasehelper";
	
	public static final String TABLE_NAME = "Event_Details";
	public static final String KEY_EVENT_ID = "_id";

	public static final String GROUP_EVENT_ID = "group_id";
	public static final int GROUP_EVENT_ID_COLUMN = 3;

	
	public static final String NAME = "EVENT_NAME";
	public static final int EVENT_NAME_COLUMN = 2;

	public static final String DESCRIPTION = "description";
	public static final int DESCREPTION_COLUMN = 3;

	public static final String INTRODUCTION = "introduction";
	public static final int INTRODUCTION_COLUMN = 4;

	/*
	 * Table Two details(Coordinators)
	 */

	public static final String COORD_TABLE_NAME = "Coords";
	public static final String COORD_ID = "_id";
	

	public static final String COORD_NAME = "coord_name";
	public static final int COORD_NAME_COLUMN = 1;

	public static final String COORD_NUMBER = "coord_number";
	public static final int COORD_NUMBER_COLUMN = 2;
	
	public static final String COORD_EVENT_ID = "event_id";
	public static final int COORD_EVENT_ID_COLUMN = 3;
	
	private SQLiteDatabase myDatabase;
	private final Context myContext;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.myContext = context;

	}

	public DatabaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	public void createDataBase() throws IOException {
		Log.e("DATABASE" , "CHECKING Databsae ");
		boolean dbExist = checkDataBase();
		if (dbExist) {
			Log.e("INFO" , "THE DATABASE EXISTS");
		} else {
			this.getReadableDatabase();
			
			try
			{
				//Copy the database when this app is installed for the first time
				copyDataBase();
			} 
			
			catch (IOException e) {
				Log.e("databse error", "No database");
				throw new Error("Error copying database " + e.toString());
			}
		}
	}

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			Log.e("database error", e.toString());

		}

		if (checkDB != null) {
			Log.e("INFO" , "The databse exists and closing it in the if statement");
			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transferring byte stream.
	 * */

	/*
	 * Opens two stream path and copies the database from assets to the
	 * "proper place"
	 */
	private void copyDataBase() throws IOException {

		
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		
		String outFileName = DB_PATH + DB_NAME;
		
		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		
		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
		
			myOutput.write(buffer, 0, length);
		}

		Log.e("INFO" ,"THe datbase is copied for the first time");
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDatabase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS events");
		onCreate(db);
	}

	public synchronized void close() {

		if (myDatabase != null)
			myDatabase.close();
		super.close();

	}

	/*
	 * This is API which gives us the event details if asked by id
	 */

	public Cursor fetchDescription(long eventId) throws SQLException {

		Cursor mCursor =

		myDatabase.query(true, TABLE_NAME, new String[] { KEY_EVENT_ID, NAME,
				DESCRIPTION, INTRODUCTION}, KEY_EVENT_ID + "="
				+ eventId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/*
	 * This is API which gives us the event details if asked by id and title
	 */
	public Cursor fetchDescription(long eventId, String title)
			throws SQLException {

		Long id = Long.valueOf(eventId);
		Cursor mCursor =

		myDatabase.query(TABLE_NAME, new String[] { KEY_EVENT_ID, NAME,
				DESCRIPTION }, KEY_EVENT_ID + "= ? AND " + NAME + "= ?",
				new String[] { id.toString(), title }, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	public Cursor fetchCordDetails(long eventId) throws SQLException {
		Cursor mCursor;
		if (eventId > 0) {
			Long id = Long.valueOf(eventId);

			mCursor = myDatabase.query(COORD_TABLE_NAME, new String[] { COORD_ID,
					COORD_NAME, COORD_NUMBER, COORD_EVENT_ID }, "event_id= ?",
					new String[] { id.toString() }, null, null, null);
			if (mCursor != null) {
				mCursor.moveToFirst();
			}
		} else {
			
			mCursor = myDatabase.query(COORD_TABLE_NAME, new String[] { COORD_ID,
					COORD_NAME,COORD_NUMBER, COORD_EVENT_ID }, null, null, null, null, null);
			if (mCursor != null) {
				mCursor.moveToFirst();
			}
		}
		return mCursor;
	}

	/*
	 * This is API which gives us all coords details
	 */
	public Cursor fetchAllCords() throws SQLException {
		Cursor mCursor;
		mCursor = myDatabase.query(COORD_TABLE_NAME, new String[] { COORD_ID,
				COORD_NAME,COORD_NUMBER, COORD_EVENT_ID }, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}

		return mCursor;
	}

	/*
	 * This is API which gives us the coord details ,The search string may by
	 * anything , may be not so efficient WILL not work yet
	 */
	public Cursor fetchCords(String search) throws SQLException {
		Cursor mCursor;
		mCursor = myDatabase.query("coords", new String[] { "_id", "name",
				"phone", "dept" }, "name like ? or dept like ?", new String[] {
				"%" + search + "%", "%" + search + "%" }, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

}
