package com.cart.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cart.models.DetalleCartCompra;
import com.cart.repo.IDetalleCartProductoRepo;
import com.cart.services.DetalleCartProductoService;

public class DetalleCartProductoImpl implements DetalleCartProductoService{

	@Autowired
	private IDetalleCartProductoRepo detalleRepo;
	
	@Override
	@Transactional(readOnly = false)
	public DetalleCartCompra save(DetalleCartCompra detalle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleted(Integer idCart, Integer idProducto) {
		// TODO Auto-generated method stub
		
	}

}
