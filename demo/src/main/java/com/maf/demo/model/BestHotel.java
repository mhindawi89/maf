package com.maf.demo.model;

public class BestHotel extends Hotel {

	private Integer hotelRate;
	private String amenities;

	public Integer getHotelRate() {
		return hotelRate;
	}

	public void setHotelRate(Integer hotelRate) {
		this.hotelRate = hotelRate;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "BestHotel [hotelRate=" + hotelRate + ", amenities=" + amenities + "]";
	} 
	
	

}