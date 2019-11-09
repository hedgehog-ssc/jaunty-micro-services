package com.jaunty;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class JauntyMicroAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(JauntyMicroAdminApplication.class, args);
    }

}
