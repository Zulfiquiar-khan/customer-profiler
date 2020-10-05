package com.customer.profiler.validator;

import com.customer.profiler.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ProfileIdValidator implements IValidator {
    @Override
    public void validate(Object profileId) throws ValidationException {
        ValidationException validationException = null;

        if(null == profileId){
            validationException = new ValidationException("Inavlid profile id");
            validationException.setErrorCode("val-100");
            validationException.setErrorType("validation");
            validationException.setDescription("ProfileId cannot be empty, Pass a valid value for profileId : "+profileId);
            throw validationException;
        }

        String id = profileId.toString();

        try {
            Integer.parseInt(id);
        }catch(NumberFormatException exception){
            validationException = new ValidationException("Inavlid profile id");
            validationException.setErrorCode("val-101");
            validationException.setErrorType("validation");
            validationException.setDescription("ProfileId should be a number, Pass a valid value for profileId : "+profileId);
            throw validationException;
        }
    }
}
