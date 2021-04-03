package com.cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.models.Producto;
import com.cart.services.ProductoService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductoService productoRepo;
	
	@PostMapping
	public ResponseEntity<Producto> save(@RequestBody Producto pro) {
		return new ResponseEntity<Producto>(this.productoRepo.save(pro), HttpStatus.CREATED);
	}
}
