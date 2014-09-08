package com.silversages.viditure.objects;

import android.util.Log;

import com.silversages.viditure.objects.fetchDocument.FetchDocObject;

public class ObjectHolder {

	private static FetchDocObject docObj;

	public static FetchDocObject getDocObj() {
		return docObj;
	}

	public static void setDocObj(FetchDocObject docObj) {
		ObjectHolder.docObj = docObj;
	}

	public static DocumentObject[] getDocumentobject() {

		DocumentObject[] docObj = new DocumentObject[getDocObj().getPages().length];

		for (int i = 0; i < docObj.length; i++) {

			Log.d("page1", getDocObj().getPages()[i].getPageImage_url());
			docObj[i] = new DocumentObject();
			docObj[i].setImgURL(getDocObj().getPages()[i].getPageImage_url());
		}
		return docObj;
	}
}
