package com.webcrawler.apiproject.util;

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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class TravelInfomationUtilTest {

    @Mock
    TravelInformationService travelInformationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Ignore
    @Test
    public void testTravelInformationDataUpdation() {
        TravelInformation travelInformation = new TravelInformation();
        travelInformation.setCustomerId(876);
        travelInformation.setFlightOrigin("Belfast");
        travelInformation.setFlightDestination("London");
        travelInformation.setFlightInformationId(7654);
        travelInformation.setPrice(300.009f);
        travelInformation.setEmailSendDate(LocalDate.now());
        travelInformation.setCreatedDate(LocalDateTime.now());
        travelInformation.setSendEmail(true);
        travelInformation.setTravelDate(LocalDate.of(2019, 07, 18));
        verify(travelInformationService, times(1)).save(travelInformation);
    }
}
