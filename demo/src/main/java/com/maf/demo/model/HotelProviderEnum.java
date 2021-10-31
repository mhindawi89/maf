package com.maf.demo.model;

public enum HotelProviderEnum {

	BEST_HOTEL("Best Hotel", "http://localhost:8080/provider/bestHotel"),
	CRAZY_HOTEL("Crazy Hotel", "http://localhost:8080/provider/crazyHotel");

	private String providerName;
	private String providerUrl;

	HotelProviderEnum(String providerName, String providerUrl) {
		this.providerName = providerName;
		this.providerUrl = providerUrl;
	}

	public String getProviderName() {
		return providerName;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

}
