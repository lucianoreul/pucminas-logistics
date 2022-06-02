package com.ms.logistics.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsUserApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MsUserApplication.class, args);
    }
}
