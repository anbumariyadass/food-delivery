package com.food_delivery.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food_delivery.cartservice.entity.Cart;
import com.food_delivery.cartservice.repository.CartRepository;


@Service
public class cartService {
	@Autowired
    private CartRepository cartRepository;
	
	public String addCart(Cart cart) {
		cartRepository.save(cart);
		return cart.getCartId() +" is added successfully";
	}
	
	public Cart getCart(String cartId) {
		return cartRepository.findById(cartId);
	}
	
	public String updateCart(Cart cart) {
		cartRepository.save(cart);
		return cart.getCartId() +" is updated successfully";
	}
	
	public String deleteCart(String cartId) {
		cartRepository.deleteById(cartId);
		return cartId +" is deleted successfully";
	}
	
	 public List<Cart> getCartItemsForUser(String customerName) {
		 System.out.println(customerName);
	     return cartRepository.findByCustomerName(customerName);
	 }
}
