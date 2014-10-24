package com.silversages.viditure.Networks.request;

import android.content.Context;
import com.silversages.viditure.ViditureApp;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;

public interface IRequestHandler {

	public Context context = ViditureApp.getContext();
	// public String
	// networkAddress="http://fajjemobile.info/VidtureApp/WebService/";
	public String networkAddress = "http://dev.viditure.com";

	public abstract void PerformTask(ViditureNetworkActivity _activity);

}
