package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerProfileService implements CustomerProfileDAO {

    @Autowired
    private CustomerProfileDAO customerProfileDAO;

    @Override
    public CustomerProfile save(CustomerProfile customerProfile) {
        if (customerProfile != null) {
            customerProfileDAO.save(customerProfile);
        }
        return customerProfile;
    }

    @Override
    public <S extends CustomerProfile> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CustomerProfile> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<CustomerProfile> findAll() {
        return null;
    }

    @Override
    public Iterable<CustomerProfile> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CustomerProfile customerProfile) {

    }

    @Override
    public void deleteAll(Iterable<? extends CustomerProfile> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CustomerProfile> findByCustomerEmail(String customerEmail) {
        return null;
    }

    @Override
    public List<CustomerProfile> findByFrequency(String frequency) {
        return null;
    }
}