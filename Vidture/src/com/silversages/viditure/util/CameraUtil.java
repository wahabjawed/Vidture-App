package com.silversages.viditure.util;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;

public class CameraUtil {

	public static int getFrontCameraId() {
		CameraInfo ci = new CameraInfo();
		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
			Camera.getCameraInfo(i, ci);
			if (ci.facing == CameraInfo.CAMERA_FACING_FRONT)
				return i;
		}
		return -1; // No front-facing camera found
	}

	public static void openFrontCamera() {
		int index = getFrontCameraId();
		if (index == -1)
			return;
		Camera c = Camera.open(index);
		
	}
}
