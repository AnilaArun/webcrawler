package com.webcrawler.apiproject.util;

import com.webcrawler.apiproject.dao.TravelInformationDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import com.webcrawler.apiproject.domain.TravelInformation;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Utility class to set the TravelInformation data
 */
@Slf4j
public final class TravelInformationUtil {

    private TravelInformationUtil() {
    }

    /**
     * Creates the TravelInformation Object, update with values and save to database
     *
     * @param travelInformationDAO
     * @param customerProfile
     * @param flightInformation
     * @return travelInformation
     */
    public static TravelInformation setAndSaveTravelInformationData(TravelInformationDAO travelInformationDAO,
                                                                    CustomerProfile customerProfile,
                                                                    FlightInformation flightInformation) {
        TravelInformation travelInformation = new TravelInformation();
        travelInformation.setCustomerId(customerProfile.getId());
        travelInformation.setFlightOrigin(customerProfile.getFlightOrigin());
        travelInformation.setFlightDestination(customerProfile.getFlightDestination());
        travelInformation.setFlightInformationId(flightInformation.getId());
        travelInformation.setPrice(flightInformation.getPrice());
        travelInformation.setEmailSendDate(LocalDate.now());
        travelInformation.setCreatedDate(LocalDateTime.now());
        travelInformation.setSendEmail(true);
        travelInformation.setTravelDate(customerProfile.getTravelDate());
        travelInformationDAO.save(travelInformation);

        return  travelInformation;
    }
}
