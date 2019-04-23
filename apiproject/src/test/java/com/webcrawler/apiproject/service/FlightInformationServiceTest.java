package com.webcrawler.apiproject.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class FlightInformationServiceTest {
    FlightInformationService flightInformationService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        flightInformationService = new FlightInformationService();
    }

    @Test
    public void testSave() {

    }
}
