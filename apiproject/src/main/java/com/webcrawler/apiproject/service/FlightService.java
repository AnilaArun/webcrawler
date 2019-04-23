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
    FlightInformationDAO flightInformationDAO;

    @Autowired
    CustomerProfileDAO customerProfileDAO;

    @Autowired
    TravelInformationDAO travelInformationDAO;

    public void setFlightInformationDAO(FlightInformationDAO flightInformationDAO) {
        this.flightInformationDAO = flightInformationDAO;
    }

    public void setCustomerProfileDAO(CustomerProfileDAO customerProfileDAO) {
        this.customerProfileDAO = customerProfileDAO;
    }

    public void setTravelInformationDAO(TravelInformationDAO travelInformationDAO) {
        this.travelInformationDAO = travelInformationDAO;
    }

    public FlightInformation getFlightInformation() {
        FlightInformation flightInformation = new FlightInformation();
        //ResponseEntity<String> response = null; // this can be the response of a restTemplate getForEntity call

        // This method can call a RestWebservice to get the flightInofmation like originCode, destinationCode, price and date etc.
        // I assumed we can call a RESTAPI available in the market for example skyscanner APIs, we can use RestTemplate to get the flightdetails.
        //if we can take the responseBody from the response then we can convert it into FlightInformation object and return that here.

        // I am going to mock this data for the test purpose with random data.
        return flightInformation;
    }

    /**
     * This method can use to update the FlightInformation table with latest Flight deals
     */
    public void save() {
        /*FlightInformation flightInformation = flightService.getFlightInformation();
        //update the flight information and save
        flightInformationDAO.save(flightInformation);*/
    }

    public void updateCustomer() {
        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
        Iterable<CustomerProfile> customerProfiles = customerProfileDAO.findAll();

        // Not performing a null check as flightInformation and customerProfile data are already created at application startup
        flightInformations.forEach((FlightInformation flightInformation)-> {
            customerProfiles.forEach((CustomerProfile customerProfile)-> {
                if ((Location.valueOf(flightInformation.getFlightOriginCode()).getValue().contains(customerProfile.getFlightOrigin())) &&
                        (Location.valueOf(flightInformation.getFlightDestinationCode()).getValue().contains(customerProfile.getFlightDestination())) &&
                        (flightInformation.getFlightDateAndTime().toLocalDate().equals(customerProfile.getTravelDate()))) {

                    List<TravelInformation> travelInformations = travelInformationDAO.findByCustomerId(customerProfile.getId());
                    for (TravelInformation travelInformation : travelInformations) {
                        //this is to compare the
                        int price = Float.compare(travelInformation.getPrice(), flightInformation.getPrice());
                        if (price >= 0 && travelInformation.getCustomerId() == customerProfile.getId()) {
                            sendEmailBasedOnFrequency(customerProfile, travelInformation, flightInformation);
                        }
                    }
                } else {
                    log.info("There are no matched data");
                }
            });
        });
    }

    private void sendEmailBasedOnFrequency(CustomerProfile customerProfile, TravelInformation travelInformation, FlightInformation flightInformation) {
        if (customerProfile.getFrequency().equals(Frequency.DAILY.name())) {
            updateDailyCustomers(customerProfile, travelInformation, flightInformation);
        } else if (customerProfile.getFrequency().equals(Frequency.WEEKLY.name())) {
            updateWeeklyCustomers(customerProfile, travelInformation, flightInformation);
        } else if (customerProfile.getFrequency().equals(Frequency.MONTHLY.name())) {
            updateMonthlyCustomers(customerProfile, travelInformation, flightInformation);
        } else if (customerProfile.getFrequency().equals(Frequency.UNCAPPED.name())) {
            updateUncappedCustomers(customerProfile, travelInformation, flightInformation);
        }
    }

    private void updateUncappedCustomers(CustomerProfile customerProfile, TravelInformation travelInformation , FlightInformation flightInformation) {
        log.info("Inside UNCAPPED if block");
        log.info(" Flight name [{}], flight price [{}] and FliteDateAndTime  [{}] send to [{}]" ,
                flightInformation.getFlightName(), flightInformation.getPrice(), flightInformation.getFlightDateAndTime(), customerProfile.getCustomerEmail());
        try {
            travelInformation.setEmailSendDate(LocalDate.now());
            travelInformationDAO.deleteById(Long.valueOf(travelInformation.getId()));
            travelInformationDAO.save(travelInformation);
        } catch (Exception e) {
            log.error("An Exception occurred : " , e.getMessage());
            throw e;
        }

    }

    private void updateWeeklyCustomers(CustomerProfile customerProfile, TravelInformation travelInformation, FlightInformation flightInformation) {
        try {
            if (travelInformation.getEmailSendDate().equals(LocalDate.now().minusDays(7))) {
                log.info(" Flight name , flight price and time send to " + customerProfile.getCustomerEmail());
                travelInformation.setEmailSendDate(LocalDate.now());
                travelInformationDAO.deleteById(Long.valueOf(travelInformation.getId()));
                travelInformationDAO.save(travelInformation);
            } else {
                log.info("Frequency do not met");
            }
        } catch (Exception e) {
        log.error("An Exception occurred : " , e.getMessage());
        throw e;
    }

    }

    private void updateDailyCustomers(CustomerProfile customerProfile, TravelInformation travelInformation, FlightInformation flightInformation) {
        try {
            if (!travelInformation.getEmailSendDate().equals(LocalDate.now())) {
                travelInformation.setEmailSendDate(LocalDate.now());
                travelInformationDAO.deleteById(Long.valueOf(travelInformation.getId()));
                travelInformationDAO.save(travelInformation);
                log.info(" Flight name [{}], flight price [{}] and FliteDateAndTime  [{}] send to [{}]" ,
                        flightInformation.getFlightName(), flightInformation.getPrice(), flightInformation.getFlightDateAndTime(), customerProfile.getCustomerEmail());
            } else {
                log.info("Email already send");
            }
        } catch (Exception e) {
            log.error("An Exception occurred : " , e.getMessage());
            throw e;
        }
    }

    private void updateMonthlyCustomers(CustomerProfile customerProfile, TravelInformation travelInformation, FlightInformation flightInformation) {
        try {
            if (travelInformation.getEmailSendDate().equals(LocalDate.now().minusMonths(1))) {
                log.info(" Flight name [{}], flight price [{}] and FliteDateAndTime  [{}] send to [{}]" ,
                        flightInformation.getFlightName(), flightInformation.getPrice(), flightInformation.getFlightDateAndTime(), customerProfile.getCustomerEmail());
                travelInformation.setEmailSendDate(LocalDate.now());
                //As there is no update for specific id , deleting it and readding it to avoid duplicates
                travelInformationDAO.deleteById(Long.valueOf(travelInformation.getId()));
                travelInformationDAO.save(travelInformation);
            } else {
                log.info("Frequency do not met");
            }
        } catch (Exception e) {
            log.error("An Exception occurred : " , e.getMessage());
            throw e;
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
        try {
            travelInformationDAO.save(travelInformation);
        } catch (Exception e) {
            log.error("An Exception occurred : " , e.getMessage());
            throw e;
        }
    }

    public void removeOlderData() {
        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
        flightInformations.forEach((FlightInformation flightInformation)-> {
            if (flightInformation.getFlightDateAndTime().isBefore(LocalDateTime.now())) {
                flightInformationDAO.deleteById(Long.valueOf(flightInformation.getId()));
                log.info("Delete the older file information with date : " + flightInformation.getId());
            }
        });

        Iterable<CustomerProfile> customerProfiles = customerProfileDAO.findAll();
        customerProfiles.forEach((CustomerProfile customerProfile)-> {
            if (customerProfile.getTravelDate().isBefore(LocalDate.now())) {
                customerProfileDAO.deleteById(Long.valueOf(customerProfile.getId()));
                log.info("Delete the older customerProfile with date : " + customerProfile.getId());
            }
        });

        Iterable<TravelInformation> travelInformations = travelInformationDAO.findAll();
        travelInformations.forEach((TravelInformation travelInformation)-> {
            if (travelInformation.getTravelDate().isBefore(LocalDate.now())) {
                customerProfileDAO.deleteById(Long.valueOf(travelInformation.getId()));
                log.info("Delete the older travelInformation with date : " + travelInformation.getId());
            }
        });
    }

    public void updateFlightInformation() {
        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
        flightInformations.forEach((FlightInformation flightInformation)-> {
            FlightInformation existedFlightInformation = getFlightInformation();
            if ((flightInformation.getFlightDateAndTime().equals(existedFlightInformation.getFlightDateAndTime())) &&
                    (flightInformation.getFlightOriginCode().equals(existedFlightInformation.getFlightOriginCode())) &&
                    (flightInformation.getFlightDestinationCode().equals(existedFlightInformation.getFlightDestinationCode())) &&
                    (flightInformation.getPrice() < existedFlightInformation.getPrice())) {
                //implement the code to update the travelInformation table
            }

        });

    }
}
