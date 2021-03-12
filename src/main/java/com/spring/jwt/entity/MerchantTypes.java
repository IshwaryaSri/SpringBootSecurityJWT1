package com.spring.jwt.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "merchanttypes")
public class MerchantTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String merchantTypeName;

    @OneToMany(mappedBy = "merchantTypes", cascade = {CascadeType.ALL})
    private List<Merchants> merchants = new ArrayList<>();

    public MerchantTypes() {
    }

    public MerchantTypes(Integer id, String merchantTypeName, List<Merchants> merchants) {
        this.id = id;
        this.merchantTypeName = merchantTypeName;
        this.merchants = merchants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantTypeName() {
        return merchantTypeName;
    }

    public void setMerchantTypeName(String merchantTypeName) {
        this.merchantTypeName = merchantTypeName;
    }

    public List<Merchants> getMerchant() {
        return merchants;
    }

    public void setMerchant(List<Merchants> merchant) {
        this.merchants = merchants;
    }
}
