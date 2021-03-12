package com.spring.jwt.config;

import com.spring.jwt.security.JwtRequestFilter;
import com.spring.jwt.service.MerchantSellerService;
import com.spring.jwt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Order(2)
public class MerchantSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private MerchantSellerService merchantService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CustomMerchantAuthenticationHandler successhandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(merchantService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http
                .antMatcher("/merchant/**")
                .authorizeRequests()
                .antMatchers("/**","/merchant/login","/merchant/signup").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/merchant/login")
                .successHandler(successhandler)
                .failureUrl("/merchant/error")
                .permitAll().and()
//                .logout().logoutUrl("/user/logout")
//                .logoutSuccessUrl("/user/login?logout=true")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .clearAuthentication(true)
//                .permitAll().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}