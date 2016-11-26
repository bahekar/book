/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

/**
 *
 * @author chhavikumar.b
 */
public class RestImages {
    private String name;
    private int size;        
    private String desc;
    private String iamgePath;

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the iamgePath
     */
    public String getIamgePath() {
        return iamgePath;
    }

    /**
     * @param iamgePath the iamgePath to set
     */
    public void setIamgePath(String iamgePath) {
        this.iamgePath = iamgePath;
    }

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
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
}
