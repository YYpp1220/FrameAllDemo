package com.djh.securitytest.config;

import com.djh.securitytest.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 该类是 Spring Security 的配置类，该类的三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Security 注解。
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入userDetailsService
     */
    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });*/
        //使用 auth.userDetailsService() 方法替换掉默认的 userDetailsService
        auth.userDetailsService(userDetailsService)
                //指定密码的加密方式
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
//                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login")
                // 设置登陆成功页
                .defaultSuccessUrl("/").permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                //自动登录
                .rememberMe();

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
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
