package com.maf.demo.model;

import java.util.List;

public class CrazyHotel extends Hotel {

	private String hotelRate;
	private Double discount;
	private List<String> amenities;

	public String getHotelRate() {
		return hotelRate;
	}

	public void setHotelRate(String hotelRate) {
		this.hotelRate = hotelRate;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "CrazyHotel [hotelRate=" + hotelRate + ", discount=" + discount + ", amenities=" + amenities + "]";
	}
}
