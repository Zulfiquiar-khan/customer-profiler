package com.customer.profiler.validator;

import com.customer.profiler.exception.ValidationException;

public interface IValidator {
    public void validate(Object object) throws ValidationException;
}
