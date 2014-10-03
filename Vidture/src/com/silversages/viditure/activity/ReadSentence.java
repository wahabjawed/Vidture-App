package com.silversages.viditure.activity;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
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
import com.silversages.viditure.objects.ObjectHolder;

@SuppressLint("SdCardPath")
public class ReadSentence extends ViditureActivity implements
		SurfaceHolder.Callback {

	SurfaceView surafceView;
	SurfaceHolder surfaceHolder;
	File video;
	Camera myCamera;
	boolean previewing = false;
	int cameraIndex;
	Button vidtureIt;
	MediaRecorder mediaRecorder = new MediaRecorder();
	boolean recording = false;
	TextView message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_sentence);
		getSupportActionBar().setTitle("Record");
		setupView();

		setupListner();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		// Get Camera for preview
		myCamera = getCameraInstance();
		if (myCamera == null) {
			Toast.makeText(ReadSentence.this, "Fail to get Camera",
					Toast.LENGTH_LONG).show();
		}

		surfaceHolder = surafceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		super.onResume();
	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		vidtureIt = (Button) findViewById(R.id.vitureIt);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		surafceView = (SurfaceView) findViewById(R.id.surfaceView1);
		message = (TextView) findViewById(R.id.message);
		message.setText((ObjectHolder.getDocObj().getMe()).getReadOutMessage());

	}

	private Camera getCameraInstance() {
		// TODO Auto-generated method stub
		Camera c = null;
		try {
			c = Camera.open(Zainu.getCameraUtil().getFrontCameraId());
			c.setDisplayOrientation(90);
			Camera.CameraInfo info = new Camera.CameraInfo();
			Camera.getCameraInfo(Zainu.getCameraUtil().getFrontCameraId(), info);

			Camera.Parameters params = c.getParameters();
			params.setRotation(90);
			c.setParameters(params);

		} catch (Exception e) {

			// Camera is not available (in use or does not exist)
			Log.d("Viditure", e.getMessage());
		}
		return c; // returns null if camera is unavailable
	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

		vidtureIt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (recording) {
					// stop recording and release camera
					mediaRecorder.stop(); // stop the recording
					releaseMediaRecorder(); // release the MediaRecorder object
					vidtureIt.setText("Vidture It");
					recording = false;

					// Exit after saved
					startActivity(new Intent(ReadSentence.this, ViewVideo.class));
					// finish();
				} else {

					// Release Camera before MediaRecorder start
					releaseCamera();

					if (!prepareMediaRecorder()) {
						Toast.makeText(ReadSentence.this,
								"Fail in prepareMediaRecorder()!\n - Ended -",
								Toast.LENGTH_LONG).show();
						// finish();
					}

					mediaRecorder.start();
					recording = true;
					vidtureIt.setText("STOP");

				}
			}
		});
	}

	private boolean prepareMediaRecorder() {
		myCamera = getCameraInstance();
		mediaRecorder = new MediaRecorder();

		myCamera.unlock();
		mediaRecorder.setCamera(myCamera);

		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

		mediaRecorder.setProfile(CamcorderProfile
				.get(CamcorderProfile.QUALITY_HIGH));

		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state))
			mediaRecorder.setOutputFile(Environment
					.getExternalStorageDirectory().getAbsolutePath()
					+ "/myvideo.mp4");

		else
			mediaRecorder.setOutputFile(getFilesDir().getAbsolutePath()
					+ "/video.mp4");

		mediaRecorder.setMaxDuration(ObjectHolder.getDocObj().getMe()
				.getVideoDuration() * 1000); // Set max duration 60 sec.
		// mediaRecorder.setMaxFileSize(5000000); // Set max file size 5M
		mediaRecorder.setOrientationHint(270);
		mediaRecorder.setPreviewDisplay(surafceView.getHolder().getSurface());

		try {
			mediaRecorder.prepare();
		} catch (IllegalStateException e) {
			releaseMediaRecorder();
			return false;
		} catch (IOException e) {
			releaseMediaRecorder();
			return false;
		}
		return true;

	}

	@Override
	protected void onPause() {
		super.onPause();
		releaseMediaRecorder(); // if you are using MediaRecorder, release it
								// first
		releaseCamera(); // release the camera immediately on pause event
	}

	private void releaseMediaRecorder() {
		if (mediaRecorder != null) {
			mediaRecorder.reset(); // clear recorder configuration
			mediaRecorder.release(); // release the recorder object
			mediaRecorder = null;
			myCamera.lock(); // lock camera for later use
		}
	}

	private void releaseCamera() {
		if (myCamera != null) {
			myCamera.release(); // release the camera for other applications
			myCamera = null;
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		// The Surface has been created, now tell the camera where to draw
		// the preview.
		try {
			myCamera.setPreviewDisplay(holder);
			myCamera.startPreview();
		} catch (IOException e) {
			Log.d("Vidture", e.getMessage());
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

		if (surfaceHolder.getSurface() == null) {
			// preview surface does not exist
			return;
		}

		// stop preview before making changes
		try {
			myCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
			Log.d("Vidture", e.getMessage());
		}

		// make any resize, rotate or reformatting changes here

		// start preview with new settings
		try {
			myCamera.setPreviewDisplay(surfaceHolder);
			myCamera.startPreview();

		} catch (Exception e) {
			Log.d("Vidture", e.getMessage());
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		releaseMediaRecorder(); // if you are using MediaRecorder, release it
		// first
		releaseCamera(); // release the camera immediately on pause event
	}
}
