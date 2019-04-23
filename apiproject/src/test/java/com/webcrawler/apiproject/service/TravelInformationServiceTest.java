package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.TravelInformationDAO;
import com.webcrawler.apiproject.domain.TravelInformation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TravelInformationServiceTest {
    @InjectMocks
    TravelInformationService travelInformationService;
    @Mock
    TravelInformationDAO travelInformationDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        travelInformationService = new TravelInformationService();
    }

    @Test
    public void testSave() {
        travelInformationService.save(new TravelInformation());
        verify(travelInformationDAO,times(1)).save(any());
    }
}
