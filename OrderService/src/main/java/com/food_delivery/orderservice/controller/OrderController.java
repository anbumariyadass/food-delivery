package com.food_delivery.orderservice.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food_delivery.orderservice.service.SqsService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private SqsService sqsService;
	
	@GetMapping
	public ResponseEntity<String> createOrder() {
		Random random = new Random();
        int randomThreeDigitNumber = 100 + random.nextInt(900); // Generates a number from 100 to 999
        String queueResponse = sqsService.sendMessage("Order Id::"+randomThreeDigitNumber);
		return ResponseEntity.ok("Order is created successfully. Order Id is " + randomThreeDigitNumber+". Queue Response : "+queueResponse);
	}
}
