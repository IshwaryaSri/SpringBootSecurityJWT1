package com.spring.jwt.config;

import com.spring.jwt.entity.AuthenticationProvider;
import com.spring.jwt.service.UsersService;
import com.spring.jwt.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomUserAuthenticationHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtils jwtUtils;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    UsersService usersService;

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {

            DefaultOidcUser oAuth2User = (DefaultOidcUser) authentication.getPrincipal();
            String email = oAuth2User.getEmail();
            String token = oAuth2User.getIdToken().getTokenValue();

            boolean isUserExists = usersService.existsByEmail(email);

            if(isUserExists!=true){
                usersService.saveOAuth2LoginUser(email,AuthenticationProvider.GOOGLE);
            }else{
                System.out.println("User Updated!!");
            }
            usersService.saveToken(email,token);
            response.sendRedirect("/user/home");

        }
    }
