package com.customer.profiler.validator;

import com.customer.profiler.exception.ValidationException;
import com.customer.profiler.service.models.CustomerAddress;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressValidator implements IValidator{

    @Override
    public void validate(Object object) throws ValidationException {
        CustomerAddress address = (CustomerAddress) object;
        List<String> attrs = new ArrayList<>();

        if(address.getCity() == null || address.getCity().isEmpty())
            attrs.add("city");
        if(address.getCountry() == null || address.getCountry().isEmpty())
            attrs.add("country");
        if(address.getLineOne() == null || address.getLineOne().isEmpty())
            attrs.add("lineOne");
        if(address.getState() == null || address.getState().isEmpty())
            attrs.add("state");
        if(address.getZipCode() == null || address.getZipCode().isEmpty())
            attrs.add("zipCode");

        if(!attrs.isEmpty()){
            ValidationException validationException = new ValidationException("Invalid address");
            validationException.setErrorCode("val-102");
            validationException.setErrorType("validation");
            validationException.setDescription("Following attributes in address in mandatory : ");
            validationException.setAttributes(attrs);
            throw validationException;
        }
    }
}
