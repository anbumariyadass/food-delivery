package com.food_delivery.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class CartServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

}
