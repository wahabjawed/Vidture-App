package com.silversages.vidture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.silversages.vidture.R;
import com.silversages.vidture.abstracts.VidtureAppActivity;



public class Login extends VidtureAppActivity {

	EditText email;
	EditText password;
	Button login;
	Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	
	@Override
	protected void setupView() {
		// TODO Auto-generated method stub
		
		email=(EditText) findViewById(R.id.email);
		password=(EditText) findViewById(R.id.password);
		login=(Button) findViewById(R.id.login);
		register=(Button) findViewById(R.id.register);
		
	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Login.this,Register.class));
				
			}
		});
		
		
		
		
	}

}
