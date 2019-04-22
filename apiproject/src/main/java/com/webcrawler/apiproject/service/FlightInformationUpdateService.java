//package com.webcrawler.apiproject.service;
//
//import com.webcrawler.apiproject.dao.FlightInformationDAO;
//import com.webcrawler.apiproject.domain.FlightInformation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@Slf4j
//public class FlightInformationUpdateService {
//
//    @Autowired
//    private FlightInformationDAO flightInformationDAO;
//
//    private FlightService flightService;
//
//    public FlightInformationUpdateService(FlightService flightService) {
//        this.flightService = flightService;
//    }
//
//    public void updateFlightInformation() {
//        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
//        flightService.updateFlightInformation(flightInformations);
//    }
//
//    public void removeOlderFlightInfomation() {
//        Iterable<FlightInformation> flightInformationIterable = getAllFlightInformation();
//        if (flightInformationIterable != null) {
//            for (FlightInformation flightInformation : flightInformationIterable) {
//                if (flightInformation.getFlightDateAndTime().isBefore(LocalDateTime.now().minusMinutes(1))) {
//                    log.info("Removing flight number" + flightInformation.getFlightNumber() +
//                            "for date :" + flightInformation.getFlightDateAndTime());
//                    flightInformationDAO.deleteById((long) flightInformation.getId());
//                }
//            }
//        }
//
////    }a
//
//    public Iterable<FlightInformation> getAllFlightInformation() {
//        return flightInformationDAO.findAll();
//    }
//
//    /**
//     * This method can use to update the FlightInformation table with latest Flight deals
//     */
//    public void save() {
//        /*FlightInformation flightInformation = flightService.getFlightInformation();
//        //update the flight information and save
//        flightInformationDAO.save(flightInformation);*/
//    }
//
//    /**
//     * This methods is to send the email to the customer
//     */
//    public void customerUpdate() {
//        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
//        flightService.updateCustomer(flightInformations);
//    }
//
//    public void delete() {
//        flightService.removeOlderData(flightInformationDAO);
//    }
//}
