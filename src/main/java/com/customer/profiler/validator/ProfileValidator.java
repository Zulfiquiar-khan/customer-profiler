package com.customer.profiler.validator;

import com.customer.profiler.exception.ValidationException;
import com.customer.profiler.service.models.CustomerAddress;
import com.customer.profiler.service.models.CustomerProfile;

import java.util.ArrayList;
import java.util.List;

public class ProfileValidator implements IValidator {

    private IValidator addressvalidator;

    public ProfileValidator(AddressValidator addressValidator){
        this.addressvalidator = addressValidator;
    }

    @Override
    public void validate(Object object) throws ValidationException {
        CustomerProfile customerProfile = (CustomerProfile) object;
        validateForNullAndEmptyAttributes(customerProfile);
        validateAddress(customerProfile.getBusinessAddress());
        validateAddress(customerProfile.getLegalAddress());
    }

    private void validateAddress(CustomerAddress businessAddress) throws ValidationException {
        this.addressvalidator.validate(businessAddress);
    }

    private void validateForNullAndEmptyAttributes(CustomerProfile customerProfile) throws ValidationException {
        List<String> attrs = new ArrayList<>();
        if(null == customerProfile.getBusinessAddress())
            attrs.add("businessAddress");
        if(null == customerProfile.getLegalAddress())
            attrs.add("legalAddress");
        if(null == customerProfile.getCompanyName() || customerProfile.getCompanyName().isEmpty())
            attrs.add("companyName");
        if(null == customerProfile.getEmail() || customerProfile.getEmail().isEmpty())
            attrs.add("email");
        if(null == customerProfile.getTaxIdentifier() || customerProfile.getTaxIdentifier().isEmpty())
            attrs.add("taxIdentifier");
        if(null == customerProfile.getTaxIdentifierType() || customerProfile.getTaxIdentifierType().isEmpty())
            attrs.add("taxIdentifierType");
        if(null == customerProfile.getCustomerId())
            attrs.add("customerId");

        if(!attrs.isEmpty()){
            ValidationException validationException = new ValidationException("Invalid customer profile");
            validationException.setErrorCode("val-103");
            validationException.setErrorType("validation");
            validationException.setDescription("Following attributes in customer profile is mandatory : ");
            validationException.setAttributes(attrs);
            throw validationException;
        }
    }
}
