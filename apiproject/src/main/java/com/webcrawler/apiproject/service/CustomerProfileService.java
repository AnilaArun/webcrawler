package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.domain.CustomerProfile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class CustomerProfileService {
    @PersistenceContext(unitName = "customer-profile")
    private EntityManager entityManager;
    public void addCustomerProfile(CustomerProfile customerProfile) {
        entityManager.persist(customerProfile);
    }
    public List<CustomerProfile> getAllCustomerProfiles() {
        CriteriaQuery<CustomerProfile> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(CustomerProfile.class);
        criteriaQuery.select(criteriaQuery.from(CustomerProfile.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    public void clear() {
        Query removeAll = entityManager.createQuery("delete from CustomerProfile");
        removeAll.executeUpdate();
    }
}
