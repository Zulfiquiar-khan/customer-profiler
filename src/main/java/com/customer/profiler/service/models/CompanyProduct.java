package com.customer.profiler.service.models;

import java.util.Date;
import java.util.Objects;

public class CompanyProduct {

    private Integer productId;

    private String productName;

    private String productApiLink;

    private Date createdDate;

    private Date updateDate;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductApiLink() {
        return productApiLink;
    }

    public void setProductApiLink(String productApiLink) {
        this.productApiLink = productApiLink;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyProduct companyProduct = (CompanyProduct) o;
        return productName.equals(companyProduct.productName) &&
                productApiLink.equals(companyProduct.productApiLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productApiLink);
    }
}
