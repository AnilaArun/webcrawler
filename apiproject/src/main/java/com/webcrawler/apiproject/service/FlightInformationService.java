package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.dao.FlightInformationDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FlightInformationService implements FlightInformationDAO {

    @Autowired
    private FlightInformationDAO flightInformationDAO;

    @Override
    public FlightInformation save(FlightInformation flightInformation) {
        if (flightInformation != null) {
            flightInformationDAO.save(flightInformation);
        }
        return flightInformation;
    }

    @Override
    public <S extends FlightInformation> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }
    @Override
    public List<FlightInformation> findByFlightDestinationCode(String flightDestinationCode) {
        return null;
    }

    @Override
    public List<FlightInformation> findByFlightOriginCode(String flightOriginCode) {
        return null;
    }

    @Override
    public Optional<FlightInformation> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<FlightInformation> findAll() {
        return null;
    }

    @Override
    public Iterable<FlightInformation> findAllById(Iterable<Long> iterable) {
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
    public void delete(FlightInformation flightInformation) {

    }

    @Override
    public void deleteAll(Iterable<? extends FlightInformation> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
