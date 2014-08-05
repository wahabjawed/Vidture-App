package com.silversages.vidture.activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.silversages.vidture.R;
import com.silversages.vidture.abstracts.VidtureAppActivity;

public class Dashboard extends VidtureAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccount);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub
		
	}
}
