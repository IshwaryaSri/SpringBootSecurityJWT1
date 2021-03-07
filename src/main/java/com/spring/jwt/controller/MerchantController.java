package com.spring.jwt.controller;

import com.spring.jwt.entity.MerchantSeller;
import com.spring.jwt.entity.Users;
import com.spring.jwt.service.MerchantSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MerchantController {

    @Autowired
    MerchantSellerService merchantService;

    @GetMapping("/merchant/login")
    public String login() {
        return "merchantlogin";
    }

    @GetMapping("/merchant/signup")
    public String signUp() {

        return "merchantsignup";
    }

    @PostMapping("/merchant/signup")
    public String saveMerchant(MerchantSeller merchant) throws Exception {
        boolean merchantExists = merchantService.existsByUsername(merchant.getUsername());
        if(merchantExists!=true) {
            MerchantSeller newMerchant = merchantService.save(merchant);
            return "signupsuccess";
        }else{
            return "error";
        }
//        return ResponseEntity.ok(usersService.save(user));
    }

    @GetMapping("/merchant/home")
    public String home() throws Exception{
        return "welcomeMerchant";
    }

    @GetMapping("/merchant/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/merchant/login?logout=true";
    }

}
