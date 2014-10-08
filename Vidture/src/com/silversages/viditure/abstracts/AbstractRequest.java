package com.silversages.viditure.abstracts;

import android.widget.Toast;

import com.silversages.viditure.ViditureApp;
import com.silversages.viditure.util.SQLHelper;
import com.silversages.viditure.zainu.ZainuObj;

public class AbstractRequest {

	protected SQLHelper db;
	protected ZainuObj Zainu = ZainuObj.getInstance();

	public void showToast(String text, int duration) {

		Toast.makeText(ViditureApp.getContext(), text, duration).show();

	}
}
