package com.webcrawler.apiproject.init;

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
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class to create the H2 DB with the mocked CustomerProfile, FlightInformation and TravelInformation data.
 */
@Component
@Slf4j
public class DataInit implements ApplicationRunner {

    private CustomerProfileDAO customerProfileDAO;
    private FlightInformationDAO flightInformationDAO;
    private TravelInformationDAO travelInformationDAO;

    @Autowired
    public DataInit(CustomerProfileDAO customerProfileDAO, FlightInformationDAO flightInformationDAO,
                    TravelInformationDAO travelInformationDAO) {
        this.customerProfileDAO = customerProfileDAO;
        this.flightInformationDAO = flightInformationDAO;
        this.travelInformationDAO = travelInformationDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = customerProfileDAO.count();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (count == 0) {
            FlightInformation flightInformation = new FlightInformation();
            FlightInformation secondFlightInformation = new FlightInformation();
            FlightInformation thirdFlightInformation = new FlightInformation();
            FlightInformation fourthFlightInformation = new FlightInformation();

            flightInformation.setFlightName("American Airlines");
            flightInformation.setFlightOriginCode(Location.BHD.name());
            flightInformation.setFlightDestinationCode(Location.LHR.name());
            flightInformation.setFlightDateAndTime(LocalDateTime.of(2019, 05, 15, 8, 30));
            flightInformation.setCreatedBy("System");
            flightInformation.setModifiedBy(null);
            flightInformation.setCreatedDate(LocalDateTime.now());
            flightInformation.setModifiedDate(null);
            flightInformation.setPrice(50.45f);
            flightInformation.setFlightNumber("AA 123");
            flightInformationDAO.save(flightInformation);


            secondFlightInformation.setFlightName("Air Canada");
            secondFlightInformation.setFlightNumber("AC 164");
            secondFlightInformation.setFlightOriginCode(Location.LHR.name());
            secondFlightInformation.setFlightDestinationCode(Location.BOS.name());
            secondFlightInformation.setFlightDateAndTime(LocalDateTime.of(2019, 06, 25, 13, 40));
            secondFlightInformation.setCreatedBy("System");
            secondFlightInformation.setModifiedBy(null);
            secondFlightInformation.setCreatedDate(LocalDateTime.now());
            secondFlightInformation.setModifiedDate(null);
            secondFlightInformation.setPrice(475.55f);
            flightInformationDAO.save(secondFlightInformation);

            thirdFlightInformation.setFlightName("British Airways");
            thirdFlightInformation.setFlightNumber("AA 123");
            thirdFlightInformation.setFlightOriginCode(Location.COK.name());
            thirdFlightInformation.setFlightDestinationCode(Location.BWI.name());
            thirdFlightInformation.setFlightDateAndTime(LocalDateTime.of(2019, 07, 15, 07, 40));
            thirdFlightInformation.setCreatedBy("System");
            thirdFlightInformation.setModifiedBy(null);
            thirdFlightInformation.setCreatedDate(LocalDateTime.now());
            thirdFlightInformation.setModifiedDate(null);
            thirdFlightInformation.setPrice(345.75f);
            flightInformationDAO.save(thirdFlightInformation);

            fourthFlightInformation.setFlightName("Emirates");
            fourthFlightInformation.setFlightNumber("AA 123");
            fourthFlightInformation.setFlightOriginCode(Location.DXB.name());
            fourthFlightInformation.setFlightDestinationCode(Location.DFW.name());
            fourthFlightInformation.setFlightDateAndTime(LocalDateTime.of(2019, 10, 27, 21, 30));
            fourthFlightInformation.setCreatedBy("System");
            fourthFlightInformation.setModifiedBy(null);
            fourthFlightInformation.setCreatedDate(LocalDateTime.now());
            fourthFlightInformation.setModifiedDate(null);
            fourthFlightInformation.setPrice(450.38f);
            flightInformationDAO.save(fourthFlightInformation);

            CustomerProfile customerProfile = new CustomerProfile();
            CustomerProfile secondCustomerProfile = new CustomerProfile();
            CustomerProfile thirdCustomerProfile = new CustomerProfile();
            CustomerProfile fourthCustomerProfile = new CustomerProfile();
            customerProfile.setFirstName("John");
            customerProfile.setLastName("Leon");
            customerProfile.setPhoneNumber("02012345678");
            customerProfile.setCustomerEmail("john.leon@gmail.com");
            customerProfile.setFlightOrigin("Belfast");
            customerProfile.setFlightDestination("Heathrow");
            customerProfile.setTravelDate(LocalDate.of(2019, 07, 17));
            customerProfile.setFrequency(Frequency.WEEKLY.name());
            customerProfile.setCreatedBy("John");
            customerProfile.setModifiedBy(null);
            customerProfile.setCreatedDate(LocalDateTime.now());
            customerProfile.setModifiedDate(null);
            customerProfileDAO.save(customerProfile);

            secondCustomerProfile.setFirstName("Joyce");
            secondCustomerProfile.setLastName("Roy");
            secondCustomerProfile.setPhoneNumber("02398472347");
            secondCustomerProfile.setCustomerEmail("Joyce.Roy@somemail.com");
            secondCustomerProfile.setFlightOrigin("Dallas");
            secondCustomerProfile.setFlightDestination("Kochi");
            secondCustomerProfile.setTravelDate(LocalDate.of(2019, 10, 20));
            secondCustomerProfile.setFrequency(Frequency.UNCAPPED.name());
            secondCustomerProfile.setCreatedBy("Joyce");
            secondCustomerProfile.setModifiedBy(null);
            secondCustomerProfile.setCreatedDate(LocalDateTime.now());
            secondCustomerProfile.setModifiedDate(null);
            customerProfileDAO.save(secondCustomerProfile);

            thirdCustomerProfile.setFirstName("Laura");
            thirdCustomerProfile.setLastName("Laws");
            thirdCustomerProfile.setPhoneNumber("07564332270");
            thirdCustomerProfile.setCustomerEmail("Laura.Laws@test.com");
            thirdCustomerProfile.setFlightOrigin("Kochi");
            thirdCustomerProfile.setFlightDestination("Baltimore");
            thirdCustomerProfile.setTravelDate(LocalDate.of(2019, 10, 27));
            thirdCustomerProfile.setFrequency(Frequency.DAILY.name());
            thirdCustomerProfile.setCreatedBy("Laura");
            thirdCustomerProfile.setModifiedBy(null);
            thirdCustomerProfile.setCreatedDate(LocalDateTime.now());
            thirdCustomerProfile.setModifiedDate(null);
            customerProfileDAO.save(thirdCustomerProfile);

            fourthCustomerProfile.setFirstName("Riya");
            fourthCustomerProfile.setLastName("Richie");
            fourthCustomerProfile.setPhoneNumber("08763451102");
            fourthCustomerProfile.setCustomerEmail("Riya.Richie@test.com");
            fourthCustomerProfile.setFlightOrigin("Dubai");
            fourthCustomerProfile.setFlightDestination("Dallas");
            fourthCustomerProfile.setTravelDate(LocalDate.of(2019, 06, 25));
            fourthCustomerProfile.setFrequency(Frequency.MONTHLY.name());
            fourthCustomerProfile.setCreatedBy("Riya");
            fourthCustomerProfile.setModifiedBy(null);
            fourthCustomerProfile.setCreatedDate(LocalDateTime.now());
            fourthCustomerProfile.setModifiedDate(null);
            customerProfileDAO.save(fourthCustomerProfile);

            updateTravelInformation();/*
            travelInformation.setCustomerId(customerProfile.getId());
            travelInformationDAO.save(travelInformation);*/
        }
    }

