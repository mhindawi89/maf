package com.maf.demo.model;

import java.time.LocalDate;
import java.util.List;

public class Room {

	private Long roomId;
	private Hotel hotelId;
	private Integer numberOfAdults;
	private List<String> roomAmenities;
	private LocalDate avilabilityStartDate;
	private LocalDate avilabilityEndDate;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Hotel getHotelId() {
		return hotelId;
	}

	public void setHotelId(Hotel hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(Integer numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public List<String> getRoomAmenities() {
		return roomAmenities;
	}

	public void setRoomAmenities(List<String> roomAmenities) {
		this.roomAmenities = roomAmenities;
	}

	public LocalDate getAvilabilityStartDate() {
		return avilabilityStartDate;
	}

	public void setAvilabilityStartDate(LocalDate avilabilityStartDate) {
		this.avilabilityStartDate = avilabilityStartDate;
	}

	public LocalDate getAvilabilityEndDate() {
		return avilabilityEndDate;
	}

	public void setAvilabilityEndDate(LocalDate avilabilityEndDate) {
		this.avilabilityEndDate = avilabilityEndDate;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", hotelId=" + hotelId + ", numberOfAdults=" + numberOfAdults
				+ ", roomAmenities=" + roomAmenities + ", avilabilityStartDate=" + avilabilityStartDate
				+ ", avilabilityEndDate=" + avilabilityEndDate + "]";
	}
	
	
	
}
