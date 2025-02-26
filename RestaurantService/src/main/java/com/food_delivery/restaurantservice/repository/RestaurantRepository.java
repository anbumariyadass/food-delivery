package com.food_delivery.restaurantservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food_delivery.restaurantservice.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	Optional<Restaurant> findByUsername(String userName);
	void deleteByUsername(String username);
}
