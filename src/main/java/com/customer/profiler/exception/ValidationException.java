package com.customer.profiler.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends ProfilerException{

    private List<String> attributes;

    public ValidationException(String message) {
        super(message);
        this.attributes = new ArrayList<>();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        this.attributes = new ArrayList<>();
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(String attribute){
        this.attributes.add(attribute);
    }
}
