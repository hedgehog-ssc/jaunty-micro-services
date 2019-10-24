package com.jaunty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class JauntyMicroSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JauntyMicroSystemApplication.class, args);
    }

}
