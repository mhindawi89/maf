package com.maf.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.maf.demo.model.BestHotel;
import com.maf.demo.model.CrazyHotel;
import com.maf.demo.model.Hotel;
import com.maf.demo.model.Room;
import com.maf.demo.service.dto.BestHotelDTO;
import com.maf.demo.service.dto.CrazyHotelDTO;

@Service("ProvidersServcie")
public class ProvidersServcieImpl implements ProvidersServcie {

	private static final Logger logger = LoggerFactory.getLogger(ProvidersServcieImpl.class);

	private final List<String> hotelAmenities = Arrays.asList("Dining", "Vending", "Exercise", "Recreation",
			"Swimming pools", "Parking");

	/**
	 * service that get available hotel from best hotel provider
	 */
	@Override
	public List<BestHotelDTO> getBestHotel(String cityCode, LocalDate fromDate, LocalDate toDate,
			Integer numberOfAdults) throws Exception {
		if (fromDate.isAfter(toDate)) {
			throw new Exception("could not send from date bigger than to date ");
		}
		List<BestHotel> bestHotels = createBestHotels();
		logger.info("bestHotels : " +  bestHotels.toString());
		List<Room> hotelRooms = bestHotels.stream().filter(hotel -> hotel.getCity().equals(cityCode))
				.flatMap(hotel -> hotel.getHotelRooms().stream()).collect(Collectors.toList());
		hotelRooms = hotelRooms.stream().filter(room -> room.getNumberOfAdults().equals(numberOfAdults))
				.filter(room -> (room.getAvilabilityStartDate().isAfter(fromDate)
						|| room.getAvilabilityStartDate().equals(fromDate))
						&& (room.getAvilabilityEndDate().isBefore(toDate)
								|| room.getAvilabilityEndDate().equals(toDate)))
				.collect(Collectors.toList());
		return BestHotelDTO.getBestotelDTO(hotelRooms).stream().filter(distinctByKey(hotel -> hotel.getHotel()))
				.collect(Collectors.toList());
	}

	/**
	 * service that get available hotel from crazy hotel provider
	 */
	@Override
	public List<CrazyHotelDTO> getCrazyHotel(String cityCode, LocalDateTime from, LocalDateTime to, Integer adultsCount)
			throws Exception {
		if (from.isAfter(to)) {
			throw new Exception("could not send from date bigger than to date ");
		}
		List<CrazyHotel> crazyHotel = createCrazyHotels();
		
		logger.info("crazyHotel : " + crazyHotel.toString());
		List<Room> hotelRooms = crazyHotel.stream().filter(hotel -> hotel.getCity().equals(cityCode))
				.flatMap(hotel -> hotel.getHotelRooms().stream()).collect(Collectors.toList());
		hotelRooms = hotelRooms.stream().filter(room -> room.getNumberOfAdults().equals(adultsCount))
				.filter(room -> (room.getAvilabilityStartDate().atStartOfDay().isAfter(from)
						|| room.getAvilabilityStartDate().atStartOfDay().equals(from))
						&& (room.getAvilabilityEndDate().atStartOfDay().isBefore(to)
								|| room.getAvilabilityEndDate().atStartOfDay().equals(to)))
				.collect(Collectors.toList());
		return CrazyHotelDTO.getCrazyHotelDTO(hotelRooms).stream().filter(distinctByKey(hotel -> hotel.getHotelName()))
				.collect(Collectors.toList());
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	private List<BestHotel> createBestHotels() {
		List<BestHotel> bestHotels = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			BestHotel bestHotel = new BestHotel();
			bestHotel.setHotelId(Long.valueOf(i));
			bestHotel.setHotelName("Best Hotel Name " + i);
			bestHotel.setCity(getRandomCity());
			bestHotel.setAmenities(String.join(",", hotelAmenities));
			bestHotel.setHotelRate(4);
			Double price = getRandomPrice();
			bestHotel.setPrice(price);
			bestHotel.setStars(4);
			bestHotel.setHotelRooms(createHotelRooms(bestHotel, price, Double.valueOf(0)));
			bestHotels.add(bestHotel);
		}

		return bestHotels;

	}

	private List<CrazyHotel> createCrazyHotels() {
		List<CrazyHotel> crazyHotels = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			CrazyHotel crazyHotel = new CrazyHotel();
			crazyHotel.setHotelId(Long.valueOf(i));
			crazyHotel.setHotelName("Crazy Hotel Name " + i);
			crazyHotel.setCity(getRandomCity());
			crazyHotel.setAmenities(hotelAmenities);
			crazyHotel.setHotelRate("****");
			Double price = getRandomPrice();
			crazyHotel.setPrice(price);
			crazyHotel.setStars(4);
			Double discount = getRandomDiscount();
			crazyHotel.setDiscount(discount);
			crazyHotel.setHotelRooms(createHotelRooms(crazyHotel, price, discount));

			crazyHotels.add(crazyHotel);

		}
		return crazyHotels;
	}

	private List<Room> createHotelRooms(Hotel hotel, Double amount, Double discount) {
		List<Room> rooms = new ArrayList<>();
		for (int i = 1; i < 5; i++) {
			Room room = new Room();
			room.setRoomId(Long.valueOf(i));
			room.setHotelId(hotel);
			room.setNumberOfAdults(i);
			room.setRoomAmenities(Arrays.asList("Kitchen facilities", "Television", "Computer and Internet access",
					"Washer and Dryer", "Towels"));
			room.setAvilabilityStartDate(createRandomDate());
			room.setAvilabilityEndDate(room.getAvilabilityStartDate().plusDays(3));
			rooms.add(room);
		}
		return rooms;

	}

	private LocalDate createRandomDate() {
		LocalDate startDate = LocalDate.now();
		long start = startDate.toEpochDay();

		LocalDate endDate = startDate.plusMonths(6);
		long end = endDate.toEpochDay();

		long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
		return LocalDate.ofEpochDay(randomEpochDay);
	}

	private Double getRandomPrice() {
		List<Integer> price = Arrays.asList(50, 100, 150);
		Random rand = new Random();
		int randomPrice = price.get(rand.nextInt(price.size()));
		return Double.valueOf(randomPrice);
	}

	private Double getRandomDiscount() {
		List<Integer> price = Arrays.asList(0, 10, 20);
		Random rand = new Random();
		int randomPrice = price.get(rand.nextInt(price.size()));
		return Double.valueOf(randomPrice);
	}

	private String getRandomCity() {
		List<String> cities = Arrays.asList("AUH", "AUE", "ADE", "ADF", "AGA", "ADJ", "ANC", "CNF", "BEB", "BGY", "EGC",
				"ITO", "HMH", "YFB");
		Random rand = new Random();
		return cities.get(rand.nextInt(cities.size()));
	}

}
