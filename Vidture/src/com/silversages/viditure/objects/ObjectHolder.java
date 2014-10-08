package com.silversages.viditure.objects;

import android.util.Log;

import com.silversages.viditure.objects.fetchDocument.FetchDocObject;

public class ObjectHolder {

	private static FetchDocObject docObj;
	private static SignatureDObject signatureDObj;
	private static AcceptDObject acceptDObj;
	private static CameraDObject cameraDObj;
	private static AuthXObject authXObject;

		
	public static AuthXObject getAuthXObject() {
		return authXObject;
	}

	public static void setAuthXObject(AuthXObject authXObject) {
		ObjectHolder.authXObject = authXObject;
	}

	public static SignatureDObject getSignatureDObj() {
		return signatureDObj;
	}

	public static void setSignatureDObj(SignatureDObject signatureDObj) {
		ObjectHolder.signatureDObj = signatureDObj;
	}

	public static AcceptDObject getAcceptDObj() {
		return acceptDObj;
	}

	public static void setAcceptDObj(AcceptDObject acceptDObj) {
		ObjectHolder.acceptDObj = acceptDObj;
	}

	public static CameraDObject getCameraDObj() {
		return cameraDObj;
	}

	public static void setCameraDObj(CameraDObject cameraDObj) {
		ObjectHolder.cameraDObj = cameraDObj;
	}

	public static FetchDocObject getDocObj() {
		return docObj;
	}

	public static void setDocObj(FetchDocObject docObj) {
		ObjectHolder.docObj = docObj;
	}

	public static DocumentObject[] getDocumentImageObject() {

		DocumentObject[] docObj = new DocumentObject[getDocObj().getPages().length];

		for (int i = 0; i < docObj.length; i++) {

			Log.d("page1", getDocObj().getPages()[i].getPageImage_url());
			docObj[i] = new DocumentObject();
			docObj[i].setImgURL(getDocObj().getPages()[i].getPageImage_url());
		}
		return docObj;
	}
}
