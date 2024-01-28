package com.UniversalRent.UniversalRent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.UniversalRent.UniversalRent.*")
public class UniversalRentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversalRentApplication.class, args);
	}

}
