package com.spring.jwt.entity;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_type_id")
    private AdminTypes adminTypes;

    public Admin() {
    }

    public Admin(String email, String password, AdminTypes adminTypes) {
        this.email = email;
        this.password = password;
        this.adminTypes = adminTypes;
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

    public AdminTypes getAdminTypes() {
        return adminTypes;
    }

    public void setAdminTypes(AdminTypes adminTypes) {
        this.adminTypes = adminTypes;
    }
}
