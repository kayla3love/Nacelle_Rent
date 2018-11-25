package com.manager.nacelle_rent;

import com.manager.nacelle_rent.controller.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

@SpringBootApplication
@EnableAutoConfiguration
public class NacelleRentApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(NacelleRentApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        //解决WebSocket不能注入的问题
        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }
}
