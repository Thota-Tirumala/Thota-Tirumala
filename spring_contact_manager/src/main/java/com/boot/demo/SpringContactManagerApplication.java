package com.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

//@EntityScan(basePackages= {"com.boot.demo.entities"})
@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan("com.boot.demo.controller")
//@ComponentScan("com.boot.demo.configuration")
public class SpringContactManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringContactManagerApplication.class, args);
		System.out.println("Done");
	}

}
