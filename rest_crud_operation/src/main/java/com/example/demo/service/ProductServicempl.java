package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.ProductNotFoundException;

@Service
public class ProductServicempl implements IProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Integer saveProduct(Product p) {
		p = productRepository.save(p);
		return p.getProdId();
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	@Override
	public Product findOneProduct(Integer id) {
		Optional<Product> p = productRepository.findById(id);
		if (p.isPresent()) {
			return p.get();
		} else {
			throw new ProductNotFoundException("Product not present of id " + id);
		}
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepository.delete(findOneProduct(id));
	}

	@Override
	public void updateProduct(Product p) {
		if (p.getProdId() != null && productRepository.existsById(p.getProdId())) {
			productRepository.save(p);
		} else {
			throw new ProductNotFoundException("Product not present of id " + p.getProdId());
		}
	}

}
