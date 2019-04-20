package com.webcrawler.apiproject.controller;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import com.webcrawler.apiproject.domain.FormSubmission;
import com.webcrawler.apiproject.enums.Frequency;
import com.webcrawler.apiproject.service.CustomerProfileService;
import com.webcrawler.apiproject.service.FlightInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author n0283715
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class WebCrawlerController {
    @Autowired
    private CustomerProfileService customerProfileService;
    @Autowired
    private FlightInformationService flightInformationService;

    @GetMapping("/")
    public String getForm(@RequestParam(required = false, value = "flightName") String firstName,
                          @RequestParam(required = false, value = "flightNumber") String lastName,
                          @RequestParam(required = false, value = "customerEmail") String customerEmail,
                          @RequestParam(required = false, value = "phoneNumber") String phoneNumber,
                          @RequestParam(required = false, value = "flightOriginCode") String flightOrigin,
                          @RequestParam(required = false, value = "flightDestinationCode") String flightDestination,
                          @RequestParam(required = false, value = "frequency") String frequency,
                          Model model) {
        FormSubmission formSubmission = new FormSubmission();
        formSubmission.setFirstName(firstName);
        formSubmission.setLastName(lastName);
        formSubmission.setPhoneNumber(phoneNumber);
        formSubmission.setCustomerEmail(customerEmail);
        formSubmission.setFlightDestination(flightDestination);
        formSubmission.setFlightOrigin(flightOrigin);
        formSubmission.setFrequency(frequency);
        model.addAttribute("formSubmission", formSubmission);
        return "home";

    }

    @PostMapping("/")
    public ResponseEntity<String> saveCustomerData(@ModelAttribute @Validated FormSubmission formSubmission,
                                                          BindingResult bindingResult,
                                                          Model model) throws IOException {
        if (formSubmission != null) {
            log.info("FormSubmission is not null");
        }

        //customerProfileDAO.save(getCustomerProfile(formSubmission));

        customerProfileService.save(getCustomerProfile(formSubmission));
        return ResponseEntity.ok().body("Success");
    }

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

        return customerProfile;
    }

    @ModelAttribute("allFrequencies")
    public List<Frequency> populateLobs() {
        return Arrays.asList(Frequency.values());
    }

}