package com.cart.services;

import java.util.List;

import com.cart.models.Cart;
import com.cart.models.DetalleCartCompra;
import com.cart.models.Producto;

public interface CartService {

	public List<Cart> getAll();
	
	public Cart getById(Integer idCart);
	
	public Cart save(Cart cart);
	
	public Cart saveProductInCart(Integer idCart, DetalleCartCompra detalle);
	
	public Cart updateStatus(Integer idCart);
	
	public Cart removeProduct(Integer idCart, Integer idProducto);
	
	public List<Producto> getAllProducts(Integer idCart);
}
