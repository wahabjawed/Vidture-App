package com.silversages.viditure.objects;

import com.silversages.viditure.objects.fetchDocument.FetchDocObject;

public class ObjectHolder {

	private static FetchDocObject docObj;

	public static FetchDocObject getDocObj() {
		return docObj;
	}

	public static void setDocObj(FetchDocObject docObj) {
		ObjectHolder.docObj = docObj;
	}
	
}
