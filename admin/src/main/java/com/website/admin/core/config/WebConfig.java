package com.website.admin.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    String baseDir = "file:/home/cosmotech/website_files/";
        String baseDir = "file:E:/Dump/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/users/**")
                .addResourceLocations(baseDir + "users/");
    }
}
