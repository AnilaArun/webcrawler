package com.webcrawler.apiproject.controller;

import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FormSubmission;
import com.webcrawler.apiproject.enums.Frequency;
import com.webcrawler.apiproject.service.CustomerProfileService;
import com.webcrawler.apiproject.service.FlightInformationService;
import com.webcrawler.apiproject.service.TravelInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * This is the controller class to get the form, it also saves the customer data to CustomerProfile DB
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class WebCrawlerController {
    @Autowired
    private CustomerProfileService customerProfileService;

    @Autowired
    private FlightInformationService flightInformationService;

    @Autowired
    TravelInformationService travelInformationService;

    @GetMapping("/")
    public String getForm(@RequestParam(required = false, value = "flightName") String firstName,
                          @RequestParam(required = false, value = "flightNumber") String lastName,
                          @RequestParam(required = false, value = "customerEmail") String customerEmail,
                          @RequestParam(required = false, value = "phoneNumber") String phoneNumber,
                          @RequestParam(required = false, value = "flightOriginCode") String flightOrigin,
                          @RequestParam(required = false, value = "flightDestinationCode") String flightDestination,
                          @RequestParam(required = false, value = "travelDate") String travelDate,
                          @RequestParam(required = false, value = "frequency") String frequency,
                          Model model) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        FormSubmission formSubmission = new FormSubmission();
        formSubmission.setFirstName(firstName);
        formSubmission.setLastName(lastName);
        formSubmission.setPhoneNumber(phoneNumber);
        formSubmission.setCustomerEmail(customerEmail);
        formSubmission.setFlightDestination(flightDestination);
        formSubmission.setFlightOrigin(flightOrigin);
        formSubmission.setFrequency(frequency);
        //String hiddenDate = formSubmission.
        if (travelDate != null) {
            formSubmission.setTravelDate(LocalDate.of(Integer.parseInt(travelDate.substring(0,3)), Integer.parseInt(travelDate.substring(5,6)), Integer.parseInt(travelDate.substring(7,8))));
        }
        model.addAttribute("formSubmission", formSubmission);
        return "home";

    }

    @PostMapping("/")
    public ResponseEntity<String> saveCustomerData(@ModelAttribute @Validated FormSubmission formSubmission,
                                                          BindingResult bindingResult,
                                                          Model model) throws IOException {
        if (formSubmission != null) {
            if (formSubmission.getCustomerEmail() != null) {
                log.info("Please go to http://localhost:8080/confirmation to confirm the email: " + formSubmission.getCustomerEmail());
            }

            log.info("FormSubmission is not null");
            CustomerProfile customerProfile = customerProfileService.save(getCustomerProfile(formSubmission));
            travelInformationService.updateWithCustomerAndFlightData(customerProfile, flightInformationService);
            //This flighhtInformation Code is to mock the information as there is no webservice call to retrieve flightDetails
            /*FlightInformation flightInformation = flightInformationService.save(getFlightInformation(formSubmission, customerProfile));
            //This TravelInformation table is to store the customerId and flightId and mailSendDate etc to determine when the email was send
            TravelInformation travelInformation = travelInformationService.save(getTravelInformation(flightInformation.getId(), formSubmission, customerProfile));
*/
        }
        return ResponseEntity.ok().body("Success");
    }


    /*private FlightInformation getFlightInformation(FormSubmission formSubmission, CustomerProfile customerProfile) {
        FlightInformation flightInformation = new FlightInformation();
        flightInformation.setFlightOriginCode(formSubmission.getFlightOrigin().);
        return flightInformation;
    }

    private TravelInformation getTravelInformation(int flightId, FormSubmission formSubmission, CustomerProfile customerProfile) {
        TravelInformation travelInformation = new TravelInformation();
        return travelInformation;
    }*/

    private CustomerProfile getCustomerProfile(@Validated @ModelAttribute FormSubmission formSubmission) {
        CustomerProfile customerProfile= new CustomerProfile();
        customerProfile.setFirstName(formSubmission.getFirstName());
        customerProfile.setLastName(formSubmission.getLastName());
        customerProfile.setCustomerEmail(formSubmission.getCustomerEmail());
        customerProfile.setPhoneNumber(formSubmission.getPhoneNumber());
        customerProfile.setFlightOrigin(formSubmission.getFlightOrigin());
        customerProfile.setFlightDestination(formSubmission.getFlightDestination());
        customerProfile.setFrequency(formSubmission.getFrequency());
        customerProfile.setCreatedDate(LocalDateTime.now());
        customerProfile.setCreatedBy(formSubmission.getFirstName() + " " + formSubmission.getLastName());
        customerProfile.setTravelDate(formSubmission.getTravelDate());

        return customerProfile;
    }

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Exception e) {
        log.error(e.getMessage());

        return "error/5xx";
    }

    @GetMapping(value = "/confirmation")
    public String getConfirmationScreen() {
        return "confirmation";
    }

    @ModelAttribute("allFrequencies")
    public List<Frequency> populateLobs() {
        return Arrays.asList(Frequency.values());
    }

}