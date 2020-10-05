package com.customer.profiler.validator.factory;

import com.customer.profiler.service.models.CompanyProduct;
import com.customer.profiler.service.models.CustomerAddress;
import com.customer.profiler.service.models.CustomerProfile;
import com.customer.profiler.validator.*;
import org.springframework.stereotype.Service;

@Service
public class ValidatorFactoryImpl implements IValidatorFactory {

    @Override
    public IValidator getValidator(Object object) {
        IValidator validator = null;
        if(object != null){
            Class objectClass = object.getClass();
            if(CustomerProfile.class.equals(objectClass))
                validator = new ProfileValidator(new AddressValidator());
            else if(CustomerAddress.class.equals(objectClass))
                validator = new AddressValidator();
            else if(CompanyProduct.class.equals(objectClass))
                validator = new ProductValidator();
            else if(Integer.class.equals(objectClass))
                validator = new ProfileIdValidator();
        }
        return validator;
    }
}
