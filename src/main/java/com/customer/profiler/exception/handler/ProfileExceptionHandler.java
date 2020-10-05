package com.customer.profiler.exception.handler;

import com.customer.profiler.exception.ProfilerException;
import com.customer.profiler.exception.ValidationException;
import com.customer.profiler.service.models.ProfilerError;
import com.customer.profiler.service.models.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProfileExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProfilerException.class})
    protected ResponseEntity<Object> handleProfilerException(ProfilerException ex, WebRequest request) {
        ProfilerError profilerError = new ProfilerError();
        profilerError.setDescription(ex.getDescription());
        profilerError.setErrorCode(ex.getErrorCode());
        profilerError.setMessage(ex.getMessage());
        profilerError.setErrorType(ex.getErrorType());
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        if(profilerError.getErrorCode().equalsIgnoreCase("res-100"))
            httpStatus = HttpStatus.NOT_FOUND;
        else
            httpStatus = HttpStatus.BAD_REQUEST;

        return handleExceptionInternal(ex,profilerError , new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(value = {RuntimeException.class,Exception.class})
    protected ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ProfilerError profilerError = new ProfilerError();
        profilerError.setDescription("Internal error occured");
        profilerError.setErrorCode("unk-100");
        profilerError.setMessage(ex.getMessage());
        profilerError.setErrorType("unknown");
        return handleExceptionInternal(ex,profilerError , new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleValidationException(ValidationException ex,WebRequest request){
        ValidationError validationError = new ValidationError();
        validationError.setDescription(ex.getDescription());
        validationError.setErrorCode(ex.getErrorCode());
        validationError.setMessage(ex.getMessage());
        validationError.setErrorType(ex.getErrorType());
        validationError.setAttributes(ex.getAttributes());
        return handleExceptionInternal(ex,validationError , new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
