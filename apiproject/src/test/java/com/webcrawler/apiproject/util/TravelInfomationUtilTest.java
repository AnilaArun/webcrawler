package com.webcrawler.apiproject.util;

import com.webcrawler.apiproject.dao.TravelInformationDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import com.webcrawler.apiproject.domain.TravelInformation;
import com.webcrawler.apiproject.service.TravelInformationService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TravelInfomationUtilTest {

    @Mock
    TravelInformationDAO travelInformationDAO;
    TravelInformationService travelInformationService;
    @Mock
    CustomerProfile customerProfile;
    @Mock
    FlightInformation flightInformation;
    @Mock
    TravelInformation travelInformation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        travelInformationService = new TravelInformationService();
        travelInformationService.setTravelInformationDAO(travelInformationDAO);
    }

    @Test
    public void testTravelInformationDataUpdation() {
        TravelInformationUtil.setAndSaveTravelInformationData(travelInformationDAO, customerProfile, flightInformation);
        verify(travelInformationDAO, times(1)).save(any());
    }
}
