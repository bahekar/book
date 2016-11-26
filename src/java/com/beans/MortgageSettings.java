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
public class MortgageSettings {
    
    private float propertyTax;
    private float insurenceTax;
    private float rateOfInterest;
    private int noOfYears;

    public float getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(float propertyTax) {
        this.propertyTax = propertyTax;
    }

    public float getInsurenceTax() {
        return insurenceTax;
    }

    public void setInsurenceTax(float insurenceTax) {
        this.insurenceTax = insurenceTax;
    }

    public float getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(float rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public int getNoOfYears() {
        return noOfYears;
    }

    public void setNoOfYears(int noOfYears) {
        this.noOfYears = noOfYears;
    }
    
}
