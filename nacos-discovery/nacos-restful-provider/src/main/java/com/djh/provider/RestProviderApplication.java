package com.djh.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * rest提供者应用程序
 *
 * @author MrMyHui
 * @date 2021/04/02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RestProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestProviderApplication.class, args);
    }
}
