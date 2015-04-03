package com.silversages.viditure.model.fetchDocument;

public class Pages {

	int pageNumber;
	String pageImage_url;
	String thumbImage_url;
	boolean needsInput;
	Fields[] fields;
	PagePosition pagePosition;

	public Fields[] getFields() {
		return fields;
	}

	public void setFields(Fields[] fields) {
		this.fields = fields;
	}

	public PagePosition getPagePosition() {
		return pagePosition;
	}

	public void setPagePosition(PagePosition pagePosition) {
		this.pagePosition = pagePosition;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageImage_url() {
		return pageImage_url.replace("dev.viditure.com", "54.183.77.229:8080");
	}

	public void setPageImage_url(String pageImage_url) {
		this.pageImage_url = pageImage_url;
	}

	public String getThumbImage_url() {
		return thumbImage_url;
	}

	public void setThumbImage_url(String thumbImage_url) {
		this.thumbImage_url = thumbImage_url;
	}

	public boolean isNeedsInput() {
		return needsInput;
	}

	public void setNeedsInput(boolean needsInput) {
		this.needsInput = needsInput;
	}

	public class Fields {

		String fieldPosition;
		boolean required;
		Kind kind;
		ScreenPos screenPos;
		FieldInput fieldInput;

		public String getFieldPosition() {
			return fieldPosition;
		}

		public void setFieldPosition(String fieldPosition) {
			this.fieldPosition = fieldPosition;
		}

		public boolean isRequired() {
			return required;
		}

		public void setRequired(boolean required) {
			this.required = required;
		}

		public Kind getKind() {
			return kind;
		}

		public void setKind(Kind kind) {
			this.kind = kind;
		}

		public ScreenPos getScreenPos() {
			return screenPos;
		}

		public void setScreenPos(ScreenPos screenPos) {
			this.screenPos = screenPos;
		}

		public FieldInput getFieldInput() {
			return fieldInput;
		}

		public void setFieldInput(FieldInput fieldInput) {
			this.fieldInput = fieldInput;
		}

		public class Kind {
			String type;
			String name;
			String fieldImage_url;

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getFieldImage_url() {
				return fieldImage_url;
			}

			public void setFieldImage_url(String fieldImage_url) {
				this.fieldImage_url = fieldImage_url;
			}

		}

		public class ScreenPos {

			int top;
			int left;
			int width;
			int height;

			public int getTop() {
				return top;
			}

			public void setTop(int top) {
				this.top = top;
			}

			public int getLeft() {
				return left;
			}

			public void setLeft(int left) {
				this.left = left;
			}

			public int getWidth() {
				return width;
			}

			public void setWidth(int width) {
				this.width = width;
			}

			public int getHeight() {
				return height;
			}

			public void setHeight(int height) {
				this.height = height;
			}

		}

		public class FieldInput {

			String fieldPosition;
			String data;
			String state;
			String screenPos;

			public String getFieldPosition() {
				return fieldPosition;
			}

			public void setFieldPosition(String fieldPosition) {
				this.fieldPosition = fieldPosition;
			}

			public String getData() {
				return data;
			}

			public void setData(String data) {
				this.data = data;
			}

			public String getState() {
				return state;
			}

			public void setState(String state) {
				this.state = state;
			}

			public String getScreenPos() {
				return screenPos;
			}

			public void setScreenPos(String screenPos) {
				this.screenPos = screenPos;
			}

		}

	}

	public class PagePosition {
		int top;
		int left;
		int width;
		int height;

		public int getTop() {
			return top;
		}

		public void setTop(int top) {
			this.top = top;
		}

		public int getLeft() {
			return left;
		}

		public void setLeft(int left) {
			this.left = left;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}
}
