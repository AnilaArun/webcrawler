package com.webcrawler.apiproject.controller;

import com.webcrawler.apiproject.domain.FormSubmission;
import com.webcrawler.apiproject.enums.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Arrays;
import java.util.List;

/**
 * @author n0283715
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class DemoController {
    @GetMapping("/")
    public String getForm(@RequestParam(required = false, value = "customerEmail") String customerEmail,
                          @RequestParam(required = false, value = "origin") String origin,
                          @RequestParam(required = false, value = "destination") String destination,
                          @RequestParam(required = false, value = "frequency") String frequency,
                          Model model) {
        FormSubmission formSubmission = new FormSubmission();
        formSubmission.setCustomerEmail(customerEmail);
        formSubmission.setDestination(destination);
        formSubmission.setOrigin(origin);
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


        return ResponseEntity.ok().body("Success");
    }
    @ModelAttribute("allFrequencies")
    public List<Frequency> populateLobs() {
        return Arrays.asList(Frequency.values());
    }

}
