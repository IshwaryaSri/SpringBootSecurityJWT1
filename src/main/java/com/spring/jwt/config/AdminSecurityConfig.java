package com.spring.jwt.config;

import com.spring.jwt.security.JwtRequestFilter;
import com.spring.jwt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CustomSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
        http.requestMatcher(new AntPathRequestMatcher("/admin/**"))
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .successHandler(successHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/admin/logout")
                .permitAll();
    }

}

