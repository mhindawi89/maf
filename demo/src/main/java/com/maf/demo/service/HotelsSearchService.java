package com.maf.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.maf.demo.service.dto.AvailableHotelDTO;

public interface HotelsSearchService {

	List<AvailableHotelDTO> getAvailableHotels(LocalDate fromDate, LocalDate toDate, String cityCode,
			Integer numOfAdults)throws Exception;

}