    private void updateTravelInformation() {
        Iterable<FlightInformation> flightInformations = flightInformationDAO.findAll();
        Iterable<CustomerProfile> customerProfiles = customerProfileDAO.findAll();
        TravelInformation travelInformation = new TravelInformation();

        flightInformations.forEach((FlightInformation flightInformation)-> {
            customerProfiles.forEach((CustomerProfile customerProfile) -> {

                if ((Location.valueOf(flightInformation.getFlightOriginCode()).getValue().contains(
                        customerProfile.getFlightOrigin())) && (Location.valueOf(flightInformation.getFlightDestinationCode()).getValue().contains(
                        customerProfile.getFlightDestination()))) {
                    travelInformation.setCustomerId(customerProfile.getId());
                    travelInformation.setFlightOrigin(customerProfile.getFlightOrigin());
                    travelInformation.setFlightDestination(customerProfile.getFlightDestination());
                    travelInformation.setFlightInformationId(flightInformation.getId());
                    travelInformation.setPrice(flightInformation.getPrice());
                    travelInformation.setEmailSendDate(LocalDate.now());
                    travelInformation.setCreatedDate(LocalDateTime.now());
                    travelInformation.setSendEmail(true);
                    travelInformationDAO.save(travelInformation);
                    log.info("Sending the email to : " + customerProfile.getFirstName() + " " + customerProfile.getLastName());
                }
            });
        });

    }
}

