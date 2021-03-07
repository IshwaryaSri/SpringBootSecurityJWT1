package com.spring.jwt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "merchantseller")
public class MerchantSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer phone;
    private String mail;
    private String mailSecondary;
    private Integer phoneSecondary;
    private String zone;
    private Date dateOfJoining;
    private String image;
    private String status;
    private Integer bookings;
    private String ownerName;
    private Integer ownerPhone;
    private Integer zaloninBenchMark;
    private String city;
    private double rating;
    private String warehouseAddress;
    private String deliveryMedium;
    private Integer productCategoriesCount;
    private String productCategoriesName;
    private String currentOrderProcessingSoftware;
    private String serviceableAreas;
    private Integer locationOfSmallStores;
    private String accessToken;

    public MerchantSeller() {
    }

    public MerchantSeller(String username, String password,String name,String mail) {
        this.username = username;
        this.password = password;
        this.name=name;
        this.mail = mail;
    }

    public MerchantSeller(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMailSecondary() {
        return mailSecondary;
    }

    public void setMailSecondary(String mailSecondary) {
        this.mailSecondary = mailSecondary;
    }

    public Integer getPhoneSecondary() {
        return phoneSecondary;
    }

    public void setPhoneSecondary(Integer phoneSecondary) {
        this.phoneSecondary = phoneSecondary;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBookings() {
        return bookings;
    }

    public void setBookings(Integer bookings) {
        this.bookings = bookings;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(Integer ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public Integer getZaloninBenchMark() {
        return zaloninBenchMark;
    }

    public void setZaloninBenchMark(Integer zaloninBenchMark) {
        this.zaloninBenchMark = zaloninBenchMark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getDeliveryMedium() {
        return deliveryMedium;
    }

    public void setDeliveryMedium(String deliveryMedium) {
        this.deliveryMedium = deliveryMedium;
    }

    public Integer getProductCategoriesCount() {
        return productCategoriesCount;
    }

    public void setProductCategoriesCount(Integer productCategoriesCount) {
        this.productCategoriesCount = productCategoriesCount;
    }

    public String getProductCategoriesName() {
        return productCategoriesName;
    }

    public void setProductCategoriesName(String productCategoriesName) {
        this.productCategoriesName = productCategoriesName;
    }

    public String getCurrentOrderProcessingSoftware() {
        return currentOrderProcessingSoftware;
    }

    public void setCurrentOrderProcessingSoftware(String currentOrderProcessingSoftware) {
        this.currentOrderProcessingSoftware = currentOrderProcessingSoftware;
    }

    public String getServiceableAreas() {
        return serviceableAreas;
    }

    public void setServiceableAreas(String serviceableAreas) {
        this.serviceableAreas = serviceableAreas;
    }

    public Integer getLocationOfSmallStores() {
        return locationOfSmallStores;
    }

    public void setLocationOfSmallStores(Integer locationOfSmallStores) {
        this.locationOfSmallStores = locationOfSmallStores;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
