package com.webcrawler.apiproject.dao;

import com.webcrawler.apiproject.domain.FlightInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightInformationDAO extends CrudRepository<FlightInformation, Long> {

    public List<FlightInformation> findByFlightDestinationCode(String flightDestinationCode);

    public List<FlightInformation> findByFlightOriginCode(String flightOriginCode);

}