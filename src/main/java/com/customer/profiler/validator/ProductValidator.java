package com.customer.profiler.validator;

import com.customer.profiler.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator implements IValidator {
    @Override
    public void validate(Object object) throws ValidationException {

    }
}
