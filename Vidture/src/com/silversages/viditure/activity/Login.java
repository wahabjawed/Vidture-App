package com.silversages.viditure.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.silversages.viditure.R;
import com.silversages.viditure.Network.Request.LoginRequest;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.util.NetworkManager;

public class Login extends ViditureNetworkActivity {

	EditText email;
	EditText password;
	Button login;
	Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setupView();
		setupListner();
		
	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);

	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Login.this, Register.class));

			}
		});

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(NetworkManager.IsConnected()){
				
					new LoginRequest(email.getText().toString(), password.getText()
						.toString()).PerformTask(Login.this);
				}else{
					
					showToast("Couldn't connect to internet", Toast.LENGTH_LONG);
					
				}
			}
		});

	}

	@Override
	public void postRequestExecute() {
		// TODO Auto-generated method stub
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("first_time", true);
		editor.commit();
		startActivity(new Intent(Login.this,Dashboard.class));
		this.finish();
		
	}

	@Override
	public void preRequestExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void progressUpdate(String update) {
		// TODO Auto-generated method stub

	}

}
