package com.senla.model.utils;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.senla.model"})
@PropertySource("classpath:config.properties")
public class AppConfig {

}
