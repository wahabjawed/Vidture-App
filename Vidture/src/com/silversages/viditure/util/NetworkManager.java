package com.silversages.viditure.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.silversages.viditure.ViditureApp;

public class NetworkManager {

	// static Context context;
	static ConnectivityManager connManager;

	static NetworkInfo mobile;
	static NetworkInfo mWifi;
	static Context context = ViditureApp.getContext();
	static NetworkInfo activeNetworkInfo;
	static WifiManager mainWifi;

	public static void Setup() {
		connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		activeNetworkInfo = connManager.getActiveNetworkInfo();
	}

	public static boolean IsConnected() {
		// boolean isConnect = false;
		Setup();
		if ((mWifi.isConnected() || mobile.isConnected())
				&& activeNetworkInfo != null) {
			return true;
		}
		return false;
	}

	public static void isOnWifi(String ID) {
		Setup();
		if (mWifi.isConnected() && activeNetworkInfo != null) {
			final WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			final WifiInfo _WifiInfo = wifiManager.getConnectionInfo();
			if (_WifiInfo != null) {
				_WifiInfo.getBSSID();
			}
		}

	}

}
