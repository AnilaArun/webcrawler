package com.webcrawler.apiproject.controller;

import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import com.webcrawler.apiproject.domain.FormSubmission;
import com.webcrawler.apiproject.domain.TravelInformation;
import com.webcrawler.apiproject.service.CustomerProfileService;
import com.webcrawler.apiproject.service.FlightInformationService;
import com.webcrawler.apiproject.service.TravelInformationService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@RunWith(SpringRunner.class)
public class WebCrawlerControllerTest {

    private static ClientAndServer mockServer;
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
    String url = "http://localhost:8082/h2";

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        webCrawlerController = new WebCrawlerController();
        date = LocalDate.of(2019, 07, 17);
        mockServer = startClientAndServer(8082);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        customerProfileService = EasyMock.createMock(CustomerProfileService.class);
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
        mockServer.when(HttpRequest.request()).respond(HttpResponse.response().withBody("success"));
        CustomerProfile customerProfile = new CustomerProfile();
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
        verify(travelInformationService, times(1)).save(any());
    }
    @After
    public void stop() {
        mockServer.stop();
    }
}
