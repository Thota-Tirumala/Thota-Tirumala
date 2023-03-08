package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entities.Products;

public interface ProductRepository extends JpaRepository<Products,Integer> {
	

}
