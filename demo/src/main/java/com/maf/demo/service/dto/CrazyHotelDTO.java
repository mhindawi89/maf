package com.maf.demo.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.maf.demo.model.CrazyHotel;
import com.maf.demo.model.Room;

public class CrazyHotelDTO {

	private String hotelName;
	private String rate;
	private Double price;
	private Double discount;
	private List<String> amenities;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public static List<CrazyHotelDTO> getCrazyHotelDTO(List<Room> rooms) {
		List<CrazyHotelDTO> crazyHotelList = new ArrayList<>();
		if (!rooms.isEmpty()) {
			rooms.stream().forEach(room -> {
				CrazyHotelDTO crazyHotelDTO = new CrazyHotelDTO();
				CrazyHotel hotel = (CrazyHotel) room.getHotelId();
				crazyHotelDTO.setHotelName(hotel.getHotelName());
				crazyHotelDTO.setAmenities(hotel.getAmenities());
				crazyHotelDTO.setDiscount(hotel.getDiscount());
				crazyHotelDTO.setPrice(hotel.getPrice());
				crazyHotelDTO.setRate(hotel.getHotelRate());
				crazyHotelList.add(crazyHotelDTO);

			});

		}
		return crazyHotelList;
	}

}
