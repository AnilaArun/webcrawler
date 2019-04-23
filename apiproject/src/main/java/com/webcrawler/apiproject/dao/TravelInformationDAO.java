package com.webcrawler.apiproject.dao;

import com.webcrawler.apiproject.domain.TravelInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelInformationDAO extends CrudRepository<TravelInformation, Long> {

    public List<TravelInformation> findByCustomerId(long customerId);

    public List<TravelInformation> findByFlightInformationId(String flightInformationId);
}
