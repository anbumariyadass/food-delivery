package com.food_delivery.restaurantservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food_delivery.restaurantservice.model.Restaurant;
import com.food_delivery.restaurantservice.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	public Restaurant createRestaurantProfile(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}
	
	public Restaurant updateRestaurantProfile(Restaurant restaurant) {
		
		return restaurantRepository.save(restaurant);
	}
	
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant getRestaurantById(Long id) {
		return restaurantRepository.findById(id).orElse(new Restaurant());
	}
	
	public Restaurant getRestaurantByUserName(String userName) {
		return restaurantRepository.findByUsername(userName).orElse(new Restaurant());
	}
	
	public void deleteRestaurantProfile(String userName) {
		restaurantRepository.deleteByUsername(userName);
	}

}
