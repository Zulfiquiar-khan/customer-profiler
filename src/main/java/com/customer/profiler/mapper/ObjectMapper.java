package com.customer.profiler.mapper;

import com.customer.profiler.dao.models.Address;
import com.customer.profiler.dao.models.Product;
import com.customer.profiler.dao.models.Profile;
import com.customer.profiler.service.models.CustomerAddress;
import com.customer.profiler.service.models.CompanyProduct;
import com.customer.profiler.service.models.CustomerProfile;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.types.AddressField;

@Service
public class ObjectMapper {

    private MapperFactory mapperFactory;

    private ObjectMapper(){
        this.mapperFactory = new DefaultMapperFactory.Builder().build();
        createMappingRule();
    }

    private void createMappingRule() {
        createProductMappingRule();
        createProfileMappeingRule();
        createAddressMappingRule();
    }

    private void createAddressMappingRule() {
        mapperFactory.classMap(Address.class, CustomerAddress.class).byDefault();
    }

    private void createProfileMappeingRule() {
        mapperFactory.classMap(Profile.class, CustomerProfile.class).byDefault();
/*                .field("profileId","profileId")
                .field("companyName","companyName")
                .field("legalName","legalName")
                .field("businessAddress","businessAddress")
                .field("legalAddress","legalAddress")
                .field("taxIdentifier","taxIdentifier")
                .field("taxIdentifierType","taxIdentifierType")
                .field("email","email")
                .field("wesiteLink","wesiteLink")
                .field("subscribedProducts","subscribedCompanyProducts");*/
    }

    private void createProductMappingRule() {
        mapperFactory.classMap(Product.class, CompanyProduct.class).byDefault();
/*                .field("productId","productId")
                .field("productName","productName")
                .field("productApiLink","productApiLink")
                .field("createdDate","createdDate")
                .field("updateDate","updateDate");*/
    }

    public Address map(CustomerAddress address){
        MapperFacade mapper = this.mapperFactory.getMapperFacade();
        Address mappedAddress =  mapper.map(address, Address.class);
        return mappedAddress;
    }

    public CustomerAddress map(Address address){
        MapperFacade mapper = this.mapperFactory.getMapperFacade();
        CustomerAddress mappedAddress = mapper.map(address, CustomerAddress.class);
        return mappedAddress;
    }

    public CustomerProfile map(Profile profile){
        MapperFacade mapper = this.mapperFactory.getMapperFacade();
        CustomerProfile customerProfile = mapper.map(profile,CustomerProfile.class);
        customerProfile.setBusinessAddress(mapper.map(profile.getBusinessAddress(), CustomerAddress.class));
        customerProfile.setLegalAddress(mapper.map(profile.getLegalAddress(),CustomerAddress.class));
        return customerProfile;
    }

    public Profile map(CustomerProfile customerProfile){
        MapperFacade mapper = this.mapperFactory.getMapperFacade();
        Profile profile = mapper.map(customerProfile,Profile.class);
        profile.setBusinessAddress(mapper.map(customerProfile.getBusinessAddress(),Address.class));
        profile.setLegalAddress(mapper.map(customerProfile.getLegalAddress(),Address.class));
        return profile;
    }

    public Product map(CompanyProduct companyProduct){
        MapperFacade mapper = this.mapperFactory.getMapperFacade();
        Product mappedProduct = mapper.map(companyProduct,Product.class);
        return mappedProduct;
    }

    public CompanyProduct map(Product product){
        MapperFacade mapper = this.mapperFactory.getMapperFacade();
        CompanyProduct companyProduct1 = mapper.map(product, CompanyProduct.class);
        return companyProduct1;
    }

}
