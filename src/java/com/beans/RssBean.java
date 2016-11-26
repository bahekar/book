/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author chhavikumar.b
 */
public class RssBean {
      private String id;
    private String category_id;
    private String description;

    /**
     * @return the category_id
     */
    public String getCategory_id() {
        return category_id;
    }

    /**
     * @param category_id the category_id to set
     */
    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
