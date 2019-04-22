package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.dao.FlightInformationDAO;
import com.webcrawler.apiproject.dao.TravelInformationDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import com.webcrawler.apiproject.domain.TravelInformation;
import com.webcrawler.apiproject.enums.Frequency;
import com.webcrawler.apiproject.enums.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FlightService {
    @Autowired
    CustomerProfileDAO customerProfileDAO;

    @Autowired
    TravelInformationDAO travelInformationDAO;

    public FlightInformation getFlightInformation() {
        FlightInformation flightInformation = new FlightInformation();
        //ResponseEntity<String> response = null; // this can be the response of a restTemplate getForEntity call

        // This method can call a RestWebservice to get the flightInofmation like originCode, destinationCode, price and date etc.
        // I assumed we can call a RESTAPI available in the market for example skyscanner APIs, we can use RestTemplate to get the flightdetails.
        //if we can take the responseBody from the response then we can convert it into FlightInformation object and return that here.

        // I am going to mock this data for the test purpose with random data.
        return flightInformation;
    }

    public void updateCustomer(Iterable<FlightInformation> flightInformations) {
        Iterable<CustomerProfile> customerProfiles = customerProfileDAO.findAll();

        flightInformations.forEach((FlightInformation flightInformation)-> {
            customerProfiles.forEach((CustomerProfile customerProfile)-> {
                if ((Location.valueOf(flightInformation.getFlightOriginCode()).getValue().contains(customerProfile.getFlightOrigin())) &&
                        (Location.valueOf(flightInformation.getFlightDestinationCode()).getValue().contains(customerProfile.getFlightDestination()))) {

                    List<TravelInformation> travelInformations = travelInformationDAO.findByCustomerId(customerProfile.getId());

                    if (travelInformations.isEmpty()) {
                        populateTravelInformationData(flightInformation, customerProfile);
                        log.info("TravelInformation data is null so adding flightInformation and Customer Information");
                        log.info("Also sending an email to the customer with current best deal");
                    } else {
                        //if (flightInformation.getPrice() <=
                        travelInformations.forEach((TravelInformation travelInformation) -> {
                            if (!travelInformation.isSendEmail()) {
                                populateTravelInformationData(flightInformation, customerProfile);
                                log.info(" Flight name , flight price and time send to " + customerProfile.getCustomerEmail());
                            } else if (travelInformation.getPrice() >= flightInformation.getPrice() && travelInformation.getCustomerId() == customerProfile.getId()) {
                                sendEmailBasedOnFrequency(customerProfile, travelInformation);
                            }
                        });
                    }
                } else {
                    log.info("There are no matched data");
                }
            });
        });
    }

    private void sendEmailBasedOnFrequency(CustomerProfile customerProfile, TravelInformation travelInformation) {
        if (customerProfile.getFrequency().equals(Frequency.DAILY)) {
            updateDailyCustomers(customerProfile, travelInformation);
        } else if (customerProfile.getFrequency().equals(Frequency.WEEKLY)) {
            updateWeeklyCustomers(customerProfile, travelInformation);
        } else if (customerProfile.getFrequency().equals(Frequency.MONTHLY)) {
            updateMonthlyCustomers(customerProfile, travelInformation);
        } else if (customerProfile.getFrequency().equals(Frequency.UNCAPPED)) {
            updateUncappedCustomers(customerProfile, travelInformation);
        }
    }

    private void updateUncappedCustomers(CustomerProfile customerProfile, TravelInformation travelInformation) {
        log.info("Inside UNCAPPED if block");
        log.info(" Flight name , flight price and time send to " + customerProfile.getCustomerEmail());
        travelInformation.setEmailSendDate(LocalDate.now());
        travelInformationDAO.deleteById((long) travelInformation.getId());
        travelInformationDAO.save(travelInformation);
    }

    private void updateWeeklyCustomers(CustomerProfile customerProfile, TravelInformation travelInformation) {
        if (travelInformation.getEmailSendDate().equals(LocalDate.now().minusDays(7))) {
            log.info(" Flight name , flight price and time send to " + customerProfile.getCustomerEmail());
            travelInformation.setEmailSendDate(LocalDate.now());
            travelInformationDAO.deleteById((long) travelInformation.getId());
            travelInformationDAO.save(travelInformation);
        } else {
            log.info("Frequency do not met");
        }
    }

    private void updateDailyCustomers(CustomerProfile customerProfile, TravelInformation travelInformation) {
        if (!travelInformation.getEmailSendDate().equals(LocalDate.now())) {
            travelInformation.setEmailSendDate(LocalDate.now());
            travelInformationDAO.deleteById((long) travelInformation.getId());
            travelInformationDAO.save(travelInformation);
            log.info(" Flight name , flight price and time send to " + customerProfile.getCustomerEmail());
        } else {
            log.info("Email already send");
        }
    }

    private void updateMonthlyCustomers(CustomerProfile customerProfile, TravelInformation travelInformation) {
        if (travelInformation.getEmailSendDate().equals(LocalDate.now().minusMonths(1))) {
            log.info(" Flight name , flight price and time send to " + customerProfile.getCustomerEmail());
            travelInformation.setEmailSendDate(LocalDate.now());
            travelInformationDAO.deleteById((long) travelInformation.getId());
            travelInformationDAO.save(travelInformation);
        } else {
            log.info("Frequency do not met");
        }
    }

    private void populateTravelInformationData(FlightInformation flightInformation, CustomerProfile customerProfile) {
        TravelInformation travelInformation = new TravelInformation();
        travelInformation.setCustomerId(customerProfile.getId());
        travelInformation.setFlightInformationId(flightInformation.getId());
        travelInformation.setFlightDestination(customerProfile.getFlightDestination());
        travelInformation.setFlightOrigin(customerProfile.getFlightOrigin());
        travelInformation.setEmailSendDate(LocalDate.now());
        travelInformation.setSendEmail(true);
        travelInformation.setPrice(flightInformation.getPrice());
        travelInformation.setCreatedBy("System");
        travelInformation.setCreatedDate(LocalDateTime.now());
        travelInformationDAO.save(travelInformation);
    }

    public void delete(FlightInformationDAO flightInformationDAO) {
        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
        flightInformations.forEach((FlightInformation flightInformation)-> {
            if (flightInformation.getFlightDateAndTime().isBefore(LocalDateTime.now())) {
                flightInformationDAO.deleteById((long) flightInformation.getId());
                /*TravelInformation travelInformation = travelInformationDAO.
                travelInformationDAO.deleteById(fl);*//*TravelInformation travelInformation = travelInformationDAO.
                travelInformationDAO.deleteById(fl);*/

            }
        });


    }
}
