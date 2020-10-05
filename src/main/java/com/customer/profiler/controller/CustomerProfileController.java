package com.customer.profiler.controller;

import com.customer.profiler.exception.ProfilerException;
import com.customer.profiler.service.CustomerProfileService;
import com.customer.profiler.service.models.CustomerProfile;
import com.customer.profiler.service.models.ValidationResponse;
import com.customer.profiler.service.models.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CustomerProfileController {

    @Autowired
    CustomerProfileService customerProfileService;

    @RequestMapping(method = RequestMethod.POST,value="/profile/")
    public ResponseEntity<CustomerProfile> createProfile(@RequestBody  CustomerProfile profile) throws ProfilerException {
        profile = customerProfileService.createProfile(profile);
        ResponseEntity<CustomerProfile> responseEntity = new ResponseEntity<>(profile,HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT, value="/profile/{profileId}")
    public  ResponseEntity<CustomerProfile> updateProfile(@RequestBody CustomerProfile profile,@PathVariable("profileId") Integer profileId) throws ProfilerException {
        profile.setProfileId(profileId);
        profile =  customerProfileService.updateProfile(profile);
        ResponseEntity<CustomerProfile> responseEntity = new ResponseEntity<>(profile,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/profile/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable("profileId") Integer profileId) throws ProfilerException {
        customerProfileService.deleteProfile(profileId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @RequestMapping(method = RequestMethod.GET,value="/profile/{profileId}")
    public  ResponseEntity<CustomerProfile> getProfile(@PathVariable("profileId") Integer profileId) throws ProfilerException {
        CustomerProfile profile = customerProfileService.getProfileById(profileId);
        ResponseEntity<CustomerProfile> responseEntity = new ResponseEntity<>(profile,HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/accounting/")
    public ResponseEntity<ValidationResponse> validateAccounting(@RequestBody CustomerProfile customerProfile){
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setProfileId(String.valueOf(customerProfile.getProfileId()));
        validationResponse.setValidationStatus(ValidationStatus.APPROVED);
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/payments/")
    public ResponseEntity<ValidationResponse> validatioPayments(@RequestBody CustomerProfile customerProfile){
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setProfileId(String.valueOf(customerProfile.getProfileId()));
        validationResponse.setValidationStatus(ValidationStatus.APPROVED);
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/payroll/")
    public ResponseEntity<ValidationResponse> validatePayroll(@RequestBody CustomerProfile customerProfile){
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setProfileId(String.valueOf(customerProfile.getProfileId()));
        validationResponse.setValidationStatus(ValidationStatus.APPROVED);
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/timesheet/")
    public ResponseEntity<ValidationResponse> valdidateTimesheet(@RequestBody CustomerProfile customerProfile){
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setProfileId(String.valueOf(customerProfile.getProfileId()));
        validationResponse.setValidationStatus(ValidationStatus.APPROVED);
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);
    }
}
