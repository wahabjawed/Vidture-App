package com.silversages.viditure.abstracts;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.silversages.viditure.util.SQLHelper;

public abstract class ViditureActivity extends ActionBarActivity{

	protected SQLHelper db;
	
	protected abstract void setupView();
	
	protected abstract void setupListner();
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
	}
	
	protected void showToast(String text,int duration){
		
		Toast.makeText(this, text, duration).show();
		
	}
	
}
