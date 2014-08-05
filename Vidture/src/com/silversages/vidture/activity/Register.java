package com.silversages.vidture.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.silversages.vidture.R;
import com.silversages.vidture.abstracts.VidtureAppActivity;

public class Register extends VidtureAppActivity {

	
	EditText email;
	EditText name;
	EditText password;
	Button cancel;
	Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
	}

	
	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		name=(EditText) findViewById(R.id.name);
		email=(EditText) findViewById(R.id.email);
		password=(EditText) findViewById(R.id.password);
		cancel=(Button) findViewById(R.id.cancel);
		register=(Button) findViewById(R.id.register);
	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Register.this.finish();
			}
		});
	}

}
