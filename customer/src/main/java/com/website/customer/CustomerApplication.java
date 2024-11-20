package com.website.customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages={"com.website"})
@EntityScan(basePackages = {"com.website"})
@EnableJpaRepositories(basePackages = {"com.website"})
public class CustomerApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
