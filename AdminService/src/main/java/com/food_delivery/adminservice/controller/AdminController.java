package com.food_delivery.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food_delivery.adminservice.service.SnsService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SnsService snsService;
	
	
	@GetMapping("/publishMessage")
	public ResponseEntity<String> publishMessage() {
		String response = snsService.publishMessage("Test content from admin service", "Message from Admin");
		return ResponseEntity.ok("Message is published successfully..." + response);
	}
	
	@PostMapping("/addSubscription/{mailId}")
	public ResponseEntity<String> addSubscription(@PathVariable String mailId) {
		String response = snsService.subscribeEmail(mailId);
		return ResponseEntity.ok("Message is published successfully..." + response);
	}
}
