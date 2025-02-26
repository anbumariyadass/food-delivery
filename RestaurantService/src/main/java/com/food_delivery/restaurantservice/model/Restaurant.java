package com.food_delivery.restaurantservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESATURANT_MASTER")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    private String restaurantname;
    
    private String address1;
    private String address2;
    private String address3;
    
    private String email;
    private String phone;
    private String restauranttype;
    
    public Restaurant() {
    }

	public Restaurant(Long id, String username, String restaurantname, String address1, String address2,
			String address3, String email, String phone, String restauranttype) {
		super();
		this.id = id;
		this.username = username;
		this.restaurantname = restaurantname;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.email = email;
		this.phone = phone;
		this.restauranttype = restauranttype;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUser_name(String username) {
		this.username = username;
	}

	public String getRestaurant_name() {
		return restaurantname;
	}

	public void setRestaurant_name(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRestauranttype() {
		return restauranttype;
	}

	public void setRestaurant_type(String restauranttype) {
		this.restauranttype = restauranttype;
	}

}
