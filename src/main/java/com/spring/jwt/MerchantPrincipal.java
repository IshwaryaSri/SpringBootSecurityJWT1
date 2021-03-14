package com.spring.jwt;

import com.spring.jwt.entity.Merchants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MerchantPrincipal implements UserDetails {

    private Merchants merchant;

    public MerchantPrincipal(Merchants merchant) {
        super();
        this.merchant = merchant;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("MERCHANT"));

    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return merchant.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return merchant.getEmail();
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
