package com.customer.profiler.service;

import com.customer.profiler.exception.ProfilerException;
import com.customer.profiler.service.models.CustomerProfile;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface ICustomerProfileService {
    CustomerProfile createProfile(CustomerProfile customerProfile) throws ProfilerException;

    @CachePut(value = "profiles",key = "#customerProfile.profileId")
    CustomerProfile updateProfile(CustomerProfile customerProfile) throws ProfilerException;

    @Cacheable(value = "profiles",key = "#profileId")
    CustomerProfile getProfileById(Integer profileId) throws ProfilerException;

    @CacheEvict(value = "profiles",key = "#profileId")
    void deleteProfile(Integer profileId) throws ProfilerException;
}
