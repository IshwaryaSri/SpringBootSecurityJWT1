package com.spring.jwt.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "merchants")
public class Merchants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="token")
    private String accessToken;

    @Column(name="is_verified")
    private boolean isVerified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merchant_type_id")
    private MerchantTypes merchantTypes;


    public Merchants() {
    }

    public Merchants(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Merchants(String accessToken) {
        this.accessToken = accessToken;
    }

    public Merchants(Integer id, String email, String password, String accessToken, boolean isVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.accessToken = accessToken;
        this.isVerified = isVerified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public MerchantTypes getMerchantTypes() {
        return merchantTypes;
    }

    public void setMerchantTypes(MerchantTypes merchantTypes) {
        this.merchantTypes = merchantTypes;
    }
}
