package com.spring.jwt.config;

import com.spring.jwt.security.JwtRequestFilter;
import com.spring.jwt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.spring.jwt.oauth.CustomOAuth2UserService;

@EnableWebSecurity
@Configuration
@Order(1)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersService usersService;


    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CustomUserAuthenticationHandler customSuccesshandler;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/**","/oauth2/**","/user/**").permitAll()
                .anyRequest().authenticated().and()
                .oauth2Login()
//                .loginPage("/user/login")
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .successHandler(customSuccesshandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
