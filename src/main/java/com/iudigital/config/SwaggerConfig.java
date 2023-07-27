package com.iudigital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import java.util.Collections;

@Configuration

public class SwaggerConfig {
	
	//http://localhost:8080/api/v1/v3/api-docs

	//http://localhost:8080/api/v1/swagger-ui-custom.html
	
	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Senado APP")
						.version("1.0")
						.description("Aplicacion Spring Boot")
						.termsOfService("http://localhost:8080/api/v1/swagger-ui-custom.html")
						.license(new License().name("Spring Boot 3.1.0").url("http://springdoc.org"))
						);
				
	}
    }
