package com.silversages.viditure.objects.fetchDocument;

public class FetchDocObject {

	String timeUpdated;
	String filename;
	String recipients_url;
	String name;
	String description;
	String self_url;
	String fields_url;
	String ownerCompany;
	String owner;
	String id;
	String shortId;
	String state;
	String fromtemplate;
	String templateId;
	String[] allRecipients;
	meProfile me;
	Pages[] pages;

	public Pages[] getPages() {
		return pages;
	}

	public void setPages(Pages[] pages) {
		this.pages = pages;
	}

	public String getFields_url() {
		return fields_url;
	}

	public void setFields_url(String fields_url) {
		this.fields_url = fields_url;
	}

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortId() {
		return shortId;
	}

	public void setShortId(String shortId) {
		this.shortId = shortId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFromtemplate() {
		return fromtemplate;
	}

	public void setFromtemplate(String fromtemplate) {
		this.fromtemplate = fromtemplate;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String[] getAllRecipients() {
		return allRecipients;
	}

	public void setAllRecipients(String[] allRecipients) {
		this.allRecipients = allRecipients;
	}

	public meProfile getMe() {
		return me;
	}

	public void setMe(meProfile me) {
		this.me = me;
	}

	public String getRecipients_url() {
		return recipients_url;
	}

	public void setRecipients_url(String recipients_url) {
		this.recipients_url = recipients_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSelf_url() {
		return self_url;
	}

	public void setSelf_url(String self_url) {
		this.self_url = self_url;
	}

	public String getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(String timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public class meProfile {

		String fullName;
		String firstName;
		String lastName;
		String email;
		String id;
		String user;
		String role;
		boolean signRequired;
		String geo;
		String readOutMessage;
		int videoDuration;
		String video_url;
		String recipients_url;
		String signerview_url;
		String signerVideo_url;
		String signerInput_url;
		boolean signer;
		String token;
		FieldInputs[] fieldInputs;
		
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

	
		public FieldInputs[] getFieldInputs() {
			return fieldInputs;
		}

		public void setFieldInputs(FieldInputs[] fieldInputs) {
			this.fieldInputs = fieldInputs;
		}

		public String getGeo() {
			return geo;
		}

		public void setGeo(String geo) {
			this.geo = geo;
		}

		public String getReadOutMessage() {
			return readOutMessage;
		}

		public void setReadOutMessage(String readOutMessage) {
			this.readOutMessage = readOutMessage;
		}

		public int getVideoDuration() {
			return videoDuration;
		}

		public void setVideoDuration(int videoDuration) {
			this.videoDuration = videoDuration;
		}

		public String getVideo_url() {
			return video_url;
		}

		public void setVideo_url(String video_url) {
			this.video_url = video_url;
		}

		public String getRecipients_url() {
			return recipients_url;
		}

		public void setRecipients_url(String recipients_url) {
			this.recipients_url = recipients_url;
		}

		public String getSignerview_url() {
			return signerview_url;
		}

		public void setSignerview_url(String signerview_url) {
			this.signerview_url = signerview_url;
		}

		public String getSignerVideo_url() {
			return signerVideo_url;
		}

		public void setSignerVideo_url(String signerVideo_url) {
			this.signerVideo_url = signerVideo_url;
		}

		public String getSignerInput_url() {
			return signerInput_url;
		}

		public void setSignerInput_url(String signerInput_url) {
			this.signerInput_url = signerInput_url;
		}

		public boolean isSigner() {
			return signer;
		}

		public void setSigner(boolean signer) {
			this.signer = signer;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public boolean isSignRequired() {
			return signRequired;
		}

		public void setSignRequired(boolean signRequired) {
			this.signRequired = signRequired;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public class FieldInputs {

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
}