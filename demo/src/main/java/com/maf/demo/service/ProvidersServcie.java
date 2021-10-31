package com.maf.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.maf.demo.service.dto.BestHotelDTO;
import com.maf.demo.service.dto.CrazyHotelDTO;

public interface ProvidersServcie {
	/**
	 * 
	 * @param cityCode       from IATA codes list
	 * @param fromDate
	 * @param toDate
	 * @param numberOfAdults
	 * @return list of available hotels from best hotel provider
	 */
	List<BestHotelDTO> getBestHotel(String cityCode, LocalDate fromDate, LocalDate toDate, Integer numberOfAdults) throws Exception;

	/**
	 * 
	 * @param cityCode    from IATA codes list
	 * @param from        date
	 * @param to          date
	 * @param adultsCount
	 * @return list of available hotels from crazy hotel provider
	 */
	List<CrazyHotelDTO> getCrazyHotel(String cityCode, LocalDateTime from, LocalDateTime to, Integer adultsCount)throws Exception;

}
