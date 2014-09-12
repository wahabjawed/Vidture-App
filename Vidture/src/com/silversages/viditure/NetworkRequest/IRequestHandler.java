package com.silversages.viditure.NetworkRequest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.silversages.viditure.ViditureApp;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.util.SQLHelper;

public interface IRequestHandler {

	public Context context = ViditureApp.getContext();
	// public String
	// networkAddress="http://fajjemobile.info/VidtureApp/WebService/";
	public String networkAddress = "http://54.183.77.229:8080";

	public abstract void PerformTask(ViditureNetworkActivity _activity);

}
