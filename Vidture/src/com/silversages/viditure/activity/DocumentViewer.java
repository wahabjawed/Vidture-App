package com.silversages.viditure.activity;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.silversages.viditure.R;
import com.silversages.viditure.Networks.request.FetchDocRequest;
import com.silversages.viditure.abstracts.ViditureNetworkActivity;
import com.silversages.viditure.adapter.DocumentAdapter;
import com.silversages.viditure.objects.ObjectHolder;
import com.silversages.viditure.objects.fetchDocument.Pages;
import com.silversages.viditure.util.Signature;

public class DocumentViewer extends ViditureNetworkActivity {

	private static final int CAMERA = 0;
	ListView docuemnt;
	Button startVituring;
	Dialog dialog_camera;
	Dialog dialog_agree;
	Dialog dialog_date;
	Dialog dialog_accept;
	Dialog dialog_take_pic;
	Dialog dialog_set_pic;
	DocumentAdapter adapter;

	View mView;
	Signature mSignature;
	LinearLayout mContent;
	File mypath;
	ImageView dilaog_image;
	public static String tempDir;
	public int count = 1;
	public String current = null;
	private String uniqueId;

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
			// String var = uri.getQueryParameter("documents"); // "str" is set
			// String path = uri.getEncodedPath();
			// showToast(uri.getPath(), Toast.LENGTH_LONG);
			// new FetchDocRequest(uri.getEncodedPath()).PerformTask(this);
			
		}
		new FetchDocRequest("/vts/signrequest/54324d60e4b000f651da5597")
		.PerformTask(this);

	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (CAMERA):
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				Bitmap bmp = (Bitmap) extras.get("data");
				dialog_take_pic.dismiss();
				dialog_set_pic.show();
				dilaog_image.setImageBitmap(bmp);

			}
			break;

		}
	}

	@Override
	public void postRequestExecute() {
		// TODO Auto-generated method stub

		// showToast(ObjectHolder.getDocObj().getPages()[0].getPageImage_url(),
		// Toast.LENGTH_LONG);

		Pages[] documentItem = ObjectHolder.getDocObj().getPages();

		adapter = new DocumentAdapter(this, documentItem);
		docuemnt.setAdapter(adapter);
		startVituring.setEnabled(true);

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
	public void setupView() {
		// TODO Auto-generated method stub

		docuemnt = (ListView) findViewById(R.id.document);
		startVituring = (Button) findViewById(R.id.startVituring);
		dialog_camera = new Dialog(DocumentViewer.this);
		dialog_agree = new Dialog(DocumentViewer.this);
		dialog_date = new Dialog(DocumentViewer.this);
		dialog_accept = new Dialog(DocumentViewer.this);
		dialog_take_pic = new Dialog(DocumentViewer.this);
		dialog_set_pic = new Dialog(DocumentViewer.this);
		dialog_date.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_agree.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_camera.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_accept.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_take_pic.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_set_pic.requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	@Override
	public void setupListner() {
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

		dialog_accept.setContentView(R.layout.dialog_confirm);
		dialog_accept.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);
		dialog_accept.setCanceledOnTouchOutside(false);
		dialog_accept.show();

		dialog_take_pic.setContentView(R.layout.dialog_take_picture);
		dialog_take_pic.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_set_pic.setContentView(R.layout.dialog_pick_picture);
		dialog_set_pic.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dilaog_image = (ImageView) dialog_set_pic.findViewById(R.id.imageView1);
		Button use_this = (Button) dialog_set_pic.findViewById(R.id.use_this);
		use_this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_set_pic.dismiss();
			}
		});

		Button re_take = (Button) dialog_set_pic.findViewById(R.id.re_take);
		re_take.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, CAMERA);

			}
		});

		tempDir = Environment.getExternalStorageDirectory() + "/"
				+ "GetSignature" + "/";
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		File directory = cw.getDir("GetSignature", Context.MODE_PRIVATE);

		Zainu.getFileIO().prepareDirectory(tempDir);
		uniqueId = Zainu.getDateTime().getTodaysDate() + "_"
				+ Zainu.getDateTime().getCurrentTime() + "_" + Math.random();
		current = uniqueId + ".png";
		mypath = new File(directory, current);

		mContent = (LinearLayout) dialog_date.findViewById(R.id.signature);
		mSignature = new Signature(this, null);
		mSignature.setField(mContent, mypath);
		mSignature.setBackgroundColor(Color.WHITE);
		mContent.addView(mSignature, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mView = mContent;
		ImageView clear = (ImageView) dialog_date.findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSignature.clear();
			}
		});

		final CheckBox accept_picture = (CheckBox) dialog_camera
				.findViewById(R.id.checkBox1);
		final CheckBox accept_name = (CheckBox) dialog_agree
				.findViewById(R.id.checkBox1);

		CheckBox accept = (CheckBox) dialog_accept.findViewById(R.id.accept);
		CheckBox decline = (CheckBox) dialog_accept.findViewById(R.id.decline);
		accept.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_accept.dismiss();
			}
		});

		decline.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_accept.dismiss();
				DocumentViewer.this.finish();
			}
		});

		Button camera_button = (Button) dialog_camera.findViewById(R.id.camera);

		camera_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				dialog_take_pic.show();

			}
		});

		Button take_picture = (Button) dialog_take_pic
				.findViewById(R.id.take_pic);

		take_picture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, CAMERA);
			}
		});

		Button viture = (Button) dialog_agree.findViewById(R.id.viture);
		// if decline button is clicked, close the custom dialog
		viture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				if (accept_name.isChecked()) {
					dialog_camera.show();

				} else {
					showToast("Agree to Continue", Toast.LENGTH_LONG);

				}
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

				mView.setDrawingCacheEnabled(true);
				mSignature.save(mView);

				startActivity(new Intent(DocumentViewer.this,
						DocumentName.class));
			}
		});

		// Include dialog.xml file

		Button next = (Button) dialog_camera.findViewById(R.id.nextbutton);
		// if decline button is clicked, close the custom dialog
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				if (accept_picture.isChecked()) {
					dialog_agree.cancel();
					dialog_camera.cancel();
					dialog_date.cancel();
					startActivity(new Intent(DocumentViewer.this,
							TestCamera.class));
				} else {
					showToast("Agree to Continue", Toast.LENGTH_LONG);

				}
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
