package com.spring.jwt.config;

import com.spring.jwt.service.MerchantSellerService;
import com.spring.jwt.service.UsersService;
import com.spring.jwt.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    OAuth2AuthorizedClientService clientService;

    @Autowired
    UsersService usersService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MerchantSellerService merchantService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isUser(roles)) {
            OAuth2AuthenticationToken oAuth2User = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oAuth2User.getAuthorizedClientRegistrationId();
            OAuth2User oauthToken = (OAuth2User) authentication.getPrincipal();
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                    clientRegistrationId, oauthToken.getName());
            String token = client.getAccessToken().getTokenValue();
            String email = oauthToken.getAttribute("email");

            boolean isUserExists = usersService.existsByEmail(email);

            if(isUserExists!=true){
                usersService.saveOAuth2LoginUser(email,clientRegistrationId);
                usersService.saveToken(email,token);
            }else{
                boolean tokenExists = usersService.existsByToken(token);
                if(tokenExists==true) {
                    usersService.updateUser(email,token);
                    System.out.println("User Updated!!");
                }else{
                    System.out.println("Invalid Token!!");
                }
            }
            url = "/user/home";
        }else if (isMerchant(roles)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            final String jwt = jwtUtils.generateToken(userDetails);
            merchantService.saveToken(username,jwt);
            url = "/merchant/home";
        } else if (isAdmin(roles)) {
             url = "/admin/home";
         } else {
            url = "/error";
        }

        return url;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isMerchant(List<String> roles) {
        if (roles.contains("MERCHANT")) {
            return true;
        }
        return false;
    }
}
