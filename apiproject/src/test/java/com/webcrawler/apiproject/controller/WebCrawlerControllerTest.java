package com.webcrawler.apiproject.controller;

import com.webcrawler.apiproject.domain.FormSubmission;
import com.webcrawler.apiproject.service.CustomerProfileService;
import com.webcrawler.apiproject.service.FlightInformationService;
import com.webcrawler.apiproject.service.TravelInformationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class WebCrawlerControllerTest {
    WebCrawlerController webCrawlerController;
    LocalDate date;
    @Mock
    BindingResult bindingResult;
    @Mock
    Model model;
    @Mock
    CustomerProfileService customerProfileService;
    @Mock
    TravelInformationService travelInformationService;
    @Mock
    FlightInformationService flightInformationService;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        webCrawlerController = new WebCrawlerController();
        date = LocalDate.of(2019, 07, 17);
        webCrawlerController.setCustomerProfileService(customerProfileService);
        webCrawlerController.setFlightInformationService(flightInformationService);
        webCrawlerController.setTravelInformationService(travelInformationService);
    }

    @Test
    public void returnsHome() {
        Model model = Mockito.mock(Model.class);

        String result = webCrawlerController.getForm("myFirstName", "myLastName", "test@test.com",
                "02802881107", "Belfast", "London", date.toString(),"", model);
        assertTrue(result.equals("home"));
    }

    @Test
    public void callSaveCustomerData() throws Exception {
        FormSubmission formSubmission = new FormSubmission();
        formSubmission.setCustomerEmail("test@test.com");
        formSubmission.setFirstName("FirstName");
        formSubmission.setLastName("LastName");
        formSubmission.setPhoneNumber("02372847");
        formSubmission.setFlightOrigin("Belfast");
        formSubmission.setFlightDestination("London");
        formSubmission.setFrequency("DAILY");
        formSubmission.setTravelDate("2019-07-23");

        webCrawlerController.saveCustomerData(formSubmission, bindingResult, model);
        verify(customerProfileService, times(1)).save(any());
        verify(travelInformationService, times(1)).updateWithCustomerAndFlightData(any(), any());
    }
}
