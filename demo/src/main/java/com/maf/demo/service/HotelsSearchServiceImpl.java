package com.maf.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maf.demo.model.HotelProviderEnum;
import com.maf.demo.service.dto.AvailableHotelDTO;
import com.maf.demo.service.dto.BestHotelDTO;
import com.maf.demo.service.dto.CrazyHotelDTO;

@Service("HotelsSearchService")
public class HotelsSearchServiceImpl implements HotelsSearchService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<AvailableHotelDTO> getAvailableHotels(LocalDate fromDate, LocalDate toDate, String cityCode,
			Integer numOfAdults) throws Exception {
		if (fromDate.isAfter(toDate)) {
			throw new Exception("could not send from date bigger than to date");
		}

		List<AvailableHotelDTO> availableHotels = new ArrayList<>();
		List<BestHotelDTO> bestHotels = new ArrayList<>();
		List<CrazyHotelDTO> crazyHotels = new ArrayList<>();
		for (HotelProviderEnum provider : HotelProviderEnum.values()) {
			switch (provider) {
			case BEST_HOTEL:
				bestHotels = getBestHotel(provider, fromDate, toDate, cityCode, numOfAdults);
				break;
			case CRAZY_HOTEL:
				crazyHotels = getCrazyHotel(provider, fromDate, toDate, cityCode, numOfAdults);
				break;
			}
		}
		
		availableHotels.addAll(AvailableHotelDTO.getAvailableHotelFromBest(bestHotels));
		availableHotels.addAll(AvailableHotelDTO.getAvailableHotelFromCrazy(crazyHotels));

		return availableHotels;
	}

	private List<BestHotelDTO> getBestHotel(HotelProviderEnum provider, LocalDate fromDate, LocalDate toDate,
			String cityCode, Integer numOfAdults) throws JsonMappingException, JsonProcessingException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> header = new HttpEntity<>("header", httpHeaders);

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(provider.getProviderUrl())
				.queryParam("city", cityCode).queryParam("fromDate", fromDate).queryParam("toDate", toDate)
				.queryParam("numOfAdults", numOfAdults);

		String response = restTemplate.getForObject(builder.toUriString(), String.class);
		ObjectMapper mapper = new ObjectMapper();
		return Arrays.asList(mapper.readValue(response, BestHotelDTO[].class));

	}

	private List<CrazyHotelDTO> getCrazyHotel(HotelProviderEnum provider, LocalDate fromDate, LocalDate toDate,
			String cityCode, Integer numOfAdults) throws JsonMappingException, JsonProcessingException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> header = new HttpEntity<>("header", httpHeaders);

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(provider.getProviderUrl())
				.queryParam("city", cityCode).queryParam("from", fromDate.atStartOfDay())
				.queryParam("to", toDate.atStartOfDay()).queryParam("numOfAdults", numOfAdults);

		String response = restTemplate.getForObject(builder.toUriString(), String.class);
		ObjectMapper mapper = new ObjectMapper();
		return Arrays.asList(mapper.readValue(response, CrazyHotelDTO[].class));
	}

}
