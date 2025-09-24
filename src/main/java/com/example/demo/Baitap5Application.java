package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;

import com.example.demo.config.StorageProperties;
import com.example.demo.services.IStorageService;

//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = { "com.example.demo" })
@EnableJpaRepositories(basePackages = "com.example.demo.Repository")
@EntityScan(basePackages = "com.example.demo.entity")
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan
//@EnableSwagger2
public class Baitap5Application {

	public static void main(String[] args) {
		SpringApplication.run(Baitap5Application.class, args);
	}

//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}

	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}

//	@Bean
//	public Docket SWAGGERApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				// .select()
//				// .apis(RequestHandlerSelectors.basePackage("vn.iotstar")).build();
//				.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
//	}

}
