package com.djh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.djh.securitytest.dao")
public class TestSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestSecurityApplication.class, args);
    }
}
