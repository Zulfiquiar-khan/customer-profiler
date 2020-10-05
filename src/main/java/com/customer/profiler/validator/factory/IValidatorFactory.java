package com.customer.profiler.validator.factory;

import com.customer.profiler.validator.IValidator;

public interface IValidatorFactory {
    public IValidator getValidator(Object object);
}
