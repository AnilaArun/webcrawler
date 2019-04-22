package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.FlightInformationDAO;
import com.webcrawler.apiproject.domain.FlightInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightInformationService implements FlightInformationDAO {

    @Autowired
    private FlightInformationDAO flightInformationDAO;

    //private FlightInformation flightInformation;
    /*public FlightInformationService(FlightInformation flightInformation) {
        this.flightInformation = flightInformation;
    }*/

    @Override
    public FlightInformation save(FlightInformation flightInformation) {
        if (flightInformation != null) {
            flightInformationDAO.save(flightInformation);
        }
        return flightInformation;
    }

    @Override
    public <S extends FlightInformation> Iterable<S> saveAll(Iterable<S> iterable) {
        return flightInformationDAO.saveAll(iterable);
    }

    @Override
    public List<FlightInformation> findByFlightDestinationCode(String flightDestinationCode) {
        return flightInformationDAO.findByFlightDestinationCode(flightDestinationCode);
    }

    @Override
    public List<FlightInformation> findByFlightOriginCode(String flightOriginCode) {
        return flightInformationDAO.findByFlightOriginCode(flightOriginCode);
    }

    /*@Override
    public void updateFlightInformation(FlightInformation flightInformation) {

    }*/

    @Override
    public Optional<FlightInformation> findById(Long aLong) {
        return flightInformationDAO.findById(aLong);
    }

    @Override
    public boolean existsById(Long flightInfomationId) {
        return flightInformationDAO.existsById(flightInfomationId);
    }

    @Override
    public Iterable<FlightInformation> findAll() {
        return flightInformationDAO.findAll();
    }

    @Override
    public Iterable<FlightInformation> findAllById(Iterable<Long> iterable) {
        return flightInformationDAO.findAllById(iterable);
    }

    @Override
    public long count() {
        return flightInformationDAO.count();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(FlightInformation flightInformation) {
        flightInformationDAO.delete(flightInformation);
    }

    @Override
    public void deleteAll(Iterable<? extends FlightInformation> iterable) {
        flightInformationDAO.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        flightInformationDAO.deleteAll();
    }
}
