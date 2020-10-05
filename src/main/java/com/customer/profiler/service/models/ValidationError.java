package com.customer.profiler.service.models;

import java.util.List;

public class ValidationError extends ProfilerError {

    private List<String> attributes;

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
}
