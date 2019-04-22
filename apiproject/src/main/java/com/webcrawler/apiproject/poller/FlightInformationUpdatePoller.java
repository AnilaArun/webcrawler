package com.webcrawler.apiproject.poller;

import com.webcrawler.apiproject.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This is poller class to run the spring cron job to update the customer or save the flight information
 */
@Component
@Slf4j
public class FlightInformationUpdatePoller {
    private FlightService flightService;

    public FlightInformationUpdatePoller(FlightService flightService) {
        this.flightService = flightService;
    }

    @Scheduled(cron = "${poller.saveFlightDetails.cron}")
    public void scheduleFlightInformationSave() {
        // this scheduler runs every hour and pulls the flight details and save it to DB.
        log.info("Inside the FlightDetailsUpdatePoller");
        flightService.save();
    }

    @Scheduled(cron = "${poller.updateFlightDetails.cron}")
    public void scheduleFlightInformationUpdate() {
        // This can be used to run the scheduler everyday at 8:15 pm to saveId the existing record with new price
        log.info("Inside the FlightDetailsUpdatePoller");
        flightService.updateFlightInformation();
    }

    @Scheduled(cron = "${poller.customerUpdate.cron}")
    public void scheduleCustomerUpdate() {
        // This is used to run the scheduler everyday at 7 pm to send the latest flight deals to the customer
        log.info("Inside the FlightDetailsUpdatePoller");
        flightService.updateCustomer();
        flightService.removeOlderData();
    }
}
