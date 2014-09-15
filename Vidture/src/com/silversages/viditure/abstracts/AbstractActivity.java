package com.silversages.viditure.abstracts;

import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.silversages.viditure.util.SQLHelper;
import com.silversages.viditure.zainu.ZainuObj;

public abstract class AbstractActivity extends ActionBarActivity {

	protected SQLHelper db;
	protected ZainuObj Zainu = ZainuObj.getInstance();

	protected abstract void setupView();

	protected abstract void setupListner();

	public void showToast(String text, int duration) {

		Toast.makeText(this, text, duration).show();

	}
}
