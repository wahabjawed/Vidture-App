package com.silversages.viditure.activity;

import android.os.Bundle;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureActivity;

public class DocumentName extends ViditureActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.document_name);
		getSupportActionBar().setTitle("Document Name");
		setupView();
		setupListner();

	}

	@Override
	public void setupView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupListner() {
		// TODO Auto-generated method stub

	}

}
