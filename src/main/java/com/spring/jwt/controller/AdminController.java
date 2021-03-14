package com.spring.jwt.controller;

import com.spring.jwt.entity.Admin;
import com.spring.jwt.entity.Merchants;
import com.spring.jwt.service.AdminService;
import com.spring.jwt.service.AdminTypeService;
import com.spring.jwt.service.MerchantSellerService;
import com.spring.jwt.service.MerchantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminTypeService adminTypeService;

    @GetMapping("/admin/login")
    public String login() {
        return "adminlogin";
    }

    @GetMapping("/admin/signup")
    public String signUp(Model model) {
        model.addAttribute("adminTypesList",adminTypeService.findAll());
        return "adminsignup";
    }

    @RequestMapping(value={"/admin/signup"}, method = RequestMethod.POST)
    public String saveAdmin(Admin admin, @RequestParam int typeId) throws Exception {
        boolean adminExists = adminService.existsByUsername(admin.getEmail());
        if(adminExists!=true) {
            Admin newAdmin = adminService.save(admin,typeId);
            return "signupsuccess";
        }else{
            return "error";
        }
    }

    @GetMapping("/admin/home")
    public String home() throws Exception{
        return "welcomeAdmin";
    }

    @GetMapping("/admin/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin/login?logout=true";
    }

}

