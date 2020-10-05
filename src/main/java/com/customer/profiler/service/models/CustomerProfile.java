package com.customer.profiler.service.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CustomerProfile implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    private Integer profileId;

    private String companyName;

    private String legalName;

    private CustomerAddress businessAddress;

    private CustomerAddress legalAddress;

    private String taxIdentifier;

    private String taxIdentifierType;

    private String email;

    private String websiteLink;

    private Integer fromProduct;

    private ValidationStatus validationStatus;

    private Integer customerId;

    private Date deleteDate;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public CustomerAddress getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(CustomerAddress businessAddress) {
        this.businessAddress = businessAddress;
    }

    public CustomerAddress getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(CustomerAddress legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
    }

    public void setTaxIdentifier(String taxIdentifier) {
        this.taxIdentifier = taxIdentifier;
    }

    public String getTaxIdentifierType() {
        return taxIdentifierType;
    }

    public void setTaxIdentifierType(String taxIdentifierType) {
        this.taxIdentifierType = taxIdentifierType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public Integer getFromProduct() {
        return fromProduct;
    }

    public void setFromProduct(Integer fromProduct) {
        this.fromProduct = fromProduct;
    }

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerProfile that = (CustomerProfile) o;
        return companyName.equals(that.companyName) &&
                legalName.equals(that.legalName) &&
                taxIdentifier.equals(that.taxIdentifier) &&
                taxIdentifierType.equals(that.taxIdentifierType) &&
                email.equals(that.email) &&
                websiteLink.equals(that.websiteLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, legalName, taxIdentifier, taxIdentifierType, email, websiteLink);
    }
}
