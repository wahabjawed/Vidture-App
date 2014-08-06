package com.silversages.viditure;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class ViditureApp extends Application {
	public static SQLiteDatabase db;
	public static Context context;
	public static String[][] WifiList;
	

	public static SQLiteDatabase getDb() {
		return db;
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		db = openOrCreateDatabase("VidtureApp", MODE_PRIVATE, null);
		context = getApplicationContext();
		super.onCreate();

	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		db.close();
	}

}
