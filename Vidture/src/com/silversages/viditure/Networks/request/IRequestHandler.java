package com.silversages.viditure.Networks.request;

import android.content.Context;
import com.silversages.viditure.ViditureApp;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;

public interface IRequestHandler {

	public Context context = ViditureApp.getContext();
	// public String
	// networkAddress="http://fajjemobile.info/VidtureApp/WebService/";
	public String networkAddress = "http://54.183.77.229";

	public abstract void PerformTask(ViditureNetworkActivity _activity);

}
