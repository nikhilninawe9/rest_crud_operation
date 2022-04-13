package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface IProductService {

	Integer saveProduct(Product p);

	List<Product> findAllProducts();

	Product findOneProduct(Integer id);

	void deleteProduct(Integer id);

	void updateProduct(Product p);
}
