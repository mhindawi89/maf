package com.maf.demo.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.maf.demo.model.BestHotel;
import com.maf.demo.model.Room;

public class BestHotelDTO {

	private String hotel;
	private Integer hotelRate;
	private Double hotelFare;
	private String roomAmenities;

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public Integer getHotelRate() {
		return hotelRate;
	}

	public void setHotelRate(Integer hotelRate) {
		this.hotelRate = hotelRate;
	}

	public Double getHotelFare() {
		return hotelFare;
	}

	public void setHotelFare(Double hotelFare) {
		this.hotelFare = hotelFare;
	}

	public String getRoomAmenities() {
		return roomAmenities;
	}

	public void setRoomAmenities(String roomAmenities) {
		this.roomAmenities = roomAmenities;
	}

	public static List<BestHotelDTO> getBestotelDTO(List<Room> rooms) {
		List<BestHotelDTO> bestHotelList = new ArrayList<>();
		if (!rooms.isEmpty()) {
			rooms.stream().forEach(room -> {
				BestHotelDTO bestHotelDTO = new BestHotelDTO();
				BestHotel hotel = (BestHotel) room.getHotelId();
				bestHotelDTO.setHotel(hotel.getHotelName());
				bestHotelDTO.setHotelFare(hotel.getPrice());
				bestHotelDTO.setHotelRate(hotel.getHotelRate());
				bestHotelDTO.setRoomAmenities(String.join(",", room.getRoomAmenities()));
				bestHotelList.add(bestHotelDTO);
			});

		}
		return bestHotelList;

	}

}
