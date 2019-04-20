package com.webcrawler.apiproject.service;

import com.webcrawler.apiproject.domain.CustomerProfile;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CustomerProfileServiceTest {
    @Mock
    CustomerProfile customerProfile;
    CustomerProfileService customerProfileService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerProfileService = new CustomerProfileService();

    }
}
