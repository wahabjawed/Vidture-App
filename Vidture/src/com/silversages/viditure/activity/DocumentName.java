package com.silversages.viditure.activity;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.gson.Gson;
import com.koushikdutta.ion.Ion;
import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureActivity;
import com.silversages.viditure.objects.ObjectHolder;
import com.silversages.viditure.objects.sendSignerData.SignerObject;

public class DocumentName extends ViditureActivity {

	Button sendSignature;

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
		sendSignature = (Button) findViewById(R.id.startVituring);
	}

	@Override
	public void setupListner() {
		// TODO Auto-generated method stub
		sendSignature.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				ObjectHolder.setSignerObject(new SignerObject());
				ObjectHolder.getSignerObject().getGeo().setCity("fdfsdf");
				Gson g = new Gson();

				Log.d("Viditure", g.toJson(ObjectHolder.getSignerObject()));

				Ion.with(DocumentName.this,
						ObjectHolder.getDocObj().getMe().getSignerInput_url())
						.setHeader("X-Auth-Toekn",
								ObjectHolder.getAuthXObject().getToken())
						.setMultipartParameter("json",
								g.toJson(ObjectHolder.getSignerObject()))
						.setMultipartFile(
								"video",
								new File(Environment
										.getExternalStorageDirectory()
										.getAbsolutePath()
										+ "/myvideo.mp4")).asJsonObject();

			}
		});
	}
}
