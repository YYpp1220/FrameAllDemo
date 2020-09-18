package com.djh;

import com.djh.securitytest.servlet.VerifyServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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

    /**
     * 指数servlet登记
     * 注入验证码servlet
     * @return {@link ServletRegistrationBean} springBoot中的servlet注入类
     */
    @SuppressWarnings("rawtypes")
    @Bean
    public ServletRegistrationBean indexServletRegistration(){
        ServletRegistrationBean<VerifyServlet> registration = new ServletRegistrationBean<>(new VerifyServlet());
        registration.addUrlMappings("/getVerifyCode");
        return registration;
    }
}
