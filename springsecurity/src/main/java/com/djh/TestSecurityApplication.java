package com.djh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan(basePackages = "com.djh.securitytest.dao")
public class TestSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestSecurityApplication.class, args);
    }

    /**
     * 注入PasswordEncoder对象，该对象用于密码加密
     * @return 返回PasswordEncoder对象
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
