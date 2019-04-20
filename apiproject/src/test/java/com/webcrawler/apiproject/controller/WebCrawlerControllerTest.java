package com.webcrawler.apiproject.controller;

import com.webcrawler.apiproject.service.CustomerProfileService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class WebCrawlerControllerTest {

    @Mock
    CustomerProfileService customerProfileService;
    WebCrawlerController webCrawlerController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        webCrawlerController = new WebCrawlerController();
    }
}
