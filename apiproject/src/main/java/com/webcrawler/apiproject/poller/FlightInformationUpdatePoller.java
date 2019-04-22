package com.webcrawler.apiproject.poller;

import com.webcrawler.apiproject.service.FlightInformationUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This is poller class to run the spring cron job to update the customer or save the flight information
 */
@Component
@Slf4j
public class FlightInformationUpdatePoller {
    private FlightInformationUpdateService flightInformationUpdateService;

    public FlightInformationUpdatePoller(FlightInformationUpdateService flightInformationUpdateService) {
        this.flightInformationUpdateService = flightInformationUpdateService;
    }

    @Scheduled(cron = "${poller.saveFlightDetails.cron}")
    public void scheduleFlightInformationSave() {
        // this scheduler runs every hour and pulls the flight details and save it to DB.
        log.info("Inside the FlightDetailsUpdatePoller");
        flightInformationUpdateService.save();
    }

    /*@Scheduled(cron = "${poller.updateFlightDetails.cron}")
    public void scheduleFlightInformationUpdate() {
        // This can be used to run the scheduler everyday at 8:15 pm to saveId the existing record with new price
        log.info("Inside the FlightDetailsUpdatePoller");
        flightInformationUpdateService.saveId();
    }*/

    @Scheduled(cron = "${poller.customerUpdate.cron}")
    public void scheduleCustomerUpdate() {
        // This is used to run the scheduler everyday at 7 pm to send the latest flight deals to the customer
        log.info("Inside the FlightDetailsUpdatePoller");
        flightInformationUpdateService.customerUpdate();
        flightInformationUpdateService.delete();
    }

}
