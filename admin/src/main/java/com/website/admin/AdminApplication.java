package com.website.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.website",exclude = FreeMarkerAutoConfiguration.class)
@EnableTransactionManagement
@EntityScan(basePackages = {"com.website"})
@EnableJpaRepositories(basePackages = {"com.website"})
public class AdminApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
