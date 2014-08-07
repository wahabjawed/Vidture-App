package com.silversages.viditure.activity;

import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_camera);
		getSupportActionBar().setTitle("Record");
		setupView();
		setupListner();

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void setupView() {
		// TODO Auto-generated method stub

		cameraStatus = (TextView) findViewById(R.id.cameraStatus);
		vidtureIt = (Button) findViewById(R.id.vitureIt);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		surafceView = (SurfaceView) findViewById(R.id.surfaceView1);

		if (CameraUtil.getFrontCameraId() == -1) {

			cameraStatus.setText("Camera Not Working");
		} else {
			cameraStatus.setText("Camera Works");

			vidtureIt.setEnabled(true);

			cameraIndex = CameraUtil.getFrontCameraId();

			surfaceHolder = surafceView.getHolder();
			// surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			surfaceHolder.setSizeFromLayout();

			surfaceHolder.addCallback(this);
		}

	}

	@Override
	protected void setupListner() {
		// TODO Auto-generated method stub

		vidtureIt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(TestCamera.this, ReadSentence.class));
			}
		});

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera = Camera.open(cameraIndex);
		camera.setDisplayOrientation(90);
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException exception) {
			camera.release();
			camera = null;
		}
	}

	 static Size getOptimalPreviewSize(List <Camera.Size>sizes, int w, int h) {
         final double ASPECT_TOLERANCE = 0.1;
         final double MAX_DOWNSIZE = 1.5;

         double targetRatio = (double) w / h;
         if (sizes == null) return null;

         Size optimalSize = null;
         double minDiff = Double.MAX_VALUE;

         int targetHeight = h;

         // Try to find an size match aspect ratio and size
         for (Camera.Size size : sizes) {
           double ratio = (double) size.width / size.height;
           double downsize = (double) size.width / w;
           if (downsize > MAX_DOWNSIZE) {
             //if the preview is a lot larger than our display surface ignore it
             //reason - on some phones there is not enough heap available to show the larger preview sizes
             continue;
           }
           if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
           if (Math.abs(size.height - targetHeight) < minDiff) {
             optimalSize = size;
             minDiff = Math.abs(size.height - targetHeight);
           }
         }
         // Cannot find the one match the aspect ratio, ignore the requirement
         //keep the max_downsize requirement
         if (optimalSize == null) {
           minDiff = Double.MAX_VALUE;
           for (Size size : sizes) {
             double downsize = (double) size.width / w;
             if (downsize > MAX_DOWNSIZE) {
               continue;
             }
             if (Math.abs(size.height - targetHeight) < minDiff) {
               optimalSize = size;
               minDiff = Math.abs(size.height - targetHeight);
             }
           }
         }
         //everything else failed, just take the closest match
         if (optimalSize == null) {
           minDiff = Double.MAX_VALUE;
           for (Size size : sizes) {
             if (Math.abs(size.height - targetHeight) < minDiff) {
               optimalSize = size;
               minDiff = Math.abs(size.height - targetHeight);
             }
           }
         }
         return optimalSize;
       }
	
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if (previewing) {
			camera.stopPreview();
			previewing = false;
		}
		if (camera != null) {
			try {
				Camera.Parameters p = camera.getParameters();
				int SurfaceViewWidth = surafceView.getWidth();
				int SurfaceViewHeight = surafceView.getHeight();

				List<Size> sizes = p.getSupportedPreviewSizes();
				Size optimalSize = getOptimalPreviewSize(sizes,
						SurfaceViewWidth, SurfaceViewHeight);

				// set parameters
				p.setPreviewSize(optimalSize.width, optimalSize.height);
				//camera.setParameters(p);

				camera.setPreviewDisplay(surfaceHolder);
				
				camera.startPreview();
				previewing = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		camera.stopPreview();
		camera.release();
		camera = null;
		previewing = false;
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
