package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.entities.Products;
import com.boot.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Products saveProducts(Products product) {
		
		
		return productRepository.save(product);
	}

	@Override
	public List<Products> getAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Products getProductById(Integer id) {
		
		return productRepository.findById(id).get();
	}

	@Override
	public String deleteProduct(Integer id) {
		
		Products product=productRepository.findById(id).get();
		
		if(product!=null)
		{
			productRepository.delete(product); 
			return "deleted successfully";
		}
		
		return "smothing wrong on the server";
	}

	@Override
	public Products updateProduct(Products p, Integer id) {
		
		Products oldproduct= productRepository.findById(id).get();
		oldproduct.setName(p.getName());
		oldproduct.setCode(p.getCode());
		oldproduct.setCost(p.getCost());
		oldproduct.setVendor(p.getVendor());
		oldproduct.setNote(p.getNote());
		
		return productRepository.save(null);
	}

}
