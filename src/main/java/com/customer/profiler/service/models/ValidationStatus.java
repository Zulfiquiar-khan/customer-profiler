package com.customer.profiler.service.models;

import java.io.Serializable;

public enum ValidationStatus implements Serializable {

    IN_PROGRESS(1),
    APPROVED(2),
    REJECTED(3),
    FAILED(4),
    CREATED(5);

    private static final long serialVersionUID = 7156526077883281628L;

    private int code;

    ValidationStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
