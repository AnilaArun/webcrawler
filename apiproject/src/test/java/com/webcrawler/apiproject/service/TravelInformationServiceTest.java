package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.TravelInformationDAO;
import com.webcrawler.apiproject.domain.TravelInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TravelInformationServiceTest {
    @InjectMocks
    TravelInformationService travelInformationService;
    @Mock
    TravelInformationDAO travelInformationDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        travelInformationService = new TravelInformationService();
        travelInformationService.setTravelInformationDAO(travelInformationDAO);
    }

    @Test
    public void testSave() {
        travelInformationService.save(new TravelInformation());
        verify(travelInformationDAO,times(1)).save(any());
    }
}
