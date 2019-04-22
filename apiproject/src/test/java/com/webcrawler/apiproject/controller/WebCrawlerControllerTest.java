package com.webcrawler.apiproject.controller;

import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import com.webcrawler.apiproject.domain.FormSubmission;
import com.webcrawler.apiproject.domain.TravelInformation;
import com.webcrawler.apiproject.service.CustomerProfileService;
import com.webcrawler.apiproject.service.FlightInformationService;
import com.webcrawler.apiproject.service.TravelInformationService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class WebCrawlerControllerTest {

    WebCrawlerController webCrawlerController;
    @Mock
    CustomerProfile customerProfile;
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
    private MockMvc mockMvc;
    @InjectMocks
    WebCrawlerController controller;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        webCrawlerController = new WebCrawlerController();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = LocalDate.of(2019, 07, 17);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        //mockMvc.perform(post("/")).andExpect(status().is(200));
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
        mockMvc.perform(post("/")).andExpect(status().is(200));
        CustomerProfile customerProfile = new CustomerProfile();
        //customerProfile.setCustomerEmail("test@test.com");
        FormSubmission formSubmission = new FormSubmission();
        formSubmission.setCustomerEmail("test@test.com");
        formSubmission.setFirstName("FirstName");
        formSubmission.setLastName("LastName");
        formSubmission.setPhoneNumber("02372847");
        formSubmission.setFlightOrigin("Belfast");
        formSubmission.setFlightDestination("London");
        formSubmission.setFrequency("DAILY");
        verify(customerProfileService, times(1)).save(any());
        verify(travelInformationService, times(1)).save(any());
        verify(flightInformationService, times(1)).save(any());
    }
}
