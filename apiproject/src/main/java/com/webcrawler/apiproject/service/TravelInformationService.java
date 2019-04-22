package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.TravelInformationDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import com.webcrawler.apiproject.domain.TravelInformation;
import com.webcrawler.apiproject.enums.Location;
import com.webcrawler.apiproject.util.TravelInformationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TravelInformationService implements TravelInformationDAO {

    @Autowired
    TravelInformationDAO travelInformationDAO;

    @Override
    public List<TravelInformation> findByCustomerId(int customerId) {
        return travelInformationDAO.findByCustomerId(customerId);
    }

    @Override
    public List<TravelInformation> findByFlightInformationId(String flightInformationId) {
        return travelInformationDAO.findByFlightInformationId(flightInformationId);
    }

    @Override
    public TravelInformation save(TravelInformation travelInformation) {
        return travelInformationDAO.save(travelInformation);
    }

    @Override
    public <S extends TravelInformation> Iterable<S> saveAll(Iterable<S> iterable) {
        return travelInformationDAO.saveAll(iterable);
    }

    @Override
    public Optional<TravelInformation> findById(Long aLong) {
        return travelInformationDAO.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return travelInformationDAO.existsById(aLong);
    }

    @Override
    public Iterable<TravelInformation> findAll() {
        return travelInformationDAO.findAll();
    }

    @Override
    public Iterable<TravelInformation> findAllById(Iterable<Long> iterable) {
        return travelInformationDAO.findAllById(iterable);
    }

    @Override
    public long count() {
        return travelInformationDAO.count();
    }

    @Override
    public void deleteById(Long aLong) {
        travelInformationDAO.deleteById(aLong);
    }

    @Override
    public void delete(TravelInformation travelInformation) {
        travelInformationDAO.delete(travelInformation);
    }

    @Override
    public void deleteAll(Iterable<? extends TravelInformation> iterable) {
        travelInformationDAO.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        travelInformationDAO.deleteAll();
    }

    public void updateWithCustomerAndFlightData(CustomerProfile customerProfile, FlightInformationService flightInformationService) {
        Iterable<FlightInformation> flightInformations = flightInformationService.findAll();
        flightInformations.forEach((FlightInformation flightInformation)-> {
            if ((Location.valueOf(flightInformation.getFlightOriginCode()).getValue().contains(customerProfile.getFlightOrigin())) &&
                    (Location.valueOf(flightInformation.getFlightDestinationCode()).getValue().contains(customerProfile.getFlightDestination()))) {
                TravelInformationUtil.setAndSaveTravelInformationData(travelInformationDAO, customerProfile, flightInformation);
                log.info("Sending the first email to : " + customerProfile.getFirstName() + " " + customerProfile.getLastName());
            }
        });
    }
}