package com.maf.demo.model;

import java.util.List;

public class Hotel {

	private Long hotelId;
	private String hotelName;
	private Double price;
	private String city;
	private Integer stars;
	private List<Room> hotelRooms;

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public List<Room> getHotelRooms() {
		return hotelRooms;
	}

	public void setHotelRooms(List<Room> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}

	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", price=" + price + ", city=" + city
				+ ", stars=" + stars + ", hotelRooms=" + hotelRooms + "]";
	} 
	
	

}
