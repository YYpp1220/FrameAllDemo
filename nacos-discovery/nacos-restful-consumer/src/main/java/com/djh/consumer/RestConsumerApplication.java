package com.djh.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 其他使用者应用程序
 *
 * @author MrMyHui
 * @date 2021/04/02
 */
@SpringBootApplication
public class RestConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestConsumerApplication.class, args);
    }

    @Bean
    public RestTemplate setRestTemplate () {
        return new RestTemplate();
    }
}
