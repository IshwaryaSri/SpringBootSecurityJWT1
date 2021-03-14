package com.spring.jwt.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admintype")
public class AdminTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="admin_type_name")
    private String adminTypeName;

    @OneToMany(mappedBy = "adminTypes", cascade = {CascadeType.ALL})
    private List<Admin> admin = new ArrayList<>();

    public AdminTypes() {
    }

    public AdminTypes(String adminTypeName, List<Admin> admin) {
        this.adminTypeName = adminTypeName;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminTypeName() {
        return adminTypeName;
    }

    public void setAdminTypeName(String adminTypeName) {
        this.adminTypeName = adminTypeName;
    }

    public List<Admin> getAdmin() {
        return admin;
    }

    public void setAdmin(List<Admin> admin) {
        this.admin = admin;
    }
}
