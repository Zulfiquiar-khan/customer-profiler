package com.customer.profiler.service.models;

import java.io.Serializable;
import java.util.Objects;

public class CustomerAddress implements Serializable {

    private static final long serialVersionUID = 7156526077883281624L;

    private Integer addressId;

    private String lineOne;

    private String lineTwo;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAddress address = (CustomerAddress) o;
        return lineOne.equals(address.lineOne) &&
                Objects.equals(lineTwo, address.lineTwo) &&
                city.equals(address.city) &&
                state.equals(address.state) &&
                country.equals(address.country) &&
                zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineOne, lineTwo, city, state, country, zipCode);
    }
}
