package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is to update, retrieve or delete the CustomerProfile data from the CustomerProfile DB
 */
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
        return customerProfileDAO.saveAll(iterable);
    }

    @Override
    public Optional<CustomerProfile> findById(Long aLong) {
        return customerProfileDAO.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return customerProfileDAO.existsById(aLong);
    }

    @Override
    public Iterable<CustomerProfile> findAll() {
        return customerProfileDAO.findAll();
    }

    @Override
    public Iterable<CustomerProfile> findAllById(Iterable<Long> iterable) {
        return customerProfileDAO.findAllById(iterable);
    }

    @Override
    public long count() {
        return customerProfileDAO.count();
    }

    @Override
    public void deleteById(Long aLong) {
        customerProfileDAO.deleteById(aLong);
    }

    @Override
    public void delete(CustomerProfile customerProfile) {
        customerProfileDAO.delete(customerProfile);
    }

    @Override
    public void deleteAll(Iterable<? extends CustomerProfile> iterable) {
        customerProfileDAO.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        customerProfileDAO.deleteAll();
    }

    @Override
    public CustomerProfile findByCustomerEmail(String customerEmail) {
        return customerProfileDAO.findByCustomerEmail(customerEmail);
    }

    @Override
    public List<CustomerProfile> findByFrequency(String frequency) {
        return customerProfileDAO.findByFrequency(frequency);
    }
}