package com.silversages.viditure.objects.sendSignerData;

public class SignerObject {

	FieldInputs[] fieldInputs;
	Geo geo = new Geo();

	public FieldInputs[] getFieldInputs() {
		return fieldInputs;
	}

	public void setFieldInputs(FieldInputs[] fieldInputs) {
		this.fieldInputs = fieldInputs;
	}

	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public class Geo {
		String remoteIP;
		String remoteHost;
		String city;
		String region;
		String country;
		Boolean isFinal;
		Loc loc = new Loc();

		public String getRemoteIP() {
			return remoteIP;
		}

		public void setRemoteIP(String remoteIP) {
			this.remoteIP = remoteIP;
		}

		public String getRemoteHost() {
			return remoteHost;
		}

		public void setRemoteHost(String remoteHost) {
			this.remoteHost = remoteHost;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public Boolean getIsFinal() {
			return isFinal;
		}

		public void setIsFinal(Boolean isFinal) {
			this.isFinal = isFinal;
		}

		public Loc getLoc() {
			return loc;
		}

		public void setLoc(Loc loc) {
			this.loc = loc;
		}

		public class Loc {
			double x;

			public double getX() {
				return x;
			}

			public void setX(double x) {
				this.x = x;
			}

			public double getY() {
				return y;
			}

			public void setY(double y) {
				this.y = y;
			}

			double y;
		}

	}

}
