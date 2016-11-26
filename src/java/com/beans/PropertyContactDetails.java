/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author pavankumar.g
 */
public class PropertyContactDetails {
 
    private String name;
    private String company;
    private int type;
    private String email;
    private int showEmail;
    private String mobile;
    private int showMobile;
    private int propertyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getShowEmail() {
        return showEmail;
    }

    public void setShowEmail(int showEmail) {
        this.showEmail = showEmail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getShowMobile() {
        return showMobile;
    }

    public void setShowMobile(int showMobile) {
        this.showMobile = showMobile;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
    
}
