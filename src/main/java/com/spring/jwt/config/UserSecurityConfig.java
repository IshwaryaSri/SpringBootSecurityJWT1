package com.spring.jwt.config;

import com.spring.jwt.security.JwtRequestFilter;
import com.spring.jwt.service.AdminService;
import com.spring.jwt.service.MerchantSellerService;
import com.spring.jwt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MerchantSellerService merchantService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CustomUserAuthenticationHandler customSuccesshandler;

    @Autowired
    CustomMerchantAuthenticationHandler successhandler;

    @Autowired
    CustomSuccessHandler successHandler;


    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).and().userDetailsService(merchantService)
                .and().userDetailsService(adminService);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
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
                .antMatchers("/**","/oauth2/**","/user/**","/merchant/**").permitAll()
                .anyRequest().authenticated().and()
                .oauth2Login()
                .successHandler(successHandler)
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and()
                .and()
                .formLogin()
                .loginPage("/merchant/login")
                .successHandler(successHandler)
                .permitAll().and()
                .logout().logoutSuccessUrl("/").and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
