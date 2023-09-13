/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mxv.configs;

import com.mxv.filters.CustomAccessDeniedHandler;
import com.mxv.filters.JwtAuthenticationTokenFilter;
import com.mxv.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author huu-thanhduong
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.mxv.controllers",
    "com.mxv.repository",
    "com.mxv.service", 
    "com.mxv.components"})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/restaurant/add").permitAll();
        http.authorizeRequests().antMatchers("/api/restaurant/").permitAll();
        http.authorizeRequests().antMatchers("/api/categoryRes/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/follower/**").permitAll();
        http.authorizeRequests().antMatchers("/api/city/").permitAll();
        http.authorizeRequests().antMatchers("/api/menu/**").permitAll();
        http.authorizeRequests().antMatchers("/api/comment/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/comments/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_RESTAURANT') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_RESTAURANT') or hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_RESTAURANT') or hasRole('ROLE_USER')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
