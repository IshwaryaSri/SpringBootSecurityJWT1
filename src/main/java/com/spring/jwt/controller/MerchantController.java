package com.spring.jwt.controller;

import com.spring.jwt.entity.Merchants;
import com.spring.jwt.service.MerchantSellerService;
import com.spring.jwt.service.MerchantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MerchantController {

    @Autowired
    MerchantSellerService merchantService;

    @Autowired
    MerchantTypeService merchantTypeService;

    @GetMapping("/merchant/login")
    public String login() {
        return "merchantlogin";
    }

    @GetMapping("/merchant/signup")
    public String signUp(Model model) {
        model.addAttribute("merchantTypes",merchantTypeService.findAll());
        return "merchantsignup";
    }

        @RequestMapping(value={"/merchant/signup"}, method = RequestMethod.POST)
    public String saveMerchant(Merchants merchant, @RequestParam int typeId) throws Exception {
        boolean merchantExists = merchantService.existsByUsername(merchant.getEmail());
        if(merchantExists!=true) {
            Merchants newMerchant = merchantService.save(merchant,typeId);
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
