package com.silversages.viditure.util;

import java.io.File;

import android.util.Log;

public class FileIO {

	private boolean makedirs(String tempDir) {
		File tempdir = new File(tempDir);
		if (!tempdir.exists())
			tempdir.mkdirs();

		if (tempdir.isDirectory()) {
			File[] files = tempdir.listFiles();
			for (File file : files) {
				if (!file.delete()) {
					System.out.println("Failed to delete " + file);
				}
			}
		}
		return (tempdir.isDirectory());
	}

	public boolean prepareDirectory(String tempDir) {
		try {
			if (makedirs(tempDir)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();

			Log.d("Vidture",
					"Could not initiate File System.. Is Sdcard mounted properly?");

			return false;
		}
	}

}
