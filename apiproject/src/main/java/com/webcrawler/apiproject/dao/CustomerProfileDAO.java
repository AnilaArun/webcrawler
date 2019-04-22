package com.webcrawler.apiproject.dao;

import com.webcrawler.apiproject.domain.CustomerProfile;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface CustomerProfileDAO extends CrudRepository<CustomerProfile, Long> {

    //public void save()
    public CustomerProfile findByCustomerEmail(String customerEmail);

    public List<CustomerProfile> findByFrequency(String frequency);
}