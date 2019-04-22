package com.webcrawler.apiproject;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiprojectApplication {

    @Autowired
    CustomerProfileDAO customerProfileDAO;

    public static void main(String[] args) {
        SpringApplication.run(ApiprojectApplication.class, args);
    }
}
