/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.Date;

/**
 *
 * @author chhavikumar.b
 */
public class PropertyBean {

    private String address;
    private String propertyUnit;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String category;
    private String forSale;
    private int id;
    private String name;
    private String description;
    private String propertyName;
    private String company;
    private String email;
    private String showEmail;
    private String showMobile;
    private String phone;
    private int price;
    private String dateAvailable;
    private int beds;
    private int baths;
    private int propertyType;
    private int squareFeet;
    private int unitFloor;
    private int furnished;
    private int parkingSpaces;
    private int petsAllowed;
    private String parkingType;
    private String images;
    private boolean isPropertyContactDetailsInserted = false;
    private String latitude;
    private String longitude;
    private int yearBuilt;
    private boolean isPropertyContactDeatils;
    private String propertycat;
    private int laundry;
    private int share_to_agents;
    private int commission;
    private String developer;
    private int listingType;
    private int crime_info;
    private int flood;
    private int seismic;

    /**
     * @return the propertyUnit
     */
    public String getPropertyUnit() {
        return propertyUnit;
    }

    /**
     * @param propertyUnit the propertyUnit to set
     */
    public void setPropertyUnit(String propertyUnit) {
        this.propertyUnit = propertyUnit;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * @param pincode the pincode to set
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    /**
     * @return the forSale
     */
    public String getForSale() {
        return forSale;
    }

    /**
     * @param forSale the forSale to set
     */
    public void setForSale(String forSale) {
        this.forSale = forSale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBaths() {
        return baths;
    }

    public void setBaths(int baths) {
        this.baths = baths;
    }

    public int getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(int propertyType) {
        this.propertyType = propertyType;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public int getUnitFloor() {
        return unitFloor;
    }

    public void setUnitFloor(int unitFloor) {
        this.unitFloor = unitFloor;
    }

    public int getFurnished() {
        return furnished;
    }

    public void setFurnished(int furnished) {
        this.furnished = furnished;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public boolean isIsPropertyContactDetailsInserted() {
        return isPropertyContactDetailsInserted;
    }

    public void setIsPropertyContactDetailsInserted(boolean isPropertyContactDetailsInserted) {
        this.isPropertyContactDetailsInserted = isPropertyContactDetailsInserted;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the isPropertyContactDeatils
     */
    public boolean isIsPropertyContactDeatils() {
        return isPropertyContactDeatils;
    }

    /**
     * @param isPropertyContactDeatils the isPropertyContactDeatils to set
     */
    public void setIsPropertyContactDeatils(boolean isPropertyContactDeatils) {
        this.isPropertyContactDeatils = isPropertyContactDeatils;
    }

    /**
     * @return the yearBuilt
     */
    public int getYearBuilt() {
        return yearBuilt;
    }

    /**
     * @param yearBuilt the yearBuilt to set
     */
    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    /**
     * @return the showEmail
     */
    public String getShowEmail() {
        return showEmail;
    }

    /**
     * @param showEmail the showEmail to set
     */
    public void setShowEmail(String showEmail) {
        this.showEmail = showEmail;
    }

    /**
     * @return the showMobile
     */
    public String getShowMobile() {
        return showMobile;
    }

    /**
     * @param showMobile the showMobile to set
     */
    public void setShowMobile(String showMobile) {
        this.showMobile = showMobile;
    }

    /**
     * @return the petsAllowed
     */
    public int getPetsAllowed() {
        return petsAllowed;
    }

    /**
     * @param petsAllowed the petsAllowed to set
     */
    public void setPetsAllowed(int petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the propertycat
     */
    public String getPropertycat() {
        return propertycat;
    }

    /**
     * @param propertycat the propertycat to set
     */
    public void setPropertycat(String propertycat) {
        this.propertycat = propertycat;
    }

    /**
     * @return the laundry
     */
    public int getLaundry() {
        return laundry;
    }

    /**
     * @param laundry the laundry to set
     */
    public void setLaundry(int laundry) {
        this.laundry = laundry;
    }

    /**
     * @return the share_to_agents
     */
    public int getShare_to_agents() {
        return share_to_agents;
    }

    /**
     * @param share_to_agents the share_to_agents to set
     */
    public void setShare_to_agents(int share_to_agents) {
        this.share_to_agents = share_to_agents;
    }

    /**
     * @return the commission
     */
    public int getCommission() {
        return commission;
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(int commission) {
        this.commission = commission;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the developer
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * @param developer the developer to set
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * @return the listingType
     */
    public int getListingType() {
        return listingType;
    }

    /**
     * @param listingType the listingType to set
     */
    public void setListingType(int listingType) {
        this.listingType = listingType;
    }

    /**
     * @return the crime_info
     */
    public int getCrime_info() {
        return crime_info;
    }

    /**
     * @param crime_info the crime_info to set
     */
    public void setCrime_info(int crime_info) {
        this.crime_info = crime_info;
    }

    /**
     * @return the flood
     */
    public int getFlood() {
        return flood;
    }

    /**
     * @param flood the flood to set
     */
    public void setFlood(int flood) {
        this.flood = flood;
    }

    /**
     * @return the seismic
     */
    public int getSeismic() {
        return seismic;
    }

    /**
     * @param seismic the seismic to set
     */
    public void setSeismic(int seismic) {
        this.seismic = seismic;
    }
}
