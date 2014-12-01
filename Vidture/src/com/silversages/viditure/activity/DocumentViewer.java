package com.silversages.viditure.activity;

import java.io.File;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class DocumentViewer extends ViditureNetworkActivity implements
		OnClickListener {

	private static final int CAMERA = 0;
	ListView docuemnt;
	Button startVituring;

	Dialog dialog_accept;

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

	// dialog picture
	Dialog dialog_camera;
	CheckBox accept_picture;
	EditText fullname_camera;

	// dialog set pic
	Dialog dialog_set_pic;

	// dialog take pic
	Dialog dialog_take_pic;

	// dialog name
	Dialog dialog_name;
	EditText fullname;
	CheckBox accept_name;

	// dialog Signature
	Dialog dialog_signature;
	EditText date;

	// dialog dummy
	Dialog dialog_dummy;
	EditText dummy_fullname;
	EditText dummy_date;
	EditText dummy_initials;
	Button dummy_viditure;

	// dialog name confirm
	Dialog dialog_name_confirm;

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
			if (Zainu.getNetworkManager().IsConnected())
				new FetchDocRequest(uri.getEncodedPath()).PerformTask(this);
			else
				showToast("No Connection", Toast.LENGTH_LONG);
			// new FetchDocRequest("/vts/signrequest/543a7a83e4b0ba42a7391d11")
			// .PerformTask(this);

		}

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
		dialog_name = new Dialog(DocumentViewer.this);
		dialog_signature = new Dialog(DocumentViewer.this);
		dialog_accept = new Dialog(DocumentViewer.this);
		dialog_take_pic = new Dialog(DocumentViewer.this);
		dialog_set_pic = new Dialog(DocumentViewer.this);
		dialog_dummy = new Dialog(DocumentViewer.this);
		dialog_name_confirm = new Dialog(DocumentViewer.this);
		dialog_signature.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_name.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_camera.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_accept.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_take_pic.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_set_pic.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_dummy.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog_name_confirm.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog_dummy.setContentView(R.layout.dialog_name_confirm);
		dialog_dummy.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_dummy.setContentView(R.layout.dialog_dummy);
		dialog_dummy.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_camera.setContentView(R.layout.dialog_picture);
		dialog_camera.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);
		// Include dialog.xml file

		dialog_signature.setContentView(R.layout.dialog_signature);
		dialog_signature.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_name.setContentView(R.layout.dialog_name);
		dialog_name.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_accept.setContentView(R.layout.dialog_confirm);
		dialog_accept.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);
		dialog_accept.setCanceledOnTouchOutside(false);

		// important
		// dialog_accept.show();

		dialog_take_pic.setContentView(R.layout.dialog_take_picture);
		dialog_take_pic.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);

		dialog_set_pic.setContentView(R.layout.dialog_pick_picture);
		dialog_set_pic.getWindow().setBackgroundDrawableResource(
				R.drawable.dialogbox);
		setupSignature();

		accept_picture = (CheckBox) dialog_camera.findViewById(R.id.checkBox1);
		accept_name = (CheckBox) dialog_name.findViewById(R.id.checkBox1);

		fullname = (EditText) dialog_name.findViewById(R.id.fullname);

		date = (EditText) dialog_signature.findViewById(R.id.dated);
		fullname_camera = (EditText) dialog_camera.findViewById(R.id.name);
	}

	@Override
	public void setupListner() {
		// TODO Auto-generated method stub

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

		ImageView clear = (ImageView) dialog_signature.findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSignature.clear();
			}
		});

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

		Button viture = (Button) dialog_name.findViewById(R.id.viture);
		// if decline button is clicked, close the custom dialog
		viture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				if (!accept_name.isChecked()) {
					showToast("Agree to Continue", Toast.LENGTH_LONG);
				} else if (fullname.getText().length() <= 0) {
					showToast("Type in Fullname to Continue", Toast.LENGTH_LONG);
				} else {
					dialog_camera.show();

				}
			}
		});

		TextView esignature = (TextView) dialog_name
				.findViewById(R.id.esignature_label);

		// set values for custom dialog components - text, image and
		// button
		esignature.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_signature.show();
			}
		});

		Button use_signature = (Button) dialog_signature
				.findViewById(R.id.use_signature);
		// if decline button is clicked, close the custom dialog
		use_signature.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog

				if (date.getText().length() <= 0) {
					showToast("Select Date to Continue", Toast.LENGTH_LONG);
				} else {
					mView.setDrawingCacheEnabled(true);
					mSignature.save(mView);

					startActivity(new Intent(DocumentViewer.this,
							DocumentName.class));
				}
			}
		});

		// Include dialog.xml file

		Button next = (Button) dialog_camera.findViewById(R.id.nextbutton);
		// if decline button is clicked, close the custom dialog
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Close dialog
				if (!accept_picture.isChecked()) {
					showToast("Agree to Continue", Toast.LENGTH_LONG);
				} else if (fullname_camera.getText().length() <= 0) {

					showToast("Type in Fullname to Continue", Toast.LENGTH_LONG);
				} else {
					dialog_name.cancel();
					dialog_camera.cancel();
					dialog_signature.cancel();
					startActivity(new Intent(DocumentViewer.this,
							TestCamera.class));

				}
			}
		});

		startVituring.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// dialog_name.show();
				dialog_dummy.show();
			}
		});
	}

	private void setupSignature() {
		tempDir = Environment.getExternalStorageDirectory() + "/"
				+ "GetSignature" + "/";
		ContextWrapper cw = new ContextWrapper(getApplicationContext());
		File directory = cw.getDir("GetSignature", Context.MODE_PRIVATE);

		Zainu.getFileIO().prepareDirectory(tempDir);
		uniqueId = Zainu.getDateTime().getTodaysDate() + "_"
				+ Zainu.getDateTime().getCurrentTime() + "_" + Math.random();
		current = uniqueId + ".png";
		mypath = new File(directory, current);

		mContent = (LinearLayout) dialog_signature.findViewById(R.id.signature);
		mSignature = new Signature(this, null);
		mSignature.setField(mContent, mypath);
		mSignature.setBackgroundColor(Color.WHITE);
		mContent.addView(mSignature, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mView = mContent;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Log.e("ID", "" + v.getId());
	}

	public void openDialog() {

		dialog_name_confirm.show();

	}

}
