package com.silversages.viditure.activity;

import android.os.Bundle;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureActivity;

public class ReadSentence extends ViditureActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_sentence);
		getSupportActionBar().setTitle("Record");
		setupView();
		setupListner();

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
