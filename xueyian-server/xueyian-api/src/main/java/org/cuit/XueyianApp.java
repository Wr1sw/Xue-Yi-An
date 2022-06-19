package org.cuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author wr1sw
 * @version 1.0.0
 * @description
 */
@SpringBootApplication
public class XueyianApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(XueyianApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
