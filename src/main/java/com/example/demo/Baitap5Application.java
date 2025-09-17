package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@SpringBootApplication(scanBasePackages = {"com.example.demo.controller"})
@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@EnableJpaRepositories(basePackages = "com.example.demo.Repository")
@EntityScan(basePackages = "com.example.demo.entity")
@ComponentScan
public class Baitap5Application {

	public static void main(String[] args) {
		SpringApplication.run(Baitap5Application.class, args);
	}
	
	@Bean
	 public InternalResourceViewResolver viewResolver() {
	 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	 viewResolver.setViewClass(JstlView.class);
	 viewResolver.setPrefix("/WEB-INF/views/");
	 viewResolver.setSuffix(".jsp");
	 return viewResolver;
	 }
	
}

