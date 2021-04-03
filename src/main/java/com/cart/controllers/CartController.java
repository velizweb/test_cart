package com.cart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.models.Cart;
import com.cart.models.DetalleCartCompra;
import com.cart.models.Producto;
import com.cart.services.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/")
	public ResponseEntity<List<Cart>> getAll() {
		return new ResponseEntity<List<Cart>>(this.cartService.getAll(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cart> getById(@PathVariable("id") Integer idCart) {
		return new ResponseEntity<Cart>(this.cartService.getById(idCart), HttpStatus.ACCEPTED);	
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cart> save(@RequestBody Cart cart) {
		return new ResponseEntity<Cart>(this.cartService.save(cart), HttpStatus.CREATED);	
	}
	
	@PostMapping("/{id}/products")
	public ResponseEntity<Cart> addProductCart(@PathVariable("id") Integer idCart, @RequestBody DetalleCartCompra detalle) {
		return new ResponseEntity<Cart>(this.cartService.saveProductInCart(idCart, detalle), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/products")
	public ResponseEntity<List<Producto>> gatAllProductos(@PathVariable("id") Integer idCart) {
		return new ResponseEntity<List<Producto>>(this.cartService.getAllProducts(idCart), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/{id}/checkout")
	public void updatestatus(@PathVariable("id") Integer idCart) {
		this.cartService.updateStatus(idCart);
	}
	
	@DeleteMapping("/{idCart}/products/{idProduct}")
	public ResponseEntity<Cart>  deleteProductCart(@PathVariable("idCart") Integer idCart, @PathVariable("idProduct") Integer idProducto) {
		return  new ResponseEntity<Cart>(this.cartService.removeProduct(idCart, idProducto), HttpStatus.OK);
	}
}
