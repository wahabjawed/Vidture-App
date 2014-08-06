package com.silversages.viditure.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;

public class DocumentViewer extends ViditureNetworkActivity {

	EditText docuemnt;
	Button startVituring;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.document_viewer);
		setupView();
		setupListner();

	}

	@Override
	public void postRequestExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void preRequestExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void progressUpdate(String update) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		docuemnt = (EditText) findViewById(R.id.document);
		startVituring = (Button) findViewById(R.id.startVituring);
	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

		final Dialog camera = new Dialog(DocumentViewer.this);

		final Dialog date = new Dialog(DocumentViewer.this);

		camera.setContentView(R.layout.dialog_picture);
		camera.getWindow().setBackgroundDrawableResource(R.drawable.dialogbox);
		// Include dialog.xml file
		date.setContentView(R.layout.dialog_date);
		date.getWindow().setBackgroundDrawableResource(R.drawable.dialogbox);

		Button use_signature = (Button) date.findViewById(R.id.use_signature);
		// if decline button is clicked, close the custom dialog
		use_signature.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				 camera.show();
			}
		});

		// Include dialog.xml file

		Button next = (Button) camera.findViewById(R.id.next);
		// if decline button is clicked, close the custom dialog
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				// camera.dismiss();
			}
		});

		startVituring.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Create custom dialog object
				final Dialog dialog = new Dialog(DocumentViewer.this);
				// Include dialog.xml file
				dialog.setContentView(R.layout.dialog_name);

				dialog.getWindow().setBackgroundDrawableResource(
						R.drawable.dialogbox);

				// set values for custom dialog components - text, image and
				// button

				dialog.show();

				Button viture = (Button) dialog.findViewById(R.id.viture);
				// if decline button is clicked, close the custom dialog
				viture.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// Close dialog
						date.show();
					}
				});

			}

		});

	}

}
