package com.silversages.viditure.util;

import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;

public class CameraUtil {

	public int getFrontCameraId() {
		CameraInfo ci = new CameraInfo();
		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
			Camera.getCameraInfo(i, ci);
			if (ci.facing == CameraInfo.CAMERA_FACING_FRONT)
				return i;
		}
		return -1; // No front-facing camera found
	}

	public void openFrontCamera() {
		int index = getFrontCameraId();
		if (index == -1)
			return;
		Camera.open(index);
		
	}
	
	
	 public static Size getOptimalPreviewSize(List <Camera.Size>sizes, int w, int h) {
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
	
}
