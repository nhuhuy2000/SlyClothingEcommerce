package com.slyclothing.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.slyclothing.common.entity")
public class SlyClothingBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlyClothingBackEndApplication.class, args);
	}

}
