package com.silversages.viditure.activity;

import java.io.File;

import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

public class DocumentViewer extends ViditureNetworkActivity {
	private int dummyFieldFilled = 0;
	private static final int CAMERA = 0;
	public ListView docuemntList;
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
	Button dialog_name_skip, dialog_name_ok;
	EditText dialog_name_field;

	// Data
	String name, d_date, initials, data;

	// progress dialg
	public ProgressDialog dialog;

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
		dialog = ProgressDialog.show(this, "", "Fetching Documents...", true);
		dialog.setCancelable(false);
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
		docuemntList.setAdapter(adapter);
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

		docuemntList = (ListView) findViewById(R.id.document);
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

		dialog_name_confirm.setContentView(R.layout.dialog_name_confirm);
		dialog_name_confirm.getWindow().setBackgroundDrawableResource(
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

		docuemntList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (dummyFieldFilled == 0) {
					showToast("Kindly Click Start Viditure", Toast.LENGTH_SHORT);
				} else {
					showToast("Kindly Click Start Viditure", Toast.LENGTH_SHORT);
				}
			}
		});

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

		dialog_name_skip = (Button) dialog_name_confirm
				.findViewById(R.id.decline);
		dialog_name_ok = (Button) dialog_name_confirm.findViewById(R.id.accept);
		dialog_name_field = (EditText) dialog_name_confirm
				.findViewById(R.id.fullname);

		dialog_name_skip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog_name_confirm.dismiss();
			}
		});
		dialog_name_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showToast(data, Toast.LENGTH_SHORT);
				DocumentAdapter.stateTextView.setText(data + "");
				dialog_name_confirm.dismiss();
			}
		});

		dummy_viditure = (Button) dialog_dummy.findViewById(R.id.viditure);
		dummy_fullname = (EditText) dialog_dummy.findViewById(R.id.full_name);
		dummy_initials = (EditText) dialog_dummy.findViewById(R.id.initials);
		dummy_date = (EditText) dialog_dummy.findViewById(R.id.date);
		dummy_viditure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dummy_fullname.getText().length() > 1
						&& dummy_initials.getText().length() > 1
						&& dummy_date.getText().length() > 1) {

					name = dummy_fullname.getText().toString();
					initials = dummy_initials.getText().toString();
					d_date = dummy_date.getText().toString();
					dialog_dummy.dismiss();
					dummyFieldFilled = 1;
				} else {
					showToast("Fill All the Data", Toast.LENGTH_LONG);
				}
			}
		});

		startVituring.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// dialog_name.show();
				if (dummyFieldFilled == 0) {
					dialog_dummy.show();
				} else {

					startActivity(new Intent(DocumentViewer.this,
							DocumentName.class));
				}
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

	public void openDialogPhoto() {

		dialog_camera.show();
	}

	public void openDialogVideo() {

		startActivity(new Intent(DocumentViewer.this, TestCamera.class));
	}

	public void openDialogSignature() {
		dialog_signature.show();
	}

	public void openDialogName(String type) {
		if (name != null && initials != null && d_date != null) {
			if (type.equals("fullname")) {
				dialog_name_field.setText(name);
				data = name.trim();
			} else if (type.equals("initials")) {
				dialog_name_field.setText(initials);
				data = initials.trim();

			} else if (type.equals("date")) {
				dialog_name_field.setText(d_date);
				data = d_date.trim();
			}

			dialog_name_confirm.show();
		} else {
			showToast("Start Viditure First", Toast.LENGTH_SHORT);
		}
	}

}
