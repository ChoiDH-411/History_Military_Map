package com.hmmap.dto;

public class Place {
	private int placeId;
	private String countryCode;
	private String category;
	private double latitude;
	private double longitude;
	private String imageFile;
	private String name;
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", countryCode=" + countryCode + ", category=" + category + ", latitude="
				+ latitude + ", longitude=" + longitude + ", imageFile=" + imageFile + ", name=" + name + "]";
	}
}
