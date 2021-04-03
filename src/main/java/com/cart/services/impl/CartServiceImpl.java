package com.cart.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cart.models.Cart;
import com.cart.models.DetalleCartCompra;
import com.cart.models.Producto;
import com.cart.repo.ICartRepo;
import com.cart.repo.IDetalleCartProductoRepo;
import com.cart.services.CartService;
import com.cart.services.ProductoService;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService{
	
	@Autowired
	private ICartRepo cartRepo;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IDetalleCartProductoRepo detalleRepo;
	
	@Override
	public List<Cart> getAll() {
		return cartRepo.findAll();
	}

	@Override
	public Cart getById(Integer idCart) {
		return cartRepo.getOne(idCart);
	}

	@Override
	@Transactional(readOnly = false)
	public Cart save(Cart cart) {
		
		cart.setCreateDate(new Date());
		
		// Calculando el total de la compra
		cart.setTotal(
				cart.getDetalleCompra()
					.stream()
					.mapToDouble(detalle -> {
						Producto producto = productoService.getById(detalle.getProducto().getId());
						detalle.setPrecio(producto.getUnit_price());
						detalle.setTotal(producto.getUnit_price() * detalle.getCantidad());
						return detalle.getTotal();
					}).sum()		
			);
		
		// Actualizar Stock del producto
		cart.getDetalleCompra()
			.forEach(detalle -> productoService.updateStock(detalle.getProducto().getId(), detalle.getCantidad()));
		
		cartRepo.save(cart);
		
		cart.getDetalleCompra().forEach(detalle -> {
			detalle.setCart(cart);
			detalleRepo.save(detalle);
		});
		
		return cart;
	}

	@Override
	@Transactional(readOnly = false)
	public Cart saveProductInCart(Integer idCart, DetalleCartCompra detalle) {
		//DetalleCartCompra detalle = new DetalleCartCompra();
		Cart cart = this.getById(idCart);
		//Producto pro = productoService.getById(idProducto);
		detalle.setCart(cart);
		//detalle.setProducto(pro);
		//detalle.setPrecio(pro.getUnit_price());
		detalle.setTotal(detalle.getPrecio() * detalle.getCantidad());
		
		detalleRepo.save(detalle);
		
		return detalle.getCart();
	}
	
	@Override
	@Transactional(readOnly = false)
	public Cart removeProduct(Integer idCart, Integer idProducto) {
		Cart cart = this.getById(idCart);
		
		cart.getDetalleCompra()
			.stream()
			.forEach(detalle->{
				if(detalle.getProducto().getId() == idProducto) {
					detalleRepo.deleteById(idProducto);
				}
			});
		
		return cart;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Cart updateStatus(Integer idCart) {
		Cart cart = this.getById(idCart);
		cart.setStatus("READY");
		cartRepo.save(cart);
		
		return cart;
	}
	
	@Override
	public List<Producto> getAllProducts(Integer idCart) {
		List<Producto> listPro = new ArrayList<Producto>();
		Cart cart = this.getById(idCart);
		cart.getDetalleCompra()
			.stream()
			.forEach(detalle -> {
				listPro.add(detalle.getProducto());
			});
		
		return listPro;
	}
}
