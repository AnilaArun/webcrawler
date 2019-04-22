package com.webcrawler.apiproject.dao;

import com.webcrawler.apiproject.domain.TravelInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelInformationDAO extends CrudRepository<TravelInformation, Long> {

    //public void save()
    public List<TravelInformation> findByCustomerId(int customerId);

    public List<TravelInformation> findByFlightInformationId(String flightInformationId);
    //public TravelInformation saveId(int travelInformationId);
}
