package com.cart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.models.Cart;

public interface ICartRepo extends JpaRepository<Cart, Integer> {

}
