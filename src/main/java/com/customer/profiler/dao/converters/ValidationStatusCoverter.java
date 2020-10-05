package com.customer.profiler.dao.converters;

import com.customer.profiler.service.models.ValidationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ValidationStatusCoverter implements AttributeConverter<ValidationStatus,Integer> {

    @Override
    public Integer convertToDatabaseColumn(ValidationStatus validationStatus) {
        return validationStatus.getCode();
    }

    @Override
    public ValidationStatus convertToEntityAttribute(Integer code) {
        for(ValidationStatus status:ValidationStatus.values()){
            if(status.getCode() == code){
                return status;
            }
        }
        return ValidationStatus.IN_PROGRESS;
    }
}
