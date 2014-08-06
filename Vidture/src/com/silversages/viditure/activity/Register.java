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
import com.silversages.viditure.NetworkRequest.RegisterRequest;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.util.NetworkManager;

public class Register extends ViditureNetworkActivity {

	
	EditText email;
	EditText name;
	EditText password;
	Button cancel;
	Button register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		setupView();
		setupListner();
	}

	
	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		name=(EditText) findViewById(R.id.name);
		email=(EditText) findViewById(R.id.email);
		password=(EditText) findViewById(R.id.password);
		cancel=(Button) findViewById(R.id.cancel);
		register=(Button) findViewById(R.id.create);
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
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(NetworkManager.IsConnected()){
					
					new RegisterRequest(name.getText().toString(), email.getText().toString(), password.getText().toString()).PerformTask(Register.this);
					
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
		startActivity(new Intent(Register.this,Dashboard.class));
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
