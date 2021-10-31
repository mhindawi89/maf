package com.maf.demo.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maf.demo.service.ProvidersServcie;
import com.maf.demo.service.dto.BestHotelDTO;
import com.maf.demo.service.dto.CrazyHotelDTO;

@RestController
@RequestMapping(value = "/provider")
public class ProvidersApi {

	@Autowired
	private ProvidersServcie providersServcie;

	@GetMapping(value = "/bestHotels", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getBestHoteles(@RequestParam("city") String cityCode,
			@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam("numOfAdults") Integer numberOfAdults) {
		try {
			List<BestHotelDTO> bestHotelList = providersServcie.getBestHotel(cityCode, fromDate, toDate,
					numberOfAdults);
			return new ResponseEntity<>(bestHotelList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/crazyHotels", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getCrazyHoteles(@RequestParam("city") String cityCode,
			@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
			@RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
			@RequestParam("adultsCount") Integer adultsCount) {
		try {
			List<CrazyHotelDTO> crazyHotelList = providersServcie.getCrazyHotel(cityCode, from, to, adultsCount);
			return new ResponseEntity<>(crazyHotelList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
