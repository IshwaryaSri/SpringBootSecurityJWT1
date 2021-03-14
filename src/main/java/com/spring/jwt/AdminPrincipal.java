package com.spring.jwt;

import com.spring.jwt.entity.Admin;
import com.spring.jwt.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AdminPrincipal implements UserDetails {

    private Admin admin;

    public AdminPrincipal(Admin admin) {
        super();
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));

    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return admin.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


}
