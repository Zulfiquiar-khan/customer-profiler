package com.customer.profiler.dao.models;

import com.customer.profiler.service.models.ValidationStatus;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private Integer profileId;

    @NonNull
    @Column(name = "company_name")
    private String companyName;

    @NonNull
    @Column(name = "legal_name")
    private String legalName;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_address_id", referencedColumnName = "address_id")
    private Address businessAddress;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "legal_address_id", referencedColumnName = "address_id")
    private Address legalAddress;

    @NonNull
    @Column(name = "tax_id")
    private String taxIdentifier;

    @NonNull
    @Column(name = "tax_id_type")
    private String taxIdentifierType;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "website_link")
    private String websiteLink;

    @NonNull
    @Column(name="from_product")
    private Integer fromProduct;

    @Column(name = "validation_status")
    private ValidationStatus validationStatus;

    @Column(name="customer_id")
    private Integer customerId;

    @Column(name="delete_dt")
    @Temporal(TemporalType.TIMESTAMP)
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

    public Address getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(Address businessAddress) {
        this.businessAddress = businessAddress;
    }

    public Address getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(Address legalAddress) {
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

    public void setWebsiteLink(String wesiteLink) {
        this.websiteLink = wesiteLink;
    }

    @NonNull
    public Integer getFromProduct() {
        return fromProduct;
    }

    public void setFromProduct(@NonNull Integer fromProduct) {
        this.fromProduct = fromProduct;
    }

    @NonNull
    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(@NonNull ValidationStatus validationStatus) {
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
        Profile profile = (Profile) o;
        return profileId.equals(profile.profileId) &&
                companyName.equals(profile.companyName) &&
                legalName.equals(profile.legalName) &&
                taxIdentifier.equals(profile.taxIdentifier) &&
                taxIdentifierType.equals(profile.taxIdentifierType) &&
                email.equals(profile.email) &&
                websiteLink.equals(profile.websiteLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, companyName, legalName, taxIdentifier, taxIdentifierType, email, websiteLink);
    }
}
