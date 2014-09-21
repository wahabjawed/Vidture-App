package com.silversages.viditure.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.adapter.DashboardAdapter;
import com.silversages.viditure.objects.DashboardObject;
import com.silversages.viditure.util.SQLHelper;

public class Dashboard extends ViditureNetworkActivity {

	ListView List;
	TextView ProText;
	DashboardObject[] dashboardItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (prefs.getBoolean("first_time", false)) {

			setContentView(R.layout.dashboard);
			setupView();
			setupListner();

		} else {
			
			SQLHelper.SetupDB(this);
			startActivity(new Intent(this,Login.class));
			this.finish();
		}

		
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
		List = (ListView) findViewById(R.id.dListView);
		ProText = (TextView) findViewById(R.id.mProText);
	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postRequestExecute() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void preRequestExecute() {
		// TODO Auto-generated method stub
		Cursor _cursor = null;

		_cursor = db.getDashboardContactList();

		Log.d(" VidtureApp--DashboardList", "Cursor populated");
		if (_cursor.getCount() > 0) {
			dashboardItem = new DashboardObject[_cursor.getCount()];
			Log.d(" VidtureApp--DashboardList", "Data Exist");
			_cursor.moveToFirst();
			Log.d(" VidtureApp--DashboardList", "Populating Adapter");

		
			for (int i = 0; i < _cursor.getCount(); i++) {

				dashboardItem[i] = (new DashboardObject(_cursor.getInt(_cursor
						.getColumnIndex("ID")), _cursor.getString(_cursor
						.getColumnIndex("name")), _cursor.getString(_cursor
						.getColumnIndex("number")), _cursor.getBlob(_cursor
						.getColumnIndex("displayPic"))));

				_cursor.moveToNext();
			}

			_cursor.close();

			DashboardAdapter adapter = new DashboardAdapter(this, dashboardItem);

			List.setAdapter(adapter);
			ProText.setVisibility(View.INVISIBLE);
			Log.d(" VidtureApp--DashboardList", "Populated");
		} else {

			dashboardItem = new DashboardObject[_cursor.getCount()];
			ProText.setVisibility(View.VISIBLE);
			Log.d(" VidtureApp--DashboardList", "No Data Found");
		}
	}

	@Override
	public void progressUpdate(String update) {
		// TODO Auto-generated method stub
		
	}
}
