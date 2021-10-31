package com.maf.demo.service.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.maf.demo.model.HotelProviderEnum;

public class AvailableHotelDTO {

	private String provider;
	private String hotelName;
	private Double far;
	private List<String> amenities;

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getFar() {
		return far;
	}

	public void setFar(Double far) {
		this.far = far;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public static List<AvailableHotelDTO> getAvailableHotelFromBest(List<BestHotelDTO> bestHotels) {
		List<AvailableHotelDTO> availableHotels = new ArrayList<>();
		if (!bestHotels.isEmpty()) {
			bestHotels.stream().forEach(hotel -> {
				AvailableHotelDTO availableHotel = new AvailableHotelDTO();
				availableHotel.setAmenities(Arrays.asList(hotel.getRoomAmenities().split(",", -1)));
				availableHotel.setFar(hotel.getHotelFare());
				availableHotel.setHotelName(hotel.getHotel());
				availableHotel.setProvider(HotelProviderEnum.BEST_HOTEL.getProviderName());
				availableHotels.add(availableHotel);
			});
		}
		return availableHotels;
	}

	public static List<AvailableHotelDTO> getAvailableHotelFromCrazy(List<CrazyHotelDTO> crazyHotels) {
		List<AvailableHotelDTO> availableHotels = new ArrayList<>();
		if (!crazyHotels.isEmpty()) {
			crazyHotels.stream().forEach(hotel -> {
				AvailableHotelDTO availableHotel = new AvailableHotelDTO();
				availableHotel.setAmenities(hotel.getAmenities());
				availableHotel.setFar(hotel.getPrice());
				availableHotel.setHotelName(hotel.getHotelName());
				availableHotel.setProvider(HotelProviderEnum.CRAZY_HOTEL.getProviderName());
				availableHotels.add(availableHotel);
			});
		}
		return availableHotels;

	}

}
