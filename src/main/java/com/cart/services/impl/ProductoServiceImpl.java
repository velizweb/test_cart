package com.cart.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cart.models.Producto;
import com.cart.repo.IProductoRepo;
import com.cart.services.ProductoService;

public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private IProductoRepo productoRepo;
	
	@Override
	public List<Producto> getAll() {
		return productoRepo.findAll();
	}

	@Override
	public Producto getById(Integer idProducto) {
		return productoRepo.getOne(idProducto);
	}

	@Override
	@Transactional(readOnly = false)
	public Producto save(Producto producto) {
		return productoRepo.save(producto);
	}

	@Override
	@Transactional(readOnly = false)
	public Producto update(Producto producto) {
		return productoRepo.saveAndFlush(producto);
	}

	@Override
	public void updateStock(Integer idProducto, Integer cantidad) {
		Producto pro =  this.getById(idProducto);
		pro.setStock(pro.getStock() - cantidad);
	}

}
