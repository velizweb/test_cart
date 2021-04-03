package com.cart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.models.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer> {

}
