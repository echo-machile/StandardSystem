package com.standard;

import com.standard.Common.Security.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Springsecurity配置

 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

    @Autowired
    private loginSuccessHandler loginSuccessHandler;

    @Autowired
    private loginFailureHandler loginFailureHandler;

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    private static final String[] URL_WHITELiST ={
            "/login",
            "/logout",
            "/captcha",
            "/password",
            "/images/**",

    };

    @Bean
    jwtAuthFilter jwtAuthFilter(){
        jwtAuthFilter jwt = new jwtAuthFilter(authentication -> {
            return authentication;
        });
        return jwt;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //开启跨域以及关闭csrf
        http
                .cors()
                .and()
                .csrf()
                .disable()


        //登陆配置

        .formLogin()
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)
        .and()
            .logout()
            .logoutSuccessHandler(jwtLogoutSuccessHandler)

        //session禁用配置


                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//无状态
        // 拦截规则配置

                .and()
                .authorizeHttpRequests()
                .requestMatchers(URL_WHITELiST).permitAll()//白名单放行
                .anyRequest().authenticated()

                //异常处理配置
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)

                .and()
                .userDetailsService(myUserDetailService)
                .addFilter(jwtAuthFilter());





        // 自定义过滤器配置
        return http.build();
    }



    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
