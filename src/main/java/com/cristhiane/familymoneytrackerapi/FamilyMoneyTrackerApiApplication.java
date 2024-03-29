package com.cristhiane.familymoneytrackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FamilyMoneyTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyMoneyTrackerApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://family-money-tracker-web-v2.herokuapp.com/")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

}
