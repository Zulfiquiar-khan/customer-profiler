package com.customer.profiler.service;

import com.customer.profiler.dao.models.Address;
import com.customer.profiler.dao.models.Profile;
import com.customer.profiler.dao.repo.ProfileRepository;
import com.customer.profiler.exception.ProfilerException;
import com.customer.profiler.mapper.ObjectMapper;
import com.customer.profiler.messaging.RabbitMQSender;
import com.customer.profiler.service.models.CustomerProfile;
import com.customer.profiler.service.models.ValidationStatus;
import com.customer.profiler.validator.IValidator;
import com.customer.profiler.validator.factory.IValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class CustomerProfileService implements ICustomerProfileService {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerProfileService.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IValidatorFactory validatorFactory;

    @Autowired
    private RabbitMQSender rabbitMQSender;


    @Override
    public CustomerProfile createProfile(CustomerProfile customerProfile) throws ProfilerException {
        LOGGER.info("Creating profile for customer");
        IValidator validator = validatorFactory.getValidator(customerProfile);
        validator.validate(customerProfile);
        Optional<Profile> fetchedProfile = profileRepository.findByCustomerId(customerProfile.getCustomerId());
        if(fetchedProfile.isPresent()){
            if(fetchedProfile.get().getDeleteDate()==null) {
                ProfilerException exception = new ProfilerException("Customer already has a profile");
                exception.setErrorType("resource error");
                exception.setErrorCode("res-204");
                exception.setDescription("Only one profile for each customer is allowed");
                throw exception;
            }else{
                Profile oldProfile = fetchedProfile.get();
                Profile newProfile = this.objectMapper.map(customerProfile);
                mergeProfile(oldProfile,newProfile);
                oldProfile.setValidationStatus(ValidationStatus.CREATED);
                oldProfile.setDeleteDate(null);
                this.profileRepository.save(oldProfile);
                this.rabbitMQSender.send(this.objectMapper.map(oldProfile));
                return this.objectMapper.map(oldProfile);
            }
        }else {
            customerProfile.setValidationStatus(ValidationStatus.CREATED);
            customerProfile.setDeleteDate(null);
            customerProfile.getBusinessAddress().setAddressId(null);
            customerProfile.getLegalAddress().setAddressId(null);
            return this.save(customerProfile, true);
        }
    }

    @Override
    @CachePut(value = "profiles",key = "#customerProfile.profileId")
    public CustomerProfile updateProfile(CustomerProfile customerProfile) throws ProfilerException{
        LOGGER.info("Updating Customer profile");
        IValidator validator = validatorFactory.getValidator(customerProfile);
        validator.validate(customerProfile);
        Optional<Profile> fetchedProfile = profileRepository.findById(customerProfile.getProfileId());
        if(!fetchedProfile.isPresent()){
            ProfilerException exception = new ProfilerException("Profile does not exists");
            exception.setErrorType("resource error");
            exception.setErrorCode("res-204");
            exception.setDescription("Only avaialble profiles can be updated");
            throw exception;
        }
        if(fetchedProfile.get().getCustomerId()!= customerProfile.getCustomerId()){
            ProfilerException exception = new ProfilerException("CustomerId does not match");
            exception.setErrorType("business error");
            exception.setErrorCode("bus-204");
            exception.setDescription("Transfer of profile to different customer is not allowed");
            throw exception;
        }
        return this.save(customerProfile,true);
    }

    @Override
    @Cacheable(value = "profiles",key = "#profileId")
    public CustomerProfile getProfileById(Integer profileId) throws ProfilerException{
            LOGGER.info("Fetching Customer profile");
            IValidator validator = validatorFactory.getValidator(profileId);
            validator.validate(profileId);
            Optional<Profile> fetchedProfile = profileRepository.findById(profileId);
            if(!fetchedProfile.isPresent()){
                ProfilerException profilerException = new ProfilerException("Profile is not present for profileId : " + profileId);
                profilerException.setDescription("Profile you are trying to access does not exists. Please pass a valid profile");
                profilerException.setErrorCode("res-100");
                profilerException.setErrorType("resource error");
                throw profilerException;
            }
            Profile profile = fetchedProfile.get();
            CustomerProfile customerProfile = this.objectMapper.map(profile);
            LOGGER.info("Customer profile fetched");
            return customerProfile;
    }

    @Override
    @CacheEvict(value = "profiles",key = "#profileId")
    public void deleteProfile(Integer profileId) throws ProfilerException{
        LOGGER.info("Deleting Customer profile");
        Optional<Profile> fetchedProfile = profileRepository.findById(profileId);
        if(!fetchedProfile.isPresent()){
            LOGGER.info("Customer profile does not exists");
            ProfilerException exception = new ProfilerException("Profile does not exists");
            exception.setErrorType("resource error");
            exception.setErrorCode("res-204");
            exception.setDescription("Only avaialble profiles can be deleted");
            throw exception;
        }
        Profile profile = fetchedProfile.get();
        if(profile.getDeleteDate()!=null){
            LOGGER.info("Customer profile already deactivated");
            ProfilerException exception = new ProfilerException("Profile is already deleted");
            exception.setErrorType("resource error");
            exception.setErrorCode("res-204");
            exception.setDescription("Profile is already deleted");
            throw exception;
        }
        profile.setDeleteDate(new Date());
        profile = this.profileRepository.save(profile);
        LOGGER.info("Customer profile deleted");
    }

    private CustomerProfile save(CustomerProfile customerProfile,boolean generateEvent){
        Profile profile = this.objectMapper.map(customerProfile);
        profile = this.profileRepository.save(profile);
        customerProfile = this.objectMapper.map(profile);
        if(generateEvent){
            this.rabbitMQSender.send(customerProfile);
        }
        return customerProfile;
    }

    private void mergeProfile(Profile oldProfile, Profile newProfile) {
        mergeAddress(oldProfile.getBusinessAddress(),newProfile.getBusinessAddress());
        mergeAddress(oldProfile.getLegalAddress(),newProfile.getLegalAddress());
        oldProfile.setDeleteDate(null);
        oldProfile.setValidationStatus(ValidationStatus.CREATED);
        oldProfile.setCompanyName(newProfile.getCompanyName());
        oldProfile.setEmail(newProfile.getEmail());
        oldProfile.setFromProduct(newProfile.getFromProduct());
        oldProfile.setLegalName(newProfile.getLegalName());
        oldProfile.setTaxIdentifier(newProfile.getTaxIdentifier());
        oldProfile.setTaxIdentifierType(newProfile.getTaxIdentifierType());
        oldProfile.setWebsiteLink(newProfile.getWebsiteLink());
    }

    private void mergeAddress(Address oldAddress, Address newAddress){
        if(null!=newAddress.getAddressId()){
            oldAddress.setCity(newAddress.getCity());
            oldAddress.setCountry(newAddress.getCountry());
            oldAddress.setLineOne(newAddress.getLineOne());
            oldAddress.setLineTwo(newAddress.getLineTwo());
            oldAddress.setState(newAddress.getState());
            oldAddress.setZipCode(newAddress.getZipCode());
        }
    }
}
