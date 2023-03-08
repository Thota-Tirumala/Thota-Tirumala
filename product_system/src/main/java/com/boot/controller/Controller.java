package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.entities.Products;
import com.boot.service.ProductService;

@RestController
public class Controller {
	
	@Autowired
	private ProductService productservice;
	
	//Add products
	@PostMapping("/saveproduct")
	public ResponseEntity<?> saveProduct(@RequestBody Products product)
	{
try {
			
			double cost = product.getCost();
			double gst = product.getGst();
			double discount = product.getDisc();
			
			product.setTotal((cost-discount)+gst);
			
			productservice.saveProducts(product);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(productservice.saveProducts(product),HttpStatus.CREATED);
	}
	
	@GetMapping("/show")
	public ResponseEntity<?>  getAllProducts()
	{
		
		return new ResponseEntity<>(productservice.getAllProducts(),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>  getproductById(@PathVariable Integer id)
	{
		return new ResponseEntity<>(productservice.getProductById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id)
	{
		return new ResponseEntity<>(productservice.deleteProduct(id),HttpStatus.OK);
	}
	
	@PutMapping("/product{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Products product,@PathVariable Integer id)
	{
		return new ResponseEntity<>(productservice.updateProduct(product,id),HttpStatus.CREATED);
	}

}
