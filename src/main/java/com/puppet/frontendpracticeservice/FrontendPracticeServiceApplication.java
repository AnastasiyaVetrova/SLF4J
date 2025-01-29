package com.puppet.frontendpracticeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FrontendPracticeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendPracticeServiceApplication.class, args);
    }
}