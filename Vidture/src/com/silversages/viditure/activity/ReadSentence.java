package com.silversages.viditure.activity;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.silversages.viditure.R;
import com.silversages.viditure.abstracts.ViditureActivity;
import com.silversages.viditure.util.CameraUtil;

public class ReadSentence extends ViditureActivity implements
		SurfaceHolder.Callback {

	SurfaceView surafceView;
	// private MyCameraSurfaceView myCameraSurfaceView;
	SurfaceHolder surfaceHolder;
	File video;
	Camera myCamera;
	boolean previewing = false;
	int cameraIndex;
	Button vidtureIt;
	MediaRecorder mediaRecorder = new MediaRecorder();
	boolean recording = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_sentence);
		getSupportActionBar().setTitle("Record");
		setupView();

		// Get Camera for preview
		myCamera = getCameraInstance();
		if (myCamera == null) {
			Toast.makeText(ReadSentence.this, "Fail to get Camera",
					Toast.LENGTH_LONG).show();
		}

		surfaceHolder = surafceView.getHolder();
		surfaceHolder.addCallback(this);
		// deprecated setting, but required on Android versions prior to 3.0
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		// myCameraSurfaceView = new MyCameraSurfaceView(this, myCamera);
		// FrameLayout myCameraPreview = (FrameLayout)
		// findViewById(R.id.videoview);
		// myCameraPreview.addView(myCameraSurfaceView);
		setupListner();
	}

	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		vidtureIt = (Button) findViewById(R.id.vitureIt);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		surafceView = (SurfaceView) findViewById(R.id.surfaceView1);

	}

	private Camera getCameraInstance() {
		// TODO Auto-generated method stub
		Camera c = null;
		try {
			c = Camera.open(CameraUtil.getFrontCameraId());
			c.setDisplayOrientation(90);

		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
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

					// recording=false;

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

		mediaRecorder.setOutputFile("/sdcard/myvideo.mp4");
		mediaRecorder.setMaxDuration(60000); // Set max duration 60 sec.
		mediaRecorder.setMaxFileSize(5000000); // Set max file size 5M

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
		}

		// make any resize, rotate or reformatting changes here

		// start preview with new settings
		try {
			myCamera.setPreviewDisplay(surfaceHolder);
			myCamera.startPreview();

		} catch (Exception e) {
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
}
