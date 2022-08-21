package com.slyclothing.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.slyclothing.common.entity")
public class SlyClothingFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlyClothingFrontEndApplication.class, args);
	}

}
