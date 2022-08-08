package com.ms.logistics.stock;

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
public class MsStockApplication {

    /**
     * MAIN
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MsStockApplication.class, args);
    }
}
