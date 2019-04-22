package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.FlightInformationDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class FlightInformationUpdateService {

    @Autowired
    private FlightInformationDAO flightInformationDAO;

    private FlightService flightService;

    public FlightInformationUpdateService(FlightService flightService) {
        this.flightService = flightService;
    }

    public void saveFlightInformation(FlightInformation flightInformation) {
        flightInformationDAO.save(flightInformation);
    }

    /*public void updateFlightInformation(FlightInformation flightInformation) {
        flightInformationDAO.updateFlightInformation(flightInformation);
    }*/

    public void removeOlderFlightInfomation() {
        Iterable<FlightInformation> flightInformationIterable = getAllFlightInformation();
        if (flightInformationIterable != null) {
            for (FlightInformation flightInformation : flightInformationIterable) {
                if (flightInformation.getFlightDateAndTime().isBefore(LocalDateTime.now().minusMinutes(1))) {
                    log.info("Removing flight number" + flightInformation.getFlightNumber() +
                            "for date :" + flightInformation.getFlightDateAndTime());
                    flightInformationDAO.deleteById((long) flightInformation.getId());
                }
            }
        }

    }

    public Iterable<FlightInformation> getAllFlightInformation() {
        return flightInformationDAO.findAll();
    }

    public void save() {
        FlightInformation flightInformation = flightService.getFlightInformation();
        flightInformationDAO.save(flightInformation);
    }

    public void customerUpdate() {
        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
        flightService.updateCustomer(flightInformations);
    }

    public void delete() {
        flightService.delete(flightInformationDAO);
    }
}
