package com.nostra.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nostra.test.entity.Category;
import com.nostra.test.repository.CategoryRepository;

@SpringBootApplication
public class NostraTestApplication implements CommandLineRunner{

	@Autowired 
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(NostraTestApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Long count = categoryRepository.count();
		if(count.intValue() == 0) {
			Category c1 = new Category();
			c1.setCategoryId("M001");
			c1.setCategoryName("My Movie");
			c1.setCreatedAt(new Date());
			c1.setStatus("Active");
			
			Category c2 = new Category();
			c2.setCategoryId("H001");
			c2.setCategoryName("Header");
			c2.setStatus("Active");
			c2.setCreatedAt(new Date());
			
			categoryRepository.save(c1);
			categoryRepository.save(c2);
		}
	}
}
