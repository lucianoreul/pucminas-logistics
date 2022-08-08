package com.ms.logistics.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Main class of application.
 *
 * @author LucianoReul
 */
@SpringBootApplication
@EnableEurekaClient
public class MsUserApplication {

    /**
     * MAIN
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MsUserApplication.class, args);
    }
}
