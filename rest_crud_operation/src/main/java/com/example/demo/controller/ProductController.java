package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private IProductService iProductService;

	// save product
	@PostMapping("/create")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		Integer id = iProductService.saveProduct(product);
		String message = "Product with id " + id + " created!..";
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}

	// get all products
	@GetMapping("/all-products")
	public ResponseEntity<List<Product>> showAllProducts() {
		List<Product> products = iProductService.findAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// get one product
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getOneProduct(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Product product = iProductService.findOneProduct(id);
			resp = new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	// delete one product
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		try {
			iProductService.deleteProduct(id);
			resp = new ResponseEntity<String>("Product with id " + id + " has been deleted!..", HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	// update one product
	@PutMapping("/modify")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> resp = null;
		try {
			iProductService.updateProduct(product);
			resp = new ResponseEntity<String>("Product with id " + product.getProdId() + " has been updated!..",
					HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
}
