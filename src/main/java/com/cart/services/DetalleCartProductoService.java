package com.cart.services;

import com.cart.models.DetalleCartCompra;

public interface DetalleCartProductoService {
	
	public DetalleCartCompra save(DetalleCartCompra detalle);
	
	public void deleted(Integer idCart, Integer idProducto);
	
}
