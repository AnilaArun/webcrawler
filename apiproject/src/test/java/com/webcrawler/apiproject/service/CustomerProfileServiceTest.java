package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class CustomerProfileServiceTest {
    @Mock
    CustomerProfile customerProfile;
    CustomerProfileService customerProfileService;
    @Mock
    CustomerProfileDAO customerProfileDAO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerProfileService = new CustomerProfileService();
        customerProfileService.setCustomerProfileDAO(customerProfileDAO);
    }

    @Test
    public void checkSave() {
        customerProfileService.save(customerProfile);
        verify(customerProfileDAO, times(1)).save(any());
    }
}
