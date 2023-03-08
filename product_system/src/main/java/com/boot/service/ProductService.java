package com.boot.service;

import java.util.List;

import com.boot.entities.Products;

public interface ProductService  {

	public Products saveProducts(Products product);
	
	public List<Products> getAllProducts();
	
	public Products getProductById(Integer id);
	
	public String deleteProduct(Integer id);
	
	public Products updateProduct(Products product, Integer id);
}
