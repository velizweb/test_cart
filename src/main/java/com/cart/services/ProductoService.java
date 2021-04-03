package com.cart.services;

import java.util.List;

import com.cart.models.Producto;

public interface ProductoService {
	
	public List<Producto> getAll();
	
	public Producto getById(Integer idProducto);
	
	public Producto save(Producto producto);
	
	public Producto update(Producto producto);
	
	public void updateStock(Integer idProducto, Integer cantidad);
}
