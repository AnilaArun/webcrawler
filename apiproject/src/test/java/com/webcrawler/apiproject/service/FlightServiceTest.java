package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.dao.FlightInformationDAO;
import com.webcrawler.apiproject.dao.TravelInformationDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FlightServiceTest {
    FlightService flightService;
    @Mock
    FlightInformationDAO flightInformationDAO;
    @Mock
    TravelInformationDAO travelInformationDAO;
    @Mock
    CustomerProfileDAO customerProfileDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        flightService = new FlightService();
        flightService.setCustomerProfileDAO(customerProfileDAO);
        flightService.setFlightInformationDAO(flightInformationDAO);
        flightService.setTravelInformationDAO(travelInformationDAO);
    }

    @Test
    public void callUpdateCustomer() {
        flightService.updateCustomer();
        verify(flightInformationDAO, times(1)).findAll();
        verify(customerProfileDAO, times(1)).findAll();
    }
}