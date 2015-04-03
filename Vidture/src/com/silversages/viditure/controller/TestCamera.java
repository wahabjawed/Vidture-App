package com.silversages.viditure.controller;

import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureActivity;
import com.silversages.viditure.util.CameraUtil;

public class TestCamera extends ViditureActivity implements
		SurfaceHolder.Callback {

	TextView cameraStatus;
	Button vidtureIt;
	SurfaceView surafceView;
	SurfaceHolder surfaceHolder;
	Camera camera;
	boolean previewing = false;
	int cameraIndex;
	Camera c = null;
	Camera.Parameters params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_camera);
		getSupportActionBar().setTitle("Record");
		setupView();
		setupListner();

	}

	@Override
	public void setupView() {
		// TODO Auto-generated method stub

		cameraStatus = (TextView) findViewById(R.id.cameraStatus);
		vidtureIt = (Button) findViewById(R.id.vitureIt);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		surafceView = (SurfaceView) findViewById(R.id.surfaceView1);

	}

	@Override
	public void setupListner() {
		// TODO Auto-generated method stub

		vidtureIt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TestCamera.this.finish();
				startActivity(new Intent(TestCamera.this, ReadSentence.class));
			}
		});

	}

	private Camera getCameraInstance() {
		// TODO Auto-generated method stub

		try {
			int cameraID = Zainu.getCameraUtil().getFrontCameraId();
			if (cameraID == -1) {
				cameraStatus.setText(getString(R.string.camera_notworking));
			} else {
				cameraStatus.setText(getString(R.string.camera_working));

				vidtureIt.setEnabled(true);

				c = Camera.open(cameraID);
				c.setDisplayOrientation(90);
				Camera.CameraInfo info = new Camera.CameraInfo();
				Camera.getCameraInfo(Zainu.getCameraUtil().getFrontCameraId(),
						info);

				params = c.getParameters();
				params.setRotation(90);

			}
		} catch (Exception e) {

			// Camera is not available (in use or does not exist)
			Log.e("Viditure", e.getMessage());
		}

		return c; // returns null if camera is unavailable
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// showToast("On Pause", Toast.LENGTH_SHORT);
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
			previewing = false;
		}
		super.onPause();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		// showToast("On Resume", Toast.LENGTH_SHORT);
		camera = getCameraInstance();
		if (camera == null) {
			Toast.makeText(TestCamera.this, "Fail to get Camera",
					Toast.LENGTH_LONG).show();
		}

		surfaceHolder = surafceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder.setSizeFromLayout();
		super.onResume();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// showToast("Surface Created", Toast.LENGTH_SHORT);

		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException exception) {
			Log.d("Viditure", exception.getMessage());
			camera.release();
			camera = null;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

		if (previewing) {
			camera.stopPreview();
			previewing = false;
		}

		try {

			camera.setPreviewDisplay(surfaceHolder);
			camera.startPreview();
			previewing = true;

			Log.e("surfaceChanged", "surfaceChanged => w=" + width + ", h="
					+ height);

			List<Size> sizes = c.getParameters().getSupportedPreviewSizes();
			Camera.Size optimalSize = CameraUtil.getOptimalPreviewSize(sizes,
					width, height);

			Log.e("optimalSizeHeight", optimalSize.height + "");
			Log.e("optimalSizeWidth", optimalSize.width + "");
			// set parameters
			params.setPreviewSize(optimalSize.width, optimalSize.height);

			c.setParameters(params);

			double ratio = (double) optimalSize.width
					/ (double) optimalSize.height;
			Log.e("Ratio", ratio + "");
			double Dheight = height;

			int newWidth = (int) (Dheight / ratio);
			Log.e("New Width", newWidth + "");

			surafceView.getLayoutParams().width = newWidth;

		} catch (IOException e) {
			Log.e("Viditure", e.getMessage());
		}

	}

	Camera.ShutterCallback myShutterCallback = new Camera.ShutterCallback() {
		@Override
		public void onShutter() {

		}
	};

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;

			previewing = false;
		}
	}

	Camera.PictureCallback myPictureCallback_RAW = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
		}
	};
	Camera.PictureCallback myPictureCallback_JPG = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {

		}
	};

}
