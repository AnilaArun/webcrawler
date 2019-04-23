package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.FlightInformationDAO;
import com.webcrawler.apiproject.domain.FlightInformation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FlightInformationServiceTest {
    FlightInformationService flightInformationService;
    @Mock
    FlightInformationDAO flightInformationDAO;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        flightInformationService = new FlightInformationService();
        flightInformationService.setFlightInformationDAO(flightInformationDAO);
    }

    @Test
    public void testSave() {
        flightInformationService.save(new FlightInformation());
        verify(flightInformationDAO, times(1)).save(new FlightInformation());

    }
}
