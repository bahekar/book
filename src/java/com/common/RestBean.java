/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.util.List;

/**
 *
 * @author chhavikumar.b
 */
public class RestBean {

    private String name;
    private String phone;
    private String closing_time;
    private String delivery;
    private String code;
    private String cuisine_id;
    private String rest_id;
    private List<RestImages> images;
    private String specific_area;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the cuisine_id
     */
    public String getCuisine_id() {
        return cuisine_id;
    }

    /**
     * @param cuisine_id the cuisine_id to set
     */
    public void setCuisine_id(String cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    /**
     * @return the images
     */
    public List<RestImages> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(List<RestImages> images) {
        this.images = images;
    }

    /**
     * @return the rest_id
     */
    public String getRest_id() {
        return rest_id;
    }

    /**
     * @param rest_id the rest_id to set
     */
    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the closing_time
     */
    public String getClosing_time() {
        return closing_time;
    }

    /**
     * @param closing_time the closing_time to set
     */
    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }

    /**
     * @return the delivery
     */
    public String getDelivery() {
        return delivery;
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    /**
     * @return the specific_area
     */
    public String getSpecific_area() {
        return specific_area;
    }

    /**
     * @param specific_area the specific_area to set
     */
    public void setSpecific_area(String specific_area) {
        this.specific_area = specific_area;
    }

}
