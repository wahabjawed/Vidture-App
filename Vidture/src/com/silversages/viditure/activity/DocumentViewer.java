package com.silversages.viditure.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silversages.viditure.R;
import com.silversages.viditure.NetworkRequest.FetchDocRequest;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.adapter.DashboardAdapter;
import com.silversages.viditure.adapter.DocumentAdapter;
import com.silversages.viditure.objects.DocumentObject;
import com.silversages.viditure.objects.ObjectHolder;

public class DocumentViewer extends ViditureNetworkActivity {

	ListView docuemnt;
	Button startVituring;
	Dialog dialog_camera;
	Dialog dialog_agree;
	Dialog dialog_date;
	DocumentAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.document_viewer);
		getSupportActionBar().setTitle("Document Name");
		setupView();
		setupListner();
		Intent intent = getIntent();
		// check if this intent is started via custom scheme link
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			Uri uri = intent.getData();
			// may be some test here with your custom uri
			String var = uri.getQueryParameter("documents"); // "str" is set
			String path = uri.getEncodedPath();
			showToast(uri.getPath(), Toast.LENGTH_LONG);

			new FetchDocRequest(path).PerformTask(this);
		}

	}

	@Override
	public void postRequestExecute() {
		// TODO Auto-generated method stub

		DocumentObject[] documentItem = ObjectHolder.getDocumentobject();

		adapter = new DocumentAdapter(this, documentItem);
		docuemnt.setAdapter(adapter);

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

		docuemnt = (ListView) findViewById(R.id.document);
		startVituring = (Button) findViewById(R.id.startVituring);
		dialog_camera = new Dialog(DocumentViewer.this);
		dialog_agree = new Dialog(DocumentViewer.this);
		dialog_date = new Dialog(DocumentViewer.this);
		dialog_date.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_agree.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_camera.requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

		dialog_camera.setContentView(R.layout.dialog_picture);
		dialog_camera.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);
		// Include dialog.xml file

		dialog_date.setContentView(R.layout.dialog_date);
		dialog_date.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_agree.setContentView(R.layout.dialog_name);
		dialog_agree.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		Button viture = (Button) dialog_agree.findViewById(R.id.viture);
		// if decline button is clicked, close the custom dialog
		viture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog

				dialog_camera.show();
			}
		});

		TextView esignature = (TextView) dialog_agree
				.findViewById(R.id.esignature_label);

		// set values for custom dialog components - text, image and
		// button
		esignature.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_date.show();
			}
		});

		Button use_signature = (Button) dialog_date
				.findViewById(R.id.use_signature);
		// if decline button is clicked, close the custom dialog
		use_signature.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog

				startActivity(new Intent(DocumentViewer.this, DocumentName.class));
			}
		});

		// Include dialog.xml file

		Button next = (Button) dialog_camera.findViewById(R.id.nextbutton);
		// if decline button is clicked, close the custom dialog
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				dialog_agree.cancel();
				dialog_camera.cancel();
				dialog_date.cancel();
				startActivity(new Intent(DocumentViewer.this, TestCamera.class));
			}
		});

		startVituring.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog_agree.show();
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}
