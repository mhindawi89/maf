package com.maf.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConf {

	@Bean
	public OpenAPI customOpenAPI() {

		return new OpenAPI().components(new Components()).info(new Info().title("Hotel Availability search service API documentation")
				.description("This is a documentation for Hotel Availability search service that's belong to maf java backend challenge")
				.version("V-1").termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}

}
