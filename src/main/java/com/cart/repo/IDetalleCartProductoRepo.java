package com.cart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.models.DetalleCartCompra;

public interface IDetalleCartProductoRepo extends JpaRepository<DetalleCartCompra, Integer> {

}
