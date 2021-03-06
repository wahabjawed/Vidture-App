package com.silversages.viditure.model;

import android.util.Log;

import com.silversages.viditure.model.dialog.*;
import com.silversages.viditure.model.fetchDocument.FetchDocObject;
import com.silversages.viditure.model.sendSignerData.SignerObject;

public class ObjectHolder {

	private static FetchDocObject docObj;
	private static SignatureDObject signatureDObj;
	private static AcceptDObject acceptDObj = new AcceptDObject();
	private static CameraDObject cameraDObj = new CameraDObject();
	private static AuthXObject authXObject;
	private static SignerObject signerObject = new SignerObject();

	public static SignerObject getSignerObject() {
		return signerObject;
	}

	public static void setSignerObject(SignerObject signerObject) {
		ObjectHolder.signerObject = signerObject;
	}

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

	
}
