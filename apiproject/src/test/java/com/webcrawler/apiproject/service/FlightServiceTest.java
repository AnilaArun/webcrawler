package com.webcrawler.apiproject.service;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class FlightServiceTest {
    FlightService flightService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        flightService = new FlightService();
    }
}