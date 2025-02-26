package com.food_delivery.restaurantservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food_delivery.restaurantservice.dto.ApiResponse;
import com.food_delivery.restaurantservice.model.Restaurant;
import com.food_delivery.restaurantservice.service.RestaurantService;

@RestController
@CrossOrigin(origins = "*")  // Allows requests from any origin
@RequestMapping("/restaurantservice")
public class RestaurantController {
    
    @Autowired
    RestaurantService restaurantService;
	
	
    @GetMapping("/public")    
    public String getMessageFromPublicEndpoint() {
        return "No Authorization required";
    }
    
    @GetMapping("/private")    
    public String getMessageFromPrivateEndpoint() {
        return "Authorization required";
    }
    
    
    @GetMapping("/allRestaurants")
    public ResponseEntity<ApiResponse> getAllRestaurants() {       
        return ResponseEntity.ok(new ApiResponse("All Restaurant Retrieval Success", 
        		restaurantService.getAllRestaurants()));
    }
    
    @GetMapping("/getRestaurntById")
    public ResponseEntity<ApiResponse> getRestaurantById(@RequestParam String id) {    
    	return ResponseEntity.ok(new ApiResponse("Retrieve Restaurant Information Success", 
    			restaurantService.getRestaurantById(Long.parseLong(id))));
    }
    
    @GetMapping("/getRestaurntByUsername")
    public ResponseEntity<ApiResponse> getRestaurant(@RequestParam String userName) {    
    	return ResponseEntity.ok(new ApiResponse("Retrieve Restaurant Information Success", 
    			restaurantService.getRestaurantByUserName(userName)));
    }
    
    
    @PostMapping("/updateProfile")
    //@PreAuthorize("hasAuthority('ROLE_RESTAURANT')")
    public ResponseEntity<ApiResponse> updateRestaurantProfile(@RequestBody Restaurant restaurant) {
    	return ResponseEntity.ok(new ApiResponse("User updated successfully", 
    			restaurantService.updateRestaurantProfile(restaurant)));
    	
    }
    
    @DeleteMapping("/deleteProfile")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam String userName) {
		System.out.println("delete restaurant user:: "+userName);
		restaurantService.deleteRestaurantProfile(userName);
		return ResponseEntity.ok(new ApiResponse("Restaurant Profile deleted successfully", null));
    }
    
}
