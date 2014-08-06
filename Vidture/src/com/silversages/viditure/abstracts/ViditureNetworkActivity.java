package com.silversages.viditure.abstracts;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.silversages.viditure.Network.INetwork;
import com.silversages.viditure.util.SQLHelper;

public abstract class ViditureNetworkActivity extends ActionBarActivity implements INetwork{

	protected SQLHelper db;
	
	protected abstract void setupView();
	
	protected abstract void setupListner();
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		
		
	}
	
	public void showToast(String text,int duration){
		
		Toast.makeText(this, text, duration).show();
		
	}
	
}
