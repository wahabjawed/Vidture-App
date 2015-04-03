package com.silversages.viditure.model;

public class PageData {

	boolean isFilled;
	String data;
	String type;
	int pageNo;
	int fieldno;

	public PageData(boolean isFilled, String data, String type, int pageNo,
			int fieldno) {
		super();
		this.isFilled = isFilled;
		this.data = data;
		this.type = type;
		this.pageNo = pageNo;
		this.fieldno = fieldno;
	}

	public PageData(boolean isFilled, String data, String type) {
		super();
		this.isFilled = isFilled;
		this.data = data;
		this.type = type;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getFieldno() {
		return fieldno;
	}

	public void setFieldno(int fieldno) {
		this.fieldno = fieldno;
	}

}
