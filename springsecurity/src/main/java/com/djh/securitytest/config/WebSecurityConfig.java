package com.djh.securitytest.config;

import com.djh.securitytest.filter.VerifyFilter;
import com.djh.securitytest.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * 该类是 Spring Security 的配置类，该类的三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Security 注解。
 * @EnableWebSecurity 是个组合注解，起到决定性的配置作用，引入三个配置类，分别是 WebSecurityConfiguration·SpringWebMvcImportSelector·OAuth2ImportSelector，
 *                    完成的工作便是加载了WebSecurityConfiguration，AuthenticationConfiguration这两个核心配置类，
 *                    也就此将spring security的职责划分为了配置安全信息，配置认证信息两部分。
 *                    WebSecurityConfiguration配置类中，有一个非常重要的Bean被注册了。就是 springSecurityFilterChain 他是spring security的核心过滤器，是整个认证的入口，
 *                    WebSecurityConfiguration中完成了声明springSecurityFilterChain的作用
 *                    最终交给DelegatingFilterProxy这个代理类，负责拦截请求（注意DelegatingFilterProxy这个类不是spring security包中的，而是存在于web包中，spring使用了代理模式来实现安全过滤的解耦）
 *                    AuthenticationConfiguration的主要任务，便是负责生成全局的身份认证管理者AuthenticationManager。
 * WebSecurityConfigurerAdapter：
 *                              适配器模式在spring中被广泛的使用，在配置中使用Adapter的好处便是，我们可以选择性的配置想要修改的那一部分配置，而不用覆盖其他不相关的配置。
 *                              WebSecurityConfigurerAdapter中我们可以选择自己想要修改的内容，来进行重写，而其提供了三个configure重载方法，是我们主要关心的，
 *                              由参数就可以知道，分别是对AuthenticationManagerBuilder，WebSecurity，HttpSecurity进行个性化的配置
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

    @Autowired
    private MyAuthenticationProvider authenticationProvider;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * 想要在WebSecurityConfigurerAdapter中进行认证相关的配置，可以使用configure(AuthenticationManagerBuilder auth)暴露一个AuthenticationManager的建造器：
     * AuthenticationManagerBuilder 。它的作用域局限于一个WebSecurityConfigurerAdapter内部
     * @param auth 身份验证
     * @throws Exception 异常
     */
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
        //auth.userDetailsService(userDetailsService)
                //指定密码的加密方式
                /* PasswordEncoder是一个密码加密接口，而BCryptPasswordEncoder是Spring Security提供的一个实现方法，我们也可以自己实现PasswordEncoder。不过Spring Security实现的BCryptPasswordEncoder已经足够强大 */
                //.passwordEncoder(passwordEncoder);
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * Configuration配置HttpSecurity的典型配置，其中http作为根开始配置，每一个and()对应了一个模块的配置（等同于xml配置中的结束标签），并且and()返回了HttpSecurity本身，
     * 于是可以连续进行配置。他们配置的含义也非常容易通过变量本身来推测，
     * authorizeRequests()配置路径拦截，表明路径访问所对应的权限，角色，认证信息。
     * formLogin()对应表单认证相关的配置
     * logout()对应了注销相关的配置
     * httpBasic()可以配置basic登录
     * @param http 根配置
     * @throws Exception 出错抛出异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
                .antMatchers("/getVerifyCode").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                .formLogin().loginPage("/login")
                // 设置登陆成功页
                .defaultSuccessUrl("/").permitAll()
                //登录失败后的错误url
                .failureUrl("/login/error")
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                // 指定authenticationDetailsSource
                .authenticationDetailsSource(authenticationDetailsSource)
                .and()
//                .addFilterBefore(new VerifyFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout().permitAll()
                .and()
                //自动登录
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                // 有效时间：单位s
                .tokenValiditySeconds(60)
                .userDetailsService(userDetailsService);

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    /**
     * 配置放行的静态资源
     * @param web web根配置
     * @throws Exception 失败抛出异常
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }
}
